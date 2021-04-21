package pl.sda.hibernate.CLI;

import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.dao.CoachDao;
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

        String base = "Podaj, co chcesz zrobić: " +
                "\n1 - dodać trenera" +
                "\n2 - zmienić dane trenera" +
                "\n3 - wyświetlić listę trenerów" +
                "\n4 - usunąć trenera" +
                "\n5 - Exit";
        System.out.println(base);
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        boolean a = true;
        do {
            in.nextLine();
            switch (choice) {

                case 1:
                    createCoach();
                    clearTerm.clearTerm();
                    System.out.println(base);
                    break;
                case 2:
                    updateCoach();
                    clearTerm.clearTerm();
                    System.out.println(base);
                    break;
                case 3:
                    printListOfCoaches();
                    clearTerm.clearTerm();
                    System.out.println(base);
                    break;
                case 4:
                    deleteCoach();
                    clearTerm.clearTerm();
                    System.out.println(base);
                    break;
                case 5:
                    a = false;
                    break;
            }
        }
        while(a);
        System.out.println("End");
    }

    private static void deleteCoach() {
        System.out.println();
    }

    private static void printListOfCoaches() {
    }

    private static void updateCoach() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię: ");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.println("Podaj adres e-mail: ");
        String email = scanner.nextLine();
        System.out.println("Podaj adres zamieszkania: ");
        String address = scanner.nextLine();
        final Coach coach = new Coach(null, firstName, lastName, email, address);
        coachDao.update(coach);
    }

    private static void createCoach() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię: ");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.println("Podaj adres e-mail: ");
        String email = scanner.nextLine();
        System.out.println("Podaj adres zamieszkania: ");
        String address = scanner.nextLine();

        final Coach coach = new Coach(null, firstName, lastName, email, address);
        coachDao.create(coach);
    }
}
