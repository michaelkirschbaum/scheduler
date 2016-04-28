import java.io.*;
import java.net.*;
import com.google.gson.Gson;

/**
 * Created by michael on 4/25/16.
 */
public class Client {
    private HttpURLConnection connection = null;
    static private String endpoint = "https://portal.zoomcare.com/ZoomCareRestApi/v2/public/schedule";

    public Client() {
        // connect client
        try {
            URL url = new URL(endpoint);
            connection = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Schedule requestSchedule(String urlParameters) {
        // set request headers
        try {
            connection.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // send request
        DataOutputStream wr;
        try {
            wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get response
        StringBuilder response = new StringBuilder();
        try {
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = rd.readLine()) != null) {
                 response.append(line);
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // unmarshal response
        Gson gson = new Gson();
        return gson.fromJson(String.valueOf(response), Schedule.class);
    }
}