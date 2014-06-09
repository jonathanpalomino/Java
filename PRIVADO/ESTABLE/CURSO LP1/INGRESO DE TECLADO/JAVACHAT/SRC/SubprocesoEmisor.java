
import java.io.*;
import java.net.*;
public class SubprocesoEmisor extends Thread
{
	private Socket socketCliente;
	private String mensajeAEnviar;
	public SubprocesoEmisor(Socket socket,String nombreUsuario,String mensaje)
	{
		super("SubprocesoEmisor: "+socket);
		socketCliente=socket;
		mensajeAEnviar=nombreUsuario+Constantes.SEPARADOR_MENSAJE+mensaje;
	}
	public void run()
	{
		try
		{
			//Enviar los paquetes desde el cliente al servidor
			PrintWriter escritor=new PrintWriter(socketCliente.getOutputStream());
			escritor.println(mensajeAEnviar);
			//Obligar a enviar los datos inmediatamente y vaciar el buffer
			escritor.flush();
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
}
