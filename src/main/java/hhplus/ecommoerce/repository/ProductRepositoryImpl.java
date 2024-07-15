package hhplus.ecommoerce.repository;


import hhplus.ecommoerce.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {


    @Override
    public List<Product> findProductTopList() {

        /*
            SELECT *
            FROM PRODUCT A
            INNER JOIN
            (
                SELECT TOP 5 PRODUCT_ID, COUNT(*) AS CNT
                FROM   ORDER_HISTORY A
                WHERE A.STATUS = '완료'
                AND A.YYYYMMDD >= '20240710'
                GROUP BY PRODUCT_ID
                ORDER BY CNT
            ) B ON A.PRODUCT_ID = B.PRODUCT_ID
         */
    }
}
