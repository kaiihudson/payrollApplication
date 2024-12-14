package payroll.person.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import payroll.order.model.AppOrder;
import payroll.person.model.Status;

@Entity
@Table(name = "people")
public class Person {

    private @Id
    @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String name;
    private String phoneNum;
    private String identity;
    private String address;
    private Status status;
    @OneToMany(mappedBy = "id")
    private List<AppOrder> orders = new ArrayList<AppOrder>();

    Person() {
    }

    Person(String firstName, String lastName, String phoneNum, String identity, String address, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.identity = identity;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Person person))
            return false;
        return Objects.equals(this.id, person.id) && Objects.equals(this.firstName, person.firstName) && Objects.equals(this.lastName, person.lastName) && //
                Objects.equals(this.phoneNum, person.phoneNum) && Objects.equals(this.identity, person.identity) && Objects.equals(this.address, person.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName, this.phoneNum, this.address, this.status);
    }


    @Override
    public String toString() {
        return "Person{" + "id=" + this.id + ", firstName=" + this.firstName + '\'' + //
                "lastName=" + this.lastName + '\'' + ", " +//
                "phoneNum=" + this.phoneNum + '\'' + ", " +//
                "identity=" + this.identity + '\'' + ", " +//
                "address=" + this.address + '\'' + ", " +//
                "status=" + this.status +//
                "}";
    }
}