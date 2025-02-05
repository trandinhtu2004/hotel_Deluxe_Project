/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package email;

import jakarta.websocket.Session;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import jdk.internal.net.http.websocket.Transport;


/**
 *
 * @author AD
 */
public class EmailUtil {
  public static void sendVerificationEmail(String email, String code) {
        String host = "smtp.gmail.com";
        final String user = "minhhieufvc@gmail.com";
        final String password = "nmh282k2";

        // Thiết lập các thuộc tính cho phiên gửi email
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");

        // Tạo một phiên làm việc mới với thông tin xác thực
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        try {
            // Tạo một message mới
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Mã xác nhận email");
            message.setText("Mã xác nhận của bạn là: " + code);

            // Gửi message
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

