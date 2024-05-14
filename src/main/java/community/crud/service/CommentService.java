package community.crud.service;

import community.crud.dto.comment.CommentDTO;
import community.crud.entity.Article;
import community.crud.entity.Comment;
import community.crud.entity.User;
import community.crud.repository.ArticleRepository;
import community.crud.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 댓글 등록
    @Transactional
    public void writeComment(Long articleId, Comment comment){

        // 게시판 번호로 게시글 찾기
        Article findArticle = articleRepository.findById(articleId).orElseThrow(() -> {
            return new IllegalArgumentException("게시판을 찾을 수 없습니다.");
        });

        comment.setArticle(findArticle);
        commentRepository.save(comment);
    }

//    // 댓글 단일 조회
//    public Comment findOne(Long articleId, Long commentId){
//        return articleRepository
//    }

    // 댓글 목록 조회
    public List<CommentDTO> findAll(Long articleId){
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        List<CommentDTO> commentDTOs = new ArrayList<>();

        comments.forEach(s -> commentDTOs.add(CommentDTO.toSaveDTO(s)));
        return commentDTOs;
    }

//    // 댓글 수정
//    @Transactional
//    public void update(Comment comment, CommentDto commentDto){
//
//    }

    // 댓글 삭제
    @Transactional
    public String delete(Long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->{
            return new IllegalArgumentException("댓글 ID를 찾을 수 없습니다.");
        });
        commentRepository.deleteById(commentId);
        return "삭제 완료";
    }
}
