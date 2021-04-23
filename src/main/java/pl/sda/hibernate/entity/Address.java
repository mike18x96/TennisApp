package pl.sda.hibernate.entity;

import java.util.Objects;

public class Address {
    private String street;
    private String streetNo;
    private String flatNo;
    private String zipCode;
    private String city;

    public Address(String street, String streetNo, String flatNo, String zipCode, String city) {
        this.street = street;
        this.streetNo = streetNo;
        this.flatNo = flatNo;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(streetNo, address.streetNo) &&
                Objects.equals(flatNo, address.flatNo) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNo, flatNo, zipCode, city);
    }
}
