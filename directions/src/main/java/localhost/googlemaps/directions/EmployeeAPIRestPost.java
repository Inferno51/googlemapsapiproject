package localhost.googlemaps.directions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * Simple Google Directions API class
 * 
 * @author Jonathan Pack
 * @since 2016-09-07
 *
 */
public class EmployeeAPIRestPost {
	
	// The URL of the API
	protected static String endpoint = "http://localhost:1337/employee";
	
	// The charset
	protected static String charset = "UTF-8";

	// Main method for running this program
	public static void main(String[] args) {

		try {
			// String values to populate the queryString below:
			String firstName = "Nathan";
			String lastName = "Strong";
			String email = "nathan.strong@gmail.com";
			String homePhone = "443-555-6666";
			String cellPhone = "443-555-6666";
			String password = "P4ssw0rd";
			String active = "1";
//			String returnType = "json";

			// creates the URL parameters as a string, and encode them with the defined charset
			String queryString = String.format("firstName=%s&lastName=%s&email=%s&homePhone=%s&cellPhone=%s&password=%s&active=%s",
					URLEncoder.encode(firstName, charset),
					URLEncoder.encode(lastName, charset),
					URLEncoder.encode(email, charset),
					URLEncoder.encode(homePhone, charset),
					URLEncoder.encode(cellPhone, charset),
					URLEncoder.encode(password, charset),
					URLEncoder.encode(active, charset)
			);
	
			// Creates a new URL and populates it with the endPoint, returnType and  queryString
			URL employeeDB = new URL(endpoint + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) employeeDB.openConnection();
			connection.setRequestMethod("POST"); // Sets the requestMethod to POST to create new records

			// Checks response, if anything other than 201 (success), then throw an error. 
			if (connection.getResponseCode() != 201) {
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