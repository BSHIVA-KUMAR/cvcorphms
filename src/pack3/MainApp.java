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
                printHeader("CVCORP HOSPITAL Appointment SYSTEM");
                System.out.println("1. Register Patient");
                System.out.println("2. Login");
                System.out.println("3. Emergency (Immediate Assistance)");
                System.out.println("4. Exit");
                System.out.print("Select: ");
                
                if(scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 

                    if(choice == 1) doRegister();
                    else if(choice == 2) currentUser = doLogin();
                    else if(choice == 3) {
                        System.out.println("\n!!! EMERGENCY SERVICES !!!");
                        System.out.println("1. Accident / Trauma");
                        System.out.println("2. Chest Pain / Heart Attack");
                        System.out.println("3. Child Emergency");
                        System.out.println("4. Severe Breathing Issue");
                        System.out.println("5. General / Other");
                        System.out.print("Select Emergency Case: ");
                        if(scanner.hasNextInt()) {
                            int emChoice = scanner.nextInt();
                            hospital.handleEmergency(emChoice);
                        } else {
                            scanner.next();
                            System.out.println("Invalid input.");
                        }
                    }
                    else if(choice == 4) systemRunning = false;
                    else System.out.println("Invalid.");
                } else {
                    scanner.next(); 
                }
            } else {
                printHeader("PATIENT DASHBOARD: " + currentUser.getUsername().toUpperCase());
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
                            break;
                        case 2:
                            System.out.print("Enter Specialty: ");
                            hospital.searchDoctorBySpecialty(scanner.nextLine());
                            waitForBack();
                            break;
                        case 3:
                            hospital.showDoctors();
                            System.out.print("Doctor ID (or 0 to cancel): ");
                            if(scanner.hasNextInt()) {
                                int dId = scanner.nextInt();
                                scanner.nextLine(); // consume newline
                                if(dId == 0) {
                                    break;
                                }
                                
                                // Show available slots
                                System.out.println("\nAvailable Time Slots:");
                                boolean morningAvailable = hospital.isSlotAvailable(dId, 1);
                                boolean afternoonAvailable = hospital.isSlotAvailable(dId, 2);
                                boolean eveningAvailable = hospital.isSlotAvailable(dId, 3);
                                
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
                                        break;
                                    }
                                    String result = hospital.bookAppointment(currentUser, dId, slot);
                                    System.out.println(result);
                                    
                                    // If slot was booked, suggest other slots
                                    if(result.contains("already booked")) {
                                        System.out.println("\nPlease try booking a different time slot.");
                                    }
                                    waitForBack();
                                } else {
                                    scanner.nextLine(); // consume invalid input
                                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                                }
                            } else {
                                scanner.nextLine();
                                System.out.println("Invalid input.");
                            }
                            break;
                        case 4:
                            handlePharmacy(currentUser);
                            break;
                        case 5:
                            hospital.showAvailableRooms();
                            System.out.print("Enter Room Number to Book (or 0 to cancel): ");
                            if(scanner.hasNextInt()) {
                                int rNum = scanner.nextInt();
                                scanner.nextLine(); // consume newline
                                if(rNum != 0) {
                                    System.out.print("Number of Days: ");
                                    if(scanner.hasNextInt()) {
                                        int days = scanner.nextInt();
                                        scanner.nextLine(); // consume newline
                                        System.out.println(hospital.bookRoom(currentUser, rNum, days));
                                        waitForBack();
                                    } else {
                                        scanner.nextLine();
                                        System.out.println("Invalid input.");
                                    }
                                }
                            } else {
                                scanner.nextLine();
                                System.out.println("Invalid input.");
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
                        
                        case 0: currentUser = null; System.out.println("Logged out."); break;
                        default: System.out.println("Invalid choice.");
                        
                    }
                } else {
                    scanner.next();
                }
            }
        }
    }

    private static void printHeader(String title) {
        System.out.println("\n" + GOLD + "##################################" + RESET);
        System.out.println(GOLD + "   " + title + RESET);
        System.out.println(GOLD + "##################################" + RESET);
    }
    
    // Print banner at startup
    public static void printBanner() {
        clear();
        String art = BLUE +
                "   /$$$$$$  /$$    /$$       /$$   /$$  /$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$ /$$$$$$$$ /$$$$$$  /$$       \n" +
                "  /$$__  $$|  $$  /$$/      | $$  | $$ /$$__  $$ /$$__  $$| $$__  $$|_  $$_/|__  $$__/ /$$__  $$| $$       \n" +
                " | $$  \\__/ \\  $$/$$/       | $$  | $$| $$  \\ $$| $$  \\__/| $$  \\ $$  | $$     | $$   | $$  \\ $$| $$       \n" +
                " | $$        \\  $$$/        | $$$$$$$$| $$  | $$|  $$$$$$ | $$$$$$$/  | $$     | $$   | $$$$$$$$| $$       \n" +
                " | $$         \\  $/         | $$__  $$| $$  | $$ \\____  $$| $$____/   | $$     | $$   | $$__  $$| $$       \n" +
                " | $$    $$    \\  /         | $$  | $$| $$  | $$ /$$  \\ $$| $$        | $$     | $$   | $$  | $$| $$       \n" +
                " |  $$$$$$/     \\/          | $$  | $$|  $$$$$$/|  $$$$$$/| $$       /$$$$$$   | $$   | $$  | $$| $$$$$$$$\n" +
                "  \\______/                  |__/  |__/ \\______/  \\______/ |__/      |______/   |__/   |__/  |__/|________/\n" +
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
        System.out.print("\nPress 0 to go back: ");
        while(true) {
            if(scanner.hasNextInt()) {
                int back = scanner.nextInt();
                scanner.nextLine();
                if(back == 0) {
                    break;
                } else {
                    System.out.print("Press 0 to go back: ");
                }
            } else {
                scanner.nextLine();
                System.out.print("Press 0 to go back: ");
            }
        }
    }

    // --- MODIFIED REGISTRATION METHOD ---
    private static void doRegister() {
        System.out.println("\n--- REGISTRATION ---");
        
        // 1. Loop for Name
        String u = "";
        while(true) {
            System.out.print("Name: "); 
            u = scanner.nextLine();
            if(!hospital.isValidName(u)) {
                System.out.println(RED + "Error: Name must contain only letters and spaces. No numbers or special characters allowed." + RESET);
            } else if(hospital.isUserExists(u)) {
                System.out.println(RED + "Error: Username already exists. Try another." + RESET);
            } else {
                break; // Valid name, exit loop
            }
        }

        // 2. Loop for Password
        String p = "";
        while(true) {
            System.out.print("Password (min 6): "); 
            p = scanner.nextLine();
            if(!hospital.isValidPassword(p)) {
                System.out.println(RED + "Error: Password must be at least 6 characters." + RESET);
            } else {
                break; // Valid password, exit loop
            }
        }

        // 3. Loop for Mobile
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

        // 4. Loop for Age
        int a = 0; 
        while(true) {
            System.out.print("Age: "); 
            if(scanner.hasNextInt()) {
                a = scanner.nextInt();
                scanner.nextLine(); // consume the leftover newline
                if(a > 0 && a < 120) {
                    break; // Valid age
                } else {
                    System.out.println(RED + "Error: Please enter a realistic age." + RESET);
                }
            } else { 
                System.out.println(RED + "Error: Invalid Age (Numbers only)." + RESET); 
                scanner.next(); // clear invalid input
            }
        }

        // 5. Loop for Blood Group
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

        // All data is valid now, attempt registration
        String res = hospital.register(u, p, m, a, bg);
        if(res.equals("Success")) { 
            System.out.println(GREEN + "Registration Successful!" + RESET); 
            System.out.print("Press 0 to go back: ");
            while(true) {
                if(scanner.hasNextInt()) {
                    int back = scanner.nextInt();
                    scanner.nextLine();
                    if(back == 0) {
                        break;
                    } else {
                        System.out.print("Press 0 to go back: ");
                    }
                } else {
                    scanner.nextLine();
                    System.out.print("Press 0 to go back: ");
                }
            }
        } else { 
            System.out.println(res); 
        }
    }

    private static Patient doLogin() {
        System.out.println("\n--- LOGIN ---");
        int attempts = 3;
        while(attempts > 0) {
            System.out.println("Attempts remaining: " + attempts);
            
            System.out.print("Name: "); String u = scanner.nextLine();
            System.out.print("Pass: "); String p = scanner.nextLine();
            System.out.print("Mobile: "); String m = scanner.nextLine();
            
            Patient pat = hospital.login(u, p, m);
            if(pat != null) { 
                System.out.println(GREEN + "Welcome " + pat.getUsername() + RESET); 
                return pat; 
            } else { 
                System.out.println(RED + "Error: Incorrect Name, Password, or Mobile." + RESET); 
                attempts--; 
            }
        }
        System.out.println("\nLogin Failed. Max attempts reached.");
        System.out.print("Forgot Password? (1. Yes / 2. No): ");
        if(scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            if(choice == 1) {
                doForgotPassword();
                // After password reset, return to login
                return doLogin();
            }
        } else {
            scanner.nextLine(); // consume invalid input
        }
        return null;
    }

    private static void doForgotPassword() {
        System.out.println("\n--- FORGOT PASSWORD ---");
        
        // Step 1: Get name and mobile
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Mobile Number: ");
        String mobile = scanner.nextLine();
        
        // Verify name and mobile exist
        Patient p = hospital.findPatientByNameMobile(name, mobile);
        if(p == null) {
            System.out.println("Error: Name and Mobile combination not found.");
            return;
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
                        break; // Break inner loop to start new OTP verification
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
                    scanner.nextLine(); // consume invalid input
                    otpAttempts--;
                }
            }
            
            // If OTP verification failed after 3 attempts
            if(!otpVerified && otpAttempts == 0) {
                System.out.println(RED + "\nOTP verification failed. Maximum attempts reached." + RESET);
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
                        // Continue loop to verify new OTP
                    } else {
                        // User chose to return to login
                        System.out.println("Returning to login menu...");
                        shouldContinue = false;
                    }
                } else {
                    scanner.nextLine(); // consume invalid input
                    System.out.println("Invalid input. Returning to login menu...");
                    shouldContinue = false;
                }
            }
        }
        
        // Step 4: If OTP verified, create new password
        if(otpVerified) {
            System.out.println("\n--- CREATE NEW PASSWORD ---");
            String newPassword = "";
            while(true) {
                System.out.print("Enter New Password (min 6 characters): ");
                newPassword = scanner.nextLine();
                if(hospital.isValidPassword(newPassword)) {
                    break;
                } else {
                    System.out.println(RED + "Error: Password must be at least 6 characters." + RESET);
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
            System.out.print("Enter Medicine ID (0 to exit): ");
            if(scanner.hasNextInt()) {
                int mId = scanner.nextInt();
                if(mId == 0) shopping = false;
                else {
                    System.out.print("Quantity: ");
                    System.out.println(hospital.buyMedicine(p, mId, scanner.nextInt()));
                }
            } else { scanner.next(); shopping = false; }
        }
    }
}