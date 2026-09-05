import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Representa la maquina tragamonedas completa: administra el conjunto de
 * ruedas, controla la visibilidad general del simulador y lleva el estado
 * de la ultima operacion realizada.
 * 
 * @author DiazR-RojasM
 * @version 1.0
 */
public class SlotMachine
{
    private ArrayList<Wheel> wheels;
    private Rectangle body;
    private Circle handle;
    private Rectangle arm;
    private Rectangle base;
    private boolean isVisible;
    private boolean ok;
    private int bodyWidth;
    private int currentX;

    /**
     * Constructor de la maquina (sin ruedas). Inicializa body, deja
     * isVisible en false y ok en su estado inicial.
     */
    public SlotMachine()
    {
        body = new Rectangle();
        handle = new Circle();
        arm = new Rectangle();
        base = new Rectangle();
        wheels = new ArrayList<Wheel>();
        isVisible = false;
        ok = true;
        body.changeSize(70, 30);
        base.changeSize(15, 40);
        arm.changeSize(1,20);
        handle.changeSize(10);
        body.moveHorizontal(65);
        body.moveVertical(100);
        base.moveHorizontal(60);
        base.moveVertical(170);
        arm.moveHorizontal(95);
        arm.moveVertical(135);
        handle.moveHorizontal(165);
        handle.moveVertical(130);
        base.changeColor("red");
        arm.changeColor("black");
        bodyWidth = 30;
        currentX = 135;
    }

    /**
     * Agrega una rueda nueva en la posicion indicada (normalizada).
     * La rueda nueva se crea con los mismos simbolos (mismo color y
     * orden) que ya tienen las demas ruedas, para mantener la
     * invariante de que todas las ruedas tienen igual cantidad de
     * simbolos. Luego reacomoda visualmente todas las ruedas
     * (centerWheels).
     * @param pos posicion en la que se agrega la rueda.
     */
    public void addWheel(int pos)
    {
        int position = normalizedPosition(pos, wheels.size()+1);
        Wheel nuevo = new Wheel();
        String[] simbolos = null;
        String[] shapeTypes = null;
        if(wheels.size() > 0) {
            simbolos = wheels.get(0).symbolColors();
            shapeTypes = wheels.get(0).symbolShapeType();
        }
        wheels.add(position - 1, nuevo);
        if(isVisible) {
            nuevo.makeVisible();
        }
        centerWheels();
        if(simbolos != null) {
            for (int i = 0; i < simbolos.length; i++) {
                nuevo.addSymbol(i, simbolos[i], shapeTypes[i]);
            }
        }
    }

    /**
     * Elimina la rueda en la posicion indicada (normalizada). Antes de
     * quitarla de wheels le pide que se oculte (remove()). Luego
     * recalcula el centrado.
     * @param pos posicion de la rueda a eliminar.
     */
    public void delWheel(int pos)
    {
        int position = normalizedPosition(pos, wheels.size());
        wheels.get(position - 1).remove();
        wheels.remove(position - 1);
        centerWheels();
    }

    /**
     * Agrega un simbolo de un color dado en una posicion dada a todas
     * las ruedas por igual (los simbolos son compartidos conceptualmente
     * por todas las ruedas). Normaliza la posicion una sola vez usando
     * el tamaño actual de cualquier rueda (todas tienen el mismo tamaño).
     * @param pos posicion donde se agrega el simbolo.
     * @param color color del simbolo (nombre valido segun CSS).
     * @param shapeType tipo de figura: "circle", "rectangle" o "triangle"
     */
    public void addSymbol(int pos, String color, String shapeType)
    {
        if(wheels.size() > 0) {
            int position = normalizedPosition(pos, wheels.get(0).size() + 1);
            for (int i = 0; i < wheels.size(); i++) {
                wheels.get(i).addSymbol(position - 1, color, shapeType);
            }
        }
    }

    /**
     * Elimina, en todas las ruedas, el simbolo con el color indicado.
     * @param symbol color del simbolo a eliminar.
     */
    public void delSymbol(String symbol)
    {
        for (int i = 0; i < wheels.size(); i++) {
            wheels.get(i).delSymbol(symbol);
        }
    }

