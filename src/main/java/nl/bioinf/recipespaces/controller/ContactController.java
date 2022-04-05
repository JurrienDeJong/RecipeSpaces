package nl.bioinf.recipespaces.controller;

import nl.bioinf.recipespaces.model.Mail;
import nl.bioinf.recipespaces.model.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Takes care of mailing developers of the app
 * @author Rose Hazenberg
 */
@Controller
@RequestMapping("contact")
public class ContactController {

    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("contact", new Mail());
        return "contact";
    }

    @RequestMapping(value = "send", method = RequestMethod.POST)
    public String send(@ModelAttribute("contact") Mail mailInfo, ModelMap modelMap) {
        try {
            Mail mail = new Mail();
            mail.setMailFrom(mailInfo.getName());
            mail.setMailTo(username);
            mail.setMailSubject(mailInfo.getMailSubject());
            mail.setMailContent(mailInfo.getMailContent());
            mailService.sendEmail(mail);
            modelMap.put("msg", "You're email has been send!");
        } catch (Exception ex) {
            modelMap.put("msg", "Something went wrong, please try again.");
        }
        return "contact";
    }
}
