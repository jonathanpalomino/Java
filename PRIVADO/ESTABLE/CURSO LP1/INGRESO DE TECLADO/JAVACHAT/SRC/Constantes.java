
public interface Constantes
{
	//Direccion donde se envian los paquetes, y donde los 
	//clientes escuchan esos paquetes
	public static final String DIRECCION_MULTICAST="239.0.0.1";
	//Puerto donde los clientes escuchan los paquetes
	public static final int PUERTO_ESCUCHA_MULTICAST=5555;
	//Puerto donde el servidor envia los paquetes
	public static final int PUERTO_ENVIO_MULTICAST=5554;
	////Puerto donde los clientes se conectan al servidor
	public static final int PUERTO_SERVIDOR=12345;
	//Cadena que indica la desconexion de un usuario
	public static final String CADENA_DESCONEXION="DESCONECTAR";
	//Sirve para separar en los paquetes el usuario y el mensaje
	public static final String SEPARADOR_MENSAJE=">>>";
	//Esta cadena indica que un usuario se conecta de nuevo.
	public static final String NUEVO_USUARIO="��casidiablochat��casidiablochat��";
}
