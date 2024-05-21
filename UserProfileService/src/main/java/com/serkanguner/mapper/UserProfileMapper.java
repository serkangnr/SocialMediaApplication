package com.serkanguner.mapper;

import com.serkanguner.dto.request.UserProfileSaveRequestDto;
import com.serkanguner.dto.response.ActivationResponseDto;
import com.serkanguner.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {

    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    UserProfile dtoToUserProfile(UserProfileSaveRequestDto dto);

    ActivationResponseDto activationToDto(UserProfile userProfile);
}
