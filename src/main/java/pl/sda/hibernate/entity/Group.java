package pl.sda.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToMany(mappedBy = "group")
    private List<Student> studentList = new ArrayList<Student>();
    private String nameLvl;
    private int maxNoOfStudents;
    private int monthlyPayment;
    private boolean isGroupFull = true;

    public Group() {
    }

    public Group(Long Id, String nameLvl, int maxNoOfStudents, int monthlyPayment) {
        this.Id = Id;
        this.nameLvl = nameLvl;
        this.maxNoOfStudents = maxNoOfStudents;
        this.monthlyPayment = monthlyPayment;
    }

    public Long getId() {
        return Id;
    }

    public boolean isGroupFull() {
        return isGroupFull;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getNameLvl() {
        return nameLvl;
    }

    public void setNameLvl(String nameLvl) {
        this.nameLvl = nameLvl;
    }

    public int getMaxNoOfStudents() {
        return maxNoOfStudents;
    }

    public void setMaxNoOfStudents(int maxNoOfStudents) {
        this.maxNoOfStudents = maxNoOfStudents;
    }

    public int getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(int monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return maxNoOfStudents == group.maxNoOfStudents && isGroupFull == group.isGroupFull && Double.compare(group.monthlyPayment, monthlyPayment) == 0 && Id.equals(group.Id) && Objects.equals(studentList, group.studentList) && Objects.equals(nameLvl, group.nameLvl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, studentList, nameLvl, maxNoOfStudents, isGroupFull, monthlyPayment);
    }

}
