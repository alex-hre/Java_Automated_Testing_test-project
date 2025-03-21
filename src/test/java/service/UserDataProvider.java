package service;

import model.User;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;

public class UserDataProvider {

    /** Loads the properties file containing user data */
    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = UserDataProvider.class.getClassLoader().getResourceAsStream("users.properties")) {

            if (input == null) {
                throw new RuntimeException("Cannot find users.properties file!");
            }

            /*Load the properties from the file into the Properties object.*/
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
        return properties;
    }

    /** Creates a Stream of "wrong" users using data from the properties file. */
    public static Stream<User> getWrongUsers() {

        Properties properties = loadProperties();

        return Stream.of(
                new User(properties.getProperty("wrongUser1.username"), properties.getProperty("wrongUser1.password")),
                new User(properties.getProperty("wrongUser2.username"), properties.getProperty("wrongUser2.password")),
                new User(properties.getProperty("wrongUser3.username"), properties.getProperty("wrongUser3.password"))
        );
    }

    /** Creates a Stream of "accepted" users using data from the properties file. */
    public static Stream<User> getAcceptedUsers() {

        Properties properties = loadProperties();

        return Stream.of(
                new User(properties.getProperty("acceptedUser1.username"), properties.getProperty("acceptedUser1.password")),
                new User(properties.getProperty("acceptedUser2.username"), properties.getProperty("acceptedUser2.password")),
                new User(properties.getProperty("acceptedUser3.username"), properties.getProperty("acceptedUser3.password"))
        );
    }

    /** DataProvider for passing the "wrong" users to test methods. */
    @DataProvider(name = "wrongUsers", parallel = true)
    public static Object[][] provideWrongUsers() {

        /* Convert the Stream of "wrong" users into a 2D array of Object[] for the DataProvider. */
        return getWrongUsers()
                .map(user -> new Object[]{user})
                .toArray(Object[][]::new);
    }

    /** DataProvider for passing the "accepted" users to test methods. */
    @DataProvider(name = "acceptedUsers", parallel = true)
    public static Object[][] provideAcceptedUsers() {

        /* Convert the Stream of "accepted" users into a 2D array of Object[] for the DataProvider. */
        return getAcceptedUsers()
                .map(user -> new Object[]{user})
                .toArray(Object[][]::new);
    }
}