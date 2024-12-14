package com.erciyes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Data
@Table(name = "email_token")
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationToken extends BaseEntity {
    private String email;
    private String token;
    private LocalDateTime expiryDate;
    @ManyToOne
    private User user;
}
