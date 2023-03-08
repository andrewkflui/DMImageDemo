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
package helper.gui;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TutorialFrame extends javax.swing.JFrame {

  public TutorialFrame() {
    initComponents();
    setTopRight();
    setAlwaysOnTop(true);
  }

  public void loadText(String resource) throws Exception {
    InputStream instream = this.getClass().getResourceAsStream(resource);
    BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
    StringBuffer sb = new StringBuffer();
    String line;
    while ((line = reader.readLine()) != null) {
      sb.append(line);
    }
    jEditorPane1.setContentType("text/html");
    jEditorPane1.setText(sb.toString());
    jEditorPane1.setCaretPosition(0);
    jEditorPane1.repaint();
  }

  public void setTopRight() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    super.setLocation(screenSize.width - getWidth(), 0);
  }

  public void hideFrame() {
    this.setVisible(false);
  }

  public void showFrame() {
    this.setVisible(true);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jEditorPane1 = new javax.swing.JEditorPane();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Help");
    setBackground(new java.awt.Color(0, 0, 0));
    setForeground(new java.awt.Color(0, 0, 0));
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        formWindowClosing(evt);
      }
    });
    getContentPane().setLayout(new java.awt.GridLayout(1, 0));

    jPanel1.setBackground(new java.awt.Color(0, 0, 0));
    jPanel1.setLayout(new java.awt.GridBagLayout());

    jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 320));

    jEditorPane1.setBorder(null);
    jEditorPane1.setContentType("text/html");
    jEditorPane1.setEditable(false);
    jEditorPane1.setFont(new java.awt.Font("Arial", 0, 9)); // NOI18N
    jEditorPane1.setForeground(new java.awt.Color(51, 51, 51));
    jEditorPane1.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n    <p style=\"margin-top: 0\">\r\n      \rabc\n    </p>\r\n  </body>\r\n</html>\r\n");
    jEditorPane1.setCaretColor(new java.awt.Color(255, 255, 255));
    jEditorPane1.setMargin(new java.awt.Insets(5, 5, 5, 5));
    jEditorPane1.setMinimumSize(new java.awt.Dimension(480, 600));
    jScrollPane1.setViewportView(jEditorPane1);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    jPanel1.add(jScrollPane1, gridBagConstraints);

    getContentPane().add(jPanel1);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    hideFrame();
  }//GEN-LAST:event_formWindowClosing

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new TutorialFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JEditorPane jEditorPane1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  // End of variables declaration//GEN-END:variables
}
