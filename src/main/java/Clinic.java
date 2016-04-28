import java.util.List;

/**
 * Created by michael on 4/26/16.
 */
public class Clinic {
    private String clinicName, clinicCode;
    private List<Hours> clinicHours;

    public List<Hours> getHours() {
        return clinicHours;
    }

    public String getName() {
        return clinicName;
    }

    public String getCode() {
        return clinicCode;
    }

    public void setHours(List<Hours> hours) {
        this.clinicHours = hours;
    }

    public void setName(String name) {
        this.clinicName = name;
    }

    public void setCode(String code) {
        this.clinicCode = code;
    }
}