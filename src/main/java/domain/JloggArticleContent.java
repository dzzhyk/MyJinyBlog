package domain;

public class JloggArticleContent {
    private int id;
    private String description;
    private String content;

    public JloggArticleContent() {
    }

    public JloggArticleContent(int id, String description, String content) {
        this.id = id;
        this.description = description;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
