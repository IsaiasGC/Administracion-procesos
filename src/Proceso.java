
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaias
 */
public class Proceso {
    private int n;
    private int tamanio;
    private Calendar hrLlegada;
    private Calendar hrEntrada=null;
    private Calendar hrSalida=null;
    int y;
    Color color;
    
    public Proceso(int n, int tamanio, Date hrLlegada){
        this.n=n;
        this.tamanio=tamanio;
        this.hrLlegada=new GregorianCalendar();
        this.hrLlegada.setTime(hrLlegada);
        
        Random numAzar=new Random();
        int r=(int)(numAzar.nextDouble()*255);
        int g=(int)(numAzar.nextDouble()*255);
        int b=(int)(numAzar.nextDouble()*255);
        
        color=new Color(r, g, b);
    }
    public void setHrSalida(){
        hrSalida=new GregorianCalendar();
    }
    public int getN(){
        return n;
    }
    public int getTamanio(){
        return tamanio;
    }
    public Date getHrLlegada(){
        return hrLlegada.getTime();
    }
    public Date getHrSalida(){
        if(hrSalida!=null)
            return hrSalida.getTime();
        return null;
    }
    public Date getTiempo(){
        Calendar c=new GregorianCalendar();
        Date d=hrLlegada.getTime();
        c.setTime(hrSalida.getTime());
        c.add(Calendar.SECOND, -d.getSeconds());
        c.add(Calendar.MINUTE, -d.getMinutes());
        c.add(Calendar.HOUR, -d.getHours());
        return c.getTime();
    }
}
