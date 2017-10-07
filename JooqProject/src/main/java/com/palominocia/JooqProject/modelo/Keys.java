/*
 * This file is generated by jOOQ.
*/
package com.palominocia.JooqProject.modelo;


import com.palominocia.JooqProject.modelo.tables.Clientes;
import com.palominocia.JooqProject.modelo.tables.GrupoRecursos;
import com.palominocia.JooqProject.modelo.tables.Grupos;
import com.palominocia.JooqProject.modelo.tables.OauthAccessToken;
import com.palominocia.JooqProject.modelo.tables.OauthClientDetails;
import com.palominocia.JooqProject.modelo.tables.OauthClientToken;
import com.palominocia.JooqProject.modelo.tables.Recursos;
import com.palominocia.JooqProject.modelo.tables.Roles;
import com.palominocia.JooqProject.modelo.tables.RolesGrupos;
import com.palominocia.JooqProject.modelo.tables.RolesUsuario;
import com.palominocia.JooqProject.modelo.tables.UsuarioTransaccion;
import com.palominocia.JooqProject.modelo.tables.Usuarios;
import com.palominocia.JooqProject.modelo.tables.records.ClientesRecord;
import com.palominocia.JooqProject.modelo.tables.records.GrupoRecursosRecord;
import com.palominocia.JooqProject.modelo.tables.records.GruposRecord;
import com.palominocia.JooqProject.modelo.tables.records.OauthAccessTokenRecord;
import com.palominocia.JooqProject.modelo.tables.records.OauthClientDetailsRecord;
import com.palominocia.JooqProject.modelo.tables.records.OauthClientTokenRecord;
import com.palominocia.JooqProject.modelo.tables.records.RecursosRecord;
import com.palominocia.JooqProject.modelo.tables.records.RolesGruposRecord;
import com.palominocia.JooqProject.modelo.tables.records.RolesRecord;
import com.palominocia.JooqProject.modelo.tables.records.RolesUsuarioRecord;
import com.palominocia.JooqProject.modelo.tables.records.UsuarioTransaccionRecord;
import com.palominocia.JooqProject.modelo.tables.records.UsuariosRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>autentificacion</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<ClientesRecord, Long> IDENTITY_CLIENTES = Identities0.IDENTITY_CLIENTES;
    public static final Identity<GruposRecord, Long> IDENTITY_GRUPOS = Identities0.IDENTITY_GRUPOS;
    public static final Identity<RecursosRecord, Long> IDENTITY_RECURSOS = Identities0.IDENTITY_RECURSOS;
    public static final Identity<RolesRecord, Long> IDENTITY_ROLES = Identities0.IDENTITY_ROLES;
    public static final Identity<UsuarioTransaccionRecord, Long> IDENTITY_USUARIO_TRANSACCION = Identities0.IDENTITY_USUARIO_TRANSACCION;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ClientesRecord> CLIENTES_PK = UniqueKeys0.CLIENTES_PK;
    public static final UniqueKey<GruposRecord> GRUPOS_PK = UniqueKeys0.GRUPOS_PK;
    public static final UniqueKey<GrupoRecursosRecord> GRUPO_RECURSOS_PK = UniqueKeys0.GRUPO_RECURSOS_PK;
    public static final UniqueKey<RecursosRecord> RECURSOS_PK = UniqueKeys0.RECURSOS_PK;
    public static final UniqueKey<RolesRecord> ROLES_PK = UniqueKeys0.ROLES_PK;
    public static final UniqueKey<RolesGruposRecord> ROLES_GRUPOS_PK = UniqueKeys0.ROLES_GRUPOS_PK;
    public static final UniqueKey<RolesUsuarioRecord> ROLES_USUARIO_PK = UniqueKeys0.ROLES_USUARIO_PK;
    public static final UniqueKey<UsuariosRecord> USUARIO_PK = UniqueKeys0.USUARIO_PK;
    public static final UniqueKey<UsuarioTransaccionRecord> USUARIO_TRANSACCION_PK = UniqueKeys0.USUARIO_TRANSACCION_PK;
    public static final UniqueKey<OauthAccessTokenRecord> OAUTH_ACCESS_TOKEN_PK = UniqueKeys0.OAUTH_ACCESS_TOKEN_PK;
    public static final UniqueKey<OauthClientDetailsRecord> OAUTH_CLIENT_DETAILS_PK = UniqueKeys0.OAUTH_CLIENT_DETAILS_PK;
    public static final UniqueKey<OauthClientTokenRecord> OAUTH_CLIENT_TOKEN_PK = UniqueKeys0.OAUTH_CLIENT_TOKEN_PK;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<GruposRecord, UsuariosRecord> GRUPOS__FK_ID_USUARIO_GRUPOS = ForeignKeys0.GRUPOS__FK_ID_USUARIO_GRUPOS;
    public static final ForeignKey<GrupoRecursosRecord, GruposRecord> GRUPO_RECURSOS__FK_GRUPOS_RECURSOS = ForeignKeys0.GRUPO_RECURSOS__FK_GRUPOS_RECURSOS;
    public static final ForeignKey<GrupoRecursosRecord, RecursosRecord> GRUPO_RECURSOS__FK_RECURSOS_GRUPOS = ForeignKeys0.GRUPO_RECURSOS__FK_RECURSOS_GRUPOS;
    public static final ForeignKey<RecursosRecord, UsuariosRecord> RECURSOS__FK_ID_USUARIO_RECURSOS = ForeignKeys0.RECURSOS__FK_ID_USUARIO_RECURSOS;
    public static final ForeignKey<RolesRecord, UsuariosRecord> ROLES__FK_ID_USUARIO_ROLES = ForeignKeys0.ROLES__FK_ID_USUARIO_ROLES;
    public static final ForeignKey<RolesGruposRecord, RolesRecord> ROLES_GRUPOS__FK_ROLES_GRUPOS = ForeignKeys0.ROLES_GRUPOS__FK_ROLES_GRUPOS;
    public static final ForeignKey<RolesGruposRecord, GruposRecord> ROLES_GRUPOS__FK_GRUPOS_ROLES = ForeignKeys0.ROLES_GRUPOS__FK_GRUPOS_ROLES;
    public static final ForeignKey<RolesUsuarioRecord, UsuariosRecord> ROLES_USUARIO__FK_USUARIOS_ROLES = ForeignKeys0.ROLES_USUARIO__FK_USUARIOS_ROLES;
    public static final ForeignKey<RolesUsuarioRecord, RolesRecord> ROLES_USUARIO__FK_ROLES_USUARIOS = ForeignKeys0.ROLES_USUARIO__FK_ROLES_USUARIOS;
    public static final ForeignKey<UsuariosRecord, UsuariosRecord> USUARIOS__FK_ID_USUARIO_USUARIOS = ForeignKeys0.USUARIOS__FK_ID_USUARIO_USUARIOS;
    public static final ForeignKey<UsuarioTransaccionRecord, UsuariosRecord> USUARIO_TRANSACCION__FK_USUARIOS = ForeignKeys0.USUARIO_TRANSACCION__FK_USUARIOS;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<ClientesRecord, Long> IDENTITY_CLIENTES = createIdentity(Clientes.CLIENTES, Clientes.CLIENTES.ID_CLIENTE);
        public static Identity<GruposRecord, Long> IDENTITY_GRUPOS = createIdentity(Grupos.GRUPOS, Grupos.GRUPOS.ID_GRUPO);
        public static Identity<RecursosRecord, Long> IDENTITY_RECURSOS = createIdentity(Recursos.RECURSOS, Recursos.RECURSOS.ID_RECURSO);
        public static Identity<RolesRecord, Long> IDENTITY_ROLES = createIdentity(Roles.ROLES, Roles.ROLES.ID_ROL);
        public static Identity<UsuarioTransaccionRecord, Long> IDENTITY_USUARIO_TRANSACCION = createIdentity(UsuarioTransaccion.USUARIO_TRANSACCION, UsuarioTransaccion.USUARIO_TRANSACCION.ID_TRANSACCION);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<ClientesRecord> CLIENTES_PK = createUniqueKey(Clientes.CLIENTES, "CLIENTES_PK", Clientes.CLIENTES.ID_CLIENTE);
        public static final UniqueKey<GruposRecord> GRUPOS_PK = createUniqueKey(Grupos.GRUPOS, "GRUPOS_PK", Grupos.GRUPOS.ID_GRUPO);
        public static final UniqueKey<GrupoRecursosRecord> GRUPO_RECURSOS_PK = createUniqueKey(GrupoRecursos.GRUPO_RECURSOS, "GRUPO_RECURSOS_PK", GrupoRecursos.GRUPO_RECURSOS.ID_GRUPO, GrupoRecursos.GRUPO_RECURSOS.ID_RECURSO);
        public static final UniqueKey<RecursosRecord> RECURSOS_PK = createUniqueKey(Recursos.RECURSOS, "RECURSOS_PK", Recursos.RECURSOS.ID_RECURSO);
        public static final UniqueKey<RolesRecord> ROLES_PK = createUniqueKey(Roles.ROLES, "ROLES_PK", Roles.ROLES.ID_ROL);
        public static final UniqueKey<RolesGruposRecord> ROLES_GRUPOS_PK = createUniqueKey(RolesGrupos.ROLES_GRUPOS, "ROLES_GRUPOS_PK", RolesGrupos.ROLES_GRUPOS.ID_ROL, RolesGrupos.ROLES_GRUPOS.ID_GRUPO);
        public static final UniqueKey<RolesUsuarioRecord> ROLES_USUARIO_PK = createUniqueKey(RolesUsuario.ROLES_USUARIO, "ROLES_USUARIO_PK", RolesUsuario.ROLES_USUARIO.ID_USUARIO, RolesUsuario.ROLES_USUARIO.ID_ROL);
        public static final UniqueKey<UsuariosRecord> USUARIO_PK = createUniqueKey(Usuarios.USUARIOS, "USUARIO_PK", Usuarios.USUARIOS.ID_USUARIO);
        public static final UniqueKey<UsuarioTransaccionRecord> USUARIO_TRANSACCION_PK = createUniqueKey(UsuarioTransaccion.USUARIO_TRANSACCION, "USUARIO_TRANSACCION_PK", UsuarioTransaccion.USUARIO_TRANSACCION.ID_TRANSACCION, UsuarioTransaccion.USUARIO_TRANSACCION.ID_USUARIO);
        public static final UniqueKey<OauthAccessTokenRecord> OAUTH_ACCESS_TOKEN_PK = createUniqueKey(OauthAccessToken.OAUTH_ACCESS_TOKEN, "oauth_access_token_pk", OauthAccessToken.OAUTH_ACCESS_TOKEN.AUTHENTICATION_ID);
        public static final UniqueKey<OauthClientDetailsRecord> OAUTH_CLIENT_DETAILS_PK = createUniqueKey(OauthClientDetails.OAUTH_CLIENT_DETAILS, "oauth_client_details_pk", OauthClientDetails.OAUTH_CLIENT_DETAILS.CLIENT_ID);
        public static final UniqueKey<OauthClientTokenRecord> OAUTH_CLIENT_TOKEN_PK = createUniqueKey(OauthClientToken.OAUTH_CLIENT_TOKEN, "oauth_client_token_pk", OauthClientToken.OAUTH_CLIENT_TOKEN.AUTHENTICATION_ID);
    }

    private static class ForeignKeys0 extends AbstractKeys {
        public static final ForeignKey<GruposRecord, UsuariosRecord> GRUPOS__FK_ID_USUARIO_GRUPOS = createForeignKey(com.palominocia.JooqProject.modelo.Keys.USUARIO_PK, Grupos.GRUPOS, "GRUPOS__FK_ID_USUARIO_GRUPOS", Grupos.GRUPOS.ID_USUARIO_MODIF);
        public static final ForeignKey<GrupoRecursosRecord, GruposRecord> GRUPO_RECURSOS__FK_GRUPOS_RECURSOS = createForeignKey(com.palominocia.JooqProject.modelo.Keys.GRUPOS_PK, GrupoRecursos.GRUPO_RECURSOS, "GRUPO_RECURSOS__FK_GRUPOS_RECURSOS", GrupoRecursos.GRUPO_RECURSOS.ID_GRUPO);
        public static final ForeignKey<GrupoRecursosRecord, RecursosRecord> GRUPO_RECURSOS__FK_RECURSOS_GRUPOS = createForeignKey(com.palominocia.JooqProject.modelo.Keys.RECURSOS_PK, GrupoRecursos.GRUPO_RECURSOS, "GRUPO_RECURSOS__FK_RECURSOS_GRUPOS", GrupoRecursos.GRUPO_RECURSOS.ID_RECURSO);
        public static final ForeignKey<RecursosRecord, UsuariosRecord> RECURSOS__FK_ID_USUARIO_RECURSOS = createForeignKey(com.palominocia.JooqProject.modelo.Keys.USUARIO_PK, Recursos.RECURSOS, "RECURSOS__FK_ID_USUARIO_RECURSOS", Recursos.RECURSOS.ID_USUARIO_MODIF);
        public static final ForeignKey<RolesRecord, UsuariosRecord> ROLES__FK_ID_USUARIO_ROLES = createForeignKey(com.palominocia.JooqProject.modelo.Keys.USUARIO_PK, Roles.ROLES, "ROLES__FK_ID_USUARIO_ROLES", Roles.ROLES.ID_USUARIO_MODIF);
        public static final ForeignKey<RolesGruposRecord, RolesRecord> ROLES_GRUPOS__FK_ROLES_GRUPOS = createForeignKey(com.palominocia.JooqProject.modelo.Keys.ROLES_PK, RolesGrupos.ROLES_GRUPOS, "ROLES_GRUPOS__FK_ROLES_GRUPOS", RolesGrupos.ROLES_GRUPOS.ID_ROL);
        public static final ForeignKey<RolesGruposRecord, GruposRecord> ROLES_GRUPOS__FK_GRUPOS_ROLES = createForeignKey(com.palominocia.JooqProject.modelo.Keys.GRUPOS_PK, RolesGrupos.ROLES_GRUPOS, "ROLES_GRUPOS__FK_GRUPOS_ROLES", RolesGrupos.ROLES_GRUPOS.ID_GRUPO);
        public static final ForeignKey<RolesUsuarioRecord, UsuariosRecord> ROLES_USUARIO__FK_USUARIOS_ROLES = createForeignKey(com.palominocia.JooqProject.modelo.Keys.USUARIO_PK, RolesUsuario.ROLES_USUARIO, "ROLES_USUARIO__FK_USUARIOS_ROLES", RolesUsuario.ROLES_USUARIO.ID_USUARIO);
        public static final ForeignKey<RolesUsuarioRecord, RolesRecord> ROLES_USUARIO__FK_ROLES_USUARIOS = createForeignKey(com.palominocia.JooqProject.modelo.Keys.ROLES_PK, RolesUsuario.ROLES_USUARIO, "ROLES_USUARIO__FK_ROLES_USUARIOS", RolesUsuario.ROLES_USUARIO.ID_ROL);
        public static final ForeignKey<UsuariosRecord, UsuariosRecord> USUARIOS__FK_ID_USUARIO_USUARIOS = createForeignKey(com.palominocia.JooqProject.modelo.Keys.USUARIO_PK, Usuarios.USUARIOS, "USUARIOS__FK_ID_USUARIO_USUARIOS", Usuarios.USUARIOS.ID_USUARIO_MODIF);
        public static final ForeignKey<UsuarioTransaccionRecord, UsuariosRecord> USUARIO_TRANSACCION__FK_USUARIOS = createForeignKey(com.palominocia.JooqProject.modelo.Keys.USUARIO_PK, UsuarioTransaccion.USUARIO_TRANSACCION, "USUARIO_TRANSACCION__FK_USUARIOS", UsuarioTransaccion.USUARIO_TRANSACCION.ID_USUARIO);
    }
}