package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

import javax.validation.Valid;

@SuppressWarnings("ALL")
@Controller
public class UserController {
    private UserDao dao;

    @Autowired
    public UserController(UserDao dao) {
        this.dao = dao;
    }

    @ModelAttribute("newUser")
    public User getPerson(){
        return new User();
    }
    @GetMapping("/users")
	public String index(Model model){
    	model.addAttribute("users",dao.getAllUsers());
    	return "view/index";
	}

    @PostMapping("/users")
    public String creat(@ModelAttribute("newUser")@Valid User user,
                        BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("users",dao.getAllUsers());
            return "view/index";
        }
    	dao.saveUser(user);
    	return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deletePerson(@PathVariable("id") int id){
        dao.removeUserById(id);
        return "redirect:/users";
    }
    @GetMapping("/users/{id}/edit")
    public String edit (@ModelAttribute("id") int id,Model model){
        model.addAttribute("users",dao.getUserById(id));
        return "view/edit";
    }

    @PatchMapping("/users/{id}")
    public String updatePerson(@ModelAttribute("user")@Valid User updateUser, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "view/edit";
        }
        dao.updateUser(updateUser);
        return "redirect:/users";
    }
}