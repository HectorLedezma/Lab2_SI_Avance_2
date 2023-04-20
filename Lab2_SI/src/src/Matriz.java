package src;

import java.util.Random;

public class Matriz {
    //Tamaño de la matriz
    private final int Nfil = 17;
    private final int Ncol = 27;
    
    private final Random dado = new Random();//randomizador
    
    public Matriz(){}

    
    public int[][] getMap(){//llenado de la matriz
        int [][] Mapa = new int[Ncol][Nfil];
        for(int i = 0;i<Ncol;i++){
            for(int j = 0; j<Nfil; j++){
                if((i == 0 || i == Ncol-1) || (j == 0 || j == Nfil-1)){
                    Mapa[i][j] = 0;//Muro
                }else{
                    int bloc = dado.nextInt(0, 7);
                    if(bloc >= 6){
                        Mapa[i][j] = 3;//salida
                    }else{
                        Mapa[i][j] = 1;//campo
                    }
                    Mapa[1][1] = 1;
                    Mapa[1][2] = 1;
                    Mapa[2][1] = 1;
                    Mapa[2][2] = 1;
                }
            }
        }
        
        boolean ok = false;
        while(!ok){
            int sx = dado.nextInt(4, Ncol);
            int sy = dado.nextInt(4, Nfil);
            if(Mapa[sx][sy] != 0){
                Mapa[sx][sy] = 2;//salida
                ok = true;
            }
        }
        
        return Mapa;
    }
}
