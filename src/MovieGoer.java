import java.util.*;

public class MovieGoer {
	public static Scanner sc = new Scanner(System.in);
    
	public static void listMovies() 
    {
        Movie[] movies = CinemaStaff.read_movie();
        for(int i = 0; i < movies.length; i++)
        {
            System.out.println(movies[i].getMovieTitle());
        }
	}
    
/*     public static void viewMovieDetails()
    {
        listMovies();
        movie_attribute[] attributes = {movie_attribute.Movie_Title, movie_attribute.Synopsis, movie_attribute.Director, movie_attribute.Cast};
		String [][] list= read_movie(attributes);
        System.out.println("Enter the index of movie you wish to know more about: ");
        int choice = 0;
        do{
            choice = sc.nextInt();
        }while (choice <= 0 || choice > list.length);
        
        utility.printBorder();
        for (int i = 0; i < attributes.length; i++)
        {
            System.out.println(attributes[i].name() + " : " + list[choice-1][i]);
        }
    } */
}
