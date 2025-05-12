
public class ExampleRun{
    public static void main(String[] args) {
        // Create instances of WaterBill
        WaterBill waterBill1 = new WaterBill("Water", 137.82, "2025-01-01");
        WaterBill waterBill2 = new WaterBill("Water", 123.34, "2025-02-01");

        // Display water bill information
        waterBill1.displayUtility();
        waterBill2.displayUtility();

        // Perform bill analysis for water bills
        waterBill1.billAnalysis(waterBill1, waterBill2);

        // Check for leaks
        waterBill1.checkForLeaks(waterBill1, waterBill2);

        // Create instances of ElectricityBill
        ElectricityBill electricityBill1 = new ElectricityBill("Electricity", 100.00, "2025-01-01");
        ElectricityBill electricityBill2 = new ElectricityBill("Electricity", 150.00, "2025-02-01");

        // Display electricity bill information
        electricityBill1.displayUtility();
        electricityBill2.displayUtility();

        // Perform bill analysis for electricity bills
        electricityBill1.billAnalysis(electricityBill1, electricityBill2);

        // Check for electrical issues
        electricityBill1.checkForElectricalIssues(electricityBill1, electricityBill2);

        // Create instances of GasBill
        GasBill gasBill1 = new GasBill("Gas", 50.00, "2025-01-01");
        GasBill gasBill2 = new GasBill("Gas", 75.00, "2025-02-01");

        // Display gas bill information
        gasBill1.displayUtility();
        gasBill2.displayUtility();

        // Perform bill analysis for gas bills
        gasBill1.billAnalysis(gasBill1, gasBill2);

        // Check for gas leaks
        gasBill1.checkForGasLeaks(gasBill1, gasBill2);
    }
}
