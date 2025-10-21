package Hospital_Mangement.com.example.demo.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class WhatsappService {

    private final String accountSid;
    private final String authToken;
    private final String fromWhatsapp;

    public WhatsappService(
            @Value("${twilio.accountSid:}") String accountSid,
            @Value("${twilio.authToken:}") String authToken,
            @Value("${twilio.fromWhatsappNumber:}") String fromWhatsapp) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.fromWhatsapp = fromWhatsapp;

        if (accountSid != null && !accountSid.isBlank())
            Twilio.init(accountSid, authToken);
    }

    public void sendWhatsappText(String toWhatsapp, String messageText) {
        Message.creator(
                new com.twilio.type.PhoneNumber(toWhatsapp),
                new com.twilio.type.PhoneNumber(fromWhatsapp),
                messageText
        ).create();
    }

    public void sendWhatsappMedia(String toWhatsapp, String messageText, String mediaUrl) {
        Message.creator(
                        new com.twilio.type.PhoneNumber(toWhatsapp),
                        new com.twilio.type.PhoneNumber(fromWhatsapp),
                        messageText)
                .setMediaUrl(List.of(URI.create(mediaUrl)))
                .create();
    }
}
