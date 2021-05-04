package pl.sda.hibernate.CLI;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.dao.GroupDao;
import pl.sda.hibernate.dao.HibernateGroupDao;
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
                    .addAnnotatedClass(Group.class)
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
                "\n2 - Zmienic dane grupy" +
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
        long idOfGroupToBeDeleted = scanner.nextInt();
        Group group = groupDao.findById(idOfGroupToBeDeleted);
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
                "Podaj nazwe grupy: ");
        group.setNameLvl(scanner.nextLine());
        System.out.println("Podaj maksymalną liczbe osób w grupie: ");
        group.setMaxNoOfStudents(scanner.nextInt());
        System.out.println("Podaj miesięczną opłate za uczestnictwo: ");
        group.setMonthlyPayment(scanner.nextInt());

        groupDao.update(group);
    }

    private static void createGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dane grupy\n");
        System.out.println("Podaj nazwę grupy: ");
        String nameLvl = scanner.nextLine();
        System.out.println("Podaj maksymalną liczbe osób w grupie: ");
        int maxNoOfStudents = scanner.nextInt();
        System.out.println("Podaj miesięczną opłate za uczestnictwo: ");
        int monthlyPayment = scanner.nextInt();

        final Group group = new Group(null,nameLvl, maxNoOfStudents, monthlyPayment);
        groupDao.create(group);
    }
}
