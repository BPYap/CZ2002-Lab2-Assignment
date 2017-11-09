package movie;
import java.util.Calendar;
import java.util.Scanner;
public class Transaction extends ShowTime{
	private Calendar transactionDateTime;
	private String transactionID;
	private double TotalSum;
	private String customer_name;
	private String mobile_number;
	private String email_address;
	private int number_of_adult;
	private int number_of_child;
	private int number_of_scitizen;
	private String cinema_code;
	private int listing_Id;
	private int rows[];
	private int columns[];
	
	
	public Transaction(Calendar transactionDateTime,double TotalSum,String customer_name,
			String mobile_number,String email_address,int number_of_adult,int number_of_child,int number_of_scitizen,
			String cinema_code,int listing_Id){
		this.transactionDateTime = transactionDateTime;
		this.TotalSum = TotalSum;
		this.customer_name = customer_name;
		this.mobile_number = mobile_number;
		this.email_address = email_address;
		this.number_of_adult = number_of_adult;
		this.number_of_child = number_of_child;
		this.number_of_scitizen = number_of_scitizen;
		this.cinema_code = cinema_code;
		setTransactionID(transactionDateTime, cinema_code);
		this.listing_Id = listing_Id;
		
		}
	
	private void setTransactionID(Calendar dateTime, String cinema_code){

		int Y = dateTime.get(Calendar.YEAR);
		int M = dateTime.get(Calendar.MONTH)+1;
		int D = dateTime.get(Calendar.DAY_OF_MONTH);
		int h = dateTime.get(Calendar.HOUR_OF_DAY);
		int m = dateTime.get(Calendar.MINUTE);
		String YYYY=Integer.toString(Y);
		String MM = Integer.toString(M);
		String DD = Integer.toString(D);
		String hh = Integer.toString(h);
		String mm = Integer.toString(m);
		transactionID= "00"+cinema_code+YYYY+(M < 10 ? "0" + MM : MM) +(D < 10 ? "0" + DD : DD)+(h < 10 ? "0" + hh : hh)+(m < 10 ? "0" + mm : mm);
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

	public int getIndexNeg1(int[] x){
		int i=0;
		while(x[i]!=-1) i++;
		return i;
	}
	
	SpecialDate[] s1 = Database.read_special_date();
	TicketPrice t1 = Database.read_ticket_price();
	Cineplex c1 = new Cineplex();
	ShowTime s2 = Database.read_show_time(listing_Id);
	
	public void Booking() {
		
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
		
		if(c1.getCinema(cinema_code)[1].equals( "Platinum Movie Suites") {
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
           }while (!ShowTime.checkSeat(x,y));
           int index=getIndexNeg1(purchased_column);
           ShowTime.purchased_column[index]=y;
           ShowTime.purchased_row[index]=x;
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
           }while (!ShowTime.checkSeat(x,y));
           int index=getIndexNeg1(purchased_column);
           ShowTime.purchased_column[index]=y;
           ShowTime.purchased_row[index]=x;
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
           }while (!ShowTime.checkSeat(x,y));
           int index=getIndexNeg1(purchased_column);
           ShowTime.purchased_column[index]=y;
           ShowTime.purchased_row[index]=x;
		}
	}
	
	public String toString() {
        return listing_Id + "|" + cinema_code + "|" +
    }
}
