package Applications;

import Pojo.GitDetails;
import Services.Git;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String... input) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nGITHUB Users Details - Tool");
        String usernames = input.length > 0 ? input[0] : null;
        if (usernames == null) {
            do {
                System.out.println("\nPlease enter GITHUB usernames (comma separated, for more than 1):");
                usernames = scanner.nextLine();
                getAndPrintGitDetails(usernames);
                System.out.println("\nDo you want to continue? [y/N].");
                usernames = scanner.nextLine();
            } while(usernames.equalsIgnoreCase("y"));

            scanner.close();
        } else {
            getAndPrintGitDetails(usernames);
        }

    }

    private static void getAndPrintGitDetails(String usernames) {
        Stream.of(usernames.split(",")).map(String::trim).forEach(username -> {
            System.out.println("\n" + username + " repo details are:\n------------------");
            GitDetails userDetails = Git.getGitDetails(username);
            System.out.println(userDetails);
        });
    }
}