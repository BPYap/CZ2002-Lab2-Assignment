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
            System.out.print("Enter your rating (1-5): ");
            rating = sc.nextInt();
        }while(rating <= 0 || rating >5);
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
        System.out.println("========== Top 5 Rating Movies(Now Showing)==========");
        Movie[] movies = Database.read_now_showing_movie();
        Review[] reviews = Database.read_review();
        
        String[] movielist = new String[movies.length];
        int[] amountofreviewer = new int[movielist.length];
        int[] totalrating = new int[movielist.length];
        double[] rating = new double[movielist.length];
        
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
            String rating_print = String.format("%.1f", rating[i]); 
            String row = movielist[i]+","+rating_print;
            utility.print_row(i+1, row, widths);
        }
    }
    
    public static void checkSeatAvailability(){
        System.out.println("========== Check Seat Availability ==========");
        ShowTime[] showtime = Database.read_all_showtime();//pending

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
        
        //select movie
        Movie movie = selectMovieTitle();
        String movietitle = movie.getMovieTitle();
        
        //select cineplex
        Cineplex cineplex = selectCineplex();
        String cineplexlocation = cineplex.getLocation();
        
        //deacon pass back showtime
        ShowTime showtime[] = Database.read_show_time(cineplexlocation,movietitle);
        
        //print showtime to select
        String widths = "20";
        utility.print_title_row("Showtime", widths);
        for(int i=0;i<showtime.length;i++){
            String row = Integer.toString(showtime[i].getStartTime());
            utility.print_row(i+1, row, widths);
        }
        
        //select showtime
        int selectedshowtime = 0;
        do{
            System.out.println("Please select 1 showtime");
            selectedshowtime= sc.nextInt();
        }while(selectedshowtime<1 || selectedshowtime>showtime.length);
        
        //print showtime layout
        showtime[selectedshowtime].printSeatLayout();
        
        //check whether ticket amount > available seat
        int number_of_adult = 0;
        int number_of_child = 0;
        int number_of_scitizen = 0;
        int totalticket = 0;
        do{
            //number of adult
            do{
                System.out.print("How many adults? ");
                number_of_adult = sc.nextInt();
            }while(number_of_adult < 0);
            //number of child,check age rating
            if(movie.getAgeRating()=="PG" || (movie.getAgeRating()=="PG13" && number_of_adult>0)){
                do{
                System.out.print("How many children? ");
                number_of_child = sc.nextInt();
                }while(number_of_child < 0); 
            }
            //number of sc
            do{
                System.out.print("How many senior citizen? ");
                number_of_scitizen = sc.nextInt();
                String dummy = sc.nextLine();
            }while(number_of_scitizen < 0);
            //total_ticket
            totalticket = number_of_adult + number_of_child + number_of_scitizen;
        }while(totalticket > showtime[selectedshowtime].getAvailableSeats());
        
        //check seat
        int rows[] = new int[totalticket];
        int columns[] = new int[totalticket];
        int i=0;
        while(i<totalticket){
            System.out.println("Enter the row and column of the seat, e.g: 1,3");
            String seatline = sc.nextLine();
            String seat[] = seatline.split(",");
            int row = Integer.parseInt(seat[0]);
            int column = Integer.parseInt(seat[1]);
            if(showtime[selectedshowtime].checkSeat(row,column)==false){
                rows[i]=row;
                columns[i]=column;
                i++;
            }
        }
        
        //get user name,email,mobile number
        System.out.print("Name : ");
        String name = sc.nextLine();
        System.out.println();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.println();
        System.out.print("Mobile Number : ");
        String mobile_number = sc.nextLine();
        System.out.println();
        
        Transaction transaction = new Transaction(name,mobile_number,email,number_of_adult,number_of_child,number_of_scitizen,showtime[selectedshowtime].getListingID(),rows,columns);
        String record = transaction.toString();
        utility.addRecord("transaction.txt",record);
        System.out.println("The transaction( ID: "+transaction.getTransactionID()+") is made. Thanks for purchasing :)");
    }
    
    public static void viewMovieShowTimes(){
        Moblima.listMovieShowTime();
    }
    
    public static void listTopSalesMovies(){
        System.out.println("========== Top 5 Sales Movies(Now Showing)==========");
        Transaction transaction[] = Database.read_transaction();
        
    }
    
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
