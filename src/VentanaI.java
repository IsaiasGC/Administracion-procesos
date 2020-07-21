
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaias
 */
public class VentanaI extends JFrame implements ActionListener{
    
    private PanelDibujos dibujos;
    private JButton llegada;
    private JButton salida;
    private JButton resultados;
    private ButtonGroup grupo;
    private DatosEntrada entrada;
    private Resultados resul;
    private ListaProcesos procesos;
    private Procesador procesador;
    private TextFileManager manager;
    private int memoria=250;
    private JLabel mem;
    private int p;
    private int y=1;
    
    
    public VentanaI(){
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Procesamiento");
        setLayout(null);
        
        procesos=new ListaProcesos();
        
        dibujos=new PanelDibujos(procesos);
        dibujos.setBounds(5, 5, 200, 500);
        
        mem=new JLabel("250");
        mem.setBounds(210, 155, 80, 30);
        
        llegada=new JButton("Llegada");
        llegada.addActionListener(this);
        llegada.setBounds(300, 50, 100, 25);
        
        salida=new JButton("Salida");
        salida.addActionListener(this);
        salida.setBounds(500, 50, 100, 25);
        salida.setEnabled(false);
        
        JRadioButton fifo=new JRadioButton("FiFo");
        fifo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                p=0;
                salida.setEnabled(true);
            }
        });
        fifo.setBounds(300, 140, 80, 35);
        
        JRadioButton menor=new JRadioButton("menor");
        menor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                p=1;
                salida.setEnabled(true);
            }
        });
        menor.setBounds(300, 170, 80, 35);
        
        
        
        
        grupo=new ButtonGroup();
        grupo.add(fifo);
        grupo.add(menor);
        
        
        resultados=new JButton("Datos Procesos");
        resultados.addActionListener(this);
        resultados.setBounds(500, 150, 100, 35);
        
        add(dibujos);
        add(mem);
        add(llegada);
        add(fifo);
        add(menor);
        add(salida);
        add(resultados);
        
        entrada=new DatosEntrada(this, true);
        resul=new Resultados(this, true);
        
        manager=new TextFileManager("DatosProcesos.txt");
        
        setVisible(true);
    }
    public void agregarProceso(Proceso proc){
        if(proc.getTamanio()<=memoria && proc.getTamanio()>0){
            proc.y=y;
            procesos.agregar(proc);
            memoria-=proc.getTamanio();
            pintarProcesos();
            y=(250-memoria)*2;
        }else{
            System.out.println("El proceso es mas grande que la capacidad");
        }
    }
    public void pintarProcesos(){
        dibujos.repaint();
        dibujos.validate();
        validate();
        mem.setText(memoria+"");
    }
    public void salir(){
        grupo.clearSelection();
        salida.setEnabled(false);
        procesador=new Procesador(this);
        procesador.establecerPolitica(p);
        procesador.start();
        pintarProcesos();
        y=1;
    }
    public Proceso sacarProceso(){
        Proceso proc=null;
        switch(p) {
            case 0:
                proc=procesos.sacarFIFO();
                break;
            case 1:
                proc=procesos.sacarMenor();
                break;
        }
        if(proc!=null){
            proc.setHrSalida();
            memoria+=proc.getTamanio();
            try {
                manager.writeLine(proc.getN()+"\t"+proc.getTamanio()+"\t"+proc.getHrLlegada()+"\t"+proc.getHrSalida()+"\t"+proc.getTiempo());
            } catch (IOException ex) {
                Logger.getLogger(VentanaI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        pintarProcesos();
        return proc;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Llegada")){
            entrada.limpiar();
            entrada.show();
        }else{
            if(ae.getActionCommand().equals("Salida")){
                salir();
            }else{
                resul.LlenarTabla();
                resul.show();
            }
        }
    }
    public static void main(String[]args){
        new VentanaI();
    }
}
