/*
 * This file is generated by jOOQ.
 */
package io.github.lubosgarancovsky.aurora.repository.model.tables.records;


import io.github.lubosgarancovsky.aurora.repository.model.tables.TeamPartner;

import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TeamPartnerRecord extends UpdatableRecordImpl<TeamPartnerRecord> implements Record3<UUID, UUID, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.team_partner.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.team_partner.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.team_partner.team_id</code>.
     */
    public void setTeamId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.team_partner.team_id</code>.
     */
    public UUID getTeamId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.team_partner.partner_id</code>.
     */
    public void setPartnerId(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.team_partner.partner_id</code>.
     */
    public UUID getPartnerId() {
        return (UUID) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, UUID> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, UUID, UUID> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return TeamPartner.TEAM_PARTNER.ID;
    }

    @Override
    public Field<UUID> field2() {
        return TeamPartner.TEAM_PARTNER.TEAM_ID;
    }

    @Override
    public Field<UUID> field3() {
        return TeamPartner.TEAM_PARTNER.PARTNER_ID;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getTeamId();
    }

    @Override
    public UUID component3() {
        return getPartnerId();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getTeamId();
    }

    @Override
    public UUID value3() {
        return getPartnerId();
    }

    @Override
    public TeamPartnerRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public TeamPartnerRecord value2(UUID value) {
        setTeamId(value);
        return this;
    }

    @Override
    public TeamPartnerRecord value3(UUID value) {
        setPartnerId(value);
        return this;
    }

    @Override
    public TeamPartnerRecord values(UUID value1, UUID value2, UUID value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TeamPartnerRecord
     */
    public TeamPartnerRecord() {
        super(TeamPartner.TEAM_PARTNER);
    }

    /**
     * Create a detached, initialised TeamPartnerRecord
     */
    public TeamPartnerRecord(UUID id, UUID teamId, UUID partnerId) {
        super(TeamPartner.TEAM_PARTNER);

        setId(id);
        setTeamId(teamId);
        setPartnerId(partnerId);
        resetChangedOnNotNull();
    }
}
