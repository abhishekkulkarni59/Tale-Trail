package com.code.taletrail.service.imp;

import com.code.taletrail.exception.ResourceNotFoundException;
import com.code.taletrail.model.User;
import com.code.taletrail.payload.UserDto;
import com.code.taletrail.repository.UserRepo;
import com.code.taletrail.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User savedUser = userRepo.save(user);

        return userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = userRepo.save(user);

        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
        userRepo.delete(user);
    }

    public User dtoToUser (UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
//        user.setId(userDto.getId());
//        user.setEmail(userDto.getEmail());
//        user.setName(userDto.getName());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto (User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setEmail(user.getEmail());
//        userDto.setName(user.getName());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
