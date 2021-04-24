package pl.sda.hibernate.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Group group;
    private LocalDate period;
    private double amountPaid;
    private double amountDue;


}
