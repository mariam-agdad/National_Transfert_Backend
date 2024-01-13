package com.ensa.client.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SendMoneyRequest {
    private String senderEmail;
    private String senderPassword;
    private String receiverEmail;
    private Double amount;
}
