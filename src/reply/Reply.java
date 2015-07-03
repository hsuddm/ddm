package reply;

public class Reply {
	   private String id;
	   private String userId;
	   private float starRating;
	   private String content;
	   private String date;
	   private Boolean answer;

	   public Reply() {
	      userId = null;
	      starRating = -1;
	      content = null;
	      date = null;
	      answer = false;
	   }

	   public Reply(String id, String userId, float starRating, String content, String date,
	         Boolean answer) {
	      this.id = id;
	      this.userId = userId;
	      this.starRating = starRating;
	      this.content = content;
	      this.date = date;
	      this.answer = answer;

	   }

	   public String getUserId() {
	      return userId;
	   }

	   public void setUserId(String userId) {
	      this.userId = userId;
	   }

	   public float getStarRating() {
	      return starRating;
	   }

	   public void setStarRating(float starRating) {
	      this.starRating = starRating;
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
	      return "Reply [userId=" + userId + ", starRating=" + starRating + ", content=" + content
	            + ", date=" + date + ", answer=" + answer + "]";
	   }

	   public Boolean getAnswer() {
	      return answer;
	   }

	   public void setAnswer(Boolean answer) {
	      this.answer = answer;
	   }

	   public String getId() {
	      return id;
	   }

	   public void setId(String id) {
	      this.id = id;
	   }

	}