package io.github.parj.hello;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes) {
        return new RedirectView("swagger-ui/index.html");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Greetings from Spring Boot!";
    }

}