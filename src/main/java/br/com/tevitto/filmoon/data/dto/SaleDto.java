package br.com.tevitto.filmoon.data.dto;

import org.joda.time.DateTime;

public class SaleDto {
    private Long id;

    private ItemDto item;

    private ClientDto client;

    private boolean returned;

    private DateTime dateHour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public DateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(DateTime dateHour) {
        this.dateHour = dateHour;
    }
}
