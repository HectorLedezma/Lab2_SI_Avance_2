package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Todo extends JPanel{
    Random dado = new Random();//randomizador
    
    int [][] mtx = new Matriz().getMap();//se crea la matriz que representa al mapa
    
    private int record = 0;
    private int nivel = 70;
    Mapa map = new Mapa(mtx,1);// la matriz se transforma en un mapa visible
    You agent = new You(40,40,Color.CYAN,mtx);//se crea el agente controlable por el usuario
    int CE = 1;//cantidad de agentes "enemigos"
    Enemy [] enems = new Enemy[CE];//lista de agentes "enemigos"
    
    boolean pintado = false;
    
    public void llenar(){//llenado de la lista de agentes "enemigos"
        
        int Nfil = 13;
        int Ncol = 23;
        
        int R=255;
        int G=0;
        int B=0;
        
        for(int i = 0; i<CE;i++){
            boolean ok = false;
            while(!ok){
                
                int Ex = dado.nextInt(3, Ncol);
                int Ey = dado.nextInt(3, Nfil);
                if((mtx[Ex][Ey] != 0) && (mtx[Ex][Ey] != 3)){
                    if((G+15)<=255){
                        G = (G+15);
                    }else if((B+15)<=255){
                        B = (B+15);
                    }else{
                        R = (R)-15;
                    }
                    //System.out.println((i+1)+") R: "+R+", G: "+G+", B: "+B);
                    Color ene = new Color(R,G,B);
                    enems[i] = new Enemy(Ex*40,Ey*40,ene,mtx);
                    ok = true;
                }
            }
        }
    }
    
    public void moverE(){//llamado de movimiento a todos los agentes "enemigos"
        for(int i = 0;i<CE;i++){
            enems[i].move();
        }
    }
    
    public void reset(int n){//RePintado del mapa visible
        mtx = new Matriz().getMap();
        map = new Mapa(mtx,n);
        agent = new You(40,40,Color.CYAN,mtx);
        pintado = false;
    }
    
    public void comprobar(){
        for(int i = 0;i<CE;i++){//Evento en caso de que el agente usuario y enemigo se encuentren
            if((enems[i].getX() == agent.getX()) && (enems[i].getY() == agent.getY())){
                JOptionPane.showMessageDialog(this, "Obtubiste "+record+" puntos","GAME OVER",0);
                nivel = 1;
                reset(nivel);
                record = 0;
            }
        }
        if(mtx[agent.getX()/40][agent.getY()/40] == 2){//Evento en caso de que el agente usuario encuentre la salida del mapa
            record = record +(nivel*10);
            JOptionPane.showMessageDialog(this,"+"+(nivel*10)+" puntos\nllevas "+record+" puntos", "nivel "+nivel+" COMPLETADO",1);
            nivel = nivel +1;
            reset(nivel);
        }
    }
    
    public Todo(){
        addKeyListener(new KeyListener() {//eventos por teclado
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
                //agent.controles(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyCode = "+e.getKeyCode());
                if(e.getKeyCode() ==90){//Z
                    if(mtx[agent.getX()/40][agent.getY()/40] != 1){
                        mtx[agent.getX()/40][agent.getY()/40] = 1;
                    }
                }
                if(e.getKeyCode() ==88){//X
                    if(mtx[agent.getX()/40][agent.getY()/40] != 3){
                        mtx[agent.getX()/40][agent.getY()/40] = 3;
                    }
                }
                if(e.getKeyCode() <=40 && e.getKeyCode()>=37){//Flechas
                    agent.controles(e);
                    moverE();
                    comprobar();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        setFocusable(true);
    }
    
    
    @Override
    public void paint(Graphics grafico){
        
        map.paint(grafico);//dibujado del mapa en el frame
        agent.paint(grafico);//dibujado del usuario en el frame
        //System.out.println("pintao: "+pintado);
        if(!pintado){
            llenar();
            pintado = true;
        }
        for(int i = 0; i < CE; i++){
            try{
                enems[i].paint(grafico);//dibujado de los enemigos en el frame
                //System.out.println(i);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        //System.out.println(mtx[3][1]);
    }
    
    public static void main(String [] args){
        
        JFrame ventana = new JFrame("Escape Field");//creacion del frame
        Todo game = new Todo();//creacion del mapa y sus agentes
        ventana.add(game);
        //propiedades del frame
        ventana.setSize(1080,700);
        ventana.setLocation(140, 20);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        //mensaje de inicio
        JOptionPane.showMessageDialog(ventana, "Hola, Bienvenido a Escape Field un juego en el que el objetivo es\n"+
                                                            "llegar hasta la salida del campo (cuadro gris), pero cuidado\n"+
                                                            "hay enemigos rondando, ellos no pueden verte, pero si te atrapan pierdes\n\n"+
                                                            "Usa las flechas de tu teclado para moverte.\n\n"+
                                                            "Puedes cavar un agujero con X\n"+
                                                            "para esconderte de los enemigos o puedes esconderte en uno del campo (cuadros marron)\n\n"+
                                                            "Dentro de los agujeros solo podras moverte a los agujeros adyacentes (si es que los hay)\n"+
                                                            "Usa Z para llenar el agujero y volver a moverte por el campo.\n\n"+
                                                            "a medida que avanzas ganaras puntos, buena suerte :D",
                "Bienvenido", 3);
        //Dibijado del mapa en tiempo real
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Todo.class.getName()).log(Level.SEVERE, null, ex);
            }
            game.repaint();
        }
    }
}
