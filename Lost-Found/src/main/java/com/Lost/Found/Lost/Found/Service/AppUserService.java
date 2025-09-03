package com.Lost.Found.Lost.Found.Service;

import com.Lost.Found.Lost.Found.DTO.AppUserDTO;
import com.Lost.Found.Lost.Found.Mapper.AppUserMapper;
import com.Lost.Found.Lost.Found.Model.AppUser;
import com.Lost.Found.Lost.Found.Repository.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;

    public static class AppUserValidator {
        public static void validate(AppUserDTO dto) {
            if (dto.getName() == null || dto.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            if (dto.getEmail() == null || !isValidEmail(dto.getEmail())) {
                throw new IllegalArgumentException("Invalid email format");
            }
        }

        private static boolean isValidEmail(String email) {
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            return email.matches(emailRegex);
        }
    }

    // Register User
    public AppUserDTO registerUser(AppUserDTO appUserDTO) {
        // Validate DTO
        AppUserValidator.validate(appUserDTO);

        if (appUserRepo.findByEmail(appUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists: " + appUserDTO.getEmail());
        }
        AppUser appUser = AppUserMapper.toAppUser(appUserDTO);

        appUserRepo.save(appUser);
        return AppUserMapper.appUserDTO(appUser);
    }

    // get user by id
    public AppUserDTO getUserById(Long id){
        AppUser appUser = appUserRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("user with userId " + id + " doesn't exists") );
        return AppUserMapper.appUserDTO(appUser);
    }
    // get all users
    public List<AppUserDTO> getAllUsers(){
        return appUserRepo.findAll().stream().map(AppUserMapper :: appUserDTO).toList();
    }
    // update User Info
    public AppUserDTO updateUser(AppUserDTO appUserDTO , Long id){
        AppUserValidator.validate(appUserDTO);
        AppUser appUser = appUserRepo.findById(id).orElseThrow(() -> new RuntimeException("User doesn't exists"));
        appUser.setDepartment(appUserDTO.getDepartment());
        appUser.setName(appUserDTO.getName());
        appUser.setEmail(appUserDTO.getEmail());
        appUserRepo.save(appUser);
        return AppUserMapper.appUserDTO(appUser);
    }
    // delete User by UserId
    public String deleteUser(Long id){
        AppUser appUser = appUserRepo.findById(id).orElseThrow(() -> new RuntimeException("User doesn't exists. "));
        appUserRepo.delete(appUser);
        return "User with user id " + id + " has been successfully deleted";
    }
}
