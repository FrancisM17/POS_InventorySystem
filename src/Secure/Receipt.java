/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secure;

import java.awt.Image;
import java.awt.print.PrinterException;
import javax.swing.ImageIcon;
import javax.swing.table.TableModel;

/**
 *
 * @author Francis Manuel
 */
public class Receipt extends javax.swing.JFrame {

    /**
     * Creates new form Receipt
     */
    
    public Receipt() {
        initComponents();
        scaleImag();
    }
    
    String lsub;
    String lpay;
    String lbal;
    
    
     public void scaleImag(){
        ImageIcon icon = new ImageIcon("C:\\Francis Manuel\\Pictures\\Saved Pictures\\roated.png"); 
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        jLabel1.setIcon(scaledIcon);
    }
    public Receipt(String sub,String pay,String bal,TableModel tableModel) throws PrinterException {
        initComponents();
        
        this.lsub = sub;
        this.lpay = pay;
        this.lbal = bal;
        
        
        
        
        for(int i = 0; i < tableModel.getRowCount(); i++)
        {
            String product = (String) tableModel.getValueAt(i, 1);
            String price = (String) tableModel.getValueAt(i, 2);
            int total = (int) tableModel.getValueAt(i, 4);
            
            txtprint.setText(txtprint.getText()+"*********************************************\n");
            txtprint.setText(txtprint.getText()+"*********Bag of Dawn Enterprises*********\n");
            txtprint.setText(txtprint.getText()+"\n");
            txtprint.setText(txtprint.getText()+ "Product:"+ "\t" + product + "\n");
            txtprint.setText(txtprint.getText()+ "Price:"+ "\t" + price + "\n");
            txtprint.setText(txtprint.getText()+ "Total:"+ "\t" + total + "\n");
            
            
            
        }
        txtprint.setText(txtprint.getText()+"\n");
        txtprint.setText(txtprint.getText()+"\n");
        
        txtprint.setText(txtprint.getText()+ "                   "   +  "Subtotal : " + sub +      "\n");
        txtprint.setText(txtprint.getText()+ "                   "   +  "Pay : " +      pay +      "\n");
        txtprint.setText(txtprint.getText()+ "                   "   +  "Balance : " +  bal +      "\n");
        
        txtprint.setText(txtprint.getText()+"....................................................\n");
        txtprint.setText(txtprint.getText()+"....................................................\n");
        txtprint.setText(txtprint.getText()+"Arigatou Gosaimasu............................\n");    
        
        txtprint.print();
    }
    
    
    
    
    
    

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtprint = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 62, -1));

        txtprint.setColumns(20);
        txtprint.setRows(5);
        jScrollPane1.setViewportView(txtprint);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 0, 269, 360));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 62, 360));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        InventoryPOS o = new InventoryPOS();
        this.hide();
        o.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Receipt.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receipt().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtprint;
    // End of variables declaration//GEN-END:variables
}
