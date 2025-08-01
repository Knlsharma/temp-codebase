package com.trans.svc.command.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OperationType {

    @Id
    private Integer id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Direction direction; // DEBIT or CREDIT

    private boolean reversible;

    public enum Direction { DEBIT, CREDIT }
}
