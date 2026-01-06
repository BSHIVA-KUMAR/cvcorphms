package pack2;

import pack1.Patient;

public class HospitalAdmin {
    // --- ARRAYS ---
    private Patient[] patients = new Patient[100]; 
    private int patientCount = 0; 

    private Doctor[] doctors = new Doctor[35]; // Increased for more doctors per specialty
    private int doctorCount = 0;

    private Medicine[] pharmacy = new Medicine[20];
    private int medCount = 0;

    private Room[] rooms = new Room[20];
    private int roomCount = 0;
    
    private Appointment[] appointments = new Appointment[500];
    private int appointmentCount = 0;

    public HospitalAdmin() {
        // DOCTORS - Cardiology
        doctors[doctorCount++] = new Doctor(101, "Dr. Krishna", "Cardiology");
        doctors[doctorCount++] = new Doctor(111, "Dr. Ram", "Cardiology");
        doctors[doctorCount++] = new Doctor(112, "Dr. Priya", "Cardiology");
        
        // DOCTORS - Neurology
        doctors[doctorCount++] = new Doctor(102, "Dr. Hari", "Neurology");
        doctors[doctorCount++] = new Doctor(113, "Dr. Anil", "Neurology");
        doctors[doctorCount++] = new Doctor(114, "Dr. Meera", "Neurology");
        
        // DOCTORS - General Physician
        doctors[doctorCount++] = new Doctor(103, "Dr. Hari Krishna", "Gen. Physician");
        doctors[doctorCount++] = new Doctor(115, "Dr. Ravi", "Gen. Physician");
        doctors[doctorCount++] = new Doctor(116, "Dr. Sunita", "Gen. Physician");
        
        // DOCTORS - Dermatology
        doctors[doctorCount++] = new Doctor(104, "Dr. Harish", "Dermatology");
        doctors[doctorCount++] = new Doctor(117, "Dr. Kavita", "Dermatology");
        doctors[doctorCount++] = new Doctor(118, "Dr. Vinod", "Dermatology");
        
        // DOCTORS - Orthopedics
        doctors[doctorCount++] = new Doctor(105, "Dr. Naresh", "Orthopedics");
        doctors[doctorCount++] = new Doctor(119, "Dr. Deepak", "Orthopedics");
        doctors[doctorCount++] = new Doctor(120, "Dr. Anjali", "Orthopedics");
        
        // DOCTORS - Pediatrics
        doctors[doctorCount++] = new Doctor(106, "Dr. Sandeep", "Pediatrics");
        doctors[doctorCount++] = new Doctor(121, "Dr. Neha", "Pediatrics");
        doctors[doctorCount++] = new Doctor(122, "Dr. Rajesh", "Pediatrics");
        
        // DOCTORS - ENT Specialist
        doctors[doctorCount++] = new Doctor(107, "Dr. Shiva", "ENT Specialist");
        doctors[doctorCount++] = new Doctor(123, "Dr. Pooja", "ENT Specialist");
        doctors[doctorCount++] = new Doctor(124, "Dr. Manoj", "ENT Specialist");
        
        // DOCTORS - Gynecology
        doctors[doctorCount++] = new Doctor(108, "Dr. Sana", "Gynecology");
        doctors[doctorCount++] = new Doctor(125, "Dr. Rekha", "Gynecology");
        doctors[doctorCount++] = new Doctor(126, "Dr. Ashok", "Gynecology");
        
        // DOCTORS - Psychiatry
        doctors[doctorCount++] = new Doctor(109, "Dr. Prakash", "Psychiatry");
        doctors[doctorCount++] = new Doctor(127, "Dr. Divya", "Psychiatry");
        doctors[doctorCount++] = new Doctor(128, "Dr. Suresh", "Psychiatry");
        
        // DOCTORS - Dentist
        doctors[doctorCount++] = new Doctor(110, "Dr. Jack Sparrow", "Dentist");
        doctors[doctorCount++] = new Doctor(129, "Dr. Manisha", "Dentist");
        doctors[doctorCount++] = new Doctor(130, "Dr. Vikram", "Dentist");

        // MEDICINES
        pharmacy[medCount++] = new Medicine(1, "Paracetamol", 5.05);
        pharmacy[medCount++] = new Medicine(2, "Ibuprofen", 7.50);
        pharmacy[medCount++] = new Medicine(3, "Amoxicillin", 13.00);
        pharmacy[medCount++] = new Medicine(4, "Cough Syrup", 7.00);
        pharmacy[medCount++] = new Medicine(5, "Cetirizine", 5.00);
        pharmacy[medCount++] = new Medicine(6, "Aspirin", 3.00);
        pharmacy[medCount++] = new Medicine(7, "Vitamin C", 8.00);
        pharmacy[medCount++] = new Medicine(8, "Antibiotic Cream", 15.00);
        pharmacy[medCount++] = new Medicine(9, "Bandages", 2.50);
        pharmacy[medCount++] = new Medicine(10, "Insulin", 45.00);

        // ROOMS
        rooms[roomCount++] = new Room(201, "General Ward", 500.00);
        rooms[roomCount++] = new Room(202, "General Ward", 500.00);
        rooms[roomCount++] = new Room(203, "General Ward", 500.00);
        rooms[roomCount++] = new Room(204, "General Ward", 500.00);
        rooms[roomCount++] = new Room(301, "Private Room", 2000.00);
        rooms[roomCount++] = new Room(302, "Private Room", 2000.00);
        rooms[roomCount++] = new Room(303, "Private Room", 2000.00);
        rooms[roomCount++] = new Room(401, "ICU", 5000.00);
        rooms[roomCount++] = new Room(402, "ICU", 5000.00);
        rooms[roomCount++] = new Room(403, "ICU", 5000.00);
    }

