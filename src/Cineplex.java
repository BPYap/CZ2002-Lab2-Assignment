

public class Cineplex {
	private String location;
	private int number_of_cinema;
    public Cinema[] cinema;
	
	public Cineplex(String location, int number_of_cinema) {
		this.location=location;
		this.number_of_cinema=number_of_cinema;
	}
	
	public Cineplex(String record)
    // format: number_of_cinema|Location|Cinema_Code|Cinema_Class|Number_of_Rows|Number_of_Columns
    {
<<<<<<< HEAD
        Object [] attributes = record.split("\\|");
        number_of_cinema = (Integer) attributes[0];
        location = (String)attributes[1];
        cinema = (cinema)attributes[2];
=======
        String [] attributes = record.split("\\|");
        number_of_cinema = Integer.parseInt(attributes[0]);
        location = attributes[1];
        cinema = new Cinema[number_of_cinema];
        int temp = 2;
        for (int i = 0; i < number_of_cinema; i++)
        {
            String cinema_code = attributes[temp];
            String cinema_class = attributes[temp+1];
            int row = Integer.parseInt(attributes[temp+2]);
            int column = Integer.parseInt(attributes[temp+3]);
            cinema[i] = new Cinema(cinema_code, cinema_class, row, column);
            temp += 4;
        }
>>>>>>> 2d707c79b6701b45c0d63a67b250bb398427d4ed
    }
	
	public String getLocation() {
		return location;
	}
	
	public int getNumberOfCinema() {
		return number_of_cinema;
	}
}
