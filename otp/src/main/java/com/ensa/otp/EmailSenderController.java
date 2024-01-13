package com.ensa.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/otp")
@RequiredArgsConstructor
public class EmailSenderController {
    private final EmailSenderService emailSenderService;
    @PostMapping
    public @ResponseBody void sendEmail(@RequestBody SendEmailRequest sendEmailRequest){
        emailSenderService.sendEmail(sendEmailRequest);
    }
}
