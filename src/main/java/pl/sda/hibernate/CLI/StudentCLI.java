package pl.sda.hibernate.CLI;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.sda.hibernate.dao.HibernateParentDao;
import pl.sda.hibernate.dao.HibernateStudentDao;
import pl.sda.hibernate.dao.StudentDao;
import pl.sda.hibernate.dao.ParentDao;
import pl.sda.hibernate.entity.Parent;
import pl.sda.hibernate.entity.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class StudentCLI {

    private static SessionFactory sessionFactory;
    private static StudentDao studentDao;
    private static ParentDao parentDao;


    public static void main(String[] args) {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        studentDao = new HibernateStudentDao(sessionFactory);
        parentDao = new HibernateParentDao(sessionFactory);

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");

        studentMenu();
    }

    private static void studentMenu() {

        System.out.println();
        String base = "Podaj, co chcesz zrobić: " +
                "\n1 - dodać ucznia" +
                "\n2 - zmienić dane ucznia" +
                "\n3 - wyświetlić listę uczniów" +
                "\n4 - usunąć ucznia" +
                "\n5 - wyjście z programu";
        System.out.println(base);
        Scanner in = new Scanner(System.in);


        boolean cont = true;
        do {
            int choice = in.nextInt();
            in.nextLine();
            switch (choice) {

                case 1:
                    createStudent();
                    System.out.println(base);
                    break;
                case 2:
                    updateStudent();
                    System.out.println(base);
                    break;
                case 3:
                    printListOfStudentes();
                    System.out.println(base);
                    break;
                case 4:
                    deleteStudent();
                    System.out.println(base);
                    break;
                case 5:
                    cont = false;
                    break;
            }
        }
        while (cont);
        System.out.println("End");
    }

    private static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id ucznia, którego dane chcesz usunąć: ");
        long idOfStudentToBeUpdated = scanner.nextInt();
        Student student = studentDao.findById(idOfStudentToBeUpdated);
        studentDao.delete(student);
    }

    private static void printListOfStudentes() {
        System.out.println("Lista uczniów");
        List<Student> students = studentDao.getAll();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id ucznia, którego dane chcesz zmienić: ");
        long idOfStudentToBeUpdated = scanner.nextInt();
        scanner.nextLine();
        Student student = studentDao.findById(idOfStudentToBeUpdated);
        System.out.println("Podaj zmienione dane\nPodaj imię: ");
        student.setFirstName(scanner.nextLine());
        System.out.println("Podaj nazwisko: ");
        student.setLastName(scanner.nextLine());
        System.out.println("Podaj datę urodzenia (rrrr-mm-dd)");
        String dob = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Od ilu lat trenuje? \n");
        int experience = scanner.nextInt();
        System.out.println("Podaj numer grupy ");
        int groupNo = scanner.nextInt();
        System.out.println("Podaj e-mail opiekuna: ");
        String parentEmail = scanner.nextLine();

        Parent byEmail = parentDao.findByEmail(parentEmail);
        if (byEmail == null) {
            byEmail = new Parent();
            System.out.println("Podaj imię opiekuna: ");
            byEmail.setName(scanner.nextLine());
            System.out.println("Podaj nazwisko opiekuna:");
            String parentLastName = scanner.nextLine();
            parentDao.create(byEmail);
        }

        studentDao.update(student);
    }

    private static void createStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dane osobowe" +
                "\nPodaj imię: ");
        String firstName = scanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String lastName = scanner.nextLine();
        System.out.println("Podaj datę urodzenia (rrrr-mm-dd)");
        String dob = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(dob, DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println("Od ilu lat trenuje? \n");
        int experience = scanner.nextInt();
        System.out.println("Podaj numer grupy ");
        int groupNo = scanner.nextInt();
        System.out.println("Podaj e-mail opiekuna: ");
        String parentEmail = scanner.nextLine();

        Parent byEmail = parentDao.findByEmail(parentEmail);
        if (byEmail == null) {
            byEmail = new Parent();
            System.out.println("Podaj imię opiekuna: ");
            byEmail.setName(scanner.nextLine());
            System.out.println("Podaj nazwisko opiekuna:");
            String parentLastName = scanner.nextLine();
        parentDao.create(byEmail);
        }


        final Student student = new Student(null, firstName, lastName, birthDate, experience, groupNo, byEmail);
        studentDao.create(student);
    }
}
