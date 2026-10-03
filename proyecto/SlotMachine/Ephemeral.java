
/**
 * Write a description of class Ephemeral here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ephemeral extends Symbol
{
    private int width;
    private int height;
    private int cont = 0;
    private final int restOriginH;
    private final int restOriginW;
    
    /**
     * Constructor de Ephemeral usa el constructor de su clase padre
     * @param color color del simbolo
     * @param shapeType figura concreta ("circle", "rectangle" o
     * "triangle") que representa graficamente al simbolo
     */
    public Ephemeral(String color, String shapeType)
    {
        super(color, shapeType);
        this.restOriginH = (24/8);
        this.restOriginW = (16/8);
    }
    
    /**
     * @Override
     * cuando se gire una rueda su tamaño se reducira en
     * su altura y su ancho menos la altura y ancho respectivamente
     * divido entre 8 solo son validos spin(), spin(wheel) y
     * spin(wheel, steps) para que se reduzca el simbolo
     * despues del 7 spin ya no se reduce
     */
    public void notifySpin(){
        if(cont < 7){
            this.cont++;
            height = height-restOriginH;
            width = width-restOriginW;
            changeSize(height, width);
        }
        else if(cont == 7){
            height = 2;
            width = 2;
            changeSize(height, width);
            this.cont++;
        }
    }
    
    /**
     * @Override
     * cambia el tamaño del simbolo
     * @param height la nueva altura
     * @param width el nuevo ancho
     */
    public void changeSize(int height, int width){
        super.changeSize(height, width);
        this.height = height;
        this.width = width;
    }
    
    /**
     * @Override
     * @return el tipo de simbolo que es
     */
    public String getType() {
        return "ephemeral";
    }
}