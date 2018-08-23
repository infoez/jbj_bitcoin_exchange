package configuration;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-context/*.xml"})
@WebAppConfiguration
public class TestConfiguration {

    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /*@Test
    public void test1() throws Exception {
        mockMvc.perform(post("/account/idValidationService.json").param("userName", "이용우")
                .header("x-core-with", "XMLHttpRequest")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(handler().handlerType(AccountController.class))
                .andDo(MockMvcResultHandlers.print());
    }*/


}
