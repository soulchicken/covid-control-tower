package com.dev.covid.model;

import com.dev.covid.DTO.InfectionTrackingDTO;
import com.dev.covid.common.BaseControllerTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Slf4j
@DisplayName("감염경로 테스트")
public class InfectionTrackingTest extends BaseControllerTest {
    @Test
    @DisplayName("포스트 저장(성공)")
    void insertPostSuccess() throws Exception {
        String url = "/infectiontracking";
//        {
//            "infectionTrackingName":"이거 머임",
//                "infectionTrackingArea":"뚝섬",
//                "infectionTrackingDate":"2022-06-24",
//                "infectionTrackingCause":"모임",
//                "patientPeopleId":1
//        }
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
