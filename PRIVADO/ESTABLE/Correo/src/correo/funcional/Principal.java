/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package correo.funcional;

/**
 *
 * @author JONATHAN
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Principal {

    private String correo;
    private String contrasena;
    private String host;
    private Integer puerto;
    private boolean auth;
    private String destinatario;
    private String protocolo;
    
    public Principal() {
        setCorreo("jonathan-palomino@hotmail.com");
        setContrasena("777@jonathan\\");
        setPuerto(25);
        setHost("smtp.live.com");
        setAuth(true);
        setDestinatario("jonathan.palomino.vilca@gmail.com");
        setProtocolo("smtp");
        
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", getHost());
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", String.valueOf(getPuerto()));
            props.setProperty("mail.smtp.user", getCorreo());
            props.setProperty("mail.smtp.auth", String.valueOf(getAuth()));
            
            System.out.println("Enviando…");
// Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

// Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getCorreo()));
            message.addRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(getDestinatario()));
            message.setSubject("Prueba");
            message.setText("Esto es una prueba…");

// Lo enviamos.
            Transport t = session.getTransport(getProtocolo());
            t.connect(getCorreo(), getContrasena());
            t.sendMessage(message, message.getAllRecipients());

// Cierre.
            t.close();
            
            System.out.println("\n\nEmail Enviado!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Principal obj = new Principal();
    
        
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the puerto
     */
    public Integer getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the auth
     */
    public boolean getAuth() {
        return auth;
    }

    /**
     * @param auth the auth to set
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    /**
     * @return the destinatario
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * @param destinatario the destinatario to set
     */
    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * @return the protocolo
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * @param protocolo the protocolo to set
     */
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
