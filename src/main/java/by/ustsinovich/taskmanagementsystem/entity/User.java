package by.ustsinovich.taskmanagementsystem.entity;

import by.ustsinovich.taskmanagementsystem.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = -2775400638652609785L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String email;

    private String password;

    private String firstName;

    private String patronymic;

    private String lastName;

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @OneToMany(mappedBy = "initiator")
    private List<Task> initiated;

    @ManyToMany
    @JoinTable(
            name = "user_executed",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "executed_id")
    )
    @Transient
    private List<Task> executed;

    @OneToMany(mappedBy = "author")
    @Transient
    private List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

}
