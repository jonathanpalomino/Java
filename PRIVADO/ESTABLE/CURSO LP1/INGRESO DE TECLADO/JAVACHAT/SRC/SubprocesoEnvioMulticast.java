
import java.io.*;
import java.net.*;
public class SubprocesoEnvioMulticast extends Thread
{
	private byte[] bytesMensaje;
	public SubprocesoEnvioMulticast(byte[] bytes)
	{
		super( "SubprocesoEnvioMulticast" );
		bytesMensaje = bytes;
	}
	public void run()
	{
		try
		{
			//Enviar paquetes a la direccion multicast
			DatagramSocket socket=new DatagramSocket(Constantes.PUERTO_ENVIO_MULTICAST);
			InetAddress grupo=InetAddress.getByName(Constantes.DIRECCION_MULTICAST);
			DatagramPacket paquete=new DatagramPacket(bytesMensaje,bytesMensaje.length,grupo,Constantes.PUERTO_ESCUCHA_MULTICAST);
			socket.send(paquete);
			socket.close();
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
}
