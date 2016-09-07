package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EmployeeAPIRestGetAll {
	// The URL of the API
	protected static String endpoint = "http://localhost:1337/employee";
	
	// The charset
	protected static String charset = "UTF-8";

	// Main method for running this program
	public static void main(String[] args) {

		try {
			// Creates a new URL and populates it with the endPoint, returnType and  queryString
			URL employeeDB = new URL(endpoint);
			HttpURLConnection connection = (HttpURLConnection) employeeDB.openConnection();
			connection.setRequestMethod("GET"); // Sets the requestMethod to POST to create new records

			// Checks response, if anything other than 201 (success), then throw an error. 
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			// Load the response into the buffer.
			BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

			// Loop over the buffer until there are no more lines. 
			while (br.readLine() != null) {
				// Print out each line to the screen.
				System.out.println(br.readLine());
			}

			// Close connection to the Employee API
			connection.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		
	}
}
