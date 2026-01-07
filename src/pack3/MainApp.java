package pack3;
import pack2.HospitalAdmin;
import pack1.Patient;
import java.util.Scanner;

public class MainApp {
    private static HospitalAdmin hospital = new HospitalAdmin();
    private static Scanner scanner = new Scanner(System.in);
    
    // ANSI Color Codes
    private static final String RESET = "\033[0m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String YELLOW = "\033[33m";
    private static final String BLUE = "\033[34m";
    private static final String GOLD = "\033[38;5;214m"; // Gold color
    private static final String BOLD = "\033[1m";

    public static void main(String[] args) {
        printBanner();
        boolean systemRunning = true;
        Patient currentUser = null;

        while (systemRunning) {
            if (currentUser == null) {
                printHeader("MEDICARE HOSPITAL DASHBOARD");
                System.out.println("1. Register Patient");
                System.out.println("2. Login");
                System.out.println("3. Emergency (Immediate Assistance)");
                System.out.println("4. Exit");
                
                // Loop for menu selection until valid input
                boolean validChoice = false;
                while(!validChoice) {
                    System.out.print("Select: ");
                    
                    if(scanner.hasNextInt()) {
                        int choice = scanner.nextInt();
                        scanner.nextLine(); 

                        if(choice == 1) {
                            doRegister();
                            validChoice = true;
                        }
                        else if(choice == 2) {
                            currentUser = doLogin();
                            validChoice = true;
                        }
                        else if(choice == 3) {
                        System.out.println("\n!!! EMERGENCY SERVICES !!!");
                        System.out.println("1. Accident / Trauma");
                        System.out.println("2. Chest Pain / Heart Attack");
                        System.out.println("3. Child Emergency");
                        System.out.println("4. Severe Breathing Issue");
                        System.out.println("5. General / Other");
                        
                        String emergencySpecialty = "";
                        int emChoice = 0;
                        
                        while(true) {
                            System.out.print("Select Emergency Case: ");
                            if(scanner.hasNextInt()) {
                                emChoice = scanner.nextInt();
                                scanner.nextLine(); // consume newline
                                if(emChoice >= 1 && emChoice <= 5) {
                                    // Map emergency to specialty
                                    switch(emChoice) {
                                        case 1: emergencySpecialty = "Orthopedics"; break;
                                        case 2: emergencySpecialty = "Cardiology"; break;
                                        case 3: emergencySpecialty = "Pediatrics"; break;
                                        case 4: emergencySpecialty = "ENT Specialist"; break;
                                        case 5: emergencySpecialty = "Gen. Physician"; break;
                                    }
                                    
                                    hospital.handleEmergency(emChoice);
                                    
                                    // Check if user is logged in
                                    if(currentUser != null) {
                                        // For emergency, use today's date
                                        java.time.LocalDate today = java.time.LocalDate.now();
                                        
                                        // Try to find first available doctor with slot for today
                                        int[] availableDoctorSlot = hospital.findFirstAvailableDoctorSlot(emergencySpecialty, today);
                                        
                                        if(availableDoctorSlot != null) {
                                            int doctorId = availableDoctorSlot[0];
                                            int slot = availableDoctorSlot[1];
                                            
                                            // Book automatically with today's date
                                            String result = hospital.bookAppointment(currentUser, doctorId, slot, today);
                                            if(result.contains("Success")) {
                                                System.out.println(GREEN + "\n" + result + RESET);
                                                System.out.println("Emergency appointment booked automatically for today!");
                                                waitForBack();
                                            } else {
                                                System.out.println(RED + result + RESET);
                                                // Show all doctors if booking failed
                                                System.out.println("\nAll doctors in " + emergencySpecialty + ":");
                                                hospital.searchDoctorBySpecialty(emergencySpecialty);
                                                waitForBack();
                                            }
                                        } else {
                                            // No available slots, show all doctors
                                            System.out.println(RED + "\nAll slots are booked for " + emergencySpecialty + " doctors today." + RESET);
                                            System.out.println("Available doctors:");
                                            hospital.searchDoctorBySpecialty(emergencySpecialty);
                                            waitForBack();
                                        }
                                    } else {
                                        // Not logged in, just show doctors
                                        System.out.println("\nPlease login or register to book an appointment.");
                                        waitForBack();
                                    }
                                    validChoice = true;
                                    break; // Valid choice, exit loop
                                } else {
                                    System.out.println(RED + "Invalid input. Please enter a number between 1 and 5." + RESET);
                                }
                            } else {
                                scanner.next(); // consume invalid input
                                scanner.nextLine(); // consume newline
                                System.out.println(RED + "Invalid input. Please enter a number between 1 and 5." + RESET);
                            }
                        }
                        validChoice = true;
                    }
                    else if(choice == 4) {
                        systemRunning = false;
                        validChoice = true;
                    }
                    else {
                        System.out.println(RED + "Invalid input. Please enter a number (1-4)." + RESET);
                        // Loop will continue without reprinting menu
                    }
                } else {
                    scanner.next(); // consume invalid input
                    scanner.nextLine(); // consume newline
                    System.out.println(RED + "Invalid input. Please enter a number (1-4)." + RESET);
                    // Loop will continue without reprinting menu
                }
                }
            } else {
                printHeader("PATIENT DASHBOARD: " + currentUser.getUsername().toUpperCase());
                
                // Loop for menu selection
                boolean menuValid = false;
                while(!menuValid) {
                    System.out.println("1. View Doctors");
                    System.out.println("2. Search Specialist");
                    System.out.println("3. Book Appointment");
                    System.out.println("4. Visit Pharmacy");
                    System.out.println("5. Admit to Room (Room Service)");
                    System.out.println("6. View Medical History");
                    System.out.println("7. Discharge (Generate Bill)");
                    System.out.println("0. Logout");
                    System.out.print("Select: ");

                    if(scanner.hasNextInt()) {
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch(choice) {
                            case 1: 
                                hospital.showDoctors();
                                waitForBack();
                                menuValid = true;
                                break;
                            case 2:
                                // Loop for specialty input
                                while(true) {
                                    System.out.print("Enter Specialty (or 0 to cancel): ");
                                    String specialty = scanner.nextLine().trim();
                                    
                                    if(specialty.equals("0")) {
                                        menuValid = true;
                                        break; // User cancelled
                                    }
                                    
                                    if(specialty.isEmpty()) {
                                        System.out.println(RED + "Invalid input. Specialty cannot be empty. Please re-enter." + RESET);
                                        continue;
                                    }
                                    
                                    hospital.searchDoctorBySpecialty(specialty);
                                    waitForBack();
                                    menuValid = true;
                                    break;
                                }
                                break;
                        case 3:
                            hospital.showDoctors();
                            
                            // Loop to get valid doctor ID
                            int dId = 0;
                            while(true) {
                                System.out.print("Doctor ID (or 0 to cancel): ");
                                if(scanner.hasNextInt()) {
                                    dId = scanner.nextInt();
                                    scanner.nextLine(); // consume newline
                                    if(dId == 0) {
                                        break; // User cancelled
                                    }
                                    
                                    // Validate doctor ID
                                    if(hospital.isValidDoctorId(dId)) {
                                        break; // Valid doctor ID, exit loop
                                    } else {
                                        System.out.println(RED + "Invalid Doctor ID. Please re-enter." + RESET);
                                    }
                                } else {
                                    scanner.next(); // consume invalid input
                                    scanner.nextLine(); // consume newline
                                    System.out.println(RED + "Invalid input. Please enter a valid Doctor ID." + RESET);
                                }
                            }
                            
                            // If user didn't cancel, proceed with date and slot selection
                            if(dId != 0) {
                                // Get appointment date
                                java.time.LocalDate appointmentDate = null;
                                java.time.LocalDate today = java.time.LocalDate.now();
                                
                                while(appointmentDate == null) {
                                    System.out.print("Enter Appointment Date (YYYY-MM-DD format, today or future date): ");
                                    String dateInput = scanner.nextLine().trim();
                                    
                                    // Check if input is empty
                                    if(dateInput.isEmpty()) {
                                        System.out.println(RED + "Invalid input. Date cannot be empty. Please re-enter." + RESET);
                                        continue;
                                    }
                                    
                                    // Validate date format
                                    if(!hospital.isValidDateFormat(dateInput)) {
                                        System.out.println(RED + "Invalid date format. Please use YYYY-MM-DD format (e.g., " + today + ")." + RESET);
                                        System.out.println("Example: " + today);
                                        continue;
                                    }
                                    
                                    // Parse date
                                    appointmentDate = hospital.parseDate(dateInput);
                                    if(appointmentDate == null) {
                                        System.out.println(RED + "Invalid date. Please enter a valid date in YYYY-MM-DD format." + RESET);
                                        System.out.println("Example: " + today);
                                        continue;
                                    }
                                    
                                    // Validate date is not in the past
                                    if(!hospital.isValidAppointmentDate(appointmentDate)) {
                                        System.out.println(RED + "Invalid date. Appointment date cannot be in the past. Please enter today's date (" + today + ") or a future date." + RESET);
                                        appointmentDate = null;
                                        continue;
                                    }
                                }
                                
                                // Loop to get available slot
                                boolean appointmentBooked = false;
                                while(!appointmentBooked) {
                                    // Show available slots for selected date
                                    System.out.println("\nAvailable Time Slots for " + appointmentDate + ":");
                                    boolean morningAvailable = hospital.isSlotAvailable(dId, 1, appointmentDate);
                                    boolean afternoonAvailable = hospital.isSlotAvailable(dId, 2, appointmentDate);
                                    boolean eveningAvailable = hospital.isSlotAvailable(dId, 3, appointmentDate);
                                    
                                    System.out.print("1. Morning");
                                    if (!morningAvailable) System.out.print(" (Booked)");
                                    System.out.println();
                                    
                                    System.out.print("2. Afternoon");
                                    if (!afternoonAvailable) System.out.print(" (Booked)");
                                    System.out.println();
                                    
                                    System.out.print("3. Evening");
                                    if (!eveningAvailable) System.out.print(" (Booked)");
                                    System.out.println();
                                    
                                    System.out.print("Choose Slot (or 0 to cancel): ");
                                    if(scanner.hasNextInt()) {
                                        int slot = scanner.nextInt();
                                        scanner.nextLine(); // consume newline
                                        if(slot == 0) {
                                            break; // User cancelled
                                        }
                                        
                                        // Validate slot number
                                        if(slot < 1 || slot > 3) {
                                            System.out.println(RED + "Invalid slot. Please enter 1, 2, or 3." + RESET);
                                            continue;
                                        }
                                        
                                        // Try to book appointment
                                        String result = hospital.bookAppointment(currentUser, dId, slot, appointmentDate);
                                        
                                        // Check if slot was already booked
                                        if(result.contains("already booked")) {
                                            System.out.println(RED + result + RESET);
                                            System.out.println("Please choose another available slot.");
                                        } else if(result.contains("Success")) {
                                            System.out.println(GREEN + result + RESET);
                                            appointmentBooked = true;
                                            waitForBack();
                                        } else {
                                            System.out.println(RED + result + RESET);
                                        }
                                    } else {
                                        scanner.next(); // consume invalid input
                                        scanner.nextLine(); // consume newline
                                        System.out.println(RED + "Invalid input. Please enter 1, 2, or 3." + RESET);
                                    }
                                }
                            }
                            break;
                        case 4:
                            handlePharmacy(currentUser);
                            break;
                        case 5:
                            hospital.showAvailableRooms();
                            
                            // Loop to get valid room number
                            int rNum = 0;
                            while(true) {
                                System.out.print("Enter Room Number to Book (or 0 to cancel): ");
                                if(scanner.hasNextInt()) {
                                    rNum = scanner.nextInt();
                                    scanner.nextLine(); // consume newline
                                    if(rNum == 0) {
                                        break; // User cancelled
                                    }
                                    
                                    // Validate room number
                                    if(!hospital.isValidRoomNumber(rNum)) {
                                        System.out.println(RED + "Invalid Room Number. Please re-enter." + RESET);
                                    } else if(!hospital.isRoomAvailable(rNum)) {
                                        System.out.println(RED + "Room is already occupied. Please choose another room." + RESET);
                                    } else {
                                        break; // Valid and available room, exit loop
                                    }
                                } else {
                                    scanner.next(); // consume invalid input
                                    scanner.nextLine(); // consume newline
                                    System.out.println(RED + "Invalid input. Please enter a valid Room Number." + RESET);
                                }
                            }
                            
                            // If user didn't cancel, proceed with days input
                            if(rNum != 0) {
                                // Loop to get valid days
                                int days = 0;
                                while(true) {
                                    System.out.print("Number of Days (at least 1): ");
                                    if(scanner.hasNextInt()) {
                                        days = scanner.nextInt();
                                        scanner.nextLine(); // consume newline
                                        
                                        // Validate days must be at least 1
                                        if(days < 1) {
                                            System.out.println(RED + "Invalid input. Number of days must be at least 1. Please re-enter." + RESET);
                                        } else {
                                            break; // Valid days, exit loop
                                        }
                                    } else {
                                        scanner.next(); // consume invalid input
                                        scanner.nextLine(); // consume newline
                                        System.out.println(RED + "Invalid input. Please enter a number (at least 1)." + RESET);
                                    }
                                }
                                
                                // Book the room
                                String result = hospital.bookRoom(currentUser, rNum, days);
                                if(result.contains("Success")) {
                                    System.out.println(GREEN + result + RESET);
                                } else {
                                    System.out.println(RED + result + RESET);
                                }
                                waitForBack();
                            }
                            break;
                        case 6: 
                            currentUser.viewHistory();
                            waitForBack();
                            break;
                        case 7: 
                            hospital.generateBill(currentUser, scanner);
                            waitForBack();
                            break;
                        
                            case 0: 
                                currentUser = null; 
                                System.out.println("Logged out."); 
                                menuValid = true;
                                break;
                            default: 
                                System.out.println(RED + "Invalid choice. Please enter a number between 0 and 7." + RESET);
                                // Loop will continue, asking again
                                break;
                        }
                    } else {
                        scanner.next(); // consume invalid input
                        scanner.nextLine(); // consume newline
                        System.out.println(RED + "Invalid input. Please enter a number between 0 and 7." + RESET);
                        // Loop will continue, asking again
                    }
                }
            }
        }
    }

    private static void printHeader(String title) {
        System.out.println("\n" + GOLD + "####################################" + RESET);
        System.out.println(GOLD + "   " + title + RESET);
        System.out.println(GOLD + "####################################" + RESET);
    }
    
    // Print banner at startup
    public static void printBanner() {
        clear();
        String art = BLUE +
                "   /$$$$$$  /$$    /$$       /$$   /$$  /$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$ /$$$$$$$$  /$$$$$$  /$$        /$$$$$$ \n" +
                "  /$$__  $$| $$   | $$      | $$  | $$ /$$__  $$ /$$__  $$| $$__  $$|_  $$_/|__  $$__/ /$$__  $$| $$       /$$__  $$\n" +
                " | $$  \\__/| $$   | $$      | $$  | $$| $$  \\ $$| $$  \\__/| $$  \\ $$  | $$     | $$   | $$  \\ $$| $$      | $$  \\__/\n" +
                " | $$      | $$  / $$/      | $$$$$$$$| $$  | $$|  $$$$$$ | $$$$$$$/  | $$     | $$   | $$$$$$$$| $$      |  $$$$$$ \n" +
                " | $$       \\  $$ $$/       | $$__  $$| $$  | $$ \\____  $$| $$____/   | $$     | $$   | $$__  $$| $$       \\____  $$\n" +
                " | $$    $$  \\  $$$/        | $$  | $$| $$  | $$ /$$  \\ $$| $$        | $$     | $$   | $$  | $$| $$       /$$  \\ $$\n" +
                " |  $$$$$$/   \\  $/         | $$  | $$|  $$$$$$/|  $$$$$$/| $$       /$$$$$$   | $$   | $$  | $$| $$$$$$$$|  $$$$$$/\n" +
                "  \\______/     \\_/          |__/  |__/ \\______/  \\______/ |__/      |______/   |__/   |__/  |__/|________/ \\______/ \n" +
                RESET;

        for (String line : art.split("\n")) {
            System.out.println(line);
            sleep(50);
        }

        String paddingHeader = "                                         ";
        typeWrite(BOLD + GOLD + paddingHeader + "Welcome to Medicare Hospital" + RESET, 50);
        String paddingSlogan = "                                 ";
        String sloganStr = "A PLACE WHERE HEARTBEATS FIND STRENGTH!";
        System.out.print(GREEN + paddingSlogan);
        String[] words = sloganStr.split(" ");
        for (int i = 0; i < words.length; i++) {
            System.out.print(words[i] + (i < words.length - 1 ? " " : ""));
            sleep(100);
        }
        System.out.println(RESET);
        sleep(500);
    }
    // public static void printBanner() {
    //     clear();
    //     String art = BLUE +
    //             "   /$$$$$$  /$$    /$$       /$$   /$$  /$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$$$$$  /$$       \n" +
    //             "  /$$__  $$|  $$  /$$/      | $$  | $$ /$$__  $$ /$$__  $$| $$__  $$|_  $$_/|__  $$__/ /$$__  $$| $$       \n" +
    //             " | $$  \\__/ \\  $$/$$/       | $$  | $$| $$  \\ $$| $$  \\__/| $$  \\ $$  | $$     | $$   | $$  \\ $$| $$       \n" +
    //             " | $$        \\  $$$/        | $$$$$$$$| $$  | $$|  $$$$$$ | $$$$$$$/  | $$     | $$   | $$$$$$$$| $$       \n" +
    //             " | $$         \\  $/         | $$__  $$| $$  | $$ \\____  $$| $$____/   | $$     | $$   | $$__  $$| $$       \n" +
    //             " | $$    $$    \\  /         | $$  | $$| $$  | $$ /$$  \\ $$| $$        | $$     | $$   | $$  | $$| $$       \n" +
    //             " |  $$$$$$/     \\/          | $$  | $$|  $$$$$$/|  $$$$$$/| $$       /$$$$$$   | $$   | $$  | $$| $$$$$$$$\n" +
    //             "  \\______/                  |__/  |__/ \\______/  \\______/ |__/      |______/   |__/   |__/  |__/|________/\n" +
    //             RESET;

    //     for (String line : art.split("\n")) {
    //         System.out.println(line);
    //         sleep(50);
    //     }

    //     String paddingHeader = "                                         ";
    //     typeWrite(BOLD + GOLD + paddingHeader + "Welcome to Medicare Hospital" + RESET, 50);
    //     String paddingSlogan = "                                 ";
    //     String sloganStr = "A PLACE WHERE HEARTBEATS FIND STRENGTH!";
    //     System.out.print(GREEN + paddingSlogan);
    //     String[] words = sloganStr.split(" ");
    //     for (int i = 0; i < words.length; i++) {
    //         System.out.print(words[i] + (i < words.length - 1 ? " " : ""));
    //         sleep(100);
    //     }
    //     System.out.println(RESET);
    //     sleep(500);
    // }
    
    public static void typeWrite(String text, int speed) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            sleep(speed);
        }
        System.out.println();
    }
    
    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // If clear fails, just print newlines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
    
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Helper method to wait for "0 to go back"
    private static void waitForBack() {
        while(true) {
            System.out.print("\nPress 0 to go back: ");
            if(scanner.hasNextInt()) {
                int back = scanner.nextInt();
                scanner.nextLine();
                if(back == 0) {
                    break;
                } else {
                    System.out.println(RED + "Invalid input. Please press 0 to go back." + RESET);
                }
            } else {
                scanner.next(); // consume invalid input
                scanner.nextLine(); // consume newline
                System.out.println(RED + "Invalid input. Please press 0 to go back." + RESET);
            }
        }
    }

    // ---  REGISTRATION Mtd ---
    private static void doRegister() {
        System.out.println("\n--- REGISTRATION ---");
        
        // 1. Loop for Name
        String u = "";
        while(true) {
            System.out.print("Name (min 3 characters): "); 
            u = scanner.nextLine();
            if(!hospital.isValidName(u)) {
                System.out.println(RED + "Error: Name must contain only letters and spaces (minimum 3 characters)." + RESET);
            } else if(hospital.isUserExists(u)) {
                System.out.println(RED + "Error: Username already exists. Try another." + RESET);
            } else {
                break; // Valid name, exit loop
            }
        }

        // 2. Loop for Email
        String e = "";
        while(true) {
            System.out.print("Email: "); 
            e = scanner.nextLine();
            // Convert email to lowercase (capital letters are allowed but converted)
            String emailLower = e.toLowerCase();
            if(!hospital.isValidEmail(e)) {
                System.out.println(RED + "Error: Invalid Email format." + RESET);
            } else if(hospital.isEmailExists(emailLower)) {
                System.out.println(RED + "Error: Email already registered. Try another." + RESET);
            } else {
                e = emailLower; // Store in lowercase
                break; // Valid email, exit loop
            }
        }

        // 3. Loop for Password
        String p = "";
        while(true) {
            System.out.println("Password Requirements:");
            System.out.println("  - 8 to 32 characters");
            System.out.println("  - At least one lowercase letter (a-z)");
            System.out.println("  - At least one uppercase letter (A-Z)");
            System.out.println("  - At least one digit (0-9)");
            System.out.println("  - At least one special character (!@#$%^&* etc.)");
            System.out.println("  - No whitespace allowed");
            System.out.print("Password: "); 
            p = scanner.nextLine();
            if(!hospital.isValidPassword(p)) {
                String errorMsg = hospital.getPasswordValidationError(p);
                if(errorMsg != null) {
                    System.out.println(RED + "Error: " + errorMsg + RESET);
                } else {
                    System.out.println(RED + "Error: Invalid password. Please check the requirements." + RESET);
                }
            } else {
                break; // Valid password, exit loop
            }
        }

        // 4. Loop for Mobile
        String m = "";
        while(true) {
            System.out.print("Mobile (start 9/8/7/6): "); 
            m = scanner.nextLine();
            if(!hospital.isValidMobile(m)) {
                System.out.println(RED + "Error: Invalid Mobile Format (Must start with 9,8,7,6 and be 10 digits)." + RESET);
            } else {
                break; // Valid mobile, exit loop
            }
        }

        // 5. Loop for Age
        int a = 0; 
        while(true) {
            System.out.print("Age: "); 
            if(scanner.hasNextInt()) {
                a = scanner.nextInt();
                scanner.nextLine(); 
                if(a > 0 && a < 120) {
                    break; 
                } else {
                    System.out.println(RED + "Error: Please enter a realistic age." + RESET);
                }
            } else { 
                System.out.println(RED + "Error: Invalid Age (Numbers only)." + RESET); 
                scanner.next(); 
            }
        }

        // 6. Loop for Gender
        String gender = "";
        while(true) {
            System.out.println("Gender:");
            System.out.println("1. Male");
            System.out.println("2. Female");
            System.out.println("3. Others");
            System.out.print("Select (1/2/3): ");
            if(scanner.hasNextInt()) {
                int genderChoice = scanner.nextInt();
                scanner.nextLine();
                if(hospital.isValidGender(genderChoice)) {
                    gender = hospital.getGenderString(genderChoice);
                    break;
                } else {
                    System.out.println(RED + "Invalid. Please select valid gender option." + RESET);
                }
            } else {
                scanner.nextLine();
                System.out.println(RED + "Invalid. Please select valid gender option." + RESET);
            }
        }

        // 7. Loop for Blood Group
        String bg = "";
        while(true) {
            System.out.print("Blood Group (e.g., A+, O-): "); 
            bg = scanner.nextLine();
            if(!hospital.isValidBloodGroup(bg)) {
                System.out.println(RED + "Error: Invalid Blood Group (Use A+, A-, B+, B-, O+, O-, AB+, AB-)." + RESET);
            } else {
                break; // Valid blood group
            }
        }

        String res = hospital.register(u, e, p, m, a, bg, gender);
        if(res.equals("Success")) { 
            System.out.println(GREEN + "Registration Successful!" + RESET);
            // Display patient information
            Patient newPatient = hospital.findPatientByNameOrEmail(u);
            if(newPatient != null) {
                System.out.println("\n" + newPatient.displayInfo());
            }
            while(true) {
                System.out.print("\nPress 0 to go back: ");
                if(scanner.hasNextInt()) {
                    int back = scanner.nextInt();
                    scanner.nextLine();
                    if(back == 0) {
                        break;
                    } else {
                        System.out.println(RED + "Invalid input. Please press 0 to go back." + RESET);
                    }
                } else {
                    scanner.next(); // consume invalid input
                    scanner.nextLine(); // consume newline
                    System.out.println(RED + "Invalid input. Please press 0 to go back." + RESET);
                }
            }
        } else { 
            System.out.println(RED + res + RESET); 
        }
    }

    private static Patient doLogin() {
        System.out.println("\n--- LOGIN ---");
        
        // Check if any patients are registered
        if(hospital.getPatientCount() == 0) {
            System.out.println(RED + "No registrations found yet. Please register first." + RESET);
            while(true) {
                System.out.print("Press 0 to go back: ");
                if(scanner.hasNextInt()) {
                    int back = scanner.nextInt();
                    scanner.nextLine();
                    if(back == 0) {
                        break;
                    } else {
                        System.out.println(RED + "Invalid input. Please press 0 to go back." + RESET);
                    }
                } else {
                    scanner.next(); // consume invalid input
                    scanner.nextLine(); // consume newline
                    System.out.println(RED + "Invalid input. Please press 0 to go back." + RESET);
                }
            }
            return null;
        }
        
        int attempts = 3;
        boolean firstAttempt = true;
        
        while(attempts > 0) {
            if(!firstAttempt) {
                System.out.println(RED + "Attempts remaining: " + attempts + RESET);
            }
            
            System.out.print("\nName/Email: "); 
            String nameOrEmail = scanner.nextLine();
            System.out.print("Password: "); 
            String p = scanner.nextLine();
            
            HospitalAdmin.LoginResult result = hospital.login(nameOrEmail, p);
            
            if(result.isSuccess()) {
                Patient pat = result.getPatient();
                System.out.println(GREEN + "Welcome " + pat.getUsername() + RESET); 
                return pat; 
            } else if(result.isUserNotFound()) {
                System.out.println(RED + "User not found." + RESET);
                attempts--;
                firstAttempt = false;
            } else if(result.isIncorrectPassword()) {
                System.out.println(RED + "Incorrect password." + RESET);
                attempts--;
                firstAttempt = false;
            }
        }
        
        System.out.println(RED + "\nLogin Failed. Max attempts reached." + RESET);
        while(true) {
            System.out.print("Forgot Password? (1. Yes / 2. No): ");
            if(scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if(choice == 1) {
                    doForgotPassword();
                    // After password reset, return to login
                    return doLogin();
                } else if(choice == 2) {
                    // User chose No, return to main menu
                    return null;
                } else {
                    // Invalid number (not 1 or 2)
                    System.out.println(RED + "Invalid input. Please enter 1 or 2." + RESET);
                }
            } else {
                // Invalid input (not a number)
                scanner.next(); // consume invalid input
                scanner.nextLine(); // consume newline
                System.out.println(RED + "Invalid input. Please re-enter again." + RESET);
            }
        }
    }

    private static void doForgotPassword() {
        System.out.println("\n--- FORGOT PASSWORD ---");
        
        // Step 1: Get name and mobile - loop until valid combination is found
        Patient p = null;
        String name = "";
        String mobile = "";
        
        while(p == null) {
            System.out.print("Enter Name (or 0 to cancel): ");
            name = scanner.nextLine();
            
            // Check if user wants to cancel
            if(name.equals("0")) {
                System.out.println("Cancelled. Returning to login menu...");
                return;
            }
            
            System.out.print("Enter Mobile Number: ");
            mobile = scanner.nextLine();
            
            // Verify name and mobile exist
            p = hospital.findPatientByNameMobile(name, mobile);
            if(p == null) {
                System.out.println(RED + "Error: Name and Mobile combination not found. Please re-enter." + RESET);
            }
        }
        
        // Step 2: Generate and display OTP
        int otp = hospital.generateOTP();
        System.out.println("\nOTP Generated: " + otp);
        System.out.println("(In production, OTP would be sent via SMS)");
        
        // Step 3: Verify OTP with 3 attempts (with option to regenerate)
        boolean otpVerified = false;
        boolean shouldContinue = true;
        
        while(!otpVerified && shouldContinue) {
            int otpAttempts = 3;
            boolean currentOTPVerified = false;
            
            while(otpAttempts > 0 && !currentOTPVerified) {
                System.out.println("\nOTP Attempts remaining: " + otpAttempts);
                System.out.print("Enter OTP (4 digits, or 0 to regenerate): ");
                
                if(scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    
                    if(input == 0) {
                        // User wants to regenerate OTP
                        System.out.println(YELLOW + "Regenerating OTP..." + RESET);
                        otp = hospital.generateOTP();
                        System.out.println("New OTP Generated: " + otp);
                        System.out.println("(In production, OTP would be sent via SMS)");
                        break; // reset attempts
                    } else if(input == otp) {
                        currentOTPVerified = true;
                        otpVerified = true;
                        System.out.println(GREEN + "OTP Verified Successfully!" + RESET);
                    } else {
                        System.out.println(RED + "Invalid OTP. Please try again." + RESET);
                        otpAttempts--;
                    }
                } else {
                    System.out.println(RED + "Invalid input. Please enter a 4-digit number." + RESET);
                    scanner.nextLine(); 
                    otpAttempts--;
                }
            }
            
            // If OTP verification failed after 3 attempts
            if(!otpVerified && otpAttempts == 0) {
                System.out.println(RED + "\nOTP verification failed. Maximum attempts reached." + RESET);
                
                // Loop until valid choice (1 or 2) is entered
                while(true) {
                    System.out.print("Regenerate OTP? (1. Yes / 2. No - Return to Login): ");
                    
                    if(scanner.hasNextInt()) {
                        int regenChoice = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        if(regenChoice == 1) {
                            // Regenerate OTP
                            System.out.println(YELLOW + "Regenerating OTP..." + RESET);
                            otp = hospital.generateOTP();
                            System.out.println("New OTP Generated: " + otp);
                            System.out.println("(In production, OTP would be sent via SMS)");
                            break; // Exit loop, continue to verify new OTP
                        } else if(regenChoice == 2) {
                            // User chose to return to login
                            System.out.println("Returning to login menu...");
                            shouldContinue = false;
                            break; // Exit loop
                        } else {
                            // Invalid number (not 1 or 2)
                            System.out.println(RED + "Invalid input. Please enter 1 or 2." + RESET);
                        }
                    } else {
                        scanner.next(); // consume invalid input
                        scanner.nextLine(); // consume newline
                        System.out.println(RED + "Invalid input. Please enter 1 or 2." + RESET);
                    }
                }
            }
        }
        
        // Step 4: If OTP verified, create new password
        if(otpVerified) {
            System.out.println("\n--- CREATE NEW PASSWORD ---");
            String newPassword = "";
            while(true) {
                System.out.println("Password Requirements:");
                System.out.println("  - 8 to 32 characters");
                System.out.println("  - At least one lowercase letter (a-z)");
                System.out.println("  - At least one uppercase letter (A-Z)");
                System.out.println("  - At least one digit (0-9)");
                System.out.println("  - At least one special character (!@#$%^&* etc.)");
                System.out.println("  - No whitespace allowed");
                System.out.print("Enter New Password: ");
                newPassword = scanner.nextLine();
                if(hospital.isValidPassword(newPassword)) {
                    break;
                } else {
                    String errorMsg = hospital.getPasswordValidationError(newPassword);
                    if(errorMsg != null) {
                        System.out.println(RED + "Error: " + errorMsg + RESET);
                    } else {
                        System.out.println(RED + "Error: Invalid password. Please check the requirements." + RESET);
                    }
                }
            }
            
            // Update password
            String result = hospital.resetPassword(name, mobile, newPassword);
            if(result.contains("Success")) {
                System.out.println(GREEN + "Password reset successful! You can now login with your new password." + RESET);
            } else {
                System.out.println(RED + result + RESET);
            }
        }
    }

    private static void handlePharmacy(Patient p) {
        boolean shopping = true;
        while(shopping) {
            hospital.showPharmacy();
            System.out.printf("Current Bill: $%.2f%n", p.getMedBill());
            
            // Loop for medicine ID input
            while(true) {
                System.out.print("Enter Medicine ID (0 to exit): ");
                if(scanner.hasNextInt()) {
                    int mId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if(mId == 0) {
                        shopping = false;
                        break;
                    }
                    
                    // Loop for quantity input
                    while(true) {
                        System.out.print("Quantity (at least 1): ");
                        if(scanner.hasNextInt()) {
                            int qty = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            if(qty < 1) {
                                System.out.println(RED + "Invalid input. Quantity must be at least 1. Please re-enter." + RESET);
                                continue;
                            }
                            
                            String result = hospital.buyMedicine(p, mId, qty);
                            if(result.contains("Invalid")) {
                                System.out.println(RED + result + RESET);
                            } else {
                                System.out.println(GREEN + result + RESET);
                            }
                            break; // Valid quantity, exit quantity loop
                        } else {
                            scanner.next(); // consume invalid input
                            scanner.nextLine(); // consume newline
                            System.out.println(RED + "Invalid input. Please enter a number (at least 1)." + RESET);
                        }
                    }
                    break; // Exit medicine ID loop to show menu again
                } else {
                    scanner.next(); // consume invalid input
                    scanner.nextLine(); // consume newline
                    System.out.println(RED + "Invalid input. Please enter a valid Medicine ID or 0 to exit." + RESET);
                }
            }
        }
    }
}