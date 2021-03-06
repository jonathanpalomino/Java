/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables.daos;


import com.palominocia.JooqProject.modelo.tables.Recursos;
import com.palominocia.JooqProject.modelo.tables.records.RecursosRecord;

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
public class RecursosDao extends DAOImpl<RecursosRecord, com.palominocia.JooqProject.modelo.tables.pojos.Recursos, Long> {

    /**
     * Create a new RecursosDao without any configuration
     */
    public RecursosDao() {
        super(Recursos.RECURSOS, com.palominocia.JooqProject.modelo.tables.pojos.Recursos.class);
    }

    /**
     * Create a new RecursosDao with an attached configuration
     */
    public RecursosDao(Configuration configuration) {
        super(Recursos.RECURSOS, com.palominocia.JooqProject.modelo.tables.pojos.Recursos.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Long getId(com.palominocia.JooqProject.modelo.tables.pojos.Recursos object) {
        return object.getIdRecurso();
    }

    /**
     * Fetch records that have <code>ID_RECURSO IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Recursos> fetchByIdRecurso(Long... values) {
        return fetch(Recursos.RECURSOS.ID_RECURSO, values);
    }

    /**
     * Fetch a unique record that has <code>ID_RECURSO = value</code>
     */
    public com.palominocia.JooqProject.modelo.tables.pojos.Recursos fetchOneByIdRecurso(Long value) {
        return fetchOne(Recursos.RECURSOS.ID_RECURSO, value);
    }

    /**
     * Fetch records that have <code>NOMBRE_RECURSO IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Recursos> fetchByNombreRecurso(String... values) {
        return fetch(Recursos.RECURSOS.NOMBRE_RECURSO, values);
    }

    /**
     * Fetch records that have <code>DIRECCION_RECURSO IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Recursos> fetchByDireccionRecurso(Long... values) {
        return fetch(Recursos.RECURSOS.DIRECCION_RECURSO, values);
    }

    /**
     * Fetch records that have <code>MCA_HABILITADO IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Recursos> fetchByMcaHabilitado(String... values) {
        return fetch(Recursos.RECURSOS.MCA_HABILITADO, values);
    }

    /**
     * Fetch records that have <code>FEC_ACTU IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Recursos> fetchByFecActu(Date... values) {
        return fetch(Recursos.RECURSOS.FEC_ACTU, values);
    }

    /**
     * Fetch records that have <code>ID_USUARIO_MODIF IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Recursos> fetchByIdUsuarioModif(String... values) {
        return fetch(Recursos.RECURSOS.ID_USUARIO_MODIF, values);
    }
}
