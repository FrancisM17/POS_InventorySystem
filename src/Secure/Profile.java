/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secure;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Francis Manuel
 */
public final class Profile extends javax.swing.JFrame {

    /**
     * Creates new form Profile
     */
    public Profile() {
        initComponents();
        scaleImage();
        scaleImag();
    }
    public void scaleImage(){
        ImageIcon icon = new ImageIcon("C:\\Users\\Francis Manuel\\Documents\\NetBeansProjects\\InventoryManagementSystem\\src\\Secure\\but.jpg"); 
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);
    }
    public void scaleImag(){
        ImageIcon icon = new ImageIcon("C:\\Francis Manuel\\Pictures\\Saved Pictures\\bag.jpg"); 
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        jLabel1.setIcon(scaledIcon);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Back = new javax.swing.JButton();
        Cont = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Cont1 = new javax.swing.JButton();
        label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Back.setBackground(new java.awt.Color(255, 204, 0));
        Back.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        getContentPane().add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 510, 120, 42));

        Cont.setBackground(new java.awt.Color(255, 204, 0));
        Cont.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cont.setText("Contact Us");
        Cont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContActionPerformed(evt);
            }
        });
        getContentPane().add(Cont, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 510, 120, 42));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 560));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 20)); // NOI18N
        jLabel2.setText("<html>Bag of Dawn Enterprises is a business that ventures into bag-making, specializing in Corporate Giveaways, is an endeavour of its family members (now ON its third generation/  quality products has been the primary goal /we have served known ). The company is our new baby, but the passion and craft of making quality products has been theprimary goal. Re-organized and is supported by the head of the family, Ms. Shiela Lopez – Dizon is the brain and is now the owner of previous company, now renamed Bag of Dawn enterprises.  The former started supplying bags and leatherette products to customers such as department stores, bookstores, corporations and even schools. It further focused and started supplying multi-national companies with bags and other selected items either for corporate giveaways or for commercial purposes. It was able to gain more contacts for its ingenuity in creating new designs based on clients’ demands.<html>  In the past years of the former company, we have servedknown and established corporations in the country, not to mention one that are included in the top 1000 companies.  The business is committed in producing these products with superior craftsmanship and quality for its growing client base. It continues its goal in providing quality yet affordable products to the market.<html>");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 570, 510));

        Cont1.setBackground(new java.awt.Color(255, 204, 0));
        Cont1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cont1.setText("Exit");
        Cont1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cont1ActionPerformed(evt);
            }
        });
        getContentPane().add(Cont1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 510, 120, 42));
        getContentPane().add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        this.hide();
        Greet g = new Greet();
        g.setVisible(true);
    }//GEN-LAST:event_BackActionPerformed

    private void ContActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContActionPerformed
        // TODO add your handling code here:
        new ContactUs().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ContActionPerformed

    private void Cont1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cont1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
        
    }//GEN-LAST:event_Cont1ActionPerformed

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
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Cont;
    private javax.swing.JButton Cont1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}
