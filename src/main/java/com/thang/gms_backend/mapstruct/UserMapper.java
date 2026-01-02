package com.thang.gms_backend.mapstruct;

import com.thang.gms_backend.dto.response.UserResponse;
import com.thang.gms_backend.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE =  Mappers.getMapper(UserMapper.class);

    @Mapping(source = "id", target = "id")
    UserResponse toUserResponse(Users user);
}
