package pack2;

public class Appointment {
    int doctorId;
    int slot;  // 1=Morning, 2=Afternoon, 3=Evening
    String patientName;
    
    public Appointment(int doctorId, int slot, String patientName) {
        this.doctorId = doctorId;
        this.slot = slot;
        this.patientName = patientName;
    }
    
    public int getDoctorId() { return doctorId; }
    public int getSlot() { return slot; }
    public String getPatientName() { return patientName; }
}





