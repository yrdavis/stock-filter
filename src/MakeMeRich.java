
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/*
 * @author Yvonne Davis
 * @version 1
 */
public class MakeMeRich {
	public static final List<String> symbols = Arrays.asList("AMD", "HPQ", "IBM", "TXN", "VMW", "XRX", "AAPL", "ADBE",
			"AMZN", "CRAY", "CSCO", "SNE", "GOOG", "INTC", "INTU", "MSFT", "ORCL", "TIBX", "VRSN", "YHOO");

	// API key for Alpha Vantage
	final static String API_KEY = "BKD7TNEXLA16EN9W";

	static String price;
	static String date;

	public static void main(String[] args) throws IOException {
		// Headers for the stock quotes
		System.out.printf("%-15S %15S %15S %n", "Date", "Symbol", "Price");
		// Get current/last price for stocks
		price = getQuoteInfo();

	}

	// Exercise #6 Get current stock prices
	private static String getQuoteInfo() throws MalformedURLException, IOException {
		// Get the most current price for the stocks in the Symbols array
		for (String symbol : symbols) {
			// Send URL request to Alpha Vantage servers
			String baseURL = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&";
			baseURL += "symbol=" + URLEncoder.encode(symbol, "UTF-8");
			baseURL += "&apikey=" + API_KEY;

			URL request = new URL(baseURL);
			InputStream openStream = request.openStream();
			String response = IOUtils.toString(openStream, StandardCharsets.UTF_8.name());

			JSONObject root = new JSONObject(response);
			JSONObject currentDate = (JSONObject) root.get("Global Quote");
			// Get the date the stock was last traded
			date = (String) currentDate.get("07. latest trading day");

			// Get the price of the stock
			price = (String) currentDate.get("05. price");

			// Print the stock symbol, date traded and last price
			System.out.printf("%-15s %15s %15s %n", date, symbol, price);

		}
		return price;

	}

	// 1. Print these symbols using a Java 8 for-each and lambdas
	// System.out.println("List of stock symbols sorted.");
	// symbols.stream().sorted().forEach(System.out::println);
	// 2. Use the StockUtil class to print the price of Bitcoin
	// System.out.println(" The cost of Bitcoin: " + StockUtil.getPrice("BTC-USD"));

	// 3. Create a new List of StockInfo that includes the stock price
	// List<Set<Entry<String, Double>>> newStockList =
	// Arrays.asList(StockUtil.prices.entrySet());
	// List<StockInfo> newStockList = List.of(StockUtil.prices);
	// newStockList.stream().collect(Collectors.toList())
	// .forEach(System.out::println);

	// List<StockInfo> newStockList = StockUtil.prices.entrySet().stream()
	// .map(x -> new StockInfo(x.getKey(),
	// x.getValue())).collect(Collectors.toList());
	// newStockList.forEach(System.out::println);

	// 4. Find the highest-priced stock under $500
	// StockInfo theHighestPriced =
	// newStockList.stream().filter(StockUtil.isPriceLessThan(500))
	// .reduce(StockUtil::pickHigh).get();
	// System.out.println("The highest priced stock under $500 is " +
	// theHighestPriced);

	// https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=PIN&apikey=BKD7TNEXLA16EN9W

}
