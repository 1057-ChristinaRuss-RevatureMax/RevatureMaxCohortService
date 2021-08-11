package models;

public class FlagDto {
    private String associateEmail;
    private String content;
    private int id;
    private String type;

    public FlagDto(String associateEmail, String content, int id, String type) {
        this.associateEmail = associateEmail;
        this.content = content;
        this.id = id;
        this.type = type;
    }

    public String getAssociateEmail() {
        return associateEmail;
    }

    public void setAssociateEmail(String associateEmail) {
        this.associateEmail = associateEmail;
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
                "associateEmail=':'" + associateEmail + '\'' +
                ", content=':'" + content + '\'' +
                ", id=':'" + id +
                ", type=':'" + type + '\'' +
                '}';
    }
}