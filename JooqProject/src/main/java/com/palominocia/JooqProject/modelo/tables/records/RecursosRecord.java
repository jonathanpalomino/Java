/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables.records;


import com.palominocia.JooqProject.modelo.tables.Recursos;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class RecursosRecord extends UpdatableRecordImpl<RecursosRecord> implements Record6<Long, String, Long, String, Date, String> {

    private static final long serialVersionUID = -332354280;

    /**
     * Setter for <code>autentificacion.RECURSOS.ID_RECURSO</code>.
     */
    public void setIdRecurso(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>autentificacion.RECURSOS.ID_RECURSO</code>.
     */
    public Long getIdRecurso() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>autentificacion.RECURSOS.NOMBRE_RECURSO</code>.
     */
    public void setNombreRecurso(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>autentificacion.RECURSOS.NOMBRE_RECURSO</code>.
     */
    public String getNombreRecurso() {
        return (String) get(1);
    }

    /**
     * Setter for <code>autentificacion.RECURSOS.DIRECCION_RECURSO</code>.
     */
    public void setDireccionRecurso(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>autentificacion.RECURSOS.DIRECCION_RECURSO</code>.
     */
    public Long getDireccionRecurso() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>autentificacion.RECURSOS.MCA_HABILITADO</code>.
     */
    public void setMcaHabilitado(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>autentificacion.RECURSOS.MCA_HABILITADO</code>.
     */
    public String getMcaHabilitado() {
        return (String) get(3);
    }

    /**
     * Setter for <code>autentificacion.RECURSOS.FEC_ACTU</code>.
     */
    public void setFecActu(Date value) {
        set(4, value);
    }

    /**
     * Getter for <code>autentificacion.RECURSOS.FEC_ACTU</code>.
     */
    public Date getFecActu() {
        return (Date) get(4);
    }

    /**
     * Setter for <code>autentificacion.RECURSOS.ID_USUARIO_MODIF</code>.
     */
    public void setIdUsuarioModif(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>autentificacion.RECURSOS.ID_USUARIO_MODIF</code>.
     */
    public String getIdUsuarioModif() {
        return (String) get(5);
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
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Long, String, Long, String, Date, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Long, String, Long, String, Date, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Recursos.RECURSOS.ID_RECURSO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Recursos.RECURSOS.NOMBRE_RECURSO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return Recursos.RECURSOS.DIRECCION_RECURSO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Recursos.RECURSOS.MCA_HABILITADO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field5() {
        return Recursos.RECURSOS.FEC_ACTU;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Recursos.RECURSOS.ID_USUARIO_MODIF;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getIdRecurso();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getNombreRecurso();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component3() {
        return getDireccionRecurso();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getMcaHabilitado();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date component5() {
        return getFecActu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getIdUsuarioModif();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getIdRecurso();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getNombreRecurso();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getDireccionRecurso();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getMcaHabilitado();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value5() {
        return getFecActu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getIdUsuarioModif();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecursosRecord value1(Long value) {
        setIdRecurso(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecursosRecord value2(String value) {
        setNombreRecurso(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecursosRecord value3(Long value) {
        setDireccionRecurso(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecursosRecord value4(String value) {
        setMcaHabilitado(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecursosRecord value5(Date value) {
        setFecActu(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecursosRecord value6(String value) {
        setIdUsuarioModif(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecursosRecord values(Long value1, String value2, Long value3, String value4, Date value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RecursosRecord
     */
    public RecursosRecord() {
        super(Recursos.RECURSOS);
    }

    /**
     * Create a detached, initialised RecursosRecord
     */
    public RecursosRecord(Long idRecurso, String nombreRecurso, Long direccionRecurso, String mcaHabilitado, Date fecActu, String idUsuarioModif) {
        super(Recursos.RECURSOS);

        set(0, idRecurso);
        set(1, nombreRecurso);
        set(2, direccionRecurso);
        set(3, mcaHabilitado);
        set(4, fecActu);
        set(5, idUsuarioModif);
    }
}
