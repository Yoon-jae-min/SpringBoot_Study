package com.example.demo.service;

import com.example.demo.dto.ArticleForm;
import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm test){
        Article article = test.toEntity();
        Article target = articleRepository.findById(article.getId()).orElse(null);
        return (target == null) ? articleRepository.save(article) : null;
    }

    public Article update(Long id, ArticleForm test){
        Article article = test.toEntity();

        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || !id.equals(article.getId())){
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        article.patch(target);
        return articleRepository.save(article);
    }

    public Article delete(Long id){
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null){
            return null;
        }

        articleRepository.delete(target);
        return target;
    }
}
