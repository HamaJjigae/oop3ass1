package oop3lab0;

public class Dishwasher extends Appliance {
    private String feature;
    private String soundRating;

    public String getSoundRating()
    {
        return soundRating;
    }

    public Dishwasher(int id, String brand, int quantity, int wattage, String colour, float price, String feature, String soundRating)
    {
        super(id, brand, quantity, wattage, colour, price);
        this.feature = feature;
        this.soundRating = soundRating;
    }

    @Override
    public String toString()
    {
        String soundText;
        switch (soundRating)
        {
            case "Qt":
                soundText = "Quietest";
                break;
            case "Qr":
                soundText = "Quieter";
                break;
            case "Qu":
                soundText = "Quiet";
                break;
            default:
                soundText = "Moderate";
                break;
        }
        return String.format("%s\nFeature: %s\nSoundRating: %s", super.toString(), feature, soundText);
    }

    @Override
    public String formatForFile()
    {
        return String.format("%s%s;%s;", super.formatForFile(), feature, soundRating);
    }
}
