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
	
	public String getCinemaCode() {
		return cinema_code;
	}
	
	public String getCinemaClass() {
		return cinema_class;
	}
	
    public int getNumberOfRows(){
        return number_of_rows;
    }
    
    public int getNumberOfColumns(){
        return number_of_columns;
    }
    
	public int getSeatCapacity() {
		return number_of_rows*number_of_columns;
	}
	
	public void printSeatLayout() {
        for(int i=0;i<number_of_rows;i++) {
			System.out.print(i + "  |  ");
			for(int j=0;j<number_of_columns;j++) {
				System.out.print("O ");
				if(j==(number_of_columns/2)) {
					System.out.print("   ");
				}
			}
			System.out.print("  |");
            System.out.println();
		}
        System.out.print("  |  ");
        for(int x=1;x<=number_of_columns;x++){
            if(x==((number_of_columns+1)/2)){
                System.out.print("   ");
            }
            System.out.print(x+" ");
        }
        System.out.print("  |");
	}
	
	public String toString() {
		return "The cinema code is "+cinema_code+". The cinema class is "+cinema_class+". The seat capacity is "+number_of_rows*number_of_columns+", which have "+number_of_rows+" rows and "+number_of_columns+" columns.";
	}
}
