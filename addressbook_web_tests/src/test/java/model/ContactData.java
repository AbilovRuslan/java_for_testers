package model;

import java.util.Objects;

public record ContactData(String id, String lastName, String firstName, String address) {
    public ContactData() {

        this("", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id,this.lastName, this.firstName, this.address);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id,lastName, this.firstName, this.address);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id,this.lastName, firstName, this.address);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id,this.lastName, this.firstName, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }




}