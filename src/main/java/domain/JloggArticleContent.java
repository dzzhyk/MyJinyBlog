package domain;

public class JloggArticleContent {
    private int aid;
    private String description;
    private String path;

    public JloggArticleContent() {
    }

    public JloggArticleContent(int aid, String description, String path) {
        this.aid = aid;
        this.description = description;
        this.path = path;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "JloggArticleContent{" +
                "aid=" + aid +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
