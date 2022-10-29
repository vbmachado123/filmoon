package br.com.tevitto.filmoon.service;

import br.com.tevitto.filmoon.data.dto.RentDto;
import br.com.tevitto.filmoon.data.model.Client;
import br.com.tevitto.filmoon.data.model.Item;
import br.com.tevitto.filmoon.data.model.Rent;
import br.com.tevitto.filmoon.data.types.StatusEnum;
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

        Item itemModel = itemService.convertItemDto(itemService.find_one(dto.getItem()));
        System.out.println("");
        if (itemModel.getQuantity() >= 1) { // Verifica se tem disponivel no estoque

            if (itemModel.getStatus().getStatus() == StatusEnum.RENT
                    || itemModel.getStatus().getStatus() == StatusEnum.ALL) {
                rent = new Rent();

                rent.setDays(dto.getDays());
                rent.setDateHour(new DateTime());
                rent.setReturned(false); // Verifica se o item já foi devolvido ou não

                itemModel.setQuantity(itemModel.getQuantity() - 1);
                Item itemUpdated = itemRepository.save(itemModel);
                rent.setItem(itemUpdated);

                rent.setClient(clientService
                        .convertClientDto(clientService
                                .find_one(dto.getClient())));

                Rent save = rentRepository.save(rent);

//                DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");

                dto.setMessage("O item precisará ser devolvido até: " + DateTime.parse(
                        String.valueOf(rent.getDateHour().plusDays(rent.getDays()))));
                dto.setId(save.getId());

                return dto;
            } else {
                dto.setMessage("O item não está disponível para aluguel");
                return dto;
            }

        } else {
            dto.setMessage("O item selecionado não possui estoque o suficiente para essa operação");
            return dto;
        }
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

    public RentDto update(RentDto dto) {
        Optional<Rent> optional = rentRepository.findById(dto.getId());
        if (optional.isPresent()) {
            rent = optional.get();
            rent.setReturned(dto.isReturned());

            // Item devolvido ao estoque
            Item itemModel = itemService.convertItemDto(dto.getItem());
            itemModel.setQuantity(itemModel.getQuantity() + 1);
            itemRepository.save(itemModel);

            rentRepository.save(rent);

            dto.setMessage("Item Devolvido!");

            return dto;
        }
        return null;
    }
}
