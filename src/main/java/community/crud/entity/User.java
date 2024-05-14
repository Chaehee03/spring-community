package community.crud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @NotNull
    private String userName;
    private String password;
    private String email;
}
