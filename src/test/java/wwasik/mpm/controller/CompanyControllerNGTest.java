package wwasik.mpm.controller;

import com.mongodb.Mongo;
import javax.json.Json;
import javax.json.JsonObject;
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
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.DataProvider;
import wwasik.mpm.Application;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.repository.MongoRepository;
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
                .add("description", "jakas firma opis")
                .add("website", "www.jakasfirma.com")
                .add("logo", "jakies logo")
                .build(), status().isBadRequest()},
            {Json.createObjectBuilder()
                .build(), status().isBadRequest()},
        };
    }

    @Test(dataProvider = "companyJsonProvider", enabled = false)
    public void apiTest(JsonObject companyJson, ResultMatcher expectedStatus) throws Exception {
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
        controller.create(mock(Company.class));
        
        //then
//        verify(repositoryMock, times(1)).save(any(Iterable.class));
    }
}
