/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package biblioteca;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class Principal extends JFrame implements ActionListener{
String usuario="root";
String contraseña="123456789";
String url="jdbc:mysql://localhost:3306/programa_biblioteca";
public Principal(String usuarioo,String Contrasena)
{
    setSize(600, 600);
    setLocation(250, 100);
    JMenuBar barra = new JMenuBar();

    JMenu Archivo = new JMenu("Archivo");
    JMenuItem SubItem = new JMenuItem("Copia de Seguridad");
    SubItem.addActionListener(this);
    Archivo.add(SubItem);

    SubItem = new JMenuItem("Restaurar Copia de Seguridad");
    SubItem.addActionListener(this);
    Archivo.add(SubItem);

    SubItem = new JMenuItem("Salir");///
    SubItem.addActionListener(this);
    Archivo.add(SubItem);

    JMenu Catalogo = new JMenu("Catalogo");
    SubItem = new JMenuItem("Añadir Recurso");///
    SubItem.addActionListener(this);
    Catalogo.add(SubItem);

    SubItem = new JMenuItem("Buscar Recurso");///
    SubItem.addActionListener(this);
    Catalogo.add(SubItem);

    SubItem = new JMenuItem("Visualizar Temas");///
    SubItem.addActionListener(this);
    Catalogo.add(SubItem);

    JMenu Usuarios = new JMenu("Usuarios");
    SubItem = new JMenuItem("Añadir Usuarios");///
    SubItem.addActionListener(this);
    Usuarios.add(SubItem);

    SubItem = new JMenuItem("Suprimir Usuarios");///
    SubItem.addActionListener(this);
    Usuarios.add(SubItem);

    SubItem = new JMenuItem("Editar Usuarios");///
    SubItem .addActionListener(this);
    Usuarios.add(SubItem);

    JMenu SubMenu = new JMenu("Buscar Usuarios");///
    SubMenu.addActionListener(this);
    Usuarios.add(SubMenu);

    SubItem = new JMenuItem("Por Apellidos");///
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);

    SubItem = new JMenuItem("Por Codigo");///
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);

    SubItem = new JMenuItem("DNI O CODIGO exacto");///
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);

    SubItem = new JMenuItem("Listar Usuarios");///
    SubItem.addActionListener(this);
    Usuarios.add(SubItem);

    JMenu Prestamo = new JMenu("Pestamos");
    SubItem = new JMenuItem("Prestamos");///
    SubItem.addActionListener(this);
    Prestamo.add(SubItem);

    SubMenu = new JMenu("Devoluciones");/////
    SubMenu.addActionListener(this);
    Prestamo.add(SubMenu);

    SubItem = new JMenuItem("General");////
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);

    SubItem = new JMenuItem("Autor");///
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);

    SubItem = new JMenuItem("Usuario");///
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);


    SubMenu = new JMenu("Listado");
    SubMenu.addActionListener(this);
    Prestamo.add(SubMenu);

    SubItem = new JMenuItem("Totales");///
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);

    SubItem = new JMenuItem("Por Fechas");///
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);
    
    SubItem = new JMenuItem("Por Morosos");///
    SubItem.addActionListener(this);
    SubMenu.add(SubItem);

    JMenu Estadistica = new JMenu("Contacto con nosotros");
    SubItem = new JMenuItem("Sugerencias");
    SubItem.addActionListener(this);
    Estadistica.add(SubItem);

    JMenu Configuracion = new JMenu("Configuracion");
    
    SubItem = new JMenuItem("Notas y Registros");///
    SubItem.addActionListener(this);
    Configuracion.add(SubItem);

    SubItem = new JMenuItem("Respaldar BD al servidor");///
    SubItem.addActionListener(this);
    Configuracion.add(SubItem);

    JMenu Ayuda = new JMenu("Ayuda");
    

    SubItem = new JMenuItem("Acerca de...");///
    SubItem.addActionListener(this);
    Ayuda.add(SubItem);


    barra.add(Archivo);
    barra.add(Catalogo);
    barra.add(Usuarios);
    barra.add(Prestamo);
    barra.add(Estadistica);
    barra.add(Configuracion);
    barra.add(Ayuda);
    setJMenuBar(barra);

    //Autentificar

    if(usuarioo.equals("Administrador")&&Contrasena.equals("123"))
    {
        setTitle("Consola de Administracion de Biblioteca");
    }
    else if(usuarioo.equals("")&&Contrasena.equals(""))
    {
        setTitle("Busqueda de Libros en Biblioteca");
        Archivo.setEnabled(false);
        Archivo.setText("");
        Prestamo.setEnabled(false);
        Prestamo.setText("");
        Configuracion.setEnabled(false);
        Configuracion.setText("");
        Usuarios.setEnabled(false);
        Usuarios.setText("");
        Catalogo.remove(0);//  Elimina añadir Recurso

    }

    this.addWindowListener(new WindowAdapter()
       {
           public void windowClosing(WindowEvent e)
           {
            System.exit(0);
           }
    }) ;
}

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Salir"))
        {
            setVisible(false);
        }
        else if(e.getActionCommand().equals("Añadir Recurso"))
        {
            Libro_nuevo ob = new Libro_nuevo(usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Añadir Usuarios"))
        {
            Lector_nuevo ob = new Lector_nuevo("","Nuevo Lector",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Prestamos"))
        {
            Prestamos ob = new Prestamos(usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Buscar Recurso"))
        {
            Busqueda ob = new Busqueda("Libro","Codigo", "Titulo", "Autor", "Editorial","Codigo", "Titulo", "Autor", "Editorial",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Por Apellidos"))
        {
            Busqueda ob = new Busqueda("Usuario", "Apellido",null,null,null,"Codigo","Nombre","Apellido","Escuela",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Por Codigo"))
         {
            Busqueda ob = new Busqueda("Usuario", "Codigo",null,null,null,"Codigo","Nombre","Apellido","Escuela",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("DNI O CODIGO exacto"))
        {
            Lector_nuevo obj = new Lector_nuevo("Buscar","Busqueda por Criterios",usuario,contraseña,url);
            obj.show();
        }
        else if(e.getActionCommand().equals("Suprimir Usuarios"))
        {
            Lector_nuevo obj = new Lector_nuevo("Eliminar","Eliminar Usuarios",usuario,contraseña,url);
            obj.show();
        }
        else if (e.getActionCommand().equals("Editar Usuarios"))
        {
            Lector_nuevo obj = new Lector_nuevo("Editar","Editar Usuarios",usuario,contraseña,url);
            obj.show();
        }
        else if (e.getActionCommand().equals("Listar Usuarios"))
        {
            Busqueda obj = new Busqueda("Listar",null,null,null,null,"Codigo","Nombre","Apellido","Email",usuario,contraseña,url);
            obj.show();
        }
        else if(e.getActionCommand().equals("Visualizar Temas"))
        {
            Temas obj = new Temas("Visualizar");
            obj.show();
        }
        else if(e.getActionCommand().equals("General"))
        {
            Devoluciones ob = new Devoluciones("General",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Autor"))
        {
            Devoluciones ob = new Devoluciones("Autor",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Usuario"))
        {
            Devoluciones ob = new Devoluciones("Usuario",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Totales"))
        {
            Devoluciones ob = new Devoluciones("Totales",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Por Fechas"))
        {
            Devoluciones ob = new Devoluciones("Por Fechas",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Por Morosos"))
        {
            Devoluciones ob = new Devoluciones("Por Morosos",usuario,contraseña,url);
            ob.show();
        }
        else if(e.getActionCommand().equals("Copia de Seguridad"))
        {
            GuardarBase();
        }
        else if(e.getActionCommand().equals("Restaurar Copia de Seguridad"))
        {
            AbrirBase();
        }
        else if(e.getActionCommand().equals("Notas y Registros"))
        {
            Notas ob = new Notas();
            ob.show();
        }
        else if(e.getActionCommand().equals("Acerca de..."))
        {
            Creditos ob = new Creditos();
            ob.show();
        }
        else if(e.getActionCommand().equals("Sugerencias"))
        {
            contacto obj = new contacto();
            obj.show();
        }
        else if(e.getActionCommand().equals("Respaldar BD al servidor"))
        {
            datos obj = new datos();
            obj.show();
        }
    }

public void GuardarBase()
    {
    try{
Runtime runtime = Runtime.getRuntime();
File backupFile = new File("C:\\Respaldo" + ".sql");
/*backupFile se utiliza para indicarle la ubicacion y nombre del archivo que contendra el backup con la extencion .sql*/

FileWriter fw = new FileWriter(backupFile);
/*objeto que escribira sobre el backup archivo*/

Process child = runtime.exec("C:\\AppServ\\MySQL\\bin\\mysqldump --opt --password=123456789 --user=root programa_biblioteca");
/*Process es el que ejecuta el comando para buscar el mysqldump.exe*/
InputStreamReader irs = new InputStreamReader(child.getInputStream());
BufferedReader br = new BufferedReader(irs);
/* se escribe sobre el archivo*/
String line;
while( (line=br.readLine()) != null ) {
fw.write(line + "\n");
}
fw.close();
irs.close();
br.close();
}catch(Exception e){
e.printStackTrace();
JOptionPane.showMessageDialog(null, "Error no se genero el archivo por el siguiente motivo: " + e.getMessage(), "Verificar",JOptionPane.ERROR_MESSAGE);
}
JOptionPane.showMessageDialog(null, "Archivo generado", "Verificar",JOptionPane.INFORMATION_MESSAGE);
    }

    public void AbrirBase()
    {
try{
String ubicacion= String.valueOf("C:\\Respaldo" + ".sql");
/*Nuevamente fileChooser para indicarle donde esta el archivoBackUp*/
Process child = Runtime.getRuntime().exec("cmd /c mysql --password= --user=root [NameDB] < " + ubicacion);

}catch(Exception e){
JOptionPane.showMessageDialog(null, "Error no se actualizo la DB por el siguiente motivo: " + e.getMessage(), "Verificar",JOptionPane.ERROR_MESSAGE);
e.printStackTrace();
}
JOptionPane.showMessageDialog(null, "Base Actualizada", "Verificar",JOptionPane.INFORMATION_MESSAGE);
    }


    }



