package br.com.tevitto.filmoon.data.dto;

import org.joda.time.DateTime;

public class RentDto {
    private Long id;

    private ItemDto item;

    private ClientDto client;

    private boolean returned;

    private int days;

    private DateTime dateHour;

    private String message;

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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public DateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(DateTime dateHour) {
        this.dateHour = dateHour;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
