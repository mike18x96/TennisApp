package pl.sda.hibernate.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Coach {
    @Id
    Long id;
    String name;
    String email;
    String address;

    public Coach() {
    }

    public Coach(Long id, String name, String email, String address) {
        this.id = id;
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(id, coach.id) && Objects.equals(name, coach.name) && Objects.equals(email, coach.email) && Objects
                .equals(address, coach.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address);
    }
}
