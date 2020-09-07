
package hu.toll.tollWeb.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String city;
    private String amount;

    @Column(unique = true)
    private String phoneNumber;
    private Date syncDate;
    private String status;
    private String source;
    private String type;

    public Place() {}

    public Place(String name, String phoneNumber, Date syncDate, String status, String source) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.syncDate = syncDate;
        this.status = status;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAmount() {
        return amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setType(String type) {
        this.type = type;
    }
}
