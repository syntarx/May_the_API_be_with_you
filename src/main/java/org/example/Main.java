package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        SwapiClient swapiClient = new SwapiClient();
        Scanner scanner = new Scanner(System.in);

        try {
            List<Person> people = swapiClient.getPeople();

            // Heimatwelt für jede Person auflösen
            for (Person person : people) {
                Planet homeworld = swapiClient.getPlanet(person.getHomeworld());
                if (homeworld != null) {
                    person.setHomeworldName(homeworld.getName());
                }
            }

            // Mögliche Filter anzeigen
            System.out.println("Mögliche Filter: ");
            System.out.println("1. Heimatwelt");
            System.out.println("2. Vorname");
            System.out.println("3. Nachname");
            System.out.println("4. Mindesthöhe");
            System.out.println("5. Maximale Höhe");
            System.out.println("6. Geschlecht");
            System.out.println("7. Haarfarbe");

            // Filter wählen
            System.out.print("Geben Sie die Nummer des Filters ein, den Sie verwenden möchten (Trennen Sie mehrere Filter mit einem Komma): ");
            String filterChoice = scanner.nextLine();
            String[] filters = filterChoice.split(",");

            // Filterkriterien eingeben
            String filterHomeworldName = null;
            String filterFirstName = null;
            String filterLastName = null;
            Integer minHeight = null;
            Integer maxHeight = null;
            String filterGender = null;
            String filterHairColor = null;

            for (String filter : filters) {
                switch (filter.trim()) {
                    case "1":
                        System.out.print("Geben Sie den Namen der Heimatwelt ein: ");
                        filterHomeworldName = scanner.nextLine();
                        break;
                    case "2":
                        System.out.print("Geben Sie den Vornamen ein: ");
                        filterFirstName = scanner.nextLine();
                        break;
                    case "3":
                        System.out.print("Geben Sie den Nachnamen ein: ");
                        filterLastName = scanner.nextLine();
                        break;
                    case "4":
                        System.out.print("Geben Sie die Mindesthöhe ein: ");
                        minHeight = Integer.parseInt(scanner.nextLine());
                        break;
                    case "5":
                        System.out.print("Geben Sie die maximale Höhe ein: ");
                        maxHeight = Integer.parseInt(scanner.nextLine());
                        break;
                    case "6":
                        System.out.print("Geben Sie das Geschlecht ein: ");
                        filterGender = scanner.nextLine();
                        break;
                    case "7":
                        System.out.print("Geben Sie die Haarfarbe ein: ");
                        filterHairColor = scanner.nextLine();
                        break;
                    default:
                        System.out.println("Ungültige Auswahl. Bitte versuchen Sie es erneut.");
                        break;
                }
            }

            // Personen filtern
            String finalFilterHomeworldName = filterHomeworldName;
            String finalFilterFirstName = filterFirstName;
            String finalFilterLastName = filterLastName;
            Integer finalMinHeight = minHeight;
            Integer finalMaxHeight = maxHeight;
            String finalFilterGender = filterGender;
            String finalFilterHairColor = filterHairColor;
            List<Person> filteredPeople = people.stream()
                    .filter(person -> finalFilterHomeworldName == null || finalFilterHomeworldName.equals(person.getHomeworldName()))
                    .filter(person -> finalFilterFirstName == null || finalFilterFirstName.equals(person.getFirstName()))
                    .filter(person -> finalFilterLastName == null || finalFilterLastName.equals(person.getLastName()))
                    .filter(person -> finalMinHeight == null || person.getHeight() >= finalMinHeight)
                    .filter(person -> finalMaxHeight == null || person.getHeight() <= finalMaxHeight)
                    .filter(person -> finalFilterGender == null || finalFilterGender.equals(person.getGender()))
                    .filter(person -> finalFilterHairColor == null || finalFilterHairColor.equals(person.getHairColor()))
                    .collect(Collectors.toList());

            // Gefilterte Personen ausgeben
            filteredPeople.forEach(person ->
                    System.out.println("Name: " + person.getName() +
                            ", Heimatwelt: " + person.getHomeworldName() +
                            ", Höhe: " + person.getHeight() +
                            ", Geschlecht: " + person.getGender() +
                            ", Haarfarbe: " + person.getHairColor()));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
