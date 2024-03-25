package community.crud.entity;

import community.crud.dto.ArticleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Article extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    @Column(length = 20, nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<Photo> photos = new ArrayList<>();

    private String title;

    @Column(length = 500)
    private String paragraph;

    private int like;
    private int views;

    // DTO -> entity
    public static Article toSaveEntity(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setUser(articleDTO.getUser());
        article.setTitle(articleDTO.getTitle());
        article.setParagraph(articleDTO.getParagraph());
        article.setCategory(article.getCategory());
        article.setLike(0);
        article.setViews(0);
        article.setComments(articleDTO.getComments());
        article.setPhotos(articleDTO.getPhotos());
        return article;
    }

    public void delete() {
    }
}
