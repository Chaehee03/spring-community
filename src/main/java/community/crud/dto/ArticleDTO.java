package community.crud.dto;

import community.crud.entity.Category;
import community.crud.entity.Comment;
import community.crud.entity.Photo;
import community.crud.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class ArticleDTO {

    private Long id;
    private User user;
    private Category category;
    private List<Comment> comments = new ArrayList<>();
    private List<Photo> photos = new ArrayList<>();
    private String title;
    private String paragraph;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int like;
    private int views;
}
