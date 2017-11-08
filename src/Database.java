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
    
    public static ShowTime[] read_show_time()
    {
        
    }

    public static TicketPrice[] read_ticket_price()
    {
        
    }

    public static Review[] read_review()
    {
        
    }
    
    public static Transaction[] read_transaction()
    {
        
    }
    
    
}