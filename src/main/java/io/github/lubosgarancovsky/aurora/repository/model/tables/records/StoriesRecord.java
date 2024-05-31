/*
 * This file is generated by jOOQ.
 */
package io.github.lubosgarancovsky.aurora.repository.model.tables.records;


import io.github.lubosgarancovsky.aurora.repository.model.tables.Stories;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class StoriesRecord extends UpdatableRecordImpl<StoriesRecord> implements Record11<UUID, String, String, String, Boolean, UUID, UUID, UUID, UUID, UUID, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.stories.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.stories.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.stories.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.stories.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.stories.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.stories.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.stories.code</code>.
     */
    public void setCode(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.stories.code</code>.
     */
    public String getCode() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.stories.in_board</code>.
     */
    public void setInBoard(Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.stories.in_board</code>.
     */
    public Boolean getInBoard() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>public.stories.project_id</code>.
     */
    public void setProjectId(UUID value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.stories.project_id</code>.
     */
    public UUID getProjectId() {
        return (UUID) get(5);
    }

    /**
     * Setter for <code>public.stories.state_id</code>.
     */
    public void setStateId(UUID value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.stories.state_id</code>.
     */
    public UUID getStateId() {
        return (UUID) get(6);
    }

    /**
     * Setter for <code>public.stories.type_id</code>.
     */
    public void setTypeId(UUID value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.stories.type_id</code>.
     */
    public UUID getTypeId() {
        return (UUID) get(7);
    }

    /**
     * Setter for <code>public.stories.created_by</code>.
     */
    public void setCreatedBy(UUID value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.stories.created_by</code>.
     */
    public UUID getCreatedBy() {
        return (UUID) get(8);
    }

    /**
     * Setter for <code>public.stories.assigned_to</code>.
     */
    public void setAssignedTo(UUID value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.stories.assigned_to</code>.
     */
    public UUID getAssignedTo() {
        return (UUID) get(9);
    }

    /**
     * Setter for <code>public.stories.created_at</code>.
     */
    public void setCreatedAt(LocalDateTime value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.stories.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<UUID, String, String, String, Boolean, UUID, UUID, UUID, UUID, UUID, LocalDateTime> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<UUID, String, String, String, Boolean, UUID, UUID, UUID, UUID, UUID, LocalDateTime> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Stories.STORIES.ID;
    }

    @Override
    public Field<String> field2() {
        return Stories.STORIES.NAME;
    }

    @Override
    public Field<String> field3() {
        return Stories.STORIES.DESCRIPTION;
    }

    @Override
    public Field<String> field4() {
        return Stories.STORIES.CODE;
    }

    @Override
    public Field<Boolean> field5() {
        return Stories.STORIES.IN_BOARD;
    }

    @Override
    public Field<UUID> field6() {
        return Stories.STORIES.PROJECT_ID;
    }

    @Override
    public Field<UUID> field7() {
        return Stories.STORIES.STATE_ID;
    }

    @Override
    public Field<UUID> field8() {
        return Stories.STORIES.TYPE_ID;
    }

    @Override
    public Field<UUID> field9() {
        return Stories.STORIES.CREATED_BY;
    }

    @Override
    public Field<UUID> field10() {
        return Stories.STORIES.ASSIGNED_TO;
    }

    @Override
    public Field<LocalDateTime> field11() {
        return Stories.STORIES.CREATED_AT;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public String component4() {
        return getCode();
    }

    @Override
    public Boolean component5() {
        return getInBoard();
    }

    @Override
    public UUID component6() {
        return getProjectId();
    }

    @Override
    public UUID component7() {
        return getStateId();
    }

    @Override
    public UUID component8() {
        return getTypeId();
    }

    @Override
    public UUID component9() {
        return getCreatedBy();
    }

    @Override
    public UUID component10() {
        return getAssignedTo();
    }

    @Override
    public LocalDateTime component11() {
        return getCreatedAt();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public String value4() {
        return getCode();
    }

    @Override
    public Boolean value5() {
        return getInBoard();
    }

    @Override
    public UUID value6() {
        return getProjectId();
    }

    @Override
    public UUID value7() {
        return getStateId();
    }

    @Override
    public UUID value8() {
        return getTypeId();
    }

    @Override
    public UUID value9() {
        return getCreatedBy();
    }

    @Override
    public UUID value10() {
        return getAssignedTo();
    }

    @Override
    public LocalDateTime value11() {
        return getCreatedAt();
    }

    @Override
    public StoriesRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public StoriesRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public StoriesRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public StoriesRecord value4(String value) {
        setCode(value);
        return this;
    }

    @Override
    public StoriesRecord value5(Boolean value) {
        setInBoard(value);
        return this;
    }

    @Override
    public StoriesRecord value6(UUID value) {
        setProjectId(value);
        return this;
    }

    @Override
    public StoriesRecord value7(UUID value) {
        setStateId(value);
        return this;
    }

    @Override
    public StoriesRecord value8(UUID value) {
        setTypeId(value);
        return this;
    }

    @Override
    public StoriesRecord value9(UUID value) {
        setCreatedBy(value);
        return this;
    }

    @Override
    public StoriesRecord value10(UUID value) {
        setAssignedTo(value);
        return this;
    }

    @Override
    public StoriesRecord value11(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public StoriesRecord values(UUID value1, String value2, String value3, String value4, Boolean value5, UUID value6, UUID value7, UUID value8, UUID value9, UUID value10, LocalDateTime value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached StoriesRecord
     */
    public StoriesRecord() {
        super(Stories.STORIES);
    }

    /**
     * Create a detached, initialised StoriesRecord
     */
    public StoriesRecord(UUID id, String name, String description, String code, Boolean inBoard, UUID projectId, UUID stateId, UUID typeId, UUID createdBy, UUID assignedTo, LocalDateTime createdAt) {
        super(Stories.STORIES);

        setId(id);
        setName(name);
        setDescription(description);
        setCode(code);
        setInBoard(inBoard);
        setProjectId(projectId);
        setStateId(stateId);
        setTypeId(typeId);
        setCreatedBy(createdBy);
        setAssignedTo(assignedTo);
        setCreatedAt(createdAt);
        resetChangedOnNotNull();
    }
}