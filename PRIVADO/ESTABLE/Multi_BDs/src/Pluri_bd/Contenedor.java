/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pluri_bd;

import java.sql.Connection;

/**
 *
 * @author JONATHAN
 */
public class Contenedor {
    private Connection enlace;
    private String url_Servidor;
    private MSSQL objeto_Sql;
    private MYSQL objeto_Mysql;
    private ACCESS objeto_access;
    private ORACLE objeto_oracle;
    
     /**
     * @param null Sin parametros se asume que desea cargar todos los conectores
     * incluye (mysql en version 5)
     */
    public Contenedor() {
        objeto_Sql = new MSSQL(this);
        objeto_Mysql = new MYSQL(this,"5");
        objeto_access = new ACCESS(this);
        objeto_oracle = new ORACLE(this);
    }
     /**
     * @param p_tip_bd el tipo de base de datos en este constructor solo se permite MYSQL
     * @param version la version 4 o 5 para mysql
     */
    public Contenedor(String p_tip_bd,String version) {
        if(p_tip_bd.equals("MYSQL")){
            objeto_Mysql = new MYSQL(this,version);
        }
    }
    
     /**
     * @param p_tip_bd el tipo de base de datos a usar (MSSQL, ACCESS, ORACLE)
     */
        public Contenedor(String p_tip_bd) {
        if(p_tip_bd.equals("MSSQL")){
           objeto_Sql = new MSSQL(this); 
        }
        else if(p_tip_bd.equals("ACCESS")){
            objeto_access = new ACCESS(this);
        }
        else if(p_tip_bd.equals("ORACLE")){
            objeto_oracle = new ORACLE(this);
        }
    }
    
    /**
     * @return el objeto de tipo enlace
     */
    public Connection getEnlace() {
        return enlace;
    }

    /**
     * @param enlace el objeto de tipo enlace
     */
    public void setEnlace(Connection enlace) {
        this.enlace = enlace;
    }

    protected  void setUrl_Servidor(String url_Servidor) {
        this.url_Servidor=url_Servidor;
    }
    public String getUrl_Servidor() {
        return url_Servidor;
    }

    /**
     * @return the objeto_Sql
     */
    public MSSQL getCONECTOR_SQL() {
        return objeto_Sql;
    }

    /**
     * @return the objeto_Mysql
     */
    public MYSQL getCONECTOR_Mysql() {
        return objeto_Mysql;
    }

    /**
     * @return the objeto_access
     */
    public ACCESS getCONECTOR_access() {
        return objeto_access;
    }

    /**
     * @return the objeto_oracle
     */
    public ORACLE getCONECTOR_ORACLE() {
        return objeto_oracle;
    }


}
