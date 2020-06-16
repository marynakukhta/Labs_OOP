package com.knu.demo.service;

import com.knu.demo.converter.UserConverter;
import com.knu.demo.dto.UserDTO;
import com.knu.demo.entity.User;
import com.knu.demo.service.data.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationControllerService {
    private final UserConverter userConverter;
    private final RegistrationService registrationService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public UserDTO save(UserDTO userDTO) {
        User currentUser = userConverter.convertToEntity(userDTO);
        UserDTO savedUserDto = userConverter.convertToDto(registrationService.save(currentUser));
        applicationEventPublisher.publishEvent(savedUserDto);
        log.info("save userDTO {}", userDTO);
        return savedUserDto;
    }
}
