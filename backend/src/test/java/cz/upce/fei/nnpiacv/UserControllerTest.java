package cz.upce.fei.nnpiacv;

import cz.upce.fei.nnpiacv.controller.UserController;
import cz.upce.fei.nnpiacv.domain.Role;
import cz.upce.fei.nnpiacv.domain.User;
import cz.upce.fei.nnpiacv.dto.UserRequestDto;
import cz.upce.fei.nnpiacv.dto.UserResponseDto;
import cz.upce.fei.nnpiacv.exception.UserAlreadyExistsException;
import cz.upce.fei.nnpiacv.exception.UserNotFoundException;
import cz.upce.fei.nnpiacv.repository.RoleRepository;
import cz.upce.fei.nnpiacv.service.JwtService;
import cz.upce.fei.nnpiacv.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = UserController.class) // Testujeme pouze UserController
@AutoConfigureMockMvc(addFilters = false)  // Zakáže bezpečnostní filtry
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private RoleRepository roleRepository;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String validToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIiLCJpYXQiOjE3NDIyMDU3ODEsImV4cCI6MTc0MjIwOTM4MX0.dNGc7gbbs9lgauDOnRYa43dd4GoakRfFrBDNLUNVJL4";

    @Test
    void testGetUserById() throws Exception {
        // Příprava testovacích dat
        Role role = new Role("USER_ROLE");
        role.setId(1L);
        User user = new User("john@example.com", "password123", role);
        user.setId(1L);

        // Mockování UserService (vrací User objekt)
        when(userService.findUser(1L)).thenReturn(user);

        UserResponseDto expectedResponse = new UserResponseDto(user.getId(), user.getEmail(), user.getPassword());

        // 🚀 Spuštění testu a výpis odpovědi
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1")
                        .header("Authorization", validToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()) // Standardní Spring výpis
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(jsonPath("$.email").value(expectedResponse.getEmail()))
                .andExpect(jsonPath("$.password").value(expectedResponse.getPassword()))
                .andReturn(); // 📌 Vrací MvcResult s odpovědí

        // 🔥 Vlastní výpis odpovědi do konzole
        System.out.println("🔥 [TEST RESPONSE] " + result.getResponse().getContentAsString());
    }

    @Test
    void testGetUserById_NotFound() throws Exception {
        Long invalidId = 99L;

        when(userService.findUser(invalidId)).thenThrow(new UserNotFoundException(invalidId));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/" + invalidId)
                        .header("Authorization", validToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("User with ID " + invalidId + " not found."));
    }

    @Test
    void testCreateUser_Success() throws Exception {
        // 📌 Příprava testovacích dat
        Role role = new Role("USER_ROLE");
        role.setId(1L);
        User user = new User("newuser@example.com", "password123", role);
        user.setId(2L);

        UserRequestDto userRequest = new UserRequestDto("newuser@example.com", "password123");
        UserResponseDto userResponse = new UserResponseDto(user.getId(), user.getEmail(), user.getPassword());

        // 📌 Mockování RoleRepository a UserService
        when(roleRepository.findByName("USER_ROLE")).thenReturn(role);
        when(userService.createUser(any(User.class))).thenReturn(user);

        // 📌 Převedení na JSON
        String userJson = objectMapper.writeValueAsString(userRequest);

        // 🚀 Spuštění testu
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .header("Authorization", validToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(userResponse.getId()))
                .andExpect(jsonPath("$.email").value(userResponse.getEmail()));
    }

    @Test
    void testCreateUser_Conflict() throws Exception {
        // 📌 Příprava testovacích dat
        Role role = new Role("USER_ROLE");
        role.setId(1L);
        UserRequestDto userRequest = new UserRequestDto("existinguser@example.com", "password123");

        // 📌 Simulujeme, že uživatel již existuje
        when(roleRepository.findByName("USER_ROLE")).thenReturn(role);
        when(userService.createUser(any(User.class))).thenThrow(new UserAlreadyExistsException(userRequest.getEmail()));

        // 📌 Převedení na JSON
        String userJson = objectMapper.writeValueAsString(userRequest);

        // 🚀 Spuštění testu
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .header("Authorization", validToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andDo(print())
                .andExpect(status().isConflict()) // Ověříme, že dostaneme 409
                .andExpect(content().string("User with email existinguser@example.com already exists."));
    }

}
