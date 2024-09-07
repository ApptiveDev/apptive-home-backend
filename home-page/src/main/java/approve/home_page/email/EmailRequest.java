package approve.home_page.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {
    private String from;
    private String subject;
    private String body;
    private String category;
}