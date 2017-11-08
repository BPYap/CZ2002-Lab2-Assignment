public class Cinema {

	private String cinema_code;
	private String cinema_class;
	private int number_of_rows;
	private int number_of_columns;
	
	public Cinema( String cinema_code,String cinema_class,int number_of_rows,int number_of_columns) {
		this.cinema_code=cinema_code;
		this.cinema_class=cinema_class;
		this.number_of_rows=number_of_rows;
		this.number_of_columns=number_of_columns;
	}
	
	public Cinema(String record)
    {
        Object [] attributes = record.split("\\|");
        cinema_code = (String) attributes[0];
        cinema_class = (String)attributes[1];
        number_of_rows = (int)attributes[2];
        number_of_columns = (int)attributes[3];
    }
	
	public String getCinemaCode() {
		return cinema_code;
	}
	
	public String getCinemaClass() {
		return cinema_class;
	}
	
	public int getSeatCapacity() {
		return number_of_rows*number_of_columns;
	}
	
	public void printSeatLayout() {
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
	
	public String toString() {
		return "The cinema code is "+cinema_code+". The cinema class is "+cinema_class+". The seat capacity is "+number_of_rows*number_of_columns+", which have "+number_of_rows+" rows and "+number_of_columns+" columns.";
	}
}
