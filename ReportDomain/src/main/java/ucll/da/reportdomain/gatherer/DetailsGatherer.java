package ucll.da.reportdomain.gatherer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ucll.da.reportdomain.domain.DomainException;

/**
 * Created by Joren on 15-5-2017.
 */
public class DetailsGatherer {

    public List<String> getPlace(long placeId) throws DomainException {
        try {
            //localhost: http://localhost:8080/PlaceWeb/place/{placeId}
            //server: http://193.191.187.14:11108/PlaceWeb/place/{placeId}
            String details = this.readUrl("http://localhost:8080/PlaceWeb/place/" + placeId);
            JSONArray arr = new JSONArray(details);
            JSONObject jsonObject = arr.getJSONObject(0);
            JSONObject placeDetails = null;
            if (jsonObject.has("placeDetails")) {
                placeDetails = jsonObject.getJSONObject("placeDetails");
            }
            String address = null;
            if (placeDetails.has("address")) {
                address = placeDetails.getString("address");
            }
            double latitude = -1;
            if (placeDetails.has("latitude")) {
                latitude = placeDetails.getDouble("latitude");
            }
            double longitude = -1;
            if (placeDetails.has("longitude")) {
                longitude = placeDetails.getDouble("longitude");
            }
            String name = null;
            if (placeDetails.has("name")) {
                name = placeDetails.getString("name");
            }
            String openingHours = null;
            if (placeDetails.has("openingHours")) {
                openingHours = placeDetails.getString("openingHours");
            }
            double rating = -1;
            if (placeDetails.has("rating")) {
                rating = placeDetails.getDouble("rating");
            }
            String text = "Place: " + name + "\n"
                    + "Address: " + address + "\n"
                    + "Location: " + latitude + "," + longitude + "\n"
                    + "Rating: " + rating + "/5" + "\n"
                    + "Opening hours: " + openingHours;
            return Arrays.asList(text);
        } catch (IOException | JSONException e) {
            throw new DomainException(e.getMessage());
        }
    }

    private static String readUrl(String urlString) throws MalformedURLException, IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
