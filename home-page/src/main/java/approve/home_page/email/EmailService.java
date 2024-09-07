package approve.home_page.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Transactional
@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final String fixedRecipient = "pnuapptive@gmail.com";

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String fromEmail, String title, String text, String category) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(fixedRecipient);
            helper.setSubject(title);
            helper.setText(createEmailContent(fromEmail, text, category), true);
            helper.setReplyTo(fromEmail);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

    private String createEmailContent(String fromEmail, String text, String category) {
        return String.format(
                "<html><body>" +
                        "<p><strong>발신자:</strong> %s</p>" +
                        "<p><strong>내용:</strong></p>" +
                        "<p>%s</p>" +
                        "<p><strong>카테고리:</strong> %s</p>" +
                        "</body></html>",
                fromEmail, text, category
        );
    }
}