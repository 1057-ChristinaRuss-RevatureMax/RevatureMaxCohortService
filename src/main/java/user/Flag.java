package user;

public class Flag {
	private String associateEmail;
	private String content;
	private int id;
	private String type;
	
	public Flag(String associateEmail, String content, int id, String type) {
		super();
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
}
