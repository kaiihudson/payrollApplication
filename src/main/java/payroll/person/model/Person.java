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

    Person() {
    }

    Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phoneNum = builder.phoneNum;
        this.identity = builder.identity;
        this.status = builder.status;
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

    public static class Builder{
        private Long id;
        private String firstName;
        private String lastName;
        private String name;
        private String phoneNum;
        private String identity;
        private String address;
        private Status status;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
            return this;
        }

        public Builder setIdentity(String identity) {
            this.identity = identity;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
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