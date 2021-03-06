/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Secure;

//import com.mysql.jdbc.Statement;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Francis Manuel
 */
public final class InventoryPOS extends javax.swing.JFrame {

    /**
     * Creates new form Inventory
     */
    public InventoryPOS() {
        
        initComponents();
        scaleImage();
        //scaleImag();
        scaleImage1();
        time1();
        date1();
        high();
       
        //table_update();
        
    }
    
    String lname;
    
    
    
    
    Connection con1;
    PreparedStatement insert;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    
    public void scaleImage(){
        ImageIcon icon = new ImageIcon("C:\\Francis Manuel\\Pictures\\Saved Pictures\\light-yellow-wood-concept.jpg"); 
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label2.getWidth(), label2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label2.setIcon(scaledIcon);
        
    }
    public void scaleImag(){
        ImageIcon icon = new ImageIcon("C:\\Users\\Francis Manuel\\Documents\\NetBeansProjects\\InventoryManagementSystem\\src\\Secure\\dawn.png"); 
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(jLabel4.getWidth(), jLabel4.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        jLabel4.setIcon(scaledIcon);
    }
    
     public void scaleImage1(){
        BufferedImage im = null;
        try{
            im = ImageIO.read(new File("C:\\Users\\Francis Manuel\\Documents\\NetBeansProjects\\InventoryManagementSystem\\src\\Secure\\logo1.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        Image img = im.getScaledInstance(jLabel14.getWidth(), jLabel14.getHeight(),
        Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(img);
        jLabel14.setIcon(imageIcon);
    }
    
    public void time1(){
    Date d = new Date();
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    jLabel16.setText(s.format(d));
    
    }
    public void date1(){
        new Timer(0, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               Date d = new Date();
            SimpleDateFormat s = new SimpleDateFormat("hh:mm:ss a");
            jLabel17.setText(s.format(d));
            }
            
        }).start();
    }
   public void high(){
       jLabel3.setBackground(Color.BLACK);
   }
      
      
      
      
      
      
      
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    
    private void InventoryPOS()
        {
            String name = txtprocode.getText();
            
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos","root","");
            insert = con1.prepareStatement("select * from product where barcode=?");
            insert.setString(1, name);
            rs = insert.executeQuery();
            
            while(rs.next())
            {
                int currentqty;
                
                currentqty = rs.getInt("qty");
                
                int price = Integer.parseInt(txtprice.getText());
                int qtynew = Integer.parseInt(txtqty.getText());
                
                int tot = price * qtynew;
                
                if(qtynew >= currentqty)
                {
                    JOptionPane.showMessageDialog(this, "Available Product" + " = " + currentqty);
                    JOptionPane.showMessageDialog(this, "Quantity is not Enough");
                }
                else
                {
                    model = (DefaultTableModel)jTable1.getModel();
                    model.addRow(new Object[]
                    {
                        txtprocode.getText(),
                        txtproname.getText(),
                        txtprice.getText(),
                        txtqty.getText(),
                        tot,
                    });
                    
                    
                    int sum = 0;
                    for(int i = 0; i<jTable1.getRowCount(); i++)
                    {
                        sum = sum + Integer.parseInt(jTable1.getValueAt(i, 4).toString());
                        
                    }
                    
                    txtsub.setText(Integer.toString(sum));
                    
                    
                    txtprocode.setText("");
                    txtproname.setText("");
                    txtprice.setText("");
                    txtqty.setText("");
                    txtprocode.requestFocus();
                }
                
                
                
                
            }
            
            
                 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InventoryPOS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPOS.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
        }
    
    
    
    
    private void sales() throws SQLException
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        
        String subtot = txtsub.getText();
        
        String pay = txtpay.getText();
        String bal = txtbal.getText();
        int lastinsertid = 0;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos","root","");
            String query = "insert into sales(date,subtotal,pay,balance)values(?,?,?,?)";
            insert=con1.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, date);
            
            insert.setString(2, subtot);
            insert.setString(3, pay);
            insert.setString(4, bal);            
            insert.executeUpdate();
            ResultSet generatedKeyResult = insert.getGeneratedKeys();
            
            
            if(generatedKeyResult.next())
            {
                lastinsertid = generatedKeyResult.getInt(1);
            }
            
            int rows = jTable1.getRowCount();
            
            
            String query1 = "insert into sales_product(sales_id,product_id,sell_price,qty,total)values(?,?,?,?,?)";
            insert=con1.prepareStatement(query1);
            
            String product_id = "";
            String price = "";
            String qty = "";
            int total = 0;
            
            for(int i = 0; i<jTable1.getRowCount(); i++)
            {
                product_id = (String)jTable1.getValueAt(i, 0);
                price = (String)jTable1.getValueAt(i, 2);
                qty = (String)jTable1.getValueAt(i, 3);
                total = (int)jTable1.getValueAt(i, 4);
                
                insert.setInt(1, lastinsertid);
                insert.setString(2, product_id);
                insert.setString(3, price);
                insert.setString(4, qty);
                insert.setInt(5, total);
                insert.executeUpdate();   
            }
            
            String query2 = "update product set qty = qty-? where barcode=?";
            insert=con1.prepareStatement(query2);
            
            for(int i = 0; i<jTable1.getRowCount(); i++)
            {
                product_id = (String)jTable1.getValueAt(i, 0);
                qty = (String)jTable1.getValueAt(i, 3);
                
                
                insert.setString(1, qty);
                insert.setString(2, product_id);
                insert.execute();
            }
            
            
            
            
            
            insert.addBatch();
            JOptionPane.showMessageDialog(this, "Record Saved");
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InventoryPOS.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
    }
    
