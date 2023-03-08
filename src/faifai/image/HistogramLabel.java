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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class HistogramLabel extends JComponent {
  
  private int marginTop = 5;
  private int marginLeft = 5;
  private Color plotColor;
  private int dataarray[] = null;
  
  public HistogramLabel() {
    this(Color.WHITE);
  }
  
  public HistogramLabel(Color plotColor) {
    this.plotColor = plotColor;
  }

  public void setPlotColor(Color plotColor) {
    this.plotColor = plotColor;
  }
   
  public void paint(Graphics graphics) {
    Graphics2D g2d = (Graphics2D)graphics;
    int width = this.getWidth();
    int height = this.getHeight();
    g2d.setColor(Color.BLACK);
    g2d.fillRect(0, 0, width, height);
    g2d.setColor(plotColor);
    g2d.drawRect(marginLeft, marginTop, width - marginLeft * 2, height - marginTop * 2);
    if (dataarray == null)
      return;
    double xstep = (double)(width - marginLeft * 2) / (dataarray.length + 1);
    int maxdata = 0;
    int sum = 0;
    int total = 0;
    for (int i=1; i<dataarray.length-1; i++) {
      if (dataarray[i] > maxdata) maxdata = dataarray[i];
      sum += dataarray[i] * i;
      total += dataarray[i];
    }
    if (total == 0)
      return;
    sum /= total;
    for (int i=0; i<dataarray.length; i++) {
      int x = (int)(marginLeft + ((i+1) * xstep));
      int starty = height - marginTop;
      double length = dataarray[i] / (double)maxdata;
      int endy = (int)(starty - (length * (height - marginTop * 2)));
      g2d.fillRect(x, endy, (int)(xstep + 1), starty - endy);
    }
    sum = (int)(marginLeft + (sum * xstep));
    g2d.setColor(Color.YELLOW);
    g2d.drawLine(sum, height - marginTop, sum, marginTop);
  }
  
  public void setData(int dataarray[]) {
    this.dataarray = dataarray;
  }
  
}
