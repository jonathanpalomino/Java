
public interface AdministradorMensajes
{
	public void conectar(EscuchaMensajes escucha);
	public void desconectar(EscuchaMensajes escucha);
	public void enviarMensaje(String de,String mensaje);
}
