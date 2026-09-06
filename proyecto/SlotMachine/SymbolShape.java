/**
 * Contrato comun que deben cumplir todas las figuras que pueden
 * representar visualmente a un Symbol, sin importar su forma concreta
 * (circulo, rectangulo o triangulo). Permite que Symbol trabaje de
 * manera uniforme con cualquiera de ellas (polimorfismo) sin necesidad
 * de modificar las clases originales del paquete shapes.
 * 
 * @author 
 * @version 1.0
 */
public interface SymbolShape
{
    /**
     * Fija la posicion absoluta de la figura. No existe en las clases
     * originales de shapes, que solo tienen movimiento relativo; cada
     * implementacion lo agrega.
     * @param x coordenada x.
     * @param y coordenada y.
     */
    void setPosition(int x, int y);

    /**
     * Devuelve la coordenada x actual, llevada por un control interno
     * propio ya que las clases originales no la exponen.
     * @return coordenada x.
     */
    int getX();

    /**
     * Devuelve la coordenada y actual.
     * @return coordenada y.
     */
    int getY();

    /**
     * Hace visible la figura (heredado directo de la figura base
     * correspondiente).
     */
    void makeVisible();

    /**
     * Hace invisible la figura (heredado directo).
     */
    void makeInvisible();

    /**
     * Cambia el color de la figura (heredado directo).
     * @param color nuevo color.
     */
    void changeColor(String color);
    
    /**
     * Cambia el tamaño de la figura. Cada implementación decide cómo
     * traducir height/width a los parámetros propios de su figura base
     * @param height alto deseado.
     * @param width ancho deseado.
     */
    void changeSize(int height, int width);
}