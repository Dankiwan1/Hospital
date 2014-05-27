/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dankiwan
 */
public class AdminTestAdding extends javax.swing.JInternalFrame {
    Connection con=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    String testname,testcost,testinitials;
    /**
     * Creates new form Adminadd
     */
    public AdminTestAdding() {
        initComponents();
        con=javaconnect.ConnectDb();
        Adminadd_table();
          btn_test_edit.setEnabled(false);
          btn_test_del.setEnabled(false);
          ListSelectionModel listSelectionModel = tb_adminadd.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
                btn_test_edit.setEnabled(!lsm.isSelectionEmpty());
                
            }
        });
        ListSelectionModel liistSelectionModel = tb_adminadd.getSelectionModel();
        liistSelectionModel.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
                ListSelectionModel lsm = (ListSelectionModel) lse.getSource();
                btn_test_del.setEnabled(!lsm.isSelectionEmpty());
                
            }
        });
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
        tb_adminadd = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_test_edit = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_test_del = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();

        setClosable(true);

        tb_adminadd.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tb_adminadd);

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-icon_1.png"))); // NOI18N
        jButton1.setText("New");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator1);

        btn_test_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Edit-icon_1.png"))); // NOI18N
        btn_test_edit.setText("Edit");
        btn_test_edit.setFocusable(false);
        btn_test_edit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_test_edit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_test_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_test_editActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_test_edit);
        jToolBar1.add(jSeparator2);

        btn_test_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/del-icon.png"))); // NOI18N
        btn_test_del.setText("Delete");
        btn_test_del.setFocusable(false);
        btn_test_del.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_test_del.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_test_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_test_delActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_test_del);
        jToolBar1.add(jSeparator3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search-icon.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton4);
        jToolBar1.add(jSeparator4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_test_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_test_editActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_test_editActionPerformed

    private void btn_test_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_test_delActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_test_delActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
AdminaddingTests tcad=new AdminaddingTests(null, closable);   
tcad.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed
 private void Adminadd_table(){
        try{
            String filltable="select * from hospitaltests";
            pst=con.prepareStatement(filltable);
            rs=pst.executeQuery( );
           tb_adminadd.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_test_del;
    private javax.swing.JButton btn_test_edit;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tb_adminadd;
    // End of variables declaration//GEN-END:variables
}