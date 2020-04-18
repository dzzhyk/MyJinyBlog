package domain;

public class JloggGeneral {
    private String title;
    private String name;
    private String description;
    private String htmlDescription;
    private String htmlCopyright;
    private String beian;

    public JloggGeneral() {
    }

    public JloggGeneral(String title, String name, String description, String htmlDescription, String htmlCopyright, String beian) {
        this.title = title;
        this.name = name;
        this.description = description;
        this.htmlDescription = htmlDescription;
        this.htmlCopyright = htmlCopyright;
        this.beian = beian;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }

    public String getHtmlCopyright() {
        return htmlCopyright;
    }

    public void setHtmlCopyright(String htmlCopyright) {
        this.htmlCopyright = htmlCopyright;
    }

    public String getBeian() {
        return beian;
    }

    public void setBeian(String beian) {
        this.beian = beian;
    }

    @Override
    public String toString() {
        return "JloggGeneral{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", htmlDescription='" + htmlDescription + '\'' +
                ", htmlCopyright='" + htmlCopyright + '\'' +
                ", beian='" + beian + '\'' +
                '}';
    }
}
