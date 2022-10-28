package br.com.tevitto.filmoon.service;

import br.com.tevitto.filmoon.data.dto.RentDto;
import br.com.tevitto.filmoon.data.model.Client;
import br.com.tevitto.filmoon.data.model.Item;
import br.com.tevitto.filmoon.data.model.Rent;
import br.com.tevitto.filmoon.repository.ClientRepository;
import br.com.tevitto.filmoon.repository.ItemRepository;
import br.com.tevitto.filmoon.repository.RentRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepository;
    private Rent rent;

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemService itemService;
    private Item item;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;
    private Client client;

    public RentDto create(RentDto dto) {

        rent = new Rent();

        rent.setDays(dto.getDays());
        rent.setDateHour(new DateTime());
        rent.setReturned(false); // Verifica se o item já foi devolvido ou não
        rent.setClient(clientService
                .convertClientDto(clientService
                        .find_one(dto.getClient())));

        rent.setItem(itemService.convertItemDto(
                itemService.find_one(dto.getItem())));

        Rent save = rentRepository.save(rent);

        dto.setId(save.getId());
        return dto;
    }

    public RentDto find_one(RentDto dto) {
        Optional<Rent> optional = rentRepository.findById(dto.getId());
        if (optional.isPresent()) {
            rent = optional.get();
            dto = convertRent(rent);
            if (rent.getDateHour().plusDays(rent.getDays()).isEqual(DateTime.now()) && !rent.isReturned()
                    || rent.getDateHour().plusDays(rent.getDays()).isAfter(DateTime.now()) && !rent.isReturned()) {
                dto.setMessage("A devolução está atrasada!");
            } else
                dto.setMessage("O Item já foi devolvido!");
        }

        return dto;
    }

    private RentDto convertRent(Rent model) {
        RentDto dto = new RentDto();
        dto.setId(model.getId());
        dto.setDays(model.getDays());
        dto.setReturned(model.isReturned());
        dto.setDateHour(model.getDateHour());
        dto.setClient(clientService
                .convertClient(model.getClient()));
        dto.setItem(itemService.convertItem(model.getItem()));
        return dto;
    }
}
