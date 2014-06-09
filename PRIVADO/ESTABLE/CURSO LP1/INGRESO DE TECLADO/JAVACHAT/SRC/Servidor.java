
import java.util.*;
import java.net.*;
import java.io.*;
public class Servidor implements EscuchaMensajes
{
	//Contador de usuarios
	private int cont=0;
	//Lista usuarios
	private String[] registro={" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
	public void iniciarServidor()
	{
		try
		{
			//Se crea un objeto ServerSocket para recivir conexiones
			ServerSocket socketServidor=new ServerSocket(Constantes.PUERTO_SERVIDOR,100);
			System.out.println("Servidor escuchando en el puerto "+Constantes.PUERTO_SERVIDOR+"...");
			//Itera infinitamente esperando conexiones
			while(true)
			{
				//Crear el socket para el cliente
				Socket socketCliente=socketServidor.accept();
				//Inicia un hilo SubprocesoReceptor para cada cliente
				new SubprocesoReceptor(this,socketCliente).start();
				System.out.println("Conexion recibida de: "+socketCliente.getInetAddress());
			}
		}
		catch(IOException excepcionES)
		{
			excepcionES.printStackTrace();
		}
	}
	//Sobrecagando el metodod de la intergaz EscuchaMensajes
	public void mensajeRecibido(String de,String mensaje)
	{
		//verfificar si se conect� un nuevo usuario
		if(mensaje.equals(Constantes.NUEVO_USUARIO))
		{
			for(int i=0;i<20;i++)
			{
				if(registro[i].equals(de))
				{
					de+=""+cont;
					break;
				}
			}
			registro[cont]=de;
			++cont;
			de="";
			for(int i=0;i<20;i++)
				de+=registro[i]+".....";
		}
		//verfificar si se desconect� un nuevo usuario
		else if(mensaje.equals(Constantes.CADENA_DESCONEXION))
		{
			String[] temp={" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
			int j=0;
			for(int i=0;i<20;i++,j++)
			{
				if(!registro[i].equals(de))
					temp[j]=registro[i];
				else
					--j;
			}
			for(int i=0;i<20;i++)
				registro[i]=temp[i];
			--cont;
		}
		//Mandar a todos los clientes el mensaje
		String mensajeCompleto=de+Constantes.SEPARADOR_MENSAJE + mensaje;
		new SubprocesoEnvioMulticast(mensajeCompleto.getBytes()).start();
	}
	public static void main(String args[])
	{
		new Servidor().iniciarServidor();
	}
}
