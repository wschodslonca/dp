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
@Table(name = "ACL")
public class ACL {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long aclId;

    @NotNull
    private Long userId;

    @NotNull
    private String tabName;

    @NotNull
    private Long rowId;
}
