package notice;

public class Notice {
	private String title;
	private String content;
	private String date;

	public Notice() {
		title = null;
		content = null;
		date = null;
	}

	public Notice(String title, String content, String date) {
		this.title = title;
		this.content = content;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "<p>" + title + "," + content + "," + date + "</p>";
	}

}
