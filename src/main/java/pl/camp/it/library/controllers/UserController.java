package pl.camp.it.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IUserService;

@Controller
public class UserController {  // niech aplikacja nadaje session id, a to session id beda wychwytwac ciasteczka i bedziemy zapietywac dane

    @Autowired
    IUserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, @RequestParam String password2) {

        boolean registerResult = this.userService.registerUser(user, password2);

        if (registerResult) {
            return "redirect:/main";
        } else {
            return "redirect:/register";
        }
    }
}
