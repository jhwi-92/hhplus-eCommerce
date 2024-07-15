package hhplus.ecommoerce.biz.application.domain.service;


import hhplus.ecommoerce.biz.application.domain.service.validator.UserValidator;
import hhplus.ecommoerce.biz.application.domain.entity.User;
import hhplus.ecommoerce.biz.application.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    //유저 포인트 조회
    public User selectUser(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        return user;
    }

    //유저 포인트 충전
    public User userChargePoints(Long userId, Integer amount) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));

        
        user.chargePoint(amount);
        return userRepository.save(user);
    }

    public void decreaseUserPoint(User user, int price) {
        userValidator.validator(user, price);
        user.decreasePoint(price);
        userRepository.save(user);

    }

}
