import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {

    private String entryDate;
    private String entryTime;
    private String departureDate;
    private String departureTime;

    //dd/mm/yyyy
    //HH:mm 24:00
    public DateTime(String entryDate, String entryTime, String departureDate, String departureTime) {
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.departureDate = departureDate;
        this.departureTime = departureTime;

    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate() {
        this.entryDate = entryDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime() {
        this.entryTime = entryTime;
    }

    public long getTimeDifference() {

        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm");

            Date entryTimeVehicle = df.parse(entryDate + " " + entryTime);
            Date departureTimeVehicle = df.parse(departureDate + " " + departureTime);

            return departureTimeVehicle.getTime() - entryTimeVehicle.getTime();
        } catch (ParseException ex) {
            System.out.println("Wrong time format");
        }
        return 0;
    }

    public long getEntryDateAndTime() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy HH:mm");

            Date entryDateAndTime = df.parse(entryDate + " " + entryTime);

            return entryDateAndTime.getTime();
        } catch (ParseException ex) {
            System.out.println("Wrong time format");
        }
        return 0;
    }

}
