package iuh.fit.backend.dto.response;

import iuh.fit.backend.entity.LeadStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class LeadDetailResponse {

    private UUID id;

    private String customerName;

    private String email;

    private String phone;

    private String vehicleBrand;

    private String vehicleModel;

    private LeadStatus status;

    private List<ActivityResponse> activities;
}