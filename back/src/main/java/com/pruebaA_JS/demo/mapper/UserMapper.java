package com.pruebaA_JS.demo.mapper;
import com.example.model.UsersDTO;
import com.pruebaA_JS.demo.entities.Users;

public class UserMapper {

    public static UsersDTO toDto(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setEmail(user.getEmail());
        // Agrega aquí los campos que necesites mapear
        return dto;
    }

}
