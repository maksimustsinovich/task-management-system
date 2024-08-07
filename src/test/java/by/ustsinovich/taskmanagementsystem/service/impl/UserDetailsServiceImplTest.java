package by.ustsinovich.taskmanagementsystem.service.impl;

import by.ustsinovich.taskmanagementsystem.entity.User;
import by.ustsinovich.taskmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private User user;

    @BeforeEach
    void setup() {
        user = new User();
        user.setEmail("gmail@gmail.com");
        user.setPassword("password");
        user.setPatronymic("test");
        user.setLastName("test");
        user.setFirstName("test");
    }

    @Test
    void loadUserByUsername_ExistingUser() {
        userRepository.save(user);

        when(userRepository.findByEmail("gmail@gmail.com")).thenReturn(Optional.ofNullable(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("gmail@gmail.com");

        assertNotNull(userDetails);
        assertEquals("gmail@gmail.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
    }

    @Test
    void loadUserByUsername_NonExistingUser() {
        when(userRepository.findByEmail("gmail1@gmail.com")).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("gmail1@gmail.com")
        );
        assertEquals("User with email `gmail1@gmail.com` not found", exception.getMessage());
    }


}