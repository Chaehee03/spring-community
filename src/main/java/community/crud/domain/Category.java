package community.crud.domain;

import community.crud.domain.Article;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @OneToMany(mappedBy = "category")
    private List<Article> articles = new ArrayList<>();

    private String categoryName;

}
