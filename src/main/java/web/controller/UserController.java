package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping
    public String save(@ModelAttribute User user) {
        service.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.getUser(id));
        return "user-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteUser(id);
        return "redirect:/";
    }
}
