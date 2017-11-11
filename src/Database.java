import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.time.LocalDateTime;
import java.text.SimpleDateFormat;

public class Database
{
    public static Movie[] read_movie(boolean FilterEndOfShow)
    // available attributes : Movie_Title, Genre, Synopsis, Director, Cast, Age_Group, Status
    {
        String [] raw_records = utility.readContent("movie.txt");
        Movie[] movies = new Movie[raw_records.length];
        
        int count = 0; 
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
    
    public static Movie[] read_now_showing_movie()
    {
        String [] raw_records = utility.readContent("movie.txt");
        Movie[] movies = new Movie[raw_records.length];
        
        int count = 0; 
        int temp = 0;
        
        for (int i = 0; i < raw_records.length; i++)
        {
            if(raw_records[i].contains("NowShowing"))
            {
                movies[temp] = new Movie(raw_records[i]);
                temp++;
                count++;
            }
        }
        movies = Arrays.copyOfRange(movies, 0, count);

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
    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    	 LocalDateTime now = LocalDateTime.now();
//    	 System.out.println(dtf.format(now));
    	 
    	 
    } */
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
    
    public static Cineplex[] read_cineplex() {
    	String [] raw_records = utility.readContent("cineplex.txt");
        Cineplex[] cineplexs = new Cineplex[raw_records.length];
        
        for(int i=0;i < raw_records.length;i++){
            cineplexs[i] = new Cineplex(raw_records[i]);
        }
        
        return cineplexs;
    }

    public static Cineplex[] read_all_cineplex() 
    {
        String [] raw_records = utility.readContent("cineplex.txt");
        Cineplex[] cineplexs = new Cineplex[raw_records.length];

        for(int i=0;i < raw_records.length;i++){
            cineplexs[i] = new Cineplex(raw_records[i]);
        }

        return cineplexs;
    }

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
 
    public static ShowTime[] read_show_time()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmm");
        Date now = new Date();
        String curDateTimeStr = sdf.format(now); //get the current time in YYYYMMDDHHMM format and store in string
        
        String [] raw_records = utility.readContent("showtime.txt");
        ShowTime[] showtimes = new ShowTime[raw_records.length];
        ArrayList<ShowTime> result = new ArrayList<ShowTime>();

        for (int i = 0; i < raw_records.length; i++)
        {
            showtimes[i] = new ShowTime(raw_records[i]);
            String showDate = Integer.toString(showtimes[i].getYear()) + Integer.toString(showtimes[i].getMonth()) + Integer.toString(showtimes[i].getDay()) + Integer.toString(showtimes[i].getStartTime());
            Date showTime = null;
            try{showTime = sdf.parse(showDate);}
            
            catch(java.text.ParseException ex)
            {
                System.out.println("Something bad happen");
            }
            String showDateTimeStr = sdf.format(showTime);
            if (curDateTimeStr.compareTo(showDateTimeStr) == -1){
                result.add(showtimes[i]);
            }
        }
        ShowTime [] resultShowTime = result.toArray(new ShowTime[result.size()]);
        return resultShowTime;
    }

    public static ShowTime read_show_time(String listingID)
    {
        String [] raw_records = utility.readContent("showtime.txt");
        ShowTime[] showtimes = new ShowTime[raw_records.length];
        
        for (int i = 0; i < raw_records.length; i++)
        {
            showtimes[i] = new ShowTime(raw_records[i]);
            if (showtimes[i].getListingID().equals(listingID))
            {
                return showtimes[i];
            }
        }
        
        return null;
    }

    public static ShowTime[] read_show_time(String cineplex, String movie_title) 
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmm");
        Date now = new Date();
        String curDateTimeStr = sdf.format(now); //get the current time in ddMMyyyyHHmm format and store in string
        
    	String [] raw_records = utility.readContent("showtime.txt");
        ShowTime[] showtimes = new ShowTime[raw_records.length];
        ArrayList<ShowTime> result = new ArrayList<ShowTime>();

        for (int i = 0; i < raw_records.length; i++)
        {
        	showtimes[i] = new ShowTime(raw_records[i]);
        	String showDate = Integer.toString(showtimes[i].getYear()) + Integer.toString(showtimes[i].getMonth()) + Integer.toString(showtimes[i].getDay()) + Integer.toString(showtimes[i].getStartTime());
            Date showTime = null;
            try{showTime = sdf.parse(showDate);}
            catch(java.text.ParseException ex)
            {
            	ex.printStackTrace();
            }
        	String showDateTimeStr = sdf.format(showTime);
        	if (showtimes[i].getCineplexLocation().equals(cineplex) && showtimes[i].getMovieTitle().equals(movie_title) &&   curDateTimeStr.compareTo(showDateTimeStr) == -1){
        		result.add(showtimes[i]);
        	}
        }
        ShowTime [] resultShowTime = result.toArray(new ShowTime[result.size()]);
        return resultShowTime;
    } 

    public static Transaction[] read_transaction() 
    {
        String [] raw_records = utility.readContent("transaction.txt");
        Transaction[] transactions = new Transaction[raw_records.length];
        
        for(int i=0;i < raw_records.length;i++)
        {
            transactions[i] = new Transaction(raw_records[i]);
        }
        
        return transactions;
    }
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