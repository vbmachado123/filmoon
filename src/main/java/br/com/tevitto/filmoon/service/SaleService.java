package br.com.tevitto.filmoon.service;

import br.com.tevitto.filmoon.data.dto.SaleDto;
import br.com.tevitto.filmoon.data.model.Client;
import br.com.tevitto.filmoon.data.model.Item;
import br.com.tevitto.filmoon.data.model.Sale;
import br.com.tevitto.filmoon.data.types.StatusEnum;
import br.com.tevitto.filmoon.repository.ClientRepository;
import br.com.tevitto.filmoon.repository.ItemRepository;
import br.com.tevitto.filmoon.repository.SaleRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;
    private Sale sale;

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

    public SaleDto create(SaleDto dto) {
        sale = new Sale();

        Item itemModel = itemService.convertItemDto(itemService.find_one(dto.getItem()));
        if (itemModel.getQuantity() >= 1) { // Verifica Item no estoque
            if (itemModel.getStatus().getStatus() == StatusEnum.SALE
                    || itemModel.getStatus().getStatus() == StatusEnum.ALL) { // Verifica se o item está disponivel para venda ou aluguel
                sale.setClient(clientService.convertClientDto(dto.getClient()));
                sale.setDateHour(DateTime.now());

                itemModel.setQuantity(itemModel.getQuantity() - 1);
                Item itemUpdated = itemRepository.save(itemModel);
                sale.setItem(itemUpdated);

                Sale save = saleRepository.save(sale);

                dto.setId(save.getId());

                return dto;
            } else
                dto.setMessage("O item não está disponivel para venda");

        } else
            dto.setMessage("O item não possui estoque disponivel para essa operação");

        return dto;
    }

    public List<SaleDto> find_all() {
        List<SaleDto> dtos = new ArrayList<>();
        List<Sale> models = saleRepository.findAll();

        // Order by Desc
        Collections.sort(models, Collections.reverseOrder());

        for (Sale s : models)
            dtos.add(convertSale(s));

        return dtos;
    }

    private SaleDto convertSale(Sale s) {
        SaleDto dto = new SaleDto();
        dto.setId(s.getId());
        dto.setClient(clientService.convertClient(s.getClient()));
        dto.setDateHour(s.getDateHour());
        dto.setItem(itemService.convertItem(s.getItem()));
        return dto;
    }

    public SaleDto find_one(SaleDto dto) {
        try {
            if (dto.getId() != null && dto.getId() > 0) {
                Optional<Sale> optional = saleRepository.findById(dto.getId());
                if (optional.isPresent())
                    return convertSale(optional.get());
                else return new SaleDto();
            } else
                return null;
        } catch (Exception e) {
            return null;
        }
    }
}
