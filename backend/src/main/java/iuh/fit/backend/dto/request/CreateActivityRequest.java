package iuh.fit.backend.dto.request;

import iuh.fit.backend.entity.ActivityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateActivityRequest {
    @NotNull(message = "Activity type is required")

    private ActivityType type;
    @NotBlank(message = "Note is required")
    private String note;
}