package wwasik.mpm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import wwasik.mpm.Application;

/**
 * @author Wojtek
 */
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class CompanyControllerNGTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeMethod
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testCreate() throws Exception {
        //given
        Map<String, String> json = new HashMap<>();
        json.put("name", "IBM");
        json.put("description", "jakas firma");
        json.put("website", "www.jakasstrona.pl");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(json);
        System.out.println(jsonStr);

        //when
        mockMvc.perform(post("/company").contentType(MediaType.APPLICATION_JSON).content(jsonStr)).
                andExpect(status().isOk());

        //then
    }

}
