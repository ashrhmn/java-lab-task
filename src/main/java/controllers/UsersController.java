package controllers;

import model.User;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService){
        this.userService = userService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users",users);
        return "user-list";
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String getCreatePage(Model model){
        model.addAttribute("user",new User());
        return "create-user";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String saveNewUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "create-user";
        }
        this.userService.createUser(user);
        return "redirect:/users/list";
    }




}
