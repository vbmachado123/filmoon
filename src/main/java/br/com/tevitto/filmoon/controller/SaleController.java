package br.com.tevitto.filmoon.controller;


import br.com.tevitto.filmoon.data.dto.SaleDto;
import br.com.tevitto.filmoon.service.SaleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/sale")
public class SaleController {

    @Autowired
    private SaleService service;


    @ApiOperation(value = "Cria um registro de venda do item, associando ao cliente e preenchendo as informações pertinentes")
    @ApiResponse(code = 200, message = "Retorna a Venda executada", response = SaleDto.class)
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody SaleDto dto) {
        SaleDto returned = service.create(dto);
        if (returned.getMessage().isEmpty()) return ok(returned);
        else
            return ResponseEntity.badRequest().body(dto.getItem());
    }

    @ApiOperation(value = "Retorna o histórico de vendas do estabelecimento, ordernando em ordem decrescente")
    @ApiResponse(code = 200, message = "Retorna uma lista de Vendas", response = SaleDto.class)
    @GetMapping("/find_all")
    public ResponseEntity find_all() {
        return ok(service.find_all());
    }

    @GetMapping("/find_one")
    public ResponseEntity find_one(@RequestBody SaleDto dto) {
        SaleDto saleDto = service.find_one(dto);

        try {
            if (saleDto.getId() <= 0)
                return ResponseEntity.badRequest().body("Nenhum item Encontrado");
            else return ok(saleDto);
        } catch (Exception e) {
            e.getMessage();
        }
        return badRequest().body("Nenhum item Encontrado");
    }


}
