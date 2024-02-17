package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public  String hello(Model model) {
        model.addAttribute("data","hello!!");
        return "hello"; // html이름인 hello가서 돌려라.
    }
}
