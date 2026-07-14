package iuh.fit.backend.service;

import iuh.fit.backend.dto.request.CreateLeadRequest;
import iuh.fit.backend.dto.response.LeadResponse;
import iuh.fit.backend.entity.Lead;
import iuh.fit.backend.exception.DuplicateEmailException;
import iuh.fit.backend.exception.ResourceNotFoundException;
import iuh.fit.backend.mapper.LeadMapper;
import iuh.fit.backend.repository.LeadRepository;
import iuh.fit.backend.repository.SalesPersonRepository;
import iuh.fit.backend.service.impl.LeadServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeadServiceImplTest {
    @Mock
    private LeadRepository leadRepository;

    @Mock
    private SalesPersonRepository salesPersonRepository;

    @Mock
    private LeadMapper leadMapper;

    @InjectMocks
    private LeadServiceImpl leadService;

    @Test
    void shouldCreateLeadSuccessfully() {

        CreateLeadRequest request = new CreateLeadRequest();
        request.setCustomerName("John");
        request.setEmail("john@gmail.com");

        Lead lead = new Lead();

        LeadResponse response = new LeadResponse();

        when(leadRepository.findByEmail(any()))
                .thenReturn(Optional.empty());

        when(leadMapper.toEntity(any()))
                .thenReturn(lead);

        when(leadRepository.save(any()))
                .thenReturn(lead);

        when(leadMapper.toResponse(any()))
                .thenReturn(response);

        LeadResponse result = leadService.createLead(request);

        assertNotNull(result);

        verify(leadRepository).save(any());
    }

    @Test
    void shouldThrowDuplicateEmailException() {

        CreateLeadRequest request = new CreateLeadRequest();

        request.setEmail("abc@gmail.com");

        when(leadRepository.findByEmail(any()))
                .thenReturn(Optional.of(new Lead()));

        assertThrows(
                DuplicateEmailException.class,
                () -> leadService.createLead(request)
        );
    }

    @Test
    void shouldThrowResourceNotFound() {

        UUID id = UUID.randomUUID();

        when(leadRepository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> leadService.getLeadById(id)
        );
    }
}
