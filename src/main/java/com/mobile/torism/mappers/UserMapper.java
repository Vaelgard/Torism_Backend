package com.mobile.torism.mappers;

import com.mobile.torism.dto.UserDTO;
import com.mobile.torism.entities.OurUsers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO userToUserDTO(OurUsers user);
    OurUsers userDTOToUser(UserDTO userDTO);
}
