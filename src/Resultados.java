
import java.util.Date;
import java.util.Vector;
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
public class Resultados extends javax.swing.JDialog {
    
    DefaultTableModel model;
    TextFileManager manager;
    int cont;
    
    /**
     * Creates new form Resultados
     */
    public Resultados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Procesos...");
        initComponents();
        setLocationRelativeTo(null);
        manager=new TextFileManager("DatosProcesos.txt");
    }
    public void LlenarTabla(){
        model=new DefaultTableModel();
        model.addColumn("No. Proceso");
        model.addColumn("Tamaño");
        model.addColumn("Hora Llegada");
        model.addColumn("Hora Salida");
        model.addColumn("Tiempo Duro");
        result.setModel(model);
        Vector<String[]> procesos=manager.getLines();
        Object o[];
        if(procesos.size()!=0){
            Date d;
            for (int i=0; i<procesos.size(); i++) {
                o=procesos.get(i);
                d=new Date((String)o[2]);
                o[2]=d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
                d=new Date((String)o[3]);
                o[3]=d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
                d=new Date((String)o[4]);
                o[4]=d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
                model.addRow(o);
            }
        }
        result.validate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        result = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        result.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(result);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable result;
    // End of variables declaration//GEN-END:variables
}