import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by michael on 4/26/16.
 */
public class App {
    public static void main (String [] args) {
        // Create a client that consumes endpoint
        Client client = new Client();

        System.out.println("Find clinic availabilities for today.\n");

        // receive schedule parameters from user
        Scanner in = new Scanner(System.in);
        String serviceLine, latLong, clinic, provider, orderedLab;
        Integer page, duration;
        Boolean allClinics;

        System.out.println("Enter search parameters. ");

        System.out.print("Service Line Id: ");
        serviceLine = in.nextLine();

        System.out.print("Latitude-Longitude (format: '(Latitude,Longitude)'): ");
        latLong = in.nextLine();

        System.out.print("Page: ");
        while (true) {
            page = Integer.parseInt(in.nextLine());
            if (page < 1 || page > 4) {
                System.out.print("The first page is 1, and currently a page consists of 4 clinics. Try again: ");
                continue;
            }
            break;
        }

        System.out.print("Duration: ");
        while (true) {
            try {
                duration = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Must be in minutes. Try again: ");
                continue;
            }
            if (duration >= 0)
                break;
            else
                System.out.print("Must be in minutes. Try again: ");
        }

        System.out.print("Clinic Id (If not applicable, press enter): ");
        clinic = in.nextLine();
        if (clinic.isEmpty())
            clinic = null;

        System.out.print("Provider Id (If not applicable, press enter): ");
        provider = in.nextLine();
        if (provider.isEmpty())
            provider = null;

        System.out.print("Ordered Lab Id (If not applicable, press enter): ");
        orderedLab = in.nextLine();
        if (orderedLab.isEmpty())
            orderedLab = null;

        // build request body
        String urlParameters = "{" +
                "\"serviceLineId\": \"" + serviceLine + "\"," +
                "\"latLong\": \"" + latLong + "\"," +
                "\"duration\": " + duration + "," +
                "\"clinicId\": " + clinic + "," +
                "\"providerId\": " + provider + "," +
                "\"orderedLabId\": " + orderedLab + "," +
                "\"page\": " + page + "," +
                "\"allClinics\": false" +
                "}";
        Schedule schedule = client.requestSchedule(urlParameters);

        // Display clinic codes
        System.out.println("\nClinics\n----------");
        List<Clinic> clinics = schedule.getClinics();
        for (Integer i = 1; i < clinics.size() + 1; i++)
            System.out.println(i.toString() + ": " + clinics.get(i-1).getCode());

        // When a number is entered, the schedule is displayed
        System.out.print("\nEnter option: ");

        // Get clinic option
        Integer option = in.nextInt();

        // Find current day (“dayOfWeek”: 1 represents Sunday)
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dateobj);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        // Display open and close time of option for current day
        List<Hours> hours = clinics.get(option - 1).getHours();
        System.out.println("\nHours\n----------");
        for (Integer i = 0; i < hours.size(); i++) {
            if (hours.get(i).getDayOfWeek() == option) {
                Hours current = hours.get(i);
                System.out.println("Opens: " + current.getOpenHour().toString() + ":" + String.format("%02d", current.getOpenMinute()));
                System.out.println("Closes: " + current.getCloseHour().toString() + ":" + String.format("%02d", current.getCloseMinute()));
                break;
            }
        }
    }
}