package hhplus.ecommoerce.biz.application.facade;

import static org.junit.jupiter.api.Assertions.*;

import hhplus.ecommoerce.biz.application.domain.entity.Order;
import hhplus.ecommoerce.biz.application.domain.entity.Product;
import hhplus.ecommoerce.biz.application.domain.entity.User;
import hhplus.ecommoerce.biz.application.domain.service.DataPlatformService;
import hhplus.ecommoerce.biz.application.domain.service.OrderService;
import hhplus.ecommoerce.biz.application.domain.service.PaymentService;
import hhplus.ecommoerce.biz.application.domain.service.ProductService;
import hhplus.ecommoerce.biz.application.domain.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("주문결제 파사드를 테스트한다.")
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class OrderFacadeTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderFacade orderFacade;

    @MockBean
    private DataPlatformService dataPlatformService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {

        Product product = new Product(10L, "윤용", 3200, 10, LocalDateTime.now());
        entityManager.persist(product);
        User user = new User(11L, "김종협", 10000, LocalDateTime.now());
        entityManager.persist(user);

        entityManager.flush();
    }

    @Test
    public void 재고차감_잔액차감_테스트() {

        //given
        Long userId = 11L;
        Long productId = 10L;
        int quantity = 1;
        int price = 1000;
        String status = "성공";

        Order order = new Order(userId, productId, quantity, price, status);

        //when
        Order newOrder = orderFacade.orderPayment(order);

        //then
        //재고 확인
        assertEquals(9, productService.getProduct(productId).getQuantity());

        //사용자 잔액 확인
        assertEquals(9000, userService.selectUser(userId).getPoint());
    }

    @Test

}