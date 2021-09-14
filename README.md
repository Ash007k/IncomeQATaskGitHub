# Income QA Task - GitHub

This repository contains Solutions for Income QA Tasks.

**For Task 1 - GitHub Application/CLI:**
The solution is designed using Java 8, Rest Assured, POJO, Maven to make call GitHub API, store response in POJO and print the details in required format.

**For Task 2 - GitHub UI - Star count validation:**
The solution is designed using Java 8, Cucumber (BDD Framework), Selenium WebDriver with wrapper to initiate WebDriver, naviagte to required repo and validate star values in format k by calling Task 1 API.

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


Application will show user details along with Repo details.


At the end will ask confirmation to continue with y/N (default N), if no value or N provided will terminate the application; otherwise will ask to input GitHub user details again to show details.


In case if no repo found, will show `No Repository Available`


if wrong/invalid GitHub user details given will show `Not Found`


Please note due to GitHub API call rate limitation, only 60 request/per hour is allowed as anonymous user, and the application will show below Warning and will terminate the application when limit issue occur with message.  
`WARNING: Please consider adding GITHUB TOKEN to void rate limit, default 60
request/per hour`

Please follow below steps to generate access token.
https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token
And add in Environment Variable as `GITHUB_TOKEN`


The Application also support CLI parameter to directly show GitHub details without any user input during the run.
Run:
```
mvn compile exec:java "-Dexec.args=torvalds,ash007ok,ash007k"
```

[Please note by default, 30 repo details will be showed, in case wanted to change need to update `src/test/resources/config.properties`, max 100 allowed]

Directory Structure:
The Solution for the Application located in `src/main/java`
Main/ initiate class: src/main/java/Applications/Main.java
Pojo: src/main/java/Pojo
Git Services: src/main/java/Services/Git.java
Config: src/test/resources/config.properties

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


Feature file: src/test/resources/features/GitUiRepoStar.feature
stepDefinitions: src/test/java/stepDefinitions
Page object: src/test/java/Pages/Git.java
results: reports/cucumber-html-report.html
