package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Lienzo extends JPanel{
    
    private int x = 0;
    private int y = 0;
    
    private int[][] coordenadas = {{0,0},{0,0},{0,0},{0,0},{0,0}};
    private int[][] colores = {{0,0},{0,0},{0,0},{0,0},{0,0}};
    
    private int iterCoordenadas = 0;
    private int last = 1;
    private int iterConfig = 0;
    private int first=0;
    private boolean firstLap = true;
    
    public Lienzo(){
        updateBackground(iterConfig);
        updateBall(iterConfig);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        int pos = iterCoordenadas;         
        try {
            g.fillOval(coordenadas[0][0], coordenadas[0][1], 15, 15);
            Thread.sleep(5);
            g.fillOval(coordenadas[1][0], coordenadas[1][1], 15, 15);
            Thread.sleep(5);
            g.fillOval(coordenadas[2][0], coordenadas[2][1], 15, 15);
            Thread.sleep(5);
            g.fillOval(coordenadas[3][0], coordenadas[3][1], 15, 15);
            Thread.sleep(5);
            g.fillOval(coordenadas[4][0], coordenadas[4][1], 15, 15);
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePosition(int x, int y){
        savePosition(x, y);
        this.repaint();
    }
    
    public void updateColors(int ball, int background){
        saveColor(ball, background);
        updateBackground(iterConfig);
        updateBall(iterConfig);
    }
    
    public boolean loadPreConfiguration(){
        if(iterConfig==0){
            iterConfig=colores.length-1;
        }else{
            iterConfig--;
        }
        updateBackground(iterConfig);
        updateBall(iterConfig);
        
        if(iterConfig == first){
            return false;
        }
        return true;
    }
    
    public boolean loadNextConfiguration(){
        if(iterConfig == colores.length-1){
            iterConfig=0;
        }else{
            iterConfig++;
        }
        updateBackground(iterConfig);
        updateBall(iterConfig);
        
        if(last==0){
            if(iterConfig == 4){
                return false;
            }
        }else{
            if(iterConfig == last-1){
                return false;
            }
        }
        return true;
    }
    
    private void savePosition(int x, int y){     
        if(iterCoordenadas==coordenadas.length){
            iterCoordenadas=0;
        }
        coordenadas[iterCoordenadas][0] = x;
        coordenadas[iterCoordenadas][1] = y;     
        iterCoordenadas++;
    }
    
    private void saveColor(int ball, int background){
        
        if(first==last){
            if(first==colores.length-1){
                first=0;
            }else{      
                first++;
            }
        }
        colores[last][0] = ball;
        colores[last][1] = background;
        iterConfig = last;
     
        if(last==colores.length-1){
            last=0;
        }else{
            last++;
        }
    }
    
    private void updateBackground(int pos){
        switch(colores[pos][1]){
            case 0:
                this.setBackground(Color.white);
                break;
            case 1:
                this.setBackground(Color.red);
                break;
            case 2:
                this.setBackground(Color.blue);
                break;
            case 3:
                this.setBackground(Color.yellow);
                break;
        }
    }
    
    private void updateBall(int pos){
        switch(colores[pos][0]){
            case 0:
                this.setForeground(Color.black);
                break;
            case 1:
                this.setForeground(Color.GREEN);
                break;
            case 2:
                this.setForeground(Color.PINK);
                break;
            case 3:
                this.setForeground(Color.orange);
                break;
        }
    }
}
