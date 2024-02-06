
package vista;

import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Gasto;
import java.time.*;
import modelo.Cuenta;
import modelo.Ingreso;


public class ListarPresupuestoCuenta extends javax.swing.JPanel  {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    
    public ListarPresupuestoCuenta(LocalDate fechaInicio,LocalDate fechaFin) {
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        initComponents();
        listarGastos();
        listarIngresos();
        listarCuenta();
        cantidadPresupuesto();
        cantidadSaldoCuenta();
    }
    private void listarCuenta(){
        List<Cuenta>lista=cargaCuenta();
        DefaultTableModel model=new DefaultTableModel();
        ListarPresupuestoCuenta.tabla1= new JTable(model);
        ListarPresupuestoCuenta.jScrollPane2.setViewportView(ListarPresupuestoCuenta.tabla1);
        model.addColumn("Código");
        model.addColumn("Banco");
        model.addColumn("Descripción");
        model.addColumn("Saldo");
        for(Cuenta g:lista){
            Object fila[]= {g.getCodigo(),g.getBanco(),g.getDescripcion(),g.getDinero()};
            model.addRow(fila);
        }
        
        
    }
     private List<Cuenta> cargaCuenta(){
        List<Cuenta>lista=new ArrayList<>();
        Connection conn=Conexion.conectar();
        String sql="select af.* from cuenta af, cuenta_persona ap where ap.cod_persona= ? AND ap.cod_cuenta=af.codigo ;";
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement(sql);
            pst.setString(1, FrmMenu.pers.getDni());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                lista.add(new Cuenta(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)));
            }
            conn.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return lista;
    }
    private void listarIngresos(){
        List<Ingreso>lista=cargaIngresos();
        DefaultTableModel model=new DefaultTableModel();
        ListarPresupuestoCuenta.tabla2= new JTable(model);
        ListarPresupuestoCuenta.jScrollPane3.setViewportView(ListarPresupuestoCuenta.tabla2);
        model.addColumn("Código");
        model.addColumn("Tipo");
        model.addColumn("Descripción");
        model.addColumn("Cantidad");
        model.addColumn("Fecha inicio");
        model.addColumn("Fecha fin");
        model.addColumn("Cuenta");
        for(Ingreso g:lista){
            Object fila[]= {g.getCodigo(),g.getTipo(),g.getDescripcion(),g.getDinero(),g.getFecha_inicio(),g.getFecha_fin(),g.getActivoFinanciero()};
            model.addRow(fila);
        }
        
    }
    private List<Ingreso> cargaIngresos(){
        List<Ingreso>lista=new ArrayList<>();
        Connection conn=Conexion.conectar();
        String sql1="SELECT * FROM ingreso  WHERE (fecha_inicio>=? OR fecha_fin<=?)AND codigo_cuenta "
                + "IN (SELECT codigo FROM cuenta WHERE codigo IN (SELECT cod_cuenta FROM cuenta_persona WHERE cod_persona=?));";
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement(sql1);
            pst.setDate(1, Date.valueOf(fechaInicio));
            pst.setDate(2, Date.valueOf(fechaFin));
            pst.setString(3, FrmMenu.pers.getDni());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Ingreso i=new Ingreso(rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5).toLocalDate(),rs.getDate(6).toLocalDate(),rs.getString(7),rs.getInt(8));
                i.setCodigo(rs.getInt(1));
                lista.add(i);
            }
            conn.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return lista;
    }
    
    private void listarGastos(){
        List<Gasto>lista=cargaGastos();
        DefaultTableModel model=new DefaultTableModel();
        ListarPresupuestoCuenta.tabla= new JTable(model);
        ListarPresupuestoCuenta.jScrollPane1.setViewportView(ListarPresupuestoCuenta.tabla);
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
        String sql="SELECT * FROM gasto WHERE (fecha_inicio>=? OR fecha_fin<=?) AND codigo_cuenta "
                + "IN (SELECT codigo FROM cuenta WHERE codigo IN (SELECT cod_cuenta FROM cuenta_persona WHERE cod_persona=?));";
        PreparedStatement pst;
        try{
            pst=conn.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(fechaInicio));
            pst.setDate(2, Date.valueOf(fechaFin));
            pst.setString(3, FrmMenu.pers.getDni());
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
    private void cantidadPresupuesto(){
        double total=0;
        for(Ingreso i: cargaIngresos()){
            total+=i.getDinero();
        }
        for(Gasto g: cargaGastos()){
            total-=g.getDinero();
        }
        textoPresupuesto.setText(String.format("%.2f", total));
    }
    private void cantidadSaldoCuenta(){
        double total=0;
        for(Cuenta c: cargaCuenta()){
            total+=c.getDinero();
        }
        textoSaldo.setText(String.format("%.2f", total));
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoPresupuesto = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        textoSaldo = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1077, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1077, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(1077, 580));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Presupuesto");

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Cuentas bancarias");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Gasto");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Ingreso");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Tu presupuesto es de:");

        textoPresupuesto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textoPresupuesto.setText("{dinero}");

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabla1);

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tabla2);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Tu saldo total es de:");

        textoSaldo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textoSaldo.setText("{saldo}");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textoPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoSaldo, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoPresupuesto)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoSaldo)))
                .addContainerGap(70, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable tabla;
    public static javax.swing.JTable tabla1;
    public static javax.swing.JTable tabla2;
    private javax.swing.JLabel textoPresupuesto;
    private javax.swing.JLabel textoSaldo;
    // End of variables declaration//GEN-END:variables

   
}
