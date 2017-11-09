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
		
		for(int i=0;i<this.available_seats;i++) {
			purchased_row[i] = -1;
			purchased_column[i] = -1;
		}
	}
	
	public ShowTime(String record){
		String [] attributes = record.split("\\|");
		listing_ID = attributes[0];
		year = Integer.parseInt(attributes[1]);
		month = Integer.parseInt(attributes[2]);
		day = Integer.parseInt(attributes[3]);
		start_time = Integer.parseInt(attributes[4]);
		end_time = Integer.parseInt(attributes[5]);
		movie_title = attributes[6];
		cineplex_location = attributes[7];
		cinema_code = attributes[8];  
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
        return this.listing_ID + "|" + this.day + "|" + this.month + "|" + this.year + "|" + this.start_time + "|" + this.end_time + "|" + this.movie_title + "|" + this.cineplex_location + "|" + this.cinema_code; 

    	public String toString() {
        	return this.listing_ID + "|" + this.day + "|" + this.month + "|" + this.year + "|" + this.start_time + "|" + this.end_time + " | " + this.movie_title + "|" + this.cineplex_location + "|" + this.cinema_code; 
	}
	
	public void printSeatLayout() {
		for(int i=0;i<purchased_row.length;i++) {
			System.out.print("|  ");
			for(int j=0;j<purchased_column.length;j++) {
				if (checkSeat(i,j) == false)
					System.out.print("X");
				if (checkSeat(i,j) == true)
					System.out.print("O");
				if(j==(purchased_column.length/2)) {
					System.out.print("   ");
				}
			}
			System.out.print("  |");
		}
	}
	
	public boolean checkSeat (int row_number, int column_number) {
		for (int i=0;i<purchased_row.length;i++)
		{
			for (int j=0;j<purchased_column.length;j++)
				if (row_number == purchased_row[i] && column_number == purchased_column[j])
					return false;
		}
			return true;
	}
	
}
