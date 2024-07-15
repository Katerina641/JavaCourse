package com.clinic.clinic.Entity;

import java.time.LocalDateTime;
import java.util.Date;

public class AppointmentDTO {
    private Long id;
    private LocalDateTime appointmentDate;
    private String title;

    public AppointmentDTO(Long id, LocalDateTime appointmentDate, String title) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAppointment() {
        return appointmentDate;
    }

    public void setAppointment(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
