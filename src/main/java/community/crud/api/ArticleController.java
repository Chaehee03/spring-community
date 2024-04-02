package community.crud.api;

import community.crud.dto.article.*;
import community.crud.entity.*;
import community.crud.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static community.crud.entity.Article.toSaveEntity;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 게시글 작성
     */
    @PostMapping("/article")
    public ArticleDTO saveArticle(@RequestBody @Valid ArticleRequest request){
        Article article = toSaveEntity(request);
        articleService.save(article);
        return ArticleDTO.toSaveDTO(article);
    }

    /**
     * 게시글 목록 조회
    */
    @GetMapping("/article")
    public Result<List<ArticleDTO>> findArticles() {
        List<Article> findArticles = articleService.findAll();
        List<ArticleDTO> collect = findArticles.stream()
                .map(ArticleDTO::toSaveDTO)
                .collect(Collectors.toList());

        return new Result<List<ArticleDTO>>(collect, collect.size());
    }

    /**
     * 게시글 단일 조회
     */
    @GetMapping("article/{id}")
    public ArticleDTO findArticle(
            @PathVariable("id") Long id) {

        Article findArticle = articleService.findOne(id);
        return ArticleDTO.toSaveDTO(findArticle);
    }

    // To Do : Spring Security와 JWT를 이용하여 회원 검증 로직 구현
//    /**
//     * 게시글 수정
//     */
//    @PutMapping("/article/update/{id}")
//    public ArticleDTO updateArticle(
//            @PathVariable("id") Long id,
//            @RequestBody @Valid ArticleRequest request) {
//
//        Article findArticle = articleService.findOne(id);
//
//        if (Objects.equals(findArticle.getUser().getUserName(), request.getUser()) && Objects.equals(findArticle.getUser().getPassword(), request.getPassword())) {
//            articleService.update(findArticle, request);
//            return ArticleDTO.toSaveDTO(findArticle);
//        }
//        return new ArticleDTO("비밀번호가 일치하지 않습니다.");
//    }
//
//    /**
//     * 게시글 삭제
//     */
//    @DeleteMapping("/article/delete/{id}")
//    public DeleteArticleResponse deleteArticle(
//            @PathVariable("id") Long id,
//            @RequestBody @Valid DeleteArticleRequest request) {
//
//        Article article = articleService.findOne(id);
//
//        if (article.getUser().getPassword().equals(request.getPassword())) {
//            articleService.delete(id);
//            return new DeleteArticleResponse(true);
//        }
//        return new DeleteArticleResponse(false);
//    }


}
