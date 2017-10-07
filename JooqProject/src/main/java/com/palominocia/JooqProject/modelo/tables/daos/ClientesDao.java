/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables.daos;


import com.palominocia.JooqProject.modelo.tables.Clientes;
import com.palominocia.JooqProject.modelo.tables.records.ClientesRecord;

import java.sql.Date;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class ClientesDao extends DAOImpl<ClientesRecord, com.palominocia.JooqProject.modelo.tables.pojos.Clientes, Long> {

    /**
     * Create a new ClientesDao without any configuration
     */
    public ClientesDao() {
        super(Clientes.CLIENTES, com.palominocia.JooqProject.modelo.tables.pojos.Clientes.class);
    }

    /**
     * Create a new ClientesDao with an attached configuration
     */
    public ClientesDao(Configuration configuration) {
        super(Clientes.CLIENTES, com.palominocia.JooqProject.modelo.tables.pojos.Clientes.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(com.palominocia.JooqProject.modelo.tables.pojos.Clientes object) {
        return object.getIdCliente();
    }

    /**
     * Fetch records that have <code>ID_CLIENTE IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByIdCliente(Long... values) {
        return fetch(Clientes.CLIENTES.ID_CLIENTE, values);
    }

    /**
     * Fetch a unique record that has <code>ID_CLIENTE = value</code>
     */
    public com.palominocia.JooqProject.modelo.tables.pojos.Clientes fetchOneByIdCliente(Long value) {
        return fetchOne(Clientes.CLIENTES.ID_CLIENTE, value);
    }

    /**
     * Fetch records that have <code>access_token_validity_seconds_alias IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByAccessTokenValiditySecondsAlias(Integer... values) {
        return fetch(Clientes.CLIENTES.ACCESS_TOKEN_VALIDITY_SECONDS_ALIAS, values);
    }

    /**
     * Fetch records that have <code>additional_information_str IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByAdditionalInformationStr(String... values) {
        return fetch(Clientes.CLIENTES.ADDITIONAL_INFORMATION_STR, values);
    }

    /**
     * Fetch records that have <code>authorities_str IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByAuthoritiesStr(String... values) {
        return fetch(Clientes.CLIENTES.AUTHORITIES_STR, values);
    }

    /**
     * Fetch records that have <code>authorized_grant_type_str IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByAuthorizedGrantTypeStr(String... values) {
        return fetch(Clientes.CLIENTES.AUTHORIZED_GRANT_TYPE_STR, values);
    }

    /**
     * Fetch records that have <code>client_id_alias IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByClientIdAlias(String... values) {
        return fetch(Clientes.CLIENTES.CLIENT_ID_ALIAS, values);
    }

    /**
     * Fetch records that have <code>client_secret_alias IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByClientSecretAlias(String... values) {
        return fetch(Clientes.CLIENTES.CLIENT_SECRET_ALIAS, values);
    }

    /**
     * Fetch records that have <code>public_key IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByPublicKey(String... values) {
        return fetch(Clientes.CLIENTES.PUBLIC_KEY, values);
    }

    /**
     * Fetch records that have <code>refresh_token_validity_seconds_alias IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByRefreshTokenValiditySecondsAlias(Integer... values) {
        return fetch(Clientes.CLIENTES.REFRESH_TOKEN_VALIDITY_SECONDS_ALIAS, values);
    }

    /**
     * Fetch records that have <code>registered_redirect_uri_str IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByRegisteredRedirectUriStr(String... values) {
        return fetch(Clientes.CLIENTES.REGISTERED_REDIRECT_URI_STR, values);
    }

    /**
     * Fetch records that have <code>resource_id_str IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByResourceIdStr(String... values) {
        return fetch(Clientes.CLIENTES.RESOURCE_ID_STR, values);
    }

    /**
     * Fetch records that have <code>scope_str IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByScopeStr(String... values) {
        return fetch(Clientes.CLIENTES.SCOPE_STR, values);
    }

    /**
     * Fetch records that have <code>MCA_HABILITADO IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByMcaHabilitado(String... values) {
        return fetch(Clientes.CLIENTES.MCA_HABILITADO, values);
    }

    /**
     * Fetch records that have <code>FEC_ACTU IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByFecActu(Date... values) {
        return fetch(Clientes.CLIENTES.FEC_ACTU, values);
    }

    /**
     * Fetch records that have <code>ID_USUARIO_MODIF IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Clientes> fetchByIdUsuarioModif(String... values) {
        return fetch(Clientes.CLIENTES.ID_USUARIO_MODIF, values);
    }
}