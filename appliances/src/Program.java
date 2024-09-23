import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import oop3lab0.*;

public class Program {

    static List<Appliance> applianceList = new ArrayList<>();
    static String filePath = "appliances/src/appliances.txt";

    public static void main(String[] args) {
        applianceList = loadApplianceData();
        mainMenu(applianceList, filePath);
    }

    static List<Appliance> loadApplianceData() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            int lineNumber = 0;
            for (String line : lines) {
                lineNumber++;

                if (!line.trim().isEmpty()) {
                    String[] values = line.split(";");
                    if (values.length <= 7) {
                        System.out.println("Error at line " + lineNumber + ": Invalid Format - Insufficient Lines");
                        continue;
                    }

                    try {
                        char type = values[0].charAt(0);
                        int id = Integer.parseInt(values[0]);
                        String brand = values[1];
                        int quantity = Integer.parseInt(values[2]);
                        int wattage = Integer.parseInt(values[3]);
                        String colour = values[4];
                        float price = Float.parseFloat(values[5]);

                        Appliance appliance;
                        if (type == '1') {
                            int doors = Integer.parseInt(values[6]);
                            int height = Integer.parseInt(values[7]);
                            int width = Integer.parseInt(values[8]);
                            appliance = new Refrigerator(id, brand, quantity, wattage, colour, price, doors, height, width);
                        } else if (type == '2' && (values[7].equals("18") || values[7].equals("24"))) {
                            String grade = values[6];
                            int batteryVoltage = Integer.parseInt(values[7]);
                            appliance = new Vacuum(id, brand, quantity, wattage, colour, price, grade, batteryVoltage);
                        } else if (type == '3' && (values[7].equals("W") || values[7].equals("K"))) {
                            float capacity = Float.parseFloat(values[6]);
                            String roomType = values[7];
                            appliance = new Microwave(id, brand, quantity, wattage, colour, price, capacity, roomType);
                        } else if ((type == '4' || type == '5') && (values[7].equals("Qt") || values[7].equals("Qr") || values[7].equals("Qu") || values[7].equals("M"))) {
                            String feature = values[6];
                            String soundRating = values[7];
                            appliance = new Dishwasher(id, brand, quantity, wattage, colour, price, feature, soundRating);
                        } else {
                            System.out.println("Error at line " + lineNumber + ": Invalid Format - Unexpected or Insufficient Information");
                            continue;
                        }

                        applianceList.add(appliance);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        System.out.println("Error at line " + lineNumber + ": Invalid Format - " + e.getMessage());
                        continue;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
        return applianceList;
    }

    static void mainMenu(List<Appliance> applianceList, String filePath) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Welcome to Modern Appliances!");
            System.out.println("1 - Check out appliance");
            System.out.println("2 - Find appliances by brand");
            System.out.println("3 - Display appliances by type");
            System.out.println("4 - Produce random appliance list");
            System.out.println("5 - Save & exit");
            System.out.print("Enter option: ");
            String optionSelect = scanner.nextLine();

            if (optionSelect.equals("1")) {
                System.out.print("\nEnter the number of an appliance: ");
                String input = scanner.nextLine().trim();
                try {
                    int applianceQuery = Integer.parseInt(input);
                    Appliance selectedAppliance = applianceList.stream()
                            .filter(appliance -> appliance.getId() == applianceQuery)
                            .findFirst()
                            .orElse(null);

                    if (selectedAppliance == null) {
                        System.out.println("No appliances found with that item number.");
                    } else if (selectedAppliance.isAvailable()) {
                        selectedAppliance.checkout();
                        System.out.println("Appliance \"" + selectedAppliance.getId() + "\" has been checked out.");
                    } else {
                        System.out.println("The appliance is not available to be checked out.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            } else if (optionSelect.equals("2")) {
                System.out.print("Enter brand to search for: ");
                String brandQuery = scanner.nextLine().toLowerCase().trim();
                List<Appliance> filteredAppliances = applianceList.stream()
                        .filter(appliance -> appliance.getBrand() != null && appliance.getBrand().toLowerCase().equals(brandQuery))
                        .collect(Collectors.toList());

                filteredAppliances.forEach(appliance ->
                {
                    System.out.println();
                    System.out.println(appliance);
                });
            } else if (optionSelect.equals("3")) {
                System.out.println();
                System.out.println("1 - Refrigerators");
                System.out.println("2 - Vacuums");
                System.out.println("3 - Microwaves");
                System.out.println("4 - Dishwashers");
                System.out.print("Enter type of appliance: ");
                String applianceQuery = scanner.nextLine();
            
                if (applianceQuery.equals("1")) {
                    System.out.print("Enter number of doors (2, 3, or 4): ");
                    int doorQuery = Integer.parseInt(scanner.nextLine().trim());
                    if (doorQuery == 2 || doorQuery == 3 || doorQuery == 4) {
                        List<Refrigerator> filteredFridge = applianceList.stream()
                                .filter(Refrigerator.class::isInstance)
                                .map(Refrigerator.class::cast)
                                .filter(fridge -> fridge.getDoors() == doorQuery)
                                .collect(Collectors.toList());
            
                        filteredFridge.forEach(fridge -> 
                        {
                            System.out.println();
                            System.out.println(fridge);
                        });
                    } else {
                        System.out.println("Invalid number of doors.");
                    }
                } else if (applianceQuery.equals("2")) {
                    System.out.print("Enter battery voltage (18 or 24): ");
                    int voltQuery = Integer.parseInt(scanner.nextLine().trim());
                    if (voltQuery == 18 || voltQuery == 24) {
                        List<Vacuum> filteredVacuums = applianceList.stream()
                                .filter(Vacuum.class::isInstance)
                                .map(Vacuum.class::cast)
                                .filter(vac -> vac.getBatteryVoltage() == voltQuery)
                                .collect(Collectors.toList());
            
                        filteredVacuums.forEach(vacuum ->
                        {
                            System.out.println();
                            System.out.println(vacuum);
                        });
                    } else {
                        System.out.println("Invalid battery voltage.");
                    }
                } else if (applianceQuery.equals("3")) {
                    System.out.print("Enter room type (K or W): ");
                    String roomQuery = scanner.nextLine().toUpperCase().trim();
                    if (roomQuery.equals("K") || roomQuery.equals("W")) {
                        List<Microwave> filteredMicrowaves = applianceList.stream()
                                .filter(Microwave.class::isInstance)
                                .map(Microwave.class::cast)
                                .filter(micro -> micro.getRoomType().equals(roomQuery))
                                .collect(Collectors.toList());
            
                        filteredMicrowaves.forEach(micro ->
                        {
                            System.out.println();
                            System.out.println(micro);
                        });
                    } else {
                        System.out.println("Invalid input.");
                    }
                } else if (applianceQuery.equals("4")) {
                    System.out.print("Enter sound rating (Qt, Qr, Qu, M): ");
                    String soundQuery = scanner.nextLine().trim().toLowerCase();
                    if (soundQuery.equals("qt") || soundQuery.equals("qr") || 
                        soundQuery.equals("qu") || soundQuery.equals("m")) {
                        List<Dishwasher> filteredDishwashers = applianceList.stream()
                                .filter(Dishwasher.class::isInstance)
                                .map(Dishwasher.class::cast)
                                .filter(dw -> dw.getSoundRating() != null && 
                                    dw.getSoundRating().toLowerCase().equals(soundQuery))
                                .collect(Collectors.toList());
            
                        filteredDishwashers.forEach(dishwasher ->
                        {
                            System.out.println();
                            System.out.println(dishwasher);
                        });
                    } else {
                        System.out.println("Invalid input.");
                    }
                } else {
                    System.out.println("Invalid input.");
                }
            } else if (optionSelect.equals("4")) {
                Random random = new Random();
                Set<Integer> usedIndices = new HashSet<>();
                System.out.print("Enter number of appliances: ");
                try {
                    int randomQuery = Integer.parseInt(scanner.nextLine().trim());
                    for (int i = 0; i < randomQuery; i++) {
                        int randomIndex;
                        do {
                            randomIndex = random.nextInt(applianceList.size());
                        } while (usedIndices.contains(randomIndex));

                        usedIndices.add(randomIndex);
                        System.out.println();
                        System.out.println(applianceList.get(randomIndex));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            } else if (optionSelect.equals("5")) {
                List<String> formattedList = applianceList.stream()
                        .map(Appliance::formatForFile)
                        .collect(Collectors.toList());

                try {
                    Files.write(Paths.get(filePath), formattedList);
                } catch (IOException e) {
                    System.out.println("Error saving file: " + e.getMessage());
                }
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
        scanner.close();
    }
}
