package risco;


import java.util.ArrayList;

/**
 * Partida es la clase en la que se encuentran los métodos para todos los "juegos"
 * que se pueden jugar en el Risco, como el trío, la escalera mayor, etc.
 * 
 * Se tienen en cuenta la ronda y el número de jugadores que hay en la partida.
 * 
 * También contiene todos los jugadores de la clase Jugador que están jugando la 
 * partida.
 * 
 * Además, desde aqui se pueden crear nuevos jugadores, tirar unos nuevos dados,
 * generar el tablero con todas las puntuaciones o calcular cual es la posición 
 * en la que está el jugador.
 *
 */
public class Partida {

  // Atributos
  private int ronda;
  private int nJugadores;

  ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

  public Partida(int nJugadores) {
    this.nJugadores = nJugadores;
    this.ronda = 1;
  }

  // Getters & Setters
  public int getRonda() {
    return ronda;
  }

  public void setRonda(int ronda) {
    this.ronda = ronda;
  }

  public int getnJugadores() {
    return nJugadores;
  }

  public void setnJugadores(int nJugadores) {
    this.nJugadores = nJugadores;
  }

  /**
   * Método para crear jugadores para la partida.
   * 
   * @param nj     (Número de jugadores)
   * @param nombre (Nombre del jugador)
   */
  public void crearJugadores(int nj, String nombre) {
    Jugador jugador = new Jugador(nj, nombre);
    jugadores.add(jugador);
  }

  /**
   * Método estático para que el jugador tire sus dados.
   * 
   * @param player
   * @return los dados del jugador
   */
  public static Dados tirarDados(Jugador player) {
    player.setDadosJugador(new Dados());
    return player.dadosJugador;
  }

  /**
   * Método para mostrar por pantalla el tablero de la partida actual.
   * 
   */
  public void muestraTablero() {
    String[] juegos = { "\t" + "Risco", "Trece", "E.Mayor", "E.Menor", "E.Par", "E.Impar", "Trio", "Seis", "Cinco",
        "Cuatro", "Tres", "Dos", "As", "Total" };
    System.out.println();
    for (int i = 0; i <= juegos.length - 1; i++) {
      System.out.print(juegos[i] + "\t");
    }
    for (int i = 0; i < nJugadores; i++) {
      for (int j = 0; j <= 14; j++) {
        if (j == 0) {
          System.out.print("\n" + jugadores.get(jugadores.indexOf(new Jugador(i + 1))).getNombre() + "\t");
        } else {
          if (jugadores.get(jugadores.indexOf(new Jugador(i + 1))).p.get(j - 1) != null) {
            System.out.print(jugadores.get(jugadores.indexOf(new Jugador(i + 1))).p.get(j - 1) + "\t");
          } else {
            System.out.print("\t");
          }
        }
      }
    }

  }

