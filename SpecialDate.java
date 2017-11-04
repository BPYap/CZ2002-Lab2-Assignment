public class SpecialDate {
	private int year;
	private int month;
	private int day;
	private double discount;
	private String[] affectedType;
	private String remark;
	
	public SpecialDate(int year, int month, int day, double discount, String[] affectedType, String remark) {
		this.month=month;
		this.year=year;
		this.day=day;
		this.discount=discount;
		this.affectedType=affectedType;
		this.remark=remark;
	}
}