    // --- VALIDATION HELPERS ---
    public boolean isValidMobile(String mobile) {
        if (mobile == null || mobile.length() != 10) return false;
        for (int i = 0; i < mobile.length(); i++) {
            if (mobile.charAt(i) < '0' || mobile.charAt(i) > '9') return false; 
        }
        char f = mobile.charAt(0);
        return (f == '9' || f == '8' || f == '7' || f == '6');
    }

    public boolean isValidPassword(String pass) { return pass != null && pass.length() >= 6; }
    
    // METHOD OVERLOADING - isValidPassword with different parameters
    public boolean isValidPassword(String pass, int minLength) {
        return pass != null && pass.length() >= minLength;
    }
    
    // METHOD OVERLOADING - isValidPassword with custom validation
    public boolean isValidPassword(String pass, boolean requireSpecialChar) {
        if(pass == null || pass.length() < 6) return false;
        if(!requireSpecialChar) return true;
        // Check for special characters
        for(char c : pass.toCharArray()) {
            if(!Character.isLetterOrDigit(c)) return true;
        }
        return false;
    }
    
    public boolean isValidName(String name) {
        if (name == null || name.length() == 0) return false;
        // Check each character - only letters and spaces allowed
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            // Allow letters (a-z, A-Z) and spaces only
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false; // Contains number or special character
            }
        }
        return true;
    }
    
    // NEW: Blood Group Validation
    public boolean isValidBloodGroup(String bg) {
        if(bg == null) return false;
        String b = bg.toUpperCase();
        return (b.equals("A+") || b.equals("A-") || 
                b.equals("B+") || b.equals("B-") || 
                b.equals("O+") || b.equals("O-") || 
                b.equals("AB+") || b.equals("AB-"));
    }

    public boolean isUserExists(String name) {
        for(int i = 0; i < patientCount; i++) {
            if(patients[i].getUsername().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    // --- MAIN METHODS ---
    public String register(String name, String pass, String mobile, int age, String bg) {
        if(patientCount >= patients.length) return "Error: Database Full.";
        if(!isValidName(name)) return "Error: Name must contain only letters and spaces.";
        if(!isValidPassword(pass)) return "Error: Password too short.";
        if(!isValidMobile(mobile)) return "Error: Invalid Mobile.";
        if(!isValidBloodGroup(bg)) return "Error: Invalid Blood Group."; // Added check here too
        if(isUserExists(name)) return "Error: Username exists.";

        patients[patientCount++] = new Patient(name, pass, mobile, age, bg);
        return "Success";
    }

    public Patient login(String name, String pass, String mobile) {
        for(int i = 0; i < patientCount; i++) {
            Patient p = patients[i];
            if(p.getUsername().equals(name) && p.checkPass(pass) && p.getMobile().equals(mobile)) {
                return p;
            }
        }
        return null;
    }

    // --- FORGOT PASSWORD METHODS ---
    
    // Generate random 4-digit OTP
    public int generateOTP() {
        return (int)(Math.random() * 9000) + 1000; // Generates 1000-9999
    }
    
    // Find patient by name and mobile (for password reset)
    public Patient findPatientByNameMobile(String name, String mobile) {
        for(int i = 0; i < patientCount; i++) {
            Patient p = patients[i];
            if(p.getUsername().equals(name) && p.getMobile().equals(mobile)) {
                return p;
            }
        }
        return null;
    }
    
    // Reset password for a patient
    public String resetPassword(String name, String mobile, String newPassword) {
        if(!isValidPassword(newPassword)) {
            return "Error: Password must be at least 6 characters.";
        }
        Patient p = findPatientByNameMobile(name, mobile);
        if(p == null) {
            return "Error: Name and Mobile combination not found.";
        }
        p.setPassword(newPassword);
        return "Success: Password updated successfully.";
    }

    public void showDoctors() {
        System.out.println("\n--- DOCTOR LIST ---");
        System.out.printf("%-5s %-20s %-20s%n", "ID", "Name", "Specialty");
        System.out.println("------------------------------------------------");
        for(int i = 0; i < doctorCount; i++) {
            System.out.printf("%-5d %-20s %-20s%n", doctors[i].id, doctors[i].name, doctors[i].specialty);
        }
    }

    public void searchDoctorBySpecialty(String spec) {
        System.out.println("Results for " + spec + ":");
        boolean found = false;
        System.out.printf("%-5s %-20s %-20s%n", "ID", "Name", "Specialty");
        for(int i = 0; i < doctorCount; i++) {
            if(doctors[i].specialty.toLowerCase().contains(spec.toLowerCase())) {
                System.out.printf("%-5d %-20s %-20s%n", doctors[i].id, doctors[i].name, doctors[i].specialty);
                found = true;
            }
        }
        if(!found) System.out.println("No doctors found.");
    }
    
    // METHOD OVERLOADING - searchDoctorBySpecialty with case sensitivity option
    public void searchDoctorBySpecialty(String spec, boolean caseSensitive) {
        System.out.println("Results for " + spec + ":");
        boolean found = false;
        System.out.printf("%-5s %-20s %-20s%n", "ID", "Name", "Specialty");
        for(int i = 0; i < doctorCount; i++) {
            String doctorSpec = caseSensitive ? doctors[i].specialty : doctors[i].specialty.toLowerCase();
            String searchSpec = caseSensitive ? spec : spec.toLowerCase();
            if(doctorSpec.contains(searchSpec)) {
                System.out.printf("%-5d %-20s %-20s%n", doctors[i].id, doctors[i].name, doctors[i].specialty);
                found = true;
            }
        }
        if(!found) System.out.println("No doctors found.");
    }
    
    // METHOD OVERLOADING - searchDoctorBySpecialty that returns array of doctor IDs (different return type)
    public int[] getDoctorIdsBySpecialty(String spec) {
        int[] tempIds = new int[doctorCount];
        int count = 0;
        for(int i = 0; i < doctorCount; i++) {
            if(doctors[i].specialty.toLowerCase().contains(spec.toLowerCase())) {
                tempIds[count++] = doctors[i].id;
            }
        }
        int[] result = new int[count];
        System.arraycopy(tempIds, 0, result, 0, count);
        return result;
    }

    public void handleEmergency(int code) {
        System.out.println("\n>>> EMERGENCY PROTOCOL ACTIVATED <<<");
        switch(code) {
            case 1: 
                System.out.println("ALERT: TRAUMA / ACCIDENT CASE");
                System.out.println("Requirement: Orthopedics & General Surgeon");
                searchDoctorBySpecialty("Orthopedics");
                break;
            case 2: 
                System.out.println("ALERT: CARDIAC ARREST / CHEST PAIN");
                System.out.println("Requirement: Cardiologist");
                searchDoctorBySpecialty("Cardiology");
                break;
            case 3: 
                System.out.println("ALERT: PEDIATRIC EMERGENCY");
                searchDoctorBySpecialty("Pediatrics");
                break;
            case 4: 
                System.out.println("ALERT: RESPIRATORY FAILURE");
                searchDoctorBySpecialty("ENT");
                break;
            case 5: 
                System.out.println("ALERT: GENERAL EMERGENCY");
                showDoctors();
                break;
            default:
                System.out.println("Invalid Emergency Code.");
        }
        System.out.println(">>> PLEASE CONTACT RECEPTION IMMEDIATELY <<<");
    }

    public String bookAppointment(Patient p, int dId, int slot) {
        // Check if slot is valid (1, 2, or 3)
        if(slot < 1 || slot > 3) {
            return "Error: Invalid slot. Choose 1, 2, or 3.";
        }
        
        // Check if this doctor-slot combination is already booked
        for(int i = 0; i < appointmentCount; i++) {
            if(appointments[i].getDoctorId() == dId && appointments[i].getSlot() == slot) {
                String slotName = (slot == 1) ? "Morning" : (slot == 2) ? "Afternoon" : "Evening";
                return "Error: This time slot (" + slotName + ") is already booked. Please choose another slot.";
            }
        }
        
        // Find doctor and book appointment
        String slotName = (slot == 1) ? "Morning" : (slot == 2) ? "Afternoon" : "Evening";
        for(int i = 0; i < doctorCount; i++) {
            if(doctors[i].id == dId) {
                // Create appointment record
                if(appointmentCount < appointments.length) {
                    appointments[appointmentCount++] = new Appointment(dId, slot, p.getUsername());
                }
                String record = "Appointment: " + doctors[i].name + " (" + slotName + ")";
                p.addHistory(record);
                return "Success: " + record;
            }
        }
        return "Error: Invalid Doctor ID.";
    }
    
    // Check if a slot is available for a doctor
    public boolean isSlotAvailable(int doctorId, int slot) {
        for(int i = 0; i < appointmentCount; i++) {
            if(appointments[i].getDoctorId() == doctorId && appointments[i].getSlot() == slot) {
                return false;
            }
        }
        return true;
    }

    public void showPharmacy() {
        System.out.println("\n--- PHARMACY ---");
        System.out.printf("%-5s %-20s %s%n", "ID", "Name", "Price");
        System.out.println("----------------------------------");
        for(int i = 0; i < medCount; i++) {
            System.out.printf("%-5d %-20s $%.2f%n", pharmacy[i].id, pharmacy[i].name, pharmacy[i].price);
        }
    }

    public String buyMedicine(Patient p, int mId, int qty) {
        for(int i = 0; i < medCount; i++) {
            if(pharmacy[i].id == mId) {
                double cost = pharmacy[i].price * qty;
                p.addBill(cost);
                p.addHistory("Medicine: " + qty + "x " + pharmacy[i].name + " ($" + cost + ")");
                return "Added to bill: $" + cost;
            }
        }
        return "Invalid Medicine ID";
    }

    public void showAvailableRooms() {
        System.out.println("\n--- AVAILABLE ROOMS ---");
        System.out.printf("%-8s %-15s %-10s%n", "Room No", "Type", "Cost/Day");
        System.out.println("------------------------------------");
        boolean any = false;
        for(int i = 0; i < roomCount; i++) {
            if(!rooms[i].isOccupied) {
                System.out.printf("%-8d %-15s $%.2f%n", rooms[i].roomNumber, rooms[i].type, rooms[i].costPerDay);
                any = true;
            }
        }
        if(!any) System.out.println("No rooms available.");
    }

    public String bookRoom(Patient p, int roomNum, int days) {
        for(int i = 0; i < roomCount; i++) {
            if(rooms[i].roomNumber == roomNum) {
                if(rooms[i].isOccupied) return "Room is already occupied.";
                double totalCost = rooms[i].costPerDay * days;
                rooms[i].isOccupied = true;
                p.addBill(totalCost);
                p.addHistory("Room Admit: " + rooms[i].type + " (" + days + " days) - $" + totalCost);
                return "Success! Admitted to Room " + roomNum + " for " + days + " days.";
            }
        }
        return "Invalid Room Number.";
    }

 // ... existing imports
 // ... existing code

     public void generateBill(Patient p, java.util.Scanner scanner) {
         System.out.println("\n===========================================");
         System.out.println("         INVOICE / DISCHARGE SUMMARY       ");
         System.out.println("===========================================");
         System.out.println("Patient Name : " + p.getUsername());
         System.out.println("Mobile No    : " + p.getMobile());
         
         System.out.println("\n--- BILLABLE ITEMS & HISTORY ---");
         p.viewHistory(); 

         double totalAmount = p.getMedBill();
         System.out.println("-------------------------------------------");
         System.out.printf("TOTAL AMOUNT DUE: Rs. %.2f%n", totalAmount);
         System.out.println("-------------------------------------------");

         if (totalAmount > 0) {
             System.out.println("Status: PAYMENT PENDING");
             System.out.println("To complete discharge, full payment is required.");
             System.out.print("Enter Payment? (1. Yes / 2. No): ");
             
             if (scanner.hasNextInt()) {
                 int payChoice = scanner.nextInt();
                 scanner.nextLine(); 
                 if (payChoice == 1) {
                     paymentGateway(p, totalAmount, scanner);
                 } else {
                     System.out.println("Payment Skipped.");
                     System.out.println("Result: Please pay amount to discharge.");
                 }
             } else {
                 scanner.nextLine(); // Clear invalid input
                 System.out.println("Invalid Input.");
                 System.out.println("Result: Please pay amount to discharge.");
             }
         } else {
             System.out.println("Status: No outstanding amount. Discharged successfully.");
         }
     }
     
     public void paymentGateway(Patient p, double totalAmount, java.util.Scanner scanner) {
         System.out.println("\n----- PAYMENT GATEWAY -----");
         System.out.println("Total Amount to Pay: Rs. " + totalAmount);
         System.out.println("Choose Payment Method:");
         System.out.println("1. Card Payment");
         System.out.println("2. UPI Payment");
         System.out.println("3. Cash Payment");
         System.out.print("Enter your choice (1-3): ");
         
         if (!scanner.hasNextInt()) {
             scanner.nextLine(); 
             System.out.println("Invalid Payment Option!");
             return;
         }
         
         int choice = scanner.nextInt();
         scanner.nextLine(); 
         boolean paymentSuccess = false;
         
         switch (choice) {
             case 1:
                 // CARD PAYMENT
                 String cardNumber;
                 while (true) {
                     System.out.print("Enter 16-digit Card Number: ");
                     cardNumber = scanner.nextLine();
                     if (cardNumber.matches("\\d{16}")) {
                         break;
                     } else {
                         System.out.println("Invalid Card Number! Must be 16 digits.");
                     }
                 }
                 String cvv;
                 while (true) {
                     System.out.print("Enter CVV (3 digits): ");
                     cvv = scanner.nextLine();
                     if (cvv.matches("\\d{3}")) {
                         break;
                     } else {
                         System.out.println("Invalid CVV! Must be 3 digits.");
                     }
                 }
                 System.out.println("Processing Card Payment...");
                 System.out.println("Payment of Rs. " + totalAmount + " Successful via Card.");
                 paymentSuccess = true;
                 break;
                 
             case 2:
                 // UPI PAYMENT
                 String upiId;
                 while (true) {
                     System.out.print("Enter UPI ID (example@upi): ");
                     upiId = scanner.nextLine();
                     if (upiId.matches("[a-zA-Z0-9._-]+@[a-zA-Z]+")) {
                         break;
                     } else {
                         System.out.println("Invalid UPI ID format! Use format: example@upi");
                     }
                 }
                 System.out.println("Request sent to UPI app...");
                 System.out.println("Payment of Rs. " + totalAmount + " Successful via UPI.");
                 paymentSuccess = true;
                 break;
                 
             case 3:
                 // CASH PAYMENT
                 double cash;
                 while (true) {
                     System.out.print("Enter Cash Amount: Rs. ");
                     if (scanner.hasNextDouble()) {
                         cash = scanner.nextDouble();
                         scanner.nextLine(); // consume newline
                         if (cash >= totalAmount) {
                             break;
                         } else {
                             System.out.println("Insufficient cash! Please enter full amount.");
                         }
                     } else {
                         System.out.println("Invalid amount! Please enter a number.");
                         scanner.nextLine(); // consume invalid input
                     }
                 }
                 double change = cash - totalAmount;
                 System.out.println("Cash Received: Rs. " + cash);
                 if (change > 0) {
                     System.out.println("Change Returned: Rs. " + change);
                 }
                 System.out.println("Payment Successful via Cash.");
                 paymentSuccess = true;
                 break;
                 
             default:
                 System.out.println("Invalid Payment Option!");
         }
         
         if (paymentSuccess) {
             System.out.println("----- PAYMENT COMPLETED -----");
             p.resetBill(); // Clear the bill
             System.out.println("Status: Paid. Discharged successfully.");
         }
     }
 }
