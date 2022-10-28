package br.com.tevitto.filmoon.controller;

import br.com.tevitto.filmoon.data.dto.ItemDto;
import br.com.tevitto.filmoon.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @ApiOperation(value = "Cria um Item (Filme, Série [DVD], Livro)")
    @ApiResponse(code = 200, message = "Retorna o item criado", response = ItemDto.class)
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody ItemDto dto) {

        return ok(service.create(dto));
    }

    @ApiOperation(value = "Retorna uma lista de itens, filtrando por Tipo (URL Recebe um int[id])")
    @ApiResponse(code = 200, message = "Retorna uma lista de itens", response = ItemDto.class)
    @GetMapping("/find_all/{type}")
    public ResponseEntity findAll(@PathVariable @Nullable int type) {
        return ok(service.findAll(type));
    }

    @ApiOperation(value = "Busca um Item por ID ou Title")
    @ApiResponse(code = 200, message = "Retorna um item", response = ItemDto.class)
    @GetMapping("/find_one")
    public ResponseEntity find_one(@RequestBody ItemDto dto) {
        ItemDto itemDto = service.find_one(dto);

        try {
            if (itemDto.getId() <= 0)
                return ResponseEntity.badRequest().body("Nenhum item Encontrado");
            else return ok(itemDto);
        } catch (Exception e) {
            e.getMessage();
        }
        return badRequest().body("Nenhum item Encontrado");
    }

    @ApiOperation(value = "Atualiza um Item")
    @ApiResponse(code = 200, message = "Retorna um item", response = ItemDto.class)
    @PutMapping("/update")
    public ResponseEntity update(@RequestBody ItemDto dto) {
        return ok(service.update(dto));
    }
}
