package br.com.tevitto.filmoon.data.dto;

public class ItemDto {
    private Long id;

    private String title;

    private String description;

    private int quantity; // quantity out the stock

    private double value_sale;
    private double value_rent;

    private StatusDto status;

    private TypeDto type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue_sale() {
        return value_sale;
    }

    public void setValue_sale(double value_sale) {
        this.value_sale = value_sale;
    }

    public double getValue_rent() {
        return value_rent;
    }

    public void setValue_rent(double value_rent) {
        this.value_rent = value_rent;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    public TypeDto getType() {
        return type;
    }

    public void setType(TypeDto type) {
        this.type = type;
    }
}
