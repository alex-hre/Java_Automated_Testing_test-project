package test;

import model.User;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import page.LoginPage;
import service.UserDataProvider;

import static org.assertj.core.api.Assertions.assertThat;

public class ParametrizedLoginTest extends BaseTest {

    /** DataProvider to supply incorrect (wrong) user credentials for testing */
    @DataProvider(name = "wrongUsers", parallel = true)
    public Object[][] provideWrongUsers() {

        // Convert the Stream<User> returned from UserDataProvider.getWrongUsers() to Object[][]
        return UserDataProvider.getWrongUsers()
                .map(user -> new Object[]{user})
                .toArray(Object[][]::new);
    }

    /** DataProvider to supply accepted (valid) user credentials for testing */
    @DataProvider(name = "acceptedUsers", parallel = true)
    public Object[][] provideAcceptedUsers() {

        // Convert the Stream<User> returned from UserDataProvider.getAcceptedUsers() to Object[][]
        return UserDataProvider.getAcceptedUsers()
                .map(user -> new Object[]{user})
                .toArray(Object[][]::new);
    }

    /** Test method for testing login with an empty username and empty password */
    @Test(dataProvider = "wrongUsers")
    public void testLoginEmptyUser(User user) throws InterruptedException {

        String errorMessage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .clearFields(true, true)
                .login()
                .getErrorMessage();

        // Assert that the error message matches the expected message for an empty username
        assertThat(errorMessage)
                .as("Expected error message for empty username")
                .isEqualTo("Epic sadface: Username is required");
    }

    /** Test method for testing login with an empty password */
    @Test(dataProvider = "wrongUsers")
    public void testLoginEmptyPassword(User user) throws InterruptedException {

        String errorMessage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .clearFields(false, true)
                .login()
                .getErrorMessage();

        // Assert that the error message matches the expected message for an empty password
        assertThat(errorMessage)
                .as("Expected error message for empty password")
                .isEqualTo("Epic sadface: Password is required");
    }

    /** Test method for testing successful login with valid user credentials */
    @Test(dataProvider = "acceptedUsers")
    public void testSuccessfulLogin(User user) throws InterruptedException {

        LoginPage loginPage = new LoginPage(getDriver())
                .open()
                .inputFields(user)
                .login();


        // Assert that correct header is displayed (login should be successful)
        assertThat(loginPage.isRedirectionSuccessfull())
                .as("Expected successfull redirection")
                .isEqualTo("Swag Labs");
    }
}
