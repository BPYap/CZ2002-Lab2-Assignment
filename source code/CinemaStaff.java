import java.util.*;

public class CinemaStaff
{
    private static String password = "123456";
    public static boolean login_status = false;
    public static Scanner sc = new Scanner(System.in);
    
    public static void login()
    {
        System.out.println("Enter password: ");
        String input = sc.next();
        if(this.password != input)
            System.out.println("Incorrect password");
        else
            this.login_status = true;
    }
    
    public static void addMovie()
    {
        try 
        {
            FileWriter fwStream = new FileWriter("Movie.txt");
            BufferedWriter bwStream = new BufferedWriter(fwStream);
            PrintWriter pwStream = new PrintWriter(bwStream);
            int num ;
            for ( num = 0 ; num < 5 ; num++ )
                pwStream.println( "Number = " + num * 5 );
                pwStream.close();
        }
        catch(IOException e)
        {
            System.out.println( "IO Error at addMovie()!" + e.getMessage() );
            System.exit(0);
        }
    }

    
    public static void setMovieStatus()
    {
        
    }
    
    public static void addSpecialDate()
    {
        
    }
    
    public static void editSpecialDate()
    {
        
    }
    
    public static void editTicketPrice()
    {
        
    }
    
    public static void createMovieShowTime()
    {
        
    }
    
    public static void showAllMovies()
    {
        
    }
    
    public static void showAllSpecialDates()
    {
        
    }
    
    public static void showAllTicketPrice()
    {
        
    }
    
    public static void showAllMovieShowTime()
    {
        
    }
    
}