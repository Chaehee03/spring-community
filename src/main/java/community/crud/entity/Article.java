package community.crud.entity;

import community.crud.dto.article.ArticleRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "article")
@Getter @Setter
@SQLDelete(sql = "UPDATE article SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Article extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "article", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<Photo> photos = new ArrayList<>();

    private String title;

    @Column(length = 500)
    private String paragraph;

    private int likes;
    private int views;

    private boolean deleted = Boolean.FALSE;

    // DTO -> entity
    public static Article toSaveEntity(ArticleRequest articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setParagraph(articleDTO.getParagraph());
        Category category = new Category();
        category.setCategoryName(articleDTO.getCategory());
        article.setCategory(category);
        User user = new User();
        user.setUserName(articleDTO.getUser());
        article.setUser(user);
        return article;
    }
}
