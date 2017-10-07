/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables.records;


import com.palominocia.JooqProject.modelo.tables.Roles;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class RolesRecord extends UpdatableRecordImpl<RolesRecord> implements Record5<Long, String, String, Date, String> {

    private static final long serialVersionUID = -1048900373;

    /**
     * Setter for <code>autentificacion.ROLES.ID_ROL</code>.
     */
    public void setIdRol(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>autentificacion.ROLES.ID_ROL</code>.
     */
    public Long getIdRol() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>autentificacion.ROLES.NOMBRE_ROL</code>.
     */
    public void setNombreRol(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>autentificacion.ROLES.NOMBRE_ROL</code>.
     */
    public String getNombreRol() {
        return (String) get(1);
    }

    /**
     * Setter for <code>autentificacion.ROLES.MCA_HABILITADO</code>.
     */
    public void setMcaHabilitado(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>autentificacion.ROLES.MCA_HABILITADO</code>.
     */
    public String getMcaHabilitado() {
        return (String) get(2);
    }

    /**
     * Setter for <code>autentificacion.ROLES.FEC_ACTU</code>.
     */
    public void setFecActu(Date value) {
        set(3, value);
    }

    /**
     * Getter for <code>autentificacion.ROLES.FEC_ACTU</code>.
     */
    public Date getFecActu() {
        return (Date) get(3);
    }

    /**
     * Setter for <code>autentificacion.ROLES.ID_USUARIO_MODIF</code>.
     */
    public void setIdUsuarioModif(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>autentificacion.ROLES.ID_USUARIO_MODIF</code>.
     */
    public String getIdUsuarioModif() {
        return (String) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, String, Date, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Long, String, String, Date, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Roles.ROLES.ID_ROL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Roles.ROLES.NOMBRE_ROL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Roles.ROLES.MCA_HABILITADO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field4() {
        return Roles.ROLES.FEC_ACTU;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Roles.ROLES.ID_USUARIO_MODIF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getIdRol();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getNombreRol();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getMcaHabilitado();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date component4() {
        return getFecActu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getIdUsuarioModif();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getIdRol();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getNombreRol();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getMcaHabilitado();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value4() {
        return getFecActu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getIdUsuarioModif();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolesRecord value1(Long value) {
        setIdRol(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolesRecord value2(String value) {
        setNombreRol(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolesRecord value3(String value) {
        setMcaHabilitado(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolesRecord value4(Date value) {
        setFecActu(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolesRecord value5(String value) {
        setIdUsuarioModif(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RolesRecord values(Long value1, String value2, String value3, Date value4, String value5) {
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
     * Create a detached RolesRecord
     */
    public RolesRecord() {
        super(Roles.ROLES);
    }

    /**
     * Create a detached, initialised RolesRecord
     */
    public RolesRecord(Long idRol, String nombreRol, String mcaHabilitado, Date fecActu, String idUsuarioModif) {
        super(Roles.ROLES);

        set(0, idRol);
        set(1, nombreRol);
        set(2, mcaHabilitado);
        set(3, fecActu);
        set(4, idUsuarioModif);
    }
}