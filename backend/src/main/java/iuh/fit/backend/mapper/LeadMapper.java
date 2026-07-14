package iuh.fit.backend.mapper;

import iuh.fit.backend.dto.request.CreateLeadRequest;
import iuh.fit.backend.dto.response.LeadDetailResponse;
import iuh.fit.backend.dto.response.LeadResponse;
import iuh.fit.backend.entity.Lead;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeadMapper {

    LeadResponse toResponse(Lead lead);

    @Mapping(target = "activities", ignore = true)
    LeadDetailResponse toDetailResponse(Lead lead);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "salesPerson", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Lead toEntity(CreateLeadRequest request);
}
