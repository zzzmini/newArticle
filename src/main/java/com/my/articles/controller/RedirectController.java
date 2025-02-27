package com.my.articles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/mapping")

public class RedirectController {
    @GetMapping("")
    public String testMain() {
        return "/test/testMain";
    }

    // 기본 페이지로 이동
    @GetMapping("page")
    public String view() {
        return "/test/page";
    }

    // RequestMapping 사용
    @RequestMapping(value = "requestMapping",
            method = RequestMethod.GET)
    public String requestMapping(Model model) {
        String msg = "RequestMapping";
        model.addAttribute("msg", msg);
        return "/test/page";
    }

    @GetMapping("modelAndView")
    public ModelAndView modelAndView(Model model) {
        String msg = "ModelAndView";
        model.addAttribute("msg", msg);
//        return new ModelAndView("redirect:page");
        return new ModelAndView("/test/page");
    }

    @GetMapping("redirectViewFirst")
    public RedirectView redirectViewFirst(
            RedirectAttributes redirectAttributes
    ) {
        String msg = "RedirectView 1";
        redirectAttributes
                .addFlashAttribute("msg", msg);
        return new RedirectView("/mapping/page");
    }

    @GetMapping("redirectViewSecond")
    public String redirectViewSecond(
            RedirectAttributes redirectAttributes
    ) {
        String msg = "RedirectView 2";
        redirectAttributes
                .addFlashAttribute("msg", msg);
        return "redirect:/mapping/page";
    }

    @GetMapping("naver")
    public String goNaver() {
        return "redirect:http://naver.com";
    }

    @GetMapping("react")
    public ModelAndView goKakao() {
        String url = "redirect:http://localhost:3000";
        return new ModelAndView(url);
    }
}
