package hhplus.ecommoerce.service;


import hhplus.ecommoerce.entity.User;
import hhplus.ecommoerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    //유저 포인트 조회
    public User selectUserPoint(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        return user;
    }

    //유저 포인트 충전
    @Transactional
    public User userChargePoints(Long userId, Integer amount) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));

        
        user.chargePoint(amount);
        return userRepository.save(user);
    }

}
