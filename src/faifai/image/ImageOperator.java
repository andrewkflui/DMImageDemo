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
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.ShortLookupTable;

public class ImageOperator {
  
  private static short[] red = new short[256];
  private static short[] green = new short[256];
  private static short[] blue = new short[256];
  
  public static BufferedImage warm(BufferedImage image) {
    for (int i = 0; i < 256; i++) {
      red[i] = (short) (i + 15);
      green[i] = (short) i;
      blue[i] = (short) i;
      if (red[i] > 255)
        red[i] = 255;
    }
    short[][] warm = new short[][] {red, green, blue};
    BufferedImageOp op = new LookupOp(new ShortLookupTable(0, warm), null);
    BufferedImage oimage = op.filter(image, null);
    return oimage;
  }
  
  public static BufferedImage chill(BufferedImage image) {
    for (int i = 0; i < 256; i++) {
      red[i] = (short) i;
      green[i] = (short) i;
      blue[i] = (short) (i-15);
      if (blue[i] < 0) blue[i] = 0;
    }
    short[][] chill = new short[][] {red, green, blue};
    BufferedImageOp op = new LookupOp(new ShortLookupTable(0, chill), null);
    BufferedImage oimage = op.filter(image, null);
    return oimage;
  }
  
  public static BufferedImage negation(BufferedImage image) {
    for (int i = 0; i < 256; i++) {
      red[i] = (short) (255 - i);
      green[i] = (short) (255 - i);
      blue[i] = (short) (255 - i);
    }
    short[][] negation = new short[][] {red, green, blue};
    BufferedImageOp op = new LookupOp(new ShortLookupTable(0, negation), null);
    BufferedImage oimage = op.filter(image, null);
    return oimage;
  }
  
  public static BufferedImage brighten(BufferedImage image, int level) {
    for (int i = 0; i < 256; i++) {
      if (i + level < 0)
        red[i] = green[i] = blue[i] = 0;
      else if (i + level > 255)
        red[i] = green[i] = blue[i] = 255;
      else {
        red[i] = (short) (i + level);
        green[i] = (short) (i + level);
        blue[i] = (short) (i + level);
      }
    }
    short[][] brighten = new short[][] {red, green, blue};
    BufferedImageOp op = new LookupOp(new ShortLookupTable(0, brighten), null);
    BufferedImage oimage = op.filter(image, null);
    return oimage;
  }
  
  public static BufferedImage posterize(BufferedImage image, int level) {
    for (int i = 0; i < 256; i++) {
      red[i] = (short) ((i >> level) << level);
      green[i] = (short) ((i >> level) << level);
      blue[i] = (short) ((i >> level) << level);
    }
    short[][] posterize = new short[][] {red, green, blue};
    BufferedImageOp op = new LookupOp(new ShortLookupTable(0, posterize), null);
    BufferedImage oimage = op.filter(image, null);
    return oimage;
  }
  
  public static BufferedImage blur(BufferedImage image) {
    float ninth = 1.0f / 9.0f;
    float[] blurKernel = {
      ninth, ninth, ninth,
      ninth, ninth, ninth,
      ninth, ninth, ninth,
    };
    BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, blurKernel));
    BufferedImage oimage = op.filter(image, null);
    return oimage;
  }
  
  public static BufferedImage flip(BufferedImage inputImage) {
        int height = inputImage.getHeight();
    float[] matrix = {
      1, 0, 0,
      -1, 0, height
    };
    BufferedImageOp op = new AffineTransformOp(
      new AffineTransform(matrix),
      AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    BufferedImage mBufferedImage = op.filter(inputImage, null);
    return mBufferedImage;
  }
  public static BufferedImage rotate(BufferedImage inputImage, double angle) {
    return rotate(inputImage, angle, AffineTransformOp.TYPE_BILINEAR);
  }

  public static BufferedImage rotate(BufferedImage inputImage, double angle, int type) {
    double cosTheta = Math.cos(angle);
    double sinTheta = Math.sin(angle);
    int width = inputImage.getWidth();
    int height = inputImage.getHeight();
    double[] matrix = {
      cosTheta, sinTheta, -sinTheta,  cosTheta, 0, 0
    };
    /*
    BufferedImageOp op = new AffineTransformOp(
      new AffineTransform(matrix),
      AffineTransformOp.TYPE_BILINEAR);
    */
    
    BufferedImageOp op = new AffineTransformOp(
       AffineTransform.getRotateInstance(angle, width / 2.0, height / 2.0),
      type);
     
    BufferedImage mBufferedImage = op.filter(inputImage, null);
    return mBufferedImage;
  }
  
  public static BufferedImage scale(BufferedImage inputImage, double sx, double sy) {
    BufferedImageOp op = new AffineTransformOp(
      AffineTransform.getScaleInstance(sx, sy),
      AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    BufferedImage mBufferedImage = op.filter(inputImage, null);
    return mBufferedImage;
  }
  
  public static BufferedImage grey(BufferedImage inputImage) {
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
        red = avg;
        green = avg;
        blue = avg;
        alpha = 0;
        pixel = (((((alpha << 8) + red) << 8) + green) << 8) + blue;
        oimage.setRGB(x, y, pixel);
      }
    }
    return oimage;
  }
  
  public static BufferedImage roughten(BufferedImage inputImage, int range) {
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
        int change = (int)(Math.random() * range * 2 - range);
        red += change;
        green += change;
        blue += change;
        if (red < 0) red = 0;
        if (red > 255) red = 255;
        if (green < 0) green = 0;
        if (green > 255) green = 255;
        if (blue < 0) blue = 0;
        if (blue > 255) blue = 255;
        alpha = 0;
        pixel = (((((alpha << 8) + red) << 8) + green) << 8) + blue;
        oimage.setRGB(x, y, pixel);
      }
    }
    return oimage;
  }
  
  public static BufferedImage logo(BufferedImage inputImage, BufferedImage logo) {
    Graphics2D g2d = (Graphics2D)inputImage.getGraphics();
    g2d.drawImage(logo, null, 0, 0);
    return inputImage;
  }
}
