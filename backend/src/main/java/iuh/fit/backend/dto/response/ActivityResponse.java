package iuh.fit.backend.dto.response;

import iuh.fit.backend.entity.ActivityType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ActivityResponse {

    private UUID id;

    private ActivityType type;

    private String note;

    private LocalDateTime createdAt;
}