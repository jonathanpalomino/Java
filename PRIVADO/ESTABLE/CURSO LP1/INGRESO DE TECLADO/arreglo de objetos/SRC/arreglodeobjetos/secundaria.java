
package arreglodeobjetos;
import java.util.Vector;
class secundaria {
double neto;
Vector netoa= new Vector(100,1);//Inicializando vectores
Vector netotec= new Vector(100,1);
Vector netopro= new Vector(100,1);
Vector netofun= new Vector(100,1);
Vector netoaf= new Vector(100,1);
Vector netotecf= new Vector(100,1);
Vector netoprof= new Vector(100,1);
Vector netofunf= new Vector(100,1);
String cat,sexo;
    secundaria(double neto, String categoria, String sexo)
    {
    neto =this.neto;
    cat=categoria;
    sexo=this.sexo;
    }
void trabajo()
    {
    if(cat.compareToIgnoreCase("auxiliar")==0)
    {
        if(sexo.compareToIgnoreCase("M")==0)
        {
            netoa.add(neto);
        }
        else if(sexo.compareToIgnoreCase("F")==0)
        {netoaf.add(neto);
        }
    }
    else if(cat.compareToIgnoreCase("tecnico")==0)
    {
        if(sexo.compareToIgnoreCase("M")==0)
        {
            netotec.add(neto);
        }
        else if(sexo.compareToIgnoreCase("F")==0)
        {netotecf.add(neto);
        }
    }
    else if(cat.compareToIgnoreCase("profesional")==0)
    {
        if(sexo.compareToIgnoreCase("M")==0)
        {
            netopro.add(neto);
        }
        else if(sexo.compareToIgnoreCase("F")==0)
        { netoprof.add(neto);
        }
    }
    else if(cat.compareToIgnoreCase("funcionario")==0)
    {
        if(sexo.compareToIgnoreCase("M")==0)
        {
            netofun.add(neto);
        }
        else if(sexo.compareToIgnoreCase("F")==0)
        {
            netofunf.add(neto);
    }
    }
    }

}
