package ecommerce.model;

public class UserSignup {
    private int userid;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String role;


    public UserSignup(String firstName, String lastName, String username, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserSignup() {
    }

    public UserSignup(int id, String firstName, String lastName, String userName, String email, String password, String role) {
        this.userid = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return userid;
    }

    public void setId(int id) {
        this.userid = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserSignup{" +
                "id=" + userid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
