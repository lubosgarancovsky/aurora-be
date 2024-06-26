/*
 * This file is generated by jOOQ.
 */
package io.github.lubosgarancovsky.aurora.repository.model.tables;


import io.github.lubosgarancovsky.aurora.repository.model.Keys;
import io.github.lubosgarancovsky.aurora.repository.model.Public;
import io.github.lubosgarancovsky.aurora.repository.model.tables.records.TeamsRecord;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Teams extends TableImpl<TeamsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.teams</code>
     */
    public static final Teams TEAMS = new Teams();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TeamsRecord> getRecordType() {
        return TeamsRecord.class;
    }

    /**
     * The column <code>public.teams.id</code>.
     */
    public final TableField<TeamsRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field(DSL.raw("gen_random_uuid()"), SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.teams.project_id</code>.
     */
    public final TableField<TeamsRecord, UUID> PROJECT_ID = createField(DSL.name("project_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.teams.created_by</code>.
     */
    public final TableField<TeamsRecord, UUID> CREATED_BY = createField(DSL.name("created_by"), SQLDataType.UUID.nullable(false), this, "");

    private Teams(Name alias, Table<TeamsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Teams(Name alias, Table<TeamsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.teams</code> table reference
     */
    public Teams(String alias) {
        this(DSL.name(alias), TEAMS);
    }

    /**
     * Create an aliased <code>public.teams</code> table reference
     */
    public Teams(Name alias) {
        this(alias, TEAMS);
    }

    /**
     * Create a <code>public.teams</code> table reference
     */
    public Teams() {
        this(DSL.name("teams"), null);
    }

    public <O extends Record> Teams(Table<O> child, ForeignKey<O, TeamsRecord> key) {
        super(child, key, TEAMS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<TeamsRecord> getPrimaryKey() {
        return Keys.TEAMS_PKEY;
    }

    @Override
    public List<ForeignKey<TeamsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.TEAMS__TEAMS_PROJECT_ID_FKEY, Keys.TEAMS__TEAMS_CREATED_BY_FKEY);
    }

    private transient Projects _projects;
    private transient Partners _partners;

    /**
     * Get the implicit join path to the <code>public.projects</code> table.
     */
    public Projects projects() {
        if (_projects == null)
            _projects = new Projects(this, Keys.TEAMS__TEAMS_PROJECT_ID_FKEY);

        return _projects;
    }

    /**
     * Get the implicit join path to the <code>public.partners</code> table.
     */
    public Partners partners() {
        if (_partners == null)
            _partners = new Partners(this, Keys.TEAMS__TEAMS_CREATED_BY_FKEY);

        return _partners;
    }

    @Override
    public Teams as(String alias) {
        return new Teams(DSL.name(alias), this);
    }

    @Override
    public Teams as(Name alias) {
        return new Teams(alias, this);
    }

    @Override
    public Teams as(Table<?> alias) {
        return new Teams(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Teams rename(String name) {
        return new Teams(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Teams rename(Name name) {
        return new Teams(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Teams rename(Table<?> name) {
        return new Teams(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, UUID> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super UUID, ? super UUID, ? super UUID, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super UUID, ? super UUID, ? super UUID, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
