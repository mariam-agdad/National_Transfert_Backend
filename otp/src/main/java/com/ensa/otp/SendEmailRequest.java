package com.ensa.otp;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SendEmailRequest {
    private String toEmail;
    private String subject;
    private String body;
}
