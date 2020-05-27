package risco;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * La clase Jugador crea el jugador con los datos de cada jugador: nombre, numero de jugador, 
 * arraylist con las puntuaciones y el resultado de los dados del jugador.
 * 
 * Además de la información del jugador, en esta clase creamos el método encargado de exportar los datos
 * del jugador a un archivo txt que se llamara risco_<nombre del jugador>.txt. Este método nos permitirá
 * guardar los datos de la partida (su puntuación, puesto en el que ha quedado, contra cuantos jugadores
 * jugaba, etc)
 *
 */
public class Jugador {
  private String nombre;
  private int nj; // Número de Jugador, como si fuera un código
  ArrayList<Integer> p = new ArrayList<Integer>(); // ArrayList de puntuaciones
  Dados dadosJugador; 
  
  /**
   * Constructor con los parámetros:
   * @param nj
   * @param nombre
   */
  public Jugador (int nj, String nombre) {
    this.nj = nj; // Número de jugador
    this.nombre = nombre; // Nombre de jugador
    this.dadosJugador = new Dados(); // Genero un objeto Dado, el dado a su vez contiene tres dados (d1, d2, d3)
    p.add(null); // 0 Risco 
    p.add(null); // 1 Trece
    p.add(null); // 2 Escalera mayor
    p.add(null); // 3 Escalera menor
    p.add(null); // 4 Escalera par
    p.add(null); // 5 Escalera impar
    p.add(null); // 6 Tres iguales
    p.add(null); // 7 Seis
    p.add(null); // 8 Cinco
    p.add(null); // 9 Cuatro
    p.add(null); // 10 Tres
    p.add(null); // 11 Dos
    p.add(null); // 12 As
    p.add(null); // 13 Para guardar el total
  }
  
  /**
   * Constructor con el parámetro:
   * @param nj
   */
  public Jugador(int nj) {
    this.nj = nj;
  }
  
/**
 * Get nombre del jugador
 * @return nombre
 */
  public String getNombre() {
    return nombre;
  }

  /**
   * Set nombre, da el nombre al jugador.
   * @param nombre
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * getDadosJugador devuelve el parámetro:
   * @return Dados del Jugador
   */
  public Dados getDadosJugador() {
    return dadosJugador;
  }

  /**
   * @param dadosJugador
   */
  public void setDadosJugador(Dados dadosJugador) {
    this.dadosJugador = dadosJugador;
  }

  
  int tmp=0; // Variable que almacena todos los puntos

  /**
 * Almacena los puntos totales.
 * @return los puntos totales.
 */
  public int totalPtos(int i) {
    
    boolean hayPtos = false;
    
      if (p.get(i) != null) {
          tmp += p.get(i);
          hayPtos = true;
      
    }
    if (hayPtos) {
      return tmp;
    } else {
      return 0;
    }
  }
  
  /**
   * Método para crear archivos para la exportación de datos.
   * @return el archivo que se crea
   */
  private BufferedWriter creaArchivo() {
    BufferedWriter manejadorExp = null;
    try {
      manejadorExp = new BufferedWriter(new FileWriter("risco_" + this.nombre + ".txt", true)); // Con true hace que pueda seguir en un archivo sin "macharlo"
    } catch (Exception e) {
      System.err.println("Error, no ha sido escribir en risco_" + this.nombre + ".txt");
      System.exit(2);
    }
    return manejadorExp;
  }
  
  /**
   * Exporta los datos
   * Estructura fichero:
   *     Fecha: 21/04/2020 ; Risco: 50 ; Trece: 20 ; E.Mayor: 10 ; ... Total: 817 ; Número jugadores: 2 ; Puesto: 1
   *     Fecha: 22/04/2020 ; Risco: 50 ; Trece: 20 ; E.Mayor: 0 ; ... Total: 807 ; Número jugadores: 2 ; Puesto: 2   
   */
  public void guardaDatos(int nJugadores, int pos)  {
  
    // Nombre de jugador
    try {
      BufferedWriter archivo = creaArchivo();
      
      /*
   
       */
      
      // Fecha
      java.util.Date fechalarga = new Date();
      String fecha = new SimpleDateFormat("dd/MM/yyyy").format(fechalarga);
      archivo.write("Fecha: " + fecha + " ; ");
      
      // Puntuacion
      String[] juegos = {"Risco: ", "Trece:  ", "E.Mayor: ", "E.Menor: ", "E.Par: ", "E.Impar: ", "Trio: ", "Seis: ", "Cinco: ",
          "Cuatro: ", "Tres: ", "Dos: ", "As: ", "Total: "};
      for (int i=0;i<=13;i++) {
        if (p.get(i) != null) {
          archivo.write(juegos[i] + p.get(i) + " ; ");
        } else {
          archivo.write(juegos[i] + "\t ; ");
        }
      }
      
      // Número de jugadores
      archivo.write("Número jugadores: " + nJugadores + " ; ");
      
      // Puesto en la partida
      archivo.write("Puesto: " + pos);
      
      // Final (cierre fichero)
      archivo.newLine(); // Hago una línea nueva, pensando en añadir mas datos en el futuro.
      archivo.close();
      System.out.println("Datos exportados.");
  
    } catch (IOException e) {
      System.err.println("Error en la escritura del fichero");
    }
  }
  

  /* Métodos equals y hashCode generados por Eclipse */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + nj;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Jugador other = (Jugador) obj;
    if (nj != other.nj)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Jugador [nombre=" + nombre +  ", dadosJugador=" + dadosJugador + "]";
  }
  
 }