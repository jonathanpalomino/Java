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
public class GrupoRecursos implements Serializable {

    private static final long serialVersionUID = -1956327990;

    private Long idGrupo;
    private Long idRecurso;

    public GrupoRecursos() {}

    public GrupoRecursos(GrupoRecursos value) {
        this.idGrupo = value.idGrupo;
        this.idRecurso = value.idRecurso;
    }

    public GrupoRecursos(
        Long idGrupo,
        Long idRecurso
    ) {
        this.idGrupo = idGrupo;
        this.idRecurso = idRecurso;
    }

    public Long getIdGrupo() {
        return this.idGrupo;
    }

    public void setIdGrupo(Long idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Long getIdRecurso() {
        return this.idRecurso;
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GrupoRecursos (");

        sb.append(idGrupo);
        sb.append(", ").append(idRecurso);

        sb.append(")");
        return sb.toString();
    }
}
