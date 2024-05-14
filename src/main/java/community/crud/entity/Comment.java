package community.crud.entity;

import community.crud.dto.article.ArticleRequest;
import community.crud.dto.comment.CommentRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private String paragraph;
    private Long parentCommentId;

    // DTO -> entity
    public static Comment toSaveEntity(CommentRequest commentDTO){
        Comment comment = new Comment();
        comment.setParagraph(commentDTO.getParagraph());
        User user = new User();
        user.setUserName(commentDTO.getWriter());
        comment.setUser(user);
        return comment;
    }

}
