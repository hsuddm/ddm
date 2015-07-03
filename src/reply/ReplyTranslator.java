package reply;


public class ReplyTranslator {
	   static public Reply queryToReply(String str) {
	      String tmp = str.substring(3, str.length() - 5); //<p>  ,</p> cut
	      String[] replyEntry = tmp.split(",");

	      String id = replyEntry[0];
	      String userId = replyEntry[1];
	      float starRating = Float.parseFloat(replyEntry[2]);
	      String content = replyEntry[3];
	      String date = replyEntry[4];

	      String asw = replyEntry[5];
	      Boolean answer;
	      if (asw.equals("0")) {
	         answer = false;
	      } else {
	         answer = true;
	      }

	      Reply rData = new Reply(id, userId, starRating, content, date, answer);

	      return rData;
	   }

	   static public String ReplyToQuery(Reply reply) {

	      return "not implemented yet!";
	   }
	}