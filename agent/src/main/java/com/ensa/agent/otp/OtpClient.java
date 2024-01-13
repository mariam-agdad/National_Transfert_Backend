package com.ensa.agent.otp;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="otp-service", url = "${application.config.otp-url}")
public interface OtpClient {
    @PostMapping
    public String sendEmail(@RequestBody SendEmailRequest sendEmailRequest);

}
