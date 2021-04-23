package pl.sda.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private List<Student> studentList = new ArrayList<Student>();
    private int maxNoOfStudents;
    private boolean isGroupFull;
    private double monthlyPayment;

    public Group() {
    }

    public Group(int groupId, List<Student> studentList, int maxNoOfStudents, boolean isGroupFull, double monthlyPayment) {
        this.groupId = groupId;
        this.studentList = studentList;
        this.maxNoOfStudents = maxNoOfStudents;
        this.isGroupFull = isGroupFull;
        this.monthlyPayment = monthlyPayment;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public int getMaxNoOfStudents() {
        return maxNoOfStudents;
    }

    public void setMaxNoOfStudents(int maxNoOfStudents) {
        this.maxNoOfStudents = maxNoOfStudents;
    }

    public boolean isGroupFull() {
        return isGroupFull;
    }

    public void setGroupFull(boolean groupFull) {
        isGroupFull = groupFull;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId &&
                maxNoOfStudents == group.maxNoOfStudents &&
                isGroupFull == group.isGroupFull &&
                Double.compare(group.monthlyPayment, monthlyPayment) == 0 &&
                Objects.equals(studentList, group.studentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, studentList, maxNoOfStudents, isGroupFull, monthlyPayment);
    }
}
