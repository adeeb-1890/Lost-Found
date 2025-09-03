package com.Lost.Found.Lost.Found.Controller;

import com.Lost.Found.Lost.Found.DTO.AppUserDTO;
import com.Lost.Found.Lost.Found.DTO.ItemDTO;
import com.Lost.Found.Lost.Found.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Register item
    @PostMapping
    public ResponseEntity<ItemDTO> registerItem(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.registerItem(itemDTO), HttpStatus.CREATED);
    }

    // Get all items
    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    // Get item by ID
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    // Update item
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@RequestBody ItemDTO itemDTO, @PathVariable Long id) {
        return ResponseEntity.ok(itemService.updateItem(itemDTO, id));
    }

    // Delete item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItemById(id);
        return ResponseEntity.noContent().build();
    }

    // Get owner of item
    @GetMapping("/{id}/owner")
    public ResponseEntity<AppUserDTO> getOwnerOfItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getOwnerOfItem(id));
    }
}
