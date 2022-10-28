package br.com.tevitto.filmoon.controller;


import br.com.tevitto.filmoon.data.dto.ItemDto;
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

    @ApiOperation(value = "Cria um Aluguel do Item, assocciando um cliente e preenchendo infos pertinentes")
    @ApiResponse(code = 200, message = "Retorna o Aluguel criado", response = RentDto.class)
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody RentDto dto) {

        return ok(service.create(dto));
    }

    @ApiOperation(value = "Cria um Aluguel do Item, assocciando um cliente e preenchendo infos pertinentes")
    @ApiResponse(code = 200, message = "Retorna o Aluguel criado", response = RentDto.class)
    @GetMapping("/find_one")
    public ResponseEntity find_one(@RequestBody RentDto dto) {

        return ok(service.find_one(dto));
    }
}
