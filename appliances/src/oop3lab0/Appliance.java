package oop3lab0;

public abstract class Appliance {
    private int id;
    private String brand;
    private int quantity;
    private int wattage;
    private String colour;
    private float price;

    public int getId()
    {
        return id;
    }

    public String getBrand()
    {
        return brand;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getWattage()
    {
        return wattage;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public Appliance(int id, String brand, int quantity, int wattage, String colour, float price)
    {
        this.id = id;
        this.brand = brand;
        this.quantity = quantity;
        this.wattage = wattage;
        this.colour = colour;
        this.price = price;
    }

    public boolean isAvailable()
    {
        return quantity > 0;
    }

    public void checkout()
    {
        quantity = quantity - 1;
    }

    @Override
    public String toString()
    {
        return String.format("ItemNumber: %d\nBrand: %s\nQuantity: %d\nWattage: %d\nColour: %s\nPrice: %.2f",
                id, brand, quantity, wattage, colour, price);
    }

    public String formatForFile()
    {
        return String.format("%d;%s;%d;%d;%s;%.2f;", id, brand, quantity, wattage, colour, price);
    }
}
