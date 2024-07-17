package hhplus.ecommoerce.biz.application.domain.repository;


import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hhplus.ecommoerce.biz.application.domain.entity.Product;
import hhplus.ecommoerce.biz.application.domain.entity.QOrderHistory;
import hhplus.ecommoerce.biz.application.domain.entity.QProduct;
import java.util.List;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {


    private final JPAQueryFactory queryFactory;
    @Override
    public List<Product> findProductTopList() {

        QProduct product = QProduct.product;
        QOrderHistory orderHistory = QOrderHistory.orderHistory;

        // 서브쿼리를 인라인으로 작성
        JPAQuery<Long> subQuery = queryFactory
            .select(orderHistory.productId)
            .from(orderHistory)
            .where(orderHistory.status.eq("완료")
                .and(orderHistory.yyyyMmDd.goe("20240710")))
            .groupBy(orderHistory.productId)
            .orderBy(orderHistory.productId.count().desc())
            .limit(5);

        // 메인 쿼리
        return queryFactory
            .selectFrom(product)
            .where(product.id.in(subQuery))
            .fetch();
    }
}
