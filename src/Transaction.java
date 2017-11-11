import java.util.*;

public class Transaction{
	private Calendar transactionDateTime;
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
		this.transactionDateTime = Calendar.getInstance();
        this.listing_Id = listing_Id;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        this.transactionID = listing_Id + "_" + dateFormat.format(this.transactionDateTime); 
		this.customer_name = customer_name;
		this.mobile_number = mobile_number;
		this.email_address = email_address;
		this.number_of_adult = number_of_adult;
		this.number_of_child = number_of_child;
		this.number_of_scitizen = number_of_scitizen;
        this.rows = rows;
        this.column = columns;
        calculateTotalFare();
        this.showtime = Database.read_show_time(listingID);
    }
	
	private void calculateTotalFare()
    {
        TicketPrice ticket_info = Database.read_ticket_price();
        Cinema cinema = Database.read_cineplex(showtime.cineplex_location).getCinema(showtime.cinema_code);
        
        if(cinema.getCinemaClass() == "Platinum Movie Suites")
        {
            int pax = this.number_of_adult + this.number_of_child + this.number_of_scitizen;
            this.total_fare = (double)pax * ticket_info.getPlatinum();
        }
        else
        {
            this.total_fare = 0;
        }
        
        for(int i = 0; i < this.number_of_adult; i++)
        {
            this.total_fare += ticket_info.getAdult();
        }
        
        for(int i = 0; i < this.number_of_child; i++)
        {
            this.total_fare += ticket_info.getChildren();
        }
        
        for(int i = 0; i < this.number_of_scitizen; i++)
        {
            this.total_fare += ticket_info.getSenior();
        }
        
        SpecialDate special_dates = Database.read_special_date();
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
	
	
	public double getTotalSum(){
		return TotalSum;
	}
	
	public String getCustomer_Name(){
		return customer_name;
	}
	
	public String getMobile_Number() {
		return mobile_number;
		
	}
	public String email_address() {
		return email_address;
		
	}
	public int getNumber_Of_Adult() {
		return number_of_adult;
	}
	
	public int getNumber_Of_Child() {
		return number_of_child;
	}
	
	public int getNumber_Of_Scitizen() {
		return number_of_scitizen;
	}
	public String getCinema_Code(){
		return cinema_code;
	}
	public int getListing_Id(){
		return listing_Id;
	}
<<<<<<< HEAD

	public int getIndexNeg1(int[] x){
		int i=0;
		while(x[i]!=-1) i++;
		return i;
	}
=======
>>>>>>> 7ec4252d855fc34e89fec14d1fcabcd2a279702b
    
	public String toString() {
        return  transactionID + "|" + total_fare + "|" + customer_name + "|" +  mobile_number + '-' + s2.end_time()+
        	"|"+ this.transactionID +"|" + this.total_amount + "|" + this.number_of_adult +"|" + this.number_of_child + "|"+this.number_of_scitizen + "|"+ Arrays.toString(row)+
        	"|"+Arrays.toString(column);
    }
    
    	private Calendar transactionDateTime;
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
}
