package seg.user.interface3125.thehominator;

/**
 * Created by Khalid on 2015-07-24.
 */
public class User {
   private String firstName, lastName, address, email, username, password, community;
    int uid, loggedIn;

    public User(String firstname, int uid, String email, String username, String password, String lastname, String address, String community) {
        this.firstName = firstname;
        this.uid = uid;
        this.email = email;
        this.lastName = lastname;
        this.username = username;
        this.address = address;
        this.password = password;
        this.community = community;
    }

    public User(String firstname, String lastname, String email, String username, String password, String address, String community, int loggedIn) {
        this.firstName = firstname;
        this.email = email;
        this.lastName = lastname;
        this.username = username;
        this.address = address;
        this.password = password;
        this.community = community;
        this.loggedIn = loggedIn;

    }

    // Getters
    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.password;
    }
    public String getAddress(){
        return this.address;
    }
    public String getCommunity(){
        return this.community;
    }
    public String getEmail(){
        return this.email;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public int getLoggedIn(){
        return this.loggedIn;
    }

    public int getUID(){
        return this.uid;
    }

    // Setters
    public void setFirstName(String x){
        this.firstName = x;
    }

    public void setLastName(String x){
       this.password = x;
    }
    public void setAddress(String x){
       this.address = x;
    }
    public void setFCommunity(String x){
       this.community = x;
    }
    public void setUsername(String x){
        this.username = x;
    }

    public void setEmail(String x){
        this.email = x;
    }

    public void setLoggedIn(int x){
        this.loggedIn = x;
    }

    public void setPassword(String x){
        this.password = x;
    }


}
