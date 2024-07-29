package hhplus.ecommoerce.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import hhplus.ecommoerce.biz.application.domain.service.UserService;
import hhplus.ecommoerce.biz.application.domain.entity.User;
import hhplus.ecommoerce.biz.application.domain.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void 유저_포인트_충전_유저ID_검증실패() {
        // given
        Long userId = 1L;
        Integer chargePoint = 50;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> userService.userChargePoints(userId, chargePoint));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void 유저_포인트_충전_성공() {
        // given
        Long userId = 2L;
        Integer initialPoints = 100;
        Integer chargePoint = 50;
        User user = new User("이석범", initialPoints, LocalDateTime.now());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        User result = userService.userChargePoints(userId, chargePoint);

        // then
        assertNotNull(result);
        assertEquals(initialPoints + chargePoint, result.getPoint());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
    }

}