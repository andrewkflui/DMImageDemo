/***************************************************************
 *
 * Interactive Demo: Digital Image Properties and Processing
 * Copyright (c) 2006 Dr. Andrew Kwok-Fai LUI
 * The Open University of Hong Kong
 *
 * Enhance the learning effectiveness of students through greater interactions
 */
/*  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package faifai.image;

public class ScaleSliderPanel extends javax.swing.JPanel {
    
    public ScaleSliderPanel() {
        initComponents();
    }
    
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jSlider1 = new javax.swing.JSlider();
    jLabel1 = new javax.swing.JLabel();

    setLayout(new java.awt.BorderLayout());

    jSlider1.setMajorTickSpacing(5);
    jSlider1.setMaximum(50);
    jSlider1.setMinimum(5);
    jSlider1.setValue(10);
    jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        jSlider1StateChanged(evt);
      }
    });
    add(jSlider1, java.awt.BorderLayout.CENTER);

    jLabel1.setText("Choose the Scale");
    add(jLabel1, java.awt.BorderLayout.NORTH);
  }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        float scale = getSliderValue();
    }//GEN-LAST:event_jSlider1StateChanged
    
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JSlider jSlider1;
  // End of variables declaration//GEN-END:variables
    
    public float getSliderValue() {
        int value = jSlider1.getValue();
        return value / 10.0F;
    }
    
}
