import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michael on 4/27/16.
 */
public class Schedule {
    @SerializedName("clinics")
    private List<Clinic> clinics;

    public Schedule(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public List<Clinic> getClinics () {
        return clinics;
    }

    public void setClinics (List<Clinic> clinics) {
        this.clinics = clinics;
    }
}