package src;

import java.awt.Color;
import java.awt.Graphics;

public class Entidad {//clase del objeto Agente
    //posicion
    private int x;
    private int y;
    //tamaño
    private int ancho = 40;
    private int alto =  40;
    
    //Entorno o ambiente
    private int[][] mtx;
    
    private Color clr;
    
    public Entidad(int x, int y,Color clr,int [][] mtx) {//constructor del agente
        this.x = x;
        this.y = y;
        this.clr = clr;
        this.mtx = mtx;
    }
    
    public Entidad(Color clr){
        this.clr = clr;
    }
    
    public void paint(Graphics grafico){//dibujado del agente en el mapa
        grafico.setColor(clr);
        grafico.fillOval(x, y, ancho, alto);
        grafico.setColor(Color.black);
        grafico.drawOval(x, y, ancho, alto);
        
    }

    public int[][] getMtx() {//Ambiente
        return mtx;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
