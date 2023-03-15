
package gneradorqr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

/**
 *
 * @author JassLS
 */
public class QR extends javax.swing.JFrame {


    public QR() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
   
    private void initComponents() {

        txtCode = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        lblQr = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 204));

        txtCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeActionPerformed(evt);
            }
        });

        jButton2.setText("Generar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 102, 204));
        jLabel1.setFont(new java.awt.Font("Sylfaen", 0, 24)); 
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ingresa tu codigo de rastreo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(lblQr, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton2)
                .addGap(38, 38, 38)
                .addComponent(lblQr, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        int size = 1000;
        String FileType = "png";

        String codigo = txtCode.getText().trim();
        // Elegir la ruta de la imagen
        String filePath = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            filePath = chooser.getSelectedFile().getAbsolutePath();
        }

        // Generar el nombre
        UUID uuid = UUID.randomUUID();
        String randonName = uuid.toString();

        // Generar el QR 
        QRCodeWriter qrcode = new QRCodeWriter();
        try {
            BitMatrix matrix = qrcode.encode(codigo, BarcodeFormat.QR_CODE, size, size);
            File f = new File(filePath + "/" + randonName + "." + FileType);
            int matrixWidth = matrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D gd = (Graphics2D) image.getGraphics();
            gd.setColor(Color.WHITE); // Fondo
            gd.fillRect(0, 0, size, size);
            gd.setColor(Color.black); // Qr

            for (int b = 0; b < matrixWidth; b++) {
                for (int j = 0; j < matrixWidth; j++) {
                    if (matrix.get(b, j)) {
                        gd.fillRect(b, j, 1, 1);
                    }
                }
            }
            
            // Mostrar la imagen generada
            ImageIO.write(image, FileType, f);
            Image mImagen = new ImageIcon(filePath + "/" + randonName + "." + FileType).getImage();
            ImageIcon mIcono = new ImageIcon(mImagen.getScaledInstance(lblQr.getWidth(), lblQr.getHeight(), 0));
            lblQr.setIcon(mIcono);
            
        } catch (WriterException ex) {
            Logger.getLogger(QR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        

        //crear y mostrar
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QR().setVisible(true);
            }
        });
    }

    // Declaracion de variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblQr;
    private javax.swing.JTextField txtCode;
    // fin
}
