import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MoneySaver {
    // create a file to save the utilities list and come back to later with
    private static final String FILE_NAME = "utilities.dat";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Utilities> utilities = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        // first major part of the code: a loop to give the user options with the program
        while (true) {
            System.out.println("Welcome! Would you like to:");
            System.out.println("1. Add to your Utility List");
            System.out.println("2. View your Utility List");
            System.out.println("3. Exit");
            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character
            //if the user wants to add to the list, this loop will run until they're done adding
            if (choice == 1) {
                while (true) {
                    String utilityName;
                    while (true) {
                        System.out.println("\nEnter the utility type (Water, Electricity, Gas) or \"done\" to finish adding:");
                        // Make case insensitive
                        utilityName = input.nextLine().toLowerCase();
                        if (utilityName.equals("done")) {
                            break;
                        }
                        if (utilityName.equals("water") || utilityName.equals("electricity") || utilityName.equals("gas")) {
                            break;
                        } else {
                            System.out.println("Invalid utility type. Please enter \"Water\", \"Electricity\", or \"Gas\".");
                        }
                    }
                    // not sure if this needs to go here but it's my safety break
                    if (utilityName.equals("done")) {
                        break;
                    }

                    // create a new while loop for bill date so typos don't affect the entire utility data
                    Date billDate;
                    while (true) {
                        System.out.println("\nEnter the date of the bill (MM/DD/YYYY):");
                        String billDateString = input.nextLine();
                        try {
                            billDate = dateFormat.parse(billDateString);
                            break;
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter the date in MM/DD/YYYY format.");
                        }
                    }
                    // same this for the price of the bill, use new while loop to prevent errors
                    double billCost;
                    while (true) {
                        System.out.println("\nEnter the cost of the bill: ");
                        try {
                            billCost = Double.parseDouble(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid cost format. Please enter a valid number. (000.00)");
                        }
                    }

                    //show completed loop inputs 
                    System.out.println("\nBill successfully added to Utility List.");

                    String formattedDate = dateFormat.format(billDate);

                    // Takes the inputs and makes them case insensitive to then adds to the arraylist
                    Utilities utility;
                    switch (utilityName) {
                        case "water":
                            utility = new WaterBill(utilityName, billCost, formattedDate);
                            break;
                        case "electricity":
                            utility = new ElectricityBill(utilityName, billCost, formattedDate);
                            break;
                        case "gas":
                            utility = new GasBill(utilityName, billCost, formattedDate);
                            break;
                        default:
                            System.out.println("Invalid utility type. Please enter Water, Electricity, or Gas.");
                            continue;
                    }
                    //add to list
                    utilities.add(utility);
                }
            } else if (choice == 2) {
                //catch error if there is nothing in the list
                if (utilities.isEmpty()) {
                    System.out.println("\nNo bills have been added to the collection.");
                    continue; // Return to the main menu
                }

                // Sort utilities by type/name and then by date from newest to oldest
                Collections.sort(utilities, (u1, u2) -> {
                    int nameCompare = u1.getUtilityName().compareToIgnoreCase(u2.getUtilityName());
                    if (nameCompare != 0) {
                        return nameCompare;
                    } else {
                        return u2.getBillDate().compareTo(u1.getBillDate());
                    }
                });

                // Display all utilities and inputs and add numericals to them so they can be later selected
                for (int i = 0; i < utilities.size(); i++) {
                    Utilities utility = utilities.get(i);
                    System.out.println((i + 1) + ". " + utility.getUtilityName() + " - "
                            + utility.getBillDate() + " - " + utility.getBillCost());
                }
                
                //nothing happens after displaying utilities so give user the option to now run the purpose of this program
                System.out.println("\nWould you like to analyze your bills? (y/n)");
                String charchoice = input.nextLine();
                if (charchoice.equalsIgnoreCase("y")) {
                    // Allow user to select two bills to compare
                    System.out.println("\n\t Analyze Utility Bills");
                    System.out.println("Enter the number of the first bill to compare:");
                    int firstBillIndex = input.nextInt() - 1;
                    System.out.println("Enter the number of the second bill to compare:");
                    int secondBillIndex = input.nextInt() - 1;
                    input.nextLine(); // Consume the newline character

                    if (firstBillIndex >= 0 && firstBillIndex < utilities.size() && secondBillIndex >= 0 && secondBillIndex < utilities.size()) {
                        Utilities utility1 = utilities.get(firstBillIndex);
                        Utilities utility2 = utilities.get(secondBillIndex);
                        if (utility1.getUtilityName().equalsIgnoreCase(utility2.getUtilityName())) {
                            utility1.billAnalysis(utility1, utility2);

                            // Call specific methods based on the type of utility using the subclasses
                            if (utility1 instanceof WaterBill && utility2 instanceof WaterBill) {
                                ((WaterBill) utility1).checkForLeaks((WaterBill) utility1, (WaterBill) utility2);
                            } else if (utility1 instanceof ElectricityBill && utility2 instanceof ElectricityBill) {
                                ((ElectricityBill) utility1).checkForElectricalIssues((ElectricityBill) utility1, (ElectricityBill) utility2);
                            } else if (utility1 instanceof GasBill && utility2 instanceof GasBill) {
                                ((GasBill) utility1).checkForGasLeaks((GasBill) utility1, (GasBill) utility2);
                            }
                        // safety for if two different bill types are selected
                        } else {
                            System.out.println("The selected bills are of different types and cannot be compared.");
                        }
                    } else {
                        System.out.println("Invalid selection. Please try again.");
                    }
                    //option to just view the list and leave
                } else if (charchoice.equalsIgnoreCase("n")) {
                    System.out.println("Type 'exit' to close program.");
                    String exitcode = input.nextLine();
                    if (exitcode.equalsIgnoreCase("exit")) {
                        break;
                    }
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        input.close();
    }

    // using I/O files to save the data and return to it if needbe, still a work in progress but my inital goal was met with analyzing the utilities
    private static void saveUtilities(List<Utilities> utilities) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(utilities);
            System.out.println("Utilities list saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving utilities list: " + e.getMessage());
        }
    }

    private static List<Utilities> loadUtilities() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Utilities>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous utilities list found or error loading utilities list: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

