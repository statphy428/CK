/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeplerLaws;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author minakim
 */
public class KeplerLaws extends javax.swing.JApplet {
    int semimajorAxis;
    double eccentricity,semiminorAxis;
    PlanetaryMotion keplerSystem;
    javax.swing.Timer timer;
    /**
     * Initializes the applet KeplerLaws
     */
    @Override
    public void init() {
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
            java.util.logging.Logger.getLogger(KeplerLaws.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KeplerLaws.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KeplerLaws.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KeplerLaws.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        semimajorAxis = 100;
        eccentricity = 0.7;
        semiminorAxis = semimajorAxis*(Math.sqrt(1-Math.pow(eccentricity, 2)));
        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                    timer = new javax.swing.Timer(1,new aListener());
                    keplerSystem = new PlanetaryMotion(semimajorAxis,semiminorAxis,eccentricity);
                    timer.start();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public class aListener implements ActionListener 
    {
            public void actionPerformed(ActionEvent e) {
                
                visualizingPanel.repaint();
                //firstLawR1TextField.setText(""+keplerSystem.positionX);
            }
    };
    
    public class displayPanel extends javax.swing.JPanel{                
        displayPanel(){
            super();
        }
        public void paintComponent(Graphics g)
        {  
         
            super.paintComponent(g);
            Graphics2D orbit = (Graphics2D)g;
            Color refColor = new Color(255,255,255);
            orbit.setColor(refColor);
            Ellipse2D.Double PlanetOrbit;
            PlanetOrbit = new Ellipse2D.Double(visualizingPanel.getWidth()/2-(semimajorAxis*(1+eccentricity)),visualizingPanel.getHeight()/2-semiminorAxis,2*semimajorAxis,2*semiminorAxis);
            orbit.draw(PlanetOrbit);
            
            Graphics2D sun = (Graphics2D)g;
            int sunRadius = 15;
            float[] dist={0.0f,0.8f};
            Color sunColor = new Color(255,230,103,0);
            Color[] sunColors={Color.yellow,sunColor};
            Point2D sunCenter;            
            RadialGradientPaint sunP; 
            sunColor = new Color(255,230,103,255);
            sunColors[0]=sunColor;
            //sunCenter = new Point2D.Float((float)400,(float)150);
            sunCenter = new Point2D.Float((float)(visualizingPanel.getWidth()/2),(float)(visualizingPanel.getHeight()/2));
            sunP = new RadialGradientPaint(sunCenter,1.0f * sunRadius,dist,sunColors);            
            sun.setPaint(sunP);
            sun.fillOval(visualizingPanel.getWidth()/2-sunRadius,visualizingPanel.getHeight()/2-sunRadius,sunRadius*2,sunRadius*2);
                       
            if(focalPointCheckBox.isSelected()){
                Graphics2D focal = (Graphics2D)g;
                refColor = new Color(0,0,255);
                focal.setColor(refColor);
                Ellipse2D.Double focalPoint ;
                focalPoint = new Ellipse2D.Double(visualizingPanel.getWidth()/2-(2*semimajorAxis*eccentricity)-3,visualizingPanel.getHeight()/2-3,6,6);                          
                focal.draw(focalPoint);
                focal.fill(focalPoint); 
            }
            if(centerCheckBox.isSelected()){
                Graphics2D center = (Graphics2D)g;
                center.setColor(Color.white);
                Ellipse2D.Double OrbitCenter ;
                OrbitCenter = new Ellipse2D.Double(visualizingPanel.getWidth()/2-(semimajorAxis*eccentricity)-3,visualizingPanel.getHeight()/2-3,6,6);                          
                center.draw(OrbitCenter);
                center.fill(OrbitCenter);                
            }
            if(semiminorAxisCheckBox.isSelected()){
                Graphics2D semiMinorLine = (Graphics2D)g;
                semiMinorLine.setColor(Color.white);
                Line2D line1=new Line2D.Double(visualizingPanel.getWidth()/2-(semimajorAxis*eccentricity),visualizingPanel.getHeight()/2,visualizingPanel.getWidth()/2-(semimajorAxis*eccentricity),visualizingPanel.getHeight()/2-semiminorAxis);
                semiMinorLine.draw(line1);
                
            }
            if(semimajorAxisCheckBox.isSelected()){
                Graphics2D semiMajorLine = (Graphics2D)g;
                semiMajorLine.setColor(Color.white);
                Line2D line2=new Line2D.Double(visualizingPanel.getWidth()/2-(semimajorAxis*eccentricity),visualizingPanel.getHeight()/2,visualizingPanel.getWidth()/2+(semimajorAxis*(1-eccentricity)),visualizingPanel.getHeight()/2);
                semiMajorLine.draw(line2);
                
            }     
           keplerSystem.ModelDynamics(semimajorAxis,semiminorAxis,eccentricity);
            Graphics2D planet = (Graphics2D)g;
            planet.setColor(Color.yellow);
            Ellipse2D.Double particle ;
            particle = new Ellipse2D.Double(visualizingPanel.getWidth()/2+keplerSystem.positionX-3,visualizingPanel.getHeight()/2+keplerSystem.positionY-3,6,6);                          
            planet.draw(particle);
            planet.fill(particle);
                
        }
    }
    /**
     * This method is called from within the init() method to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        visualizingPanel = new displayPanel();
        controlParameterPanel = new javax.swing.JPanel();
        controlParameterLabel = new javax.swing.JLabel();
        semimajorAxisLabel = new javax.swing.JLabel();
        semimajorAxisTextField = new javax.swing.JTextField();
        semimajorAxisUnitLabel = new javax.swing.JLabel();
        semimajorAxisSlider = new javax.swing.JSlider();
        eccentricityLabel = new javax.swing.JLabel();
        eccentricityTextField = new javax.swing.JTextField();
        eccentricitySlider = new javax.swing.JSlider();
        jToggleButton1 = new javax.swing.JToggleButton();
        KeplersLawsPanel = new javax.swing.JTabbedPane();
        firstLaw = new javax.swing.JPanel();
        focalPointCheckBox = new javax.swing.JCheckBox();
        centerCheckBox = new javax.swing.JCheckBox();
        semiminorAxisCheckBox = new javax.swing.JCheckBox();
        semimajorAxisCheckBox = new javax.swing.JCheckBox();
        distanceFromFocalPointsCheckBox = new javax.swing.JCheckBox();
        firstLawexplanationLabel = new javax.swing.JLabel();
        firstLawEquationPane = new javax.swing.JLayeredPane();
        firstLawEquationLabel = new javax.swing.JLabel();
        firstLawLabel1 = new javax.swing.JLabel();
        firstLawLabel2 = new javax.swing.JLabel();
        firstLawR1TextField = new javax.swing.JTextField();
        firstLawR2TextField = new javax.swing.JTextField();
        firstLawSumOfR1AndR2TextField = new javax.swing.JTextField();
        secondLaw = new javax.swing.JPanel();
        thirdLaw = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        jInternalFrame1.setTitle("케플러의 법칙");
        jInternalFrame1.setMaximumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setName("Kepler's law"); // NOI18N
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setVisible(true);

        visualizingPanel.setBackground(new java.awt.Color(24, 13, 1));
        visualizingPanel.setPreferredSize(new java.awt.Dimension(800, 300));

        javax.swing.GroupLayout visualizingPanelLayout = new javax.swing.GroupLayout(visualizingPanel);
        visualizingPanel.setLayout(visualizingPanelLayout);
        visualizingPanelLayout.setHorizontalGroup(
            visualizingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        visualizingPanelLayout.setVerticalGroup(
            visualizingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        controlParameterLabel.setText("변수 조절");

        semimajorAxisLabel.setText("장반경");

        semimajorAxisTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        semimajorAxisTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semimajorAxisTextFieldActionPerformed(evt);
            }
        });

        semimajorAxisUnitLabel.setText("(AU)");

        semimajorAxisSlider.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        semimajorAxisSlider.setMaximum(130);
        semimajorAxisSlider.setMinimum(1);
        semimajorAxisSlider.setValue(25);
        semimajorAxisSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        semimajorAxisSlider.setDoubleBuffered(true);
        semimajorAxisSlider.setPreferredSize(new java.awt.Dimension(200, 50));
        semimajorAxisSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                semimajorAxisSliderStateChanged(evt);
            }
        });

        eccentricityLabel.setText("이심률");

        eccentricityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eccentricityTextFieldActionPerformed(evt);
            }
        });

        eccentricitySlider.setMaximum(70);
        eccentricitySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                eccentricitySliderStateChanged(evt);
            }
        });

        jToggleButton1.setBackground(java.awt.Color.gray);
        jToggleButton1.setText("시작");
        jToggleButton1.setToolTipText("");
        jToggleButton1.setActionCommand("정지");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlParameterPanelLayout = new javax.swing.GroupLayout(controlParameterPanel);
        controlParameterPanel.setLayout(controlParameterPanelLayout);
        controlParameterPanelLayout.setHorizontalGroup(
            controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlParameterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlParameterPanelLayout.createSequentialGroup()
                        .addComponent(controlParameterLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(controlParameterPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlParameterPanelLayout.createSequentialGroup()
                                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(semimajorAxisLabel)
                                    .addComponent(eccentricityLabel))
                                .addGap(18, 18, 18)
                                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(controlParameterPanelLayout.createSequentialGroup()
                                        .addComponent(semimajorAxisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(semimajorAxisUnitLabel))
                                    .addGroup(controlParameterPanelLayout.createSequentialGroup()
                                        .addComponent(eccentricityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(eccentricitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(semimajorAxisSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(controlParameterPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlParameterPanelLayout.setVerticalGroup(
            controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlParameterPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(controlParameterLabel)
                .addGap(9, 9, 9)
                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(semimajorAxisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(semimajorAxisLabel)
                    .addComponent(semimajorAxisUnitLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(semimajorAxisSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eccentricityLabel)
                    .addComponent(eccentricityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eccentricitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        firstLaw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        focalPointCheckBox.setText("초점 보기");
        focalPointCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                focalPointCheckBoxActionPerformed(evt);
            }
        });

        centerCheckBox.setText("중심 보기");
        centerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centerCheckBoxActionPerformed(evt);
            }
        });

        semiminorAxisCheckBox.setText("단반경 보기");

        semimajorAxisCheckBox.setText("장반경 보기");

        distanceFromFocalPointsCheckBox.setText("초점으로부터의 거리");

        firstLawexplanationLabel.setText("모든 행성은 태양을 초점으로 하는 타원 궤도 운동을 한다.");

        firstLawEquationPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        firstLawEquationLabel.setFont(new java.awt.Font("Ubuntu", 2, 24)); // NOI18N
        firstLawEquationLabel.setText("r_1   +  r_2   =  2 \\times a");

        firstLawLabel1.setText("+");

        firstLawLabel2.setText("=\\");

            firstLawR1TextField.setText("jTextField1");
            firstLawR1TextField.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    firstLawR1TextFieldActionPerformed(evt);
                }
            });

            firstLawR2TextField.setText("jTextField2");

            firstLawSumOfR1AndR2TextField.setText("jTextField3");

            javax.swing.GroupLayout firstLawEquationPaneLayout = new javax.swing.GroupLayout(firstLawEquationPane);
            firstLawEquationPane.setLayout(firstLawEquationPaneLayout);
            firstLawEquationPaneLayout.setHorizontalGroup(
                firstLawEquationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(firstLawEquationPaneLayout.createSequentialGroup()
                    .addGroup(firstLawEquationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(firstLawEquationPaneLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(firstLawR1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(firstLawLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(firstLawR2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                            .addComponent(firstLawLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(firstLawSumOfR1AndR2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(firstLawEquationPaneLayout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(firstLawEquationLabel)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            firstLawEquationPaneLayout.setVerticalGroup(
                firstLawEquationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(firstLawEquationPaneLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(firstLawEquationLabel)
                    .addGap(23, 23, 23)
                    .addGroup(firstLawEquationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(firstLawLabel1)
                        .addComponent(firstLawR1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(firstLawR2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(firstLawSumOfR1AndR2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(firstLawLabel2))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            firstLawEquationPane.setLayer(firstLawEquationLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
            firstLawEquationPane.setLayer(firstLawLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
            firstLawEquationPane.setLayer(firstLawLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
            firstLawEquationPane.setLayer(firstLawR1TextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
            firstLawEquationPane.setLayer(firstLawR2TextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
            firstLawEquationPane.setLayer(firstLawSumOfR1AndR2TextField, javax.swing.JLayeredPane.DEFAULT_LAYER);

            javax.swing.GroupLayout firstLawLayout = new javax.swing.GroupLayout(firstLaw);
            firstLaw.setLayout(firstLawLayout);
            firstLawLayout.setHorizontalGroup(
                firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(firstLawLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(firstLawexplanationLabel)
                        .addGroup(firstLawLayout.createSequentialGroup()
                            .addGroup(firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(distanceFromFocalPointsCheckBox)
                                .addComponent(focalPointCheckBox)
                                .addComponent(centerCheckBox)
                                .addComponent(semiminorAxisCheckBox)
                                .addComponent(semimajorAxisCheckBox))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(firstLawEquationPane)))
                    .addContainerGap())
            );
            firstLawLayout.setVerticalGroup(
                firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(firstLawLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(firstLawexplanationLabel)
                    .addGap(33, 33, 33)
                    .addGroup(firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(firstLawLayout.createSequentialGroup()
                            .addComponent(focalPointCheckBox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(centerCheckBox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(semiminorAxisCheckBox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(semimajorAxisCheckBox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(distanceFromFocalPointsCheckBox))
                        .addComponent(firstLawEquationPane))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            KeplersLawsPanel.addTab("케플러 제 1법칙", firstLaw);

            secondLaw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

            javax.swing.GroupLayout secondLawLayout = new javax.swing.GroupLayout(secondLaw);
            secondLaw.setLayout(secondLawLayout);
            secondLawLayout.setHorizontalGroup(
                secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 548, Short.MAX_VALUE)
            );
            secondLawLayout.setVerticalGroup(
                secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 248, Short.MAX_VALUE)
            );

            KeplersLawsPanel.addTab("케플러 제 2법칙", secondLaw);

            thirdLaw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

            javax.swing.GroupLayout thirdLawLayout = new javax.swing.GroupLayout(thirdLaw);
            thirdLaw.setLayout(thirdLawLayout);
            thirdLawLayout.setHorizontalGroup(
                thirdLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 548, Short.MAX_VALUE)
            );
            thirdLawLayout.setVerticalGroup(
                thirdLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 248, Short.MAX_VALUE)
            );

            KeplersLawsPanel.addTab("케플러 제 3법칙", thirdLaw);

            javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
            jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
            jInternalFrame1Layout.setHorizontalGroup(
                jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(controlParameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(KeplersLawsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(visualizingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE))
                    .addContainerGap())
            );
            jInternalFrame1Layout.setVerticalGroup(
                jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(visualizingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(controlParameterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(KeplersLawsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addContainerGap())
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void semimajorAxisTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semimajorAxisTextFieldActionPerformed
        // TODO add your handling code here:
        int value = Integer.parseInt(semimajorAxisTextField.getText());
    }//GEN-LAST:event_semimajorAxisTextFieldActionPerformed

    private void semimajorAxisSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_semimajorAxisSliderStateChanged
        // TODO add your handling code here:
        semimajorAxisTextField.setText(""+semimajorAxisSlider.getValue());
        semimajorAxis= semimajorAxisSlider.getValue();
        semiminorAxis= semimajorAxis*Math.sqrt(1-Math.pow(eccentricity,2));
        visualizingPanel.repaint();
    }//GEN-LAST:event_semimajorAxisSliderStateChanged

    private void eccentricityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eccentricityTextFieldActionPerformed
        // TODO add your handling code here:
        int value = Integer.parseInt(eccentricityTextField.getText());
    }//GEN-LAST:event_eccentricityTextFieldActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if(jToggleButton1.isSelected()) {
            timer.start();
        }else{
            timer.stop();
            firstLawR1TextField.setText(""+semimajorAxisSlider.getValue());
            //System.out.printf("%f %f\n",keplerSystem.positionX,keplerSystem.positionY);
        }        
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void eccentricitySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_eccentricitySliderStateChanged
        // TODO add your handling code here:
        eccentricityTextField.setText(""+(eccentricitySlider.getValue())/100.);
        eccentricity= (eccentricitySlider.getValue())/100.;
        semiminorAxis= semimajorAxis*Math.sqrt(1-Math.pow(eccentricity,2));  
        visualizingPanel.repaint();
    }//GEN-LAST:event_eccentricitySliderStateChanged

    private void focalPointCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_focalPointCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_focalPointCheckBoxActionPerformed

    private void firstLawR1TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstLawR1TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstLawR1TextFieldActionPerformed

    private void centerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centerCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_centerCheckBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane KeplersLawsPanel;
    private javax.swing.JCheckBox centerCheckBox;
    private javax.swing.JLabel controlParameterLabel;
    private javax.swing.JPanel controlParameterPanel;
    private javax.swing.JCheckBox distanceFromFocalPointsCheckBox;
    private javax.swing.JLabel eccentricityLabel;
    private javax.swing.JSlider eccentricitySlider;
    private javax.swing.JTextField eccentricityTextField;
    private javax.swing.JPanel firstLaw;
    private javax.swing.JLabel firstLawEquationLabel;
    private javax.swing.JLayeredPane firstLawEquationPane;
    private javax.swing.JLabel firstLawLabel1;
    private javax.swing.JLabel firstLawLabel2;
    private javax.swing.JTextField firstLawR1TextField;
    private javax.swing.JTextField firstLawR2TextField;
    private javax.swing.JTextField firstLawSumOfR1AndR2TextField;
    private javax.swing.JLabel firstLawexplanationLabel;
    private javax.swing.JCheckBox focalPointCheckBox;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JPanel secondLaw;
    private javax.swing.JCheckBox semimajorAxisCheckBox;
    private javax.swing.JLabel semimajorAxisLabel;
    private javax.swing.JSlider semimajorAxisSlider;
    private javax.swing.JTextField semimajorAxisTextField;
    private javax.swing.JLabel semimajorAxisUnitLabel;
    private javax.swing.JCheckBox semiminorAxisCheckBox;
    private javax.swing.JPanel thirdLaw;
    private javax.swing.JPanel visualizingPanel;
    // End of variables declaration//GEN-END:variables

    ///// PlanetaryMotion

}


class PlanetaryMotion{
    int semiMajorAxis;
    double semiMinorAxis,eccentricity;
    double positionR,positionTheta;
    double positionX,positionY;
    double startTime,dt;
   
    //initial conditition
    PlanetaryMotion(int semiMajor,double semiMinor,double ecc){
        eccentricity = ecc ;
        semiMajorAxis = semiMajor ;
        semiMinorAxis = semiMinor;
        startTime = 0;
        dt = 10;
        positionR=semiMajorAxis*(1-eccentricity);
        positionTheta=0 ;
        positionX = positionR*Math.cos(positionTheta);
        positionY = positionR*Math.sin(positionTheta);
    }
    public void ModelDynamics(int semiMajor,double semiMinor,double ecc){
        eccentricity = ecc ;
        semiMajorAxis = semiMajor ;
        semiMinorAxis = semiMinor;
        startTime += dt ;
        positionTheta += dt/positionR/positionR ; //Angular momentum conservation
        if(positionTheta>2*Math.PI) positionTheta -= 2*Math.PI ; //constraint 0<=theta < 2* pi
        positionR = (semiMajorAxis*(1-eccentricity*eccentricity)/(1+eccentricity*Math.cos(positionTheta))) ;//Orbit equation
        //From polar to Cartesian coordinates
        positionX = positionR*Math.cos(positionTheta);
        positionY = -positionR*Math.sin(positionTheta);      
        
    }
    
    
}

