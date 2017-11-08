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
		Cinema c = new Cinema('Jurong Point','JP1','Platinum Movie Suites',3,10);
		this.year = year;
		this.month = month;
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.cineplex_location = cineplex_location;
		this.cinema_code = cinema_code;
		this.listing_ID = ID;
		//this.purchased_row = ;
		//this.purchased_column = ;
		//this.available_seats = c.getSeatCapacity() - 
	}
	
	public ShowTime(String record)
    {
        Object [] attributes = record.split("\\|");
        year = (int) attributes[0];
        month = (int) attributes[1];
        day = (int) attributes[2];
        start_time = (int)attributes[3];
        end_time = (int)attributes[4];
        cineplex_location = (String) attributes[5];
        cinema_code = (String)attributes[6];
        listing_ID = (int)attributes[7];
    }
	
    public String toString() {
        return this.day + "|" + this.month + "|" + this.year + "|" + this.start_time + "|" + this.end_time + " | " + this.cineplex_location + "|" + this.cinema_code + "|" + this.listing_ID; 
	}
	
	public void printSeatLayout() {
		for (int i=0;i<)
	}
	
	public boolean checkSeat (int row_number, int column_number) {
		if (Cinema[number_of_rows][number_of_column] )
	}
	public int selectSeat() {
		if (checkSeat)
	}
	
}
