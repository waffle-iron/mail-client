/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hieunq
 */
public class Client extends javax.swing.JFrame {

  //================
  String host = "localhost";
  String user = "diep@doan.net";
  String password = "diep";
  //=================
  String content;
  Object con;
  ArrayList<Mail> ds = new ArrayList();
  Properties getPro = new Properties();
  Properties sendPro = new Properties();
  Session sessionSend, sessionGet;
  Authenticator p = new Authenticator() {
    private PasswordAuthentication authentication;

    {
      authentication = new PasswordAuthentication(user, password);
    }

    protected PasswordAuthentication getPasswordAuthentication() {
      return authentication;
    }
  };

  /**
   * Creates new form Client
   */
  public Client() throws NoSuchProviderException, MessagingException, IOException {
    initComponents();
    model1 = (DefaultTableModel) jTable1.getModel();
    model1.getDataVector().removeAllElements();
    jPanel6.setVisible(true);
    jPanel7.setVisible(false);
    jPanel8.setVisible(false);
    jPanel9.setVisible(false);
    jPanel10.setVisible(false);

    // send mail
    sendPro = new Properties();
    sendPro.put("mail.transport.protocol", "smtp");
    sendPro.put("mail.smtp.host", host);
    sendPro.put("mail.smtp.port", "25");
    sendPro.put("mail.smtp.auth", "true");

    sessionSend = Session.getInstance(sendPro, p);
    // get mail
    getPro = new Properties();
    getPro.put("mail.transport.protocol", "pop3");
    getPro.put("mail.pop3.host", host);
    getPro.put("mail.pop3.port", "110");
    getPro.put("mail.pop3.auth", "true");

    sessionGet = Session.getInstance(getPro, p);

    checkMail();
  }

