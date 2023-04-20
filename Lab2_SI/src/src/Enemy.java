package src;

import java.awt.Color;
import java.util.Random;


public class Enemy extends Entidad{//Agente Enemigo
    Random des = new Random();//randomizador
    private int [] CoAnterior = new int[2];
    public Enemy(int x, int y, Color clr,int[][] mtx) {
        super(x, y, clr,mtx);
    }
    
    private int direccion(boolean n){
        int res;
        if(n){
            res = 1;
        }else{
            res = -1;
        }
        return res;
    }
    
    public void move(){
        //se decide aleatoriamente el eje en el cual se movera el agente tras cada movimiento del usuario
        
        System.out.println("anterior: \nX="+CoAnterior[0]+"\nY="+CoAnterior[1]);
        
        boolean movido = false;
        
        int errorCont = 0;
        
        while(!movido){
            boolean horver = des.nextBoolean();//true = horizontal / false = vertical
            int masmen = direccion(des.nextBoolean());//posibles valores: -1 y +1

            int npX = (this.getX()+(masmen*40))/40;
            int npY = (this.getY()+(masmen*40))/40;


            int NPMX = this.getMtx()[npX][(this.getY())/40];
            int NPMY = this.getMtx()[(this.getX())/40][npY];

            if(horver){
                if(NPMX != 3 && NPMX != 0){
                    if(npX != CoAnterior[0]){
                        CoAnterior[0] = this.getX()/40;
                        CoAnterior[1] = this.getY()/40;
                        this.setX(npX*40);
                        movido = true;
                    }else{
                        errorCont = errorCont + 1;
                    }
                }
            }else{
                if(NPMY != 3 && NPMY != 0){
                    if((npY != CoAnterior[1])){
                        CoAnterior[0] = this.getX()/40;
                        CoAnterior[1] = this.getY()/40;
                        this.setY(npY*40);
                        movido = true;
                    }else{
                        errorCont = errorCont + 1;
                    }
                }
            }
            if(errorCont > 10){
                //System.out.println("sin salida");
                CoAnterior[0] = 0;
                CoAnterior[1] = 0;
                errorCont = 0;
            }
        }
    }
    
    
}
