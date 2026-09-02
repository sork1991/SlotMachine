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
    private int actualX;
    private int currentY;
    private int width;
    private int height;

    /**
     * Constructor: invoca al constructor de la superclase e inicializa
     * currentX y currentY con la posicion por defecto de esa figura.
     */
    public SymbolRectangle()
    {
        super();
        actualX = 70;
        currentY = 15;
        width = 40;
        height = 30;
    }

    /**
     * Ademas de cambiar el tamano real (heredado de Rectangle), guarda
     * width y height localmente, necesarios para centrar la figura en
     * setPosition().
     * @param newHeight alto deseado.
     * @param newWidth ancho deseado.
     */
    public void changeSize(int newHeight, int newWidth)
    {
        super.changeSize(newHeight, newWidth);
        height = newHeight;
        width = newWidth;
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
        int targetX = x - width / 2;
        int dx = targetX - actualX;
        int dy = y - currentY;
        moveHorizontal(dx);
        moveVertical(dy);
        actualX = targetX;
        currentY = y;
    }

    /**
     * Devuelve currentX.
     * @return coordenada x actual.
     */
    public int getX()
    {
        return actualX + width / 2;
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