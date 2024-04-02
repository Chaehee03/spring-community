package community.crud.dto.article;

import community.crud.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String user;
    private String category;
//    private List<Comment> comments = new ArrayList<>();
//    private List<Photo> photos = new ArrayList<>();
    private String title;
    private String paragraph;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int likes;
    private int views;

    private String msg;

    public ArticleDTO(String msg) {
        this.msg = msg;
    }

    // entity -> DTO
    public static ArticleDTO toSaveDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        User user = article.getUser();
        articleDTO.setUser(user.getUserName());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setParagraph(article.getParagraph());
        articleDTO.setCategory(article.getCategory().getCategoryName());
        articleDTO.setLikes(article.getLikes());
        articleDTO.setViews(article.getViews());
        articleDTO.setCreatedAt(article.getCreatedAt());
        articleDTO.setUpdatedAt(article.getUpdatedAt());
//        articleDTO.setComments(article.getComments());
//        articleDTO.setPhotos(article.getPhotos());
        return articleDTO;
    }

}

