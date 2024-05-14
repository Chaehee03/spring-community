package community.crud.dto.comment;

import community.crud.dto.article.ArticleDTO;
import community.crud.entity.Article;
import community.crud.entity.Comment;
import community.crud.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String paragraph;
    private String writer;

    // entity -> DTO
    public static CommentDTO toSaveDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setParagraph(comment.getParagraph());
        User user = comment.getUser();
        commentDTO.setWriter(user.getUserName());
        return commentDTO;
    }
}