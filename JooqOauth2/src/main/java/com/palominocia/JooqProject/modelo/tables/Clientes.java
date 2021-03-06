/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables;


import com.palominocia.JooqProject.modelo.Autentificacion;
import com.palominocia.JooqProject.modelo.Keys;
import com.palominocia.JooqProject.modelo.tables.records.ClientesRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
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
public class Clientes extends TableImpl<ClientesRecord> {

    private static final long serialVersionUID = 434075651;

    /**
     * The reference instance of <code>autentificacion.CLIENTES</code>
     */
    public static final Clientes CLIENTES = new Clientes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ClientesRecord> getRecordType() {
        return ClientesRecord.class;
    }

    /**
     * The column <code>autentificacion.CLIENTES.ID_CLIENTE</code>.
     */
    public final TableField<ClientesRecord, Long> ID_CLIENTE = createField("ID_CLIENTE", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('autentificacion.\"CLIENTES_ID_CLIENTE_seq\"'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.access_token_validity_seconds_alias</code>.
     */
    public final TableField<ClientesRecord, Integer> ACCESS_TOKEN_VALIDITY_SECONDS_ALIAS = createField("access_token_validity_seconds_alias", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>autentificacion.CLIENTES.additional_information_str</code>.
     */
    public final TableField<ClientesRecord, String> ADDITIONAL_INFORMATION_STR = createField("additional_information_str", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.authorities_str</code>.
     */
    public final TableField<ClientesRecord, String> AUTHORITIES_STR = createField("authorities_str", org.jooq.impl.SQLDataType.VARCHAR(500), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.authorized_grant_type_str</code>.
     */
    public final TableField<ClientesRecord, String> AUTHORIZED_GRANT_TYPE_STR = createField("authorized_grant_type_str", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.client_id_alias</code>.
     */
    public final TableField<ClientesRecord, String> CLIENT_ID_ALIAS = createField("client_id_alias", org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.client_secret_alias</code>.
     */
    public final TableField<ClientesRecord, String> CLIENT_SECRET_ALIAS = createField("client_secret_alias", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.public_key</code>.
     */
    public final TableField<ClientesRecord, String> PUBLIC_KEY = createField("public_key", org.jooq.impl.SQLDataType.VARCHAR(5000).nullable(false), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.refresh_token_validity_seconds_alias</code>.
     */
    public final TableField<ClientesRecord, Integer> REFRESH_TOKEN_VALIDITY_SECONDS_ALIAS = createField("refresh_token_validity_seconds_alias", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>autentificacion.CLIENTES.registered_redirect_uri_str</code>.
     */
    public final TableField<ClientesRecord, String> REGISTERED_REDIRECT_URI_STR = createField("registered_redirect_uri_str", org.jooq.impl.SQLDataType.VARCHAR(1024), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.resource_id_str</code>.
     */
    public final TableField<ClientesRecord, String> RESOURCE_ID_STR = createField("resource_id_str", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.scope_str</code>.
     */
    public final TableField<ClientesRecord, String> SCOPE_STR = createField("scope_str", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.MCA_HABILITADO</code>.
     */
    public final TableField<ClientesRecord, String> MCA_HABILITADO = createField("MCA_HABILITADO", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>autentificacion.CLIENTES.FEC_ACTU</code>.
     */
    public final TableField<ClientesRecord, Date> FEC_ACTU = createField("FEC_ACTU", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>autentificacion.CLIENTES.ID_USUARIO_MODIF</code>.
     */
    public final TableField<ClientesRecord, String> ID_USUARIO_MODIF = createField("ID_USUARIO_MODIF", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>autentificacion.CLIENTES</code> table reference
     */
    public Clientes() {
        this(DSL.name("CLIENTES"), null);
    }

    /**
     * Create an aliased <code>autentificacion.CLIENTES</code> table reference
     */
    public Clientes(String alias) {
        this(DSL.name(alias), CLIENTES);
    }

    /**
     * Create an aliased <code>autentificacion.CLIENTES</code> table reference
     */
    public Clientes(Name alias) {
        this(alias, CLIENTES);
    }

    private Clientes(Name alias, Table<ClientesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Clientes(Name alias, Table<ClientesRecord> aliased, Field<?>[] parameters) {
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
    public Identity<ClientesRecord, Long> getIdentity() {
        return Keys.IDENTITY_CLIENTES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ClientesRecord> getPrimaryKey() {
        return Keys.CLIENTES_PK;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ClientesRecord>> getKeys() {
        return Arrays.<UniqueKey<ClientesRecord>>asList(Keys.CLIENTES_PK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clientes as(String alias) {
        return new Clientes(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Clientes as(Name alias) {
        return new Clientes(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Clientes rename(String name) {
        return new Clientes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Clientes rename(Name name) {
        return new Clientes(name, null);
    }
}
