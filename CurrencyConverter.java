import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    private static double getExchangeRate(String base, String target) throws Exception {
        String apiUrl = "https://api.exchangerate.host/latest?base=" + base + "&symbols=" + target;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        String result = response.toString();
        String rateString = result.split(target + "\":")[1].split("}")[0];

        return Double.parseDouble(rateString);
    }

    private static String getCurrencySymbol(String currency) {
        switch (currency) {
            case "USD": return "$";
            case "INR": return "₹";
            case "EUR": return "€";
            case "GBP": return "£";
            case "JPY": return "¥";
            default: return currency + " ";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter base currency (e.g., USD, INR, EUR): ");
            String baseCurrency = scanner.next().toUpperCase();

            System.out.print("Enter target currency (e.g., USD, INR, EUR): ");
            String targetCurrency = scanner.next().toUpperCase();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();

            double rate = getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * rate;

            String symbol = getCurrencySymbol(targetCurrency);

            System.out.println("Converted Amount: " + symbol + convertedAmount);

        } catch (Exception e) {
            System.out.println("Error occurred while fetching exchange rates");
        }

        scanner.close();
    }
}