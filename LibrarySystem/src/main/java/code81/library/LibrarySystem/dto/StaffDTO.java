package code81.library.LibrarySystem.dto;


import code81.library.LibrarySystem.entity.StaffRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffDTO {

	private String name;
	private String email;
	private String phone;
	private String address;
	private Boolean isActive;
	private StaffRole role;

}
