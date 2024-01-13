package com.ensa.agent.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientResponse {
    private String firstName;
    private String lastName;
    private String walletId;
    private Double balance;
}
