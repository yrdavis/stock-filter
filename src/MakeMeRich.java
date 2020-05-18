
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @author Yvonne Davis
 * @version 1
 */
public class MakeMeRich {
	public static final List<String> symbols = Arrays.asList("AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
			"AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

	public static void main(String[] args) {

		// 1. Print these symbols using a Java 8 for-each and lambdas
		System.out.println("List of stock symbols sorted.");
		symbols.stream().sorted().forEach(System.out::println);
		// 2. Use the StockUtil class to print the price of Bitcoin
		System.out.println(" The cost of Bitcoin: " + StockUtil.getPrice("BTC-USD"));

		// 3. Create a new List of StockInfo that includes the stock price
		// List<Set<Entry<String, Double>>> newStockList =
		// Arrays.asList(StockUtil.prices.entrySet());
		// List<StockInfo> newStockList = List.of(StockUtil.prices);
		// newStockList.stream().collect(Collectors.toList())
		// .forEach(System.out::println);

		List<StockInfo> newStockList = StockUtil.prices.entrySet().stream()
				.map(x -> new StockInfo(x.getKey(), x.getValue())).collect(Collectors.toList());
		newStockList.forEach(System.out::println);

		// 4. Find the highest-priced stock under $500
		StockInfo theHighestPriced = newStockList.stream().filter(StockUtil.isPriceLessThan(500))
				.reduce(StockUtil::pickHigh).get();
		System.out.println(theHighestPriced);
	}

}
