package pack2;
import java.time.LocalDate;

public class Appointment {
    int doctorId;
    int slot;  // 1=Morning, 2=Afternoon, 3=Evening
    String patientName;
    LocalDate appointmentDate;
    
    public Appointment(int doctorId, int slot, String patientName, LocalDate appointmentDate) {
        this.doctorId = doctorId;
        this.slot = slot;
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
    }
    
    public int getDoctorId() { return doctorId; }
    public int getSlot() { return slot; }
    public String getPatientName() { return patientName; }
    public LocalDate getAppointmentDate() { return appointmentDate; }
}






