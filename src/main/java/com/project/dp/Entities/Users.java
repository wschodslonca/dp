package com.project.dp.Entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Table(name = "Users")
public class Users {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private Long employeeId;

    @NotNull
    private Long roleId;
}
