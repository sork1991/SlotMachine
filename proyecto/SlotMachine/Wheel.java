import java.util.ArrayList;

/**
 * Representa una rueda individual de la maquina. Administra su propia
 * coleccion de simbolos, sabe cual es el simbolo actualmente mostrado y
 * gestiona su propia visibilidad y posicion.
 * 
 * @author 
 * @version 1.0
 */
public class Wheel
{
    private Rectangle frame;
    private ArrayList<Symbol> symbols;
    private Symbol currentSymbol;
    private boolean visible;
    private int currentX;
    private int currentY;

    /**
     * Constructor: crea la rueda vacia, sin posicion asignada todavia.
     */
    public Wheel()
    {
        frame = new Rectangle();
        symbols = new ArrayList<Symbol>();
        currentX = 70;
        currentY = 15;
        frame.changeSize(40, 24);
        frame.changeColor("white");
    }

    /**
     * Fija la posicion absoluta de la rueda: mueve frame y reposiciona
     * en cascada todos sus simbolos. Llamado por SlotMachine durante el
     * centrado.
     * @param x coordenada x.
     * @param y coordenada y.
     */
    public void setPosition(int x, int y)
    {
        int dx = x - currentX;
        int dy = y - currentY;
        frame.moveHorizontal(dx);
        frame.moveVertical(dy);
        currentX = x;
        currentY = y;
        for (int i = 0; i < symbols.size(); i++) {
            symbols.get(i).setPosition(x, y);
        }
    }

    /**
     * Devuelve la coordenada x actual de la rueda.
     * @return coordenada x.
     */
    public int getX()
    {
        return currentX;
    }

    /**
     * Devuelve la coordenada y actual de la rueda.
     * @return coordenada y.
     */
    public int getY()
    {
        return currentY;
    }

    /**
     * Crea un nuevo Symbol del color dado y lo inserta en la posicion
     * indicada (ya normalizada por SlotMachine).
     * @param pos posicion (normalizada) donde insertar.
     * @param color color del nuevo simbolo.
     */
    public void addSymbol(int pos, String color)
    {
        SymbolTriangle figura = new SymbolTriangle();
        figura.changeSize(24, 16);
        Symbol nuevo = new Symbol(color, figura);
        nuevo.setPosition(currentX + 12, 138);
        symbols.add(pos, nuevo);
    }

    /**
     * Busca (usando getColor() de cada simbolo) y elimina el primer
     * simbolo con ese color.
     * @param color color del simbolo a eliminar.
     */
    public void delSymbol(String color)
    {
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i).getColor().equals(color)) {
                symbols.remove(i);
                break;
            }
        }
    }

    /**
     * Busca el simbolo con ese color dentro de symbols y lo asigna como
     * currentSymbol, ocultando el anterior y mostrando el nuevo si la
     * rueda esta visible.
     * @param color color del simbolo a colocar.
     */
    public void placeSymbol(String color)
    {
        for (int i = 0; i < symbols.size(); i++) {
            if(symbols.get(i).getColor().equals(color)) {
                if (currentSymbol != null) {
                    currentSymbol.makeInvisible();
                }
                currentSymbol = symbols.get(i);
                if(visible) {
                    currentSymbol.makeVisible();
                }
                break;
            }
        }
    }

    /**
     * Selecciona al azar una posicion dentro de symbols (usando el
     * tamano actual de la lista), oculta el currentSymbol anterior,
     * actualiza currentSymbol al elegido y lo muestra si la rueda esta
     * visible.
     */
    public void spin()
    {
        int indice = (int) (Math.random() * symbols.size());
        String color = symbols.get(indice).getColor();
        placeSymbol(color);
    }

    /**
     * Hace visible frame y el currentSymbol; actualiza visible = true.
     */
    public void makeVisible()
    {
        visible = true;
        frame.makeVisible();
        if (currentSymbol != null) {
            currentSymbol.makeVisible();
        }
    }

    /**
     * Oculta frame y el currentSymbol; actualiza visible = false.
     */
    public void makeInvisible()
    {
        visible = false;
        frame.makeInvisible();
        if (currentSymbol != null) {
            currentSymbol.makeInvisible();
        }   
    }

    /**
     * Devuelve los colores de todos los simbolos de la rueda, en el
     * orden en que estan almacenados (iniciando en la posicion 1).
     * @return arreglo de colores.
     */
    public String[] symbolColors()
    {
        String[] colors = new String[symbols.size()];
        String color = null;
        for (int i = 0; i < symbols.size(); i++) {
            color = symbols.get(i).getColor();
            colors[i] = color;
        }
        return colors;
    }

    /**
     * Devuelve el color del currentSymbol (delega en
     * currentSymbol.getColor()).
     * @return color del simbolo actualmente mostrado.
     */
    public String getCurrentColor()
    {
        if (currentSymbol != null) {
            return currentSymbol.getColor();
        }
        return null;
    }

    /**
     * Devuelve la cantidad de simbolos que tiene la rueda
     * (symbols.size()).
     * @return numero de simbolos.
     */
    public int size()
    {
        return symbols.size();
    }

    /**
     * Oculta todos los simbolos y el frame de la rueda, dejandola lista
     * para ser eliminada de SlotMachine.wheels.
     */
    public void remove()
    {
        for (int i = 0; i < symbols.size(); i++) {
            symbols.get(i).makeInvisible();
        }
        frame.makeInvisible();
        visible = false;
    }
}