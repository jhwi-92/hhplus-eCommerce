package hhplus.ecommoerce.service.validator;

import hhplus.ecommoerce.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {


    public void validator(User user, int price) {

        if(user.getPoint() < price) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }


    }

}
