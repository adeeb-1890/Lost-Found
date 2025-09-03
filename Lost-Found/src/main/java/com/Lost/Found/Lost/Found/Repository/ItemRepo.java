package com.Lost.Found.Lost.Found.Repository;

import com.Lost.Found.Lost.Found.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item , Long> {
    List<Item> findByOwnerId(Long ownerId);
}
