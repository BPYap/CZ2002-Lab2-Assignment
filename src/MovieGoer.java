import java.util.*;

public class MovieGoer {
	public static Scanner sc = new Scanner(System.in);
    
    	public static Movie selectMovieTitle(){
		Movie[] movies = Database.read_movie(false);
        listMovies();
		System.out.println();
		int choice = 0;
		do
		{
		    System.out.print("Select an index from the movie titles: ");
		    choice = sc.nextInt();
		}while(choice <= 0 || choice > movies.length);
		sc.nextLine();

		return movies[choice-1];
    	}
    
    public static Cineplex selectCineplex(){
        Cineplex[] cineplex = Database.read_cineplex();
        String widths = "20";
        utility.print_title_row("Cineplex Location", widths);
        for(int i=0;i<cineplex.length;i++){
            String row = cineplex[i].getLocation();
            utility.print_row(i+1, row, widths);
        }
        int choice =0;
        do
        {
            System.out.println("Which cineplex you want to go?");
            choice = sc.nextInt();
        }while(choice <= 0 || choice > cineplex.length);
        sc.nextLine();
        
        return cineplex[choice-1];
    }
    
	public static void listMovies() 
    {
        Movie[] movies = Database.read_movie(true);
        
        String widths = "30,15,12,18";
        utility.print_title_row("Movie Title, Genre, Age_Rating, Status", widths);
        for(int i = 0; i < movies.length; i++)
        {
            String row = movies[i].getMovieTitle() + "," + movies[i].getGenre() + "," + movies[i].getAgeRating() + "," + movies[i].getStatus();
            utility.print_row(i+1, row, widths);
        }
	}
    
	 public static void viewMovieDetails(){
		listMovies();
		Movie movies[] = Database.read_movie(true);
		int choice = 0;
		do
		{
		    System.out.print("Enter the index of movie you wish to know more about: ");
		    choice = sc.nextInt();
		}while (choice <= 0 || choice > movies.length);
		sc.nextLine();
		utility.printBorder();
		System.out.println("Movie Title: " + movies[choice-1].getMovieTitle());
		System.out.println("Synopsis   : " + movies[choice-1].getSynopsis());
		System.out.println("Director   : " + movies[choice-1].getDirector());
		System.out.println("Cast       : " + movies[choice-1].getCast());
		utility.printBorder();
	    }
    
    public static void reviewMovie(){
        System.out.println("========== Review Movie ==========");
        String movie_title = selectMovieTitle().getMovieTitle();
        System.out.print("Enter your name: ");
        String reviewer = sc.nextLine();
        System.out.print("Enter your comment: ");
        String comment = sc.nextLine();
        int rating;
        do{
            System.out.print("Enter your rating (1-10): ");
            rating = sc.nextInt();
        }while(rating <= 0 || rating >10);
        sc.nextLine();
        
        Review review = new Review(movie_title, reviewer, comment, rating);
        String record = review.toString();
        utility.addRecord("review.txt",record);
        System.out.println("Your review has been added into review database");
    }
    
    public static void listReview(){
        System.out.println("========== View Movie Review ==========");
        String movie_title = selectMovieTitle().getMovieTitle();
        Review[] reviews = Database.read_review();
        
        System.out.println("\nMovie Title: " + movie_title);
        utility.printline();
        for(int i=0;i<reviews.length;i++){
            if(reviews[i].getMovieTitle().equals(movie_title)){
                System.out.println("Reviewer : " + reviews[i].getReviewer());
                System.out.println("Rating   : " + reviews[i].getRating());
                System.out.println('"'+ reviews[i].getComments() + '"');
                utility.printline();
            }
        }
    }
    
    public static void listTopRatedMovies(){
        System.out.println("========== Top 5 Now Showing Movies==========");
        Movie[] movies = Database.read_now_showing_movie();
        Review[] reviews = Database.read_review();
        
        String[] movielist = new String[movies.length];
        int[] amountofreviewer = new int[movielist.length];
        int[] totalrating = new int[movielist.length];
        double[] rating = new double[movielist.length];
        
        //initialise into 0
        for(int i=0;i<movielist.length;i++){
            amountofreviewer[i]=0;
        }
        
        for(int i=0;i<movielist.length;i++){
            totalrating[i]=0;
        }
        
        for(int i=0;i<movielist.length;i++){
            rating[i]=0.0;
        }
        //end of initialising
        
        for(int i=0;i<movies.length;i++){
            movielist[i]=movies[i].getMovieTitle();
        }
        
        for(int k=0;k<reviews.length;k++){
            //System.out.println(reviews[k].getMovieTitle());
            String movietitle = reviews[k].getMovieTitle();
            for(int j=0;j<movielist.length;j++){
                if(movietitle.equals(movielist[j])){
                    amountofreviewer[j]=amountofreviewer[j]+1;
                    totalrating[j]=totalrating[j]+reviews[k].getRating();
                    break;
                }
            }
        }
        
        //calculate the average
        for(int i=0;i<movies.length;i++){
            if(totalrating[i]!=0){
                rating[i]=(double)totalrating[i]/(double)amountofreviewer[i];
            }
        }
        
        //implement insertion sort
        insertionsort(rating, movielist);
        String widths = "30,20";
        utility.print_title_row("Movie Title, Average Rating", widths);
        for(int i=0;i<movielist.length;i++){
            String rating_print = String.format("%.1f", rating[i]); //pending
            String row = movielist[i]+","+rating_print;
            utility.print_row(i+1, row, widths);
        }
    }
    
