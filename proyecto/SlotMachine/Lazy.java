
/**
 * Write a description of class Lazy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lazy extends Wheel {
    private boolean intento;
    /**
     * Constructor for objects of class Rebel
     */
    public Lazy()
    {
        super();
        borde.changeColor("green");
        frame.changeColor("white");
        intento = false;
    }
    
    /**
     * @return el tipo de rueda
     */
    @Override
    public String type(){
        return "lazy";
    }
    
    /**
     * al llamar el metodo una vez, la rueda no gira,y actualiza intento a true,
     * se requiere llamar el metodo una segunda vez para girar y de nuevo
     * actualizar intento a false
     */
    @Override
    public void spin()
    {
        if (!intento) {
            intento = true;
        } else {
            super.spin();
            intento = false;
        }
    }
    
    /**
     * gira unicamente la mitad de pasos que se le indicó
     */
    @Override
    public void spin(int steps)
    {
        super.spin(steps/2);
    }
}