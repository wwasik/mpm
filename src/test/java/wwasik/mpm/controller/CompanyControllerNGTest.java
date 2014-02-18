package wwasik.mpm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.DataProvider;
import wwasik.mpm.Application;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import wwasik.mpm.model.Company;
import wwasik.mpm.repository.CompanyRepository;

/**
 * @author Wojtek
 */
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class CompanyControllerNGTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CompanyRepository repositoryMock;
    @Autowired
    @InjectMocks
    private CompanyController controller;
    private MockMvc mockMvc;
    private final Logger log = LoggerFactory.getLogger(CompanyControllerNGTest.class);

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider
    public static Object[][] companyJsonProvider() {
        return new Object[][]{
            {Json.createObjectBuilder()
                .add("name", "JakasFirma")
                .add("description", "jakas firma opis")
                .add("website", "www.jakasfirma.com")
                .add("logo", "jakies logo")
                .build(), status().isOk()},
            {Json.createObjectBuilder()
                .add("name", "JakasFirma")
                .build(), status().isOk()},
            {Json.createObjectBuilder()
                .add("name", "JakasFirma")
                .build(), status().isOk()},
            {Json.createObjectBuilder()
                .add("description", "jakas firma opis")
                .add("website", "www.jakasfirma.com")
                .add("logo", "jakies logo")
                .build(), status().isBadRequest()},
            {Json.createObjectBuilder()
                .build(), status().isBadRequest()},};
    }

    @Test(dataProvider = "companyJsonProvider")
    public void saveApi(JsonObject companyJson, ResultMatcher expectedStatus) throws Exception {
        //given
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String content = companyJson.toString();

        //when
        mockMvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON).content(content)).
                //then
                andExpect(expectedStatus);
    }

    @Test
    public void shouldCreate() throws Exception {
        //given

        //when
        controller.save(mock(Company.class));

        //then
        verify(repositoryMock, times(1)).save(any(Company.class));
    }

    @Test(dataProvider = "companyJsonProvider")
    public void updateApi(JsonObject companyJson, ResultMatcher expectedStatus) throws Exception {
        //given
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String content = companyJson.toString();

        //when
        mockMvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON).content(content)).
                //then
                andExpect(expectedStatus);
    }
    
    @Test
    public void shouldUpdate() throws Exception {
        //given

        //when
        Company companyMock = mock(Company.class);
        when(repositoryMock.findOne(any(Serializable.class))).thenReturn(companyMock);
        controller.update(companyMock);

        //then
        verify(repositoryMock, times(1)).findOne(any(Serializable.class));
        verify(repositoryMock, times(1)).save(any(Company.class));
    }

    @Test
    public void findAllApi() throws Exception {
        //given
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        List<Company> companies = new ArrayList<>();
        Company c1 = new Company();
        c1.setName("company 1 sp. z o. o.");
        companies.add(c1);
        Company c2 = new Company();
        c2.setName("company 2 sp. z o. o.");
        c2.setWebsite("www.company2.pl");
        companies.add(c2);
        Company c3 = new Company();
        c3.setName("company 3 sp. z o. o.");
        c3.setWebsite("www.company3.pl");
        c3.setDescription("jakas firma");
        companies.add(c3);
        when(repositoryMock.findAll()).thenReturn(companies);

        //when
        mockMvc.perform(get("/company"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].name", Matchers.is("company 1 sp. z o. o.")))
                .andExpect(jsonPath("$[1].name", Matchers.is("company 2 sp. z o. o.")))
                .andExpect(jsonPath("$[1].website", Matchers.is("www.company2.pl")))
                .andExpect(jsonPath("$[2].name", Matchers.is("company 3 sp. z o. o.")))
                .andExpect(jsonPath("$[2].website", Matchers.is("www.company3.pl")))
                .andExpect(jsonPath("$[2].description", Matchers.is("jakas firma")));
    }

    @Test
    public void findOneApi() throws Exception {
        //given
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Company c = new Company();
        c.setId("1");
        c.setName("company 1 sp. z o. o.");
        when(repositoryMock.findOne("1")).thenReturn(c);

        //when
        mockMvc.perform(get("/company/1"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", Matchers.is("1")))
                .andExpect(jsonPath("$.name", Matchers.is("company 1 sp. z o. o.")));
    }
}
