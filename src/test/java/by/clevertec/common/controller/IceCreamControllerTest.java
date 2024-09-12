package by.clevertec.common.controller;

import by.clevertec.service.IceCreamService;
import by.clevertec.util.TestData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(IceCreamController.class)
class IceCreamControllerTest {


    @MockBean
    private IceCreamService iceCreamService;


    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldFindAllIceCreams() throws Exception {
        //given

        when(iceCreamService.getIceCreams())
                .thenReturn(List.of(TestData.generateIceCream()));

        //when
        mockMvc.perform(get("/api/v1/IceCreams"))
                .andExpect(status().isOk());

        //then
    }
}