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
    private boolean locked;

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
            int symX = symbols.get(i).getX();
            int symY = symbols.get(i).getY();
            symbols.get(i).setPosition(symX + dx, symY + dy);
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
     * indicada (ya normalizada por SlotMachine)
     * @param pos posicion (normalizada) donde insertar
     * @param type tipo de simbolo si es normal shy o ephemeral
     * @param shapeType tipo de figura: "circle", "rectangle" o "triangle"
     * @param color color del nuevo simbolo
     */
    public void addSymbol(int pos, String figure, String type, String color) throws SlotMachineException
    {
        Symbol nuevo = createTypeSymbol(type, figure, color);
        nuevo.changeSize(24, 16);
        nuevo.setPosition(currentX + 12, currentY + 8);
        symbols.add(pos, nuevo);
    }
    
    
    /**
     * crea el tipo de simbolo correspondiente
     * @param type tipo de simbolo si es normal shy o ephemeral
     * @param figura la figura que entra
     * @param color el color del que sera la figura
     * @return el simbolo creado
     * @Throws SlotMachineException una excepcion para cuando el tipo
     * es invalido
     */
    public Symbol createTypeSymbol(String type, String figure, String color) throws SlotMachineException{
        if(type.equals("normal")){
            return new Symbol(color, figure);
        }
        else if(type.equals("shy")){
            return new Shy(color, figure);
        }
        else if(type.equals("ephemeral")){
            return new Ephemeral(color, figure);
        }
        throw new SlotMachineException("el tipo " + type + " es invalido solo se permite normal, shy o ephemeral");
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
                    currentSymbol.notifySelection();
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
        notifySpinForSymbols();
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
     * Devuelve las figuras de todos los simbolos de la rueda, en el
     * orden en que estan almacenados (iniciando en la posicion 1).
     * @return arreglo de figuras.
     */
    public String[] symbolShapeType()
    {
        String[] shapeTypes = new String[symbols.size()];
        String shapeType = null;
        for (int i = 0; i < symbols.size(); i++) {
            shapeType = symbols.get(i).getShape();
            shapeTypes[i] = shapeType;
        }
        return shapeTypes;
    }
    
    /**
     * Devuelve los tipos de los simbolos de la rueda
     * @return arreglo de tipos
     */
    public String[] typeSymbols(){
        String[] types = new String[symbols.size()];
        String type = null;
        for(int i = 0; i < symbols.size(); i++){
            type = symbols.get(i).getType();
            types[i] = type;
        }
        return types;
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
    /**
     * Consulta si la rueda esta bloqueada.
     * @return true si esta bloqueada.
     */
    public boolean isLocked(){
        return locked;
    }
    
    /**
     * Bloquea la rueda
     */
    public void lock(){
        locked = true;
    }
    
    /**
     * Desbloquea la rueda
     */
    public void unlock(){
        locked = false;
    }
    
    /**
     * Rota la rueda steps posiciones desde el simbolo actual, en el
     * orden de la lista. Si la rueda esta visible, el
     * movimiento se ve paso a paso.
     * @param steps cantidad de posiciones a avanzar.
     */
    public void spin(int steps)
    {
        int actual = 0;
        for (int i = 0; i < symbols.size(); i++) {
            if (symbols.get(i) == currentSymbol) {
                actual = i;
                break;
            }
        }
    
        int posicionMostrada = actual;
        for (int paso = 1; paso <= steps; paso++) {
            posicionMostrada = (posicionMostrada + 1) % symbols.size();
            placeSymbol(symbols.get(posicionMostrada).getColor());
            notifySpinForSymbols();
            Canvas.getCanvas().wait(200);
        }
    }
    
    public void notifySpinForSymbols(){
        for(int i=0; i<symbols.size();i++){
                symbols.get(i).notifySpin();
            }
    }
}