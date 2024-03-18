package LoginApplication.CustomApplication.controller;

import LoginApplication.CustomApplication.dto.UserDto;
import LoginApplication.CustomApplication.model.User;
import LoginApplication.CustomApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController{

    @Autowired
    private UserDetailsService userDetailsService;

//    @Autowired
//    private EmployeeService employeeService;

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userdetail", userDetails);
        return "home";
    }



    @GetMapping("/help")
    public String help(Model model, UserDto userDto) {
//		model.addAttribute("user", userDto);
        return "help";
    }

    @GetMapping("/info")
    public String task(Model model, UserDto userDto) {
//		model.addAttribute("user", userDto);
        return "info";
    }

    @GetMapping("/Contact")
    public String Contact(Model model, UserDto userDto) {
//		model.addAttribute("user", userDto);
        return "Contact";
    }

    @GetMapping("/login")
    public String login(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "login";
    }
    @GetMapping("/analytics")
    public String analytics(Model model, UserDto userDto) {
//		model.addAttribute("user", userDto);
        return "analytics";
    }


//    @GetMapping("/list")
//    public String list(Model model, UserDto userDto) {
//        List<Employee> theEmployees = employeeService.findAll();
//        model.addAttribute("employees",theEmployees);
//        return "list-employees";
//    }


    @GetMapping("/register")
    public String register(Model model, UserDto userDto) {

        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserDto userDto, Model model) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            model.addAttribute("userexist", user);
            return "register";

        }
        userService.save(userDto);
        return "redirect:/register?success";
    }



}
