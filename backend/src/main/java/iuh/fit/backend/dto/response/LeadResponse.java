package iuh.fit.backend.dto.response;

import iuh.fit.backend.entity.LeadStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LeadResponse {

    private UUID id;

    private String customerName;

    private String email;

    private String phone;

    private LeadStatus status;
}