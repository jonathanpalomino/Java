
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
public class SubprocesoReceptor extends Thread
{
	private BufferedReader entrada;
	private EscuchaMensajes escuchaMensajes;
	private boolean seguirEscuchando = true;
	//Constructor de SubprocesoReceptor
	public SubprocesoReceptor(EscuchaMensajes escucha,Socket socketCliente)
	{
		super("SubprocesoReceptor: "+socketCliente);
		escuchaMensajes=escucha;
		try
		{
			//Establecer tiempo fuera del socket
			socketCliente.setSoTimeout(5000);
			//Establecer flujos de entrada
			entrada=new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
	public void run()
	{
		String mensaje;
		//Iterar infinitamente escuchando mensajes
		while(seguirEscuchando)
		{
			try
			{
				//Leer mensaje del cliente
				mensaje=entrada.readLine();
			}
			catch(InterruptedIOException excepcionInterrupcion)
			{
				//Reestablece el ciclo para seguir escuchando
				continue;
			}
			catch(IOException excepcionES)
			{
				excepcionES.printStackTrace();
				break;
			}
			//Si el mensaje es valido enviarselo al servidor
			if(mensaje!=null)
			{
				//Separar el mensaje en tokens (destinatario y Mensajes)
				StringTokenizer tokenizer=new StringTokenizer(mensaje,Constantes.SEPARADOR_MENSAJE);
				if(tokenizer.countTokens()==2)
					escuchaMensajes.mensajeRecibido(tokenizer.nextToken(),tokenizer.nextToken());
				else if(mensaje.equalsIgnoreCase(Constantes.SEPARADOR_MENSAJE+Constantes.CADENA_DESCONEXION))
					dejarDeEscuchar();
			}
		}
		try
		{
			//Cerrar el flujo de entrada
			entrada.close();
		}
		catch (IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
	//Cancela el bucle While, para que no escuche m�s mensajes
	public void dejarDeEscuchar()
	{
		seguirEscuchando=false;
	}
}
