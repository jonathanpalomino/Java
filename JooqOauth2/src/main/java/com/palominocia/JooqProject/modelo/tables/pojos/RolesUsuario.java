/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class RolesUsuario implements Serializable {

    private static final long serialVersionUID = 1427011011;

    private String idUsuario;
    private Long   idRol;

    public RolesUsuario() {}

    public RolesUsuario(RolesUsuario value) {
        this.idUsuario = value.idUsuario;
        this.idRol = value.idRol;
    }

    public RolesUsuario(
        String idUsuario,
        Long   idRol
    ) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdRol() {
        return this.idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RolesUsuario (");

        sb.append(idUsuario);
        sb.append(", ").append(idRol);

        sb.append(")");
        return sb.toString();
    }
}