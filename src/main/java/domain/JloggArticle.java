package domain;


import java.time.LocalDate;

public class JloggArticle {

    private String id;
    private LocalDate time;
    private String title;
    private String author;
    private int views;
    private JloggArticleContent detail;

    public JloggArticle() {
    }

    public JloggArticle(String id, LocalDate time, String title, String author, int views) {
        this.id = id;
        this.time = time;
        this.title = title;
        this.author = author;
        this.views = views;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public JloggArticleContent getDetail() {
        return detail;
    }

    public void setDetail(JloggArticleContent detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "JloggArticle{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", views=" + views +
                ", detail=" + detail +
                '}';
    }
}
