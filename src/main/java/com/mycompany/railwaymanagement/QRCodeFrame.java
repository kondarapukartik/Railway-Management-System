/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.railwaymanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeFrame extends JFrame {

    private static final int QR_CODE_SIZE = 300;
    private static final String SAVE_DIRECTORY = "C:\\Users\\konda\\Desktop\\QRDemo\\";
    private static final String SAVE_FILE_NAME = "Quote.png";

    public QRCodeFrame() {
        setTitle("QR Code Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Generate the QR code image
        BufferedImage qrCodeImage = generateQRCodeImage("PAYMENT SUCCESSFULL");

        // Save the QR code image
        saveQRCodeImage(qrCodeImage);

        // Create a JLabel to display the QR code image
        JLabel qrCodeLabel = new JLabel(new ImageIcon(qrCodeImage));
        getContentPane().add(qrCodeLabel);

        pack();
    }

    private BufferedImage generateQRCodeImage(String text) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
            BufferedImage qrCodeImage = new BufferedImage(QR_CODE_SIZE, QR_CODE_SIZE, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < QR_CODE_SIZE; x++) {
                for (int y = 0; y < QR_CODE_SIZE; y++) {
                    qrCodeImage.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }
            return qrCodeImage;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void saveQRCodeImage(BufferedImage image) {
        try {
            File directory = new File(SAVE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File outputFile = new File(SAVE_DIRECTORY + SAVE_FILE_NAME);
            javax.imageio.ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QRCodeFrame frame = new QRCodeFrame();
            frame.setVisible(true);
        });
    }
}

/**
 *
 * @author konda
 */
