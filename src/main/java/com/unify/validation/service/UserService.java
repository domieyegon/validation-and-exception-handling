package com.unify.validation.service;

import com.unify.validation.domain.User;
import com.unify.validation.repository.UserRepository;
import com.unify.validation.service.dto.UserDTO;
import com.unify.validation.service.mapper.UserMapper;
import com.unify.validation.web.rest.errors.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserDTO save(UserDTO userDTO) {
        log.info("Request to save user: {}", userDTO);
        if (userDTO.getId() == null) {
            userDTO.setCreatedOn(LocalDateTime.now().toString());
        } else {
            userDTO.setUpdatedOn(LocalDateTime.now().toString());
        }

        User user = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        log.info("Request to get all users");
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> filter(Pageable pageable){
        log.info("Request to get users by page: {}", pageable);

        return userRepository.findAllBy(pageable).map(userMapper::toDto);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id){
        log.info("Request to find user by id: {}", id);
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow(() -> new UserNotFoundException("No user with id '"+id+"' found"));
    }

    public void delete(Long id){
        log.info("Request to delete user by id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("No user with id '"+id+"' exists");
        }

        userRepository.deleteById(id);
    }
}
