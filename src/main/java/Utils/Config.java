package Utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    public static Dotenv appConfig = Dotenv.configure()
            .directory("src/test/resources")
            .filename("config.properties")
            .systemProperties()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();
}