    /**
     * Coloca (fija) en una rueda especifica el simbolo del color dado
     * como su simbolo actual.
     * @param wheel posicion de la rueda.
     * @param symbol color del simbolo a colocar.
     */
    public void placeSymbol(int wheel, String symbol)
    {
        int position = normalizedPosition(wheel, wheels.size());
        wheels.get(position - 1).placeSymbol(symbol);
    }

    /**
     * Gira una unica rueda especifica (le delega el giro a esa Wheel).
     * @param wheel posicion de la rueda a girar.
     */
    public void spin(int wheel)
    {
        int position = normalizedPosition(wheel, wheels.size());
        if(!wheels.get(position-1).isLocked()){
           wheels.get(position - 1).spin(); 
        }
        else if (isVisible) {
            JOptionPane.showMessageDialog(null, "La rueda está bloqueada");
        }
        if (isJackpot()) {
            body.changeColor("yellow");
        } 
        else {
        body.changeColor("magenta");
        }
        if(isVisible){
            makeVisible();
        }
    }

    /**
     * Gira todas las ruedas de la maquina (itera wheels y llama spin()
     * en cada una).
     */
    public void spin()
    {
        for (int i = 0; i < wheels.size(); i++) {
            if(!wheels.get(i).isLocked()){
                wheels.get(i).spin();
            }
        }
        if (isJackpot()) {
            body.changeColor("yellow");
        } 
        else {
        body.changeColor("magenta");
        }
        if(isVisible){
            makeVisible();
        }
    }

    /**
     * Retorna los colores de los simbolos de la rueda de referencia en el orden
     * en que estan almacenados dentro de ella, iniciando por la
     * posicion 1.
     * @return arreglo de colores.
     */
    public String[] symbols()
    {
        if(wheels.size() == 0) {
            return null;
        }
        return wheels.get(0).symbolColors();
    }