  /**
   * Método para comprobar si una casilla está vacía o no.
   * 
   * @param player
   * @param casilla
   * @return boolean
   */
  public static boolean casillaVacia(Jugador player, int casilla) {
    if (player.p.get(casilla) == null) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Método de comprobación: Risco.
   * 
   * @param player
   * @return 1 si acierta, 0 si no acierta y 50 si la casilla está ocupada
   */
  public static int Risco(Jugador player) {
    if (!casillaVacia(player, 0)) {
      // ocupada = 50
      return 50;
    }
    if ((player.dadosJugador.getD1() == player.dadosJugador.getD2()
        || player.dadosJugador.getD1() == player.dadosJugador.getD3()
        || player.dadosJugador.getD2() == player.dadosJugador.getD3())
        && (player.dadosJugador.getD1() + player.dadosJugador.getD2() + player.dadosJugador.getD3() == 13)) {

      player.p.set(0, 50);
      player.p.set(13, player.totalPtos(0));
      // risco 50 puntos
      return 1;
    } else {
      player.p.set(0, 0);
      player.p.set(13, player.totalPtos(0));
      // Esta combinación de números no es un risco
      return 0;
    }
  }

  /**
   * Método de comprobación: Trece.
   * 
   * @param player
   * @return 1 si acierta, 0 si no acierta y 50 si la casilla está ocupada
   */
  public static int Trece(Jugador player) {
    if (!casillaVacia(player, 1)) {
      return 2;
    }
    if (player.dadosJugador.getD1() + player.dadosJugador.getD2() + player.dadosJugador.getD3() == 13) {
      player.p.set(1, 26);
      player.p.set(13, player.totalPtos(1));
      // Casilla Trece completada, 26 ptos
      return 1;
    } else {
      player.p.set(1, 0);
      player.p.set(13, player.totalPtos(1));
      // Esta combinación de números no es un trece
      return 0;

    }
  }

  /**
   * Método de comprobación: Escalera Mayor.
   * 
   * @param player
   * @return 1 si acierta, 0 si no acierta y 50 si la casilla está ocupada
   */
  public static int EscaleraMayor(Jugador player) {
    boolean valord1 = false;
    boolean valord2 = false;
    boolean valord3 = false;

    if (!casillaVacia(player, 2)) {
      return 50;
    }
    for (int i = 4; i <= 6; i++) {

      if (player.dadosJugador.getD1() == i) {
        valord1 = true;
      } else if (player.dadosJugador.getD2() == i) {
        valord2 = true;
      } else if (player.dadosJugador.getD3() == i) {
        valord3 = true;
      } else {
        player.p.set(2, 0);
        player.p.set(13, player.totalPtos(2));
        // Esta combinación de dados no es una Escalera Mayor
        return 0;
      }
    }
    if (valord1 && valord2 && valord3) {
      player.p.set(2, 20);
      player.p.set(13, player.totalPtos(2));
    }
    // Esta combinación es una Escalera Mayor, 20 ptos
    return 1;
  }

  /**
   * Método de comprobación: Escalera Menor.
   * 
   * @param player
   * @return 1 si acierta, 0 si no acierta y 50 si la casilla está ocupada
   */
  public static int EscaleraMenor(Jugador player) {
    boolean valord1 = false;
    boolean valord2 = false;
    boolean valord3 = false;
    if (!casillaVacia(player, 3)) {
      return 50;
    }

    for (int i = 1; i <= 3; i++) {

      if (player.dadosJugador.getD1() == i) {
        valord1 = true;
      } else if (player.dadosJugador.getD2() == i) {
        valord2 = true;
      } else if (player.dadosJugador.getD3() == i) {
        valord3 = true;
      } else {
        player.p.set(3, 0);
        player.p.set(13, player.totalPtos(3));
        // "Esta combinación de dados no es una Escalera Menor"
        return 0;

      }
    }

    if (valord1 && valord2 && valord3) {
      player.p.set(3, 20);
      player.p.set(13, player.totalPtos(3));
    }
    // "Esta combinación es una Escalera Menor, 20 ptos"
    return 1;
  }

  /**
   * Método de comprobación: Escalera Par.
   * 
   * @param player
   * @return 1 si acierta, 0 si no acierta y 50 si la casilla está ocupada
   */
  public static int EscaleraPar(Jugador player) {
    boolean valord1 = false;
    boolean valord2 = false;
    boolean valord3 = false;
    if (!casillaVacia(player, 4)) {
      return 50;
    }

    for (int i = 2; i <= 6; i += 2) {

      if (player.dadosJugador.getD1() == i) {
        valord1 = true;
      } else if (player.dadosJugador.getD2() == i) {
        valord2 = true;
      } else if (player.dadosJugador.getD3() == i) {
        valord3 = true;
      } else {
        player.p.set(4, 0);
        player.p.set(13, player.totalPtos(4));
        // no es Escalera Par
        return 0;
      }

    }
    if (valord1 && valord2 && valord3) {
      player.p.set(4, 20);
      player.p.set(13, player.totalPtos(4));
    }
    // "Esta combinación es una Escalera Par, 20 ptos"
    return 1;
  }

  /**
   * Método de comprobación: Escalera Impar.
   * 
   * @param player
   * @return 1 si acierta, 0 si no acierta y 50 si la casilla está ocupada
   */
  public static int EscaleraImpar(Jugador player) {
    boolean valord1 = false;
    boolean valord2 = false;
    boolean valord3 = false;

    if (!casillaVacia(player, 5)) {
      return 50;
    }
    for (int i = 2; i <= 6; i += 2) {

      if (player.dadosJugador.getD1() == i) {
        valord1 = true;
      } else if (player.dadosJugador.getD2() == i) {
        valord2 = true;
      } else if (player.dadosJugador.getD3() == i) {
        valord3 = true;
      } else {
        player.p.set(5, 0);
        player.p.set(13, player.totalPtos(5));
        return 1;
      }

    }
    //Esta combinación es una escalera impar, 20 ptos
    if (valord1 && valord2 && valord3) {
      player.p.set(5, 20);
      player.p.set(13, player.totalPtos(5));
     
    }  
    return 0;
  }

  /**
   * Método de comprobación: Trío.
   * 
   * @param player
   * @return 1 si acierta, 0 si no acierta y 50 si la casilla está ocupada
   */
  public static int Trio(Jugador player) {
    if (!casillaVacia(player, 6)) {
      return 50;
    }
    if (player.dadosJugador.getD1() == player.dadosJugador.getD2()
        && player.dadosJugador.getD1() == player.dadosJugador.getD3()) {

      player.p.set(6, 25);
      player.p.set(13, player.totalPtos(6));
      // "Casilla Trío completada, 25 ptos"
      return 1;
    } else {
      player.p.set(6, 0);
      player.p.set(13, player.totalPtos(6));
      // "Esta combinación de números no es un trío."
      return 0;
    }
  }

  /**
   * Método de comprobación: Seis, cinco, cuatro, tres, dos, ases.
   * 
   * @param player
   * @param n      (Número de juego, p. ej 5 en refencia al cinco)
   * @return cantidad de puntos o 50 si la casilla está ocupada
   */
  public static int numero(Jugador player, int n) {
    int puntos = 0;

    if (player.dadosJugador.getD1() == n) {
      puntos += n;
    }
    if (player.dadosJugador.getD2() == n) {
      puntos += n;
    }
    if (player.dadosJugador.getD3() == n) {
      puntos += n;
    }

    int casilla = 0;
    if (n == 6) {
      casilla = 7;

    } else if (n == 5) {
      casilla = 8;
    } else if (n == 4) {
      casilla = 9;
    } else if (n == 3) {
      casilla = 10;
    } else if (n == 2) {
      casilla = 11;
    } else if (n == 1) {
      casilla = 12;
    }
    if (!casillaVacia(player, casilla)) {
      return 50;
    } 
    
      player.p.set(casilla, puntos);
      player.p.set(13, player.totalPtos(casilla));
      // "Casilla" + n + "completada con " + n + " ptos"
      return puntos;
  }

  /**
   * Método que calcula la posición de un jugador en la partida
   * 
   * Empieza en la primera posición, compara la puntuación del jugador con la de
   * los demás, si es mayor, incrementa la posición en 1.
   * 
   * @param player
   * @return posición del jugador
   */
  int posicion(Jugador player) {
    int pos = 1;
    int puntos = player.p.get(13);

    for (int i = 0; i < nJugadores; i++) {
      if (jugadores.get(jugadores.indexOf(new Jugador(i + 1))).p.get(13) != null) {
        if (jugadores.get(jugadores.indexOf(new Jugador(i + 1))).p.get(13) > puntos) {
          pos++;
        }
      }
    }
    return pos;
  }

  @Override
  public String toString() {
    return "\n Partida ronda=" + ronda + ", nJugadores=" + nJugadores;
  }
}
