/**
 * SymbolRectangle es un Rectangle extendido. Extiende su figura
 * original sin modificarla y ademas implementa SymbolShape, agregando
 * la capacidad de posicionamiento absoluto que las figuras originales
 * no tienen (solo ofrecen movimiento relativo).
 * SymbolRectangle implementa el contrato SymbolShape.
 * 
 * makeVisible(), makeInvisible() y changeColor() se heredan
 * directamente de Rectangle y ya cumplen con lo que pide la interfaz
 * SymbolShape.
 * 
 * @author 
 * @version 1.0
 */
public class SymbolRectangle extends Rectangle implements SymbolShape
{
    private int currentX;
    private int currentY;

    /**
     * Constructor: invoca al constructor de la superclase e inicializa
     * currentX y currentY con la posicion por defecto de esa figura.
     */
    public SymbolRectangle()
    {
        super();
        currentX = 70;
        currentY = 15;
    }

    /**
     * Fija la posicion absoluta de la figura. Calcula el desplazamiento
     * necesario (x - currentX, y - currentY) y lo aplica usando los
     * metodos heredados moveHorizontal y moveVertical.
     * @param x coordenada x.
     * @param y coordenada y.
     */
    public void setPosition(int x, int y)
    {
        int dx = x - currentX;
        int dy = y - currentY;
        moveHorizontal(dx);
        moveVertical(dy);
        currentX = x;
        currentY = y;
    }

    /**
     * Devuelve currentX.
     * @return coordenada x actual.
     */
    public int getX()
    {
        return currentX;
    }

    /**
     * Devuelve currentY.
     * @return coordenada y actual.
     */
    public int getY()
    {
        return currentY;
    }
}