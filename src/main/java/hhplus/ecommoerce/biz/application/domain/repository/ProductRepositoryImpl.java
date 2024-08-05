package hhplus.ecommoerce.biz.application.domain.repository;


import static hhplus.ecommoerce.biz.application.domain.entity.QOrderHistory.orderHistory;
import static hhplus.ecommoerce.biz.application.domain.entity.QProduct.product;

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

        return queryFactory
            .selectFrom(product)
            .join(orderHistory).on(product.id.eq(orderHistory.productId))
            .where(orderHistory.status.eq("성공")
                .and(orderHistory.yyyyMmDd.goe("20240701")))
            .groupBy(product.id)
            .orderBy(orderHistory.productId.count().desc())
            .limit(5)
            .fetch();
    }

}
