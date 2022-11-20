package com.unify.validation.service.mapper;

import com.unify.validation.domain.User;
import com.unify.validation.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends EntityMapper<UserDTO, User> {
}
