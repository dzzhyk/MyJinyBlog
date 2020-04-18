package domain;

public class JloggUserProfile {

    private String uid;
    private String selfdes;
    private String email;
    private String github;
    private String csdn;

    public JloggUserProfile() {
    }

    public JloggUserProfile(String uid, String selfdes, String email, String github, String csdn) {
        this.uid = uid;
        this.selfdes = selfdes;
        this.email = email;
        this.github = github;
        this.csdn = csdn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
        return "JloggUserProfile{" +
                "uid='" + uid + '\'' +
                ", selfdes='" + selfdes + '\'' +
                ", email='" + email + '\'' +
                ", github='" + github + '\'' +
                ", csdn='" + csdn + '\'' +
                '}';
    }
}
