package com.serkanguner.mapper;

import com.serkanguner.dto.request.*;
import com.serkanguner.dto.response.ActivationResponseDto;
import com.serkanguner.dto.response.LoginResponseDto;
import com.serkanguner.dto.response.RegisterResponseDto;
import com.serkanguner.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);


    RegisterResponseDto authToDto(Auth auth);
    Auth dtoToAuth(RegisterRequestDto dto);

    LoginResponseDto loginToDto(Auth auth);

    ActivationResponseDto activationToDto(Auth auth);


    @Mapping(source = "id", target = "authId")
    UserProfileSaveRequestDto toUserSaveRequestDto(Auth auth);


}
