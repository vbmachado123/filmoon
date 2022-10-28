package br.com.tevitto.filmoon.controller;


import br.com.tevitto.filmoon.data.dto.ClientDto;
import br.com.tevitto.filmoon.data.dto.ItemDto;
import br.com.tevitto.filmoon.service.ClientService;
import br.com.tevitto.filmoon.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @ApiOperation(value = "Cria um Cliente")
    @ApiResponse(code = 200, message = "Retorna o Cliente criado", response = ClientDto.class)
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ClientDto dto) {

        return ok(service.create(dto));
    }

    @GetMapping("/find_one")
    public ResponseEntity find_one(@RequestBody ClientDto dto) {
        ClientDto clientDto = service.find_one(dto);

        try {
            if (clientDto.getId() <= 0)
                return ResponseEntity.badRequest().body("Nenhum item Encontrado");
            else return ok(clientDto);
        } catch (Exception e) {
            e.getMessage();
        }
        return badRequest().body("Nenhum item Encontrado");
    }

    @GetMapping("/find_all")
    public ResponseEntity find_all() {
        return ok(service.find_all());
    }

}
