/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import AppPackage.AnimationClass;
import com.bulenkov.darcula.DarculaLaf;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.jdesktop.xswingx.PromptSupport;

/**
 * A class that shows basic implementation of
 * threads in Java.
 * @author Kevin
 */
public class Threads extends javax.swing.JFrame {
        /**A class for animations ie basic left right and diagonal movement of some objects. 
         * Note: This class is not inbuilt into the java system but an imported jar file included
         * in the libraries.
        */
        AnimationClass ac = new AnimationClass();
        
        /**
         * a variable to enable looping out of endless loops when stop all button is 
         * clicked
         */
        private boolean[] running = {true, true, true, true};

        /**
         * The first thread.
         * Takes a JLabel and moves it left to right changing images
         * after every move.
         */
        MyRunnable first = new MyRunnable() {
                @Override
                public void run() {
                        isAlive = true;
                        int count = 0;
                        lblImages.setIcon(getResizedImage("/Resources/1575882.jpg", lblImages));
                        while (running[0]) {
                                try {
                                        count++;
                                        if (count == 1) {
                                                Thread.sleep(2000);
                                        }
                                        ac.jLabelXLeft(6, -194, 10, 1, lblImages);
                                        Thread.sleep(3700);

                                        lblImages.setIcon(getResizedImage("/Resources/image.jpeg", lblImages));
                                        ac.jLabelXRight(-194, 6, 10, 1, lblImages);
                                        Thread.sleep(6000);
                                        ac.jLabelXLeft(6, -194, 10, 1, lblImages);
                                        Thread.sleep(3700);

                                        lblImages.setIcon(getResizedImage("/Resources/image2.jpg", lblImages));
                                        ac.jLabelXRight(-194, 6, 10, 1, lblImages);
                                        Thread.sleep(6000);
                                        ac.jLabelXLeft(6, -194, 10, 1, lblImages);
                                        Thread.sleep(3700);

                                        lblImages.setIcon(getResizedImage("/Resources/shutter.jpg", lblImages));
                                        ac.jLabelXRight(-194, 6, 10, 1, lblImages);
                                        Thread.sleep(6000);
                                        ac.jLabelXLeft(6, -194, 10, 1, lblImages);
                                        Thread.sleep(3700);

                                        lblImages.setIcon(getResizedImage("/Resources/1575882.jpg", lblImages));
                                        ac.jLabelXRight(-194, 6, 10, 1, lblImages);
                                        Thread.sleep(6000);
                                } catch (Exception e) {
                                }
                        }
                        lblImages.setIcon(new ImageIcon());
                        setEnabled(btn1, true);
                        isAlive = false;
                }
        };

        /**
         * The second thread.
         * Generates random numbers after every one second and displays
         * to the user.
         */
        MyRunnable second = new MyRunnable() {
                @Override
                public void run() {
                        isAlive = true;
                        while (running[1]) {
                                try {
                                        Random temp = new Random();
                                        lblRandom.setText(String.valueOf(temp.nextInt(1000)));
                                        Thread.sleep(1000);
                                } catch (Exception e) {
                                }
                        }
                        lblRandom.setText("");
                        setEnabled(btn2, true);
                        isAlive = false;
                }
        };

        /**
         * The third thread.
         * Moves a JLabel up and down over a period of time.
         */
        MyRunnable third = new MyRunnable() {
                @Override
                public void run() {
                        isAlive = true;
                        lblSlide.setIcon(getResizedImage("/Resources/1575904.jpg", lblSlide));
                        while (running[2]) {
                                try {
                                        switch (lblSlide.getLocation().y) {
                                                case (-172):
                                                        Thread.sleep(100);
                                                        ac.jLabelYDown(-172,460,5,1,lblSlide);
                                                        Thread.sleep(3500);
                                                        break;
                                                case (460):
                                                        Thread.sleep(100);
                                                        lblSlide.setLocation(lblSlide.getLocation().x,-172);
                                                        break;
                                        }
                                } catch (Exception e) {
                                }
                        }
                        lblSlide.setIcon(new ImageIcon());
                        setEnabled(btn3, true);
                        isAlive = false;
                }
        };

        /**
         * The fourth thread.
         * Shows the current time with an accuracy of the millisecond.
         * One could also use the System.currentTimeMillis() to get
         * current time in milliseconds but due to the variations in 
         * number of days in a month its better and easier to use 
         * the calendar class as shown below.
         */
        MyRunnable fourth = new MyRunnable() {
                @Override
                public void run() {
                        isAlive = true;
                        while (running[3]) {
                                String time = "";
                                time += Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + " : ";
                                time += Calendar.getInstance().get(Calendar.MINUTE) + " : ";
                                time += Calendar.getInstance().get(Calendar.SECOND) + " : ";
                                time += Calendar.getInstance().get(Calendar.MILLISECOND);
                                lblTime.setText(time);
                        }
                        lblTime.setText("");
                        setEnabled(btn4, true);
                        isAlive = false;
                }
        };

