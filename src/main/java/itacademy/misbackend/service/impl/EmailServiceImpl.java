package itacademy.misbackend.service.impl;

import itacademy.misbackend.dto.RegistrationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl {
    private final JavaMailSender mailSender;

    @RabbitListener(queues = "email_queue")
    public void handleMessage(RegistrationMessage message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(message.getEmail());
        email.setSubject("Registration Confirmation");
        email.setText("To confirm your e-mail address, please click the link below:\n"
                + "http://localhost:8080/api/confirm?token=" + message.getVerificationToken());
        mailSender.send(email);
    }
}
