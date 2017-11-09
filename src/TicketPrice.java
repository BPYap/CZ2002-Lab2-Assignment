public class TicketPrice {
    private double adult_price;
    private double children_price;
    private double senior_citizen_price;
    private double platinum_charge;
	
    public TicketPrice(String record) {
        String [] attributes = record.split("\\|");
        adult_price = Double.parseDouble(attributes[0]);
        children_price = Double.parseDouble(attributes[1]);
        senior_citizen_price = Double.parseDouble(attributes[2]);
        platinum_charge = Double.parseDouble(attributes[3]);
	}
    
    public double getAdult() {return adult_price;}
    public double getChildren() {return children_price;}
    public double getSenior() {return senior_citizen_price;}
    public double getPlatinum() {return platinum_charge;}
    public void setAdult(double price) {adult_price = price;}
    public void setChildren(double price) {children_price = price;}
    public void setSenior(double price) {senior_citizen_price = price;}
    public void setPlatinum(double charge) {platinum_charge = charge;}
    public String toString()
    {
        return (adult_price + "|" + children_price + "|" + senior_citizen_price + "|" + platinum_charge);
    }
}
