package code81.library.LibrarySystem.mapper;

import code81.library.LibrarySystem.dto.RegisterRequestDTO;
import code81.library.LibrarySystem.dto.StaffDTO;
import code81.library.LibrarySystem.entity.Staff;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StaffMapper {

    public StaffDTO toDTO(Staff staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setName(staff.getName());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setPhone(staff.getPhone());
        staffDTO.setAddress(staff.getAddress());
        staffDTO.setRole(staff.getRole());
        return staffDTO;

    }
    public Staff toEntity(StaffDTO staffDTO) {
        Staff staff = new Staff();
        staff.setName(staffDTO.getName());
        staff.setEmail(staffDTO.getEmail());
        staff.setPhone(staffDTO.getPhone());
        staff.setAddress(staffDTO.getAddress());
        staff.setIsActive(staffDTO.getIsActive());
        staff.setRole(staffDTO.getRole());
        return staff;
    }
    public Staff toEntity(RegisterRequestDTO staffDTO) {
        Staff staff = new Staff();
        staff.setName(staffDTO.getName());
        staff.setEmail(staffDTO.getEmail());
        staff.setPassword(staffDTO.getPassword());
        staff.setPhone(staffDTO.getPhone());
        staff.setAddress(staffDTO.getAddress());
        staff.setIsActive(staffDTO.isActive());
        staff.setRole(staffDTO.getRole());
        return staff;
    }
    public StaffDTO toDTO(RegisterRequestDTO staff) {
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setName(staff.getName());
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setPhone(staff.getPhone());
        staffDTO.setAddress(staff.getAddress());
        staffDTO.setIsActive(staff.isActive());
        staffDTO.setRole(staff.getRole());
        return staffDTO;
    }
}
