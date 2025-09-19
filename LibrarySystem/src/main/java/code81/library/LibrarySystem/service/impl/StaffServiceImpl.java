package code81.library.LibrarySystem.service.impl;

import code81.library.LibrarySystem.dto.LoginRequestDTO;
import code81.library.LibrarySystem.dto.RegisterRequestDTO;
import code81.library.LibrarySystem.dto.StaffDTO;
import code81.library.LibrarySystem.entity.Staff;
import code81.library.LibrarySystem.entity.User;
import code81.library.LibrarySystem.exception.EmailAlreadyExistsException;
import code81.library.LibrarySystem.exception.UserNotFoundException;
import code81.library.LibrarySystem.mapper.StaffMapper;
import code81.library.LibrarySystem.repository.StaffRepository;
import code81.library.LibrarySystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;


import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
	private final StaffRepository userRepository;
	private final StaffMapper staffMapper;

	public StaffServiceImpl(StaffRepository userRepository) {
		this.userRepository = userRepository;
		this.staffMapper = new StaffMapper();
	}

	@Override
	public Optional<StaffDTO> getUserByEmail(String email) {
		return Optional.ofNullable(staffMapper.toDTO(userRepository.findByEmail(email)));
	}

	@Override
	public Optional<StaffDTO> getUserById(Integer userId) {
        return userRepository.findById(userId).map(staffMapper::toDTO);
	}


	@Override
	public Page<StaffDTO> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable).map(staffMapper::toDTO);
	}
	@Override
	public boolean phoneExists(String phone) {
		return userRepository.existsByphone(phone);
	}
	@Override
	public boolean emailExists(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public StaffDTO login(LoginRequestDTO loginRequestDto, HttpSession session) {
		// if (loginRequestDto == null) {
		// 	return null;
		// }
		// String email = loginRequestDto.getEmail();
		// String password = loginRequestDto.getPassword();
		// Optional<User> userOpt = userRepository.findByEmail(email);
		
		// if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
		// 	User user = userOpt.get();
		// 	session.setAttribute("USER_ID", user.getId());
		// 	session.setAttribute("USER_EMAIL", user.getEmail());
		// 	UserDTO dto = new UserDTO();
		// 	dto.setId(user.getId());
		// 	dto.setName(user.getName());
		// 	dto.setEmail(user.getEmail());
		// 	dto.setPhone(user.getPhone());
		// 	dto.setAddress(user.getAddress());
		// 	return dto;
		// }
		return null;
	}

	@Override
	public void logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
	}

	@Override
	public StaffDTO Register(Staff staffuser) {

        if (userRepository.existsByEmail(staffuser.getEmail()) || userRepository.existsByphone(staffuser.getPhone())) {
                throw new EmailAlreadyExistsException("Email or Phone already exist! Please try another email or phone number.");
		}
		staffuser.setEmail(staffuser.getEmail());
		staffuser.setPhone(staffuser.getPhone());
		staffuser.setAddress(staffuser.getAddress());
		staffuser.setName(staffuser.getName());
		staffuser.setRole(staffuser.getRole());
		staffuser.setIsActive(staffuser.getIsActive());
		// Hash the password
		String hashedPassword;
		hashedPassword = BCrypt.hashpw(staffuser.getPassword(), BCrypt.gensalt(12));
		staffuser.setPassword(hashedPassword);

	
        Staff savedUser = userRepository.save(staffuser);
        return staffMapper.toDTO(savedUser);
	    
        }
    
	
}
