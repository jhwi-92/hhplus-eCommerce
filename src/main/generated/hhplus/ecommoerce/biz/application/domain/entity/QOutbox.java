package hhplus.ecommoerce.biz.application.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOutbox is a Querydsl query type for Outbox
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOutbox extends EntityPathBase<Outbox> {

    private static final long serialVersionUID = 432349572L;

    public static final QOutbox outbox = new QOutbox("outbox");

    public final StringPath aggregateId = createString("aggregateId");

    public final StringPath aggregateType = createString("aggregateType");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath eventType = createString("eventType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath payload = createString("payload");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QOutbox(String variable) {
        super(Outbox.class, forVariable(variable));
    }

    public QOutbox(Path<? extends Outbox> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOutbox(PathMetadata metadata) {
        super(Outbox.class, metadata);
    }

}

