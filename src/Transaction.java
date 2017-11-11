import java.util.*;
import java.text.*;

public class Transaction{
	private String transactionID;
	private double total_fare;
	private String customer_name;
	private String mobile_number;
	private String email_address;
	private int number_of_adult;
	private int number_of_child;
	private int number_of_scitizen;
	private String listing_Id;
	private int rows[];
	private int columns[];
    private ShowTime showtime;
	
	public Transaction(String customer_name, String mobile_number,String email_address, int number_of_adult,int number_of_child,int number_of_scitizen, 
        String listing_Id, int[] rows, int[] columns)
    {
        this.listing_Id = listing_Id;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        String datestr = dateFormat.format(date);
        this.transactionID = listing_Id + "_" + datestr; 
		this.customer_name = customer_name;
		this.mobile_number = mobile_number;
		this.email_address = email_address;
		this.number_of_adult = number_of_adult;
		this.number_of_child = number_of_child;
		this.number_of_scitizen = number_of_scitizen;
        this.rows = rows;
        this.columns = columns;
        this.showtime = Database.read_show_time(this.listing_Id);
        calculateTotalFare();
    }
    
    public Transaction(String record)
    {
        String [] attributes = record.split("\\|");
        this.transactionID = attributes[0];
        this.listing_Id = attributes[1];
        this.total_fare = Double.parseDouble(attributes[2]);
        this.customer_name = attributes[3];
        this.mobile_number = attributes[4];
        this.email_address = attributes[5];
        this.number_of_adult = Integer.parseInt(attributes[6]);
        this.number_of_child = Integer.parseInt(attributes[7]);
        this.number_of_scitizen = Integer.parseInt(attributes[8]);
        String[] rows_str = attributes[9].split(","); 
        String[] cols_str = attributes[10].split(",");
        this.rows = new int[rows_str.length];
        this.columns = new int[rows_str.length];
        for (int i = 0; i < rows_str.length; i++)
        {
            this.rows[i] = Integer.parseInt(rows_str[i]);
            this.columns[i] = Integer.parseInt(rows_str[i]);
        }
    }
	
	private void calculateTotalFare()
    {
        TicketPrice ticket_info = Database.read_ticket_price();
        Cineplex cineplex = Database.read_cineplex(showtime.getCineplexLocation());
        Cinema cinema = cineplex.getCinema(showtime.getCinemaCode());

        if(cinema.getCinemaClass().equals("Platinum Movie Suites"))
        {
            int pax = number_of_adult + number_of_child + number_of_scitizen;
            this.total_fare = (double)pax * ticket_info.getPlatinum();
        }
        else
        {
            this.total_fare = 0;
        }
        
        for(int i = 0; i < number_of_adult; i++)
        {
            this.total_fare += ticket_info.getAdult();
        }
        
        for(int i = 0; i < number_of_child; i++)
        {
            this.total_fare += ticket_info.getChildren();
        }
        
        for(int i = 0; i < number_of_scitizen; i++)
        {
            this.total_fare += ticket_info.getSenior();
        }
        
        SpecialDate[] special_dates = Database.read_special_date();
        for (int i = 0; i < special_dates.length; i++)
        {
            if (showtime.getYear() == special_dates[i].getYear() && showtime.getMonth() == special_dates[i].getMonth() && showtime.getDay() == special_dates[i].getDay())
            {
                this.total_fare = this.total_fare - this.total_fare * special_dates[i].getDiscount() / 100;
                break;
            }
        }
        
	}
	
	public String getTransactionID(){
		return transactionID;
	}

	public double getTotalFare(){
		return total_fare;
	}
	
	public String getCustomerName(){
		return customer_name;
	}
	
	public String getMobileNumber() {
		return mobile_number;
		
	}
	public String getEmailAddress() {
		return email_address;
		
	}
	public int getNumberOfAdult() {
		return number_of_adult;
	}
	
	public int getNumberOfChildren() {
		return number_of_child;
	}
	
	public int getNumberOfSenior() {
		return number_of_scitizen;
	}

	public String getListingID(){
		return listing_Id;
	}
    
	public String toString(){
        String row_str = "";
        String col_str = "";
        for(int i = 0; i < rows.length; i++)
        {
            row_str += Integer.toString(rows[i]);
            col_str += Integer.toString(columns[i]);
            if(i+1!=rows.length){
                row_str +=",";
                col_str +=",";
            }
        }
        return  transactionID + "|" + listing_Id + "|" + total_fare + "|" + customer_name + "|" +  mobile_number + "|" + email_address +"|"+ number_of_adult + "|" + number_of_child + "|" + number_of_scitizen + "|" + row_str + "|" + col_str;
    }
}
