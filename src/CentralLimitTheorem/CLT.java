/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CentralLimitTheorem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author minakim
 */
public class CLT extends javax.swing.JApplet {
    int sampleSize;
    double probability;
    CLTMotion CLTSystem;
    javax.swing.Timer timer;
    private Object event;
    /**
     * Initializes the applet CLT
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
            java.util.logging.Logger.getLogger(CLT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CLT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CLT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CLT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        sampleSize = 20;
        probability = 0.5;

        /* Create and display the applet */
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

           
                public void run() {
                    initComponents();
                    //timer = new javax.swing.Timer(1,new CLT.aListener());
                    CLTSystem = new CLTMotion(sampleSize,probability);
                    //timer.stop();
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
        }
    }

        
    public class aListener implements ActionListener 
    {
          
            public void actionPerformed(ActionEvent e) {
                
//                visualizingPanel.removeAll();       
//                visualizingPanel.repaint();  
                
            }
    };
   
    public class displayPanel extends javax.swing.JPanel{                
        displayPanel(){
            super();
        }
      
        public void paintComponent(Graphics g)
        {  
            int width = visualizingPanel.getWidth();
            int height = visualizingPanel.getHeight();
            super.paintComponent(g);
            
            CLTSystem.ModelDynamics(sampleSize, probability);
            System.out.print("error\n");
            XYSeries series1 = new XYSeries("binomial");
            for(int i=0;i<=sampleSize;i++) { 
                series1.add(i,CLTSystem.prob_k[i]);
            }
            XYSeries series2 = new XYSeries("normal");
            for(int i=0;i<=1000;i++){
                series2.add((double)(sampleSize*i)/1000.,CLTSystem.prob_x[i]);
            }
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series1);
            dataset.addSeries(series2);
            JFreeChart chart = ChartFactory.createXYLineChart("","n","p(n)",dataset,PlotOrientation.VERTICAL,true,true,false);
            ChartPanel panel = new ChartPanel(chart);
            panel.setSize(visualizingPanel.getWidth(),visualizingPanel.getHeight());
            panel.setVisible(true);            
             
            visualizingPanel.add(panel);
           visualizingPanel.validate();
            //visualizingPanel.repaint();
            
            /*
            Function2D normal = new NormalDistributionFunction2D(sampleSize*probability,sampleSize*probability*(1-probability));
            XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, 0, sampleSize, 100, "Normal");
            JFreeChart chart = ChartFactory.createXYLineChart("","n","p(n)",dataset,PlotOrientation.VERTICAL,true,true,false);
            ChartPanel panel = new ChartPanel(chart);
            panel.setSize(visualizingPanel.getWidth(),visualizingPanel.getHeight());
            panel.setVisible(true);
            visualizingPanel.add(panel);
            visualizingPanel.validate();
            visualizingPanel.repaint();
            */
           // if(timer.isRunning()){
                
          //  }else{
           //     return;
           // }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pTextField = new javax.swing.JTextField();
        pSlider = new javax.swing.JSlider();
        NTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        NSlider = new javax.swing.JSlider();
        explanationPanel = new javax.swing.JPanel();
        binomialLabel = new javax.swing.JLabel();
        normalLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jInternalFrame1.setTitle("Normal approximation to the binomial distribution");
        jInternalFrame1.setVisible(true);

        visualizingPanel.setBackground(new java.awt.Color(246, 215, 184));
        visualizingPanel.setPreferredSize(new java.awt.Dimension(500, 300));

        javax.swing.GroupLayout visualizingPanelLayout = new javax.swing.GroupLayout(visualizingPanel);
        visualizingPanel.setLayout(visualizingPanelLayout);
        visualizingPanelLayout.setHorizontalGroup(
            visualizingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        visualizingPanelLayout.setVerticalGroup(
            visualizingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        controlParameterPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Settings");

        jLabel2.setText("p");

        pTextField.setText("0.5");
        pTextField.setMaximumSize(new java.awt.Dimension(60, 30));
        pTextField.setMinimumSize(new java.awt.Dimension(60, 30));
        pTextField.setPreferredSize(new java.awt.Dimension(60, 30));
        pTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pTextFieldActionPerformed(evt);
            }
        });

        pSlider.setMaximum(99);
        pSlider.setMinimum(1);
        pSlider.setMaximumSize(new java.awt.Dimension(160, 30));
        pSlider.setMinimumSize(new java.awt.Dimension(160, 30));
        pSlider.setPreferredSize(new java.awt.Dimension(160, 30));
        pSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pSliderStateChanged(evt);
            }
        });

        NTextField.setText("20");
        NTextField.setMaximumSize(new java.awt.Dimension(60, 30));
        NTextField.setMinimumSize(new java.awt.Dimension(60, 30));
        NTextField.setPreferredSize(new java.awt.Dimension(60, 30));
        NTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("N");

        NSlider.setMaximumSize(new java.awt.Dimension(160, 30));
        NSlider.setMinimumSize(new java.awt.Dimension(160, 30));
        NSlider.setPreferredSize(new java.awt.Dimension(160, 30));
        NSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                NSliderStateChanged(evt);
            }
        });

        javax.swing.GroupLayout controlParameterPanelLayout = new javax.swing.GroupLayout(controlParameterPanel);
        controlParameterPanel.setLayout(controlParameterPanelLayout);
        controlParameterPanelLayout.setHorizontalGroup(
            controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlParameterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlParameterPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(pTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlParameterPanelLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(NTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        controlParameterPanelLayout.setVerticalGroup(
            controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlParameterPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(controlParameterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(NTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(NSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        explanationPanel.setBackground(new java.awt.Color(254, 254, 254));
        explanationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        binomialLabel.setText("Binomial distribution");

        normalLabel.setText("Normal distribution");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel4.setText("Probability distribution");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CentralLimitTheorem/B1.jpg"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CentralLimitTheorem/B2.jpg"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CentralLimitTheorem/N1.jpg"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CentralLimitTheorem/N2.jpg"))); // NOI18N

        javax.swing.GroupLayout explanationPanelLayout = new javax.swing.GroupLayout(explanationPanel);
        explanationPanel.setLayout(explanationPanelLayout);
        explanationPanelLayout.setHorizontalGroup(
            explanationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(explanationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4))
            .addGroup(explanationPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(explanationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(explanationPanelLayout.createSequentialGroup()
                        .addComponent(binomialLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(jLabel6)
                    .addGroup(explanationPanelLayout.createSequentialGroup()
                        .addComponent(normalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addComponent(jLabel8)))
        );
        explanationPanelLayout.setVerticalGroup(
            explanationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(explanationPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addGroup(explanationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(explanationPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel5))
                    .addGroup(explanationPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(binomialLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(explanationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(normalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(24, 24, 24))
        );

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel9.setText("Binomial to Normal Approx.");

        jLabel10.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel10.setText("If N is large enourgh,");

        jLabel11.setFont(new java.awt.Font("Ubuntu", 2, 15)); // NOI18N
        jLabel11.setText("then");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CentralLimitTheorem/A1.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(14, 14, 14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(131, 131, 131))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(visualizingPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(controlParameterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(explanationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(visualizingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlParameterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(explanationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
    }// </editor-fold>//GEN-END:initComponents
/**/
    private void pTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pTextFieldActionPerformed
        // TODO add your handling code here:
        try{
            double value = 100*Double.parseDouble(pTextField.getText());
            if(value<=100){
                pSlider.setValue((int) (value));
            }else{
                pSlider.setValue(50);
                pTextField.setText("0.50");
            }
        }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
            pSlider.setValue(50);
            pTextField.setText("0.50");
        }
        visualizingPanel.repaint();
    }//GEN-LAST:event_pTextFieldActionPerformed

    private void pSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pSliderStateChanged
        // TODO add your handling code here:
        pTextField.setText(""+(pSlider.getValue())/100.);
        probability= (pSlider.getValue())/100.; 
        CLTSystem = new CLTMotion(sampleSize,probability);
        CLTSystem.ModelDynamics(sampleSize, probability);
        visualizingPanel.repaint();
    }//GEN-LAST:event_pSliderStateChanged

    private void NTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NTextFieldActionPerformed
        // TODO add your handling code here:
        try{ 
            int value = Integer.parseInt(NTextField.getText());
            if(value<=100){
                NSlider.setValue(value);
            }else{
                NSlider.setValue(20);
                NTextField.setText("20");
            }
        }catch(java.lang.NumberFormatException e){// 문자 Input 에러 처리
                NSlider.setValue(20);
                NTextField.setText("20");
        }
        visualizingPanel.repaint();
    }//GEN-LAST:event_NTextFieldActionPerformed

    private void NSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_NSliderStateChanged
        // TODO add your handling code here:
        NTextField.setText(""+NSlider.getValue());
        sampleSize= NSlider.getValue();
        CLTSystem = new CLTMotion(sampleSize,probability);
        CLTSystem.ModelDynamics(sampleSize, probability);
        visualizingPanel.repaint();
    }//GEN-LAST:event_NSliderStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider NSlider;
    private javax.swing.JTextField NTextField;
    private javax.swing.JLabel binomialLabel;
    private javax.swing.JPanel controlParameterPanel;
    private javax.swing.JPanel explanationPanel;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel normalLabel;
    private javax.swing.JSlider pSlider;
    private javax.swing.JTextField pTextField;
    private javax.swing.JPanel visualizingPanel;
    // End of variables declaration//GEN-END:variables
}
class CLTMotion{
    int sampleSize;
    double probability;
    BigDecimal prob_k[];
    BigDecimal prob_x[];
    
    //initial conditition
    CLTMotion(int size,double prob){
        sampleSize = size;
        probability = prob;
        prob_k = new BigDecimal [101] ;
        prob_x = new BigDecimal [1001] ;
        
    }
 
    static BigInteger factorial(int n)
    {
		BigInteger res = BigInteger.ONE;

		for (int i = n; i>1; i--)
		{
			res = res.multiply(BigInteger.valueOf(i));
		}
		return(res);
    }
    public void ModelDynamics(int size,double prob){        
        sampleSize = size;
        probability = prob;
        double m = sampleSize*probability;
        double variance_2 = sampleSize*probability*(1.-probability);
        for(int i=0;i<=1000;i++){
            double x = (double)i*((double)sampleSize/1000.);
            double exponent = Math.pow(x-m,2.0)/(2*variance_2);
            double scale = Math.exp((-1.)*exponent);
            double coeff = Math.sqrt(variance_2*2.*Math.PI);
            BigDecimal coeffBD = new BigDecimal(coeff);
            BigDecimal scaleBD = new BigDecimal(scale);            
            prob_x[i]=scaleBD.divide(coeffBD, 40, RoundingMode.HALF_UP);
            
        }
        
        for(int i=0;i<=sampleSize;i++){
            BigInteger ntF = factorial(sampleSize);
            BigInteger denom = factorial(i).multiply(factorial(sampleSize-i));
            BigDecimal ntFBD = new BigDecimal(ntF);
            BigDecimal denomBD = new BigDecimal(denom);
            BigDecimal quotient = ntFBD.divide(denomBD, 40, RoundingMode.HALF_UP);
            BigDecimal restBD = BigDecimal.valueOf(Math.pow(probability,(double)i) * Math.pow(1.-probability,(double)sampleSize-i));
            prob_k[i]=quotient.multiply(restBD);
            System.out.format("[%d]\t%.20f\n", i,prob_k[i]);
        }
        
            
    }
     
}
 

