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
        if(!password.equals(input))
            System.out.println("Incorrect password");
        else
            login_status = true;
            System.out.println("Welcome Staff");
    }
    
    public static void addMovie()
    {
        System.out.println("========== Add Movie ==========");
        sc.nextLine();
        System.out.println("Enter movie title: ");
        String title = sc.nextLine();
        System.out.println("Enter genre: ");
        String genre = sc.nextLine();
        System.out.println("Enter movie synopsis: ");
        String synopsis = sc.nextLine();
        System.out.println("Enter director(s) for this movie: ");
        String director = sc.nextLine();
        System.out.println("Enter cast(s) for this movie: ");
        String cast = sc.nextLine();
        System.out.println("Select age group for this movie: ");
        System.out.println("    1. PG13");
        System.out.println("    2. NC16");
        System.out.println("    3. M18");
        int choice;
        do
        {
            choice = sc.nextInt();
        }while(choice <= 0 || choice > 3);
        String age_group = "NULL";
        switch(choice)
        {
            case 1:
                age_group = "PG13";
                break;
            case 2:
                age_group = "NC16";
                break;
            case 3:
                age_group = "M18";
                break;
        }
        System.out.println("Select movie status for this movie: ");
        System.out.println("    1. Coming Soon");
        System.out.println("    2. Now Showing");
        System.out.println("    3. Preview");
        do
        {
            choice = sc.nextInt();
        }while(choice <= 0 || choice > 3);
        String status = "NULL";
        switch(choice)
        {
            case 1:
                status = "Coming Soon";
                break;
            case 2:
                status = "Now Showing";
                break;
            case 3:
                status = "Preview";
                break;
        }
        
        String record = title + "|" + genre + "|" + synopsis + "|" + director + "|" + cast + "|" + age_group + "|" + status;
        utility.addRecord("movie.txt", record);
        System.out.println(title + " is added into movie database.");
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