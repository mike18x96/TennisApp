package pl.sda.hibernate.CLI;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.dao.CoachDao;
import pl.sda.hibernate.dao.HibernateCoachDao;
import pl.sda.hibernate.entity.Address;
import pl.sda.hibernate.entity.Coach;

import java.util.List;
import java.util.Scanner;


public class CoachCLI {
    private static SessionFactory sessionFactory;
    private static CoachDao coachDao;

    public CoachCLI() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Coach.class)
                    .buildSessionFactory();
            coachDao = new HibernateCoachDao(sessionFactory);
            System.out.println("\n\n--------------------->\n" +
                    "Hibernate Session Factory Created");
        }
        coachMenu();
    }


    private static void coachMenu() {

        System.out.println();
        String base = "Podaj, co chcesz zrobić: " +
                "\n1 - Dodać trenera" +
                "\n2 - Zmienić dane trenera" +
                "\n3 - Wyświetlić listę trenerów" +
                "\n4 - Usunąć trenera" +
                "\n5 - Cofnij";

        System.out.println(base);
        Scanner in = new Scanner(System.in);

        boolean a = true;
        do {
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {

                case 1:
                    createCoach();
                    System.out.println(base);
                    break;
                case 2:
                    updateCoach();
                    System.out.println(base);
                    break;
                case 3:
                    printListOfCoaches();
                    System.out.println(base);
                    break;
                case 4:
                    deleteCoach();
                    System.out.println(base);
                    break;
                case 5:
                    a = false;
                    break;
            }
        }
        while (a);
        System.out.println("End");
    }

    private static void deleteCoach() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id trenera, którego dane chcesz usunąć: ");
        long idOfCoachToBeDeleted = scanner.nextInt();
        Coach coach = coachDao.findById(idOfCoachToBeDeleted);
        coachDao.delete(coach);
    }

    private static void printListOfCoaches() {
        System.out.println("Lista trenerów");
        List<Coach> coaches = coachDao.getAll();
        for (Coach coach : coaches) {
            System.out.println(coach);
        }
    }

    private static void updateCoach() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id trenera, którego dane chcesz zmienić: ");
        long idOfCoachToBeUpdated = scanner.nextInt();
        scanner.nextLine();
        Coach coach = coachDao.findById(idOfCoachToBeUpdated);

        System.out.println("Podaj zmienione dane\nPodaj imię: ");
        coach.setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko: ");
        coach.setLastName(scanner.nextLine());
        System.out.println("Podaj adres e-mail: ");
        coach.setEmail(scanner.nextLine());
        System.out.println("Adres zamieszkania");
        Address address = new Address();
        System.out.println("Podaj nazwę ulicy: ");
        address.setStreet(scanner.nextLine());
        System.out.println("Podaj numer budynku: ");
        address.setStreet(scanner.nextLine());
        coach.setAddress(address);
        address.setStreetNo(scanner.nextLine());
        System.out.println("Podaj numer mieszkania: ");
        address.setFlatNo(scanner.nextLine());
        System.out.println("Podaj kod pocztowy: ");
        address.setZipCode(scanner.nextLine());
        System.out.println("Podaj miejscowość: ");
        address.setCity(scanner.nextLine());

        coachDao.update(coach);
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
        System.out.println("Adres zamieszkania\n");
        Address address = new Address();
        System.out.println("Podaj nazwę ulicy: ");
        address.setStreet(scanner.nextLine());
        System.out.println("Podaj numer budynku: ");
        address.setStreetNo(scanner.nextLine());
        System.out.println("Podaj numer mieszkania: ");
        address.setFlatNo(scanner.nextLine());
        System.out.println("Podaj kod pocztowy: ");
        address.setZipCode(scanner.nextLine());
        System.out.println("Podaj miejscowość: ");
        address.setCity(scanner.nextLine());

        final Coach coach = new Coach(null, firstName, lastName, email, address);
        coachDao.create(coach);
    }
}
