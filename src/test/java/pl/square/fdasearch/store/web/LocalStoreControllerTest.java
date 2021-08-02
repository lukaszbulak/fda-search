package pl.square.fdasearch.store.web;

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
import pl.square.fdasearch.store.application.LocalStoreService;
import pl.square.fdasearch.store.domain.Drug;
import pl.square.fdasearch.store.repository.LocalDrugRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LocalStoreControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private LocalDrugRepository repository;


    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void list() throws Exception {

//        DrugSearchResponse response = mockResponse1234();
        Drug drug = new Drug();
        drug.setApplication_number("1234");
        Iterable<Drug> all = List.of(drug);
        Mockito.when(repository.findAll()).thenReturn(all);

        mvc.perform(get("/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1234")));
    }

    @Disabled
    @Test
    void store() throws Exception {
    }

}
