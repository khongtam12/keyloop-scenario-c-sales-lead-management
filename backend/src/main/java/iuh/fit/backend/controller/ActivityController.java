package iuh.fit.backend.controller;

import iuh.fit.backend.dto.request.CreateActivityRequest;
import iuh.fit.backend.dto.response.ActivityResponse;
import iuh.fit.backend.dto.response.ApiResponse;
import iuh.fit.backend.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leads/{leadId}/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    @PostMapping
    public ResponseEntity<ApiResponse<ActivityResponse>> addActivity(
            @PathVariable UUID leadId,
            @Valid @RequestBody CreateActivityRequest request) {

        ActivityResponse response =
                activityService.addActivity(leadId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<ActivityResponse>builder()
                        .success(true)
                        .message("Activity created successfully")
                        .data(response)
                        .build());
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<ActivityResponse>>> getTimeline(
            @PathVariable UUID leadId) {

        return ResponseEntity.ok(
                ApiResponse.<List<ActivityResponse>>builder()
                        .success(true)
                        .message("Timeline retrieved successfully")
                        .data(activityService.getTimeline(leadId))
                        .build()
        );
    }
}
