package pl.sda.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String telephoneNumber;
    private String email;
    private Address address;

    public Parent() {
    }

    public Parent(Long id, String name, String telephoneNumber, String email, Address address) {
        this.id = id;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return Objects.equals(id, parent.id) &&
                Objects.equals(name, parent.name) &&
                Objects.equals(telephoneNumber, parent.telephoneNumber) &&
                Objects.equals(email, parent.email) &&
                Objects.equals(address, parent.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, telephoneNumber, email, address);
    }
}
