package com.uca.pncsegundoparcialgestiontareas.entities;

import com.uca.pncsegundoparcialgestiontareas.utils.enums.Priority;
import com.uca.pncsegundoparcialgestiontareas.utils.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Status status;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "estimatedHours")
    private int estimatedHours;

    @Column(name = "loggedHours")
    private int loggedHours;

    @Column(name = "dueDate")
    private Date dueDate;

    @Column(name = "assignedTo")
    private String assignedTo;

    @Column(name = "active")
    private boolean active;
}
