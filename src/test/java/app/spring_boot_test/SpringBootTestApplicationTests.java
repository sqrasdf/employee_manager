package app.spring_boot_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ApiControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldAddEmployee() throws Exception {
        String employeeJson = """
            {
                "name": "Jan",
                "surname": "Kamiński",
                "salary": 3000.0,
                "birthYear": 1990,
                "condition": "present",
                "groupId": 1
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jan"));
    }

    @Test
    void shouldDeleteEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetAllEmployeesAsCsv() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/csv"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN + "; charset=UTF-8"));
    }

    @Test
    void shouldGetAllGroups() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/group"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldAddGroup() throws Exception {
        String groupJson = """
            {
                "id": 5,
                "name": "Support",
                "capacity": 5
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(groupJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Support"));
    }

    @Test
    void shouldAddGroupRating() throws Exception {
        String ratingJson = """
            {
                "groupId": 1,
                "rating": 6
            }
        """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/rating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ratingJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Rating added."));
    }

    @Test
    void shouldGetGroupFillPercentage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/group/1/fill"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN + "; charset=UTF-8"));
    }

    @Test
    void shouldDeleteGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/group/2"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldGetEmployeesByGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/group/1/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


}
