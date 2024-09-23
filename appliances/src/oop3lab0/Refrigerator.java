package oop3lab0;

public class Refrigerator extends Appliance {
    private int doors;
    private int height;
    private int width;

    public int getDoors()
    {
        return doors;
    }
    public Refrigerator(int id, String brand, int quantity, int wattage, String colour, float price, int doors, int height, int width)
    {
        super(id, brand, quantity, wattage, colour, price);
        this.doors = doors;
        this.height = height;
        this.width = width;
    }
    @Override
    public String toString()
    {
        String doorText;
        switch (doors) 
        {
            case 2:
                doorText = "Two Doors";
                break;
            case 3:
                doorText = "Three Doors";
                break;
            default:
                doorText = "Four Doors";
                break;
        }
        return String.format("%s\nNumber of Doors: %s\nHeight: %s\nWidth: %s", super.toString(), doorText, height, width);
    }
    @Override
    public String formatForFile() 
    {
        return String.format("%s%s;%s;%s;", super.formatForFile(), doors, height, width);
    }
}