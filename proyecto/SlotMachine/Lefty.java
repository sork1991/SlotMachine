
/**
 * Write a description of class Lefty here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lefty extends Wheel
{
    /**
     * Constructor for objects of class Lefty
     */
    public Lefty()
    {
        super();
        borde.changeColor("red");
        frame.changeColor("white");
    }

    /**
     * @ return el tipo de rueda
     */
    @Override
    public String type(){
        return "lefty";
    }
    
    /**
     * al girar, si hay una rueda a su izquiera copia su estado,
     * el simbolo que está mostrando y si está bloqueada, si no hay nada a la
     * izquierda, gira normalmente
     */
    @Override
    public void spin(Wheel izquierda)
    {
        if (izquierda != null) {
            placeSymbol(izquierda.getCurrentColor());
            if(izquierda.isLocked()){
                lock();
            }
        } else {
            super.spin(izquierda);
        }
    }
    
    /**
     * al girar, si hay una rueda a su izquiera copia su estado,
     * el simbolo que está mostrando y si está bloqueada, si no hay nada a la
     * izquierda, gira normalmente
     */
    @Override
    public void spin(Wheel izquierda, int steps)
    {
        if (izquierda != null) {
            placeSymbol(izquierda.getCurrentColor());
            if(izquierda.isLocked()){
                lock();
            }
        } else {
            super.spin(izquierda, steps);
        }
    }
}