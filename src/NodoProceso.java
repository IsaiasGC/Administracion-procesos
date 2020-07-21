/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaias
 */
public class NodoProceso {
    Proceso proceso;
    NodoProceso sig;
    
    public NodoProceso(Proceso proceso){
        this.proceso=proceso;
        sig=null;
    }
}
