import java.util.*;

public class MovieGoer {
	public static Scanner sc = new Scanner(System.in);
    
	public static void listMovies() 
    {
        Movie[] movies = Moblima.read_movie(true);
        
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
        Movie movies[] = Moblima.read_movie(true);
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
