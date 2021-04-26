package pl.sda.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.CLI.CoachCLI;
import pl.sda.hibernate.CLI.GroupCLI;
import pl.sda.hibernate.dao.CoachDao;
import pl.sda.hibernate.dao.GroupDao;
import pl.sda.hibernate.dao.HibernateCoachDao;
import pl.sda.hibernate.dao.HibernateGroupDao;
import pl.sda.hibernate.entity.Coach;

import java.util.Scanner;


public class MainApp {

    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);

        boolean condDoWhile = true;
        String base = "Podaj, co chcesz zrobić: " +
                "\n1 - Edytowac trenera" +
                "\n2 - Edytowac grupe" +
                "\n3 - Edytowac rodzica" +
                "\n4 - Edytowac ucznia" +
                "\n5 - Zamknij aplikacje";


        System.out.println("START");
        do {
            System.out.println(base);
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {
                case 1:
                    CoachCLI coachCLI = new CoachCLI();
                    break;
                case 2:
                    GroupCLI groupCLI = new GroupCLI();
                    break;
                case 3:
                    ParentCLI parentCLI = new ParentCLI();
                    break;
                case 4:
                    StudentCLI studentCLI = new StudentCLI();
                    break;
                case 5:
                    condDoWhile = false;
                    break;
            }
        }
        while (condDoWhile);
        System.out.println("END");
    }
}