package risco;
import utiles.Teclado;

/**
 * La clase dados encargado de generar los dados de la partida de forma aleatoria de 1 al 6.
 * Contiene el resultado de los 3 dados. 
 * Además, permite cambiar los dados, en caso de que el jugador así lo quiera.
 * 
 */
public class Dados {

  private int d1;
  private int d2;
  private int d3;

  /**
   * Constructor de dados.
   */
  public Dados() {

    setD1();
    setD2();
    setD3();
  }

  /**
   * Get dado 1 
   * @return dado número 1
   */
  public int getD1() {
    return d1;
  }

  /**
   * Set dado 1
   * Da valor al dado 1 mediante un generador aleatorio de 1 a 6
   */
  public void setD1() {
    this.d1 = (int) (Math.random() * 6 + 1);
  }

  /**
   * Get dado 2 
   * @return dado número 2
   */
  public int getD2() {
    return d2;
  }
  
  /**
   * Set dado 2
   * Da valor al dado 2 mediante un generador aleatorio de 1 a 6
   */
  public void setD2() {
    this.d2 = (int) (Math.random() * 6 + 1);
  }

  /**
   * Get dado 3 
   * @return dado número 3
   */
  public int getD3() {
    return d3;
  }

  /**
   * Set dado 2
   * Da valor al dado 2 mediante un generador aleatorio de 1 a 6
   */
  public void setD3() {
    this.d3 = (int) (Math.random() * 6 + 1);
  }

  /**
   * Cambiar dados. Al tirar los dados puedes solicitar cambiar 1, 2 o los 3 dados.
   * 
   * @param nDadosCambiar
   * @return dados
   */
  String cambiarDados(int nDadosCambiar) {
/**
 * Si se indica mas de 2 se cambiarán todos los dados
 */
    if (nDadosCambiar > 2) {
      if (nDadosCambiar > 3) {
        System.out.println("Solo puedes cambiar 3 dados");
        nDadosCambiar = 3;
      }
      setD1();
      setD2();
      setD3();

      return toString();
    }

    /**
     * Si se indican menos de dos dados, se indicará que dado cambiar.
     */
    int dado;
    boolean cambiod1 = true;
    boolean cambiod2 = true;
    boolean cambiod3 = true;
    for (int i = 0; i < nDadosCambiar; i++) {

      dado = Teclado.leerEntero("¿Que dado quiere cambiar? 1, 2 o 3: ");

      if (dado == 1 && cambiod1) {
        setD1();
        cambiod1 = false;
        
      } else if (dado == 2 && cambiod2) {
        setD2();
        cambiod2 = false;
        
      } else if (dado == 3 && cambiod3) {
        setD3();
        cambiod3 = false;
        
      } else if (!cambiod1 || !cambiod2 || !cambiod3) {
        System.out.println("Dado " + dado + " ya ha sido cambiado introduzca otro");
        --i;
        
      } else {
        System.out.println("Dado " + dado + " no existe introduzca otro");
        --i;
      }
    }
    return toString();
  }

  /**
   * @return el valor de los tres dados
   */
  @Override
  public String toString() {
    return "Dado 1: " + getD1() + "\nDado 2: " + getD2() + "\nDado 3: " + getD3();
  }
}