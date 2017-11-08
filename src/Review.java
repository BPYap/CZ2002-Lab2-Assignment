
public class Review {
   private String comments;
   private int rating;
   private String reviewer;
   private String movie_title;
   
   public Review( String movie_title,String reviewer, String comments,int rating) {
	   this.movie_title = movie_title;
	   this.reviewer = reviewer;
	   this.comments = comments;
	   this.rating = rating;
	   
   }
   
   public Review (String record){
        String[] attributes = record.split("\\|");
        movie_title = attributes[0];
        reviewer = attributes[1];
        comments = attributes[2];
        int record_rating = Integer.parseInt(attributes[3]);
        rating = record_rating;
   }
   
   public String getMovieTitle() {
	   return this.movie_title;
	   
   }
   public String getReviewer() {
	   return this.reviewer;
   }
   public String getComments() {
	   return this.comments;
   }
   public int getRating() {
	   return this.rating;
   }
   public String toString() {
        return movie_title + "|" + reviewer + "|" + comments + "|" +rating;
   }
}
