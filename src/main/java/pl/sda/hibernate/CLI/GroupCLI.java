package pl.sda.hibernate.CLI;

import com.sun.xml.bind.v2.TODO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.dao.GroupDao;
import pl.sda.hibernate.dao.HibernateCoachDao;
import pl.sda.hibernate.dao.HibernateGroupDao;
import pl.sda.hibernate.entity.Address;
import pl.sda.hibernate.entity.Coach;
import pl.sda.hibernate.entity.Group;

import java.util.List;
import java.util.Scanner;


public class GroupCLI {

    private static SessionFactory sessionFactory;
    private static GroupDao groupDao;

    public GroupCLI() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Coach.class)
                    .buildSessionFactory();
            groupDao = new HibernateGroupDao(sessionFactory);
            System.out.println("\n\n--------------------->\n" +
                    "Hibernate Session Factory Created");
        }
        groupMenu();
    }

    private static void groupMenu() {

        System.out.println();
        System.out.println("Informacje o grupie: ");
        String base = "Podaj, co chcesz zrobić: " +
                "\n1 - Dodać grupe" +
                "\n2 - Zmienić dane trenera" +
                "\n3 - Wyświetlić listę grup" +
                "\n4 - Usunąć grupe" +
                "\n5 - Cofnij";

        System.out.println("START");
        Scanner in = new Scanner(System.in);
        System.out.println(base);
        boolean condDoWhile = true;

        do {
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {

                case 1:
                    createGroup();
                    System.out.println(base);
                    break;
                case 2:
                    updateGroup();
                    System.out.println(base);
                    break;
                case 3:
                    printListOfGroups();
                    System.out.println(base);
                    break;
                case 4:
                    deleteGroup();
                    System.out.println(base);
                    break;
                case 5:
                    condDoWhile = false;
                    break;
            }
        }
        while (condDoWhile);
        System.out.println("End");
    }

    private static void deleteGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id grupy, którą chcesz usunąć: ");
        long idOfGroupToBeUpdated = scanner.nextInt();
        Group group = groupDao.findById(idOfGroupToBeUpdated);
        groupDao.delete(group);
    }

    private static void printListOfGroups() {
        System.out.println("Lista grup tenisowych:\n");
        List<Group> groups = groupDao.getAll();
        for (Group group : groups) {
            System.out.println(group.toString());
        }
    }

    private static void updateGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id grupy, której dane chcesz zmienić: ");
        long idOfGroupToBeUpdated = scanner.nextInt();
        scanner.nextLine();
        Group group = groupDao.findById(idOfGroupToBeUpdated);

        System.out.println("Podaj zmienione dane\n" +
                "Podaj poziom zaawansowania: ");
        group.setNameLvl(scanner.nextLine());
        System.out.println("Podaj maksymalną wielkość grupy: ");
        group.setMaxNoOfStudents(scanner.nextInt());
        System.out.println("Podaj miesięczną opłate za wpisowe do grupy: ");
        group.setMonthlyPayment(scanner.nextInt());

        groupDao.update(group);
    }
    //TODO
    private static void createGroup() {
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

        //final Group group = new Group(null, firstName, lastName, email, address);
        //groupDao.create(group);
    }
}
