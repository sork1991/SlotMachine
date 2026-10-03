import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SlotMachineContestTest
{
    /**
     * Default constructor for test class SlotMachineContestTest
     */
    public SlotMachineContestTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    
    @Test
    public void shouldGenerateSameSizeInWheelsandSymbols(){
        SlotMachine sM = new SlotMachine(4);
        int sizeW = sM.configuration().length;
        String[] s = sM.symbols();
        int sizeS = s.length;
        assertEquals(4, sizeW);
        assertEquals(4, sizeS);
    }
    
    @Test
    public void shouldGenereteMoreWheelsThanSymbolsWithNumberMajorthanColors(){
        SlotMachine sM = new SlotMachine(8);
        int sizeW = sM.configuration().length;
        String[] s = sM.symbols();
        int sizeS = s.length;
        assert(sizeW > sizeS);
    }
    
    @Test
    public void accordingDrRmShouldNotCreateWheelAndSymbolWithNegativeNumber(){
        int n = -3;
        SlotMachine sM = new SlotMachine(n);
        int sizeW = sM.configuration().length;
        String[] s = sM.symbols();
        int sizeS = s.length;
        assertEquals(0, sizeW);
        assertEquals(0, sizeS);
    }
    
    @Test
    public void accordingDrRmShouldHaveNDifferentColors()
    {
        int n = 3;
        SlotMachine sM = new SlotMachine(n);
        int res = sM.distinctSymbols();
        assertEquals(n, res);
    }
    
    /** 
     * Verifica que si la rueda no esta resuelta al inicio debe tener
     * mas de 0 movimientos
     */
    @Test
    public void accordingDrRmShouldReturnNonEmptyMovesWhenNotInJackpot() {
        int n = 4;
        SlotMachine sM = new SlotMachine(n);
        int[][] moves = SlotMachineContest.solve(n);
        if (!sM.isJackpot()) {
            assertTrue(moves.length > 0);
        }
    }

    /**
     * Verifica que no tenga pasos negativos
     */
    @Test
    public void accordingDrRmShouldNotReturnNegativeStepsInSolve() {
        int n = 4;
        int[][] moves = SlotMachineContest.solve(n);
        for (int[] move : moves) {
            int steps = move[1];
            assertTrue(steps > 0);
        }
    }

    /**
     * Verifica que si la rueda está en jackpot no hace nada
     */
    @Test
    public void shouldReturnZeroMovesWhenMachineStartsInJackpot() {
        int n = 3;
        int[][] moves = SlotMachineContest.solve(n);
        SlotMachine sM = new SlotMachine(n);
        
        if (sM.isJackpot()) {
            assertEquals(0, moves.length);
        }
    }

    /**
     * Verifica que el indice de las ruedas a mover
     * en el resultado si sea valido, debe estar entre 1 y n
     */
    @Test
    public void shouldNotReturnInvalidWheelIndicesInSolve() {
        int n = 4;
        int[][] moves = SlotMachineContest.solve(n);
        
        for (int[] move : moves) {
            int wheelIndex = move[0];
            assertTrue(wheelIndex >= 1 && wheelIndex <= n);
        }
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}