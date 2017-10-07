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
public class RolesGrupos implements Serializable {

    private static final long serialVersionUID = -177292220;

    private Long idRol;
    private Long idGrupo;

    public RolesGrupos() {}

    public RolesGrupos(RolesGrupos value) {
        this.idRol = value.idRol;
        this.idGrupo = value.idGrupo;
    }

    public RolesGrupos(
        Long idRol,
        Long idGrupo
    ) {
        this.idRol = idRol;
        this.idGrupo = idGrupo;
    }

    public Long getIdRol() {
        return this.idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public Long getIdGrupo() {
        return this.idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RolesGrupos (");

        sb.append(idRol);
        sb.append(", ").append(idGrupo);

        sb.append(")");
        return sb.toString();
    }
}