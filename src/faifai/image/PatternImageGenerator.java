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
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PatternImageGenerator {

  public static void main(String args[]) throws Exception {
    int size = 128;
    BufferedImage source = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = source.createGraphics();
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, size, size);

    g2d.setColor(new Color(204, 255, 0));
    for (int x = 8; x < size; x = x + 16) {
      g2d.fillRect(x, 0, 8, size);
    }
    for (int y = 8; y < size; y = y + 16) {
      g2d.fillRect(0, y, size, 8);
    }
    ImageIO.write(source, "jpg", new File("GreenPattern128.jpg"));
  }
}
