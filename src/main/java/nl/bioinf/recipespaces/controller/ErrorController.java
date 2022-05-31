package nl.bioinf.recipespaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;

/**
 * Serves the user an error page when one occurs
 * @author Jurriën de Jong
 */
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ErrorController.class.getName());

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(status.toString());
        logger.log(Level.SEVERE, "Something went wrong with the page, status code = " + statusCode);
        return switch (statusCode) {
            case 404 -> "error-404";
            case 500 -> "error-500";
            default -> "error";
        };
    }
}
