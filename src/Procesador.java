
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaias
 */
public class Procesador extends Thread{
    private VentanaI principal;
    private int politica;
    private Proceso proc;

    public Procesador(VentanaI principal) {
        this.principal=principal;
    }
    public void establecerPolitica(int politica){
        this.politica=politica;
    }
    @Override
    public void run(){
        do{
            proc=principal.sacarProceso();
            if(proc!=null){
                try {
                    Thread.sleep(proc.getTamanio()*100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
                }
                    System.out.println("Proceso "+proc.getN()+" se termino");
            }else{
                try {
                    System.out.println("Ya no hay Procesos");
                    this.finalize();
                } catch (Throwable ex) {
                    Logger.getLogger(Procesador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }while(proc!=null);
    }
}
