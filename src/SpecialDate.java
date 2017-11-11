public class SpecialDate {
    private int year;
    private int month;
    private int day;
    private double discount;
    private String remark;
	
    public SpecialDate(int year, int month, int day, double discount, String remark) {
	this.month = month;
	this.year = year;
	this.day = day;
	this.discount = discount;
	this.remark = remark;
	}
    
    public SpecialDate(String record)
    {
        String [] attributes = record.split("\\|");
        year = Integer.parseInt(attributes[0]);
        month = Integer.parseInt(attributes[1]);
        day = Integer.parseInt(attributes[2]);
        discount = Double.parseDouble(attributes[3]);
        remark = attributes[4];
    }
    
    public String getDate()
    {
        return this.year + "-" + this.month + "-" + this.day;
    }
    
    public int getYear(){return this.year;}
    public int getMonth(){return this.month;}
    public int getDay(){return this.day;}
    
    public double getDiscount()
    {
        return this.discount;
    }
    
    public void setDiscount(double discount)
    {
        this.discount = discount;
    }
    
    public String getRemark()
    {
        return this.remark;
    }
    
    public String toString()
    {
        return year + "|" + month + "|" + day + "|" + discount + "|" + remark;
    }
}
