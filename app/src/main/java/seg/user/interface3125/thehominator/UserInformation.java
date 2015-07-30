package seg.user.interface3125.thehominator;

import java.security.PublicKey;

/**
 * Created by nnamdi on 15-07-29.
 */
public class UserInformation {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String address;

    public UserInformation(String username, String firstname, String lastname, String email, String address){
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
