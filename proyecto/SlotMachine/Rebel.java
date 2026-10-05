import javax.swing.JOptionPane;
/**
 * Write a description of class Rebel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rebel extends Wheel {
    /**
     * Constructor for objects of class Rebel
     */
    public Rebel()
    {
        super();
        borde.changeColor("blue");
        frame.changeColor("white");
    }
    
    /**
     * @return el tipo de rueda
     */
    @Override
    public String type(){
        return "rebel";
    }
    
    /**
     * @return true indicando que la rueda es rebelde
     */
    @Override
    public boolean isRebel(){
        return true;
    }
    
    /**
     * no bloquea la rueda, solo indica que no se puede bloquar debido a su tipo
     */
    @Override
    public void lock(){
        if(visible){
            JOptionPane.showMessageDialog(null, "No se puede bloquear una rueda rebelde");
        }
    }
    


}