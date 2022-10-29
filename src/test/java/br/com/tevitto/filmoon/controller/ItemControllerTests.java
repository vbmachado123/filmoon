package br.com.tevitto.filmoon.controller;

import br.com.tevitto.filmoon.data.dto.ItemDto;
import br.com.tevitto.filmoon.data.dto.StatusDto;
import br.com.tevitto.filmoon.data.dto.TypeDto;
import br.com.tevitto.filmoon.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ItemService service;

    @Test
    @Description("Retorna uma lista com os itens, filtrando por tipo")
    void should_be_return_all_itens_in_list_filter_by_type() {
        String endpoint = "http://localhost:" + port + "/api/v1/item/find_all/1";

        ResponseEntity<ItemDto[]> response = restTemplate.getForEntity(endpoint, ItemDto[].class);

        System.out.println(response.getStatusCode());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void should_be_return_a_created_item() {
        String endpoint = "http://localhost:" + port + "/api/v1/item/create";

        ItemDto dto = new ItemDto();

        StatusDto statusDto = new StatusDto();
        statusDto.setId(3L);
        TypeDto typeDto = new TypeDto();
        typeDto.setId(2L);

        dto.setQuantity(3);
        dto.setStatus(statusDto);
        dto.setType(typeDto);
        dto.setTitle("");
        dto.setDescription("");
        dto.setValue_sale(0);
        dto.setValue_sale(0);

        ResponseEntity<ItemDto> response = restTemplate.postForEntity(endpoint, dto, ItemDto.class);

        System.out.println(response.getStatusCode());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
