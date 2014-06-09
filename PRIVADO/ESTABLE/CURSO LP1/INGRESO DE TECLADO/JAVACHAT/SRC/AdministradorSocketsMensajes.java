
import java.util.*;
import java.net.*;
import java.io.*;
public class AdministradorSocketsMensajes implements AdministradorMensajes
{
	//Declarar objetos y variables de la clase
	private Socket socketCliente;
	private String direccionServidor;
	private SubprocesoReceptorPaquetes subprocesoReceptor;
	private boolean conectado=false;
	//constructor de AdministradorSocketsMensajes
	public AdministradorSocketsMensajes(String direccion)
	{
		direccionServidor=direccion;
	}
	//Sobrecargar el metodo conectar
	public void conectar(EscuchaMensajes escucha)
	{
		if(conectado)
			return;
		try
		{
			//Crear el socket del cliente
			socketCliente=new Socket(InetAddress.getByName(direccionServidor),Constantes.PUERTO_SERVIDOR);
			//Crear un hilo para recivir paquetes del servidor
			subprocesoReceptor=new SubprocesoReceptorPaquetes(escucha);
			subprocesoReceptor.start();
			//Establecer la bandera booleana como 'true'
			conectado = true;
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
	//Sobrecargar el metodo conectar
	public void desconectar(EscuchaMensajes escucha)
	{
		if(!conectado)
			return;
		try
		{
			//crear el hilo que envia los paquetes al servidor
			Thread desconectarSubproceso=new SubprocesoEmisor(socketCliente,"",Constantes.CADENA_DESCONEXION);
			desconectarSubproceso.start();
			desconectarSubproceso.join(10000);
			subprocesoReceptor.dejarDeEscuchar();
			//Cerrar el socket del cliente
			socketCliente.close();
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
		catch(InterruptedException excepcionInterrupcion)
		{
			excepcionInterrupcion.printStackTrace();
		}
		//Establecer la bandera de conexi�n en false
		conectado=false;
	}
	public void enviarMensaje(String de,String mensaje)
	{
		if(!conectado)
			return;
		//enviar paquetes al Servidor
		new SubprocesoEmisor(socketCliente,de,mensaje).start();
	}
}
