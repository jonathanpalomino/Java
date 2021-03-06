/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables.records;


import com.palominocia.JooqProject.modelo.tables.Grupos;

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
public class GruposRecord extends UpdatableRecordImpl<GruposRecord> implements Record5<Long, String, String, Date, String> {

    private static final long serialVersionUID = 610799932;

    /**
     * Setter for <code>autentificacion.GRUPOS.ID_GRUPO</code>.
     */
    public void setIdGrupo(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>autentificacion.GRUPOS.ID_GRUPO</code>.
     */
    public Long getIdGrupo() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>autentificacion.GRUPOS.NOMBRE_GRUPO</code>.
     */
    public void setNombreGrupo(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>autentificacion.GRUPOS.NOMBRE_GRUPO</code>.
     */
    public String getNombreGrupo() {
        return (String) get(1);
    }

    /**
     * Setter for <code>autentificacion.GRUPOS.MCA_HABILITADO</code>.
     */
    public void setMcaHabilitado(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>autentificacion.GRUPOS.MCA_HABILITADO</code>.
     */
    public String getMcaHabilitado() {
        return (String) get(2);
    }

    /**
     * Setter for <code>autentificacion.GRUPOS.FEC_ACTU</code>.
     */
    public void setFecActu(Date value) {
        set(3, value);
    }

    /**
     * Getter for <code>autentificacion.GRUPOS.FEC_ACTU</code>.
     */
    public Date getFecActu() {
        return (Date) get(3);
    }

    /**
     * Setter for <code>autentificacion.GRUPOS.ID_USUARIO_MODIF</code>.
     */
    public void setIdUsuarioModif(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>autentificacion.GRUPOS.ID_USUARIO_MODIF</code>.
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
        return Grupos.GRUPOS.ID_GRUPO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Grupos.GRUPOS.NOMBRE_GRUPO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Grupos.GRUPOS.MCA_HABILITADO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field4() {
        return Grupos.GRUPOS.FEC_ACTU;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Grupos.GRUPOS.ID_USUARIO_MODIF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getIdGrupo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getNombreGrupo();
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
        return getIdGrupo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getNombreGrupo();
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
    public GruposRecord value1(Long value) {
        setIdGrupo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GruposRecord value2(String value) {
        setNombreGrupo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GruposRecord value3(String value) {
        setMcaHabilitado(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GruposRecord value4(Date value) {
        setFecActu(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GruposRecord value5(String value) {
        setIdUsuarioModif(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GruposRecord values(Long value1, String value2, String value3, Date value4, String value5) {
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
     * Create a detached GruposRecord
     */
    public GruposRecord() {
        super(Grupos.GRUPOS);
    }

    /**
     * Create a detached, initialised GruposRecord
     */
    public GruposRecord(Long idGrupo, String nombreGrupo, String mcaHabilitado, Date fecActu, String idUsuarioModif) {
        super(Grupos.GRUPOS);

        set(0, idGrupo);
        set(1, nombreGrupo);
        set(2, mcaHabilitado);
        set(3, fecActu);
        set(4, idUsuarioModif);
    }
}
