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
import java.awt.geom.Line2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.awt.image.*;

public class ImagePrinter implements Printable {
    
    private BufferedImage image;
    
    public ImagePrinter(BufferedImage image) {
        this.image = image;

        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (Exception PrintException) {
                PrintException.printStackTrace();
            }
        }
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int page) {
        int i;
        Graphics2D g2d = (Graphics2D)graphics;
        Line2D.Double line = new Line2D.Double();
        if (page == 0) {
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            g2d.drawImage(image, null, 0, 0);
            return (PAGE_EXISTS);
        } else
            return (NO_SUCH_PAGE);
    }  
}
