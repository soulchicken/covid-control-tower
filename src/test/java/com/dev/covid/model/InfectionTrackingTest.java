package com.dev.covid.model;

import com.dev.covid.DTO.InfectionTrackingDTO;
import com.dev.covid.common.BaseControllerTest;
import com.dev.covid.repository.InfectionTrackingRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

@Slf4j
@DisplayName("감염경로 테스트")
public class InfectionTrackingTest extends BaseControllerTest {

    String url = "/infectiontracking";
    
    @Autowired
    InfectionTrackingRepository infectionTrackingRepository;
    
    @Test
    @DisplayName("감염경로 전체 조회 (성공)")
    void findAllTest() throws Exception {
         List<InfectionTracking> result = infectionTrackingRepository.findAll();
        log.info("감염경로 전체 개수 : " + result.size());
    }

    @Test
    @DisplayName("포스트 저장(성공)")
    void insertPostSuccess() throws Exception {

        InfectionTrackingDTO infectionTrackingDTO = InfectionTrackingDTO
                .builder()
                .infectionTrackingName("이거 머임")
                .infectionTrackingArea("뚝섬")
                .infectionTrackingCause("모임")
                .patientPeopleId(1L)
                .build();
        String content = objectMapper.writeValueAsString(infectionTrackingDTO);
        log.info(content);

        mockMvc.perform(MockMvcRequestBuilders.post(url).content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    MockHttpServletResponse response = result.getResponse();
                    log.info(response.getContentAsString());
                });
    }
}
