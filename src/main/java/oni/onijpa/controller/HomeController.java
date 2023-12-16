package oni.onijpa.controller;

import oni.onijpa.domain.Human;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("data", "home");
        return "home";
    }

    @GetMapping("/name")
    public String name(@RequestParam(name = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "name";
    }

    @GetMapping("/api/human")
    @ResponseBody
    public Human nameData(@RequestParam(name = "name", required = true) String name, Model model) {
        Human human = new Human();
        human.setName(name);
        return human;
    }
}
