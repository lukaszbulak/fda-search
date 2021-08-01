package pl.square.fdasearch.search.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.square.fdasearch.search.application.SearchService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)
class SearchControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private SearchService service;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void searchWillFailWithNoArguments() throws Exception {
        mvc.perform(get("/search"))
                //.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("both arguments cannot be empty"));
    }

    @Test
    void searchWillForBrand() throws Exception {

        Mockito.when(service.searchForBrand(Mockito.anyString())).thenReturn("gsk response");
        mvc.perform(get("/search")
                .param("brand", "gsk")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("gsk")));
    }

}