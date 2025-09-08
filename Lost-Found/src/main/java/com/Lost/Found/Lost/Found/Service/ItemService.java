package com.Lost.Found.Lost.Found.Service;

import com.Lost.Found.Lost.Found.DTO.AppUserDTO;
import com.Lost.Found.Lost.Found.DTO.ItemDTO;
import com.Lost.Found.Lost.Found.Mapper.AppUserMapper;
import com.Lost.Found.Lost.Found.Mapper.ItemMapper;
import com.Lost.Found.Lost.Found.Model.AppUser;
import com.Lost.Found.Lost.Found.Model.Item;
import com.Lost.Found.Lost.Found.Repository.AppUserRepo;
import com.Lost.Found.Lost.Found.Repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private AppUserRepo appUserRepo;
    // register Item
    public ItemDTO registerItem(ItemDTO item){
        Item newItem = new Item();
        newItem.setName(item.getName());
        newItem.setDate(item.getDate());
        newItem.setLocation(item.getLocation());
        if(item.getOwnerId() == null){
            newItem.setOwner(null);
            newItem.setType("Found");
        }
        else{
            AppUser appUser = appUserRepo.findById(item.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("no such user exists , please register first."));
            newItem.setOwner(appUser);
            newItem.setType("lost");
        }
        itemRepo.save(newItem);
        return ItemMapper.itemDTO(newItem);
    }

    // get all lost items
    public List<ItemDTO> getAllItems(){
        return itemRepo.findAll().stream().map(ItemMapper :: itemDTO).toList();
    }

    // get item by ID
    public ItemDTO getItemById(Long id){
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No such exists with id " + id + "."));
        return ItemMapper.itemDTO(item);
    }

    // update item
    public ItemDTO updateItem(ItemDTO itemDTO, Long id) {
        Item item = itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("No such item exists with id " + id + "."));

        if (itemDTO.getOwnerId() != null) {
            AppUser appUser = appUserRepo.findById(itemDTO.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("No owner exists for the given owner id."));
            item.setOwner(appUser);
            item.setType("Lost");
        } else {
            item.setOwner(null);
            item.setType("Found");
        }
        item.setName(itemDTO.getName());
        item.setLocation(itemDTO.getLocation());
        item.setDate(itemDTO.getDate());
        itemRepo.save(item);
        return ItemMapper.itemDTO(item);
    }
    // delete item
    public void deleteItemById(Long id){
        Item item = itemRepo.findById(id).orElseThrow(() -> new RuntimeException("No such item exists."));
        itemRepo.delete(item);
    }
    // get owner by Item
    public AppUserDTO getOwnerOfItem(Long id){
        Item item = itemRepo.findById(id).orElseThrow(() -> new RuntimeException("No such item exists."));
        AppUser owner = item.getOwner();
        if(owner == null){
            throw new RuntimeException("This is item doesn't have any specified owner yet.");
        }
        return AppUserMapper.appUserDTO(owner);
    }

}
