/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaias
 */
public class ListaProcesos {
    private NodoProceso inicio;
    private int size;
    
    public ListaProcesos(){
        inicio=null;
        size=0;
    }
    public void agregar(Proceso proceso){
        if(inicio==null){
            inicio=new NodoProceso(proceso);
        }else{
            NodoProceso rec=inicio;
            while(rec.sig!=null)
                rec=rec.sig;
            rec.sig=new NodoProceso(proceso);
        }
        size++;
    }
    public Proceso sacarFIFO(){
        Proceso aux=null;
        if(inicio!=null){
            aux=inicio.proceso;
            inicio=inicio.sig;
            size--;
        }
        return aux;
    }
    public Proceso sacarMenor(){
        Proceso aux=null;
        if(inicio!=null){
            NodoProceso menor=inicio;
            NodoProceso rec=inicio;
            NodoProceso ant=inicio;
            while(rec.sig!=null){
                if(menor.proceso.getTamanio()>rec.sig.proceso.getTamanio()){
                    menor=rec.sig;
                    ant=rec;
                }
                rec=rec.sig;
            }
            aux=menor.proceso;
            if(ant==menor){
                inicio=inicio.sig;
            }else{
                ant.sig=menor.sig;
            }
            size--;
        }
        return aux;
    }
    public Proceso ver(int n){
        Proceso aux=null;
        if(inicio!=null){
            NodoProceso rec=inicio;
            while(n>0 && rec.sig!=null){
                rec=rec.sig;
                n--;
            }
            if(rec!=null){
                aux=rec.proceso;
            }
        }
        return aux;
    }
    public int size(){
        return size;
    }
}
