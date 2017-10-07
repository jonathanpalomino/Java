/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables;


import com.palominocia.JooqProject.modelo.Autentificacion;
import com.palominocia.JooqProject.modelo.Keys;
import com.palominocia.JooqProject.modelo.tables.records.GruposRecord;

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
public class Grupos extends TableImpl<GruposRecord> {

    private static final long serialVersionUID = -575657558;

    /**
     * The reference instance of <code>autentificacion.GRUPOS</code>
     */
    public static final Grupos GRUPOS = new Grupos();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GruposRecord> getRecordType() {
        return GruposRecord.class;
    }

    /**
     * The column <code>autentificacion.GRUPOS.ID_GRUPO</code>.
     */
    public final TableField<GruposRecord, Long> ID_GRUPO = createField("ID_GRUPO", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('autentificacion.\"GRUPOS_ID_GRUPO_seq\"'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>autentificacion.GRUPOS.NOMBRE_GRUPO</code>.
     */
    public final TableField<GruposRecord, String> NOMBRE_GRUPO = createField("NOMBRE_GRUPO", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>autentificacion.GRUPOS.MCA_HABILITADO</code>.
     */
    public final TableField<GruposRecord, String> MCA_HABILITADO = createField("MCA_HABILITADO", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>autentificacion.GRUPOS.FEC_ACTU</code>.
     */
    public final TableField<GruposRecord, Date> FEC_ACTU = createField("FEC_ACTU", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>autentificacion.GRUPOS.ID_USUARIO_MODIF</code>.
     */
    public final TableField<GruposRecord, String> ID_USUARIO_MODIF = createField("ID_USUARIO_MODIF", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>autentificacion.GRUPOS</code> table reference
     */
    public Grupos() {
        this(DSL.name("GRUPOS"), null);
    }

    /**
     * Create an aliased <code>autentificacion.GRUPOS</code> table reference
     */
    public Grupos(String alias) {
        this(DSL.name(alias), GRUPOS);
    }

    /**
     * Create an aliased <code>autentificacion.GRUPOS</code> table reference
     */
    public Grupos(Name alias) {
        this(alias, GRUPOS);
    }

    private Grupos(Name alias, Table<GruposRecord> aliased) {
        this(alias, aliased, null);
    }

    private Grupos(Name alias, Table<GruposRecord> aliased, Field<?>[] parameters) {
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
    public Identity<GruposRecord, Long> getIdentity() {
        return Keys.IDENTITY_GRUPOS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<GruposRecord> getPrimaryKey() {
        return Keys.GRUPOS_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<GruposRecord>> getKeys() {
        return Arrays.<UniqueKey<GruposRecord>>asList(Keys.GRUPOS_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<GruposRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<GruposRecord, ?>>asList(Keys.GRUPOS__FK_ID_USUARIO_GRUPOS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grupos as(String alias) {
        return new Grupos(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Grupos as(Name alias) {
        return new Grupos(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Grupos rename(String name) {
        return new Grupos(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Grupos rename(Name name) {
        return new Grupos(name, null);
    }
}
