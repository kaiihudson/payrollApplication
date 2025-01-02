package payroll.person.service;

public class PersonDTO {
    private Long id;
    private String name;
    private String phoneNum;
    private String address;

    public PersonDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.phoneNum = builder.phoneNum;
        this.address = builder.address;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String phoneNum;
        private String address;

        public PersonDTO build() {
            return new PersonDTO(this);
        }

        public Builder setId(Long id) {
            this.id = id;
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

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }
    }
}
