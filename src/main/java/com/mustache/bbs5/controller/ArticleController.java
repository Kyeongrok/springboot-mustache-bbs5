package com.mustache.bbs5.controller;

import com.mustache.bbs5.domain.Article;
import com.mustache.bbs5.domain.ArticleDto;
import com.mustache.bbs5.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("")
    public String list(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "list";
    }

    @GetMapping("/new")
    public String createArticle() {
        return "new";
    }

    @PostMapping("")
    public String add(ArticleDto articleDto) {
        log.info(articleDto.getTitle());
        Article article = articleRepository.save(articleDto.toEntity());
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        log.info("id:{}", id);

        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            model.addAttribute("article", article.get());
            return "show";
        } else {
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        log.info("id:{}", id);

        Optional<Article> article = articleRepository.findById(id);
        if (article.isPresent()) {
            model.addAttribute("article", article.get());
            return "edit";
        } else {
            return "error";
        }
    }

    @PostMapping("/{id}/update")
    public String update(ArticleDto articleDto, Model model) {
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", savedArticle);
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }

}
