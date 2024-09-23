package oop3lab0;

public class Microwave extends Appliance{
    private float capacity;
    private String roomType;

    public String getRoomType()
    {
        return roomType;
    }

    public Microwave(int id, String brand, int quantity, int wattage, String colour, float price, float capacity, String roomType)
    {
        super(id, brand, quantity, wattage, colour, price);
        this.capacity = capacity;
        this.roomType = roomType;
    }

    @Override
    public String toString()
    {
        String roomText;
        switch (roomType) {
            case "W":
                roomText = "Work Site";
                break;
            default:
                roomText = "Kitchen";
                break;
        }
        return String.format("%s\nCapacity: %s\nRoom Type: %s", super.toString(), capacity, roomText);
    }

    @Override
    public String formatForFile()
    {
        return String.format("%s%s;%s;", super.formatForFile(), capacity, roomType);
    }
}