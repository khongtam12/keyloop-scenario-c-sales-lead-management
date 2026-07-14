package iuh.fit.backend.service;

import iuh.fit.backend.dto.request.CreateActivityRequest;
import iuh.fit.backend.dto.response.ActivityResponse;
import iuh.fit.backend.entity.Activity;
import iuh.fit.backend.entity.Lead;
import iuh.fit.backend.mapper.ActivityMapper;
import iuh.fit.backend.repository.ActivityRepository;
import iuh.fit.backend.repository.LeadRepository;
import iuh.fit.backend.service.impl.ActivityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

    @Mock
    private ActivityRepository activityRepository;

    @Mock
    private LeadRepository leadRepository;

    @Mock
    private ActivityMapper activityMapper;

    @InjectMocks
    private ActivityServiceImpl activityService;

    @Test
    void shouldAddActivity() {

        UUID id = UUID.randomUUID();

        Lead lead = new Lead();

        CreateActivityRequest request = new CreateActivityRequest();

        Activity activity = new Activity();

        ActivityResponse response = ActivityResponse.builder().build();

        when(leadRepository.findById(id))
                .thenReturn(Optional.of(lead));

        when(activityMapper.toEntity(request))
                .thenReturn(activity);

        when(activityRepository.save(activity))
                .thenReturn(activity);

        when(activityMapper.toResponse(activity))
                .thenReturn(response);

        ActivityResponse result =
                activityService.addActivity(id, request);

        assertNotNull(result);
    }
}