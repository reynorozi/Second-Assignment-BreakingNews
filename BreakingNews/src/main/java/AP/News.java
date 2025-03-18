package AP;
public class News
{
    private String title;
    private String description;
    private String sourceName;
    private String Author;
    private String url;
    private String publishedAt;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    public void setAuthor(String Author) {
        this.Author = Author;
    }
    public void setURL(String url) {
        this.url = url;
    }
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
    public String getTitle() {
        return title;
    }
    public String getURL() {
        return url;
    }
    public String getAuthor() {
        return Author;
    }
    public String getDescription() {
        return description;
    }
    public String getPublishedAt() {
        return publishedAt;
    }
    public String getSourceName() {
        return sourceName;
    }
    public void  displaynew() {
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Source Name: " + sourceName);
        System.out.println("Author: " + Author);
        System.out.println("URL: " + url);
        System.out.println("Published At: " + publishedAt);
    }
}



