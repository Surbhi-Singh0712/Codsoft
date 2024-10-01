package ProjectCodsoft;

import java.io.IOException;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class Task4CurrencyConverter {
    private static final String APIKEY="29fc2f8acdc6f8201db2c979";
    private static final String APIURL="https://v6.exchangerate-api.com/v6/";

    private static String[] CURRENCY_Code={"USD", "INR", "EUR", "CAD", "GBP", "BSD", "AUD", "AFN", "NZD", "JPY"};
    private static final String[] CURRENCY_SYMBOLS = {"$", "₹", "€", "CA$", "£", "B$", "AU$", "AFN", "$NZ", "¥"};
    static int baseCurIndex;
    static int  targetCurIndex;

    private static void convertCurrency(int baseCurIndex, int targetCurIndex, double amount) throws IOException {
        if (baseCurIndex < 1 || baseCurIndex > CURRENCY_Code.length || targetCurIndex < 1 || targetCurIndex > CURRENCY_Code.length) {
            System.out.println("Invalid currency index. Please enter a value between 1 and " + CURRENCY_Code.length);
            return;
        }

        if (baseCurIndex == targetCurIndex) {
            System.out.println("The target currency is the same as the base currency. The conversion rate is 1.");
            System.out.println("The result is: " + amount + " " + CURRENCY_SYMBOLS[baseCurIndex -1]);
        } else {
            String url = APIURL + APIKEY + "/pair/" + CURRENCY_Code[baseCurIndex - 1] + "/" + CURRENCY_Code[targetCurIndex - 1];

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode != 200) {
                    System.out.println("Error: " + responseCode);
                    return;
                }

                // Read response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null)  // Read the response from the API
                {
                    response.append(inputLine);
                }

                JSONObject jsonObject = new JSONObject(response.toString()); // Parse the response into a JSONObject & Create JSONObject from response string

                if (jsonObject.has("result") && jsonObject.getString("result").equals("error")) // Check if the API returned an error
                {
                    System.out.println("Error: " + jsonObject.getString("error-type"));
                    return;
                }
                double exchangeRate = jsonObject.getDouble("conversion_rate");
                double convertedAmount = amount * exchangeRate;  // Step 4: Currency Conversion

                System.out.println("Converted amount: " + convertedAmount + " " + CURRENCY_SYMBOLS[targetCurIndex - 1]);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


    private static Void PrintCurrency() {
        for (int i = 0; i < CURRENCY_Code.length; i++) {
            System.out.println((i + 1)+" "+CURRENCY_Code[i]);
        }
        return null;
    }
    private static void ValidateInput(int index){
        if(index<=0||index>CURRENCY_Code.length){
            System.out.println("Enter a valid Index");


        }
    }


    public static void main(String []args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // Base currency selection

        while (true) {
            System.out.println("Choose a Base Currency");
            PrintCurrency();
            try {
                int baseCurIndex = sc.nextInt();
                if (baseCurIndex < 1 || baseCurIndex > CURRENCY_Code.length) {
                    System.out.println("Please enter a value between 1 and " + CURRENCY_Code.length);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid integer.");
                sc.next();  // Clear the invalid input
            }
        }

        //Target currency Selection
        while (true) {
            System.out.println("Choose a Target Currency");
            PrintCurrency();

            try {
                int targetCurIndex = sc.nextInt();
                if (targetCurIndex < 1 || targetCurIndex > CURRENCY_Code.length) {
                    System.out.println("Please enter a value between 1 and " + CURRENCY_Code.length);
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid integer.");
                sc.next();
            }
        }

        double amount;
        while (true) {
            System.out.println("Enter amount to convert: ");
            try {
                amount = sc.nextDouble();
                if (amount < 0) {
                    System.out.println("Please enter a non-negative value.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.next();
            }

        }

        convertCurrency(baseCurIndex, targetCurIndex, amount);

    }
}
