package iuh.fit.backend.dto.request;

import iuh.fit.backend.entity.LeadStatus;
import lombok.Data;

@Data
public class UpdateLeadRequest {

    private String customerName;

    private String email;

    private String phone;

    private String vehicleBrand;

    private String vehicleModel;

    private LeadStatus status;
}