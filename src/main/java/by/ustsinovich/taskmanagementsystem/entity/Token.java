package by.ustsinovich.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    @Serial
    private static final long serialVersionUID = 4926097364170454514L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;

    private String refreshToken;

    private boolean isLoggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
