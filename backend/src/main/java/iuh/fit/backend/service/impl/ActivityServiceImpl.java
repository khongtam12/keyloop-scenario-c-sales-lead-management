package iuh.fit.backend.service.impl;

import iuh.fit.backend.dto.request.CreateActivityRequest;
import iuh.fit.backend.dto.response.ActivityResponse;
import iuh.fit.backend.entity.Activity;
import iuh.fit.backend.entity.Lead;
import iuh.fit.backend.exception.ResourceNotFoundException;
import iuh.fit.backend.mapper.ActivityMapper;
import iuh.fit.backend.repository.ActivityRepository;
import iuh.fit.backend.repository.LeadRepository;
import iuh.fit.backend.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ActivityServiceImpl
        implements ActivityService {

    private final ActivityRepository activityRepository;

    private final LeadRepository leadRepository;

    private final ActivityMapper activityMapper;

    @Override
    public ActivityResponse addActivity(UUID leadId, CreateActivityRequest request) {
        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

        Activity activity = activityMapper.toEntity(request);

        activity.setLead(lead);

        Activity saved = activityRepository.save(activity);

        return activityMapper.toResponse(saved);
    }

    @Override
    public List<ActivityResponse> getTimeline(UUID leadId) {
        leadRepository.findById(leadId)
                .orElseThrow(() -> new ResourceNotFoundException("Lead not found"));

        return activityRepository

                .findByLeadIdOrderByCreatedAtDesc(leadId)

                .stream()

                .map(activityMapper::toResponse)

                .toList();
    }
}
