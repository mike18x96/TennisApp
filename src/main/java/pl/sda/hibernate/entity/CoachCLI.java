package pl.sda.hibernate.entity;

import java.util.Scanner;

public class CoachCLI {

    private static void coachMenu(){

        Coach coach;
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj, co chcesz zrobić: " +
                "\n1 - dodać trenera" +
                "\n2 - zmienić dane trenera" +
                "\n3 - wyświetlić listę trenerów" +
                "\n4 - usunąć trenera");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                addCoach();
            case 2:
                updateCoach();
            case 3:
                printListOfCoaches();
            case 4:
                deleteCoach();
        }

    }

    private static void deleteCoach() {
    }

    private static void printListOfCoaches() {
    }

    private static void updateCoach() {
    }

    private static void addCoach() {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię i nazwisko: ");
        String name = scanner.nextLine();
        System.out.println("Podaj adres e-mail: ");
        String email = scanner.nextLine();
        System.out.println("Podaj adres zamieszkania: ");
        String address = scanner.nextLine();
    }
}
