package Beans;

public class Admin {
    private String adminID;
    private String password;
    private String adminName;

    public Admin(String adminID, String password, String adminName) {
        this.adminID = adminID;
        this.password = password;
        this.adminName = adminName;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
}
