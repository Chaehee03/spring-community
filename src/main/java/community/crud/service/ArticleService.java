package community.crud.service;

import community.crud.dto.article.ArticleRequest;
import community.crud.entity.Article;
import community.crud.entity.Category;
import community.crud.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 게시글 저장
    @Transactional
    public void save(Article article) {
        articleRepository.save(article);
    }

    // 게시글 단일 조회
    public Article findOne(Long articleId) {
        return articleRepository.findById(articleId).orElse(null);
    }

    // 게시글 목록 조회
    public List<Article> findAll() {
        return articleRepository.findAll();
    }


    // 게시글 수정
    @Transactional
    public void update(Article article, ArticleRequest request) {
        article.setTitle(request.getTitle());
        article.setParagraph(request.getParagraph());
        Category category = article.getCategory();
        category.setCategoryName(request.getCategory());
    }

    // 게시글 삭제
    @Transactional
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
