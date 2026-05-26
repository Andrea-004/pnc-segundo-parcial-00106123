package com.uca.pncsegundoparcialgestiontareas.dto.request;

import com.uca.pncsegundoparcialgestiontareas.utils.enums.Priority;
import com.uca.pncsegundoparcialgestiontareas.utils.enums.Status;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;

@Builder
public record TaskDTORequest(
    @NotNull(message = "El titulo no debe de ser nulo")
    String title,

    String description,

    Status status,

    Priority priority,

    @NotNull(message = "LAs horas estimadas no deben de ser nulas")
    @Min(value=1, message = "Las horas estimadas no pueden ser menores que 1")
    int estimatedHours,

    @Min(value=0, message = "Las horas registradas no pueden ser menores que 0")
    //debe ser menor que estimated hours
    int loggedHours,

    @NotNull(message = "La fecha de entrega no puede ser nula")
    //tambien debe ser una fecha futura
    Date dueDate,

    @NotNull(message = "El campo asignado a no debe de ser nulo")
    String assignedTo,

    //true por defecto, false si la tarea es CANCELLED o DONE
    boolean active
){ }
