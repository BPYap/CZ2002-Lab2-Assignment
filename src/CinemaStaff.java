import java.util.*;

public class CinemaStaff
{
    private static String password = "123456";
    public static boolean login_status = false;
    public static Scanner sc = new Scanner(System.in);
    
    private static void listMovies() 
    {
        Movie[] movies = Database.read_movie(false);
        
        String widths = "30,18";
        utility.print_title_row("Movie Title, Status", widths);
        for(int i = 0; i < movies.length; i++)
        {
            String row = movies[i].getMovieTitle() + "," + movies[i].getStatus();
            utility.print_row(i+1, row, widths);
        }
	}
    
    public static void login()
    {
        System.out.println("Enter password: ");
        String input = sc.nextLine();;
        if(!password.equals(input))
            System.out.println("Incorrect password");
        else
        {
            login_status = true;
            System.out.println("\nWelcome, Staff");
        }

    }
    
    public static void addMovie()
    {
        System.out.println("========== Add Movie ==========");
        System.out.print("Enter movie title: ");
        String title = sc.nextLine();
        System.out.print("Enter genre: ");
        String genre = sc.nextLine();
        System.out.print("Enter movie synopsis: ");
        String synopsis = sc.nextLine();
        System.out.print("Enter director(s) for this movie: ");
        String director = sc.nextLine();
        System.out.print("Enter cast(s) for this movie: ");
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
        
        Movie movie = new Movie(title, genre, synopsis, director, cast, age_group, status);
        String record = movie.toString();
        utility.addRecord("movie.txt", record);
        System.out.println(title + " is added into movie database.");
    }

    
    public static void setMovieStatus()
    {
        System.out.println("========== Set Movie Status ==========");
        listMovies();
        Movie[] movies = Database.read_movie(false);
        System.out.print("Enter index of movie to be updated: ");
        int choice = 0;
        do
        {
            choice = sc.nextInt();
        }while (choice <= 0 || choice > movies.length);
        
        Movie selected_movie = movies[choice-1];
        String old_record = selected_movie.toString();
        
        System.out.println("Select status for " + selected_movie.getMovieTitle() + " (Current Status: " + selected_movie.getStatus() + ") : ");
        System.out.println("    1. Coming Soon");
        System.out.println("    2. Now Showing");
        System.out.println("    3. Preview");
        System.out.println("    4. End of Showing");
        do
        {
            choice = sc.nextInt();
        }while(choice <= 0 || choice > 4);
        sc.nextLine();
        switch(choice)
        {
            case 1:
                selected_movie.setStatus("Coming Soon");
                break;
            case 2:
                selected_movie.setStatus("Now Showing");
                break;
            case 3:
                selected_movie.setStatus("Preview");
                break;
            case 4:
                selected_movie.setStatus("End of Showing");
                break;
        }
        utility.updateFile("movie.txt", old_record, selected_movie.toString());
        
        System.out.println("Successfully updated status to " + selected_movie.getStatus());
    }
    
    public static void addSpecialDate()
    {
        System.out.println("========== Add Special Date ==========");
        System.out.print("Enter year: ");
        int year = sc.nextInt();
        System.out.print("Enter month: ");
        int month = 0;
        do
        {
            month = sc.nextInt();
        }while(month <= 0 || month > 12);
        System.out.print("Enter day: ");
        int day = 0;
        do
        {
            day = sc.nextInt();
        }while(month <= 0 || month > 31);
        System.out.print("Enter discount: ");
        double discount = 0;
        do
        {
            discount = sc.nextDouble();
        }while(discount <= 0 || discount > 100);
        System.out.print("Enter remark: ");
        sc.nextLine();
        String remark = sc.nextLine();
        
        SpecialDate sp = new SpecialDate(year, month, day, discount, remark);
        String record = sp.toString();
        utility.addRecord("special_date.txt", record);
        System.out.println(remark + " is added into special_date database.");
    }
    
    public static void editSpecialDate()
    {
        System.out.println("========== Edit Special Date ==========");

        SpecialDate[] special_dates = Database.read_special_date();
        String widths = "12,15,30";
        utility.print_title_row("Date, Discount(%), Remark", widths);
        for(int i = 0; i < special_dates.length; i++)
        {
            String row = special_dates[i].getDate() + "," + special_dates[i].getDiscount() + "," + special_dates[i].getRemark();
            utility.print_row(i+1, row, widths);
        }
        System.out.print("Enter index of dates to be updated: ");
        int choice = 0;
        do
        {
            choice = sc.nextInt();
        }while (choice <= 0 || choice > special_dates.length);
        
        SpecialDate selected_date = special_dates[choice-1];
        String old_record = selected_date.toString();
        
        System.out.println("Enter new discount : ");
        double discount = 0;
        do
        {
            discount = sc.nextDouble();
        }while(discount <= 0 || discount > 100);
        selected_date.setDiscount(discount);
        sc.nextLine();
        utility.updateFile("special_date.txt", old_record, selected_date.toString());
        
        System.out.println("Successfully updated new discount rate to " + selected_date.getDiscount() + "%");
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