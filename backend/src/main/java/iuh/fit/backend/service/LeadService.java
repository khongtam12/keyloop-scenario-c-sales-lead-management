package iuh.fit.backend.service;

import iuh.fit.backend.dto.request.CreateLeadRequest;
import iuh.fit.backend.dto.request.UpdateLeadRequest;
import iuh.fit.backend.dto.response.LeadDetailResponse;
import iuh.fit.backend.dto.response.LeadResponse;
import iuh.fit.backend.entity.LeadStatus;

import java.util.List;
import java.util.UUID;

public interface LeadService {

    LeadResponse createLead(CreateLeadRequest request);

    List<LeadResponse> getAllLeads();

    LeadDetailResponse getLeadById(UUID id);

    LeadResponse updateLead(UUID id, UpdateLeadRequest request);

    void deleteLead(UUID id);

    List<LeadResponse> searchLeads(String keyword);

    List<LeadResponse> filterByStatus(LeadStatus status);
}