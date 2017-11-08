import java.util.*;

public class MovieGoer {
	public static Scanner sc = new Scanner(System.in);
    
    public static void reviewMovie(){
        System.out.println("========== Review Movie ==========");
        System.out.println("Which movie you want to review");
        String movie = listAllMovies();
        System.out.print("Enter your comment: ");
        String dummy = sc.nextLine();
        String comment = sc.nextLine();
        System.out.print("Enter your name: ");
        String reviewer = sc.nextLine();
        System.out.print("Enter your rating (1-10): ");
        int rating;
        do{
            rating = sc.nextInt();
        }while(rating<0 || rating >10);
        
        Review review = new Review(movie, reviewer, comment, rating);
        String record = review.toString();
        utility.addRecord("review.txt",record);
        System.out.println("Your review is added into review database");
    }
    
    public static void listReview(){
        String movie;
        movie = listAllMovies();
        Review[] reviews = Database.read_review();
        
        for(int i=0;i<reviews.length;i++){
            if(reviews[i].getMovieTitle().equals(movie)){
                String row = "Movie Title : "+reviews[i].getMovieTitle()+"\n"+"Reviewer :    "+reviews[i].getReviewer()+"\n"+"Comment :    "+reviews[i].getComments()+"\n"+"Rating :    "+reviews[i].getRating();
                System.out.println(row);
            }
        }
    }
    
    public static String listAllMovies(){
        String movie;
        
        Movie[] movies = Database.read_movie(false);
        
        for(int i=0;i<movies.length;i++){
            int k=i+1;
            System.out.println("("+k+") : "+movies[i].getMovieTitle());
        }
        
        int choice = 0;
        choice = sc.nextInt();
        
        movie=movies[choice-1].getMovieTitle();
        
        return movie;
    }
    
	public static void listMovies() 
    {
        Movie[] movies = Database.read_movie(true);
        
        String widths = "30,15,12,18";
        utility.print_title_row("Movie Title, Genre, Age_Rating, Status", widths);
        for(int i = 0; i < movies.length; i++)
        {
            String row = movies[i].getMovieTitle() + "," + movies[i].getGenre() + "," + movies[i].getAgeRating() + "," + movies[i].getStatus();
            utility.print_row(i+1, row, widths);
        }
	}
    
    public static void viewMovieDetails()
    {
        listMovies();
        System.out.print("Enter the index of movie you wish to know more about: ");
        Movie movies[] = Database.read_movie(true);
        int choice = 0;
        do
        {
            choice = sc.nextInt();
        }while (choice <= 0 || choice > movies.length);
        sc.nextLine();
        utility.printBorder();
        System.out.println("Movie Title: " + movies[choice-1].getMovieTitle());
        System.out.println("Synopsis   : " + movies[choice-1].getSynopsis());
        System.out.println("Director   : " + movies[choice-1].getDirector());
        System.out.println("Cast       : " + movies[choice-1].getCast());
    }
}
