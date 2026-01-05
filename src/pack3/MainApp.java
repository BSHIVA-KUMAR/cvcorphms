package pack3;
import pack2.HospitalAdmin;
import pack1.Patient;
import java.util.Scanner;

public class MainApp {
    private static HospitalAdmin hospital = new HospitalAdmin();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean systemRunning = true;
        Patient currentUser = null;

        while (systemRunning) {
            if (currentUser == null) {
                printHeader("MEDICARE HOSPITAL SYSTEM");
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
        System.out.println("\n##################################");
        System.out.println("   " + title);
        System.out.println("##################################");
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
                System.out.println("Error: Name must contain only letters and spaces. No numbers or special characters allowed.");
            } else if(hospital.isUserExists(u)) {
                System.out.println("Error: Username already exists. Try another.");
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
                System.out.println("Error: Password must be at least 6 characters.");
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
                System.out.println("Error: Invalid Mobile Format (Must start with 9,8,7,6 and be 10 digits).");
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
                    System.out.println("Error: Please enter a realistic age.");
                }
            } else { 
                System.out.println("Error: Invalid Age (Numbers only)."); 
                scanner.next(); // clear invalid input
            }
        }

        // 5. Loop for Blood Group
        String bg = "";
        while(true) {
            System.out.print("Blood Group (e.g., A+, O-): "); 
            bg = scanner.nextLine();
            if(!hospital.isValidBloodGroup(bg)) {
                System.out.println("Error: Invalid Blood Group (Use A+, A-, B+, B-, O+, O-, AB+, AB-).");
            } else {
                break; // Valid blood group
            }
        }

        // All data is valid now, attempt registration
        String res = hospital.register(u, p, m, a, bg);
        if(res.equals("Success")) { 
            System.out.println("Registration Successful!"); 
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
                System.out.println("Welcome " + pat.getUsername()); 
                return pat; 
            } else { 
                System.out.println("Error: Incorrect Name, Password, or Mobile."); 
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
        
        // Step 3: Verify OTP with 3 attempts
        int otpAttempts = 3;
        boolean otpVerified = false;
        
        while(otpAttempts > 0 && !otpVerified) {
            System.out.println("\nOTP Attempts remaining: " + otpAttempts);
            System.out.print("Enter OTP (4 digits): ");
            
            if(scanner.hasNextInt()) {
                int enteredOTP = scanner.nextInt();
                scanner.nextLine(); // consume newline
                
                if(enteredOTP == otp) {
                    otpVerified = true;
                    System.out.println("OTP Verified Successfully!");
                } else {
                    System.out.println("Invalid OTP. Please try again.");
                    otpAttempts--;
                }
            } else {
                System.out.println("Invalid input. Please enter a 4-digit number.");
                scanner.next(); // consume invalid input
                otpAttempts--;
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
                    System.out.println("Error: Password must be at least 6 characters.");
                }
            }
            
            // Update password
            String result = hospital.resetPassword(name, mobile, newPassword);
            if(result.contains("Success")) {
                System.out.println("Password reset successful! You can now login with your new password.");
            } else {
                System.out.println(result);
            }
        } else {
            System.out.println("\nOTP verification failed. Maximum attempts reached.");
            System.out.println("Password reset cancelled.");
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