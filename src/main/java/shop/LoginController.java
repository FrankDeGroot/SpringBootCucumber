package shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/login")
    public String get(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String post(@ModelAttribute Login login, Model model) {
        if(login.getName().length() == 0) {
            model.addAttribute("error", "You must enter a user name");
            return "login";
        }
        if(login.getPassword().length() == 0) {
            model.addAttribute("error", "You must enter a password");
            return "login";
        }
        if(!customerRepository.exists(login.getName()) ||
                !customerRepository.findOne(login.getName()).getPassword().equals(login.getPassword())) {
            model.addAttribute("error", "Unknown user or wrong password");
            return "login";
        }
        model.addAttribute("name", login.getName());
        return "profile";
    }
}
