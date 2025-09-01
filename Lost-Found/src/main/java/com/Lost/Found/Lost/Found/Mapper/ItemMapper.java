package com.Lost.Found.Lost.Found.Mapper;

import com.Lost.Found.Lost.Found.DTO.ItemDTO;
import com.Lost.Found.Lost.Found.Model.AppUser;
import com.Lost.Found.Lost.Found.Model.Item;

public class ItemMapper {

    // from ItemDto to Item entity
    public static Item toItem(ItemDTO itemDTO){
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setType(itemDTO.getType());
        item.setDate(itemDTO.getDate());
        item.setLocation(itemDTO.getLocation());

        AppUser owner = new AppUser();
        owner.setId(itemDTO.getOwnerId());
        item.setOwner(owner);
        return item;
    }
    // from Item entity to ItemDTO
    public static ItemDTO itemDTO(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(item.getName());
        itemDTO.setType(item.getType());
        itemDTO.setLocation(item.getLocation());
        itemDTO.setDate(item.getDate());
        itemDTO.setOwnerId(item.getOwner().getId());
        return itemDTO;
    }
}
