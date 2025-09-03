package com.Lost.Found.Lost.Found.Mapper;

import com.Lost.Found.Lost.Found.DTO.ItemDTO;
import com.Lost.Found.Lost.Found.Model.AppUser;
import com.Lost.Found.Lost.Found.Model.Item;

public class ItemMapper {

    // from ItemDto to Item entity
    public static Item toItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setType(itemDTO.getType());
        item.setDate(itemDTO.getDate());
        item.setLocation(itemDTO.getLocation());

        if (itemDTO.getOwnerId() != null) {
            AppUser owner = new AppUser();
            owner.setId(itemDTO.getOwnerId());
            item.setOwner(owner);
        } else {
            item.setOwner(null); // explicitly set null for "Found" items
        }

        return item;
    }

    // from Item entity to ItemDTO
    public static ItemDTO itemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setType(item.getType());
        itemDTO.setLocation(item.getLocation());
        itemDTO.setDate(item.getDate());

        if (item.getOwner() != null) {
            itemDTO.setOwnerId(item.getOwner().getId());
        } else {
            itemDTO.setOwnerId(null); // safely return null
        }

        return itemDTO;
    }
}
