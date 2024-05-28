/*
 * This file is generated by jOOQ.
 */
package io.github.lubosgarancovsky.aurora.repository.model.tables.records;


import io.github.lubosgarancovsky.aurora.repository.model.tables.Requests;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RequestsRecord extends UpdatableRecordImpl<RequestsRecord> implements Record5<UUID, UUID, UUID, UUID, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.requests.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.requests.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.requests.sender_id</code>.
     */
    public void setSenderId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.requests.sender_id</code>.
     */
    public UUID getSenderId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.requests.recipient_id</code>.
     */
    public void setRecipientId(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.requests.recipient_id</code>.
     */
    public UUID getRecipientId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.requests.team_id</code>.
     */
    public void setTeamId(UUID value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.requests.team_id</code>.
     */
    public UUID getTeamId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>public.requests.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.requests.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, UUID, UUID, UUID, LocalDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<UUID, UUID, UUID, UUID, LocalDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Requests.REQUESTS.ID;
    }

    @Override
    public Field<UUID> field2() {
        return Requests.REQUESTS.SENDER_ID;
    }

    @Override
    public Field<UUID> field3() {
        return Requests.REQUESTS.RECIPIENT_ID;
    }

    @Override
    public Field<UUID> field4() {
        return Requests.REQUESTS.TEAM_ID;
    }

    @Override
    public Field<LocalDateTime> field5() {
        return Requests.REQUESTS.CREATED_AT;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getSenderId();
    }

    @Override
    public UUID component3() {
        return getRecipientId();
    }

    @Override
    public UUID component4() {
        return getTeamId();
    }

    @Override
    public LocalDateTime component5() {
        return getCreatedAt();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getSenderId();
    }

    @Override
    public UUID value3() {
        return getRecipientId();
    }

    @Override
    public UUID value4() {
        return getTeamId();
    }

    @Override
    public LocalDateTime value5() {
        return getCreatedAt();
    }

    @Override
    public RequestsRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public RequestsRecord value2(UUID value) {
        setSenderId(value);
        return this;
    }

    @Override
    public RequestsRecord value3(UUID value) {
        setRecipientId(value);
        return this;
    }

    @Override
    public RequestsRecord value4(UUID value) {
        setTeamId(value);
        return this;
    }

    @Override
    public RequestsRecord value5(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public RequestsRecord values(UUID value1, UUID value2, UUID value3, UUID value4, LocalDateTime value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RequestsRecord
     */
    public RequestsRecord() {
        super(Requests.REQUESTS);
    }

    /**
     * Create a detached, initialised RequestsRecord
     */
    public RequestsRecord(UUID id, UUID senderId, UUID recipientId, UUID teamId, LocalDateTime createdAt) {
        super(Requests.REQUESTS);

        setId(id);
        setSenderId(senderId);
        setRecipientId(recipientId);
        setTeamId(teamId);
        setCreatedAt(createdAt);
        resetChangedOnNotNull();
    }
}