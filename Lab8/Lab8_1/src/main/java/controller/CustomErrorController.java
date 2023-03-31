package controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model) {
        String errorMsg = "";
        int statusCode = (Integer)model.getAttribute("javax.servlet.error.status_code");

        switch(statusCode) {
            case 400:
                errorMsg = "Bad Request";
                break;
            case 401:
                errorMsg = "Unauthorized";
                break;
            case 403:
                errorMsg = "Forbidden";
                break;
            case 404:
                errorMsg = "Page Not Found";
                break;
            case 500:
                errorMsg = "Internal Server Error";
                break;
            default:
                errorMsg = "Unknown Error";
        }

        model.addAttribute("errorMsg", errorMsg);
        return "error";
    }

}

