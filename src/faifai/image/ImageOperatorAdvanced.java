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
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;

public class ImageOperatorAdvanced {
  
  public static BufferedImage rotate(BufferedImage inputImage, double theta) {
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    BufferedImage oimage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),
      BufferedImage.TYPE_INT_RGB);
    double costheta = Math.cos(theta);
    double sintheta = Math.sin(theta);
    for (int x=0; x<width; x++) {
      for (int y=0; y<height; y++) {
        int xc = x - width / 2;
        int yc = y - height / 2;
        int xprime = (int)(xc * costheta - yc * sintheta) + width / 2;
        int yprime = (int)(xc * sintheta + yc * costheta) + height / 2;
        if (xprime < 0 || xprime >= width || yprime < 0 || yprime >= height)
          continue;
        int pixel = inputImage.getRGB(x, y);
        oimage.setRGB(xprime, yprime, pixel);
      }
    }
    return oimage;
  }
  
  public static BufferedImage rotateBackward(BufferedImage inputImage, double theta) {
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    BufferedImage oimage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),
      BufferedImage.TYPE_INT_RGB);
    double costheta = Math.cos(-theta);
    double sintheta = Math.sin(-theta);
    for (int xprime=0; xprime<width; xprime++) {
      for (int yprime=0; yprime<height; yprime++) {
                int xc = xprime - width / 2;
        int yc = yprime - height / 2;
        int x = (int)(xc * costheta - yc * sintheta) + width / 2;
        int y = (int)(xc * sintheta + yc * costheta) + height / 2;
        if (x < 0 || x >= width || y < 0 || y >= height)
          continue;
        int pixel = inputImage.getRGB(x, y);
        oimage.setRGB(xprime, yprime, pixel);
      }
    }
    return oimage;
  }
  
  private static double[] getMean(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    double sum[] = new double[3];
    for (int x=0; x<width; x++) {
      for (int y=0; y<height; y++) {
        int pixel = image.getRGB(x, y);
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        sum[0] += red;
        sum[1] += green;
        sum[2] += blue;
      }
    }
    int size = width * height;
    sum[0] /= size;
    sum[1] /= size;
    sum[2] /= size;
    return sum;
  }
  
  private static short[] red = new short[256];
  private static short[] green = new short[256];
  private static short[] blue = new short[256];
  
  public static BufferedImage adjustContrast(BufferedImage image, int brightness, double contrast) {
    double[] mean = getMean(image);
    for (int i = 0; i < 256; i++) {
      red[i] = (short)((i-(int)mean[0]) * contrast + mean[0] + brightness) ;
      green[i] = (short)((i-(int)mean[1]) * contrast + mean[1] + brightness);
      blue[i] = (short)((i-(int)mean[2]) * contrast + mean[2] + brightness);
      if (red[i] < 0) red[i] = 0;
      if (red[i] > 255) red[i] = 255;
      if (green[i] < 0) green[i] = 0;
      if (green[i] > 255) green[i] = 255;
      if (blue[i] < 0) blue[i] = 0;
      if (blue[i] > 255) blue[i] = 255;
    }
    short[][] mat = new short[][] {red, green, blue};
    BufferedImageOp op = new LookupOp(new ShortLookupTable(0, mat), null);
    BufferedImage oimage = op.filter(image, null);
    return oimage;
  }
  
  public static BufferedImage revealWatermark(BufferedImage inputImage) {
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    BufferedImage oimage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),
      BufferedImage.TYPE_INT_RGB);
    for (int x=0; x<width; x++) {
      for (int y=0; y<height; y++) {
        int pixel = inputImage.getRGB(x, y);
        int alpha = (pixel >> 24) & 0xff;
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        int avg = (red + green + blue) / 3;
        red = (red & 0x03) * 32;
        green = (green & 0x03) *32;
        blue = (blue & 0x03) * 32;
        alpha = 0;
        pixel = (((((alpha << 8) + red) << 8) + green) << 8) + blue;
        oimage.setRGB(x, y, pixel);
      }
    }
    return oimage;
  }
  
}
