package project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.controller.AdminController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
public class AdminControllerTests {

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void adminCreateNewUser_successful() throws Exception {

        ChatUser user = new ChatUser();
        user.setUserId(UUID.fromString("126a9246-9ad2-46a7-98d0-c1ec00d873c1"));
        user.setUsername("JONAS123");
        user.setIsActive(true);
        user.setIsAdmin(false);

        Mockito.when(userRepository.findUserByUsername("JONAS123")).thenReturn(null);

        mockMvc.perform(post("/admin/user")
                        .content(objectMapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        verify(userRepository, times(1)).createNewUser(any(UUID.class), eq(user.getUsername()), eq(user.getIsActive()), eq(user.getIsAdmin()), any(LocalDateTime.class));
    }

    @Test
    void adminCreateNewUser_conflict() throws Exception {

        ChatUser user = new ChatUser();
        user.setUserId(UUID.fromString("126a9246-9ad2-46a7-98d0-c1ec00d873c1"));
        user.setUsername("JONAS123");
        user.setIsActive(true);
        user.setIsAdmin(false);

        Mockito.when(userRepository.findUserByUsername("JONAS123")).thenReturn("JONAS123");

        mockMvc.perform(post("/admin/user")
                        .content(objectMapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isConflict())
                .andExpect(content().contentType("application/json"));

        verify(userRepository, times(1)).findUserByUsername(user.getUsername());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void adminDeleteUser_successful() throws Exception {

        ChatUser user = new ChatUser();
        user.setUserId(UUID.fromString("126a9246-9ad2-46a7-98d0-c1ec00d873c1"));
        user.setUsername("JONAS123");
        user.setIsActive(true);
        user.setIsAdmin(false);

        Mockito.when(userRepository.findUserById(UUID.fromString("126a9246-9ad2-46a7-98d0-c1ec00d873c1")))
                .thenReturn("126a9246-9ad2-46a7-98d0-c1ec00d873c1");

        mockMvc.perform(delete("/admin/user").param("userID", user.getUserId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        verify(userRepository, times(1)).findUserById(user.getUserId());
        verify(messageRepository, times(1)).updateMessageUserToAnonymous(eq(user.getUserId()), any(LocalDateTime.class));
        verify(userRepository, times(1)).deleteExistingUser(user.getUserId());
    }

    @Test
    void adminDeleteUser_conflict() throws Exception {

        ChatUser user = new ChatUser();
        user.setUserId(UUID.fromString("126a9246-9ad2-46a7-98d0-c1ec00d873c1"));
        user.setUsername("JONAS123");
        user.setIsActive(true);
        user.setIsAdmin(false);

        Mockito.when(userRepository.findUserById(UUID.fromString("126a9246-9ad2-46a7-98d0-c1ec00d873c1")))
                .thenReturn(null);

        mockMvc.perform(delete("/admin/user").param("userID", user.getUserId().toString()))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"));

        verify(userRepository, times(1)).findUserById(user.getUserId());
        verifyNoMoreInteractions(userRepository);
        verifyNoMoreInteractions(messageRepository);
    }


    @Test
    void adminGetStatistics_successful() throws Exception {

        Mockito.when(messageRepository.getStatistics())
                .thenReturn(List.of(getMockStatistics()));

        mockMvc.perform(get("/admin/statistics")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].username").value("TOMAS123"))
                .andExpect(jsonPath("$[0].messageCount").value(1))
                .andExpect(jsonPath("$[0].lastMessageText").value("HELLO"))
                .andExpect(jsonPath("$[0].avgMessageLength").value(12.2));

        verify(messageRepository, times(1)).getStatistics();
    }

    private Statistics getMockStatistics() {
        return new Statistics() {
            @Override
            public String getUsername() {
                return "TOMAS123";
            }

            @Override
            public Integer getMessageCount() {
                return 1;
            }

            @Override
            public LocalDateTime getFirstMessageDate() {
                return LocalDateTime.now();
            }

            @Override
            public LocalDateTime getLastMessageDate() {
                return LocalDateTime.now();
            }

            @Override
            public Double getAvgMessageLength() {
                return 12.2;
            }

            @Override
            public String getLastMessageText() {
                return "HELLO";
            }
        };
    }

}
