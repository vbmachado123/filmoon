package br.com.tevitto.filmoon.data.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Client client;

    private DateTime dateHour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(DateTime dateHour) {
        this.dateHour = dateHour;
    }
}
