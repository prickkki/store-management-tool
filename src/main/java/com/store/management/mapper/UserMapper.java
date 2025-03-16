package com.store.management.mapper;


import com.store.management.entity.User;
import com.store.management.dtoRecord.UserDTO;
import com.store.management.entity.StoreUnitNomenclator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDTO userToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);  // Direct mapping
    }

    public User dtoToUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);  // Direct mapping

        return user;
    }

}
