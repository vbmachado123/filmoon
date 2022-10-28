package br.com.tevitto.filmoon.data.dto;

import br.com.tevitto.filmoon.data.types.StatusEnum;

public class StatusDto {
    private Long id;

    private StatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
