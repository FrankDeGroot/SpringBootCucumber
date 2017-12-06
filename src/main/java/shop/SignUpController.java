package shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/signup")
    public String get(Model model) {
        model.addAttribute("signUp", new SignUp());
        return "signup";
    }

    @PostMapping("/signup")
    public String post(@ModelAttribute SignUp signUp, Model model) {
        if(signUp.getName().length() == 0) {
            model.addAttribute("error", "You must enter a user name");
            return "signup";
        }
        if(signUp.getPassword().length() == 0) {
            model.addAttribute("error", "You must enter a password");
            return "signup";
        }
        if(!signUp.getPassword().equals(signUp.getPasswordVerification())) {
            model.addAttribute("error", "Passwords don't match");
            return "signup";
        }
        if(customerRepository.exists(signUp.getName()) ) {
            model.addAttribute("error", "User already registered");
            return "signup";
        }
        customerRepository.save(new Customer(signUp.getName(), signUp.getPassword()));
        model.addAttribute("name", signUp.getName());
        return "registered";
    }
}
