import java.util.*;

public class Database
{
    public static Movie[] read_movie(boolean FilterEndOfShow)
    // available attributes : Movie_Title, Genre, Synopsis, Director, Cast, Age_Group, Status
    {
        String [] raw_records = utility.readContent("movie.txt");
        Movie[] movies = new Movie[raw_records.length];
        
        int count = 0; // end of show count
        int temp = 0;
        
        for (int i = 0; i < raw_records.length; i++)
        {
            if(FilterEndOfShow && raw_records[i].contains("End of Showing"))
            {
                count++;
                continue;
            }
            movies[temp] = new Movie(raw_records[i]);
            temp++;
        }
        
        if(count > 0)
        {
            movies = Arrays.copyOfRange(movies, 0, movies.length - count);
        }

        return movies;
    }
    
    public static SpecialDate[] read_special_date()
    {
        String[] raw_records = utility.readContent("special_date.txt");
        SpecialDate[] special_dates = new SpecialDate[raw_records.length];
        for(int i = 0; i < special_dates.length; i++)
        {
            special_dates[i] = new SpecialDate(raw_records[i]);
        }
        return special_dates;
    }
    
/*     public static ShowTime[] read_show_time()
    {
        
    }
*/
    public static TicketPrice read_ticket_price()
    {
        String raw_record = utility.readContent("ticket.txt")[0];
        return new TicketPrice(raw_record);
    }

    public static Review[] read_review(){
        String [] raw_records = utility.readContent("review.txt");
        Review[] reviews = new Review[raw_records.length];
        
        for(int i=0;i < raw_records.length;i++){
            reviews[i] = new Review(raw_records[i]);
        }
        
        return reviews;
    }
    
/*     public static Transaction[] read_transaction()
    {
        
    } */
/*     public static Cineplex[] read_cineplex() {
    	String [] raw_records = utility.readContent("cineplex.txt");
        Cineplex[] cineplexs = new Cineplex[raw_records.length];
        
        for(int i=0;i < raw_records.length;i++){
            cineplexs[i] = new Cineplex(raw_records[i]);
        }
        
        return cineplexs;
    } */

public static Cineplex read_cineplex(String location) 
{
    	String [] raw_records = utility.readContent("cineplex.txt");
        Cineplex[] cineplexs = new Cineplex[raw_records.length];
        
        for(int i=0;i < raw_records.length;i++){
            cineplexs[i] = new Cineplex(raw_records[i]);
            if (cineplexs[i].getLocation().equals(location))
            {
                return cineplexs[i];
            }
        }
        
        return null;
}
 
 
// example call: 
/* Cineplex[] cineplexes = Database.read_cineplex();
for(int i = 0; i < cineplexes.length; i++)
{
    System.out.println(cineplexes[i].getLocation() + ": ");
    for(int j = 0; j < cineplexes[i].getNumberOfCinema(); j++)
    {
         System.out.println(cineplexes[i].cinema[j].toString());
    }
} */
    
}