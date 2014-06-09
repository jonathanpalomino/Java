
package palomino.multimedia.audio.codec;

import java.util.Collection;
import java.util.Iterator;

/**
 * This class implements a threaded events launcher.
 */
public class ReproductorLanzarEvento extends Thread
{
    /**
	 * @uml.property  name="code"
	 */
    private int code = -1;
    /**
	 * @uml.property  name="position"
	 */
    private int position = -1;
    /**
	 * @uml.property  name="value"
	 */
    private double value = 0.0;
    /**
	 * @uml.property  name="description"
	 */
    private Object description = null;
    /**
	 * @uml.property   name="listeners"
	 * @uml.associationEnd   multiplicity="(0 -1)" elementType="Reproductor_1.Codec.ReproductorLanzador"
	 */
    private Collection listeners = null;
    /**
	 * @uml.property  name="source"
	 */
    private Object source = null;

    /**
     * Contructor.
     * @param code
     * @param position
     * @param value
     * @param description
     * @param listeners
     * @param source
     */
    public ReproductorLanzarEvento(int code, int position, double value, Object description, Collection listeners, Object source)
    {
        super();
        this.code = code;
        this.position = position;
        this.value = value;
        this.description = description;
        this.listeners = listeners;
        this.source = source;
    }

    public void run()
    {
        if (listeners != null)
        {
            Iterator it = listeners.iterator();
            while (it.hasNext())
            {
                ReproductorLanzador bpl = (ReproductorLanzador) it.next();
                ReproductorEvento event = new ReproductorEvento(source, code, position, value, description);
                bpl.estadoActualizado(event);
            }
        }
    }
}
