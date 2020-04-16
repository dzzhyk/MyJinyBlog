package domain;

public class JloggUser {
    private String username;
    private String passwd;
    private String selfdes;
    private String email;
    private String github;
    private String csdn;

    public JloggUser() {
    }

    public JloggUser(String username, String passwd) {
        this.username = username;
        this.passwd = passwd;
    }

    public JloggUser(String username, String passwd, String selfdes, String email, String github, String csdn) {
        this.username = username;
        this.passwd = passwd;
        this.selfdes = selfdes;
        this.email = email;
        this.github = github;
        this.csdn = csdn;
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

    public String getSelfdes() {
        return selfdes;
    }

    public void setSelfdes(String selfdes) {
        this.selfdes = selfdes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getCsdn() {
        return csdn;
    }

    public void setCsdn(String csdn) {
        this.csdn = csdn;
    }

    @Override
    public String toString() {
        return "JloggUser{" +
                "username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", selfdes='" + selfdes + '\'' +
                ", email='" + email + '\'' +
                ", github='" + github + '\'' +
                ", csdn='" + csdn + '\'' +
                '}';
    }
}
