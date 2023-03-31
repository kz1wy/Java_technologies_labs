package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public ModelAndView submitContactForm(@RequestParam("name") String name,
                                          @RequestParam("email") String email,
                                          @RequestParam("message") String message) {
        ModelAndView mav = new ModelAndView("contact");
        mav.addObject("name", name);
        mav.addObject("email", email);
        mav.addObject("message", message);
        return mav;
    }

    @GetMapping(value = "/about")
    @ResponseBody
    public String about() {
        return "About this site";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, Model model) {
        model.addAttribute("message", "Unsupported HTTP method: " + ex.getMethod());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("message", "An error occurred: " + ex.getMessage());
        return "error";
    }

    @GetMapping("/error")
    public String handleError() {
        return "error";
    }



}