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
import java.io.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;

public class ImageLabel extends JComponent {

  private BufferedImage image = null;
  private BufferedImageOp paintOp = null;
  private float scale = 1;
  private int offsetX = 0;
  private int offsetY = 0;
  private double[] transformMatrix = {
      1, 0, 0, 1, 0, 0
    };

  public ImageLabel() {
  }

  public void paint(Graphics graphics) {
    int width = super.getWidth();
    int height = super.getHeight();
    Graphics2D g2d = (Graphics2D) graphics;
    g2d.setBackground(Color.BLACK);
    g2d.fillRect(0, 0, (int) width, (int) height);
    if (image != null) {
      int imageWidth = image.getWidth();
      int imageHeight = image.getHeight();
      int xpos = (int) (width - imageWidth * scale) / 2;
      int ypos = (int) (height - imageHeight * scale) / 2;
      if (xpos < 0) {
        xpos = 0;
      }
      if (ypos < 0) {
        ypos = 0;
      }
      g2d.drawImage(image, paintOp, xpos, ypos);
    }
  }

  public float getScale() {
    return this.scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
    transformMatrix[0] = transformMatrix[3] = scale;
    paintOp = new AffineTransformOp(
      new AffineTransform(transformMatrix),
      AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    //setPreferredSize(new Dimension((int) (image.getWidth() * scale), (int) (image.getHeight() * scale)));
  }

  public void setOffset(int offsetX, int offsetY) {
    this.offsetX = offsetX;
    this.offsetY = offsetY;
    transformMatrix[4] = offsetX;
    transformMatrix[5] = offsetY;
    paintOp = new AffineTransformOp(
      new AffineTransform(transformMatrix),
      AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
  }

  public void setImage(BufferedImage image) {
    this.image = image;
    if (image != null) {
      setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    }
    repaint();
    revalidate();
  }

  public BufferedImage getImage() {
    return this.image;
  }

  public static void main(String[] args) throws Exception {

    ImageLabel displayer = new ImageLabel();
    BufferedImage image = ImageIO.read(new File("/sampleimages/Island.jpg"));
    displayer.setImage(image);

    JFrame frame = new JFrame();
    frame.getContentPane().add(displayer);
    frame.setSize(300, 200);
    frame.setVisible(true);
  }
}
