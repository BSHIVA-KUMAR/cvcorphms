package pack2;

import pack1.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class HospitalAdmin {
    // --- ARRAYS ---
    private Patient[] patients = new Patient[100]; 
    private int patientCount = 0; 

    private Doctor[] doctors = new Doctor[35]; // Increased for more doctors per specialty
    private int doctorCount = 0;

    private Medicine[] pharmacy = new Medicine[50]; // Expanded for more medicines
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

        // MEDICINES - General
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
        
        // MEDICINES - Cardiology
        pharmacy[medCount++] = new Medicine(11, "Atorvastatin", 12.50);
        pharmacy[medCount++] = new Medicine(12, "Amlodipine", 8.75);
        pharmacy[medCount++] = new Medicine(13, "Metoprolol", 9.50);
        pharmacy[medCount++] = new Medicine(14, "Enalapril", 10.25);
        pharmacy[medCount++] = new Medicine(15, "Nitroglycerin", 14.00);
        
        // MEDICINES - Neurology
        pharmacy[medCount++] = new Medicine(16, "Gabapentin", 16.50);
        pharmacy[medCount++] = new Medicine(17, "Carbamazepine", 18.00);
        pharmacy[medCount++] = new Medicine(18, "Diazepam", 11.75);
        pharmacy[medCount++] = new Medicine(19, "Sumatriptan", 22.00);
        
        // MEDICINES - General Physician
        pharmacy[medCount++] = new Medicine(20, "Azithromycin", 15.00);
        pharmacy[medCount++] = new Medicine(21, "Omeprazole", 9.00);
        pharmacy[medCount++] = new Medicine(22, "Loratadine", 6.50);
        pharmacy[medCount++] = new Medicine(23, "Dextromethorphan", 8.00);
        
        // MEDICINES - Dermatology
        pharmacy[medCount++] = new Medicine(24, "Hydrocortisone Cream", 12.00);
        pharmacy[medCount++] = new Medicine(25, "Clotrimazole", 7.50);
        pharmacy[medCount++] = new Medicine(26, "Benzoyl Peroxide", 10.00);
        pharmacy[medCount++] = new Medicine(27, "Calamine Lotion", 5.50);
        
        // MEDICINES - Orthopedics
        pharmacy[medCount++] = new Medicine(28, "Diclofenac Gel", 13.50);
        pharmacy[medCount++] = new Medicine(29, "Calcium Tablets", 11.00);
        pharmacy[medCount++] = new Medicine(30, "Glucosamine", 19.00);
        pharmacy[medCount++] = new Medicine(31, "Pain Relief Spray", 14.50);
        
        // MEDICINES - Pediatrics
        pharmacy[medCount++] = new Medicine(32, "Pediatric Syrup", 9.50);
        pharmacy[medCount++] = new Medicine(33, "Children's Vitamin", 12.00);
        pharmacy[medCount++] = new Medicine(34, "Pediatric Antibiotic", 16.00);
        pharmacy[medCount++] = new Medicine(35, "Fever Reducer (Kids)", 8.25);
        
        // MEDICINES - ENT Specialist
        pharmacy[medCount++] = new Medicine(36, "Nasal Decongestant", 7.75);
        pharmacy[medCount++] = new Medicine(37, "Ear Drops", 9.00);
        pharmacy[medCount++] = new Medicine(38, "Throat Lozenges", 6.00);
        pharmacy[medCount++] = new Medicine(39, "Saline Nasal Spray", 8.50);
        
        // MEDICINES - Gynecology
        pharmacy[medCount++] = new Medicine(40, "Folic Acid", 7.00);
        pharmacy[medCount++] = new Medicine(41, "Iron Supplements", 10.50);
        pharmacy[medCount++] = new Medicine(42, "Prenatal Vitamins", 15.00);
        
        // MEDICINES - Psychiatry
        pharmacy[medCount++] = new Medicine(43, "Sertraline", 20.00);
        pharmacy[medCount++] = new Medicine(44, "Escitalopram", 18.50);
        pharmacy[medCount++] = new Medicine(45, "Alprazolam", 16.75);
        
        // MEDICINES - Dentist
        pharmacy[medCount++] = new Medicine(46, "Toothpaste (Medicated)", 6.50);
        pharmacy[medCount++] = new Medicine(47, "Mouthwash", 8.00);
        pharmacy[medCount++] = new Medicine(48, "Dental Gel", 11.50);
        pharmacy[medCount++] = new Medicine(49, "Pain Reliever (Dental)", 9.75);

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

    public boolean isValidPassword(String pass) {
        if(pass == null || pass.length() < 8 || pass.length() > 32) return false;
        
        // Check for at least one lowercase letter
        boolean hasLower = false;
        // Check for at least one uppercase letter
        boolean hasUpper = false;
        // Check for at least one digit
        boolean hasDigit = false;
        // Check for at least one special character (non-alphanumeric, non-whitespace)
        boolean hasSpecial = false;
        // Check for whitespace (should not contain any)
        boolean hasWhitespace = false;
        
        for(char c : pass.toCharArray()) {
            if(Character.isWhitespace(c)) {
                hasWhitespace = true;
                break; // No whitespace allowed
            }
            if(Character.isLowerCase(c)) {
                hasLower = true;
            }
            if(Character.isUpperCase(c)) {
                hasUpper = true;
            }
            if(Character.isDigit(c)) {
                hasDigit = true;
            }
            // Special character: not letter, not digit, not whitespace
            if(!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                hasSpecial = true;
            }
        }
        
        // Must have all requirements and no whitespace
        return hasLower && hasUpper && hasDigit && hasSpecial && !hasWhitespace;
    }
    
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
    
    // Get password validation error message
    public String getPasswordValidationError(String pass) {
        if(pass == null) return "Password cannot be empty.";
        if(pass.length() < 8) return "Password must be at least 8 characters long.";
        if(pass.length() > 32) return "Password must be at most 32 characters long.";
        
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        boolean hasWhitespace = false;
        
        for(char c : pass.toCharArray()) {
            if(Character.isWhitespace(c)) {
                hasWhitespace = true;
                break;
            }
            if(Character.isLowerCase(c)) hasLower = true;
            if(Character.isUpperCase(c)) hasUpper = true;
            if(Character.isDigit(c)) hasDigit = true;
            if(!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) hasSpecial = true;
        }
        
        if(hasWhitespace) return "Password cannot contain whitespace.";
        if(!hasLower) return "Password must contain at least one lowercase letter (a-z).";
        if(!hasUpper) return "Password must contain at least one uppercase letter (A-Z).";
        if(!hasDigit) return "Password must contain at least one digit (0-9).";
        if(!hasSpecial) return "Password must contain at least one special character (!@#$%^&* etc.).";
        
        return null; // Valid password
    }
    
    public boolean isValidName(String name) {
        if (name == null || name.trim().length() < 3) return false; // Minimum 3 characters after trimming
        // Check if name contains at least one letter (not just spaces)
        boolean hasLetter = false;
        // Check each character - only letters and spaces allowed
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            // Allow letters (a-z, A-Z) and spaces only
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false; // Contains number or special character
            }
            // Check if it's a letter
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                hasLetter = true;
            }
        }
        return hasLetter; // Must contain at least one letter
    }
    
    public boolean isValidEmail(String email) {
        if (email == null || email.length() == 0) return false;
        
        // Convert to lowercase for validation (capital letters are allowed but converted)
        String emailLower = email.toLowerCase();
        
        // Check for spaces
        if (emailLower.contains(" ")) return false;
        
        // Check that @ exists
        int atIndex = emailLower.indexOf("@");
        if (atIndex == -1) return false;
        
        // Check that there's at least one character before @
        if (atIndex == 0) return false;
        
        // Check that local part (before @) contains at least one letter (a-z)
        String localPart = emailLower.substring(0, atIndex);
        boolean hasLetter = false;
        for(int i = 0; i < localPart.length(); i++) {
            char c = localPart.charAt(i);
            if (c >= 'a' && c <= 'z') {
                hasLetter = true;
                break;
            }
        }
        if (!hasLetter) return false;
        
        // Check that . exists after @
        int dotIndex = emailLower.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex <= atIndex) return false;
        
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
    
    public boolean isEmailExists(String email) {
        for(int i = 0; i < patientCount; i++) {
            if(patients[i].getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }
    
    // Find patient by name or email
    public Patient findPatientByNameOrEmail(String nameOrEmail) {
        for(int i = 0; i < patientCount; i++) {
            Patient p = patients[i];
            if(p.getUsername().equalsIgnoreCase(nameOrEmail) || p.getEmail().equalsIgnoreCase(nameOrEmail)) {
                return p;
            }
        }
        return null;
    }
    
    // Get patient count
    public int getPatientCount() {
        return patientCount;
    }

    // --- MAIN METHODS ---
    public String register(String name, String email, String pass, String mobile, int age, String bg, String gender) {
        if(patientCount >= patients.length) return "Error: Database Full.";
        if(!isValidName(name)) return "Error: Name must contain only letters and spaces (minimum 3 characters).";
        if(!isValidEmail(email)) return "Error: Invalid Email format.";
        if(!isValidPassword(pass)) {
            String errorMsg = getPasswordValidationError(pass);
            return errorMsg != null ? "Error: " + errorMsg : "Error: Invalid password format.";
        }
        if(!isValidMobile(mobile)) return "Error: Invalid Mobile.";
        if(!isValidBloodGroup(bg)) return "Error: Invalid Blood Group.";
        if(isUserExists(name)) return "Error: Username exists.";
        if(isEmailExists(email)) return "Error: Email already registered.";

        patients[patientCount++] = new Patient(name, email, pass, mobile, age, bg, gender);
        return "Success";
    }
    
    // Convert gender number to string
    public String getGenderString(int genderNum) {
        switch(genderNum) {
            case 1: return "Male";
            case 2: return "Female";
            case 3: return "Others";
            default: return null;
        }
    }
    
    // Validate gender selection
    public boolean isValidGender(int genderNum) {
        return genderNum >= 1 && genderNum <= 3;
    }

    // Login with name/email and password - returns result object
    public LoginResult login(String nameOrEmail, String pass) {
        // Check if user exists
        Patient p = findPatientByNameOrEmail(nameOrEmail);
        if(p == null) {
            return new LoginResult(null, "USER_NOT_FOUND");
        }
        
        // User found, check password
        if(p.checkPass(pass)) {
            return new LoginResult(p, "SUCCESS");
        } else {
            return new LoginResult(null, "INCORRECT_PASSWORD");
        }
    }
    
    // Inner class for login result
    public static class LoginResult {
        private Patient patient;
        private String status;
        
        public LoginResult(Patient patient, String status) {
            this.patient = patient;
            this.status = status;
        }
        
        public Patient getPatient() { return patient; }
        public String getStatus() { return status; }
        public boolean isSuccess() { return "SUCCESS".equals(status); }
        public boolean isUserNotFound() { return "USER_NOT_FOUND".equals(status); }
        public boolean isIncorrectPassword() { return "INCORRECT_PASSWORD".equals(status); }
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
            if(p.getUsername().equalsIgnoreCase(name) && p.getMobile().equals(mobile)) {
                return p;
            }
        }
        return null;
    }
    
    // Reset password for a patient
    public String resetPassword(String name, String mobile, String newPassword) {
        if(!isValidPassword(newPassword)) {
            String errorMsg = getPasswordValidationError(newPassword);
            return errorMsg != null ? "Error: " + errorMsg : "Error: Invalid password format.";
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

    public String bookAppointment(Patient p, int dId, int slot, LocalDate date) {
        // Check if slot is valid (1, 2, or 3)
        if(slot < 1 || slot > 3) {
            return "Error: Invalid slot. Choose 1, 2, or 3.";
        }
        
        // Check if date is valid (not in the past)
        if(!isValidAppointmentDate(date)) {
            return "Error: Appointment date cannot be in the past.";
        }
        
        // Check if this doctor-slot-date combination is already booked
        for(int i = 0; i < appointmentCount; i++) {
            if(appointments[i].getDoctorId() == dId && 
               appointments[i].getSlot() == slot && 
               appointments[i].getAppointmentDate().equals(date)) {
                String slotName = (slot == 1) ? "Morning" : (slot == 2) ? "Afternoon" : "Evening";
                return "Error: This time slot (" + slotName + ") on " + date + " is already booked. Please choose another slot or date.";
            }
        }
        
        // Find doctor and book appointment
        String slotName = (slot == 1) ? "Morning" : (slot == 2) ? "Afternoon" : "Evening";
        for(int i = 0; i < doctorCount; i++) {
            if(doctors[i].id == dId) {
                // Create appointment record
                if(appointmentCount < appointments.length) {
                    appointments[appointmentCount++] = new Appointment(dId, slot, p.getUsername(), date);
                }
                String record = "Appointment: " + doctors[i].name + " (" + slotName + ") on " + date;
                p.addHistory(record);
                
                // Automatically apply prescription based on specialty
                applyPrescription(p, doctors[i].specialty);
                
                // Get prescription details for display
                String prescriptionList = getPrescriptionDetails(doctors[i].specialty);
                
                return "Success: " + record + "\nAfter consultation Prescription list: " + prescriptionList;
            }
        }
        return "Error: Invalid Doctor ID.";
    }
    
    // Overloaded method for backward compatibility (uses today's date)
    public String bookAppointment(Patient p, int dId, int slot) {
        return bookAppointment(p, dId, slot, LocalDate.now());
    }
    
    // Check if a doctor ID exists
    public boolean isValidDoctorId(int doctorId) {
        for(int i = 0; i < doctorCount; i++) {
            if(doctors[i].id == doctorId) {
                return true;
            }
        }
        return false;
    }
    
    // Check if a slot is available for a doctor on a specific date
    public boolean isSlotAvailable(int doctorId, int slot, LocalDate date) {
        for(int i = 0; i < appointmentCount; i++) {
            if(appointments[i].getDoctorId() == doctorId && 
               appointments[i].getSlot() == slot && 
               appointments[i].getAppointmentDate().equals(date)) {
                return false;
            }
        }
        return true;
    }
    
    // Overloaded method for backward compatibility (uses today's date)
    public boolean isSlotAvailable(int doctorId, int slot) {
        return isSlotAvailable(doctorId, slot, LocalDate.now());
    }
    
    // Find first available doctor with available slot for a specialty on a specific date
    // Returns array: [doctorId, slot] or null if no available slots
    public int[] findFirstAvailableDoctorSlot(String specialty, LocalDate date) {
        for(int i = 0; i < doctorCount; i++) {
            if(doctors[i].specialty.equalsIgnoreCase(specialty) || 
               doctors[i].specialty.toLowerCase().contains(specialty.toLowerCase())) {
                // Check all slots for this doctor
                for(int slot = 1; slot <= 3; slot++) {
                    if(isSlotAvailable(doctors[i].id, slot, date)) {
                        return new int[]{doctors[i].id, slot};
                    }
                }
            }
        }
        return null; // No available slots found
    }
    
    // Overloaded method for backward compatibility (uses today's date)
    public int[] findFirstAvailableDoctorSlot(String specialty) {
        return findFirstAvailableDoctorSlot(specialty, LocalDate.now());
    }
    
    // Validate date format (YYYY-MM-DD)
    public boolean isValidDateFormat(String dateStr) {
        if(dateStr == null || dateStr.trim().isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(dateStr.trim(), formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    // Parse date string to LocalDate
    public LocalDate parseDate(String dateStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(dateStr.trim(), formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
    
    // Check if date is not in the past
    public boolean isValidAppointmentDate(LocalDate date) {
        return date != null && !date.isBefore(LocalDate.now());
    }
    
    // Get first doctor ID in a specialty
    public int getFirstDoctorBySpecialty(String specialty) {
        for(int i = 0; i < doctorCount; i++) {
            if(doctors[i].specialty.equalsIgnoreCase(specialty) || 
               doctors[i].specialty.toLowerCase().contains(specialty.toLowerCase())) {
                return doctors[i].id;
            }
        }
        return -1; // No doctor found
    }
    
    // Get prescription medicines based on specialty
    public int[] getPrescriptionMedicines(String specialty) {
        java.util.ArrayList<Integer> medIds = new java.util.ArrayList<>();
        
        if(specialty.equalsIgnoreCase("Cardiology")) {
            medIds.add(11); // Atorvastatin
            medIds.add(12); // Amlodipine
            medIds.add(1);  // Paracetamol
        } else if(specialty.equalsIgnoreCase("Neurology")) {
            medIds.add(16); // Gabapentin
            medIds.add(17); // Carbamazepine
            medIds.add(1);  // Paracetamol
        } else if(specialty.equalsIgnoreCase("Gen. Physician")) {
            medIds.add(3);  // Amoxicillin
            medIds.add(1);  // Paracetamol
            medIds.add(5);  // Cetirizine
        } else if(specialty.equalsIgnoreCase("Dermatology")) {
            medIds.add(24); // Hydrocortisone Cream
            medIds.add(25); // Clotrimazole
            medIds.add(8);  // Antibiotic Cream
        } else if(specialty.equalsIgnoreCase("Orthopedics")) {
            medIds.add(28); // Diclofenac Gel
            medIds.add(2);  // Ibuprofen
            medIds.add(9);  // Bandages
        } else if(specialty.equalsIgnoreCase("Pediatrics")) {
            medIds.add(32); // Pediatric Syrup
            medIds.add(35); // Fever Reducer (Kids)
            medIds.add(33); // Children's Vitamin
        } else if(specialty.equalsIgnoreCase("ENT Specialist")) {
            medIds.add(36); // Nasal Decongestant
            medIds.add(4);  // Cough Syrup
            medIds.add(38); // Throat Lozenges
        } else if(specialty.equalsIgnoreCase("Gynecology")) {
            medIds.add(40); // Folic Acid
            medIds.add(41); // Iron Supplements
            medIds.add(1);  // Paracetamol
        } else if(specialty.equalsIgnoreCase("Psychiatry")) {
            medIds.add(43); // Sertraline
            medIds.add(18); // Diazepam
            medIds.add(7);  // Vitamin C
        } else if(specialty.equalsIgnoreCase("Dentist")) {
            medIds.add(46); // Toothpaste (Medicated)
            medIds.add(47); // Mouthwash
            medIds.add(49); // Pain Reliever (Dental)
        } else {
            // Default prescription for unknown specialties
            medIds.add(1);  // Paracetamol
            medIds.add(3);  // Amoxicillin
        }
        
        // Convert ArrayList to int array
        int[] result = new int[medIds.size()];
        for(int i = 0; i < medIds.size(); i++) {
            result[i] = medIds.get(i);
        }
        return result;
    }
    
    // Get prescription details as formatted string (without applying)
    public String getPrescriptionDetails(String specialty) {
        int[] prescriptionMedIds = getPrescriptionMedicines(specialty);
        java.util.ArrayList<String> medNames = new java.util.ArrayList<>();
        
        for(int medId : prescriptionMedIds) {
            // Find medicine in pharmacy
            for(int i = 0; i < medCount; i++) {
                if(pharmacy[i].id == medId) {
                    medNames.add(pharmacy[i].name);
                    break;
                }
            }
        }
        
        // Format prescription list
        if(medNames.size() > 0) {
            String prescriptionList = "";
            for(int i = 0; i < medNames.size(); i++) {
                prescriptionList += medNames.get(i);
                if(i < medNames.size() - 1) prescriptionList += ", ";
            }
            return prescriptionList;
        }
        return "";
    }
    
    // Apply prescription to patient (add medicines to bill and history)
    public void applyPrescription(Patient p, String specialty) {
        int[] prescriptionMedIds = getPrescriptionMedicines(specialty);
        double prescriptionTotal = 0.0;
        java.util.ArrayList<String> medNames = new java.util.ArrayList<>();
        
        for(int medId : prescriptionMedIds) {
            // Find medicine in pharmacy
            for(int i = 0; i < medCount; i++) {
                if(pharmacy[i].id == medId) {
                    double cost = pharmacy[i].price;
                    prescriptionTotal += cost;
                    medNames.add(pharmacy[i].name);
                    p.addBill(cost);
                    break;
                }
            }
        }
        
        // Add prescription to history
        if(medNames.size() > 0) {
            String prescriptionRecord = "Prescription (" + specialty + "): ";
            for(int i = 0; i < medNames.size(); i++) {
                prescriptionRecord += medNames.get(i);
                if(i < medNames.size() - 1) prescriptionRecord += ", ";
            }
            prescriptionRecord += " - $" + String.format("%.2f", prescriptionTotal);
            p.addHistory(prescriptionRecord);
        }
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

    // Check if a room number exists
    public boolean isValidRoomNumber(int roomNum) {
        for(int i = 0; i < roomCount; i++) {
            if(rooms[i].roomNumber == roomNum) {
                return true;
            }
        }
        return false;
    }
    
    // Check if a room is available (exists and not occupied)
    public boolean isRoomAvailable(int roomNum) {
        for(int i = 0; i < roomCount; i++) {
            if(rooms[i].roomNumber == roomNum) {
                return !rooms[i].isOccupied;
            }
        }
        return false;
    }
    
    public String bookRoom(Patient p, int roomNum, int days) {
        // Validate days
        if(days < 1) {
            return "Error: Number of days must be at least 1.";
        }
        
        for(int i = 0; i < roomCount; i++) {
            if(rooms[i].roomNumber == roomNum) {
                if(rooms[i].isOccupied) return "Error: Room is already occupied.";
                double totalCost = rooms[i].costPerDay * days;
                rooms[i].isOccupied = true;
                p.addBill(totalCost);
                p.addHistory("Room Admit: " + rooms[i].type + " (" + days + " days) - $" + totalCost);
                return "Success! Admitted to Room " + roomNum + " for " + days + " days.";
            }
        }
        return "Error: Invalid Room Number.";
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
            
            // Loop until valid payment choice is entered
            while(true) {
                System.out.print("Enter Payment? (1. Yes / 2. No): ");
                
                if (scanner.hasNextInt()) {
                    int payChoice = scanner.nextInt();
                    scanner.nextLine(); 
                    if (payChoice == 1) {
                        paymentGateway(p, totalAmount, scanner);
                        break;
                    } else if (payChoice == 2) {
                        System.out.println("Payment Skipped.");
                        System.out.println("Result: Please pay amount to discharge.");
                        break;
                    } else {
                        // Invalid number (not 1 or 2)
                        System.out.println("\033[31mInvalid input. Please enter 1 or 2.\033[0m");
                    }
                } else {
                    scanner.next(); // Clear invalid input
                    scanner.nextLine();
                    System.out.println("\033[31mInvalid input. Please enter 1 or 2.\033[0m");
                }
            }
        } else {
            System.out.println("Status: No outstanding amount. Discharged successfully.");
        }
     }
     
     public void paymentGateway(Patient p, double totalAmount, java.util.Scanner scanner) {
         System.out.println("\n----- PAYMENT GATEWAY -----");
         System.out.println("Total Amount to Pay: Rs. " + totalAmount);
         
         // Loop for payment method selection
         int choice = 0;
         while(true) {
             System.out.println("Choose Payment Method:");
             System.out.println("1. Card Payment");
             System.out.println("2. UPI Payment");
             System.out.println("3. Cash Payment");
             System.out.print("Enter your choice (1-3): ");
             
             if (!scanner.hasNextInt()) {
                 scanner.next(); // consume invalid input
                 scanner.nextLine(); 
                 System.out.println("\033[31mInvalid input. Please enter a number (1-3).\033[0m");
                 continue;
             }
             
             choice = scanner.nextInt();
             scanner.nextLine();
             
             if(choice >= 1 && choice <= 3) {
                 break; // Valid choice
             } else {
                 System.out.println("\033[31mInvalid Payment Option! Please enter 1, 2, or 3.\033[0m");
             }
         }
         
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
                         System.out.println("\033[31mInvalid Card Number! Must be 16 digits.\033[0m");
                     }
                 }
                 String cvv;
                 while (true) {
                     System.out.print("Enter CVV (3 digits): ");
                     cvv = scanner.nextLine();
                     if (cvv.matches("\\d{3}")) {
                         break;
                     } else {
                         System.out.println("\033[31mInvalid CVV! Must be 3 digits.\033[0m");
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
                         System.out.println("\033[31mInvalid UPI ID format! Use format: example@upi\033[0m");
                         // Continue loop to re-prompt
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
                             System.out.println("\033[31mInsufficient cash! Please enter full amount.\033[0m");
                         }
                     } else {
                         System.out.println("\033[31mInvalid amount! Please enter a number.\033[0m");
                         scanner.next(); // consume invalid input
                         scanner.nextLine(); // consume newline
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
                 System.out.println("\033[31mInvalid Payment Option!\033[0m");
         }
         
         if (paymentSuccess) {
             System.out.println("----- PAYMENT COMPLETED -----");
             p.resetBill(); // Clear the bill
             System.out.println("Status: Paid. Discharged successfully.");
         }
     }
 }
