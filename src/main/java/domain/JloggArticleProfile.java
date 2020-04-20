package domain;


import java.time.LocalDate;

public class JloggArticleProfile {

    private int aid;
    private LocalDate time;
    private String title;
    private String author;
    private int views;
    private String shown;

    public JloggArticleProfile() {
    }

    public JloggArticleProfile(int aid, LocalDate time, String title, String author, int views, String shown) {
        this.aid = aid;
        this.time = time;
        this.title = title;
        this.author = author;
        this.views = views;
        this.shown = shown;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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

    public String getShown() {
        return shown;
    }

    public void setShown(String shown) {
        this.shown = shown;
    }

    @Override
    public String toString() {
        return "JloggArticleProfile{" +
                "aid=" + aid +
                ", time=" + time +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", views=" + views +
                ", shown='" + shown + '\'' +
                '}';
    }
}
