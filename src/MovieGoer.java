import java.util.*;

public class MovieGoer {
	public static Scanner sc = new Scanner(System.in);
	

    public static String[][] read_movie(movie_attribute[] attributes)
    // available attributes : Movie_Title, Genre, Synopsis, Director, Cast, Age_Group, Status
    {
        String allmovie = utility.readContent("movie.txt");
        String [] movie = allmovie.split("\n");
        
        int count = 0;
        for (int i = 0; i < movie.length; i++)
        {
            if (movie[i].contains("End of Showing"))
            {
                String temp = movie[0];
                movie[0] = movie[i];
                movie[i] = temp;
                count++;
            }
        }
        movie = Arrays.copyOfRange(movie, count, movie.length);
        
        String [][] details = new String [movie.length][attributes.length];
        //int count=0;
        for(int i = 0; i < movie.length; i++){
            String [] moviename= movie[i].split("\\|");
            for (int j = 0; j < attributes.length; j++)
            {
                details[i][j] = moviename[attributes[j].ordinal()];
            }
           //if(moviename[6].equals("End of Showing")){
/*            for(int k=0;k<7;k++){
                details[count][k]=moviename[k];
                } */
                //count++;
            //}   
        }
/*        for(int z=count;z<movie.length;z++){
            details[z][0]="ignore";
        } */ 
        return details;
    }
    
	public static void listMovies() 
    {
        String[] column_title = {"Movie Title",  "Age group", "Status"};
        movie_attribute[] attributes = {movie_attribute.Movie_Title, movie_attribute.Age_Group, movie_attribute.Status};
		String [][] list= read_movie(attributes);
        utility.print_table(column_title, list);
	}
    
    /* public static void viewMovieDetails(){
        String [][] list= readmovie();
        
        for(int i=0;i<list.length;i++){
            if(list[i][6].equals("End of Showing")){
                continue;
            }
            int k=i+1;
            System.out.println("("+k+") "+list[i][0]);
        }
    } */
}
