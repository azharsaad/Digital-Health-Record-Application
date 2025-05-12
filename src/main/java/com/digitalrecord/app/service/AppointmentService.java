package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.AppointmentDto;
import com.digitalrecord.app.dto.UpdateAppointmentRequestDto;
import com.digitalrecord.app.entity.Appointment;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.enums.AppointmentStatus;
import com.digitalrecord.app.repository.AppointmentRepository;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.utility.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    public AppointmentRepository appointmentRepository;

    @Autowired
    public UserRepository userRepository;

    public Appointment bookAppointment(AppointmentDto dto) throws Exception {
        boolean exists = appointmentRepository.existsByPatientIdAndDoctorIdAndAppointmentTime(dto.getPatientId(),dto.getDoctorId(),dto.getAppointmentTime());
        if(exists)
        {
            throw new Exception("Already Appointment Exists");
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setReason(dto.getReason());
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setDoctorId(dto.getDoctorId());
        appointment.setPatientId(dto.getPatientId());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setDate(LocalDateTime.now());
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getPatientAppointments(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        return appointmentRepository.findByPatientId(user.getId()).stream().toList();
    }

    public List<Appointment> getDoctorAppointments(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        return appointmentRepository.findByDoctorId(user.getId()).stream().toList();
    }

    public Appointment rescheduleAppointment(String id, AppointmentDto dto) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Appointment not found"));
        if(dto.getAppointmentTime()!=null && dto.getReason()!=null)
        {
            appointment.setAppointmentTime(dto.getAppointmentTime());
            appointment.setReason(dto.getReason());
        }
        appointment.setStatus(AppointmentStatus.RESCHEDULED);
        appointment.setDate(LocalDateTime.now());
        return appointmentRepository.save(appointment);
    }

    public Appointment cancelAppointment(String id) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Appointment Not Found"));
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointment.setDate(LocalDateTime.now());
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(String id, UpdateAppointmentRequestDto dto) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Appointment Id not found"));
        if(dto.getDoctorId()!=null)
        {
            appointment.setDoctorId(dto.getDoctorId());
        }
        if(dto.getAppointmentDate()!=null)
        {
            appointment.setAppointmentTime(dto.getAppointmentDate());
        }
        if(dto.getDate()!=null)
        {
            appointment.setDate(dto.getDate());
        }
        if(dto.getPatientId()!=null)
        {
            appointment.setPatientId(dto.getPatientId());
        }
        appointment.setStatus(AppointmentStatus.BOOKED);
        appointment.setReason(dto.getReason());
        return appointmentRepository.save(appointment);
    }
}
