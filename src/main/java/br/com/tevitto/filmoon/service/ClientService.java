package br.com.tevitto.filmoon.service;

import br.com.tevitto.filmoon.data.dto.ClientDto;
import br.com.tevitto.filmoon.data.model.Client;
import br.com.tevitto.filmoon.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    private Client client;

    public ClientDto create(ClientDto dto) {
        client = new Client();

        client.setEmail(dto.getEmail());
        client.setName(dto.getName());
        client.setPhone(dto.getPhone());

        Client save = clientRepository.save(client);
        dto.setId(save.getId());

        return dto;
    }

    public ClientDto find_one(ClientDto dto) {
        Optional<Client> optional = null;

        try {
            if (dto.getId() >= 1) {
                optional = clientRepository.findById(dto.getId());
                if (optional.isPresent()) {

                    return convertClient(optional.get());
                } else {
                    return null;
                }
            } else if (dto.getName() != null) {
                optional = clientRepository.findByName(dto.getName());
                return convertClient(optional.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return dto;
        }

        return null;
    }

    protected ClientDto convertClient(Client model) {

        ClientDto dto = new ClientDto();

        dto.setId(model.getId());
        dto.setEmail(model.getEmail());
        dto.setName(model.getName());
        dto.setPhone(model.getPhone());

        return dto;
    }

    public List<ClientDto> find_all() {
        List<ClientDto> dtos = new ArrayList<>();
        List<Client> models = clientRepository.findAll();

        for (Client c : models)
            dtos.add(convertClient(c));

        return dtos;
    }

    public Client convertClientDto(ClientDto dto) {
        client = new Client();

        client.setEmail(dto.getEmail());
        client.setName(dto.getName());
        client.setPhone(dto.getPhone());
        client.setId(dto.getId());
        return client;

    }
}
