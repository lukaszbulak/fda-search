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
import pl.square.fdasearch.search.domain.ErrorResult;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
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
                //.andExpect(content().string("both arguments cannot be empty"))
                ;
    }

    @Test
    void searchForBrand() throws Exception {
        // given
        DrugSearchResponse response = mockResponse1234();
        Mockito.when(service.searchForBrand(eq("apap"))).thenReturn(response);
        // when
        mvc.perform(get("/search")
                .param("brand", "apap")
                )
                .andDo(print())
        // then
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }

    @Test
    void searchForManufacturer() throws Exception {
        // given
        DrugSearchResponse response = mockResponse1234();
        Mockito.when(service.searchForManufacturer(eq("gsk"))).thenReturn(response);

        mvc.perform(get("/search")
                .param("manufacturer", "gsk")
        )
                .andDo(print())
        // then
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }


    @Test
    void searchForBoth() throws Exception {
        // given
        DrugSearchResponse response = mockResponse1234();
        Mockito.when(service.searchForBoth(eq("gsk"), eq("apap"))).thenReturn(response);
        // when
        mvc.perform(get("/search")
                .param("brand", "apap")
                .param("manufacturer", "gsk")
        )
                .andDo(print())
        // then
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }

    @Test
    void searchWillReturnNoData() throws Exception {

        DrugSearchResponse emptyResponse = new DrugSearchResponse();
        ErrorResult error = new ErrorResult();
        error.setCode("NOT_FOUND");
        emptyResponse.setError(error);
        Mockito.when(service.searchForBrand(eq("notexisting"))).thenReturn(emptyResponse);
        mvc.perform(get("/search")
                .param("brand", "notexisting")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("NOT_FOUND")));
    }


    private DrugSearchResponse mockResponse1234() {
        DrugSearchResponse response = new DrugSearchResponse();
        DrugSearchResult result1 = new DrugSearchResult();
        result1.setApplication_number("1234");
        response.setResults(List.of(result1));
        return response;
    }

}