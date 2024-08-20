package model;

public record ContactData(String lastName, String firstName, String address,String nickName) {
    public ContactData() {
        this("", "", "","");
    }



    public ContactData withNickname(String nickName) {
        return new ContactData(this.lastName, this.firstName, this.address, nickName);
    }


    public ContactData withLastName(String lastName) {
        return new ContactData(lastName, this.firstName, this.address,this.nickName);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.lastName, firstName, this.address,this.nickName);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.lastName, this.firstName, address,this.nickName);
    }

}