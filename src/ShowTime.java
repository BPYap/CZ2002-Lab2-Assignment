import java.io.*;
import java.util.*;

public class ShowTime {
	private int year;
	private int month;
	private int day;
	private int start_time;
	private int end_time;
	private String movie_title;
	private String cineplex_location;
	private String cinema_code;
	private String listing_ID;
	private int[] purchased_row;
	private int[] purchased_column;
	private int available_seats;
	
	public ShowTime(int year, int month, int day, int start_time, String movie, String cineplex_location, String cinema_code) {
		//assume all movies have the same duration of 2hours
		//assume all movies are played at start_time before 2200
        //pending
		this.year = year;
		this.month = month;
		this.day = day;
		this.start_time = start_time;
		this.end_time = start_time + 200;
		this.movie_title = movie;
		this.cineplex_location = cineplex_location;
		this.cinema_code = cinema_code;
		this.listing_ID = cinema_code + Integer.toString(year)+ Integer.toString(month) + Integer.toString(day) + Integer.toString(start_time);
		
		Cineplex cineplex = Database.read_cineplex(cineplex_location);
		this.available_seats = cineplex.getCinema(cinema_code).getSeatCapacity();
		
		this.purchased_row = new int[this.available_seats];
		this.purchased_column = new int[this.available_seats];
	}
	
	public ShowTime(String record){
		String [] attributes = record.split("\\|");
		listing_ID = attributes[0];
		year = Integer.parseInt(attributes[3]);
		month = Integer.parseInt(attributes[2]);
		day = Integer.parseInt(attributes[1]);
		start_time = Integer.parseInt(attributes[4]);
		end_time = Integer.parseInt(attributes[5]);
		movie_title = attributes[6];
		cineplex_location = attributes[7];
		cinema_code = attributes[8];  
        
		Cineplex cineplex = Database.read_cineplex(cineplex_location);
        int capacity = cineplex.getCinema(cinema_code).getSeatCapacity();
        
        purchased_row = new int[capacity];
        purchased_column =  new int[capacity];
        if(attributes.length < 10)
        {
            this.available_seats = capacity;
            return;
        }
        
        String[] row = attributes[9].split(",");
        String[] column = attributes[10].split(",");
        
        if(row.length == 0)
        {
            return;
        }
        int counter = 0;
        for (int i = 0; i < row.length; i++)
        {
            purchased_row[i] = Integer.parseInt(row[i]);
            purchased_column[i] = Integer.parseInt(column[i]);
            counter++;
        }
        
        this.available_seats = capacity - counter;
	}
        
        public String getDate()
        {
            return this.year + "-" + this.month + "-" + this.day;
        }
    	public int getYear(){return  year; }
    	public int getMonth() { return month; }
    	public int getDay() { return day; }
    	public int getStartTime() { return start_time; }
    	public int getEndTime() { return end_time; }
    	public String getMovieTitle() { return movie_title; }
    	public String getCineplexLocation() { return cineplex_location; }
    	public String getCinemaCode() {return cinema_code;}
    	public String getListingID() {return listing_ID;}
    	public int getAvailableSeats() {return available_seats; }
	
    public String toString() {
    	String rowBought = new String(""); 
    	String colBought = new String("");
        if (purchased_row != null)
        {
            for (int i=0;i<purchased_row.length;i++) {
                if (purchased_row[i] != 0) 
                {
                    rowBought += Integer.toString(purchased_row[i]) + ",";
                }
            }   
            for (int i=0;i<purchased_column.length;i++) {
                if (purchased_column[i] != 0) 
                {
                    colBought += Integer.toString(purchased_column[i]) + ",";
                }
            }
        }

        return this.listing_ID + "|" + this.day + "|" + this.month + "|" + this.year + "|" + this.start_time + "|" + this.end_time + "|" + this.movie_title + "|" + this.cineplex_location + "|" + this.cinema_code +  "|" + rowBought +  "|" + colBought; 
	}
	
	public void printSeatLayout() {;
        Cineplex cineplex = Database.read_cineplex(cineplex_location);
        int capacity = cineplex.getCinema(cinema_code).getSeatCapacity();
        int row = cineplex.getCinema(cinema_code).getNumberOfRows();
        int column = cineplex.getCinema(cinema_code).getNumberOfColumns();
        /* for(int i=0;i<available_seats;i++){
            System.out.println(purchased_row[i]);
            System.out.println(purchased_column[i]);
        } */
		for(int i=1;i<=row+1;i++) {
            if(i != row+1){
                System.out.print(i + "  |  ");
                for(int j=1;j<=column;j++) {
                    if(j==((column+1)/2)){
                        System.out.print("   ");
                    }
                    if(checkSeat(i,j) == true){
                        System.out.print("X ");
                    }else{
                        System.out.print("O ");
                    }
                }
                System.out.print("  |");
            }else{
                System.out.print("      ");
                for(int x=1;x<=column;x++){
                    if(x==((column+1)/2)){
                        System.out.print("   ");
                    }
                    System.out.print(x+" ");
                }
            }
            System.out.println();
		}
	}
    //checkSeat return true if the seat is occupied
	public boolean checkSeat (int row_number, int column_number){
        if(purchased_row == null){return false;}
        for (int i=0;i<available_seats;i++){
			if (row_number == purchased_row[i] && column_number == purchased_column[i]){
                return true;
            }
		}
		return false;
	}
    
    public void decrement_seat(int[] row, int[] column)
    {
        int temp = 0;
        for(int i = 0; i < this.purchased_row.length; i++)
        {
            if(purchased_row[i] == 0)
            {
                purchased_row[i] = row[temp];
                purchased_column[i] = column[temp];
                temp++;
                this.available_seats -= 1;
                if(temp == row.length)
                    break;
            }
        }
    }
}
