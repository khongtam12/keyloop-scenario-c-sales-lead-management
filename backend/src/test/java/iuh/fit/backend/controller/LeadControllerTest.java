package iuh.fit.backend.controller;

import iuh.fit.backend.dto.response.LeadResponse;
import iuh.fit.backend.service.LeadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LeadController.class)
class LeadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeadService leadService;

    @Test
    void shouldReturnAllLeads() throws Exception {

        when(leadService.getAllLeads())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/leads"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateLead() throws Exception {

        when(leadService.createLead(any()))
                .thenReturn(LeadResponse.builder().build());

        mockMvc.perform(post("/api/leads")
                        .contentType(APPLICATION_JSON)
                        .content("""
                        {
                          "customerName":"John",
                          "email":"john@gmail.com",
                          "phone":"0123456789"
                        }
                        """))
                .andExpect(status().isCreated());
    }
}