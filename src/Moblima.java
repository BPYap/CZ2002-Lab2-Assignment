import java.util.Scanner;

public class Moblima {
	private static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) {
		int stafforcust;
		do {
			System.out.println("\n(1)Customer");
			System.out.println("(2)Staff");
			System.out.println("(3)Exit");
			System.out.println("\nEnter the number of your choice: ");
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
						System.out.println("\nEnter the number of your choice: ");
						choice=sc.nextInt();
						
						switch(choice) {
							case 1:
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
						}
					}while(choice<10);
					break;
				case 2:
					int choice2;
					do {
						System.out.println("\n(1)Login");
						System.out.println("(2)Add Movie");
						System.out.println("(3)Set Movie Status");
						System.out.println("(4)Add Special Date");
						System.out.println("(5)Edit Special Date");
						System.out.println("(6)Edit Ticket Price");
						System.out.println("(7)Create Movie Show Time");
						System.out.println("(8)Show All Movie");
						System.out.println("(9)Show All Special Date");
						System.out.println("(10)Show All Ticket Price");
						System.out.println("(11)Show All Movie Show Time");
						System.out.println("(12)Exit");
						System.out.println("\nEnter the number of your choice: ");
						choice2=sc.nextInt();
					}while(choice2<12);
					
					switch(choice2) {
					case 1:
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
					case 12:
						break;
				}
					break;
				}
			}while(stafforcust<3);
		}
}
