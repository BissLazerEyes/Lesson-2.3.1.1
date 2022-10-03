package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.Service.UserService;
import web.model.User;

import javax.validation.Valid;

@SuppressWarnings("ALL")
@Controller
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @ModelAttribute("newUser")
    public User getPerson(){
        return new User();
    }
    @GetMapping("/users")
	public String index(Model model){
    	model.addAttribute("users",service.getAllUsers());
    	return "view/index";
	}

    @PostMapping("/users")
    public String creat(@ModelAttribute("newUser")@Valid User user,
                        BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("users",service.getAllUsers());
            return "view/index";
        }
    	service.saveUser(user);
    	return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deletePerson(@PathVariable("id") int id){
        service.removeUserById(id);
        return "redirect:/users";
    }
    @GetMapping("/users/{id}/edit")
    public String edit (@ModelAttribute("id") int id,Model model){
        model.addAttribute("users",service.getUserById(id));
        return "view/edit";
    }

    @PatchMapping("/users/{id}")
    public String updatePerson(@ModelAttribute("user")@Valid User updateUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "view/edit";
        }
        service.updateUser(updateUser);
        return "redirect:/users";
    }
}