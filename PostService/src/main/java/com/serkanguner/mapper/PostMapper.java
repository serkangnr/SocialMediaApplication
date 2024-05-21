package com.serkanguner.mapper;

import com.serkanguner.dto.request.PostRequestDto;
import com.serkanguner.dto.request.PostSaveRequestDto;
import com.serkanguner.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    Post dtoToPost(PostSaveRequestDto dto);


}
