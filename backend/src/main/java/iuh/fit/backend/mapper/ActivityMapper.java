package iuh.fit.backend.mapper;


import iuh.fit.backend.dto.request.CreateActivityRequest;
import iuh.fit.backend.dto.response.ActivityResponse;
import iuh.fit.backend.entity.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {

    Activity toEntity(CreateActivityRequest request);

    ActivityResponse toResponse(Activity activity);

}