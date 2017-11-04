
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
	   return String.format("Reviewer " + this.reviewer +
			  " ,Comments " + this.comments +
			  ",Rating: " + this.rating);
			      
   }
}
