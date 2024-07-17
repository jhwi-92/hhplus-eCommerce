package hhplus.ecommoerce.biz.application.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderHistory is a Querydsl query type for OrderHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderHistory extends EntityPathBase<OrderHistory> {

    private static final long serialVersionUID = -1508422163L;

    public static final QOrderHistory orderHistory = new QOrderHistory("orderHistory");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath status = createString("status");

    public final StringPath yyyyMmDd = createString("yyyyMmDd");

    public QOrderHistory(String variable) {
        super(OrderHistory.class, forVariable(variable));
    }

    public QOrderHistory(Path<? extends OrderHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderHistory(PathMetadata metadata) {
        super(OrderHistory.class, metadata);
    }

}

