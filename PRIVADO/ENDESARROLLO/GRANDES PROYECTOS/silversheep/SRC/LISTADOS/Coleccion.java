package listados;

//TODO crear una estructura de arbol para esto desde cero y en otra clase.
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.ToolTipManager;
import javax.swing.ImageIcon;
import javax.swing.Icon;

import almacenamiento.AlmacenarInfoBibliotecaRefinado;
import almacenamiento.Biblioteca;
import almacenamiento.TransaccionesSQLite;

import main.Constantes;
import main.DialogoPersonalizarBusqueda;
import medios.Archivo;

import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Coleccion extends JPanel implements TreeSelectionListener,
		Constantes, MouseListener, KeyListener, TreeModelListener,
		ActionListener, TreeWillExpandListener {
	private static final long serialVersionUID = -8359984107448140209L;
	private JTree arbolPrincipal;
	private ListaReproduccion listaRepro;
	private DefaultMutableTreeNode ultimoNodoSeleccionado;
	private JButton btnExpandir, btnBorrarBusqueda, btnActualizar,
			btnConfigurar;
	private JTextField txtBuscar;
	private boolean expandido = false, dobleClic = false;
	private DefaultMutableTreeNode raizArbol, raizNegativa;
	private DefaultTreeModel modeloArbol, modeloArbolNegativo;
	private Biblioteca biblio;
	private TransaccionesSQLite sqlite;
	private Vector<Archivo> archivos;
	private DialogoPersonalizarBusqueda personalizarBusqueda;
	private JPopupMenu menuContextual;
	private JMenuItem itmAgregar;

	/**
	 * Constructor de la clase Coleccion... recibe un vector con los archivos
	 * que mostrará en el arbol
	 * 
	 * @param archivos
	 */
	public Coleccion() {
		super(new GridLayout(1, 0));

		sqlite = new TransaccionesSQLite();
		biblio = new AlmacenarInfoBibliotecaRefinado(sqlite);

		itmAgregar = new JMenuItem("Agregar");
		itmAgregar.setMnemonic('A');
		itmAgregar.setToolTipText("Agregar archivo a la lista de reproducción");
		itmAgregar.addActionListener(this);
		
		menuContextual = new JPopupMenu();
		menuContextual.add(itmAgregar);

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				crearInterfazGrafica();
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void cargarMedios() {
		archivos = biblio.getTodosArchivos(EXTENSIONES_AUDIO);
		raizArbol = new DefaultMutableTreeNode("M\u00fasica");
		crearNodos(raizArbol);
		modeloArbol = new DefaultTreeModel(raizArbol);
		arbolPrincipal.setModel(modeloArbol);
	}

	/**
	 * Crea los nodos de la interfaz gráfica
	 */
	@SuppressWarnings("unchecked")
	private void crearInterfazGrafica() {
		setLayout(new BorderLayout());
		archivos = biblio.getTodosArchivos(EXTENSIONES_AUDIO);
		// Asigna la raiz del arbol
		raizArbol = new DefaultMutableTreeNode("M\u00fasica");
		// crear los nodos del arbol
		crearNodos(raizArbol);
		// crear el modelo del arbol
		modeloArbol = new DefaultTreeModel(raizArbol);
		// iniciar arbol principal
		arbolPrincipal = new JTree(modeloArbol);
		arbolPrincipal.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		arbolPrincipal.setShowsRootHandles(true);
		arbolPrincipal.setRootVisible(false);
		// Escuchar cambios en la seleccion
		arbolPrincipal.addMouseListener(this);
		arbolPrincipal.addMouseListener(

		new MouseAdapter() { // clase interna anonima

					// manejar evento de oprimir botón del ratón
					public void mousePressed(MouseEvent evento) {
						checkForTriggerEvent(evento);
					}

					// manejar evento de soltar el botón del ratón
					public void mouseReleased(MouseEvent evento) {
						checkForTriggerEvent(evento);
					}

					// determinar si evento debe desencadenar el menú contextual
					private void checkForTriggerEvent(MouseEvent evento) {
						if (evento.isPopupTrigger())
							menuContextual.show(evento.getComponent(), evento
									.getX(), evento.getY());
					}

				} // fin de la clase interna anónima

				); // fin de la llamada a addMouseListener
		arbolPrincipal.addTreeSelectionListener(this);
		arbolPrincipal.addTreeWillExpandListener(this);
		modeloArbol.addTreeModelListener(this);

		// Habilitar tooltips
		ToolTipManager.sharedInstance().registerComponent(arbolPrincipal);

		// Asignar iconos
		ImageIcon icono = crearIcono(IMG_SONIDO_16);
		if (icono != null)
			arbolPrincipal.setCellRenderer(new MiRenderizador(icono));
		else
			System.err.println("No se encuentra el icono " + IMG_SONIDO_16);

		// iniciar objetos de busqueda
		crearPanelBusqueda();

		add(crearPanelBusqueda(), BorderLayout.NORTH);
		add(new JScrollPane(arbolPrincipal));
		add(crearHerramientasBusqueda(), BorderLayout.SOUTH);

		/**
		 * En esta seccion se crea el arbol negativo
		 */
		// iniciar raiz y modelo
		raizNegativa = new DefaultMutableTreeNode("Residuos");
		modeloArbolNegativo = new DefaultTreeModel(raizNegativa);
		modeloArbolNegativo.addTreeModelListener(this);
	}

	/**
	 * Crea los nodos del arbol basado en el vector de archivos
	 * 
	 * @param raiz
	 */
	private void crearNodos(DefaultMutableTreeNode raiz) {
		DefaultMutableTreeNode artista = null;
		DefaultMutableTreeNode album = null;
		DefaultMutableTreeNode cancion = null;
		// creacion de artistas
		String strArtista, strAlbum;
		for (int i = 0; i < archivos.size(); i++) {
			// aniadir artista
			strArtista = archivos.get(i).getArtista();
			strArtista = strArtista.equals("") ? "Desconocido" : strArtista;
			// preguntar por uno existente
			artista = obtenerNodo(raiz, strArtista);
			if (artista == null) {
				// si no hay, crearlo
				artista = new DefaultMutableTreeNode(strArtista);
				raiz.add(artista);
			}
			// aniadir album
			strAlbum = archivos.get(i).getAlbumDisco();
			strAlbum = strAlbum.equals("") ? "Desconocido" : strAlbum;
			// preguntar por album existente
			album = obtenerNodo(artista, strAlbum);
			if (album == null) {
				// si no hay, crearlo
				album = new DefaultMutableTreeNode(strAlbum);
				artista.add(album);
			}
			cancion = new DefaultMutableTreeNode(archivos.get(i));
			album.add(cancion);
		}
	}

	/**
	 * Este metodo inicia los objetos del panel busqueda
	 */
	private JPanel crearPanelBusqueda() {
		// iniciar panel
		JPanel pnlBusqueda = new JPanel();
		pnlBusqueda.setLayout(new BoxLayout(pnlBusqueda, BoxLayout.LINE_AXIS));
		pnlBusqueda.add(Box.createHorizontalGlue());
		pnlBusqueda.setPreferredSize(new Dimension(100, 25));

		// Caja de busqueda
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(this);

		// boton de borrar busqueda
		btnBorrarBusqueda = new JButton(new ImageIcon(this.getClass()
				.getResource(IMG_LIMPIAR_16)));
		btnBorrarBusqueda.setToolTipText("Borrar campo de b\u00fasqueda");
		btnBorrarBusqueda.setPreferredSize(new Dimension(20, 20));
		btnBorrarBusqueda.addActionListener(this);

		pnlBusqueda.add(txtBuscar);
		pnlBusqueda.add(btnBorrarBusqueda);

		return pnlBusqueda;
	}

	/**
	 * Crear objetos de utilidades de busqueda
	 */
	private JPanel crearHerramientasBusqueda() {
		JPanel pnlHerramientas = new JPanel();
		pnlHerramientas.setLayout(new BoxLayout(pnlHerramientas,
				BoxLayout.LINE_AXIS));
		pnlHerramientas.add(Box.createHorizontalGlue());
		pnlHerramientas.setPreferredSize(new Dimension(100, 25));

		// boton de expandir
		btnExpandir = new JButton(new ImageIcon(this.getClass().getResource(
				IMG_EXPANDIR_16)));
		btnExpandir.setToolTipText("Expandir arbol");
		btnExpandir.setPreferredSize(new Dimension(20, 20));
		btnExpandir.addActionListener(this);

		// boton de recargar
		btnActualizar = new JButton(new ImageIcon(this.getClass().getResource(
				IMG_REFRESCAR_16)));
		btnActualizar.setToolTipText("Actualizar lista");
		btnActualizar.setPreferredSize(new Dimension(20, 20));
		btnActualizar.addActionListener(this);

		// boton de configuracion de busquedas en la coleccion
		btnConfigurar = new JButton(new ImageIcon(this.getClass().getResource(
				IMG_HERRAMIENTAS_16)));
		btnConfigurar.setToolTipText("Configurar modo de b\u00fasqueda");
		btnConfigurar.setPreferredSize(new Dimension(20, 20));
		btnConfigurar.addActionListener(this);

		pnlHerramientas.add(btnExpandir);
		pnlHerramientas.add(btnActualizar);
		pnlHerramientas.add(btnConfigurar);

		return pnlHerramientas;
	}

	/**
	 * Busca un nodo por nombre, y lo devuelve
	 * 
	 * @param raiz
	 * @param texto
	 * @return
	 */
	private DefaultMutableTreeNode obtenerNodo(DefaultMutableTreeNode raiz,
			String texto) {
		for (int i = 0; i < raiz.getChildCount(); i++)
			if (raiz.getChildAt(i).toString().toLowerCase().equals(
					texto.toLowerCase()))
				return (DefaultMutableTreeNode) raiz.getChildAt(i);
		return null;
	}

	/**
	 * Devuelve una imagen o null si no existe
	 * 
	 * @param ruta
	 * @return
	 */
	protected static ImageIcon crearIcono(String ruta) {
		URL urlImagen = Coleccion.class.getResource(ruta);
		if (urlImagen != null) {
			return new ImageIcon(urlImagen);
		} else {
			System.err.println("No puedo encontrar el archivo: " + ruta);
			return null;
		}
	}

	/**
	 * Clase que renderiza la forma en que se muestran los nodos.
	 * 
	 */
	private class MiRenderizador extends DefaultTreeCellRenderer {
		private static final long serialVersionUID = 2718364362535743782L;
		private Icon iconoNodo;

		public MiRenderizador(Icon icono) {
			iconoNodo = icono;
		}

		public Component getTreeCellRendererComponent(JTree arbol,
				Object valor, boolean sel, boolean expandido, boolean hijo,
				int fila, boolean tieneFoco) {

			super.getTreeCellRendererComponent(arbol, valor, sel, expandido,
					hijo, fila, tieneFoco);
			if (hijo && esCancion(valor)) {
				setIcon(iconoNodo);
				setToolTipText("TODO: cambiar esta shit");
			} else {
				setToolTipText(null);
			}

			return this;
		}

		protected boolean esCancion(Object valor) {
			return true;
		}
	}

	/**
	 * Implementacion de los metodos que manejan los eventos del mouse
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getClickCount() == 2) {
			dobleClic = true;
			ultimoNodoSeleccionado = (DefaultMutableTreeNode) arbolPrincipal
					.getLastSelectedPathComponent();
			if (ultimoNodoSeleccionado != null
					&& ultimoNodoSeleccionado.isLeaf()) {
				listaRepro.aniadirMedio((Archivo) ultimoNodoSeleccionado
						.getUserObject());
				dobleClic = false;
			}
		}
	}

	/**
	 * Implementacion de metodos de escucha de eventos de teclado
	 */
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO corregir el bug de borrar con suprimir ;)
		if (e.getKeyCode() == 8) {// si está borrando cosas
			filtrarMedio(txtBuscar.getText(), raizNegativa, raizArbol, false);
		} else
			// si esta escribiendo
			filtrarMedio(txtBuscar.getText(), raizArbol, raizNegativa, true);
		if (txtBuscar.getText().equals(""))
			modeloArbol.reload();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Este metodo es requerido por la interfaz {@link TreeSelectionListener}
	 */
	public void valueChanged(TreeSelectionEvent e) {
		ultimoNodoSeleccionado = (DefaultMutableTreeNode) arbolPrincipal
				.getLastSelectedPathComponent();
	}

	/**
	 * Este metodo filtra el contenido del arbol. Su funcionamiento es el
	 * siguiente: basado en las raices que le pasemos, filtra los datos de un
	 * archivo de medios y los que no coincidan los copia a otro arbol. La
	 * manera en que funciona depende de la variable filtrar: si es true mueve
	 * los nodos que no coinciden al otro arbol, si es false, mueve los nodos
	 * que coinciden al otro arbol.
	 * 
	 * @param texto
	 * @param raizArbol
	 * @param raizNegativa
	 * @param filtrar
	 */
	private void filtrarMedio(String texto, DefaultMutableTreeNode raizArbol,
			DefaultMutableTreeNode raizNegativa, boolean filtrar) {
		int artista, albumes, canciones;
		artista = albumes = canciones = 0;
		DefaultMutableTreeNode nodoArtista, nodoAlbum, nodoCancion;
		DefaultMutableTreeNode nodoArtistaClon, nodoAlbumClon, nodoCancionClon;
		boolean filtrando = false;

		artista = raizArbol.getChildCount();
		for (int i = 0; i < artista; i++) {
			albumes = raizArbol.getChildAt(i).getChildCount();
			for (int j = 0; j < albumes; j++) {
				canciones = raizArbol.getChildAt(i).getChildAt(j)
						.getChildCount();
				for (int k = 0; k < canciones; k++) {
					nodoArtista = (DefaultMutableTreeNode) raizArbol
							.getChildAt(i);
					nodoAlbum = (DefaultMutableTreeNode) nodoArtista
							.getChildAt(j);
					nodoCancion = (DefaultMutableTreeNode) nodoAlbum
							.getChildAt(k);
					Archivo ar = (Archivo) nodoCancion.getUserObject();
					String[] opciones = biblio
							.opcionesPorNombre("criterio-busqueda");
					if (filtrar) {// si se esta filtrando... busque las que no
						// tengan coincidencias
						// filtrando = (ar.getNombreCortoArchivo().toLowerCase()
						// .indexOf(texto.toLowerCase()) < 0);
						filtrando = (ar.tieneEstosDatos(texto, opciones,
								filtrar));
					} else {// si se esta quitando el filtro... busque las
						// que tengan coincidencias
						filtrando = (ar.getNombreCortoArchivo().toLowerCase()
								.indexOf(texto.toLowerCase()) >= 0);
						filtrando = (ar.tieneEstosDatos(texto, opciones,
								filtrar));
					}
					if (filtrando) {
						/**
						 * Copiar nodos de un arbol a otro
						 */
						nodoArtistaClon = (DefaultMutableTreeNode) nodoArtista
								.clone();
						nodoAlbumClon = (DefaultMutableTreeNode) nodoAlbum
								.clone();
						nodoCancionClon = (DefaultMutableTreeNode) nodoCancion
								.clone();

						nodoArtistaClon = obtenerNodo(raizNegativa,
								nodoArtistaClon.toString());
						if (nodoArtistaClon == null) {
							nodoArtistaClon = (DefaultMutableTreeNode) nodoArtista
									.clone();
							raizNegativa.add(nodoArtistaClon);
						}

						nodoAlbumClon = obtenerNodo(nodoArtistaClon,
								nodoAlbumClon.toString());
						if (nodoAlbumClon == null) {
							nodoAlbumClon = (DefaultMutableTreeNode) nodoAlbum
									.clone();
							nodoArtistaClon.add(nodoAlbumClon);
						}

						nodoAlbumClon.add(nodoCancionClon);

						/**
						 * Borrar los nodos copiados
						 */
						modeloArbol.removeNodeFromParent(nodoCancion);
						canciones--;// acortar el ciclo por que
						k--;// acabo de eliminar un nodo
						if (nodoAlbum.getChildCount() == 0) {
							modeloArbol.removeNodeFromParent(nodoAlbum);
							albumes--;
							j--;
							if (nodoArtista.getChildCount() == 0) {
								modeloArbol.removeNodeFromParent(nodoArtista);
								artista--;
								i--;
							}
						}
					}
				}
			}
		}
		// esto es necesario para actualizar la estructura del arbol
		modeloArbol.reload();
		// esto expande todos los nodos del arbol
		expandirTodo(arbolPrincipal, true);
	}

	/**
	 * Este metodo expande o contrae todos los nodos de un arbol. Fue extraida
	 * de ExampleDepot.com: <a href="http://tinyurl.com/nmtvmd">ver</a>
	 * 
	 * @param arbol
	 * @param expandir
	 */
	public void expandirTodo(JTree arbol, boolean expandir) {
		TreeNode root = (TreeNode) arbol.getModel().getRoot();

		// Traverse tree from root
		expandirTodo(arbol, new TreePath(root), expandir);
	}

	/**
	 * Este metodo expande o contrae todos los nodos de un arbol. Fue extraida
	 * de ExampleDepot.com: <a href="http://tinyurl.com/nmtvmd">ver</a>
	 * 
	 * @param arbol
	 * @param padre
	 * @param expandir
	 */
	@SuppressWarnings("unchecked")
	private void expandirTodo(JTree arbol, TreePath padre, boolean expandir) {
		// Navegar a traves de los hijos
		TreeNode node = (TreeNode) padre.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements();) {
				TreeNode n = e.nextElement();
				TreePath path = padre.pathByAddingChild(n);
				expandirTodo(arbol, path, expandir);
			}
		}
		// expandir o contraer
		if (expandir)
			arbol.expandPath(padre);
		else
			arbol.collapsePath(padre);
	}

	/**
	 * Metodos requeridos por TreeSelectionListener
	 */
	@Override
	public void treeNodesChanged(TreeModelEvent e) {
	}

	@Override
	public void treeNodesInserted(TreeModelEvent e) {
	}

	@Override
	public void treeNodesRemoved(TreeModelEvent e) {
	}

	@Override
	public void treeStructureChanged(TreeModelEvent e) {
	}

	/**
	 * Asignar la lista de reproduccion a la cual aniadira los archivos
	 * 
	 * @param listaRepro
	 */
	public void setListaRepro(ListaReproduccion listaRepro) {
		this.listaRepro = listaRepro;
	}

	/**
	 * Obtener la lista de reproduccion a la cual esta aniadiendo archivos
	 * 
	 * @return
	 */
	public ListaReproduccion getListaRepro() {
		return listaRepro;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBorrarBusqueda) {
			txtBuscar.setText("");
			filtrarMedio(txtBuscar.getText(), raizNegativa, raizArbol, false);
			modeloArbol.reload();
		}
		if (e.getSource() == btnActualizar) {
			cargarMedios();
			modeloArbol.reload();
		}
		if (e.getSource() == btnExpandir) {
			expandido = !expandido;
			if (!expandido)
				btnExpandir.setIcon(new ImageIcon(this.getClass().getResource(
						IMG_EXPANDIR_16)));
			else
				btnExpandir.setIcon(new ImageIcon(this.getClass().getResource(
						IMG_COLAPSAR_16)));
			expandirTodo(arbolPrincipal, expandido);
			if (!expandido)
				modeloArbol.reload();
		}
		if (e.getSource() == btnConfigurar)
			personalizarBusqueda = new DialogoPersonalizarBusqueda();
		if (e.getSource() == itmAgregar)
			;
	}

	/**
	 * Metodos implementados de la interfaz {@link TreeWillExpandListener}
	 */
	@Override
	public void treeWillCollapse(TreeExpansionEvent evt)
			throws ExpandVetoException {
		ultimoNodoSeleccionado = (DefaultMutableTreeNode) arbolPrincipal
				.getLastSelectedPathComponent();
		// si es un album, es decir, si el primer hijo es una hoja y no otro
		// padre...
		if (ultimoNodoSeleccionado != null
				&& ultimoNodoSeleccionado.getChildCount() > 0
				&& ultimoNodoSeleccionado.getChildAt(0).isLeaf() && dobleClic) {
			dobleClic = false;
			throw new ExpandVetoException(evt);
		}
	}

	@Override
	public void treeWillExpand(TreeExpansionEvent evt)
			throws ExpandVetoException {
		ultimoNodoSeleccionado = (DefaultMutableTreeNode) arbolPrincipal
				.getLastSelectedPathComponent();
		// si es un album, es decir, si el primer hijo es una hoja y no otro
		// padre...
		if (ultimoNodoSeleccionado != null
				&& ultimoNodoSeleccionado.getChildCount() > 0
				&& ultimoNodoSeleccionado.getChildAt(0).isLeaf() && dobleClic) {
			dobleClic = false;
			for (int i = 0; i < ultimoNodoSeleccionado.getChildCount(); i++)
				listaRepro
						.aniadirMedio((Archivo) ((DefaultMutableTreeNode) ultimoNodoSeleccionado
								.getChildAt(i)).getUserObject());
			throw new ExpandVetoException(evt);
		}
	}
}