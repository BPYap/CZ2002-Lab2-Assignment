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
		transactionID = cinema_code+YYYY+(M < 10 ? "0" + MM : MM) +(D < 10 ? "0" + DD : DD)+(h < 10 ? "0" + hh : hh)+(m < 10 ? "0" + mm : mm);
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
	
    public static void selectSeat(){
        ShowTime[] showtime = Database.read_all_showtime();

        String movietitle=selectMovieTitle();
        String cineplex=selectCineplex();
        
        for(int i=0;i<showtime.length;i++){
            if(showtime[i].getMovieTitle().equals(movietitle) && showtime[i].getCineplexLocation().equals(cineplex)){
                showtime[i].printSeatLayout();
            }
        }
        
        do{
            System.out.print("How many adults? ");
            int number_of_adult = sc.nextInt();
        }while(number_of_adult < 0); //assume costumer wont buy more than cinema seats as they have seen the seatlayout 
        
        if()
        do{
            System.out.print("How many children? ");
            int number_of_child = sc.nextInt();
        }while(number_of_child < 0);
    }
    
	public String toString() {
        ShowTime s2 = Database.read_show_time(listing_Id);
        return  s2.getCineplexLocation()+"|"+ s2.getMovieTitle() + "|" + s2.getYear()+'-'+s2.getMonth()+'-'+s2.getDay() + "|" +  s2.start_time() + '-' + s2.end_time()+
        	"|"+ this.transactionID +"|" + this.total_amount + "|" + this.number_of_adult +"|" + this.number_of_child + "|"+this.number_of_scitizen + "|"+ Arrays.toString(row)+
        	"|"+Arrays.toString(column);
    }
}
