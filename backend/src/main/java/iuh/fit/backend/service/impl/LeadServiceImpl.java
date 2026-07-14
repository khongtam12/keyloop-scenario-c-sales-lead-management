package iuh.fit.backend.service.impl;

import iuh.fit.backend.dto.request.CreateLeadRequest;
import iuh.fit.backend.dto.request.UpdateLeadRequest;
import iuh.fit.backend.dto.response.ActivityResponse;
import iuh.fit.backend.dto.response.LeadDetailResponse;
import iuh.fit.backend.dto.response.LeadResponse;
import iuh.fit.backend.entity.Lead;
import iuh.fit.backend.entity.LeadStatus;
import iuh.fit.backend.exception.DuplicateEmailException;
import iuh.fit.backend.exception.ResourceNotFoundException;
import iuh.fit.backend.mapper.LeadMapper;
import iuh.fit.backend.repository.ActivityRepository;
import iuh.fit.backend.repository.LeadRepository;
import iuh.fit.backend.service.LeadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    private final ActivityRepository activityRepository;

    private final LeadMapper leadMapper;

    @Override
    public LeadResponse createLead(CreateLeadRequest request) {

        if (leadRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Duplicate email: {}", request.getEmail());
            throw new DuplicateEmailException("Email already exists.");
        }

        Lead lead = leadMapper.toEntity(request);

        lead.setStatus(LeadStatus.NEW);

        Lead saved = leadRepository.save(lead);

        log.info("Lead {} created successfully", saved.getId());
        return leadMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeadResponse> getAllLeads() {

        return leadRepository.findAll()

                .stream()

                .map(leadMapper::toResponse)

                .toList();
    }

    @Override
    public LeadDetailResponse getLeadById(UUID id) {
        Lead lead = leadRepository.findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

        List<ActivityResponse> activities = activityRepository
                .findByLeadIdOrderByCreatedAtDesc(id)
                .stream()
                .map(activity -> ActivityResponse.builder()
                        .id(activity.getId())
                        .type(activity.getType())
                        .note(activity.getNote())
                        .createdAt(activity.getCreatedAt())
                        .build())
                .toList();

        LeadDetailResponse response = leadMapper.toDetailResponse(lead);
        response.setActivities(activities);
        return response;
    }

    @Override
    public LeadResponse updateLead(UUID id, UpdateLeadRequest request) {

        Lead lead = leadRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lead not found"));

        if (request.getEmail() != null
                && !request.getEmail().equals(lead.getEmail())
                && leadRepository.findByEmail(request.getEmail()).isPresent()) {

            throw new DuplicateEmailException("Email already exists.");
        }

        lead.setCustomerName(request.getCustomerName());
        lead.setEmail(request.getEmail());
        lead.setPhone(request.getPhone());
        lead.setStatus(request.getStatus());

        Lead updated = leadRepository.save(lead);

        return leadMapper.toResponse(updated);
    }

    @Override
    public void deleteLead(UUID id) {
        log.info("Deleting lead {}", id);

        Lead lead = leadRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Lead not found"));

        leadRepository.delete(lead);

        log.info("Lead {} deleted successfully", id);
    }

    @Override
    public List<LeadResponse> searchLeads(String keyword) {
        log.info("Searching leads with keyword: {}", keyword);
        return leadRepository

                .findByCustomerNameContainingIgnoreCase(keyword)

                .stream()

                .map(leadMapper::toResponse)

                .toList();
    }

    @Override
    public List<LeadResponse> filterByStatus(LeadStatus status) {
        log.info("Filtering leads by status: {}", status);
        return leadRepository

                .findByStatus(status)

                .stream()

                .map(leadMapper::toResponse)

                .toList();
    }
}
