
import java.io.*;
import java.net.*;
import java.util.*;
public class SubprocesoReceptorPaquetes extends Thread
{
	private EscuchaMensajes escuchaMensajes;
	private MulticastSocket socketMulticast;
	private InetAddress grupoMulticast;
	private boolean seguirEscuchando=true;
	public SubprocesoReceptorPaquetes(EscuchaMensajes escucha)
	{
		super("SubprocesoReceptorPaquetes");
		escuchaMensajes=escucha;
		try
		{
			socketMulticast=new MulticastSocket(Constantes.PUERTO_ESCUCHA_MULTICAST);
			grupoMulticast=InetAddress.getByName(Constantes.DIRECCION_MULTICAST);
			socketMulticast.joinGroup(grupoMulticast);
			socketMulticast.setSoTimeout(5000);
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
	public void run()
	{
		//Iterar, escuchando mensajes publicados en la direcci�n multicast
		while(seguirEscuchando)
		{
			byte[] bufer=new byte[512];
			DatagramPacket paquete=new DatagramPacket(bufer,512);
			try
			{
				socketMulticast.receive(paquete);
			}
			catch(InterruptedIOException excepcionESInterrupcion)
			{
				continue;
			}
			catch(IOException excepcionES)
			{
				excepcionES.printStackTrace();
				break;
			}
			String mensaje=new String(paquete.getData());
			mensaje=mensaje.trim();
			StringTokenizer tokenizer=new StringTokenizer(mensaje,Constantes.SEPARADOR_MENSAJE);
			if(tokenizer.countTokens()==2)
			{
				escuchaMensajes.mensajeRecibido(tokenizer.nextToken(),tokenizer.nextToken());
			}
		}
		try
		{
			socketMulticast.leaveGroup(grupoMulticast);
			socketMulticast.close();
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
	public void dejarDeEscuchar()
	{
		seguirEscuchando = false;
	}
}
