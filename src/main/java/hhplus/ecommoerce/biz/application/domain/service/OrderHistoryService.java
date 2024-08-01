package hhplus.ecommoerce.biz.application.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderHistoryService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void bulkInsert() {
        insertProducts();
        insertOrderHistories();
    }


    private void insertProducts() {
        String sql = "INSERT INTO PRODUCT (name, price, quantity, created_at) VALUES (?, ?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();

        for (int i = 1; i <= 100; i++) {
            Object[] values = new Object[]{
                "Product " + i,                    // name
                random.nextInt(1000) + 1,          // price
                random.nextInt(100) + 1,           // quantity
                now                                // createdAt
            };
            batchArgs.add(values);

            if (batchArgs.size() == 1000) {
                jdbcTemplate.batchUpdate(sql, batchArgs);
                batchArgs.clear();
            }
        }

        if (!batchArgs.isEmpty()) {
            jdbcTemplate.batchUpdate(sql, batchArgs);
        }
    }

    private void insertOrderHistories() {
        String sql = "INSERT INTO ORDER_HISTORY (order_id, product_id, price, status, yyyy_mm_dd, created_at) VALUES (?, ?, ?, ?, ?, ?)";

        List<Object[]> batchArgs = new ArrayList<>();
        Random random = new Random();
        LocalDate endDate = LocalDate.of(2024, 8, 1);
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDateTime now = LocalDateTime.now();

        for (int i = 0; i < 1_000_000; i++) {
            LocalDate randomDate = generateRandomDate(startDate, endDate);
            Object[] values = new Object[]{
                random.nextLong(1000000),     // orderId
                random.nextLong(100) + 1,   // productId (1 to 10000)
                random.nextInt(10000),        // price
                "성공",                        // status
                randomDate.format(DateTimeFormatter.BASIC_ISO_DATE),  // yyyyMmDd
                now                           // createdAt
            };
            batchArgs.add(values);

            if (batchArgs.size() == 10000) {
                jdbcTemplate.batchUpdate(sql, batchArgs);
                batchArgs.clear();
            }
        }

        if (!batchArgs.isEmpty()) {
            jdbcTemplate.batchUpdate(sql, batchArgs);
        }
    }

    private LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate) {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}