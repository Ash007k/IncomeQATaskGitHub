# Income QA Task - GitHub

This repository contains Solutions for Income QA Tasks.

**For Task 1 - GitHub Application/CLI:**
The solution is designed using Java 8, Rest Assured, POJO, Maven to make call GitHub API, store response in POJO and print the details in required format.

**For Task 2 - GitHub UI - Star count validation:**
The solution is designed using Java 8, Cucumber (BDD Framework), Selenium WebDriver with wrapper to initiate WebDriver, naviagte to required repo and validate star values in format k by calling Task 1 API.

**For Task 3:**
Branch name is created as `feature/qa_mad_yours`, apart from Git hygiene practice also enabled GitHub Actions for the purpose of demo/showcase to view the execution in real-time.

### Development tools and others
Below are the tools and library used for the development

|       |       | 
| :------------- | :----------: |
| Language | Java 8 |
| Design Pattern | Page Object Model with POJO for CLI application |
| Web Automation | Selenium WebDriver (with Selenide wrapper) |
| API | Rest Assured |
| BDD | Cucumber - Java8 |
| Unit Framework | Junit |
| Continuous Integration CI | GitHub Actions |
| Build tool | Maven |
| Container | Docker and DockerCompose |

### Prerequisites
To run the automation in local minimum need belows
* [Git](https://git-scm.com/downloads) [optional - can download the source zip]
* [Maven](https://maven.apache.org/download.cgi)
* [Java 8+](https://java.com/en/download/help/download_options.html)
* [Docker and Docker compose](https://docs.docker.com/get-docker/) [optional - but highly recommended for Task 2 to avoid local machine config issues]

### Clone repo
```
git clone https://github.com/Ash007k/IncomeQATaskGitHub.git
```
[or download the repo as zip]

### Steps to run Task 1 - GitHub CLI

1. After cloning or downloading repo navigate to corresponding directory
2. To run the application
```
mvn compile exec:java
```

After running above command, user has to input GitHub username and Enter (comma separated for more than 1).

Example: 

![image](https://user-images.githubusercontent.com/89639359/133173391-cf00690d-b024-4d6c-84f4-b4b1b3c16717.png)

![image](https://user-images.githubusercontent.com/89639359/133173428-2d2f9c65-cccf-4027-bdea-5f7b1a5bec4c.png)

or

![image](https://user-images.githubusercontent.com/89639359/133173477-e2f858cc-e892-4375-877d-95b689654537.png)


Application will show user details along with Repo details.
Example:

![image](https://user-images.githubusercontent.com/89639359/133173566-53994518-8b83-44e5-ad5b-c95a942734a0.png)

For more than one username

![image](https://user-images.githubusercontent.com/89639359/133173809-bd1f5775-f00d-4eff-9185-8656972a4d80.png)



At the end will ask confirmation to continue with y/N (default N), if no value or N provided will terminate the application; otherwise will ask to input GitHub user details again to show details.

![image](https://user-images.githubusercontent.com/89639359/133173881-09dafac0-31ae-4771-a044-f0b7fe4c5e3c.png)


In case if no repo found, will show `No Repository Available`

![image](https://user-images.githubusercontent.com/89639359/133173918-05f85e85-5884-45de-84ca-90ed615ec7f2.png)


if wrong/invalid GitHub user details given will show `Not Found`

![image](https://user-images.githubusercontent.com/89639359/133173949-fc75574e-8d38-4759-b0b8-4635fb0df966.png)

#### Setting GITHUB ACCESS TOKEN - Avoid rate limit

Please note due to GitHub API call rate limitation, only 60 request/per hour is allowed as anonymous user, and the application will show below Warning and will terminate the application when limit issue occur with message.  
`WARNING: Please consider adding GITHUB TOKEN to void rate limit, default 60
request/per hour`

Please follow below steps to generate access token.
https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token
And add in Environment Variable as `GITHUB_TOKEN`

#### Run App with CLI input

The Application also support CLI parameter to directly show GitHub details without any user input during the run.
Run:
```
mvn compile exec:java "-Dexec.args=torvalds,ash007ok,ash007k"
```

[Please note by default, 30 repo details will be showed, in case wanted to change need to update `src/test/resources/config.properties`, max 100 allowed]

Directory Structure:

The Solution for the Application located in `src/main/java`

Main/ initiate class: [src/main/java/Applications/Main.java](https://github.com/Ash007k/IncomeQATaskGitHub/blob/feature/qa_made_yours/src/main/java/Applications/Main.java)

Pojo: [src/main/java/Pojo](https://github.com/Ash007k/IncomeQATaskGitHub/tree/feature/qa_made_yours/src/main/java/Pojo)

Git Services: [src/main/java/Services/Git.java](https://github.com/Ash007k/IncomeQATaskGitHub/blob/feature/qa_made_yours/src/main/java/Services/Git.java)

Config: [src/test/resources/config.properties](https://github.com/Ash007k/IncomeQATaskGitHub/blob/feature/qa_made_yours/src/test/resources/config.properties)

### Steps to run Task 2 - GitHub UI Star Validation
1. After cloning or downloading repo navigate to corresponding directory
2. To run the test
```
mvn test
```
This will open Chrome browser, Search required user and navigate to repo and validate Star details in UI and API call by compare.

To run in different browser need to pass argument `-Dselenide.browser`
```
mvn test "-Dselenide.browser=edge"
```

The test reports are created in `reports/cucumber-html-report.html`

Sample Test result:

![image](https://user-images.githubusercontent.com/89639359/133174453-95088c20-e027-4ca0-bc75-d887814eacdd.png)

Feature file:

![image](https://user-images.githubusercontent.com/89639359/133174674-27c10f6f-6b42-47d8-b568-598f0fef0930.png)


The Solution for the Automation located in `src/main/test`

Feature file: [src/test/resources/features/GitUiRepoStar.feature](https://github.com/Ash007k/IncomeQATaskGitHub/blob/feature/qa_made_yours/src/test/resources/features/GitUiRepoStar.feature)

stepDefinitions: [src/test/java/stepDefinitions](https://github.com/Ash007k/IncomeQATaskGitHub/tree/feature/qa_made_yours/src/test/java/stepDefinitions)

Page object: [src/test/java/Pages/Git.java](https://github.com/Ash007k/IncomeQATaskGitHub/tree/feature/qa_made_yours/src/test/java/Pages)

results: reports/cucumber-html-report.html

### Showcase
For the purpose of demo/showcase the Task 1 and 2, have enabled the GitHub Actions Continous Integration.

![image](https://user-images.githubusercontent.com/89639359/133203448-d1fc1df1-b050-47a4-823f-5ccdf5b912a2.png)


Task 1: 

![image](https://user-images.githubusercontent.com/89639359/133203322-34b497b8-d8fc-4f75-b177-faa080fc49ac.png)


Task 2:

![image](https://user-images.githubusercontent.com/89639359/133203517-e44e95fe-9566-4483-9156-8fb63025cb97.png)





