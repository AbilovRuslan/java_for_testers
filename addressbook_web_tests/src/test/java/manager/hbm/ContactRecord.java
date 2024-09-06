package manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {
    @Id
    public int id;
    public String firstName;
    public String lastName;
    public String Address;

    public ContactRecord() {}

    public ContactRecord(int id, String firstname, String lastname, String address) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.Address = address;
    }
}
