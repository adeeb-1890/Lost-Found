package com.Lost.Found.Lost.Found.Mapper;

import com.Lost.Found.Lost.Found.DTO.AppUserDTO;
import com.Lost.Found.Lost.Found.Model.AppUser;

import java.util.ArrayList;

public class AppUserMapper {
    //from DTO to entity
    public static AppUser toAppUser(AppUserDTO appUserDTO) {
        AppUser appUser = new AppUser();
        appUser.setName(appUserDTO.getName());
        appUser.setDepartment(appUserDTO.getDepartment());
        appUser.setEmail(appUserDTO.getEmail());

        if (appUserDTO.getItems() != null) {
            appUser.setItems(appUserDTO.getItems()
                    .stream()
                    .map(ItemMapper::toItem)
                    .toList()
            );
        } else {
            appUser.setItems(new ArrayList<>()); // prevent NPE
        }
        return appUser;
    }

    // from entity to DTO
    public static AppUserDTO appUserDTO(AppUser appUser) {
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setId(appUser.getId());
        appUserDTO.setName(appUser.getName());
        appUserDTO.setDepartment(appUser.getDepartment());
        appUserDTO.setEmail(appUser.getEmail());

        if (appUser.getItems() != null) {
            appUserDTO.setItems(
                    appUser.getItems().stream()
                            .map(ItemMapper::itemDTO)
                            .toList()
            );
        } else {
            appUserDTO.setItems(new ArrayList<>()); // avoid null in JSON
        }
        return appUserDTO;
    }
}
