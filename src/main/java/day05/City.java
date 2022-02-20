package day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class City {
    private Map<String, List<Integer>> streets = new LinkedHashMap<>();

    public static void main(String[] args) {
        City city = new City();
        city.readFromFile(Path.of("src/main/java/day05/streets.txt"));

        city.streets.entrySet().stream()
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }

    public void readFromFile(Path path) {
        try {
            BufferedReader br = Files.newBufferedReader(path);
            String line;
            while ((line = br.readLine()) != null ) {
                String[] temp = line.split(" ");
                if (!streets.containsKey(temp[0])) {
                    streets.put(temp[0], new ArrayList<>());
                    if (Integer.parseInt(temp[1]) == 0) {
                        streets.get(streets).add(2);
                    } else {
                        streets.get(streets).add(1);
                    }
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Error!");
        }
    }

    private void addNewNumber(String street, int number) {
        int maxNumber = streets.get(street).stream()
                .mapToInt(i -> i).filter(i -> i % 2 == number).max().getAsInt();
        streets.get(street).add(maxNumber + 2);
    }

    public Map<String, List<Integer>> getStreets() {
        return streets;
    }
}