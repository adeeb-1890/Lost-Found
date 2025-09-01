package com.Lost.Found.Lost.Found.Mapper;

import com.Lost.Found.Lost.Found.DTO.AppUserDTO;
import com.Lost.Found.Lost.Found.Model.AppUser;

public class AppUserMapper {
    //from DTO to entity
    public static AppUser toAppUser(AppUserDTO appUserDTO){
        AppUser appUser = new AppUser();
        appUser.setName(appUserDTO.getName());
        appUser.setDepartment(appUserDTO.getDepartment());
        appUser.setItems(appUserDTO.getItems().stream().map(ItemMapper :: toItem).toList());
        return appUser;
    }

    // from entity to DTO
    public static AppUserDTO appUserDTO(AppUser appUser){
        AppUserDTO appUserDTO = new AppUserDTO();
        appUserDTO.setName(appUser.getName());
        appUserDTO.setDepartment(appUser.getDepartment());
        appUserDTO.setItems(appUser.getItems().stream().map(ItemMapper :: itemDTO).toList());
        return appUserDTO;
    }
}
