package com.serkanguner.mapper;

import com.serkanguner.dto.request.LoginRequestDto;
import com.serkanguner.dto.request.RegisterRequestDto;
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

    @Mapping(target = "create_at",source = "auth.createAt")
    RegisterResponseDto authToDto(Auth auth);
    Auth dtoToAuth(RegisterRequestDto dto);

    @Mapping(target = "create_at",source = "auth.createAt")
    LoginResponseDto loginToDto(Auth auth);
    Auth dtoToLogin(LoginRequestDto dto);
}
