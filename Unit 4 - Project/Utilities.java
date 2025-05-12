import java.text.DecimalFormat;

// Create base class for all utilities
// This class will be used to store all the utility methods that will be used in the project
public class Utilities {
    // Create Utility class
    private String utilityName;
    private String billDate;
    private double billCost;
    //private double utilityUsage;

// Decimal format for billCost
    final DecimalFormat df = new DecimalFormat("$000.00");

    public String getUtilityName() {
        return utilityName;
    }

    public void setUtilityName(String utilityName) {
        this.utilityName = utilityName;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public double getBillCost() {
        return billCost;
    }

    public void setBillCost(double billCost) {
        this.billCost = billCost;
    }

    /* public double getUtilityUsage() {
        return utilityUsage;
    }

    public void setUtilityUsage(double utilityUsage) {
        this.utilityUsage = utilityUsage;
    } */

    // Create a constructor for the Utility class
    public Utilities(String utilityName, double billCost, String billDate) {
        this.utilityName = utilityName;
        this.billCost = billCost;
        this.billDate = billDate;
        //this.utilityUsage = utilityUsage;
    }

    /* Learned i can input directly in the main() file instead of creating separate methods
    // Create a method to input the utility name
    public void inputUtilityName() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the utility: ");
        utilityName = input.nextLine();
    }

    // Create a method to input the bill cost
    public void inputBillCost() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the cost of the bill: ");
        billCost = input.nextDouble();
    }

    // Create a method to input the bill date
    public void inputBillDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the date of the bill: ");
        billDate = input.nextLine();
    }
    */

    // Create a method to display the utility information
    public void displayUtility() {
        System.out.println("Utility: " + utilityName);
        System.out.println("Bill Date: " + billDate);
        System.out.println("Monthly Bill: " + df.format(billCost));
        System.out.println();
    }
    
    // Create Method to calculate the difference between two bill cycles
    // Display the percentage difference, price increase or decrease, and dates of the bills
    public void billAnalysis(Utilities utility1, Utilities utility2) {
        double billDifference = utility2.billCost - utility1.billCost;
        double percentDiff = (billDifference / utility1.billCost) * 100;
        if (billDifference > 0) {
            System.out.println("From " + utility1.billDate + " to " + utility2.billDate +
                    " the " + utilityName + " bill has increased: " + df.format(billDifference) + " (" + percentDiff + "%)\n");
        } else if (billDifference < 0) {
            System.out.println("From " + utility1.billDate + " to " + utility2.billDate +
                    " the " + utilityName + " bill has decreased: " + df.format(Math.abs(billDifference)) + " (" + Math.abs(percentDiff) + "%)\n");
        } else {
            System.out.println("From " + utility1.billDate + " to " + utility2.billDate +
                    " the " + utilityName + " bill has stayed the same: " + df.format(billDifference) + " (" + percentDiff + "%)\n");
        }
    }
}

// Subclass for WaterBill
class WaterBill extends Utilities {
    // Create a constructor for the WaterBill class
    public WaterBill(String utilityName, double billCost, String billDate) {
        super(utilityName, billCost, billDate);
    }

    // Create a method to check for potential leaks with significant bill increase or decrease
    public void checkForLeaks(Utilities utility1, Utilities utility2) {
        double billDifference = utility2.getBillCost() - utility1.getBillCost();
        if (billDifference > 50) {
            System.out.println("There may be a leak in the water system.\n");
        } else if (billDifference < -50) {
            System.out.println("Water Usage has gone down.\n");
        } else {
            System.out.println("There is no significant change in the water bill.\n");
        }
    }
}

class ElectricityBill extends Utilities {
    // Create a constructor for the ElectricityBill class
    public ElectricityBill(String utilityName, double billCost, String billDate) {
        super(utilityName, billCost, billDate);
    }

    // Create a method to check for potential electrical issues with significant bill increase or decrease
    public void checkForElectricalIssues(Utilities utility1, Utilities utility2) {
        double billDifference = utility2.getBillCost() - utility1.getBillCost();
        if (billDifference > 50) {
            System.out.println("There may be an electrical issue.\n");
        } else if (billDifference < -50) {
            System.out.println("Electrical Usage has gone down.\n");
        } else {
            System.out.println("There is no significant change in the electrical bill.\n");
        }
    }
}

class GasBill extends Utilities {
    // Create a constructor for the GasBill class
    public GasBill(String utilityName, double billCost, String billDate) {
        super(utilityName, billCost, billDate);
    }

    // Create a method to check for potential gas leaks with significant bill increase or decrease
    public void checkForGasLeaks(Utilities utility1, Utilities utility2) {
        double billDifference = utility2.getBillCost() - utility1.getBillCost();
        if (billDifference > 50) {
            System.out.println("There may be a gas leak.\n");
        } else if (billDifference < -50) {
            System.out.println("Gas Usage has gone down.\n");
        } else {
            System.out.println("There is no significant change in the gas bill.\n");
        }
    }
}