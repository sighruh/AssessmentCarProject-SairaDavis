
// Saira Davis
// October 13, 2026
// Create Main class 

package assessmentCarProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<Car> loadCars(String filename) {
		// Loads all cars from CSV file
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
		runMenu(working);
	}

	public static void insertionSortByYear(ArrayList<Car> working) { // Sorts cars in order by year
		for (int i = 1; i < working.size(); i++) {
			Car temp = working.get(i);
			int j = i - 1;

			while (j >= 0 && working.get(j).getYear() > temp.getYear()) { 
				working.set(j + 1, working.get(j));
				j--; // Moves through the list
			}
			working.set(j + 1, temp); // Places car in correct position
		}
	}

	public static int binarySearchByYear(ArrayList<Car> working, int targetYear) { // Finds a car using specific year
		int low = 0;
		int high = working.size() - 1;

		while (low <= high) {
			int mid = (high + low) / 2; // Finds middle index
			int midYear = working.get(mid).getYear();

			if (midYear == targetYear) {
				return mid;
			} else if (midYear < targetYear) {
				low = mid + 1; // Searches right half 
			} else {
				high = mid - 1; // Searches left half
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

	public static void displayStats(ArrayList<Car> working) { // Calculates average mileage
		double totalMileage = 0.0;

		String[] fuelTypes = new String[working.size()]; // Stores each fuel type and counts them
		int[] fuelCounts = new int[working.size()];

		int numberOfFuelTypes = 0;

		for (int i = 0; i < working.size(); i++) {
			Car car = working.get(i);

			totalMileage = totalMileage + car.getMileage_kmpl(); // Adds current car to the total mileage 

			String fuel = car.getFuel_Type(); // Current fuel type
			boolean found = false; // Checks if type already exists

			for (int j = 0; j < numberOfFuelTypes; j++) { // Searches existing fuel types 
				if (fuelTypes[j].equalsIgnoreCase(fuel)) { // Ignores lowercase/uppercass differences
					fuelCounts[j]++;
					found = true;
					break; // Stops searching for specific fuel type
				}
			}

			if (!found) { // Records new fuel type
				fuelTypes[numberOfFuelTypes] = fuel;
				fuelCounts[numberOfFuelTypes] = 1;
				numberOfFuelTypes++;
			}
		}

		double averageMileage = totalMileage / working.size(); // Calculates average mileage of all cars
		System.out.printf("Average mileage: %.2f km/l%n", averageMileage);
		System.out.println("\nCars by fuel type: ");

		for (int i = 0; i < numberOfFuelTypes; i++) { // Displays each fuel type and count
			System.out.println(fuelTypes[i] + ": " + fuelCounts[i]);
		}
	}

	public static void runMenu(ArrayList<Car> working) { // Displays menu and records user choices
		try (Scanner scanner = new Scanner(System.in)) {
			boolean running = true; // Helps keep menu running

			while (running) { // Will repeat until the exit choice is inputed
				System.out.println("\n---- Car Menu ----");
				System.out.println("1. Sort cars by Year");
				System.out.println("2. Search for the cars by Year");
				System.out.println("3. Display stats");
				System.out.println("4. Exit Menu");
				System.out.println("Choose an option: ");

				int choice = scanner.nextInt();

				if (choice == 1) {
					insertionSortByYear(working); // Sorts cars by year

					System.out.println("\nFirst 10 cars sorted by year: ");
					for (int i = 0; i < 10; i++) {
						System.out.println(working.get(i));
					}
				} else if (choice == 2) {
					System.out.println("Input year to search: ");

					if (!scanner.hasNextInt()) { // For invalid inputed numbers
						System.out.println("Error. Invalid year.");
						scanner.nextLine();
						continue;
					}

					int targetYear = scanner.nextInt();
					searchCarsByYear(working, targetYear); // Looks for and displays cars from year

				} else if (choice == 3) {
					displayStats(working); // Displays car stats 

				} else if (choice == 4) { // Ends menu loop
					running = false;
					System.out.println("Exiting program.");

				} else { // For invalid numbers
					System.out.println("Invalid option. Please try again.");
				}
			}
		}
	}

}