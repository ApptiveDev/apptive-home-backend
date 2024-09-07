package approve.home_page.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        try {
            emailService.sendEmail(
                    emailRequest.getFrom(),
                    emailRequest.getSubject(),
                    emailRequest.getBody(),
                    emailRequest.getCategory()
            );
            return "Email sent successfully";
        } catch (Exception e) {
            return "Failed to send email: " + e.getMessage();
        }
    }
}