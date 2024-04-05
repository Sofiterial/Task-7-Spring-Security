package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class UserController {

    @GetMapping("/user")
    public String getUserPage(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object princ = authentication.getPrincipal();
        if (princ instanceof User) {
            User user = (User) princ;
            model.addAttribute("user", user);
        }
        return "user";
    }
}