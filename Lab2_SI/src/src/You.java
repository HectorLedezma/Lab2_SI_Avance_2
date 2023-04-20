package src;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class You extends Entidad{//Agente controlable por el usuario
    
    
    private final int mov = 40;//Movimento del agente
    public You(int x, int y, Color clr,int[][] mtx) {
        super(x, y, clr,mtx);
    }

    public void controles(KeyEvent ev){//Eventos por teclado
        switch(ev.getKeyCode()){
            case 37 ->{//izquierda
                if(this.getMtx()[(this.getX())/40][(this.getY())/40] == 1){
                    if(this.getMtx()[(this.getX()-mov)/40][(this.getY())/40] != 0){
                        this.setX(this.getX()-mov);
                    }
                }else{
                    if(this.getMtx()[(this.getX()-mov)/40][(this.getY())/40] == 3){
                        this.setX(this.getX()-mov);
                    }
                }
            }
            case 38 ->{//arriba
                if(this.getMtx()[(this.getX())/40][(this.getY())/40] == 1){
                    if(this.getMtx()[(this.getX())/40][(this.getY()-mov)/40] != 0){
                        this.setY(this.getY()-mov);
                    }
                }else{
                    if(this.getMtx()[(this.getX())/40][(this.getY()-mov)/40] == 3){
                        this.setY(this.getY()-mov);
                    }
                }
            }
            case 39 ->{//derecha
                if(this.getMtx()[(this.getX())/40][(this.getY())/40] == 1){
                    if(this.getMtx()[(this.getX()+mov)/40][(this.getY())/40] != 0){
                        this.setX(this.getX()+mov);
                    }
                }else{
                    if(this.getMtx()[(this.getX()+mov)/40][(this.getY())/40] == 3){
                        this.setX(this.getX()+mov);
                    }
                }
            }
            case 40 ->{//abajo
                if(this.getMtx()[(this.getX())/40][(this.getY())/40] == 1){
                    if(this.getMtx()[(this.getX())/40][(this.getY()+mov)/40] != 0){
                        this.setY(this.getY()+mov);
                    }
                }else{
                    if(this.getMtx()[(this.getX())/40][(this.getY()+mov)/40] == 3){
                        this.setY(this.getY()+mov);
                    }
                }
            }
        }
    }
}
