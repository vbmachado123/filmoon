package br.com.tevitto.filmoon.controller;

import br.com.tevitto.filmoon.data.dto.ClientDto;
import br.com.tevitto.filmoon.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ClientService service;

    @Test
    void should_be_return_all_clients_in_list() {
        String endpoint = "http://localhost:" + port + "/api/v1/client/find_all";

        ResponseEntity<ClientDto[]> response = restTemplate.getForEntity(endpoint, ClientDto[].class);

        System.out.println(response.getStatusCode());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void should_be_return_a_created_client() {
        String endpoint = "http://localhost:" + port + "/api/v1/client/create";

        ClientDto dto = new ClientDto();
        dto.setPhone("(11) 00000000");
        dto.setName("Name Test");
        dto.setEmail("test@mail.com");
        ResponseEntity<ClientDto> response = restTemplate.postForEntity(endpoint, dto, ClientDto.class);

        System.out.println(response.getStatusCode());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

//    @Test
//    void should_be_return_a_found_client() {
//        String endpoint = "http://localhost:" + port + "/api/v1/client/find_one";
//
//        ClientDto dto = new ClientDto();
//        dto.setId(1L);
//
//        ResponseEntity<ClientDto> response = restTemplate.getForObject(endpoint, dto, ClientDto.class);
//
//        System.out.println(response.getStatusCode());
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
}
