package br.com.tevitto.filmoon.controller;


import br.com.tevitto.filmoon.data.dto.RentDto;
import br.com.tevitto.filmoon.service.RentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/rent")
public class RentController {

    @Autowired
    private RentService service;

    @ApiOperation(value = "Cria um Aluguel do Item, associando um cliente e preenchendo infos pertinentes")
    @ApiResponse(code = 200, message = "Retorna o Aluguel criado", response = RentDto.class)
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody RentDto dto) {
        RentDto returned = service.create(dto);
        if (returned.getMessage().isEmpty())
            return ok(service.create(dto));
        else return ResponseEntity.badRequest().body(dto);
    }

    @ApiOperation(value = "Busca um Aluguel por ID")
    @ApiResponse(code = 200, message = "Retorna o Aluguel se existir", response = RentDto.class)
    @GetMapping("/find_one")
    public ResponseEntity find_one(@RequestBody RentDto dto) {

        return ok(service.find_one(dto));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody RentDto dto) {
        return ok(service.update(dto));
    }
}
