package bytetech.movierecmommendations.server.infrastructure.email.service;

import bytetech.movierecmommendations.server.infrastructure.email.entity.EmailDetails;

import java.util.concurrent.CompletableFuture;

public interface EmailService {

    CompletableFuture<String> sendSimpleMail(EmailDetails details);

    CompletableFuture<String> sendMailWithAttachment(EmailDetails details);

}
