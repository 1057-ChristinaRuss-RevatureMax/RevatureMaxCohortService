package models;

public class Flag {
    private String content;
    private int id;
    private String type;

    public Flag(String content, int id, String type) {
        this.content = content;
        this.id = id;
        this.type = type;
    }

    public Flag() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{" +
                "content=':'" + content + '\'' +
                ", id=':'" + id +
                ", type=':'" + type + '\'' +
                '}';
    }
}