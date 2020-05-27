package risco;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Historial es una clase para leer los ficheros de exportación de los
 * jugadores que pueden crearse durante la partida. 
 * Muestra la Fecha de la partida, las puntuaciones, el número de
 * jugadores que había y la posición del jugador en cuestión.
 * 
 */
public class Historial {
  // Atributos ////////
  private String nombreJugador;
  private int numeroPartidas; // Número de partidas jugadas (número de líneas)
  private float mediaPuntos; // Suma todos los puntos de todas las partidas
  private int partidasPrimero; // Veces que estaba primero
  private float puestoMedio; // Media de la posición de todas las partidas
  private BufferedReader archivo; // Archivo con el historial

  /**
   * Constructor de la clase Historial. Se le pasa como parámetro un jugador.
   * 
   * @param player
   * @throws IOException
   */
  public Historial(Jugador player) throws IOException {
    this.nombreJugador = player.getNombre();
    this.archivo = abrirFichero();
    this.numeroPartidas = cuentaLineas(); // Inicializamos con 0
    this.mediaPuntos = 0; // Inicializamos con 0
    this.partidasPrimero = 0; // Inicializamos con 0
    this.puestoMedio = 0; // Inicializamos con 0
  }

  // Getters & Setters
  private String getNombreJugador() {
    return nombreJugador;
  }

  private BufferedReader getArchivo() {
    return archivo;
  }

  private void setArchivo(BufferedReader archivo) {
    this.archivo = archivo;
  }

  private int getNumeroPartidas() {
    return numeroPartidas;
  }

  /**
   * Set Numero de partidas jugadas
   * @param numeroPartidas
   */
  private void setNumeroPartidas(int numeroPartidas) {
    this.numeroPartidas = numeroPartidas;
  }

  /**
   * Calcula la media de los puntos obtenidos en las partidas guardadas en el historial
   * @return Media de los puntos obtenidos
   */
  private float getMediaPuntos() {

    String texto;
    float veces = 0;
    texto = texto();

    // Puesto: (matcher.group())
    Pattern patron = Pattern.compile("Total:(.*?);", Pattern.DOTALL);
    Matcher matcher = patron.matcher(texto);

    while (matcher.find()) {
      veces++;
      System.out.println(matcher.group(1));
      mediaPuntos += Integer.parseInt(matcher.group(1).trim());

    }

    mediaPuntos = mediaPuntos / veces;

    return mediaPuntos;
  }

  /**
   * Set de la media de Puntos obtenidos
   * @param mediaPuntos
   */
  private void setMediaPuntos(float mediaPuntos) {
    this.mediaPuntos = mediaPuntos;
  }

  /**
   *Calcula las veces que ha quedado primero en las partida registradas
   * @return PartidasPrimero
   */
  private int getPartidasPrimero() {

    String texto;

    texto = texto();
    // Puesto: 1
    Pattern patron = Pattern.compile("Puesto: 1");
    Matcher match = patron.matcher(texto);
    while (match.find()) {
      partidasPrimero++;
    }

    return partidasPrimero;
  }

  /**
   * Set Para las veces que ha quedado primero en una partida
   * @param partidasPrimero
   */
  private void setPartidasPrimero(int partidasPrimero) {
    this.partidasPrimero = partidasPrimero;
  }

  /**
   * Get para puesto medio, utiliza expresión regular para ver los puestos en los que
   * ha quedado y calcular la media.
   * @return media de los puestos que ha quedado en las partidas
   */
  private float getPuestoMedio() {

    float veces = 0;

    String archivo = texto();

    // Puesto: (matcher.group())
    Pattern patron = Pattern.compile("Puesto:\\s+(.*?)\\n", Pattern.DOTALL);
    Matcher matcher = patron.matcher(archivo);

    while (matcher.find()) {
      veces++;
      puestoMedio += Integer.parseInt(matcher.group(1));

    }

    puestoMedio = puestoMedio / veces;
    return puestoMedio;
  }

  /**
   * Set puesto Medio que queda el jugador.
   * @param puestoMedio
   */
  private void setPuestoMedio(float puestoMedio) {
    this.puestoMedio = puestoMedio;
  }

  /**
   * Método para abrir el fichero del jugador
   * 
   * @return el archivo
   */
  private BufferedReader abrirFichero() {
    // Abro el archivo
    BufferedReader archivo = null;
    try {
      archivo = new BufferedReader(new FileReader("risco_" + getNombreJugador() + ".txt"));
    } catch (IOException e) {
      System.err.println("Error al abrir el fichero.");
      System.exit(2);
    }
    return archivo;
  }

  /**
   * Método que cuenta las líneas del archivo, o lo que es lo mismo, el número de
   * partidas.
   * 
   * @return número de líneas o de partidas
   * @throws IOException
   */
  private int cuentaLineas() throws IOException {
    int nPartidas = 0;
    while (archivo.readLine() != null) {
      nPartidas++;
    }
    setArchivo(abrirFichero()); // Vuelvo a abrir el fichero y meterlo en el objeto, porque ahora estará leyendo
                                // el final
    return nPartidas;
  }


  /**
   *  Devuelve toda la información del archivo en un solo parámetro
   * @return texto 
   */
  private String texto() {
    String linea = "";
    String texto = "";
    try {
      while ((linea = archivo.readLine()) != null) {
        texto += linea + "\n";
      }
    } catch (IOException e) {
      System.err.println("error al leer el archivo");
    }
    setArchivo(abrirFichero());
    return texto;
  }

  @Override
  public String toString() {
    return "\nHistorial de " + getNombreJugador() + "\nPartidas hechas → " + getNumeroPartidas()
        + "\nPuntuación media → " + getMediaPuntos() + "\nPartidas primero → " + getPartidasPrimero()
        + "\nPuesto medio → " + getPuestoMedio();
  }

}
