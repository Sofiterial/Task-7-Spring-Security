package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    private void allRoleForUser(ModelMap model) {
        List<Role> roleList = roleService.findAllRolles();
        model.addAttribute("allRoles", roleList);
    }

    @GetMapping("/{id}")
    public String readUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.readUser(id));
        return "admin/show";
    }

    @GetMapping("/users")
    public String showUserList(ModelMap model) {
        List<User> users = userService.getUserAndRoles();
        model.addAttribute("users", users);
        return "admin/all";
    }

    @GetMapping("admin/createUser")
    public String showCreateForm(ModelMap model) {
        allRoleForUser(model);
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/editUser/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        User user = userService.readUser(id);
        List<Role> roles = roleService.findAllRolles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String master(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
