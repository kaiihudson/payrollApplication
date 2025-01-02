package payroll.person.model;

import java.util.Objects;

import jakarta.persistence.*;

/**
 * The type Person.
 */
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

    /**
     * Instantiates a new Person.
     */
    Person() {
    }

    /**
     * Instantiates a new Person.
     *
     * @param builder the builder
     */
    Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phoneNum = builder.phoneNum;
        this.identity = builder.identity;
        this.status = builder.status;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    /**
     * Gets phone num.
     *
     * @return the phone num
     */
    public String getPhoneNum() {
        return this.phoneNum;
    }

    /**
     * Sets phone num.
     *
     * @param phoneNum the phone num
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * Gets identity.
     *
     * @return the identity
     */
    public String getIdentity() {
        return this.identity;
    }

    /**
     * Sets identity.
     *
     * @param identity the identity
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * The type Builder.
     */
    public static class Builder{
        private Long id;
        private String firstName;
        private String lastName;
        private String name;
        private String phoneNum;
        private String identity;
        private String address;
        private Status status;

        /**
         * Sets id.
         *
         * @param id the id
         * @return the id
         */
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets first name.
         *
         * @param firstName the first name
         * @return the first name
         */
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets last name.
         *
         * @param lastName the last name
         * @return the last name
         */
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets phone num.
         *
         * @param phoneNum the phone num
         * @return the phone num
         */
        public Builder setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
            return this;
        }

        /**
         * Sets identity.
         *
         * @param identity the identity
         * @return the identity
         */
        public Builder setIdentity(String identity) {
            this.identity = identity;
            return this;
        }

        /**
         * Sets address.
         *
         * @param address the address
         * @return the address
         */
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * Sets status.
         *
         * @param status the status
         * @return the status
         */
        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        /**
         * Build person.
         *
         * @return the person
         */
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