    /**
     * Devuelve la cantidad de colores distintos presentes entre los
     * simbolos de la maquina.
     * @return numero de colores distintos.
     */
    public int distinctSymbols()
    {
        String[] colores = symbols();
        int contador = 0;
    
        for (int i = 0; i < colores.length; i++) {
            boolean yaVisto = false;
            for (int j = 0; j < i; j++) {
                if (colores[j].equals(colores[i])) {
                    yaVisto = true;
                }
            }
            if (!yaVisto) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Retorna los colores de los simbolos visibles (currentSymbol) en
     * todas las ruedas de la maquina, ordenados de izquierda a derecha.
     * @return arreglo de colores de la configuracion actual.
     */
    public String[] configuration()
    {
        String[] config = new String[wheels.size()];
        for (int i = 0; i < wheels.size(); i++) {
            config[i] = wheels.get(i).getCurrentColor();
        }
        return config;
    }

    /**
     * Consulta si la configuracion actualmente visible es la
     * configuracion ganadora.
     * @return true si es ganadora, false en caso contrario.
     */
    public boolean isJackpot()
    {
        String[] config = configuration();
        for (int i = 1; i < config.length; i++) {
            if (!config[i].equals(config[0])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Hace visible la maquina: muestra body y pide a cada Wheel que se
     * haga visible. Actualiza isVisible = true.
     */
    public void makeVisible()
    {
        body.makeVisible();
        base.makeVisible();
        arm.makeVisible();
        handle.makeVisible();
        for (int i = 0; i < wheels.size(); i++) {
            wheels.get(i).makeVisible();
        }
        isVisible = true;
    }

    /**
     * Oculta la maquina y todas sus ruedas. Actualiza isVisible = false.
     */
    public void makeInvisible()
    {
        body.makeInvisible();
        base.makeInvisible();
        arm.makeInvisible();
        handle.makeInvisible();
        for (int i = 0; i < wheels.size(); i++) {
            wheels.get(i).makeInvisible();
        }
        isVisible = false;
    }

    /**
     * Termina el simulador (cierra/oculta todo antes de finalizar el
     * programa).
     */
    public void exit()
    {
        for (int i = 0; i < wheels.size(); i++) {
            wheels.get(i).remove();
        }
        body.makeInvisible();
        base.makeInvisible();
        arm.makeInvisible();
        handle.makeInvisible();
        isVisible = false;
    }

    /**
     * Devuelve si la ultima operacion fue exitosa.
     * @return true si la ultima operacion se realizo con exito.
     */
    public boolean ok()
    {
        return ok;
    }
    
    /**
     * Intercambia de posicion dos ruedas.
     * @param wheel1 posicion de la primera rueda.
     * @param wheel2 posicion de la segunda rueda.
     */
    public void swap(int wheel1, int wheel2){
        int i = normalizedPosition(wheel1, wheels.size());
        int j = normalizedPosition(wheel2, wheels.size());
        Wheel first = wheels.get(i-1);
        Wheel second = wheels.get(j-1);
        wheels.set(i-1, second);
        wheels.set(j-1, first);
        centerWheels();
        if(isVisible){
            makeVisible();
        }
    }
    
    /**
     * Bloquea la rueda en la posicion indicada.
     * @param wheel posicion de la rueda a bloquear.
     */
    public void lock(int wheel){
        int pos = normalizedPosition(wheel, wheels.size());
        wheels.get(pos-1).lock();
    }
    
    /**
     * Desbloquea la rueda en la posicion indicada.
     * @param wheel posicion de la rueda a bloquear.
     */
    public void unlock(int wheel){
        int pos = normalizedPosition(wheel, wheels.size());
        wheels.get(pos-1).unlock();
    }
    
    /**
     * Gira una rueda especifica un numero de pasos dado.
     * @param wheel posicion de la rueda a girar.
     * @param steps cantidad de pasos a girar.
     */
    public void spin(int wheel, int steps){
        int pos = normalizedPosition(wheel, wheels.size());
        if(!wheels.get(pos-1).isLocked()){
            wheels.get(pos-1).spin(steps);
        }
        else if (isVisible) {
            JOptionPane.showMessageDialog(null, "La rueda está bloqueada");
        }
        if (isJackpot()) {
            body.changeColor("yellow");
        } else {
            body.changeColor("magenta");
        }
        if(isVisible){
            makeVisible();
        }
    }
    
    /**
     * Deja la maquina en la configuracion exacta dada. Si el arreglo
     * tiene mas elementos que ruedas, no hace nada.
     * @param setSymbols colores deseados, uno por rueda.
     */
    public void spin(String[] setSymbols){
        int longitud = setSymbols.length;
        if(longitud <= wheels.size()){
            for(int i = 0; i < longitud; i++){
                wheels.get(i).placeSymbol(setSymbols[i]);
            }
        }
        else if (isVisible) {
            JOptionPane.showMessageDialog(null, "La cantidad de simbolos excede la cantidad de ruedas");
        }
        if (isJackpot()) {
            body.changeColor("yellow");
        } else {
            body.changeColor("magenta");
        }
        if(isVisible){
            makeVisible();
        }
    }

    /**
     * Metodo de apoyo privado: aplica la regla de recorte de posiciones
     * (si pos < 1 usa 1; si pos > max usa max). Es privado porque es un
     * detalle interno de implementacion, no forma parte de la interfaz
     * publica que pide el enunciado.
     * @param pos posicion solicitada.
     * @param max valor maximo permitido.
     * @return posicion normalizada.
     */

    private int normalizedPosition(int pos, int max)
    {
        if (pos < 1) {
            return 1;
        }
        if (pos > max) {
            return max;
        }
    return pos;
    }
    

    /**
     * Recalcula y aplica (via setPosition) la posicion visual de cada
     * rueda y de la maquina para mantenerlas centradas, cada vez que
     * se agrega o elimina una.
     * Privado por la misma razon de normalizedPosition.
     */
    private void centerWheels()
    {
        int nuevoW = 30 + 30 * wheels.size();
        int dx = (nuevoW - bodyWidth) / 2;
        body.changeSize(70, nuevoW);
        body.moveHorizontal(-(dx));
        bodyWidth = nuevoW;
        currentX -= dx;
        base.moveHorizontal(-(dx));
        base.changeSize(15, nuevoW + 10);
        arm.moveHorizontal(dx);
        handle.moveHorizontal(dx);
        for (int i = 0; i < wheels.size(); i++) {
            int xWheel = currentX + i * 30 + 18;
            wheels.get(i).setPosition(xWheel, 130);
        }
    }
}