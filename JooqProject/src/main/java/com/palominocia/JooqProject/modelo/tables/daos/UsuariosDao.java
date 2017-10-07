/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables.daos;


import com.palominocia.JooqProject.modelo.tables.Usuarios;
import com.palominocia.JooqProject.modelo.tables.records.UsuariosRecord;

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
public class UsuariosDao extends DAOImpl<UsuariosRecord, com.palominocia.JooqProject.modelo.tables.pojos.Usuarios, String> {

    /**
     * Create a new UsuariosDao without any configuration
     */
    public UsuariosDao() {
        super(Usuarios.USUARIOS, com.palominocia.JooqProject.modelo.tables.pojos.Usuarios.class);
    }

    /**
     * Create a new UsuariosDao with an attached configuration
     */
    public UsuariosDao(Configuration configuration) {
        super(Usuarios.USUARIOS, com.palominocia.JooqProject.modelo.tables.pojos.Usuarios.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getId(com.palominocia.JooqProject.modelo.tables.pojos.Usuarios object) {
        return object.getIdUsuario();
    }

    /**
     * Fetch records that have <code>ID_USUARIO IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Usuarios> fetchByIdUsuario(String... values) {
        return fetch(Usuarios.USUARIOS.ID_USUARIO, values);
    }

    /**
     * Fetch a unique record that has <code>ID_USUARIO = value</code>
     */
    public com.palominocia.JooqProject.modelo.tables.pojos.Usuarios fetchOneByIdUsuario(String value) {
        return fetchOne(Usuarios.USUARIOS.ID_USUARIO, value);
    }

    /**
     * Fetch records that have <code>NOMBRE IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Usuarios> fetchByNombre(String... values) {
        return fetch(Usuarios.USUARIOS.NOMBRE, values);
    }

    /**
     * Fetch records that have <code>PASSWORD IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Usuarios> fetchByPassword(String... values) {
        return fetch(Usuarios.USUARIOS.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>DESCRIPCION IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Usuarios> fetchByDescripcion(String... values) {
        return fetch(Usuarios.USUARIOS.DESCRIPCION, values);
    }

    /**
     * Fetch records that have <code>MCA_HABILITADO IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Usuarios> fetchByMcaHabilitado(String... values) {
        return fetch(Usuarios.USUARIOS.MCA_HABILITADO, values);
    }

    /**
     * Fetch records that have <code>FEC_ACTU IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Usuarios> fetchByFecActu(Date... values) {
        return fetch(Usuarios.USUARIOS.FEC_ACTU, values);
    }

    /**
     * Fetch records that have <code>ID_USUARIO_MODIF IN (values)</code>
     */
    public List<com.palominocia.JooqProject.modelo.tables.pojos.Usuarios> fetchByIdUsuarioModif(String... values) {
        return fetch(Usuarios.USUARIOS.ID_USUARIO_MODIF, values);
    }
}
