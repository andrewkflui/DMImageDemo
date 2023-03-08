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

public class ImageStreamLabel extends ImageLabel implements Runnable {

  private InputStream istream = null;
  private Thread thisThread = null;
  private boolean toStop = false;

  public synchronized OutputStream getOutputStream() throws Exception {
    if (thisThread == null) {
      thisThread = new Thread(this);
      thisThread.start();
    }

    PipedInputStream pin = new PipedInputStream();
    PipedOutputStream pout = new PipedOutputStream(pin);

    istream = new BufferedInputStream(pin);
    return new BufferedOutputStream(pout);
  }

  public void stop() {
    toStop = true;
  }

  public void run() {
    while (true) {
      if (toStop) {
        return;
      }
      if (istream != null) {
        synchronized (this) {
          try {
            BufferedImage streamImage = ImageIO.read(istream);
            istream.close();
            istream = null;
            super.setImage(streamImage);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
        }
      }
      try {
        Thread.currentThread().sleep(100);
      } catch (Exception ex) {
      }
    }
  }

  public static void main(String[] args) throws Exception {

    ImageStreamLabel displayer = new ImageStreamLabel();
    BufferedImage image = ImageIO.read(new File("/sampleimages/Island.jpg"));
    displayer.setImage(image);

    JFrame frame = new JFrame();
    frame.getContentPane().add(displayer);
    frame.setSize(300, 200);
    frame.setVisible(true);
  }
}
