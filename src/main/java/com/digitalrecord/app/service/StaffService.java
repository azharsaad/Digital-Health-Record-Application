package com.digitalrecord.app.service;

import com.digitalrecord.app.dto.StaffDto;
import com.digitalrecord.app.dto.StaffProfileRequest;
import com.digitalrecord.app.entity.Patient;
import com.digitalrecord.app.entity.Staff;
import com.digitalrecord.app.entity.User;
import com.digitalrecord.app.repository.StaffRepository;
import com.digitalrecord.app.repository.UserRepository;
import com.digitalrecord.app.utility.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService {

    @Autowired
    public StaffRepository staffRepository;

    @Autowired
    public UserRepository userRepository;

    public Staff createStaffProfile(StaffDto request)
    {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        if(staffRepository.findByUserId(user.getId()).isPresent())
        {
            throw new RuntimeException("Staff profile already exists");
        }
        Staff staff = new Staff();
        staff.setUserId(user.getId());
        staff.setEmail(user.getEmail());
        staff.setAddress(request.getAddress());
        staff.setShift(request.getShift());
        staff.setPhone(request.getPhone());
        staff.setDepartment(request.getDepartment());
        staff.setPosition(request.getPosition());
        return staffRepository.save(staff);
    }

    public Staff getCurrentStaffProfile(String email)
    {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("user not found"));
        return staffRepository.findByUserId(user.getId())
                .orElseThrow(()-> new RuntimeException("Staff profile not found"));
    }

    public Staff updateStaff(String email, StaffDto dto) {
        Staff staff = staffRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Staff User id not found"));
        staff.setDepartment(dto.getDepartment());
        staff.setPosition(dto.getPosition());
        staff.setPhone(dto.getPhone());
        staff.setShift(dto.getShift());
        staff.setAddress(dto.getAddress());
        return staffRepository.save(staff);
    }

    public void deletePatients() {
        staffRepository.deleteAll();
    }
}
