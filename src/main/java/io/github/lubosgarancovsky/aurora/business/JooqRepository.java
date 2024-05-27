package io.github.lubosgarancovsky.aurora.business;

import org.jooq.DSLContext;

public abstract class JooqRepository {

    protected final DSLContext dslContext;

    protected JooqRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }
}
