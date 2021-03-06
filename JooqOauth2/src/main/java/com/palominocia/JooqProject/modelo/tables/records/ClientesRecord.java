/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables.records;


import com.palominocia.JooqProject.modelo.tables.Clientes;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record15;
import org.jooq.Row15;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ClientesRecord extends UpdatableRecordImpl<ClientesRecord> implements Record15<Long, Integer, String, String, String, String, String, String, Integer, String, String, String, String, Date, String> {

    private static final long serialVersionUID = 1591457138;

    /**
     * Setter for <code>autentificacion.CLIENTES.ID_CLIENTE</code>.
     */
    public void setIdCliente(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.ID_CLIENTE</code>.
     */
    public Long getIdCliente() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.access_token_validity_seconds_alias</code>.
     */
    public void setAccessTokenValiditySecondsAlias(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.access_token_validity_seconds_alias</code>.
     */
    public Integer getAccessTokenValiditySecondsAlias() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.additional_information_str</code>.
     */
    public void setAdditionalInformationStr(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.additional_information_str</code>.
     */
    public String getAdditionalInformationStr() {
        return (String) get(2);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.authorities_str</code>.
     */
    public void setAuthoritiesStr(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.authorities_str</code>.
     */
    public String getAuthoritiesStr() {
        return (String) get(3);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.authorized_grant_type_str</code>.
     */
    public void setAuthorizedGrantTypeStr(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.authorized_grant_type_str</code>.
     */
    public String getAuthorizedGrantTypeStr() {
        return (String) get(4);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.client_id_alias</code>.
     */
    public void setClientIdAlias(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.client_id_alias</code>.
     */
    public String getClientIdAlias() {
        return (String) get(5);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.client_secret_alias</code>.
     */
    public void setClientSecretAlias(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.client_secret_alias</code>.
     */
    public String getClientSecretAlias() {
        return (String) get(6);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.public_key</code>.
     */
    public void setPublicKey(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.public_key</code>.
     */
    public String getPublicKey() {
        return (String) get(7);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.refresh_token_validity_seconds_alias</code>.
     */
    public void setRefreshTokenValiditySecondsAlias(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.refresh_token_validity_seconds_alias</code>.
     */
    public Integer getRefreshTokenValiditySecondsAlias() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.registered_redirect_uri_str</code>.
     */
    public void setRegisteredRedirectUriStr(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.registered_redirect_uri_str</code>.
     */
    public String getRegisteredRedirectUriStr() {
        return (String) get(9);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.resource_id_str</code>.
     */
    public void setResourceIdStr(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.resource_id_str</code>.
     */
    public String getResourceIdStr() {
        return (String) get(10);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.scope_str</code>.
     */
    public void setScopeStr(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.scope_str</code>.
     */
    public String getScopeStr() {
        return (String) get(11);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.MCA_HABILITADO</code>.
     */
    public void setMcaHabilitado(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.MCA_HABILITADO</code>.
     */
    public String getMcaHabilitado() {
        return (String) get(12);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.FEC_ACTU</code>.
     */
    public void setFecActu(Date value) {
        set(13, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.FEC_ACTU</code>.
     */
    public Date getFecActu() {
        return (Date) get(13);
    }

    /**
     * Setter for <code>autentificacion.CLIENTES.ID_USUARIO_MODIF</code>.
     */
    public void setIdUsuarioModif(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>autentificacion.CLIENTES.ID_USUARIO_MODIF</code>.
     */
    public String getIdUsuarioModif() {
        return (String) get(14);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record15 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row15<Long, Integer, String, String, String, String, String, String, Integer, String, String, String, String, Date, String> fieldsRow() {
        return (Row15) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row15<Long, Integer, String, String, String, String, String, String, Integer, String, String, String, String, Date, String> valuesRow() {
        return (Row15) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Clientes.CLIENTES.ID_CLIENTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Clientes.CLIENTES.ACCESS_TOKEN_VALIDITY_SECONDS_ALIAS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Clientes.CLIENTES.ADDITIONAL_INFORMATION_STR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Clientes.CLIENTES.AUTHORITIES_STR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Clientes.CLIENTES.AUTHORIZED_GRANT_TYPE_STR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Clientes.CLIENTES.CLIENT_ID_ALIAS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Clientes.CLIENTES.CLIENT_SECRET_ALIAS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return Clientes.CLIENTES.PUBLIC_KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field9() {
        return Clientes.CLIENTES.REFRESH_TOKEN_VALIDITY_SECONDS_ALIAS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return Clientes.CLIENTES.REGISTERED_REDIRECT_URI_STR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return Clientes.CLIENTES.RESOURCE_ID_STR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return Clientes.CLIENTES.SCOPE_STR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return Clientes.CLIENTES.MCA_HABILITADO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field14() {
        return Clientes.CLIENTES.FEC_ACTU;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return Clientes.CLIENTES.ID_USUARIO_MODIF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getIdCliente();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getAccessTokenValiditySecondsAlias();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getAdditionalInformationStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getAuthoritiesStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getAuthorizedGrantTypeStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getClientIdAlias();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getClientSecretAlias();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getPublicKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component9() {
        return getRefreshTokenValiditySecondsAlias();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component10() {
        return getRegisteredRedirectUriStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component11() {
        return getResourceIdStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component12() {
        return getScopeStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component13() {
        return getMcaHabilitado();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date component14() {
        return getFecActu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component15() {
        return getIdUsuarioModif();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getIdCliente();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getAccessTokenValiditySecondsAlias();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getAdditionalInformationStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getAuthoritiesStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getAuthorizedGrantTypeStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getClientIdAlias();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getClientSecretAlias();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getPublicKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value9() {
        return getRefreshTokenValiditySecondsAlias();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getRegisteredRedirectUriStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getResourceIdStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getScopeStr();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getMcaHabilitado();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value14() {
        return getFecActu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getIdUsuarioModif();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value1(Long value) {
        setIdCliente(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value2(Integer value) {
        setAccessTokenValiditySecondsAlias(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value3(String value) {
        setAdditionalInformationStr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value4(String value) {
        setAuthoritiesStr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value5(String value) {
        setAuthorizedGrantTypeStr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value6(String value) {
        setClientIdAlias(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value7(String value) {
        setClientSecretAlias(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value8(String value) {
        setPublicKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value9(Integer value) {
        setRefreshTokenValiditySecondsAlias(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value10(String value) {
        setRegisteredRedirectUriStr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value11(String value) {
        setResourceIdStr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value12(String value) {
        setScopeStr(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value13(String value) {
        setMcaHabilitado(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value14(Date value) {
        setFecActu(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord value15(String value) {
        setIdUsuarioModif(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClientesRecord values(Long value1, Integer value2, String value3, String value4, String value5, String value6, String value7, String value8, Integer value9, String value10, String value11, String value12, String value13, Date value14, String value15) {
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
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ClientesRecord
     */
    public ClientesRecord() {
        super(Clientes.CLIENTES);
    }

    /**
     * Create a detached, initialised ClientesRecord
     */
    public ClientesRecord(Long idCliente, Integer accessTokenValiditySecondsAlias, String additionalInformationStr, String authoritiesStr, String authorizedGrantTypeStr, String clientIdAlias, String clientSecretAlias, String publicKey, Integer refreshTokenValiditySecondsAlias, String registeredRedirectUriStr, String resourceIdStr, String scopeStr, String mcaHabilitado, Date fecActu, String idUsuarioModif) {
        super(Clientes.CLIENTES);

        set(0, idCliente);
        set(1, accessTokenValiditySecondsAlias);
        set(2, additionalInformationStr);
        set(3, authoritiesStr);
        set(4, authorizedGrantTypeStr);
        set(5, clientIdAlias);
        set(6, clientSecretAlias);
        set(7, publicKey);
        set(8, refreshTokenValiditySecondsAlias);
        set(9, registeredRedirectUriStr);
        set(10, resourceIdStr);
        set(11, scopeStr);
        set(12, mcaHabilitado);
        set(13, fecActu);
        set(14, idUsuarioModif);
    }
}