        /**
         * Creates new form Threads
         */
        public Threads() {
                initComponents();
                setResizable(false);
                setSize(680, 460);
                setLocationRelativeTo(null);
                PromptSupport.setPrompt("Input Here", txtInput);
                PromptSupport.setForeground(Color.GRAY, txtInput);
                PromptSupport.setFontStyle(4, txtInput);
                PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtInput);
                PromptSupport.setPrompt("Output Here", txtOutput);
                PromptSupport.setForeground(Color.GRAY, txtOutput);
                PromptSupport.setFontStyle(4, txtOutput);
                PromptSupport.setFocusBehavior(PromptSupport.FocusBehavior.SHOW_PROMPT, txtOutput);
        }

        /**
         * This method gets an image and a JLabel and
         * resizes the image to the size of the JLabel.
         * It can be used with almost any component. Just
         * change JLabel with the component where you want
         * your image to appear.
         * <b>Note: </b>This method does not place the image in the 
         * Component but just resizes the image to the size of the 
         * component and returns the image. To place the image in the
         * component, use the method setIcon() for JLabels.
         * @param url : The URL as a string containing the path to the resource.
         * @param jlabel : The JLabel where the image is to be placed. 
         * @return An IconImage that has been resized to the size of the JLabel.
         */
        private ImageIcon getResizedImage(String url, JLabel jlabel) {
                ImageIcon temp = new ImageIcon(new ImageIcon(getClass().getResource(url)).getImage().getScaledInstance(jlabel.getWidth(), jlabel.getHeight(), Image.SCALE_SMOOTH));
                return temp;
        }

        /**
         * This method is used by all threads to set the enabled state of their 
         * respective buttons to either true or false. This helps prevent clashing 
         * of threads or Thread leaking caused by starting two threads with the 
         * same name before one is finished. The method is synchronised 
         * meaning only one thread can use it at a time to prevent concurrency
         * issues.
         * @param jbutton The JButton in question.
         * @param state The state of that JButton
         */
        private synchronized void setEnabled(JButton jbutton, boolean state) {
                jbutton.setEnabled(state);
                if (btn1.isEnabled() && btn2.isEnabled() && btn3.isEnabled() && btn4.isEnabled()) {
                        btn5.setEnabled(true);
                } else {
                        btn5.setEnabled(false);
                }
        }

        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                btn1 = new javax.swing.JButton();
                btn2 = new javax.swing.JButton();
                btn3 = new javax.swing.JButton();
                btn4 = new javax.swing.JButton();
                btn5 = new javax.swing.JButton();
                btn6 = new javax.swing.JButton();
                lblImages = new javax.swing.JLabel();
                lblRandom = new javax.swing.JLabel();
                lblSlide = new javax.swing.JLabel();
                txtInput = new javax.swing.JTextField();
                txtOutput = new javax.swing.JTextField();
                lblTime = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                getContentPane().setLayout(null);

                btn1.setText("Process 1");
                btn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btn1.setFocusable(false);
                btn1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn1ActionPerformed(evt);
                        }
                });
                getContentPane().add(btn1);
                btn1.setBounds(6, 6, 88, 32);

                btn2.setText("Process 2");
                btn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btn2.setFocusable(false);
                btn2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn2ActionPerformed(evt);
                        }
                });
                getContentPane().add(btn2);
                btn2.setBounds(112, 6, 88, 32);

                btn3.setText("Process 3");
                btn3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btn3.setFocusable(false);
                btn3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn3ActionPerformed(evt);
                        }
                });
                getContentPane().add(btn3);
                btn3.setBounds(218, 6, 88, 32);

                btn4.setText("Process 4");
                btn4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btn4.setFocusable(false);
                btn4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn4ActionPerformed(evt);
                        }
                });
                getContentPane().add(btn4);
                btn4.setBounds(324, 6, 88, 32);

                btn5.setText("Start All");
                btn5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btn5.setFocusable(false);
                btn5.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn5ActionPerformed(evt);
                        }
                });
                getContentPane().add(btn5);
                btn5.setBounds(439, 6, 87, 32);

                btn6.setText("Stop All");
                btn6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                btn6.setFocusable(false);
                btn6.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btn6ActionPerformed(evt);
                        }
                });
                getContentPane().add(btn6);
                btn6.setBounds(544, 6, 88, 32);
                getContentPane().add(lblImages);
                lblImages.setBounds(6, 56, 194, 172);

                lblRandom.setFont(new java.awt.Font("DialogInput", 3, 48)); // NOI18N
                lblRandom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                getContentPane().add(lblRandom);
                lblRandom.setBounds(6, 246, 194, 172);
                getContentPane().add(lblSlide);
                lblSlide.setBounds(218, -172, 194, 172);

                txtInput.setFont(new java.awt.Font("Courier New", 3, 18)); // NOI18N
                txtInput.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtInputKeyReleased(evt);
                        }
                });
                getContentPane().add(txtInput);
                txtInput.setBounds(430, 56, 210, 40);

                txtOutput.setEditable(false);
                txtOutput.setFont(new java.awt.Font("Courier New", 3, 18)); // NOI18N
                getContentPane().add(txtOutput);
                txtOutput.setBounds(430, 114, 210, 40);

                lblTime.setFont(new java.awt.Font("DigifaceWide", 3, 20)); // NOI18N
                getContentPane().add(lblTime);
                lblTime.setBounds(430, 246, 230, 50);

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
                btn1.doClick();
                btn2.doClick();
                btn3.doClick();
                btn4.doClick();
        }//GEN-LAST:event_btn5ActionPerformed

        private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
                running[0] = false;
                running[1] = false;
                running[2] = false;
                running[3] = false;
                if (first.isAlive()) {
                        btn1.setEnabled(false);
                }
                if (second.isAlive()) {
                        btn2.setEnabled(false);
                }
                if (third.isAlive()) {
                        btn3.setEnabled(false);
                }
                if (fourth.isAlive()) {
                        btn4.setEnabled(false);
                }
        }//GEN-LAST:event_btn6ActionPerformed

        /**
         * This method is used with the JTextfield to reverse a string and display
         * the output in real time. This is just to show that execution of all other
         * threads will not interfere with the execution of other processes such 
         * as this one. Try running all threads at the same time by clicking the 
         * start all button then type in the JTextfield to see that output is still
         * delivered in real time even though the other processes are continuing.
         * @param evt a keyReleased event which is fired each time a key is
         * released on the keyboard.
         */
        private void txtInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInputKeyReleased
                String reversed = "";
                for (int a = 0; a < txtInput.getText().trim().length(); a++) {
                        char temps = txtInput.getText().trim().charAt(a);
                        reversed = String.join("", String.valueOf(temps), reversed);
                }
                txtOutput.setText(reversed);
                txtOutput.setCaretPosition(0);
        }//GEN-LAST:event_txtInputKeyReleased

        private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
                if (!first.isAlive()) {
                        running[0] = true;
                        Thread temp = new Thread(first);
                        temp.start();
                }
        }//GEN-LAST:event_btn1ActionPerformed

        private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
                if (!second.isAlive()) {
                        running[1] = true;
                        Thread temp = new Thread(second);
                        temp.start();
                }
        }//GEN-LAST:event_btn2ActionPerformed

        private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
                if (!third.isAlive()) {
                        running[2] = true;
                        Thread temp = new Thread(third);
                        temp.start();
                }
        }//GEN-LAST:event_btn3ActionPerformed

        private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
                if (!fourth.isAlive()) {
                        running[3] = true;
                        Thread temp = new Thread(fourth);
                        temp.start();
                }
        }//GEN-LAST:event_btn4ActionPerformed

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
                /* Set the Darcula look and feel */
                //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
                /** I have used the Darcula jar in the lib folder to access the Darcula look and feel which
                 * is a dark themed look and feel. To use the Darcula Look and feel, ensure the Darcula
                 * Look and feel is in the libraries of this project.
                 */
                try {
                        UIManager.setLookAndFeel(new DarculaLaf());
                } catch (UnsupportedLookAndFeelException ex) {
                }
                //</editor-fold>

                /* Create and display the form */
                java.awt.EventQueue.invokeLater(() -> {
                        new Threads().setVisible(true);
                });
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btn1;
        private javax.swing.JButton btn2;
        private javax.swing.JButton btn3;
        private javax.swing.JButton btn4;
        private javax.swing.JButton btn5;
        private javax.swing.JButton btn6;
        private javax.swing.JLabel lblImages;
        private javax.swing.JLabel lblRandom;
        private javax.swing.JLabel lblSlide;
        private javax.swing.JLabel lblTime;
        private javax.swing.JTextField txtInput;
        private javax.swing.JTextField txtOutput;
        // End of variables declaration//GEN-END:variables
}
