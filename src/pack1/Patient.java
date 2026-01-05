package pack1;

public class Patient {
    private String username;
    private String password;
    private String mobile;
    private int age;
    private String bloodGroup;
    private double medBill = 0;
    
    private String[] history = new String[50];
    private int historyCount = 0;

    public Patient(String username, String password, String mobile, int age, String bloodGroup) {
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.age = age;
        this.bloodGroup = bloodGroup;
    }

    public String getUsername() { return username; }
    public String getMobile() { return mobile; }
    
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