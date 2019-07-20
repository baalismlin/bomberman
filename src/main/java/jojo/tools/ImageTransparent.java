package jojo.tools;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import jojo.tools.PathHelper;

public class ImageTransparent {

    private List<URL> imageUrls;
    private List<Integer> filters;

    public ImageTransparent(List<String> resources, List<Integer> filters) {
        setImageURLs(resources);

        this.filters = filters;
    }

    public ImageTransparent(List<String> resources, int filter) {
        setImageURLs(resources);
        this.filters = new ArrayList<Integer>(1);
        this.filters.add(Integer.valueOf(filter));

    }

    private void setImageURLs(List<String> resources) {
        this.imageUrls = resources.stream().map(resource -> PathHelper.resourceURL(resource))
                .collect(Collectors.toList());
    }

    public void MakeTransparent() {
        Image srcImage;
        BufferedImage dstImage;
        int width;
        int height;
        Graphics2D g2d;
        try {

            for (URL url : imageUrls) {
                srcImage = new ImageIcon(url).getImage();
                width = srcImage.getWidth(null);
                height = srcImage.getHeight(null);
                dstImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                g2d = (Graphics2D) dstImage.getGraphics();

                for (int y = dstImage.getMinY(); y < dstImage.getHeight(); y++) {
                    for (int x = dstImage.getMinX(); x < dstImage.getWidth(); x++) {
                        var rgb = dstImage.getRGB(x, y);
                        if (filters.stream().anyMatch(value -> value.intValue() == rgb)) {
                            var newRGB = (1 << 24) | (rgb & 0x00ffffff);

                            dstImage.setRGB(x, y, newRGB);
                        }
                    }
                }

                g2d.drawImage(dstImage, 0, 0, null);
                ImageIO.write(dstImage, "png", new File(url.getPath()));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}