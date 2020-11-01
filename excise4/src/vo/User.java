package vo;

/**
 * @author 13281
 */
public class User {
    private String userName;
    private String password;
    private String chrName;
    private String role;
    private String email;
    private String province;
    private String city;

    public User() {
        super();
    }

    public String getUserName() {
        return userName;
    }

    public User(String userName, String password, String chrName, String role, String email, String province, String city) {
        this.userName = userName;
        this.password = password;
        this.chrName = chrName;
        this.role = role;
        this.email = email;
        this.province = province;
        this.city = city;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getChrName() {
        return chrName;
    }

    public void setChrName(String chrName) {
        this.chrName = chrName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", chrName='" + chrName + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
