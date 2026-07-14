package iuh.fit.backend.service;

import iuh.fit.backend.dto.request.CreateActivityRequest;
import iuh.fit.backend.dto.response.ActivityResponse;

import java.util.List;
import java.util.UUID;

public interface ActivityService {

    ActivityResponse addActivity(
            UUID leadId,
            CreateActivityRequest request);

    List<ActivityResponse> getTimeline(UUID leadId);
}