package domain;

public class JloggUser {
    private String uid;
    private String username;
    private String passwd;
    private String admin;

    public JloggUser() {
    }

    public JloggUser(String uid, String username, String passwd, String admin) {
        this.uid = uid;
        this.username = username;
        this.passwd = passwd;
        this.admin = admin;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "JloggUser{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", admin='" + admin + '\'' +
                '}';
    }
}
