package community.crud.api;

import community.crud.dto.article.*;
import community.crud.dto.comment.CommentDTO;
import community.crud.dto.comment.CommentRequest;
import community.crud.entity.Article;
import community.crud.entity.Comment;
import community.crud.entity.User;
import community.crud.service.ArticleService;
import community.crud.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static community.crud.dto.comment.CommentDTO.toSaveDTO;
import static community.crud.entity.Article.toSaveEntity;
import static community.crud.entity.Comment.toSaveEntity;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final ArticleService articleService;
    private final CommentService commentService;

    /**
     * 게시글 댓글 등록
     */
    @PostMapping("/article/{article-id}/comment")
    public CommentDTO writeComment(
            @PathVariable("article-id") Long articleId,
            @RequestBody @Valid CommentRequest request) {

        Comment comment = toSaveEntity(request);

        commentService.writeComment(articleId, comment);
        return CommentDTO.toSaveDTO(comment);

    }

    /**
     * 게시글 댓글 단일 조회
     */

    /**
     * 게시글 댓글 목록 조회
     */
    @GetMapping("/article/{article-id}/comment")
    public Result<List<CommentDTO>> findComments(
            @PathVariable("article-id") Long articleId){

        List<CommentDTO> commentDTOS = commentService.findAll(articleId);
        return new Result<List<CommentDTO>>(commentDTOS, commentDTOS.size());
    }

    /**
     * 게시글 수정
     */

    /**
     * 게시글 삭제
     */
    @DeleteMapping("/article/{article-id}/comment/{comment-id}")
    public DeleteArticleResponse deleteComment(
            @PathVariable("article-id") Long articleId,
            @PathVariable("comment-id") Long commentId,
            @RequestBody @Valid DeleteArticleRequest request
            ){
        commentService.delete(commentId);
        return new DeleteArticleResponse(true);
    }
}
