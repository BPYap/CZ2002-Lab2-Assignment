import java.util.*;

public class Moblima {
	private static Scanner sc= new Scanner(System.in);
    
    public static Movie[] read_movie(boolean FilterEndOfShow)
    // available attributes : Movie_Title, Genre, Synopsis, Director, Cast, Age_Group, Status
    {
        String allmovie = utility.readContent("movie.txt");
        String [] raw_records = allmovie.split("\n");
        
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
    
	public static void main(String[] args) {
		int stafforcust;
		do {
			System.out.println("\n(1)Customer");
			System.out.println("(2)Staff");
			System.out.println("(3)Exit");
			System.out.print("\nChoose an option: ");
			stafforcust=sc.nextInt();
			
			switch(stafforcust) {
				case 1:
					int choice;
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
						System.out.println("(10)Exit");
						System.out.print("\nEnter the number of your choice: ");
						choice=sc.nextInt();
						
						switch(choice) {
							case 1:
								MovieGoer.listMovies();
								break;
							case 2:
                                MovieGoer.viewMovieDetails();
								break;
							case 3:
								break;
							case 4:
								break;
							case 5:
								break;
							case 6:
								break;
							case 7:
								break;
							case 8:
								break;
							case 9:
								break;
							case 10:
								break;
						}
					}while(choice<10);
					break;
				case 2:
                    System.out.println("\nPlease login.");   
                    CinemaStaff.login();
                    if(CinemaStaff.login_status == false)
                    {
                        stafforcust = -1;
                        break;
                    }
					
                    int choice2;
					do {
                        utility.printBorder();
						System.out.println("(1)Add Movie");
						System.out.println("(2)Set Movie Status");
						System.out.println("(3)Add Special Date");
						System.out.println("(4)Edit Special Date");
						System.out.println("(5)Edit Ticket Price");
						System.out.println("(6)Create Movie Show Time");
						System.out.println("(7)Show All Movie");
						System.out.println("(8)Show All Special Date");
						System.out.println("(9)Show All Ticket Price");
						System.out.println("(10)Show All Movie Show Time");
						System.out.println("(11)Exit");
						System.out.print("\nChoose an option: ");
						choice2=sc.nextInt();
                        switch(choice2) {
                        case 1:
                            CinemaStaff.addMovie();
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;
                        case 9:
                            break;
                        case 10:
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
