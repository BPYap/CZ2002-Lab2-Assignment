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
	
	public ShowTime(int year, int month, int day, int start_time, int end_time, String movie, String cineplex_location, String cinema_code, int ID) {
//		
		this.year = year;
		this.month = month;
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.movie_title = movie;
		this.cineplex_location = cineplex_location;
		this.cinema_code = cinema_code;
		this.listing_ID = ID;
		//this.purchased_row = ;
		//this.purchased_column = ;
		//this.available_seats = c.getSeatCapacity() - 
	}
	
	public ShowTime(String record)
    {
        String [] attributes = record.split("\\|");
        year = Integer.parseInt(attributes[0]);
        month = Integer.parseInt(attributes[1]);
        day = Integer.parseInt(attributes[2]);
        start_time = Integer.parseInt(attributes[3]);
        end_time = Integer.parseInt(attributes[4]);
        movie_title = attributes[5];
        cineplex_location = attributes[6];
        cinema_code = attributes[7];
        listing_ID = Integer.parseInt(attributes[8]);
    }
	
    public String toString() {
        return this.day + "|" + this.month + "|" + this.year + "|" + this.start_time + "|" + this.end_time + " | " + this.cineplex_location + "|" + this.cinema_code + "|" + this.listing_ID; 
	}
	
	public void printSeatLayout() {
		Cinema c = new Cinema();
		for(int i=0;i<number_of_rows;i++) {
			System.out.print("|  ");
			for(int j=0;j<number_of_columns;j++) {
				System.out.print("O");
				if(j==(number_of_columns/2)) {
					System.out.print("   ");
				}
			}
			System.out.print("  |");
		}
	}
	
	public boolean checkSeat (int row_number, int column_number) {
		if (Cinema[row_number][column_number] = 'O')
			return true;
		else return false;
			
	}
	public void selectSeat(int row_number, int column_number) {
		if (checkSeat[row_number][column_number] == TRUE)
			Cinema[row_number][column_number] = 'X';
	}
	
}
