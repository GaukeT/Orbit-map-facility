package nl.gauket;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class OrbitMapper {
    private static int count;
    private static final String FILENAME = "Input6.txt";

    public static void main(String[] args) {
        URL path = OrbitMapper.class.getClassLoader().getResource(FILENAME);
        if (path != null)
            processFile(new File(path.getFile()));
    }

    private static void processFile(File filename) {
        try (Scanner scanner = new Scanner(filename)) {
            processAllLinesInFile(scanner);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void processAllLinesInFile(Scanner scanner) {
        Map<String, List<String>> orbits = new HashMap<>();

        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split("\\)");
            addPlanets(orbits, data[0], data[1]);
        }
        orbits.forEach((k, v) -> findPlanets(orbits, k));
        System.out.println(count);
    }

    private static void addPlanets(Map<String, List<String>> orbits, String planetA, String planetB) {
        orbits.putIfAbsent(planetA, new ArrayList<>());
        List<String> planets = orbits.get(planetA);
        planets.add(planetB);
    }

    private static void findPlanets(Map<String, List<String>> orbits, String planet) {
        List<String> planets = orbits.get(planet);
        if (planets != null) {
            count += planets.size();
            planets.forEach((k) -> findPlanets(orbits, k));
        }
    }
}
