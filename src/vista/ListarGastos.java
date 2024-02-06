
package vista;

import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Gasto;


public class ListarGastos extends javax.swing.JPanel  {
    public ListarGastos() {
        initComponents();
        listarGastos();
    }
    
    private void listarGastos(){
        List<Gasto>lista=cargaGastos();
        DefaultTableModel model=new DefaultTableModel();
        ListarGastos.tabla= new JTable(model);
        ListarGastos.jScrollPane1.setViewportView(ListarGastos.tabla);
        model.addColumn("Código");
        model.addColumn("Tipo");
        model.addColumn("Descripción");
        model.addColumn("Cantidad");
        model.addColumn("Fecha inicio");
        model.addColumn("Fecha fin");
        model.addColumn("Cuenta");
        for(Gasto g:lista){
            Object fila[]= {g.getCodigo(),g.getTipo(),g.getDescripcion(),g.getDinero(),g.getFechaInicio(),g.getFechaFin(),g.getActivoFinanciero()};
            model.addRow(fila);
        }
        
    }
    private List<Gasto> cargaGastos(){
        List<Gasto>lista=new ArrayList<>();
        Connection conn=Conexion.conectar();
        String sql="select * from gasto where codigo_persona= ?;";
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, FrmMenu.pers.getDni());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Gasto g=new Gasto(rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5).toLocalDate(),rs.getDate(6).toLocalDate(),rs.getInt(7),rs.getString(8));
                g.setCodigo(rs.getInt(1));
                lista.add(g);
            }
            conn.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return lista;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1077, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1077, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(1077, 580));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Lista de Gastos");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

   
}
