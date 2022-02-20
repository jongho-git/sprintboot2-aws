package org.example.book.springboot.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.book.springboot.config.auth.LoginUser;
import org.example.book.springboot.config.auth.dto.SessionUser;
import org.example.book.springboot.service.posts.PostsService;
import org.example.book.springboot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    private final PostsService postsService;
//    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

//        SessionUser user= (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("googleName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