    public static void checkSeatAvailability(){
        System.out.println("========== Check Seat Availability ==========");
        ShowTime[] showtime = Database.read_all_showtime();

        String movietitle=selectMovieTitle().getMovieTitle();
        String cineplex=selectCineplex().getLocation();
        
        for(int i=0;i<showtime.length;i++){
            if(showtime[i].getMovieTitle().equals(movietitle) && showtime[i].getCineplexLocation().equals(cineplex)){
                showtime[i].printSeatLayout();
            }
        }
    }
    
    public static void bookTickets(){
        System.out.println("========== Book Tickets ==========");
        
    }
    
    /* public static void Booking() {
		SpecialDate[] s1 = Database.read_special_date();
        TicketPrice t1 = Database.read_ticket_price();
        Cineplex c1 = new Cineplex();
        ShowTime s2 = Database.read_show_time(listing_Id);
        
		TotalSum=0;
		int extracharge=0;
		int[] row = new int[12];
		int[] column = new int[12];
		// get year,date,time from the movie object
		for(int i=0;i<s1.length;i++) {
    	     if  (s1[i].getDate().equals(s2.getYear+'-'+s2.getMonth+'-'+s2.getDay ) ){
                extracharge = s1[i].getDiscount;
			}
		}
		
		if(c1.getCinema(cinema_code)[1].equals("Platinum Movie Suites")) {
			extracharge += t1.getPlatinum();
		}
		
			
		for(int i = number_of_adult;i>0;i--) {
			TotalSum += t1.getAdult()+extracharge;
			do{
	            System.out.println("Select a Seat");
				Scanner scn1 = new Scanner(System.in);
				System.out.println("    Please Enter Row Number:");
				int x = scn1.nextInt();
				System.out.println("    Please Enter Column Number:");
		 		int y = scn1.nextInt();
		 		//check seat automaticalies print out the layout 
           }while (!s2.checkSeat(x,y));
           int index=getIndexNeg1(purchased_column);
           s2.purchased_column[index]=y;
           s2.purchased_row[index]=x;
		}
		
		for( int j = number_of_child ;j>0;j--) {
			TotalSum += t1.getChild()+extracharge;
			do{
	            System.out.println("Select a Seat");
				Scanner scn2 = new Scanner(System.in);
				System.out.println("    Please Enter Row Number:");
				int x = scn2.nextInt();
				System.out.println("    Please Enter Column Number:");
		 		int y = scn2.nextInt();
		 		//check seat automaticalies print out the layout 
           }while (!s2.checkSeat(x,y));
           int index=getIndexNeg1(purchased_column);
           s2.purchased_column[index]=y;
           s2.purchased_row[index]=x;
		}
			
		
		for( int h = number_of_scitizen;h>0;h--) {
			TotalSum += t1.getSenior()+extracharge;
			do{
	            System.out.println("Select a Seat");
				Scanner scn3 = new Scanner(System.in);
				System.out.println("    Please Enter Row Number:");
				int x = scn3.nextInt();
				System.out.println("    Please Enter Column Number:");
		 		int y = scn3.nextInt();
		 		//check seat automaticalies print out the layout 
           }while (!s2.checkSeat(x,y));
           int index=getIndexNeg1(purchased_column);
           s2.purchased_column[index]=y;
           s2.purchased_row[index]=x;
		}
	} */
    
    //Function to sort array using insertion sort
    public static void insertionsort(double arr[],String str[])
    {
        for (int i=0; i<(arr.length-1); i++){
            for (int j=i+1; j<arr.length; j++){
                if (arr[i]<arr[j]){
                    double temp = arr[i];
                    String tempmovie = str[i];
                    arr[i] = arr[j];
                    str[i] = str[j];
                    arr[j] = temp;
                    str[j] = tempmovie;
                }
            }
        }
    }
}
