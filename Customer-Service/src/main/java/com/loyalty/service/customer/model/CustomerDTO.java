package com.loyalty.service.customer.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDTO {
    private Long Id;

    private String name;

    private String email;

    private Long number;

    private String address;
}