  private void checkMail() throws NoSuchProviderException, MessagingException, IOException {
    //check mail

    Store store = sessionGet.getStore("pop3");
    store.connect(host, user, password);
    Folder inbox = store.getFolder("Inbox");
    inbox.open(Folder.READ_ONLY);

    Folder[] f = store.getDefaultFolder().list();

    Message[] messages = inbox.getMessages();

    Mail e;
    Date d;
    for (int i = 0; i < messages.length; i++) {
      e = new Mail();
      e.setIndex(i + 1);
      e.setFrom(messages[i].getFrom()[0].toString());
      e.setSubject(messages[i].getSubject().toString());

      con = messages[i].getContent();
      String attachFiles = "";
      String saveDirectory = "";
      if (con instanceof String) {
        content = (String) messages[i].getContent();
      } else if (con instanceof Multipart) {
        Multipart multipart = (Multipart) messages[i].getContent();
          int numberOfParts = multipart.getCount();
          for (int partCount = 0; partCount < numberOfParts; partCount++) {
              MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(partCount);
              if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
                  // this part is attachment
                  String fileName = part.getFileName();
                  attachFiles += fileName + ", ";
                  part.saveFile(saveDirectory + File.separator + fileName);
              } else {
                  // this part may be the message content
                  content = part.getContent().toString();
              }
          }
          if (attachFiles.length() > 1) {
              attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
              
              // set attach file to mail object
              e.setAttachFile(multipart.getBodyPart(1).getContent().toString());
          }
      } else {
          Object contentObj = messages[i].getContent();
          if (content != null) {
              content = contentObj.toString();
          }
      }
      e.setContent(content);
      d = messages[i].getSentDate();
      if (d != null) {
        e.setSentTime(messages[i].getSentDate().toString());
      } else {
        e.setSentTime("");
      }
      ds.add(e);
      addTable(e);
    }
    inbox.close(true);
    store.close();
  }

  private void addTable(Mail e) {
    model1.addRow(new Object[]{e.getIndex(), e.getFrom(), e.getSubject(), e.getContent(), e.getSentTime()});
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
    jPanel3 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    jLabel7 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jPanel4 = new javax.swing.JPanel();
    jLabel5 = new javax.swing.JLabel();
    jPanel6 = new javax.swing.JPanel();
    jLabel10 = new javax.swing.JLabel();
    jLabel15 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    jLabel17 = new javax.swing.JLabel();
    jPanel7 = new javax.swing.JPanel();
    jLabel11 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTable1 = new javax.swing.JTable();
    jLabel18 = new javax.swing.JLabel();
    jLabel19 = new javax.swing.JLabel();
    jLabel20 = new javax.swing.JLabel();
    jLabel21 = new javax.swing.JLabel();
    jLabel22 = new javax.swing.JLabel();
    jLabel23 = new javax.swing.JLabel();
    jLabel25 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    jScrollPane4 = new javax.swing.JScrollPane();
    jTextAreaKeyContentReceiver = new javax.swing.JTextArea();
    jLabel35 = new javax.swing.JLabel();
    jPanel8 = new javax.swing.JPanel();
    jLabel12 = new javax.swing.JLabel();
    jScrollPane3 = new javax.swing.JScrollPane();
    jTextArea2 = new javax.swing.JTextArea();
    jLabel24 = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    jLabel28 = new javax.swing.JLabel();
    jLabel29 = new javax.swing.JLabel();
    jTextField3 = new javax.swing.JTextField();
    jTextField4 = new javax.swing.JTextField();
    jSeparator2 = new javax.swing.JSeparator();
    jLabel26 = new javax.swing.JLabel();
    jLabel27 = new javax.swing.JLabel();
    jLabel30 = new javax.swing.JLabel();
    jLabel31 = new javax.swing.JLabel();
    jLabel32 = new javax.swing.JLabel();
    jLabel33 = new javax.swing.JLabel();
    jLabel34 = new javax.swing.JLabel();
    jProgressBar1 = new javax.swing.JProgressBar();
    jScrollPane5 = new javax.swing.JScrollPane();
    jTextAreaKeyContentSender = new javax.swing.JTextArea();
    jLabel36 = new javax.swing.JLabel();
    jPanel9 = new javax.swing.JPanel();
    jLabel13 = new javax.swing.JLabel();
    jPanel10 = new javax.swing.JPanel();
    jLabel14 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setLocation(new java.awt.Point(200, 100));
    setUndecorated(true);
    setResizable(false);
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanel1.setBackground(new java.awt.Color(34, 49, 63));

    jPanel3.setBackground(new java.awt.Color(248, 148, 6));
    jPanel3.setForeground(new java.awt.Color(255, 255, 255));

    jLabel1.setFont(new java.awt.Font("MS Reference Sans Serif", 0, 18)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("E-MAIL CLIENT SIDE");
    jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_User_20px.png"))); // NOI18N
    jLabel2.setText("Tài khoản");
    jLabel2.setAlignmentX(0.5F);
    jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        jLabel2MouseEntered(evt);
      }
      public void mouseExited(java.awt.event.MouseEvent evt) {
        jLabel2MouseExited(evt);
      }
      public void mousePressed(java.awt.event.MouseEvent evt) {
        jLabel2MousePressed(evt);
      }
    });

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Inbox_20px.png"))); // NOI18N
    jLabel3.setText("Hộp thư đến");
    jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        jLabel3MouseEntered(evt);
      }
      public void mouseExited(java.awt.event.MouseEvent evt) {
        jLabel3MouseExited(evt);
      }
      public void mousePressed(java.awt.event.MouseEvent evt) {
        jLabel3MousePressed(evt);
      }
    });

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(255, 255, 255));
    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Spam_20px.png"))); // NOI18N
    jLabel4.setText("Spam");
    jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        jLabel4MouseEntered(evt);
      }
      public void mouseExited(java.awt.event.MouseEvent evt) {
        jLabel4MouseExited(evt);
      }
      public void mousePressed(java.awt.event.MouseEvent evt) {
        jLabel4MousePressed(evt);
      }
    });

    jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(255, 255, 255));
    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Envelope_20px.png"))); // NOI18N
    jLabel6.setText("Soạn email");
    jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        jLabel6MouseEntered(evt);
      }
      public void mouseExited(java.awt.event.MouseEvent evt) {
        jLabel6MouseExited(evt);
      }
      public void mousePressed(java.awt.event.MouseEvent evt) {
        jLabel6MousePressed(evt);
      }
    });

    jPanel5.setBackground(new java.awt.Color(44, 62, 80));

    jLabel7.setBackground(new java.awt.Color(25, 181, 254));
    jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel7.setForeground(new java.awt.Color(25, 181, 254));
    jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel7.setText("© hieunquy 2017");

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 9, Short.MAX_VALUE))
    );

    jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel9.setForeground(new java.awt.Color(255, 255, 255));
    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Services_20px.png"))); // NOI18N
    jLabel9.setText("Cài đặt");
    jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(java.awt.event.MouseEvent evt) {
        jLabel9MouseEntered(evt);
      }
      public void mouseExited(java.awt.event.MouseEvent evt) {
        jLabel9MouseExited(evt);
      }
      public void mousePressed(java.awt.event.MouseEvent evt) {
        jLabel9MousePressed(evt);
      }
    });

    jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Sent_100px_1.png"))); // NOI18N

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel8)
          .addComponent(jLabel2)
          .addComponent(jLabel4)
          .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(53, 53, 53))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(35, 35, 35)
        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(44, 44, 44)
        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 540));

    jPanel2.setBackground(new java.awt.Color(52, 73, 94));
    jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jPanel4.setBackground(new java.awt.Color(207, 0, 15));

    jLabel5.setBackground(new java.awt.Color(207, 0, 15));
    jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(255, 255, 255));
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel5.setText("X");
    jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        jLabel5MousePressed(evt);
      }
    });

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
        .addGap(0, 0, Short.MAX_VALUE)
        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
        .addGap(0, 0, Short.MAX_VALUE)
        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
    );

    jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, -1, -1));

    jPanel6.setBackground(new java.awt.Color(52, 73, 94));
    jPanel6.setEnabled(false);

    jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel10.setForeground(new java.awt.Color(255, 255, 255));
    jLabel10.setText("Tài khoản");

    jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Male_User_96px.png"))); // NOI18N

    jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel16.setForeground(new java.awt.Color(255, 255, 255));
    jLabel16.setText("Email:");

    jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel17.setForeground(new java.awt.Color(255, 255, 255));
    jLabel17.setText(user);

    javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
      jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel6Layout.createSequentialGroup()
        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(277, 277, 277)
            .addComponent(jLabel10))
          .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(258, 258, 258)
            .addComponent(jLabel15))
          .addGroup(jPanel6Layout.createSequentialGroup()
            .addGap(220, 220, 220)
            .addComponent(jLabel16)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(211, Short.MAX_VALUE))
    );
    jPanel6Layout.setVerticalGroup(
      jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel6Layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addComponent(jLabel10)
        .addGap(18, 18, 18)
        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(42, 42, 42)
        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel16)
          .addComponent(jLabel17))
        .addContainerGap(261, Short.MAX_VALUE))
    );

    jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 710, 500));

    jPanel7.setBackground(new java.awt.Color(52, 73, 94));

    jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel11.setForeground(new java.awt.Color(255, 255, 255));
    jLabel11.setText("Hộp thư đến");

    jTable1.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null},
        {null, null, null, null, null}
      },
      new String [] {
        "Số thứ tự", "Người gửi", "Chủ đề", "Nội dung", "Thời gian"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
    jTable1.setRowHeight(20);
    jTable1.setSelectionBackground(new java.awt.Color(248, 148, 6));
    jTable1.getTableHeader().setReorderingAllowed(false);
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        jTable1MouseClicked(evt);
      }
      public void mousePressed(java.awt.event.MouseEvent evt) {
        jTable1MousePressed(evt);
      }
    });
    jScrollPane1.setViewportView(jTable1);
    jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    if (jTable1.getColumnModel().getColumnCount() > 0) {
      jTable1.getColumnModel().getColumn(0).setResizable(false);
      jTable1.getColumnModel().getColumn(1).setResizable(false);
      jTable1.getColumnModel().getColumn(2).setResizable(false);
      jTable1.getColumnModel().getColumn(3).setResizable(false);
      jTable1.getColumnModel().getColumn(4).setResizable(false);
    }

    jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel18.setForeground(new java.awt.Color(255, 255, 255));
    jLabel18.setText("Người gửi:");

    jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel19.setForeground(new java.awt.Color(255, 255, 255));
    jLabel19.setText("Chủ đề:");

    jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel20.setForeground(new java.awt.Color(255, 255, 255));
    jLabel20.setText("Nội dung: ");

    jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel21.setForeground(new java.awt.Color(255, 255, 255));
    jLabel21.setText("Thời gian:");

    jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel22.setForeground(new java.awt.Color(25, 181, 254));

    jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel23.setForeground(new java.awt.Color(25, 181, 254));

    jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel25.setForeground(new java.awt.Color(25, 181, 254));

    jScrollPane2.setBorder(null);
    jScrollPane2.setAutoscrolls(true);

    jTextArea1.setEditable(false);
    jTextArea1.setBackground(new java.awt.Color(52, 73, 94));
    jTextArea1.setColumns(20);
    jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
    jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
    jTextArea1.setRows(5);
    jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    jScrollPane2.setViewportView(jTextArea1);

    jScrollPane4.setBorder(null);
    jScrollPane4.setAutoscrolls(true);

    jTextAreaKeyContentReceiver.setEditable(false);
    jTextAreaKeyContentReceiver.setBackground(new java.awt.Color(52, 73, 94));
    jTextAreaKeyContentReceiver.setColumns(20);
    jTextAreaKeyContentReceiver.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
    jTextAreaKeyContentReceiver.setForeground(new java.awt.Color(255, 255, 255));
    jTextAreaKeyContentReceiver.setRows(5);
    jTextAreaKeyContentReceiver.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    jScrollPane4.setViewportView(jTextAreaKeyContentReceiver);

    jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel35.setForeground(new java.awt.Color(255, 255, 255));
    jLabel35.setText("Key content:");

    javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
    jPanel7.setLayout(jPanel7Layout);
    jPanel7Layout.setHorizontalGroup(
      jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel7Layout.createSequentialGroup()
        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(277, 277, 277)
            .addComponent(jLabel11))
          .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel20)
              .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane4))
          .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(jPanel7Layout.createSequentialGroup()
            .addGap(22, 22, 22)
            .addComponent(jLabel18)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel21)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel35)
                .addGap(0, 0, Short.MAX_VALUE))
              .addComponent(jScrollPane2))))
        .addGap(19, 19, 19))
    );
    jPanel7Layout.setVerticalGroup(
      jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel7Layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addComponent(jLabel11)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel18)
          .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(jLabel21)
            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jLabel19)
          .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel20)
        .addGap(18, 18, 18)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel35)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(16, Short.MAX_VALUE))
    );

    jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 710, 500));

    jPanel8.setBackground(new java.awt.Color(52, 73, 94));
    jPanel8.setEnabled(false);

    jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel12.setForeground(new java.awt.Color(255, 255, 255));
    jLabel12.setText("Soạn email");

    jScrollPane3.setBorder(null);
    jScrollPane3.setAutoscrolls(true);

    jTextArea2.setBackground(new java.awt.Color(52, 73, 94));
    jTextArea2.setColumns(20);
    jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
    jTextArea2.setForeground(new java.awt.Color(255, 255, 255));
    jTextArea2.setRows(5);
    jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    jTextArea2.setCaretColor(new java.awt.Color(248, 148, 6));
    jTextArea2.setDisabledTextColor(new java.awt.Color(52, 73, 94));
    jTextArea2.setSelectionColor(new java.awt.Color(52, 73, 94));
    jScrollPane3.setViewportView(jTextArea2);

    jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel24.setForeground(new java.awt.Color(255, 255, 255));
    jLabel24.setText("Người nhận:");

    jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel28.setForeground(new java.awt.Color(255, 255, 255));
    jLabel28.setText("Chủ đề:");

    jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel29.setForeground(new java.awt.Color(255, 255, 255));
    jLabel29.setText("Nội dung:");

    jTextField3.setBackground(new java.awt.Color(52, 73, 94));
    jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTextField3.setForeground(new java.awt.Color(255, 255, 255));
    jTextField3.setBorder(null);

    jTextField4.setBackground(new java.awt.Color(52, 73, 94));
    jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jTextField4.setForeground(new java.awt.Color(255, 255, 255));
    jTextField4.setBorder(null);

    jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Remove_50px.png"))); // NOI18N

    jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Send_64px.png"))); // NOI18N
    jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        jLabel27MousePressed(evt);
      }
    });

    jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8_Save_50px.png"))); // NOI18N

    jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel31.setForeground(new java.awt.Color(255, 255, 255));
    jLabel31.setText("Xoá");

    jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel32.setForeground(new java.awt.Color(255, 255, 255));
    jLabel32.setText("Lưu");

    jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel33.setForeground(new java.awt.Color(255, 255, 255));
    jLabel33.setText("Gửi");

    jLabel34.setBackground(new java.awt.Color(25, 181, 254));
    jLabel34.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
    jLabel34.setForeground(new java.awt.Color(25, 181, 254));
    jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

    jScrollPane5.setBorder(null);
    jScrollPane5.setAutoscrolls(true);

    jTextAreaKeyContentSender.setBackground(new java.awt.Color(52, 73, 94));
    jTextAreaKeyContentSender.setColumns(20);
    jTextAreaKeyContentSender.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
    jTextAreaKeyContentSender.setForeground(new java.awt.Color(255, 255, 255));
    jTextAreaKeyContentSender.setRows(5);
    jTextAreaKeyContentSender.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
    jTextAreaKeyContentSender.setCaretColor(new java.awt.Color(248, 148, 6));
    jTextAreaKeyContentSender.setDisabledTextColor(new java.awt.Color(52, 73, 94));
    jTextAreaKeyContentSender.setSelectionColor(new java.awt.Color(52, 73, 94));
    jScrollPane5.setViewportView(jTextAreaKeyContentSender);

    jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel36.setForeground(new java.awt.Color(255, 255, 255));
    jLabel36.setText("Key content:");

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
      jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel8Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel8Layout.createSequentialGroup()
            .addGap(17, 17, 17)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                  .addComponent(jLabel29)
                  .addGap(26, 26, 26))
                .addGroup(jPanel8Layout.createSequentialGroup()
                  .addComponent(jLabel28)
                  .addGap(37, 37, 37))))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(jPanel8Layout.createSequentialGroup()
            .addComponent(jScrollPane3)
            .addContainerGap())
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addGap(38, 38, 38))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel30)
              .addComponent(jLabel32))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel33))
              .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel27)))
            .addGap(29, 29, 29))
          .addGroup(jPanel8Layout.createSequentialGroup()
            .addComponent(jScrollPane5)
            .addContainerGap())))
      .addGroup(jPanel8Layout.createSequentialGroup()
        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel8Layout.createSequentialGroup()
            .addGap(277, 277, 277)
            .addComponent(jLabel12))
          .addGroup(jPanel8Layout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addComponent(jLabel36)))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel8Layout.setVerticalGroup(
      jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel8Layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addComponent(jLabel12)
        .addGap(18, 40, Short.MAX_VALUE)
        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel24))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel28))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(21, 21, 21)
        .addComponent(jLabel29)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(35, 35, 35)
        .addComponent(jLabel36)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addGroup(jPanel8Layout.createSequentialGroup()
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel31)
              .addComponent(jLabel32)
              .addComponent(jLabel33)))
          .addGroup(jPanel8Layout.createSequentialGroup()
            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );

    jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 710, 500));

    jPanel9.setBackground(new java.awt.Color(52, 73, 94));
    jPanel9.setEnabled(false);

    jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel13.setForeground(new java.awt.Color(255, 255, 255));
    jLabel13.setText("Spam");

    javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
    jPanel9.setLayout(jPanel9Layout);
    jPanel9Layout.setHorizontalGroup(
      jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel9Layout.createSequentialGroup()
        .addGap(277, 277, 277)
        .addComponent(jLabel13)
        .addContainerGap(389, Short.MAX_VALUE))
    );
    jPanel9Layout.setVerticalGroup(
      jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel9Layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addComponent(jLabel13)
        .addContainerGap(454, Short.MAX_VALUE))
    );

    jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 710, 500));

    jPanel10.setBackground(new java.awt.Color(52, 73, 94));
    jPanel10.setEnabled(false);

    jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel14.setForeground(new java.awt.Color(255, 255, 255));
    jLabel14.setText("Cài đặt");

    javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
    jPanel10.setLayout(jPanel10Layout);
    jPanel10Layout.setHorizontalGroup(
      jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel10Layout.createSequentialGroup()
        .addGap(277, 277, 277)
        .addComponent(jLabel14)
        .addContainerGap(378, Short.MAX_VALUE))
    );
    jPanel10Layout.setVerticalGroup(
      jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel10Layout.createSequentialGroup()
        .addGap(24, 24, 24)
        .addComponent(jLabel14)
        .addContainerGap(454, Short.MAX_VALUE))
    );

    jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 710, 500));

    getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 0, 730, 540));

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered

      changColorLabel(jLabel2);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
      // TODO add your handling code here:

      changColorLabel(jLabel3);
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
      // TODO add your handling code here:

      changColorLabel(jLabel4);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
      // TODO add your handling code here:

      changColorLabel(jLabel6);
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
      // TODO add your handling code here:
      resetColorLabel();
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
      // TODO add your handling code here:
      resetColorLabel();
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
      // TODO add your handling code here:
      resetColorLabel();
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
      // TODO add your handling code here:
      resetColorLabel();
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
      changColorLabel(jLabel9);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
      resetColorLabel();
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
      System.exit(0);
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
      hideAll();
      jPanel6.setVisible(true);
      choose = jLabel2;
      resetColorLabel();
      choose.setForeground(new Color(248, 148, 6));
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
      hideAll();
      jPanel7.setVisible(true);
      choose = jLabel3;
      resetColorLabel();
      choose.setForeground(new Color(248, 148, 6));
    }//GEN-LAST:event_jLabel3MousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
      // TODO add your handling code here:
      hideAll();
      jPanel8.setVisible(true);
      choose = jLabel6;
      resetColorLabel();
      choose.setForeground(new Color(248, 148, 6));
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
      // TODO add your handling code here:
      hideAll();
      jPanel9.setVisible(true);
      choose = jLabel4;
      resetColorLabel();
      choose.setForeground(new Color(248, 148, 6));
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
      // TODO add your handling code here:
      hideAll();
      jPanel10.setVisible(true);
      choose = jLabel9;
      resetColorLabel();
      choose.setForeground(new Color(248, 148, 6));
    }//GEN-LAST:event_jLabel9MousePressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
      int i = Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
      i--;
      jLabel22.setText(ds.get(i).getFrom());
      jLabel23.setText(ds.get(i).getSubject());
      jTextArea1.setText(ds.get(i).getContent());
      jTextAreaKeyContentReceiver.setText(ds.get(i).getAttachFile());
      jLabel25.setText(ds.get(i).getSentTime());
      
    }//GEN-LAST:event_jTable1MousePressed

    private void jLabel27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MousePressed
      String st = "";
      if (jTextField3.getText().equals("")) {
        st += "Người gửi <trống>\n";
      }
      if (jTextField4.getText().equals("")) {
        st += "Subject <trống>\n";
      }
      if (jTextArea2.getText().equals("")) {
        st += "Content <trống>\n";
      }
      jProgressBar1.setValue(10);
      if (st.equals("")) {

        try {
          sendMail(jTextField3.getText(), jTextField4.getText(), jTextArea2.getText());
        } catch (MessagingException ex) {
          st = ex.toString();
          jLabel34.setText(st);
        }
        jProgressBar1.setValue(100);
        jLabel34.setText("Thư đã được gửi!");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextArea2.setText("");

      } else {
        jLabel34.setText(st);
      }

    }//GEN-LAST:event_jLabel27MousePressed

  private void changColorLabel(JLabel a) {
    a.setForeground(new Color(129, 207, 224));
  }

  private void sendMail(String to, String sub, String cont) throws AddressException, MessagingException {
    try {

          Message message = new MimeMessage(sessionSend);
         // Set From: header field of the header.
         message.setFrom(new InternetAddress(user));
         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject(sub);
         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();
         // Now set the actual message
         messageBodyPart.setText(pgp.pgp.encryptMessageByAES(cont));
         // Create a multipar message
         Multipart multipart = new MimeMultipart();
         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "keyFile.txt";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");
         } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void hideAll() {
    jPanel6.setVisible(false);
    jPanel7.setVisible(false);
    jPanel8.setVisible(false);
    jPanel9.setVisible(false);
    jPanel10.setVisible(false);
  }

  private void resetColorLabel() {
    jLabel2.setForeground(Color.WHITE);
    jLabel3.setForeground(Color.WHITE);
    jLabel4.setForeground(Color.WHITE);
    jLabel6.setForeground(Color.WHITE);
    jLabel9.setForeground(Color.WHITE);
    if (choose != null) {
      choose.setForeground(new Color(248, 148, 6));
    }
  }

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
      java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          new Client().setVisible(true);
        } catch (MessagingException ex) {
          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    });
  }
  DefaultTableModel model1;
  private javax.swing.JLabel choose = null;
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel17;
  private javax.swing.JLabel jLabel18;
  private javax.swing.JLabel jLabel19;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel20;
  private javax.swing.JLabel jLabel21;
  private javax.swing.JLabel jLabel22;
  private javax.swing.JLabel jLabel23;
  private javax.swing.JLabel jLabel24;
  private javax.swing.JLabel jLabel25;
  private javax.swing.JLabel jLabel26;
  private javax.swing.JLabel jLabel27;
  private javax.swing.JLabel jLabel28;
  private javax.swing.JLabel jLabel29;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel30;
  private javax.swing.JLabel jLabel31;
  private javax.swing.JLabel jLabel32;
  private javax.swing.JLabel jLabel33;
  private javax.swing.JLabel jLabel34;
  private javax.swing.JLabel jLabel35;
  private javax.swing.JLabel jLabel36;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel10;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel6;
  private javax.swing.JPanel jPanel7;
  private javax.swing.JPanel jPanel8;
  private javax.swing.JPanel jPanel9;
  private javax.swing.JProgressBar jProgressBar1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JScrollPane jScrollPane4;
  private javax.swing.JScrollPane jScrollPane5;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JTable jTable1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JTextArea jTextArea2;
  private javax.swing.JTextArea jTextAreaKeyContentReceiver;
  private javax.swing.JTextArea jTextAreaKeyContentSender;
  private javax.swing.JTextField jTextField3;
  private javax.swing.JTextField jTextField4;
  // End of variables declaration//GEN-END:variables

}
