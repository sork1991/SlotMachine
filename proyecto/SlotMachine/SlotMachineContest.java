import java.util.ArrayList;

public class SlotMachineContest
{
    public static int[][] solve(int n) throws SlotMachineException
    {
        SlotMachine sm = new SlotMachine(n);
        ArrayList<int[]> resultado = new ArrayList<int[]>();

        if (!sm.isJackpot()) {
            for (int i = 2; i <= n; i++) {
                int antes = sm.distinctCurrentSymbols();
                int pasosDados = 0;
                while (sm.distinctCurrentSymbols() <= antes && pasosDados < n) {
                    sm.spin(i, 1);
                    pasosDados++;
                }
                if (pasosDados > 0) {
                    resultado.add(new int[]{i, pasosDados});
                }
            }
            int[] grupo = new int[n];
            grupo[0] = 1;
            int tamGrupo = 1;

            for (int i = 2; i <= n; i++) {
                if(sm.isJackpot()){
                    break;
                }
                int pasosNecesarios = 0;

                for (int intento = 1; intento < n; intento++) {
                    if(sm.isJackpot()){
                        break;
                    }
                    int[] antes = new int[n];
                    for (int s = 0; s < n; s++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        sm.spin(i, 1);
                        antes[s] = sm.distinctCurrentSymbols();
                    }
                    for (int g = 0; g < tamGrupo; g++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        sm.spin(grupo[g], intento);
                    }

                    int[] despues = new int[n];
                    for (int s = 0; s < n; s++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        sm.spin(i, 1);
                        despues[s] = sm.distinctCurrentSymbols();
                    }

                    for (int g = 0; g < tamGrupo; g++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        sm.spin(grupo[g], n - intento);
                    }
                    int candidato = -1;
                    int cantidad = 0;
                    for (int s = 0; s < n; s++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        if (despues[s] - antes[s] > 0) {
                            candidato = s;
                            cantidad++;
                        }
                    }
                    if (cantidad == 1) {
                        pasosNecesarios = candidato + 1;
                        break;
                    }
                }

                if (pasosNecesarios > 0) {
                    sm.spin(i, pasosNecesarios);
                    resultado.add(new int[]{i, pasosNecesarios});
                }
                grupo[tamGrupo] = i;
                tamGrupo++;
            }
        }

        int[][] acciones = new int[resultado.size()][];
        for (int k = 0; k < resultado.size(); k++) {
            acciones[k] = resultado.get(k);
        }
        return acciones;
    }

    public static void simulate(int n) throws SlotMachineException
    {
        SlotMachine sm = new SlotMachine(n);
        sm.makeVisible();

        if (!sm.isJackpot()) {
            for (int i = 2; i <= n; i++) {
                int antes = sm.distinctCurrentSymbols();
                int pasosDados = 0;
                while (sm.distinctCurrentSymbols() <= antes && pasosDados < n) {
                    sm.spin(i, 1);
                    pasosDados++;
                }
            }

            int[] grupo = new int[n];
            grupo[0] = 1;
            int tamGrupo = 1;

            for (int i = 2; i <= n; i++) {
                if(sm.isJackpot()){
                    break;
                }
                int pasosNecesarios = 0;

                for (int intento = 1; intento < n; intento++) {
                    if(sm.isJackpot()){
                        break;
                    }
                    int[] antes = new int[n];
                    for (int s = 0; s < n; s++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        sm.spin(i, 1);
                        antes[s] = sm.distinctCurrentSymbols();
                    }

                    for (int g = 0; g < tamGrupo; g++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        sm.spin(grupo[g], intento);
                    }

                    int[] despues = new int[n];
                    for (int s = 0; s < n; s++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        sm.spin(i, 1);
                        despues[s] = sm.distinctCurrentSymbols();
                    }

                    for (int g = 0; g < tamGrupo; g++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        sm.spin(grupo[g], n - intento);
                    }

                    int candidato = -1;
                    int cantidad = 0;
                    for (int s = 0; s < n; s++) {
                        if(sm.isJackpot()){
                            break;
                        }
                        if (despues[s] - antes[s] > 0) {
                            candidato = s;
                            cantidad++;
                        }
                    }
                    if (cantidad == 1) {
                        pasosNecesarios = candidato + 1;
                        break;
                    }
                }

                if (pasosNecesarios > 0) {
                    sm.spin(i, pasosNecesarios);
                }
                grupo[tamGrupo] = i;
                tamGrupo++;
            }
        }
    }
}