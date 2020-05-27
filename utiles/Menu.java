package utiles;
import java.util.InputMismatchException;

public class Menu {
    private final String titulo;
    private final String opciones[];
    private int numOpciones;

    /**
     * Constructor.
     * 
     * @param titulo
     * @param opciones
     */
    public Menu(final String titulo, final String[] opciones) {
        this.titulo = titulo;
        this.opciones = opciones;
        this.numOpciones = this.opciones.length;

    }

    /**
     * Función encargada de gestionar el menú, muestra y recoge la opción indicada
     * por el usuario
     * 
     * @return opcion elegida por el usuario
     */
    public int gestionar() {
        mostrar();
        return recogerOpcion();
    }

    /**
     * Bucle que muestra las opciones
     */
    private void mostrar() {
        int i = 1;
        System.out.println("\n**" + titulo);
        for (final String elemento : opciones)
            System.out.println("(" + (i++) + ") " + elemento);
    }

    /**
     * Función que recoge la opción elegida por el usuario
     * 
     * @return opcion elegida por el usuario
     */
    private int recogerOpcion() {
        int opcion;
        do {
            opcion = Teclado.leerEntero("Indique la opción: ");
        } while (opcion < 1 || opcion > numOpciones);
        return opcion;
    }

}