package wwasik.mpm.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import org.hamcrest.Matchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import wwasik.mpm.model.Employee;
import wwasik.mpm.model.Rank;
import wwasik.mpm.repository.EmployeeRepository;

/**
 * @author Wojtek
 */
public class EmployeeControllerNGTest {

    @Mock
    private EmployeeRepository repositoryMock;
    @Autowired
    @InjectMocks
    private EmployeeController controller;
    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider
    public static Object[][] employeeJsonProvider() {
        return new Object[][]{
            {Json.createObjectBuilder()
                .add("name", "Jan")
                .add("surname", "Kowalski")
                .add("rank", "MANAGER")
                .add("mail", "jkowalski@jakasfirma.com")
                .add("phone", "41 123 123 12")
                .add("additionalInfo", "jakies informacje dodatkowe")
                .build(), status().isOk()},
            {Json.createObjectBuilder()
                .add("name", "Jan")
                .add("surname", "Kowalski")
                .build(), status().isOk()},
            {Json.createObjectBuilder()
                .add("name", "Jan")
                .build(), status().isBadRequest()},
            {Json.createObjectBuilder()
                .add("surname", "Kowalski")
                .build(), status().isBadRequest()},
            {Json.createObjectBuilder()
                .build(), status().isBadRequest()},
        };
    }

    @Test(dataProvider = "employeeJsonProvider")
    public void saveApi(JsonObject companyJson, ResultMatcher expectedStatus) throws Exception {
        //given
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String content = companyJson.toString();

        //when
        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(content)).
                //then
                andExpect(expectedStatus);
    }

    @Test
    public void shouldCreate() throws Exception {
        //given

        //when
        controller.save(mock(Employee.class));

        //then
        verify(repositoryMock, times(1)).save(any(Employee.class));
    }

    @Test(dataProvider = "employeeJsonProvider")
    public void updateApi(JsonObject companyJson, ResultMatcher expectedStatus) throws Exception {
        //given
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        String content = companyJson.toString();

        //when
        mockMvc.perform(post("/employee").contentType(MediaType.APPLICATION_JSON).content(content)).
                //then
                andExpect(expectedStatus);
    }
    
    @Test
    public void shouldUpdate() throws Exception {
        //given

        //when
        Employee companyMock = mock(Employee.class);
        when(repositoryMock.findOne(any(Serializable.class))).thenReturn(companyMock);
        controller.update(companyMock);

        //then
        verify(repositoryMock, times(1)).findOne(any(Serializable.class));
        verify(repositoryMock, times(1)).save(any(Employee.class));
    }

    @Test
    public void findAllApi() throws Exception {
        //given
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        List<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee();
        e1.setName("Jan");
        e1.setSurname("Kowalski");
        employees.add(e1);
        Employee e2 = new Employee();
        e2.setName("Adam");
        e2.setSurname("Nowak");
        employees.add(e2);
        Employee e3 = new Employee();
        e3.setName("Grzegorz");
        e3.setSurname("Brzęczyszczykiewicz");
        e3.setRank(Rank.MANAGER);
        employees.add(e3);
        when(repositoryMock.findAll()).thenReturn(employees);

        //when
        mockMvc.perform(get("/employee"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Jan")))
                .andExpect(jsonPath("$[0].surname", Matchers.is("Kowalski")))
                .andExpect(jsonPath("$[1].name", Matchers.is("Adam")))
                .andExpect(jsonPath("$[1].surname", Matchers.is("Nowak")))
                .andExpect(jsonPath("$[2].name", Matchers.is("Grzegorz")))
                .andExpect(jsonPath("$[2].surname", Matchers.is("Brzęczyszczykiewicz")))
                .andExpect(jsonPath("$[2].rank", Matchers.is("MANAGER")));
    }

    @Test
    public void findOneApi() throws Exception {
        //given
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Employee e = new Employee();
        e.setId("1");
        e.setName("Jan");
        e.setSurname("Kowalski");
        when(repositoryMock.findOne("1")).thenReturn(e);

        //when
        mockMvc.perform(get("/employee/1"))
                //then
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", Matchers.is("1")))
                .andExpect(jsonPath("$.name", Matchers.is("Jan")))
                .andExpect(jsonPath("$.surname", Matchers.is("Kowalski")));
    }

}