package pl.square.fdasearch.search.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.square.fdasearch.search.application.SearchService;
import pl.square.fdasearch.search.domain.DrugSearchResponse;
import pl.square.fdasearch.search.domain.DrugSearchResult;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@EnableAutoConfiguration(exclude= DataSourceAutoConfiguration.class)
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
    void searchForBrand() throws Exception {

        DrugSearchResponse response = mockResponse();

        Mockito.when(service.searchForBrand(Mockito.eq("apap"))).thenReturn(response);
        mvc.perform(get("/search")
                .param("brand", "apap")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }

    @Test
    void searchForManufacturer() throws Exception {

        DrugSearchResponse response = mockResponse();

        Mockito.when(service.searchForManufacturer(Mockito.eq("gsk"))).thenReturn(response);
        mvc.perform(get("/search")
                .param("manufacturer", "gsk")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }


    @Test
    void searchForBoth() throws Exception {

        DrugSearchResponse response = mockResponse();

        Mockito.when(service.searchForBrand(Mockito.eq("apap"))).thenReturn(response);
        Mockito.when(service.searchForManufacturer(Mockito.eq("gsk"))).thenReturn(response);
        mvc.perform(get("/search")
                .param("brand", "apap")
                .param("manufacturer", "gsk")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }

    @Disabled // FIXME
    @Test
    void searchWillReturnNoData() throws Exception {

        DrugSearchResponse response = mockResponse();

        Mockito.when(service.searchForBrand(Mockito.eq("apap"))).thenReturn(response);
        Mockito.when(service.searchForManufacturer(Mockito.eq("gsk"))).thenReturn(response);
        mvc.perform(get("/search")
                .param("brand", "apap")
                .param("manufacturer", "gsk")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }


    private DrugSearchResponse mockResponse() {
        DrugSearchResponse response = new DrugSearchResponse();
        DrugSearchResult result1 = new DrugSearchResult();
        result1.setApplication_number("1234");
        response.setResults(List.of(result1));
        return response;
    }

}