    public void receipt()
    {
        String sub = txtsub.getText();
        String pay = txtpay.getText();
        String bal = txtbal.getText();
        
        
        
        try {
            new Receipt(sub,pay,bal,jTable1.getModel()).setVisible(true);
        } catch (PrinterException ex) {
            Logger.getLogger(InventoryPOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblbrand = new javax.swing.JLabel();
        Back = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        Back1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtproname = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtprocode = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtprice = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtbal = new javax.swing.JTextField();
        txtsub = new javax.swing.JTextField();
        txtpay = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();

        lblbrand.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        getContentPane().add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 780, 80, 40));

        jPanel1.setBackground(new java.awt.Color(204, 204, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), new java.awt.Color(153, 153, 153), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Product");
        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 53, 130, 40));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Category");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 53, 130, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("POS");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(526, 53, 130, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Brand");
        jLabel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel5MouseMoved(evt);
            }
        });
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 53, 130, 40));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(674, 32, 15, 61));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Cashier");
        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(694, 53, 130, 40));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 32, 15, 61));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 32, 15, 61));

        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 32, 15, 61));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 2, 150, 44));

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 15, 326, 10));

        jSeparator8.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 15, 326, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 110));

        Back1.setBackground(new java.awt.Color(255, 204, 0));
        Back1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Back1.setText("Exit");
        Back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Back1ActionPerformed(evt);
            }
        });
        getContentPane().add(Back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 780, 80, 40));

        jPanel2.setBackground(new java.awt.Color(204, 204, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "POS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtproname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtproname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpronameMouseClicked(evt);
            }
        });
        jPanel2.add(txtproname, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 210, 37));

        jButton1.setBackground(new java.awt.Color(255, 204, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 75, 40));

        jButton4.setBackground(new java.awt.Color(255, 204, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, -1, 40));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Product Name");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, 37));

        txtprocode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtprocode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtprocodeMouseClicked(evt);
            }
        });
        txtprocode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprocodeKeyPressed(evt);
            }
        });
        jPanel2.add(txtprocode, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 200, 37));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Quantity");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, -1, 37));

        txtprice.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtprice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpriceMouseClicked(evt);
            }
        });
        jPanel2.add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 106, 37));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Price");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 41, 37));

        txtqty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtqty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtqtyMouseClicked(evt);
            }
        });
        jPanel2.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 106, 37));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Product Code");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 37));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(988, 73, 50, 10));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 840, 220));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Code", "Product Name", "Price", "Qty", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 820, 300));

        jButton2.setBackground(new java.awt.Color(255, 204, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 660, 75, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Balance");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 660, 70, 37));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Subtotal");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 660, 100, 37));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Pay");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 660, 40, 37));

        txtbal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtbal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbalMouseClicked(evt);
            }
        });
        getContentPane().add(txtbal, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 660, 106, 37));

        txtsub.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtsub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsubMouseClicked(evt);
            }
        });
        getContentPane().add(txtsub, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 660, 106, 37));

        txtpay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtpay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpayMouseClicked(evt);
            }
        });
        getContentPane().add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 660, 106, 37));

        jButton3.setBackground(new java.awt.Color(255, 204, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Pay");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 660, 75, 40));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 730, 320, 90));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 840, 10));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Today is:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 730, 100, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 730, 230, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 760, 300, 40));

        label2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 840, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        this.hide();
        Greet g = new Greet();
        g.setVisible(true);
    }//GEN-LAST:event_BackActionPerformed

    private void Back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Back1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Back1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        InventoryPOS();
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtpronameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpronameMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtpronameMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        InventoryCategory c = new InventoryCategory();
        this.hide();
        c.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        InventoryBrand b = new InventoryBrand();
        this.hide();
        b.setVisible(true); 
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        InventoryProduct p = new InventoryProduct();
        this.hide();
        p.setVisible(true); 
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtprocodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtprocodeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprocodeMouseClicked

    private void txtpriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpriceMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpriceMouseClicked

    private void txtqtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtqtyMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtqtyMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        txtprocode.setText(null);
        txtprice.setText(null);
        txtproname.setText(null);
        txtqty.setText(null);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        model.removeRow(jTable1.getSelectedRow());
        int sum = 0;
        for(int i = 0; i<jTable1.getRowCount(); i++)

        {
            sum = sum + Integer.parseInt(jTable1.getValueAt(i, 4).toString());
                        
        }
                    
                    txtsub.setText(Integer.toString(sum));
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtbalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbalMouseClicked

    private void txtsubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsubMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsubMouseClicked

    private void txtpayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpayMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpayMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        int pay = Integer.parseInt(txtpay.getText());
        int subtotal = Integer.parseInt(txtsub.getText());
        
        
        int bal = pay - subtotal;
        
        txtbal.setText(String.valueOf(bal));
        
        try {
            
            sales();
            receipt();
            
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPOS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtprocodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprocodeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER)
        {
            String name = txtprocode.getText();
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                 con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos","root","");
                 insert = con1.prepareStatement("select * from product where barcode=?");
                 insert.setString(1, name);
                 rs = insert.executeQuery();
                 
                 if(rs.next()==false)
                 {
                     JOptionPane.showMessageDialog(this, "Barcode Not Found");
                 }
                 else
                 {
                     String productname = rs.getString("product");
                     String price = rs.getString("retail_price");
                     
                     
                     txtproname.setText(productname.trim());
                     txtprice.setText(price.trim());
                     
                 }
                 
                 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(InventoryPOS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(InventoryPOS.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
    }//GEN-LAST:event_txtprocodeKeyPressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        InventoryPOS o = new InventoryPOS();
        this.hide();
        o.setVisible(true); 
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        InventoryCashier r = new InventoryCashier();
        this.hide();
        r.setVisible(true);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseMoved
    
    }//GEN-LAST:event_jLabel5MouseMoved

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
            java.util.logging.Logger.getLogger(InventoryPOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryPOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryPOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryPOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new InventoryPOS().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton Back1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel lblbrand;
    private javax.swing.JTextField txtbal;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txtprice;
    private javax.swing.JTextField txtprocode;
    private javax.swing.JTextField txtproname;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtsub;
    // End of variables declaration//GEN-END:variables
}
