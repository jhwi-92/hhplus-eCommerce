package hhplus.ecommoerce.biz.application.facade;

import static org.junit.jupiter.api.Assertions.*;

import hhplus.ecommoerce.biz.application.domain.entity.Order;
import hhplus.ecommoerce.biz.application.domain.entity.Product;
import hhplus.ecommoerce.biz.application.domain.entity.User;
import hhplus.ecommoerce.biz.application.domain.repository.ProductRepository;
import hhplus.ecommoerce.biz.application.domain.repository.UserRepository;
import hhplus.ecommoerce.biz.application.domain.service.DataPlatformService;
import hhplus.ecommoerce.biz.application.domain.service.OrderService;
import hhplus.ecommoerce.biz.application.domain.service.PaymentService;
import hhplus.ecommoerce.biz.application.domain.service.ProductService;
import hhplus.ecommoerce.biz.application.domain.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class OrderFacadeTest {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {

        Product product = new Product("윤용", 3200, 100, LocalDateTime.now());
        productRepository.save(product);

        User user = new User( "김종협", 1000000, LocalDateTime.now());
        userRepository.save(user);

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
        assertEquals(999000, userService.selectUser(userId).getPoint());
    }

    //1350ms
    @Test
    public void 낙관적락_테스트() throws InterruptedException {

        //given
        int numberOfThreads = 10;
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        AtomicInteger successCount = new AtomicInteger(0);

        Long userId = 1L;
        Long productId = 1L;
        int quantity = 1;
        int price = 1000;
        String status = "성공";

        //when
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    latch.await(); // 모든 스레드가 준비될 때까지 대기
                    Order order = new Order(userId, productId, quantity, price, status);
                    Order newOrder = orderFacade.orderPayment(order);
                    if (newOrder != null && "성공".equals(newOrder.getStatus())) {
                        successCount.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        latch.countDown(); // 모든 스레드 시작
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // 모든 스레드가 종료될 때까지 대기
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("전체 스레드 실행 시간: " + duration + "ms");

        //then
        Product updatedProduct = productService.getProduct(productId);
        User updatedUser = userService.selectUser(userId);

        System.out.println("성공한 주문수: " + successCount.get());
        System.out.println("남은 재고 수: " + updatedProduct.getQuantity());
        // 성공한 주문 수 확인
        assertEquals(successCount.get(), 100 - updatedProduct.getQuantity());

        System.out.println("상품 재고: " + updatedProduct.getQuantity());
        // 재고 확인
        assertTrue(updatedProduct.getQuantity() == 90);

        System.out.println("유저 잔액: " + updatedUser.getPoint());
        // 사용자 잔액 확인
        assertEquals(10000000 - (successCount.get() * price), updatedUser.getPoint());

    }

}