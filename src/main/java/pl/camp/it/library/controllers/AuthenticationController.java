package pl.camp.it.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IUserService;
import pl.camp.it.library.session.SessionObject;

import javax.annotation.Resource;

@Controller // controller jest ciezko przetestowac, zalezy nam zeby testowac logike czyli service
public class AuthenticationController {

    @Autowired //poszukaj w pudeleczku takiej klasy i wstrzyknij mi te informacje
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("user", new User());

        if(sessionObject.getUser() == null) {
            model.addAttribute("isLogged", false);
            return "login";
        } else {
            return "redirect:/main";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        boolean authenticationResult = userService.authenticate(user);

        if(authenticationResult) {
            sessionObject.setUser(new User());
            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }
}
