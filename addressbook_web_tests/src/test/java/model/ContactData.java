package model;

public record ContactData(String lastName, String firstName, String address) {
    public ContactData() {
        this("", "", "");
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(lastName, this.firstName, this.address);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.lastName, firstName, this.address);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.lastName, this.firstName, address);
    }
}