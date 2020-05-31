
public class Test {

	public static void main(String[] args) {
		// System.out.printf("%s%15s%s%15s%s%n", "Date", "Symbol", "Price");
		System.out.printf("%-15s %15s %15s %n", "Date", "Symbol", "Price");
		double amount = 123.987;
		System.out.printf("%-15s %15s %,15.2f %n", "04-23-2020", "XPSN", amount);
		// System.out.printf("%-15s %15s %,15.2f %n", "04-23-2020", "XPSN", 2345.5678);
	}
}

//Copyright Yvonne Davis 2020