package v1;

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
	private int listing_ID;
	private int[] purchased_row;
	private int[] purchased_column;
	private int available_seats;
	
	public ShowTime(int year, int month, int day, int start_time, int end_time, String cineplex_location, String cinema_code, int ID) {
		Cinema c = new Cinema();
		this.year = year;
		this.month = month;
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.cineplex_location = cineplex_location;
		this.cinema_code = cinema_code;
		this.listing_ID = ID;
		this.available_seats = c.getSeatCapacity() - 
		
		String s = toString();
		
		utility.addRecord("Showtime.txt", s);
	}
	
	public String toString() {
		return "The movie is shown on " + this.day + "/" + this.month + "/" + this.year + " at " + this.start_time + "to" + this.end_time + " at " + this.cineplex_location + " cinema " + this.cinema_code + ", (" + this.listing_ID + ")."; 
	}
	
	public void printSeatLayout() {
		for (int i=0;i<)
	}
	
	public boolean checkSeat (int row_number, int column_number) {
		if ()
	}
	public int selectSeat() {
		if (checkSeat)
	}
	
}
