package pl.sda.hibernate.entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.dao.CoachDao;
import pl.sda.hibernate.entity.Address;
import pl.sda.hibernate.dao.HibernateCoachDao;
import pl.sda.hibernate.entity.Coach;

import java.util.Scanner;


public class CoachCLI {

    private static SessionFactory sessionFactory;
    private static CoachDao coachDao;

    public static void main(String[] args) {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Coach.class)
                .buildSessionFactory();
        coachDao = new HibernateCoachDao(sessionFactory);

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        coachMenu();
    }


    private static void coachMenu() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj, co chcesz zrobić: " +
                "\n1 - dodać trenera" +
                "\n2 - zmienić dane trenera" +
                "\n3 - wyświetlić listę trenerów" +
                "\n4 - usunąć trenera");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createCoach();
            case 2:
                updateCoach();
            case 3:
                printListOfCoaches();
            case 4:
                deleteCoach();
        }

    }

    private static void deleteCoach() {
        System.out.println("");
    }

    private static void printListOfCoaches() {
    }

    private static void updateCoach() {

    }

    private static void createCoach() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dane osobowe" +
                "\nPodaj imię: ");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.println("Podaj adres e-mail: ");
        String email = scanner.nextLine();
        System.out.println("Adres zamieszkania\n" +
                "Podaj ulicę: ");
        System.out.println("Podaj numer ulicy: ");
        Address address = new Address();
        address.setStreet(scanner.nextLine());
        System.out.println("Podaj numer mieszkania: ");
        address.setStreetNo(scanner.nextLine());
        System.out.println("Podaj kod pocztowy: ");
        address.setZipCode(scanner.nextLine());
        System.out.println("Podaj miejscowość: ");
        address.setCity(scanner.nextLine());

        final Coach coach = new Coach(null,firstName, lastName,email, new Address());
        coachDao.create(coach);
    }
}
