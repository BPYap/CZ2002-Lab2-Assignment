public class Cineplex {
	private Cinema[] cinema;
	private String location;
	private int number_of_cinema;
	
	public Cineplex(String location, int number_of_cinema) {
		this.location=location;
		this.number_of_cinema=number_of_cinema;
	}
	
	public String getLocation() {
		return location;
	}
	
	public int getNumberOfCinema() {
		return number_of_cinema;
	}
	
	public String toString() {
		return "This cineplex is at "+location+". It has "+number_of_cinema+" cinemas";
	}
}
