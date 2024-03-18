package LoginApplication.CustomApplication.service;


import LoginApplication.CustomApplication.dto.UserDto;
import LoginApplication.CustomApplication.model.User;

public interface UserService {

    User findByUsername(String username);
    User save (UserDto userDto);

}

