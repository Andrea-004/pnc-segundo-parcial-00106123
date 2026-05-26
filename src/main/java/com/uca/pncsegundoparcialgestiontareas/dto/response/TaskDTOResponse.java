package com.uca.pncsegundoparcialgestiontareas.dto.response;

import java.util.Date;

public record TaskDTOResponse(
        Long id,
        String title,
        int estimatedHours,
        int loggedHours,
        Date dueDate,
        String assignedTo,
        boolean active
) {

}
