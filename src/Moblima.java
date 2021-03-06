import java.util.*;

public class Moblima {
	private static Scanner sc= new Scanner(System.in);
    
    public static void listMovieShowTime(){
        ShowTime[] showtimes = Database.read_show_time();
        String widths = "12,12,35,18,25,20,25,18";
        utility.print_title_row("Date, Start time, Movie Title, Genre, Cineplex Location, Cinema Code, Cinema Class, Available Seat", widths);
        for(int i = 0; i < showtimes.length; i++)
        {
            Cineplex cineplex = Database.read_cineplex(showtimes[i].getCineplexLocation());
            String cinema_class = cineplex.getCinema(showtimes[i].getCinemaCode()).getCinemaClass();
            Movie movie = Database.read_movie(showtimes[i].getMovieTitle());
            String row = showtimes[i].getDate() + "," + showtimes[i].getStartTime() + "," + showtimes[i].getMovieTitle() + "," + movie.getGenre() + "," + showtimes[i].getCineplexLocation() + "," +
                            showtimes[i].getCinemaCode() + "," + cinema_class + "," + showtimes[i].getAvailableSeats();
            utility.print_row(i+1, row, widths);
        }
    }
    
    public static void showAllTicketPrice()
    {
        TicketPrice ticket_info = Database.read_ticket_price();
        String widths = "8,10,15,30,20";
        utility.print_title_row("Adult, Children, Senior Citizen, Platinum suite extra charge, 3D movie charge", widths);
        String row = "S$" + ticket_info.getAdult() + "," + "S$" +  ticket_info.getChildren() + "," + "S$" + ticket_info.getSenior() + "," + "S$" + ticket_info.getPlatinum() + "," + "S$" + ticket_info.get3D();
        utility.print_row(1, row, widths);
    }
    
	public static void main(String[] args) {
		int stafforcust = 0;
		do {
			System.out.println("\n(1)Customer");
			System.out.println("(2)Staff");
			System.out.println("(3)Exit");
            do
            {
                System.out.print("\nChoose an option: ");
                stafforcust=sc.nextInt();
            }while(stafforcust <= 0 || stafforcust > 3);
            sc.nextLine();
			
			switch(stafforcust) {
				case 1:
					int choice = 0;
					do {
						System.out.println("\n(1)List Movies");
						System.out.println("(2)View Movie Details");
						System.out.println("(3)View Movie Showtimes");
						System.out.println("(4)Check Seat Availability");
						System.out.println("(5)Book Tickets");
						System.out.println("(6)Check Transaction History");
						System.out.println("(7)List Current Top Sales Movies");
						System.out.println("(8)List Current Top Rated Movies");
						System.out.println("(9)Review Movie");
                        System.out.println("(10)Show current ticket price");
                        System.out.println("(11)Exit");
                        do
                        {
                            System.out.print("\nChoose an option: ");
                            choice=sc.nextInt();
                        }while(choice <= 0 || choice > 11);
						sc.nextLine();
						switch(choice) {
							case 1:
								MovieGoer.listMovies(true, false);
								break;
							case 2:
                                MovieGoer.viewMovieDetails();
								break;
							case 3:
                                listMovieShowTime();
								break;
							case 4:
                                MovieGoer.checkSeatAvailability();
								break;
							case 5:
                                MovieGoer.bookTickets();
								break;
							case 6:
                                MovieGoer.checkTransactionHistory();
								break;
							case 7:
                                MovieGoer.listTopSalesMovies();
								break;
							case 8:
                                MovieGoer.listTopRatedMovies();
								break;
							case 9:
                                MovieGoer.reviewMovie();
								break;
							case 10:
                                showAllTicketPrice();
								break;
                            case 11:
                                break;
						}
					}while(choice < 11);
					break;
				case 2:
                    System.out.println("\nPlease login.");   
                    CinemaStaff.login();
                    if(CinemaStaff.login_status == false)
                    {
                        stafforcust = -1;
                        break;
                    }
					
                    int choice2 = 0;
					do {
                        utility.printBorder();
						System.out.println("(1)Add Movie");
						System.out.println("(2)Set Movie Status");
                        System.out.println("(3)Show All Movie");
						System.out.println("(4)Add Special Date");
						System.out.println("(5)Edit Special Date");
                        System.out.println("(6)Show All Special Date");
						System.out.println("(7)Edit Ticket Price");
                        System.out.println("(8)Show All Ticket Price");
						System.out.println("(9)Create Movie Show Time");
						System.out.println("(10)Show All Movie Show Time");
						System.out.println("(11)Exit");
                        do
                        {
                            System.out.print("\nChoose an option: ");
                            choice2 = sc.nextInt();
                        }while(choice2 <= 0 || choice2 > 11);
                        sc.nextLine();
                        switch(choice2) {
                        case 1:
                            CinemaStaff.addMovie();
                            break;
                        case 2:
                            CinemaStaff.setMovieStatus();
                            break;
                        case 3:
                            CinemaStaff.listMovies(false, false);
                            break;
                        case 4:
                            CinemaStaff.addSpecialDate();
                            break;
                        case 5:
                            CinemaStaff.editSpecialDate();
                            break;
                        case 6:
                            CinemaStaff.showAllSpecialDates();
                            break;
                        case 7:
                            CinemaStaff.editTicketPrice();
                            break;
                        case 8:
                            showAllTicketPrice();
                            break;
                        case 9:
                            CinemaStaff.createMovieShowTime();
                            break;
                        case 10:
                            listMovieShowTime();
                            break;
                        case 11:
                            break;
                    }
                        }while(choice2 < 11);
					break;
				}
			}while(stafforcust<3);
		}
}
