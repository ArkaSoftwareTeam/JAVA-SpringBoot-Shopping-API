package app.shopapi.Services.AccountingServices;
import app.shopapi.DTOs.UserDTO;
import app.shopapi.DTOs.UserResponseDTO;

public interface IUserService {
    UserDTO registerUser(UserDTO userDTO);

    UserResponseDTO getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    UserDTO getUserById(Long userId);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    String deleteUser(Long userId);
}
