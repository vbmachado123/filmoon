package br.com.tevitto.filmoon.service;

import br.com.tevitto.filmoon.data.dto.ItemDto;
import br.com.tevitto.filmoon.data.dto.StatusDto;
import br.com.tevitto.filmoon.data.dto.TypeDto;
import br.com.tevitto.filmoon.data.model.Item;
import br.com.tevitto.filmoon.data.model.Status;
import br.com.tevitto.filmoon.data.model.Type;
import br.com.tevitto.filmoon.repository.ItemRepository;
import br.com.tevitto.filmoon.repository.StatusRepository;
import br.com.tevitto.filmoon.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    private Item item;

    @Autowired
    private StatusRepository statusRepository;
    private Status status;

    @Autowired
    private TypeRepository typeRepository;
    private Type type;


    public ItemDto create(ItemDto dto) {

        Item saved = itemRepository.save(convertItemDto(dto));
        dto.setId(saved.getId());

        return dto;
    }

    protected Status convertStatusDto(StatusDto dto) {

        status = new Status();
        status = statusRepository.getById(dto.getId());
        return status;
    }

    protected Type convertTypeDto(TypeDto dto) {

        type = new Type();
        type = typeRepository.getById(dto.getId());
        return type;
    }

    public List<ItemDto> findAll(int type) {
        List<ItemDto> dtos = new ArrayList<>();
        List<Item> models = itemRepository.findAll();

        for (Item i : models) {
            ItemDto dto = new ItemDto();

            if (type != 0 && type > 0) { // Filtrado por tipo
                if (type == i.getStatus().getId()) {
                    //dto.setStatus(convertStatus(i.getStatus()));
                    dtos.add(convertItem(i));
                }
            } else
                dtos.add(addItem(i));
        }

        return dtos;
    }

    protected ItemDto addItem(Item i) {
        return convertItem(i);
    }

    protected ItemDto convertItem(Item i) {
        ItemDto dto = new ItemDto();

        dto.setQuantity(i.getQuantity());
        dto.setDescription(i.getDescription());
        dto.setTitle(i.getTitle());
        dto.setValue_rent(i.getValue_rent());
        dto.setValue_sale(i.getValue_sale());
        dto.setType(convertType(i.getType()));
        dto.setId(i.getId());
        dto.setStatus(convertStatus(i.getStatus()));
        return dto;
    }

    protected Item convertItemDto(ItemDto i) {

        item = new Item();

        item.setQuantity(i.getQuantity());
        item.setDescription(i.getDescription());
        item.setTitle(i.getTitle());
        item.setValue_rent(i.getValue_rent());
        item.setValue_sale(i.getValue_sale());
        item.setType(convertTypeDto(i.getType()));
        item.setStatus(convertStatusDto(i.getStatus()));
        item.setId(i.getId());
        return item;
    }

    private TypeDto convertType(Type model) {
        TypeDto dto = new TypeDto();
        dto.setId(model.getId());
        dto.setCategory(model.getCategory());
        return dto;
    }

    private StatusDto convertStatus(Status model) {
        StatusDto dto = new StatusDto();
        dto.setId(model.getId());
        dto.setStatus(model.getStatus());
        return dto;
    }

    public ItemDto find_one(ItemDto dto) {

        try {
            Optional<Item> optional = null;
            if (dto.getId() != 0 && dto.getId() > 0) {
                optional = itemRepository.findById(dto.getId());
            } else if (dto.getTitle() != null)
                optional = itemRepository.findByTitle(dto.getTitle());
            else if (dto.getType() != null)
                optional = itemRepository.findByType(convertTypeDto(dto.getType()));

            if (optional.isPresent()) return convertItem(optional.get());
            else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ItemDto update(ItemDto dto) {
        Optional<Item> optional = itemRepository.findById(dto.getId());

        if (optional.isPresent()) {
            item = optional.get();
            item = convertItemDto(dto);
            itemRepository.save(item);
            return dto;
        } else
            return null;
    }
}
