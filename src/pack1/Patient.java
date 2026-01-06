package pack1;

public class Patient extends Person {
    private String password;
    private String bloodGroup;
    private double medBill = 0;
    
    private String[] history = new String[50];
    private int historyCount = 0;

    public Patient(String name, String password, String mobile, int age, String bloodGroup) {
        super(name, mobile, age);  // calling person const
        this.password = password;
        this.bloodGroup = bloodGroup;
    }

    public String getUsername() { return name; }
    
   
    // Override abstract mtd from Person class
    @Override
    public String displayInfo() {
        return "Patient: " + name + " | Mobile: " + mobile + " | Age: " + age + " | Blood Group: " + bloodGroup;
    }
    
    @Override
    public String toString() {
        return "Patient{name='" + name + "', mobile='" + mobile + "', age=" + age + ", bloodGroup='" + bloodGroup + "'}";
    }
    
    public boolean checkPass(String p) { return this.password.equals(p); }
    public void setPassword(String newPassword) { this.password = newPassword; }

    public double getMedBill() { return medBill; }
    public void addBill(double amount) { this.medBill += amount; }
    public void resetBill() { this.medBill = 0; }

    public void addHistory(String record) {
        if(historyCount < history.length) {
            history[historyCount++] = record;
        } else {
            System.out.println("History full!");
        }
    }

    public void viewHistory() {
        System.out.println("\n--- MEDICAL HISTORY ---");
        if(historyCount == 0) System.out.println("No history found.");
        else {
            for(int i = 0; i < historyCount; i++) {
                System.out.println("- " + history[i]);
            }
        }
    }
}