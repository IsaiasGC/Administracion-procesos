
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaias
 */
public class PanelDibujos extends JPanel{
    
    private ListaProcesos procesos;
    private int e;
    private int s;
    
    public PanelDibujos(ListaProcesos procesos){
        this.procesos=procesos;
    }
    public void setE(int e){
        this.e=e;
    }
    public void setS(int s){
        this.s=s;
    }
    public void paint(Graphics g){
        super.paint(g);
        int x=1;
        Proceso proceso=null;
        setBackground(Color.WHITE);
        for (int i=0; i<procesos.size(); i++) {
            proceso=procesos.ver(i);
            g.setColor(proceso.color);
            g.fillRect(x, proceso.y, 200, proceso.getTamanio()*2);
        }
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 199, 499);
    }
}
