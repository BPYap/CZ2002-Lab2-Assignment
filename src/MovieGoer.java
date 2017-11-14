import java.util.*;

public class MovieGoer {
	public static Scanner sc = new Scanner(System.in);
    
    public static Movie selectMovieTitle(){
    Movie[] movies = Database.read_movie(true, true);
    listMovies(true, true);
    System.out.println();
    int choice = 0;
    do
    {
        System.out.print("Select an index from the movie titles: ");
        choice = sc.nextInt();
        sc.nextLine();
    }while(choice <= 0 || choice > movies.length);


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
            System.out.print("Which cineplex you want to go? ");
            choice = sc.nextInt();
        }while(choice <= 0 || choice > cineplex.length);
        sc.nextLine();
        
        return cineplex[choice-1];
    }
    
	public static void listMovies(boolean FilterEndOfShow, boolean FilterComingSoon) 
    {
        Movie[] movies = Database.read_movie(FilterEndOfShow, FilterComingSoon);
        
        String widths = "30,15,12,18";
        utility.print_title_row("Movie Title, Genre, Age_Rating, Status", widths);
        for(int i = 0; i < movies.length; i++)
        {
            String row = movies[i].getMovieTitle() + "," + movies[i].getGenre() + "," + movies[i].getAgeRating() + "," + movies[i].getStatus();
            utility.print_row(i+1, row, widths);
        }
	}
    
	public static void viewMovieDetails(){
		listMovies(true, false);
		Movie movies[] = Database.read_movie(true, false);
		int choice = 0;
		do
		{
		    System.out.print("Enter the index of movie you wish to know more about: ");
		    choice = sc.nextInt();
            sc.nextLine();
		}while (choice <= 0 || choice > movies.length);
		utility.printBorder();
		System.out.println("Movie Title: " + movies[choice-1].getMovieTitle());
		System.out.println("Synopsis   : " + movies[choice-1].getSynopsis());
		System.out.println("Director   : " + movies[choice-1].getDirector());
		System.out.println("Cast       : " + movies[choice-1].getCast());
		utility.printBorder();
        String movie_title = movies[choice-1].getMovieTitle();
        Review[] reviews = Database.read_review();
        System.out.println("Reviews :");
        utility.printline();
        for(int i=0;i<reviews.length;i++){
            if(reviews[i].getMovieTitle().equals(movie_title)){
                System.out.println("Reviewer : " + reviews[i].getReviewer());
                System.out.println("Rating   : " + reviews[i].getRating());
                System.out.println('"'+ reviews[i].getComments() + '"');
                utility.printline();
            }
        }
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
    
    public static void listTopRatedMovies(){
        System.out.println("========== Top 5 Rating Movies(Now Showing)==========");
        Movie[] movies = Database.read_movie(true, true);
        Review[] reviews = Database.read_review();
        
        String[] movielist = new String[movies.length];
        int[] amountofreviewer = new int[movielist.length];
        int[] totalrating = new int[movielist.length];
        
        for(int i=0;i<movies.length;i++){
            movielist[i]=movies[i].getMovieTitle();
        }
        
        for(int k=0;k<reviews.length;k++){
            String movietitle = reviews[k].getMovieTitle();
            for(int j=0;j<movielist.length;j++){
                if(movietitle.equals(movielist[j])){
                    amountofreviewer[j]=amountofreviewer[j]+1;
                    totalrating[j]=totalrating[j]+reviews[k].getRating();
                    break;
                }
            }
        }
        
        int counter=0;
        for(int i=0;i<movielist.length;i++){
            if(amountofreviewer[i]>=2){
                movielist[counter]=movielist[i];
                amountofreviewer[counter]=amountofreviewer[i];
                totalrating[counter]=totalrating[i];
                counter++;
            }
        }
        
        String[] movielist_print = new String[counter];
        int[] amountofreviewer_print = new int[counter];
        int[] totalrating_print = new int[counter];
        for(int i=0;i<movielist_print.length;i++){
            if(amountofreviewer[i]>=2){
                movielist_print[i]=movielist[i];
                amountofreviewer_print[i]=amountofreviewer[i];
                totalrating_print[i]=totalrating[i];
            }
        }
        
        double[] rating = new double[counter];
        //calculate the average
        for(int i=0;i<movielist_print.length;i++){
            if(totalrating_print[i]!=0){
                rating[i]=(double)totalrating_print[i]/(double)amountofreviewer_print[i];
            }
        }
        
        //implement insertion sort
        insertionsort(rating, movielist_print,amountofreviewer_print);
        
        String widths = "30,20";
        utility.print_title_row("Movie Title, Average Rating", widths);
        for(int i=0;i<5;i++){
            if(i<counter){
                String prettyprint = String.format("%.1f",rating[i]);
                String row = movielist_print[i]+","+prettyprint;
                utility.print_row(i+1, row, widths);
            }else{
                String row = "-,NA";
                utility.print_row(i+1, row, widths);
            }
        }
    }
    
    public static void checkSeatAvailability(){
        System.out.println("========== Check Seat Availability ==========");
        ShowTime[] showtime = Database.read_show_time();

        String movietitle=selectMovieTitle().getMovieTitle();
        String cineplex=selectCineplex().getLocation();
        
        utility.printBorder();
        System.out.println("Seats allocation(s) for " + movietitle + " at " + cineplex + ": ");
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
        
        //select showtime
        ShowTime showtime[] = Database.read_show_time(cineplexlocation,movietitle); 
        //when there is no showtime 
        while(showtime.length==0){
            System.out.println("Sorry, there is no '" + movietitle + "' in " + cineplexlocation + ". Choose again");
            System.out.println();
            movie = selectMovieTitle();
            movietitle = movie.getMovieTitle();
            cineplex = selectCineplex();
            cineplexlocation = cineplex.getLocation();
            showtime = Database.read_show_time(cineplexlocation,movietitle); 
        }

        //print showtime to select
        String widths = "20,20,30,20,20";
        utility.print_title_row("Date,Showtime,Cinema Class,Promotion,Discount", widths);
        SpecialDate[] special_dates = Database.read_special_date();
        String discount;
        String promotion;
        for(int i=0;i<showtime.length;i++){
            discount = "NA";
            promotion = "NA";
            int year = showtime[i].getYear();
            int month = showtime[i].getMonth();
            int day = showtime[i].getDay();
            for(int j = 0; j < special_dates.length; j++)
            {
                if(special_dates[j].getYear() == year && special_dates[j].getMonth() == month && special_dates[j].getDay() == day)
                {
                    discount = special_dates[j].getDiscount() + "%";
                    promotion = special_dates[j].getRemark();
                    break;
                }
            }
            String row = year+"/"+month+"/"+day+","+Integer.toString(showtime[i].getStartTime()) + "," + cineplex.getCinema(showtime[i].getCinemaCode()).getCinemaClass() + "," + promotion + "," + discount;
            utility.print_row(i+1, row, widths);
        }
        
        //select showtime
        int selectedshowtime = 0;
        do{
            System.out.print("Please select one showtime: ");
            selectedshowtime= sc.nextInt();
        }while(selectedshowtime<1 || selectedshowtime>showtime.length);
        
        //minus 1 = index;
        selectedshowtime--;
        
        //print showtime layout
        showtime[selectedshowtime].printSeatLayout();
        String cinema_code = showtime[selectedshowtime].getCinemaCode();
        
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
            if(movie.getAgeRating().equals("PG") || (movie.getAgeRating().equals("PG13") && number_of_adult>0)){
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
        }while(totalticket > showtime[selectedshowtime].getAvailableSeats()||totalticket==0);
        
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
            int row_number = cineplex.getCinema(cinema_code).getNumberOfRows();
            int column_number = cineplex.getCinema(cinema_code).getNumberOfColumns();
            
            if(showtime[selectedshowtime].checkSeat(row,column)==false && localcheckseat(rows,columns,row,column,row_number,column_number)==false){
                rows[i]=row;
                columns[i]=column;
                i++;
            }else{
                System.out.println("The seat is booked by others. Choose again.");
            }
        }
        
        //get user name,email,mobile number
        System.out.print("Name : ");
        String name = sc.nextLine();
        System.out.print("Email : ");
        String email = sc.nextLine();
        System.out.print("Mobile Number : ");
        String mobile_number = sc.nextLine();
        
        //construct transaction
        Transaction transaction = new Transaction(name,mobile_number,email,number_of_adult,number_of_child,number_of_scitizen,showtime[selectedshowtime].getListingID(),rows,columns);
        String record = transaction.toString();
        utility.addRecord("transaction.txt",record);
        String old_record = showtime[selectedshowtime].toString();
        showtime[selectedshowtime].decrement_seat(rows, columns);
        utility.updateFile("showtime.txt", old_record, showtime[selectedshowtime].toString());
        System.out.println("The transaction( ID: "+transaction.getTransactionID()+") is made. Thanks for purchasing :)");
        System.out.println("\nThe receipt is at below: ");
        System.out.println("Cineplex: " + cineplexlocation);
        System.out.println("Cinema Hall: " + cinema_code);
        System.out.println("Name: "+name);
        System.out.println("Mobile Number: "+mobile_number);
        System.out.println("Email: "+email);
        System.out.println("Pax: "+totalticket);
        System.out.println("Total Fare: "+transaction.getTotalFare());
    }
    
    public static boolean localcheckseat(int rows[],int columns[], int row, int column, int row_number, int column_number){
        int rowlength=rows.length-1;
        if(row>row_number || column>column_number){
            return true;
        }
        for(int i=0;i<rowlength;i++){
            if(row==rows[i] && column==columns[i]){
                return true;   
            }
        }
        return false;
    }
    
    public static void checkTransactionHistory()
    {
        System.out.println("========== Check Transaction History ==========");
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your email address: ");
        String email = sc.nextLine();
        
        String widths = "45,20,15,15,15,15,35,30";
        utility.print_title_row("Transaction ID, Name, Email, No. Adult, No. Children, No. S.Citizen, Movie Title, Total Fare (S$)", widths);
        Transaction[] transactions = Database.read_transaction();
        for(int i = 0; i < transactions.length; i++)
        {
            if(transactions[i].getCustomerName().equals(name) && transactions[i].getEmailAddress().equals(email))
            {
                String movie_title = Database.read_show_time(transactions[i].getListingID()).getMovieTitle();
                String row = transactions[i].getTransactionID() + "," + transactions[i].getCustomerName() + "," + transactions[i].getEmailAddress() + "," + transactions[i].getNumberOfAdult() + "," +
                                transactions[i].getNumberOfChildren() + "," + transactions[i].getNumberOfSenior() + "," + movie_title + "," + transactions[i].getTotalFare();
                utility.print_row(i+1, row, widths);
            }
        }
        
    }
    
    public static void listTopSalesMovies(){
        System.out.println("========== Top 5 Sales Movies(Now Showing)==========");
        Transaction transaction[] = Database.read_transaction();
        Movie movies[] = Database.read_movie(true, true);
        String movielist[] = new String[movies.length];
        int movie_sale[] = new int[movies.length];
        
        for(int i=0;i<movies.length;i++){
            movielist[i]=movies[i].getMovieTitle();
        }
        
        for(int i=0;i<transaction.length;i++){
            String movietitle = Database.read_show_time(transaction[i].getListingID()).getMovieTitle();
            for(int j=0;j<movielist.length;j++){
                if(movietitle.equals(movielist[j])){
                    int total_ticket = transaction[i].getNumberOfAdult() + transaction[i].getNumberOfChildren() + transaction[i].getNumberOfSenior();
                    movie_sale[j]+=total_ticket;
                    break;
                }
            }
        }
        
        //implement insertion sort
        insertionsort_forint(movie_sale, movielist);
        
        String widths = "30,20";
        utility.print_title_row("Movie Title, Sales", widths);
        for(int i=0;i<5;i++){
            String row = movielist[i]+","+movie_sale[i];
            utility.print_row(i+1, row, widths);
        }
    }
    
    //Function to sort array using insertion sort
    public static void insertionsort(double arr[],String str[],int amountofreviewer[])
    {
        for (int i=0; i<(arr.length-1); i++){
            for (int j=i+1; j<arr.length; j++){
                if (arr[i]<arr[j]){
                    double temp = arr[i];
                    String tempmovie = str[i];
                    int temp_amount = amountofreviewer[i];
                    arr[i] = arr[j];
                    str[i] = str[j];
                    amountofreviewer[i] = amountofreviewer[j];
                    arr[j] = temp;
                    str[j] = tempmovie;
                    amountofreviewer[j] = temp_amount; 
                }
            }
        }
    }
    
    public static void insertionsort_forint(int arr[],String str[])
    {
        for (int i=0; i<(arr.length-1); i++){
            for (int j=i+1; j<arr.length; j++){
                if (arr[i]<arr[j]){
                    
                    int temp = arr[i];
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
