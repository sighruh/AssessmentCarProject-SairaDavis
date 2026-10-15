
// Saira Davis
// October 13, 2026
// Create Main class 

package assessmentCarProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static ArrayList<Car> loadCars(String filename) { 
		//Loads all cars from CSV file
		ArrayList<Car> cars = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			reader.readLine(); // Skips line that is not needed

			while ((line = reader.readLine()) != null) {
				try {
					String[] fields = line.split(",", -1);
					if (fields.length != 7) { // Only need 7 columns
						System.out.println("Skipping malformed row: " + line);
						continue;
					}

					String carId = fields[0].trim();
					String brand = fields[1].trim();
					String model = fields[2].trim();
					int year = Integer.parseInt(fields[3].trim());
					String fuelType = fields[4].trim();
					String color = fields[5].trim();
					double mileage = Double.parseDouble(fields[6].trim());

					Car car = new Car(carId, brand, model, year, fuelType, color, mileage);
					// This creates a car using the information from the row
					cars.add(car);

				} catch (Exception e) { // Skips any invalid numbers 
					System.out.println("Skipping malformed row: ");
				}

			}

		} catch (IOException e) { 
			System.out.println("Could not open file: "); // Displays when file is not opening
		}

		System.out.println("Total cars loaded: " + cars.size());

		return cars;
	}

	public static void main(String[] args) {
		ArrayList<Car> cars = loadCars("Car_data.csv"); // Loads every car from file

		ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));

		insertionSortByYear(working); // Sorts the working list by year

		System.out.println("\nFirst 10 cars sorted by year: ");
		for (int i = 0; i < 10; i++) {
			System.out.println(working.get(i));
		}
		//
	}

	public static void insertionSortByYear(ArrayList<Car> working) { // Sorts cars in order by year
		for (int i = 1; i < working.size(); i++) {
			Car temp = working.get(i);
			int j = i - 1;

			while (j >= 0 && working.get(j).getYear() > temp.getYear()) { // Temp variable
				working.set(j + 1, working.get(j));
				j--;
			}
			working.set(j + 1, temp);
		}
	}

	public static int binarySearchByYear(ArrayList<Car> working, int targetYear) { // Finds a car using specific year
		int low = 0;
		int high = working.size() - 1;

		while (low <= high) {
			int mid = (high + low) / 2;
			int midYear = working.get(mid).getYear();

			if (midYear == targetYear) {
				return mid;
			} else if (midYear < targetYear) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return -1; // not found
	}

	public static void searchCarsByYear(ArrayList<Car> working, int targetYear) { // Displays every car requested 
		int foundIndex = binarySearchByYear(working, targetYear);

		if (foundIndex == -1) {
			System.out.println("No cars were found for that year.");
			return;
		}

		int first = foundIndex;

		while (first > 0 && working.get(first - 1).getYear() == targetYear) {
			first--;
		}

		int i = first; // All cars with the requested year 

		while (i < working.size() && working.get(i).getYear() == targetYear) {
			System.out.println(working.get(i));
			i++;
		}
	}

}