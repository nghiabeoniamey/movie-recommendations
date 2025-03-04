package bytetech.movierecmommendations.server.infrastructure.security.service.impl;

import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.infrastructure.email.entity.EmailDetails;
import bytetech.movierecmommendations.server.infrastructure.email.service.EmailService;
import bytetech.movierecmommendations.server.infrastructure.security.service.SecurityEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecurityEmailServiceImpl implements SecurityEmailService {

    @Value("${frontend.url}")
    private String frontendUrl;

    private final EmailService emailService;

    public SecurityEmailServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendMailVerified(User newUser, String accessToken) {
        String redirect = frontendUrl + "auth/redirect/verify?token=" + accessToken;

        String emailContent = String.format("""
                        <html>
                            <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                                <div style="max-width: 600px; margin: 0 auto; border: 1px solid #ddd; border-radius: 8px; padding: 20px; background-color: #f9f9f9;">
                                    <h2 style="text-align: center; color: #4CAF50;">Xác Minh Tài Khoản</h2>
                                    <p>Xin chào <strong>%s</strong>,</p>
                                    <p>Bạn đã tạo tài khoản trên hệ thống <strong>Movie Recommendation</strong>. Vui lòng nhấp vào nút bên dưới để xác nhận tài khoản của bạn:</p>
                                    <div style="text-align: center; margin-top: 20px;">
                                        <a href="%s" style="display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #4CAF50; text-decoration: none; border-radius: 5px;">Xác nhận tài khoản</a>
                                    </div>
                                    <p style="margin-top: 20px;">Nếu bạn không yêu cầu tạo tài khoản, vui lòng bỏ qua email này.</p>
                                    <p style="text-align: center; margin-top: 30px; color: #888;">Cảm ơn bạn đã đăng ký tài khoản tại Movie Recommendation!</p>
                                </div>
                            </body>
                        </html>
                        """,
                newUser.getFullName(),
                redirect
        );

        emailService.sendSimpleMail(
                new EmailDetails(
                        newUser.getEmail(),
                        emailContent,
                        "Hệ Thống Movie Recommendations Thông Báo",
                        null
                )
        );

    }

    @Override
    public void sendMailForgotPass(User newUser, String accessToken) {
        String redirect = frontendUrl + "auth/redirect/forgot?token=" + accessToken;

        String emailContent = String.format("""
                        <html>
                            <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                                <div style="max-width: 600px; margin: 0 auto; border: 1px solid #ddd; border-radius: 8px; padding: 20px; background-color: #f9f9f9;">
                                    <h2 style="text-align: center; color: #4CAF50;">Xác Minh Tài Khoản - Lấy Lại Mật Khẩu</h2>
                                    <p>Xin chào <strong>%s</strong>,</p>
                                    <p>Bạn đã yêu cầu ấy lại mật khẩu trên hệ thống <strong>Movie Recommendation</strong>. Vui lòng nhấp vào nút bên dưới để xác nhận tài khoản và đặt lại mật khẩu của bạn:</p>
                                    <div style="text-align: center; margin-top: 20px;">
                                        <a href="%s" style="display: inline-block; padding: 10px 20px; font-size: 16px; color: white; background-color: #4CAF50; text-decoration: none; border-radius: 5px;">Xác nhận tài khoản - Lấy lại mật khẩu</a>
                                    </div>
                                    <p style="margin-top: 20px;">Nếu bạn không yêu cầu lấy lại mật khẩu, vui lòng bỏ qua email này.</p>
                                    <p style="text-align: center; margin-top: 30px; color: #888;">Cảm ơn bạn dùng Movie Recommendation!</p>
                                </div>
                            </body>
                        </html>
                        """,
                newUser.getFullName(),
                redirect
        );

        emailService.sendSimpleMail(
                new EmailDetails(
                        newUser.getEmail(),
                        emailContent,
                        "Hệ Thống Movie Recommendations Thông Báo",
                        null
                )
        );

    }

}
