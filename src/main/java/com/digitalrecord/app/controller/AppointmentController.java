package com.digitalrecord.app.controller;

import com.digitalrecord.app.dto.AppointmentDto;
import com.digitalrecord.app.dto.UpdateAppointmentRequestDto;
import com.digitalrecord.app.entity.Appointment;
import com.digitalrecord.app.repository.AppointmentRepository;
import com.digitalrecord.app.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/appointments")
@RestController
public class AppointmentController {

    @Autowired
    public AppointmentService appointmentService;

    @Autowired
    public AppointmentRepository appointmentRepository;

    @PostMapping("/book")
    public ResponseEntity<Appointment> bookAppointment (@Valid @RequestBody AppointmentDto appointmentDto) throws Exception {
        return ResponseEntity.ok(appointmentService.bookAppointment(appointmentDto));
    }

    @GetMapping("/patient")
    public ResponseEntity <List<Appointment>> getPatientAppointments(@RequestParam String email)
    {
        return ResponseEntity.ok(appointmentService.getPatientAppointments(email));
    }

    @GetMapping("/doctor")
    public ResponseEntity <List<Appointment>> getDoctorAppointments(@RequestParam String email)
    {
        return ResponseEntity.ok(appointmentService.getDoctorAppointments(email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments()
    {
        return ResponseEntity.ok(appointmentRepository.findAll());
    }

    @PutMapping("/reschedule/{id}")
    public ResponseEntity<?> rescheduleAppointment(@PathVariable String id, @RequestBody AppointmentDto dto)
    {
        Appointment updatedAppointment = appointmentService.rescheduleAppointment(id,dto);
        return ResponseEntity.ok(updatedAppointment);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(@PathVariable String id)
    {
        Appointment cancelledAppointment = appointmentService.cancelAppointment(id);
        return ResponseEntity.ok(cancelledAppointment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable String id, @RequestBody UpdateAppointmentRequestDto dto)
    {
        try{
            Appointment updatedAppointment = appointmentService.updateAppointment(id,dto);
            return ResponseEntity.ok(updatedAppointment);
    }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
