package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.Mail;
import nl.bioinf.recipespaces.model.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;

/**
 * Takes care of mailing developers of the app
 * @author Rose Hazenberg
 */
@Controller
@RequestMapping("contact")
public class ContactController {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ContactController.class.getName());

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private MailService mailService;

    /**
     * This serves the contact page
     * @param modelMap adds mail object to the model for use in html
     * @return contact page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String contact(ModelMap modelMap) {
        logger.log(Level.INFO, "Serving the contact page with a Mail model");
        modelMap.put("contact", new Mail());
        return "contact";
    }

    /**
     * Adds all the mail info to send an email and displays message when send
     * @param mailInfo mail model
     * @param modelMap to add mail to model for use in html
     * @return contact page with message the email is send or not
     */
    @RequestMapping(method = RequestMethod.POST)
    public String send(@ModelAttribute("contact") Mail mailInfo, ModelMap modelMap) {
        try {
            Mail mail = new Mail();
            mail.setMailFrom(mailInfo.getName());
            mail.setMailTo(username);
            mail.setMailSubject(mailInfo.getMailSubject());
            mail.setMailContent(mailInfo.getMailContent());
            mailService.sendEmail(mail);
            modelMap.put("msg", "The email has been send");
            logger.log(Level.INFO, "Setting up the mail and sending");
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Something went wrong; cause= " + ex.getCause() + ", message= " + ex.getMessage());
            modelMap.put("msg", "Something went wrong, please try again.");
        }
        return "contact";
    }
}
