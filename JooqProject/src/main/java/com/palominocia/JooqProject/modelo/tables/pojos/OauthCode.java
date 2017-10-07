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
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 1654635817;

    private String error;
    private byte[] authentication;

    public OauthCode() {}

    public OauthCode(OauthCode value) {
        this.error = value.error;
        this.authentication = value.authentication;
    }

    public OauthCode(
        String error,
        byte[] authentication
    ) {
        this.error = error;
        this.authentication = authentication;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public byte[] getAuthentication() {
        return this.authentication;
    }

    public void setAuthentication(byte... authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OauthCode (");

        sb.append(error);
        sb.append(", ").append("[binary...]");

        sb.append(")");
        return sb.toString();
    }
}
