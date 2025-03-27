package model;

import java.util.Objects;

/**Class for creating User objects*/
public class User {

    private String username;
    private String password;

    /** User structure */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }


    @Override
    public String toString(){

        return "User{" +
                "username'" + username + '\'' +
                "password'" + password + '\'' +
                '}';

    }

    /**updated equal properties*/
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof User user)){
            return false;
        }

        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword());

    }


    @Override
    public int hashCode(){
        return Objects.hash(getUsername(), getPassword());
    }

}
