package com.example.Dodo.model.entity;

import com.example.Dodo.base.BaseEntity;
import com.example.Dodo.model.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_account")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String email;
    String tempPassword;

    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;

}
