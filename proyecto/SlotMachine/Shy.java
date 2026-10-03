
/**
 * Write a description of class Shy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shy extends Symbol
{
    private boolean isVisible = true;
    
    /**
     * Constructor de shy usa el constructor de su clase padre
     * @param color color del simbolo
     * @param shapeType figura concreta ("circle", "rectangle" o
     * "triangle") que representa graficamente al simbolo
     */
    public Shy(String color, String shapeType)
    {
        super(color, shapeType);
    }

    /**
     * @Override
     * cuando sea visible en la maquina el simbolo se volvera 
     * invisible por que es timido
     */
    public void notifySelection(){
        if(isVisible){
            isVisible = false;
            makeInvisible();
        }
        else{
            isVisible = true;
            makeVisible();
        }
    }
    
    /**
     * @Override
     * si el simbolo shy tiene su atributo de visibilidad (isVisible)
     * true se dibuja normal si se llama a makeVisible
     * pero si es false no se va a dibujar
     */
    public void makeVisible(){
        if(isVisible){
            shape.makeVisible();
        }
    }
    
    /**
     * @Override
     * @return el tipo de simbolo que es
     */
    public String getType() {
        return "shy";
    }
    
    public boolean getVisibility(){
        return isVisible;
    }
}