import java.util.*;

public class CinemaStaff
{
    private static String password = "123456";
    public static boolean login_status = false;
    public static Scanner sc = new Scanner(System.in);
    
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
    
    public static void listMovies(boolean FilterEndOfShow, boolean FilterComingSoon) 
    {
        Movie[] movies = Database.read_movie(FilterEndOfShow, FilterComingSoon);
        
        String widths = "30,18";
        utility.print_title_row("Movie Title, Status", widths);
        for(int i = 0; i < movies.length; i++)
        {
            String row = movies[i].getMovieTitle() + "," + movies[i].getStatus();
            utility.print_row(i+1, row, widths);
        }
	}
    
    public static void listCineplexs()
    {
        Cineplex[] cineplexs = Database.read_all_cineplex();
        String widths = "30,18";
        utility.print_title_row("Location, Number of cinema", widths);
        for(int i = 0; i < cineplexs.length; i++)
        {
            String row = cineplexs[i].getLocation() + "," + cineplexs[i].getNumberOfCinema();
            utility.print_row(i+1, row, widths);
        }
    }
    
    public static void listCinemas(Cineplex cineplex)
    {
        String widths = "20,18";
        utility.print_title_row("Cinema Code, Seats Capacity", widths);
        for(int i = 0; i < cineplex.cinema.length; i++)
        {
            String row = cineplex.cinema[i].getCinemaCode() + "," + cineplex.cinema[i].getSeatCapacity();
            utility.print_row(i+1, row, widths);
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
        System.out.println("    1. PG");
        System.out.println("    2. PG13");
        System.out.println("    3. M18");
        int choice;
        do
        {
            System.out.print("> ");
            choice = sc.nextInt();
        }while(choice <= 0 || choice > 3);
        String age_group = "NULL";
        switch(choice)
        {
            case 1:
                age_group = "PG";
                break;
            case 2:
                age_group = "PG13";
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
            System.out.print("> ");
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
        listMovies(false, false);
        Movie[] movies = Database.read_movie(false, false);
        int choice = 0;
        do
        {
            System.out.print("Enter index of movie to be updated: ");
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
            System.out.print("> ");
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
        int month = 0;
        do
        {
            System.out.print("Enter month: ");
            month = sc.nextInt();
        }while(month <= 0 || month > 12);
        int day = 0;
        do
        {
            System.out.print("Enter day: ");
            day = sc.nextInt();
        }while(month <= 0 || month > 31);
        double discount = 0;
        do
        {
            System.out.print("Enter discount: ");
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
        showAllSpecialDates();
        SpecialDate[] special_dates = Database.read_special_date();
        int choice = 0;
        do
        {
            System.out.print("Enter index of dates to be updated: ");
            choice = sc.nextInt();
        }while (choice <= 0 || choice > special_dates.length);
        
        SpecialDate selected_date = special_dates[choice-1];
        String old_record = selected_date.toString();
        double discount = 0;
        do
        {
            System.out.println("Enter new discount : ");
            discount = sc.nextDouble();
        }while(discount <= 0 || discount > 100);
        selected_date.setDiscount(discount);
        sc.nextLine();
        utility.updateFile("special_date.txt", old_record, selected_date.toString());
        
        System.out.println("Successfully updated new discount rate to " + selected_date.getDiscount() + "%");
    }
    
     public static void showAllSpecialDates()
    {
        SpecialDate[] special_dates = Database.read_special_date();
        String widths = "12,15,30";
        utility.print_title_row("Date, Discount(%), Remark", widths);
        for(int i = 0; i < special_dates.length; i++)
        {
            String row = special_dates[i].getDate() + "," + special_dates[i].getDiscount() + "," + special_dates[i].getRemark();
            utility.print_row(i+1, row, widths);
        }
    }   
    public static void editTicketPrice()
    {
        System.out.println("========== Edit Ticket Price==========");
        TicketPrice ticket_info = Database.read_ticket_price(); 
        int choice = 0;
        System.out.println("Select which to update: ");
        System.out.println("    1. Adult Ticket Price          (current: S$" + ticket_info.getAdult() + ")");
        System.out.println("    2. Children Ticket Price       (current: S$" + ticket_info.getChildren() + ")");
        System.out.println("    3. Senior Citizen Ticket Price (current: S$" + ticket_info.getSenior() + ")");
        System.out.println("    4. Platinum suites charge      (current: S$" + ticket_info.getPlatinum() + ")");
        do
        {
            System.out.print("> ");
            choice = sc.nextInt();
        }while(choice <= 0 || choice > 4);
        sc.nextLine();
        double amount = 0;
        do
        {
            System.out.print("Enter new amount: S$");
            amount = sc.nextDouble();
        }while(amount < 0);

        String old_record = ticket_info.toString();
        switch(choice)
        {
            case 1:
                ticket_info.setAdult(amount);
                break;
            case 2:
                ticket_info.setChildren(amount);
                break;
            case 3:
                ticket_info.setSenior(amount);
                break;
            case 4:
                ticket_info.setPlatinum(amount);
                break;
        }
        
        utility.updateFile("ticket.txt", old_record, ticket_info.toString());
        
        System.out.println("Successfully updated ticket price");
        
    }
    
    public static void showAllTicketPrice()
    {
        TicketPrice ticket_info = Database.read_ticket_price();
        String widths = "8,10,15,30";
        utility.print_title_row("Adult, Children, Senior Citizen, Platinum suite extra charge", widths);
        String row = "S$" + ticket_info.getAdult() + "," + "S$" +  ticket_info.getChildren() + "," + "S$" + ticket_info.getSenior() + "," + "S$" + ticket_info.getPlatinum();
        utility.print_row(1, row, widths);
    }
    
    public static void createMovieShowTime()
    // Assumptions : Cinema Staff will not create duplicate showtimes
    //               Last show time is 22:00 for simplicity
    {
        System.out.println("========== Create Movie Showtime ==========");
        System.out.print("Enter year: ");
        int year = sc.nextInt();
        int month = 0;
        do
        {
            System.out.print("Enter month: ");
            month = sc.nextInt();
        }while(month < 0 || month > 12);
        int day = 0;
        do
        {
            System.out.print("Enter day: ");
            day = sc.nextInt();
        }while(month < 0 || month > 31);
        int start_time = 0;
        do
        {
            System.out.print("Enter start_time (Eg: 1530): ");
            start_time = sc.nextInt();
        }while(start_time <= 0000 || start_time > 2000); // Set the start time for last movie of the day at 2000 for simplicity
        sc.nextLine();
        
        listMovies(true, true);
        Movie[] movies = Database.read_movie(true, true);
        int choice = 0;
        do
        {
            System.out.print("Choose a movie: ");
            choice = sc.nextInt();
        }while (choice <= 0 || choice > movies.length);
        sc.nextLine();
        String movie_title = movies[choice-1].getMovieTitle();
        
        listCineplexs();
        Cineplex[] cineplexs = Database.read_all_cineplex();
        choice = 0;
        do
        {
            System.out.print("Choose a cineplex: ");
            choice = sc.nextInt();
        }while (choice <= 0 || choice > cineplexs.length);
        sc.nextLine();
        String location = cineplexs[choice-1].getLocation();
        
        Cineplex selected_cineplex = cineplexs[choice-1];
        listCinemas(selected_cineplex);
        choice = 0;
        do
        {
            System.out.print("Choose a cinema: ");
            choice = sc.nextInt();
        }while (choice <= 0 || choice > selected_cineplex.cinema.length);
        sc.nextLine();
        String cinema_code = selected_cineplex.cinema[choice-1].getCinemaCode();
        
        ShowTime showtime = new ShowTime(year, month, day, start_time, movie_title, location, cinema_code);
        String record = showtime.toString();
        utility.addRecord("showtime.txt", record);
        System.out.println("Show Time ID: " + showtime.getListingID() + " is added into show time database.");
    }
}