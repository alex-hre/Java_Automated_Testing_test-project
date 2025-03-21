# Automated Testing for Saucedemo Login Page

## Project Overview
This project automates testing for the login page of [Saucedemo](https://www.saucedemo.com/) using **Selenium WebDriver**, **TestNG**, **Maven**, and **Page Object Model**. It supports parallel execution and logging via SLF4J.

## Test Scenarios

- UC-1: Test Login Form with Empty Credentials

  #### Enter any credentials into "Username" and "Password" fields.
  #### Clear the inputs.
  #### Click the "Login" button.
  #### Validate the error message: "Username is required".

- UC-2: Test Login Form with Missing Password
  
  #### Enter any credentials in the "Username" field.
  #### Enter a password.
  #### Clear the "Password" field.
  #### Click the "Login" button.
  #### Validate the error message: "Password is required".

- UC-3: Test Login with Valid Credentials
  #### Enter a valid username from the accepted users list.
  #### Enter the password "secret_sauce".
  #### Click the "Login" button.
  #### Validate the page title "Swag Labs" on the dashboard.

## Technologies Used
- **Java** (JDK 17)
- **Selenium WebDriver**
- **TestNG** (for test execution and reporting)
- **Maven** (dependency management)
- **WebDriverManager** (automated driver management)
- **SLF4J + Logback** (logging)
- **AssertJ** (fluent assertions)
- **Page Object Model (POM)** (for test structure)

## Setup & Installation
```sh
git clone <repository-url>
cd <repository-folder>
mvn clean install
```

## Run Tests
### Browser Selection
By default, tests run in **Chrome**. To run tests in **Firefox** or **Edge**, use the `-Dbrowser` parameter:
```sh
mvn test
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
```

### Parallel Execution
Tests are configured to run in parallel using TestNG. The `testng.xml` file sets `thread-count=6`.

## Test Options
| Test options | Description |
|-----------|------------|
| `testLoginEmptyUser` | Tests login with an empty username and password |
| `testLoginEmptyPassword` | Tests login with a missing password |
| `testSuccessfulLogin` | Verifies login success with valid credentials |

## Logging
- Logs are handled by **SLF4J + Logback**.
- Log format: `[HH:mm:ss] [LEVEL] - Message`.
- Logging configuration is in `logback.xml`.

