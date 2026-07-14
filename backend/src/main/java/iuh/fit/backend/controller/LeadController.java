package iuh.fit.backend.controller;

import iuh.fit.backend.dto.request.CreateLeadRequest;
import iuh.fit.backend.dto.request.UpdateLeadRequest;
import iuh.fit.backend.dto.response.ApiResponse;
import iuh.fit.backend.dto.response.LeadDetailResponse;
import iuh.fit.backend.dto.response.LeadResponse;
import iuh.fit.backend.entity.LeadStatus;
import iuh.fit.backend.service.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;
    @PostMapping
    public ResponseEntity<ApiResponse<LeadResponse>> createLead(
            @Valid @RequestBody CreateLeadRequest request) {

        LeadResponse response = leadService.createLead(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<LeadResponse>builder()
                        .success(true)
                        .message("Lead created successfully")
                        .data(response)
                        .build());
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<LeadResponse>>> getAllLeads() {

        List<LeadResponse> response = leadService.getAllLeads();

        return ResponseEntity.ok(
                ApiResponse.<List<LeadResponse>>builder()
                        .success(true)
                        .message("Leads retrieved successfully")
                        .data(response)
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LeadDetailResponse>> getLeadById(
            @PathVariable UUID id) {

        LeadDetailResponse response = leadService.getLeadById(id);

        return ResponseEntity.ok(
                ApiResponse.<LeadDetailResponse>builder()
                        .success(true)
                        .message("Lead retrieved successfully")
                        .data(response)
                        .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LeadResponse>> updateLead(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateLeadRequest request) {

        LeadResponse response = leadService.updateLead(id, request);

        return ResponseEntity.ok(
                ApiResponse.<LeadResponse>builder()
                        .success(true)
                        .message("Lead updated successfully")
                        .data(response)
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLead(
            @PathVariable UUID id) {

        leadService.deleteLead(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Lead deleted successfully")
                        .build()
        );
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<LeadResponse>>> searchLead(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                ApiResponse.<List<LeadResponse>>builder()
                        .success(true)
                        .message("Search completed")
                        .data(leadService.searchLeads(keyword))
                        .build()
        );
    }
    @GetMapping("/status")
    public ResponseEntity<ApiResponse<List<LeadResponse>>> filterByStatus(
            @RequestParam LeadStatus status) {

        return ResponseEntity.ok(
                ApiResponse.<List<LeadResponse>>builder()
                        .success(true)
                        .message("Filter completed")
                        .data(leadService.filterByStatus(status))
                        .build()
        );
    }

}