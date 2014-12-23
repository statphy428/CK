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
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author minakim
 */
public class KeplerLaws extends javax.swing.JApplet {
    int semimajorAxis;
    double eccentricity,semiminorAxis;
    double focalPointDistance1,focalPointDistance2;
    int periodStep;
    PlanetaryMotion keplerSystem;
    javax.swing.Timer timer;
    /**
     * Initializes the applet KeplerLaws
     */
    public void cal(){
            double startTheta = keplerSystem.positionTheta1;
            int startDt = keplerSystem.dtTime;
            do{
            keplerSystem.ModelDynamics(semimajorAxis,semiminorAxis,eccentricity);            
            }while(keplerSystem.positionTheta1-startTheta<2*Math.PI);
            periodStep =  keplerSystem.dtTime-startDt;
            System.out.format("%d\n", periodStep);
    }
        
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
        eccentricity = 0.35;              
        semiminorAxis = semimajorAxis*(Math.sqrt(1-Math.pow(eccentricity, 2)));
        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(() -> {
                initComponents();
                timer = new javax.swing.Timer(1,new aListener());                
                keplerSystem = new PlanetaryMotion(semimajorAxis,semiminorAxis,eccentricity);
                timer.stop();
                
            });
        } catch (InterruptedException | InvocationTargetException ex) {
        }
    }

    
    public class aListener implements ActionListener 
    {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                visualizingPanel.repaint();
                
                //firstLawR1TextField.setText(""+keplerSystem.positionX);
            }
    };
    
    public class displayPanel extends javax.swing.JPanel{                
        displayPanel(){
            super();
        }
        @Override
        public void paintComponent(Graphics g)
        {  
            int width = visualizingPanel.getWidth();
            int height = visualizingPanel.getHeight();
            super.paintComponent(g);
            Graphics2D orbit = (Graphics2D)g;
            Color refColor = new Color(255,255,255);
            orbit.setColor(refColor);
            Ellipse2D.Double PlanetOrbit;
            PlanetOrbit = new Ellipse2D.Double(width/2-(semimajorAxis*(1+eccentricity)),height/2-semiminorAxis,2*semimajorAxis,2*semiminorAxis);
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
            sunCenter = new Point2D.Float((float)(width/2),(float)(height/2));
            sunP = new RadialGradientPaint(sunCenter,1.0f * sunRadius,dist,sunColors);            
            sun.setPaint(sunP);
            sun.fillOval(width/2-sunRadius,height/2-sunRadius,sunRadius*2,sunRadius*2);
                       
            if(focalPointCheckBox.isSelected()){
                Graphics2D focal = (Graphics2D)g;
                refColor = new Color(0,0,255);
                focal.setColor(refColor);
                Ellipse2D.Double focalPoint ;
                focalPoint = new Ellipse2D.Double(width/2-(2*semimajorAxis*eccentricity)-3,height/2-3,6,6);                          
                focal.draw(focalPoint);
                focal.fill(focalPoint); 
            }
            if(centerCheckBox.isSelected()){
                Graphics2D center = (Graphics2D)g;
                center.setColor(Color.white);
                Ellipse2D.Double OrbitCenter ;
                OrbitCenter = new Ellipse2D.Double(width/2-(semimajorAxis*eccentricity)-3,height/2-3,6,6);                          
                center.draw(OrbitCenter);
                center.fill(OrbitCenter);                
            }
            if(semiminorAxisCheckBox.isSelected()){
                Graphics2D semiMinorLine = (Graphics2D)g;
                semiMinorLine.setColor(Color.white);
                Line2D line1=new Line2D.Double(width/2-(semimajorAxis*eccentricity),height/2,width/2-(semimajorAxis*eccentricity),height/2-semiminorAxis);
                semiMinorLine.draw(line1);
                
            }
            if(semimajorAxisCheckBox.isSelected()){
                Graphics2D semiMajorLine = (Graphics2D)g;
                semiMajorLine.setColor(Color.white);
                Line2D line2=new Line2D.Double(width/2-(semimajorAxis*eccentricity),height/2,width/2+(semimajorAxis*(1-eccentricity)),height/2);
                semiMajorLine.draw(line2);
                
            }     
            if(timer.isRunning()){
                
            }else{
                return;
            }
            keplerSystem.ModelDynamics(semimajorAxis,semiminorAxis,eccentricity);
            /*X , Y */
            Graphics2D planet = (Graphics2D)g;
            planet.setColor(Color.yellow);
            Ellipse2D.Double particle ;
            particle = new Ellipse2D.Double(width/2+keplerSystem.positionX-3,height/2+keplerSystem.positionY-3,6,6);                          
            planet.draw(particle);
            planet.fill(particle);
            
            
            focalPointDistance1=Math.sqrt(Math.pow(keplerSystem.positionX+2*semimajorAxis*eccentricity,2)+Math.pow(keplerSystem.positionY,2));
            focalPointDistance2=Math.sqrt(Math.pow(keplerSystem.positionX,2)+Math.pow(keplerSystem.positionY,2));                       
            
            if(distanceFromFocalPointsCheckBox.isSelected()){
                Graphics2D focalDistance1 = (Graphics2D)g;
                refColor = new Color(0,0,255);
                focalDistance1.setColor(refColor);
                Line2D line3=new Line2D.Double(width/2-(2*semimajorAxis*eccentricity),height/2,width/2+keplerSystem.positionX,height/2+keplerSystem.positionY);
                focalDistance1.draw(line3);
                firstLawR1TextField.setText(String.format("%.1f", focalPointDistance1));   
                Graphics2D focalDistance2 = (Graphics2D)g;
                refColor = new Color(0,0,255);
                focalDistance2.setColor(refColor);
                Line2D line4=new Line2D.Double(width/2,height/2,width/2+keplerSystem.positionX,height/2+keplerSystem.positionY);
                focalDistance2.draw(line4);
                firstLawR2TextField.setText(String.format("%.1f", focalPointDistance2));   
                firstLawSumOfR1AndR2TextField.setText(String.format("%.1f", focalPointDistance1+focalPointDistance2));   
                
            }
            if(sweepingCheckBox.isSelected()){
                double ratio = (float) 100/ (float) periodStep;
                jTextField1.setText(String.format("%.3f",ratio));
                double areaRatio = Math.PI*(float)(Math.pow(semimajorAxis,2))*Math.sqrt(1-Math.pow(eccentricity,2))*ratio;
                jTextField2.setText(String.format("%.1f",areaRatio));
                for(int i=0;i<100;i++) { 
                    Graphics2D areaPoint = (Graphics2D)g;
                    refColor = new Color(0,255,255);
                    areaPoint.setColor(refColor);
                    Line2D line5=new Line2D.Double(width/2,height/2,width/2+keplerSystem.arrowPositionX[i],height/2+keplerSystem.arrowPositionY[i]);
                    areaPoint.draw(line5);
                    
                }
            }
                
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
        startPauseToggleButton = new javax.swing.JToggleButton();
        ResetButton = new javax.swing.JButton();
        KeplersLawsPanel = new javax.swing.JTabbedPane();
        firstLaw = new javax.swing.JPanel();
        focalPointCheckBox = new javax.swing.JCheckBox();
        centerCheckBox = new javax.swing.JCheckBox();
        semiminorAxisCheckBox = new javax.swing.JCheckBox();
        semimajorAxisCheckBox = new javax.swing.JCheckBox();
        distanceFromFocalPointsCheckBox = new javax.swing.JCheckBox();
        firstLawEquationPane = new javax.swing.JLayeredPane();
        firstLawLabel1 = new javax.swing.JLabel();
        firstLawLabel2 = new javax.swing.JLabel();
        firstLawR1TextField = new javax.swing.JTextField();
        firstLawR2TextField = new javax.swing.JTextField();
        firstLawSumOfR1AndR2TextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        secondLaw = new javax.swing.JPanel();
        sweepingCheckBox = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        thirdLaw = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        jInternalFrame1.setTitle("Kepler's Law");
        jInternalFrame1.setMaximumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setMinimumSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setName("Kepler's law"); // NOI18N
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(800, 600));
        jInternalFrame1.setVisible(true);

        visualizingPanel.setBackground(new java.awt.Color(24, 13, 1));
        visualizingPanel.setPreferredSize(new java.awt.Dimension(500, 300));

        javax.swing.GroupLayout visualizingPanelLayout = new javax.swing.GroupLayout(visualizingPanel);
        visualizingPanel.setLayout(visualizingPanelLayout);
        visualizingPanelLayout.setHorizontalGroup(
            visualizingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );
        visualizingPanelLayout.setVerticalGroup(
            visualizingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        controlParameterPanel.setToolTipText("");
        controlParameterPanel.setMaximumSize(new java.awt.Dimension(200, 236));
        controlParameterPanel.setMinimumSize(new java.awt.Dimension(200, 236));
        controlParameterPanel.setPreferredSize(new java.awt.Dimension(200, 236));

        controlParameterLabel.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        controlParameterLabel.setText("Orbit settings");

        semimajorAxisLabel.setText("semimajor axis");

        semimajorAxisTextField.setText("100");
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
        semimajorAxisSlider.setValue(100);
        semimajorAxisSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        semimajorAxisSlider.setDoubleBuffered(true);
        semimajorAxisSlider.setPreferredSize(new java.awt.Dimension(200, 50));
        semimajorAxisSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                semimajorAxisSliderStateChanged(evt);
            }
        });

        eccentricityLabel.setText("eccentricity");

        eccentricityTextField.setText("0.35");
        eccentricityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eccentricityTextFieldActionPerformed(evt);
            }
        });

        eccentricitySlider.setMaximum(70);
        eccentricitySlider.setValue(35);
        eccentricitySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                eccentricitySliderStateChanged(evt);
            }
        });

        startPauseToggleButton.setBackground(java.awt.Color.gray);
        startPauseToggleButton.setText("Start");
        startPauseToggleButton.setToolTipText("");
        startPauseToggleButton.setActionCommand("정지");
        startPauseToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPauseToggleButtonActionPerformed(evt);
            }
        });

        ResetButton.setText("Reset");
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlParameterPanelLayout = new javax.swing.GroupLayout(controlParameterPanel);
        controlParameterPanel.setLayout(controlParameterPanelLayout);
        controlParameterPanelLayout.setHorizontalGroup(
            controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlParameterPanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlParameterPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlParameterPanelLayout.createSequentialGroup()
                                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(semimajorAxisLabel)
                                    .addComponent(eccentricityLabel))
                                .addGap(3, 3, 3)
                                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(controlParameterPanelLayout.createSequentialGroup()
                                        .addComponent(semimajorAxisTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(semimajorAxisUnitLabel))
                                    .addComponent(eccentricityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(eccentricitySlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(semimajorAxisSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(controlParameterPanelLayout.createSequentialGroup()
                        .addComponent(controlParameterLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(controlParameterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startPauseToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlParameterPanelLayout.setVerticalGroup(
            controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlParameterPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startPauseToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(ResetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        KeplersLawsPanel.setMaximumSize(new java.awt.Dimension(558, 236));
        KeplersLawsPanel.setMinimumSize(new java.awt.Dimension(558, 236));
        KeplersLawsPanel.setOpaque(true);
        KeplersLawsPanel.setPreferredSize(new java.awt.Dimension(558, 236));

        firstLaw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        firstLaw.setMaximumSize(new java.awt.Dimension(558, 236));
        firstLaw.setMinimumSize(new java.awt.Dimension(558, 236));
        firstLaw.setPreferredSize(new java.awt.Dimension(558, 236));

        focalPointCheckBox.setText("show empty focus");
        focalPointCheckBox.setActionCommand("Show ");
        focalPointCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                focalPointCheckBoxActionPerformed(evt);
            }
        });

        centerCheckBox.setText("show center");
        centerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centerCheckBoxActionPerformed(evt);
            }
        });

        semiminorAxisCheckBox.setText("show semiminor axis");
        semiminorAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semiminorAxisCheckBoxActionPerformed(evt);
            }
        });

        semimajorAxisCheckBox.setText("show semimajor axis");
        semimajorAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                semimajorAxisCheckBoxActionPerformed(evt);
            }
        });

        distanceFromFocalPointsCheckBox.setText("show radial lines");
        distanceFromFocalPointsCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distanceFromFocalPointsCheckBoxActionPerformed(evt);
            }
        });

        firstLawEquationPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        firstLawLabel1.setText("+");

        firstLawLabel2.setText("=");

        firstLawR1TextField.setMaximumSize(new java.awt.Dimension(70, 30));
        firstLawR1TextField.setMinimumSize(new java.awt.Dimension(70, 30));
        firstLawR1TextField.setPreferredSize(new java.awt.Dimension(70, 30));
        firstLawR1TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstLawR1TextFieldActionPerformed(evt);
            }
        });

        firstLawR2TextField.setMaximumSize(new java.awt.Dimension(70, 30));
        firstLawR2TextField.setMinimumSize(new java.awt.Dimension(70, 30));
        firstLawR2TextField.setPreferredSize(new java.awt.Dimension(70, 30));
        firstLawR2TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstLawR2TextFieldActionPerformed(evt);
            }
        });

        firstLawSumOfR1AndR2TextField.setMaximumSize(new java.awt.Dimension(70, 30));
        firstLawSumOfR1AndR2TextField.setMinimumSize(new java.awt.Dimension(70, 30));
        firstLawSumOfR1AndR2TextField.setPreferredSize(new java.awt.Dimension(70, 30));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("radial line 1        +        radial line 2       =    2    x    semimajor axis");

        jLabel3.setText("(AU)");

        jLabel4.setText("(AU)");

        jLabel5.setText("(AU)");

        javax.swing.GroupLayout firstLawEquationPaneLayout = new javax.swing.GroupLayout(firstLawEquationPane);
        firstLawEquationPane.setLayout(firstLawEquationPaneLayout);
        firstLawEquationPaneLayout.setHorizontalGroup(
            firstLawEquationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstLawEquationPaneLayout.createSequentialGroup()
                .addGroup(firstLawEquationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(firstLawEquationPaneLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(firstLawEquationPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(firstLawR1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addComponent(firstLawLabel1)
                        .addGap(30, 30, 30)
                        .addComponent(firstLawR2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(firstLawLabel2)
                        .addGap(33, 33, 33)
                        .addComponent(firstLawSumOfR1AndR2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        firstLawEquationPaneLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {firstLawR1TextField, firstLawR2TextField, firstLawSumOfR1AndR2TextField});

        firstLawEquationPaneLayout.setVerticalGroup(
            firstLawEquationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstLawEquationPaneLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addGroup(firstLawEquationPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstLawR1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstLawR2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstLawSumOfR1AndR2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstLawLabel1)
                    .addComponent(firstLawLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)))
        );
        firstLawEquationPane.setLayer(firstLawLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        firstLawEquationPane.setLayer(firstLawLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        firstLawEquationPane.setLayer(firstLawR1TextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        firstLawEquationPane.setLayer(firstLawR2TextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        firstLawEquationPane.setLayer(firstLawSumOfR1AndR2TextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        firstLawEquationPane.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        firstLawEquationPane.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        firstLawEquationPane.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        firstLawEquationPane.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("The orbit of a planet is an ellipse with the Sun at one of the two foci.");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout firstLawLayout = new javax.swing.GroupLayout(firstLaw);
        firstLaw.setLayout(firstLawLayout);
        firstLawLayout.setHorizontalGroup(
            firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstLawLayout.createSequentialGroup()
                .addGroup(firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstLawLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(firstLawEquationPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(firstLawLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(distanceFromFocalPointsCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(centerCheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(focalPointCheckBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                        .addGap(60, 60, 60)
                        .addGroup(firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(semimajorAxisCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(semiminorAxisCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstLawLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(30, 30, 30))
        );
        firstLawLayout.setVerticalGroup(
            firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, firstLawLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(focalPointCheckBox)
                    .addComponent(semiminorAxisCheckBox))
                .addGap(10, 10, 10)
                .addGroup(firstLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(centerCheckBox)
                    .addComponent(semimajorAxisCheckBox))
                .addGap(10, 10, 10)
                .addComponent(distanceFromFocalPointsCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(firstLawEquationPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        firstLawLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {centerCheckBox, distanceFromFocalPointsCheckBox, focalPointCheckBox, semimajorAxisCheckBox, semiminorAxisCheckBox});

        KeplersLawsPanel.addTab("Kepler's 1st Law", firstLaw);

        secondLaw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        secondLaw.setMaximumSize(new java.awt.Dimension(558, 236));
        secondLaw.setMinimumSize(new java.awt.Dimension(558, 236));
        secondLaw.setPreferredSize(new java.awt.Dimension(558, 236));

        sweepingCheckBox.setText("start sweeping");
        sweepingCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sweepingCheckBoxActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("A line segment joining a planet and the Sun sweeps out equal areas");

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText(" during equal intervals of time.");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel9.setText("a fraction sweep size of ");

        jLabel10.setText("corresponds to a sweep area of ");

        jLabel11.setText("sq AU");

        javax.swing.GroupLayout secondLawLayout = new javax.swing.GroupLayout(secondLaw);
        secondLaw.setLayout(secondLawLayout);
        secondLawLayout.setHorizontalGroup(
            secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secondLawLayout.createSequentialGroup()
                .addGroup(secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(secondLawLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6))
                    .addGroup(secondLawLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(secondLawLayout.createSequentialGroup()
                .addGroup(secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(secondLawLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(sweepingCheckBox))
                    .addGroup(secondLawLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(secondLawLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(secondLawLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(9, 9, 9)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel11)))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        secondLawLayout.setVerticalGroup(
            secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secondLawLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(31, 31, 31)
                .addComponent(sweepingCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(secondLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        KeplersLawsPanel.addTab("Kepler's 2nd Law", secondLaw);

        thirdLaw.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        thirdLaw.setMaximumSize(new java.awt.Dimension(558, 236));
        thirdLaw.setMinimumSize(new java.awt.Dimension(558, 236));
        thirdLaw.setPreferredSize(new java.awt.Dimension(558, 236));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("The square of the orbital period of a planet is proportional to the cube");

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setText("of the semimajor axis of its orbit.");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Naptune", "Pluto" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setText("Period2 = Semimajor Axis3");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel14.setText("=");

        jLabel15.setText("=");

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jTextField5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout thirdLawLayout = new javax.swing.GroupLayout(thirdLaw);
        thirdLaw.setLayout(thirdLawLayout);
        thirdLawLayout.setHorizontalGroup(
            thirdLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thirdLawLayout.createSequentialGroup()
                .addGroup(thirdLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(thirdLawLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8))
                    .addGroup(thirdLawLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12))
                    .addGroup(thirdLawLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLayeredPane1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        thirdLawLayout.setVerticalGroup(
            thirdLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(thirdLawLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(thirdLawLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        KeplersLawsPanel.addTab("Kepler's 3rd Law", thirdLaw);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(visualizingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(controlParameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KeplersLawsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlParameterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(KeplersLawsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
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
        try{ 
            int value = Integer.parseInt(semimajorAxisTextField.getText());
            if(value<=130){
                semimajorAxisSlider.setValue(value);
            }else{
                semimajorAxisSlider.setValue(100);
                semimajorAxisTextField.setText("100");
            }
      }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
                semimajorAxisSlider.setValue(100);
                semimajorAxisTextField.setText("100");
        }
        cal();
    }//GEN-LAST:event_semimajorAxisTextFieldActionPerformed

    private void semimajorAxisSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_semimajorAxisSliderStateChanged
        // TODO add your handling code here:
        semimajorAxisTextField.setText(""+semimajorAxisSlider.getValue());
        semimajorAxis= semimajorAxisSlider.getValue();
        semiminorAxis= semimajorAxis*Math.sqrt(1-Math.pow(eccentricity,2));
        visualizingPanel.repaint();
        cal();
    }//GEN-LAST:event_semimajorAxisSliderStateChanged

    private void eccentricityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eccentricityTextFieldActionPerformed
        // TODO add your handling code here:
        try{
            double value = 100*Double.parseDouble(eccentricityTextField.getText());
            if(value<=70){
                eccentricitySlider.setValue((int) (value));
            }else{
                eccentricitySlider.setValue(35);
                eccentricityTextField.setText("0.35");
            }
        }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
            eccentricitySlider.setValue(35);
            eccentricityTextField.setText("0.35");
        }
        cal();
    }//GEN-LAST:event_eccentricityTextFieldActionPerformed

    private void startPauseToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startPauseToggleButtonActionPerformed
        if(startPauseToggleButton.isSelected()) {
            startPauseToggleButton.setText("Pause");
            timer.start();
        }else{
            startPauseToggleButton.setText("Start");
            timer.stop();
      //      firstLawR1TextField.setText(""+semimajorAxisSlider.getValue());
            //System.out.printf("%f %f\n",keplerSystem.positionX,keplerSystem.positionY);
        }
        cal();
    }//GEN-LAST:event_startPauseToggleButtonActionPerformed

    private void eccentricitySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_eccentricitySliderStateChanged
        // TODO add your handling code here:
        eccentricityTextField.setText(""+(eccentricitySlider.getValue())/100.);
        eccentricity= (eccentricitySlider.getValue())/100.;
        semiminorAxis= semimajorAxis*Math.sqrt(1-Math.pow(eccentricity,2));  
        visualizingPanel.repaint();
        cal();
    }//GEN-LAST:event_eccentricitySliderStateChanged

    private void focalPointCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_focalPointCheckBoxActionPerformed
        // TODO add your handling code here:
        visualizingPanel.repaint();
    }//GEN-LAST:event_focalPointCheckBoxActionPerformed

    private void centerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centerCheckBoxActionPerformed
        // TODO add your handling code here:
        visualizingPanel.repaint();
    }//GEN-LAST:event_centerCheckBoxActionPerformed

    private void distanceFromFocalPointsCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distanceFromFocalPointsCheckBoxActionPerformed
        // TODO add your handling code here:
        visualizingPanel.repaint();
                         
    }//GEN-LAST:event_distanceFromFocalPointsCheckBoxActionPerformed

    private void firstLawR1TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstLawR1TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstLawR1TextFieldActionPerformed

    private void semiminorAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semiminorAxisCheckBoxActionPerformed
        // TODO add your handling code here:
        visualizingPanel.repaint();
    }//GEN-LAST:event_semiminorAxisCheckBoxActionPerformed

    private void semimajorAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_semimajorAxisCheckBoxActionPerformed
        // TODO add your handling code here:
        
        visualizingPanel.repaint();
    }//GEN-LAST:event_semimajorAxisCheckBoxActionPerformed

    private void sweepingCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sweepingCheckBoxActionPerformed
        // TODO add your handling code here:
        visualizingPanel.repaint();
    }//GEN-LAST:event_sweepingCheckBoxActionPerformed

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        // TODO add your handling code here:
        keplerSystem = new PlanetaryMotion(semimajorAxis,semiminorAxis,eccentricity);
        cal();
                 
        
//        timer.start();
    }//GEN-LAST:event_ResetButtonActionPerformed

    private void firstLawR2TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstLawR2TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstLawR2TextFieldActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
       
       
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane KeplersLawsPanel;
    private javax.swing.JButton ResetButton;
    private javax.swing.JCheckBox centerCheckBox;
    private javax.swing.JLabel controlParameterLabel;
    private javax.swing.JPanel controlParameterPanel;
    private javax.swing.JCheckBox distanceFromFocalPointsCheckBox;
    private javax.swing.JLabel eccentricityLabel;
    private javax.swing.JSlider eccentricitySlider;
    private javax.swing.JTextField eccentricityTextField;
    private javax.swing.JPanel firstLaw;
    private javax.swing.JLayeredPane firstLawEquationPane;
    private javax.swing.JLabel firstLawLabel1;
    private javax.swing.JLabel firstLawLabel2;
    private javax.swing.JTextField firstLawR1TextField;
    private javax.swing.JTextField firstLawR2TextField;
    private javax.swing.JTextField firstLawSumOfR1AndR2TextField;
    private javax.swing.JCheckBox focalPointCheckBox;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JPanel secondLaw;
    private javax.swing.JCheckBox semimajorAxisCheckBox;
    private javax.swing.JLabel semimajorAxisLabel;
    private javax.swing.JSlider semimajorAxisSlider;
    private javax.swing.JTextField semimajorAxisTextField;
    private javax.swing.JLabel semimajorAxisUnitLabel;
    private javax.swing.JCheckBox semiminorAxisCheckBox;
    private javax.swing.JToggleButton startPauseToggleButton;
    private javax.swing.JCheckBox sweepingCheckBox;
    private javax.swing.JPanel thirdLaw;
    private javax.swing.JPanel visualizingPanel;
    // End of variables declaration//GEN-END:variables

    ///// PlanetaryMotion

}


class PlanetaryMotion{
    int semiMajorAxis;
    double semiMinorAxis,eccentricity;
    double positionR,positionTheta,positionTheta1;
    double positionX,positionY;
    int dt, dtRate, dtTime ;
    double arrowPositionX[], arrowPositionY[];
    
   
    //initial conditition
    PlanetaryMotion(int semiMajor,double semiMinor,double ecc){
        eccentricity = ecc ;
        semiMajorAxis = semiMajor ;
        semiMinorAxis = semiMinor;        
        dt = 1;
        dtRate = 20;
        dtTime = 0;        
        positionR=semiMajorAxis*(1-eccentricity);
        positionTheta=0 ;
        positionX = positionR*Math.cos(positionTheta);
        positionY = -positionR*Math.sin(positionTheta);
        
        
        arrowPositionX = new double[100] ;
        arrowPositionY = new double[100] ;
        for(int i=0;i<100;i++) { 
            arrowPositionX[i]=0;
            arrowPositionY[i]=0;            
        }
        
        
    }
    
    
    public void ModelDynamics(int semiMajor,double semiMinor,double ecc){        
        
        dtTime += dt ;
        eccentricity = ecc ;
        semiMajorAxis = semiMajor ;
        semiMinorAxis = semiMinor;
        positionTheta += (dt*dtRate)/positionR/positionR ; //Angular momentum conservation
        positionTheta1 += (dt*dtRate)/positionR/positionR ;
        if(positionTheta>2*Math.PI){
            positionTheta -= 2*Math.PI ;
          //  System.out.printf("%d \n",dtTime);
            
        } //constraint 0<=theta < 2* pi
        positionR = (semiMajorAxis*(1-eccentricity*eccentricity)/(1+eccentricity*Math.cos(positionTheta))) ;//Orbit equation
        //From polar to Cartesian coordinates
        positionX = positionR*Math.cos(positionTheta);
        positionY = -positionR*Math.sin(positionTheta);      
        
        arrowPositionX[dtTime%100]=positionX;
        arrowPositionY[dtTime%100]=positionY;  
        
    }
    
    
}

