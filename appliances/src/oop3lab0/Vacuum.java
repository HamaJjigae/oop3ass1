package oop3lab0;

public class Vacuum extends Appliance {
    private String grade;
    private int batteryVoltage;

    public int getBatteryVoltage()
    {
        return batteryVoltage;
    }

    public Vacuum(int id, String brand, int quantity, int wattage, String colour, float price, String grade, int batteryVoltage)
    {
        super(id, brand, quantity, wattage, colour, price);
        this.grade = grade;
        this.batteryVoltage = batteryVoltage;
    }

    @Override
    public String toString()
    {
        String batteryText;
        switch (batteryVoltage)
        {
            case 18:
                batteryText = "Low";
                break;
            default:
                batteryText = "High";
                break;
        }
        return String.format("%s\nGrade: %s\nBattery Voltage: %s", super.toString(), grade, batteryText);
    }
    
    @Override
    public String formatForFile() 
    {
        return String.format("%s%s;%s;", super.formatForFile(), grade, batteryVoltage);
    }
}
