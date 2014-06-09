/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.Reflexion;

/**
 *
 * @author JONATHAN
 */
public class User {

    private String alias = null;
    public String name;
    public String address="abc";

    public User(String name) {
        this.name = name;
    }

    /**
     * @return the alias
     */
    public String getAlias() {
        if (alias == null) {
            return name;
        } else {
            return alias;
        }
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}
