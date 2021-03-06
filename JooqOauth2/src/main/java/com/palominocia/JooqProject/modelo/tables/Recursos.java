/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables;


import com.palominocia.JooqProject.modelo.Autentificacion;
import com.palominocia.JooqProject.modelo.Keys;
import com.palominocia.JooqProject.modelo.tables.records.RecursosRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Recursos extends TableImpl<RecursosRecord> {

    private static final long serialVersionUID = 236994289;

    /**
     * The reference instance of <code>autentificacion.RECURSOS</code>
     */
    public static final Recursos RECURSOS = new Recursos();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RecursosRecord> getRecordType() {
        return RecursosRecord.class;
    }

    /**
     * The column <code>autentificacion.RECURSOS.ID_RECURSO</code>.
     */
    public final TableField<RecursosRecord, Long> ID_RECURSO = createField("ID_RECURSO", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('autentificacion.\"RECURSOS_ID_RECURSO_seq\"'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>autentificacion.RECURSOS.NOMBRE_RECURSO</code>.
     */
    public final TableField<RecursosRecord, String> NOMBRE_RECURSO = createField("NOMBRE_RECURSO", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>autentificacion.RECURSOS.DIRECCION_RECURSO</code>.
     */
    public final TableField<RecursosRecord, Long> DIRECCION_RECURSO = createField("DIRECCION_RECURSO", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>autentificacion.RECURSOS.MCA_HABILITADO</code>.
     */
    public final TableField<RecursosRecord, String> MCA_HABILITADO = createField("MCA_HABILITADO", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>autentificacion.RECURSOS.FEC_ACTU</code>.
     */
    public final TableField<RecursosRecord, Date> FEC_ACTU = createField("FEC_ACTU", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>autentificacion.RECURSOS.ID_USUARIO_MODIF</code>.
     */
    public final TableField<RecursosRecord, String> ID_USUARIO_MODIF = createField("ID_USUARIO_MODIF", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>autentificacion.RECURSOS</code> table reference
     */
    public Recursos() {
        this(DSL.name("RECURSOS"), null);
    }

    /**
     * Create an aliased <code>autentificacion.RECURSOS</code> table reference
     */
    public Recursos(String alias) {
        this(DSL.name(alias), RECURSOS);
    }

    /**
     * Create an aliased <code>autentificacion.RECURSOS</code> table reference
     */
    public Recursos(Name alias) {
        this(alias, RECURSOS);
    }

    private Recursos(Name alias, Table<RecursosRecord> aliased) {
        this(alias, aliased, null);
    }

    private Recursos(Name alias, Table<RecursosRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Autentificacion.AUTENTIFICACION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<RecursosRecord, Long> getIdentity() {
        return Keys.IDENTITY_RECURSOS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<RecursosRecord> getPrimaryKey() {
        return Keys.RECURSOS_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<RecursosRecord>> getKeys() {
        return Arrays.<UniqueKey<RecursosRecord>>asList(Keys.RECURSOS_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<RecursosRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RecursosRecord, ?>>asList(Keys.RECURSOS__FK_ID_USUARIO_RECURSOS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Recursos as(String alias) {
        return new Recursos(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Recursos as(Name alias) {
        return new Recursos(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Recursos rename(String name) {
        return new Recursos(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Recursos rename(Name name) {
        return new Recursos(name, null);
    }
}
