import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Task4 {
    private static final String API_KEY = "Replace with your API key";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available currencies: USD, EUR, GBP");
        System.out.print("Enter the base currency code: ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter the target currency code: ");
        String targetCurrency = scanner.next().toUpperCase();

        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);

        System.out.print("Enter the amount to convert: ");
        double amountToConvert = scanner.nextDouble();

        double convertedAmount = convertCurrency(amountToConvert, exchangeRate);

        System.out.printf("%.2f %s is equal to %.2f %s%n",
                amountToConvert, baseCurrency, convertedAmount, targetCurrency);

        scanner.close();
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            URL url = new URL("https://v6.exchangerate-api.com/v6/2527f49884de4199c06b2289/latest/" + baseCurrency);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            String jsonResponse = response.toString();
            double rate = parseExchangeRate(jsonResponse, targetCurrency);

            return rate;
        } catch (IOException e) {
            e.printStackTrace();
            return 1.0;
        }
    }

    private static double parseExchangeRate(String jsonResponse, String targetCurrency) {


        
        double rate = 1.0;

        if (jsonResponse.contains("\"" + targetCurrency + "\":")) {
            int startIndex = jsonResponse.indexOf("\"" + targetCurrency + "\":") + targetCurrency.length() + 3;
            int endIndex = jsonResponse.indexOf(",", startIndex);
            String rateString = jsonResponse.substring(startIndex, endIndex);
            rate = Double.parseDouble(rateString);
        }

        return rate;
    }

    private static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}
