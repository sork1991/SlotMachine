/**
 * Representa un simbolo del dominio del juego. Es responsable del color
 * (identidad logica del simbolo) y de si forma parte de la
 * configuracion ganadora. Delega toda la representacion grafica en un
 * objeto SymbolShape.
 * 
 * @author 
 * @version 1.0
 */
public class Symbol
{
    private String color;
    private SymbolShape shape;
    private boolean winning;
    private String shapeType;

    /**
     * Constructor: recibe ya construidos el color y el nombre de la figura. 
     * @param color color del simbolo.
     * @param shapeType figura concreta ("circle", "rectangle" o
     * "triangle") que representa graficamente al simbolo.
     */
    public Symbol(String color, String shapeType)
    {
        this.color = color;
        this.shape = createShape(shapeType);
        shape.changeColor(color);
        this.shapeType = shapeType;
    }

    /**
     * Metodo de fabrica privado: decide que implementacion concreta de
     * SymbolShape instanciar segun el tipo pedido.
     * @param shapeType tipo de figura solicitado.
     * @return la figura concreta correspondiente.
     */
    private SymbolShape createShape(String shapeType)
    {
        if (shapeType.equals("circle")) {
            return new SymbolCircle();
        } else if (shapeType.equals("rectangle")) {
            return new SymbolRectangle();
        } else{
            return new SymbolTriangle();
        }
    }
    
    /**
     * Devuelve el color actual del simbolo.
     * @return color del simbolo.
     */
    public String getColor()
    {
        return color;
    }
    
    /**
     * devueve la el nombre de la figura actual
     * @return nombre de la figura
     */
    public String getShape()
    {
        return shapeType;
    }

    /**
     * Cambia el color del simbolo: actualiza el atributo propio y delega en
     * shape.changeColor(color).
     * @param color nuevo color.
     */
    public void setColor(String color)
    {
        this.color = color;
        shape.changeColor(color);
    }

    /**
     * Delega en shape.setPosition(x, y) para posicionar visualmente el
     * simbolo.
     * @param x coordenada x.
     * @param y coordenada y.
     */
    public void setPosition(int x, int y)
    {
        shape.setPosition(x, y);
    }

    /**
     * Delega en shape.getX().
     * @return coordenada x actual.
     */
    public int getX()
    {
        return shape.getX();
    }

    /**
     * Delega en shape.getY().
     * @return coordenada y actual.
     */
    public int getY()
    {
        return shape.getY();
    }

    /**
     * Delega en shape.makeVisible().
     */
    public void makeVisible()
    {
        shape.makeVisible();
    }

    /**
     * Delega en shape.makeInvisible().
     */
    public void makeInvisible()
    {
        shape.makeInvisible();
    }

    /**
     * Marca o desmarca el simbolo como parte de la configuracion
     * ganadora.
     * @param winning true si el simbolo hace parte de la configuracion
     *                ganadora.
     */
    public void setWinningPart(boolean winning)
    {
        this.winning = winning;
    }

    /**
     * Consulta si el simbolo es parte de la configuracion ganadora.
     * @return true si es parte de la configuracion ganadora.
     */
    public boolean isWinningPart()
    {
        return winning;
    }
    
    /**
     * delega a symbolshape
     */
    public void changeSize(int height, int width)
    {
        shape.changeSize(height, width);
    }
}