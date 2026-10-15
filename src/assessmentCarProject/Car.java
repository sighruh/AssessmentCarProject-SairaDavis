
// Saira Davis 
// October 13, 2026
// Create a Car Project 

package assessmentCarProject;

public class Car {

	private String Car_ID;
	private String Brand;
	private String Model;
	private int Year;
	private String Fuel_Type;
	private String Color;
	private double Mileage_kmpl;

	public Car(String car_ID, String brand, String model, int year, String fuel_Type, String color,
			double mileage_kmpl) {
		super();
		Car_ID = car_ID;
		Brand = brand;
		Model = model;
		Year = year;
		Fuel_Type = fuel_Type;
		Color = color;
		Mileage_kmpl = mileage_kmpl;
	}

	public String getCar_ID() {
		return Car_ID;
	}

	public void setCar_ID(String car_ID) {
		Car_ID = car_ID;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		Model = model;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public String getFuel_Type() {
		return Fuel_Type;
	}

	public void setFuel_Type(String fuel_Type) {
		Fuel_Type = fuel_Type;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public double getMileage_kmpl() {
		return Mileage_kmpl;
	}

	public void setMileage_kmpl(double mileage_kmpl) {
		Mileage_kmpl = mileage_kmpl;
	}

	@Override
	public String toString() {
		return "Car [Car_ID=" + Car_ID + ", Brand=" + Brand + ", Model=" + Model + ", Year=" + Year + ", Fuel_Type="
				+ Fuel_Type + ", Color=" + Color + ", Mileage_kmpl=" + Mileage_kmpl + "]";
	}

}
