package shade;

import model.Vertex;
import raster.ImageBuffer;
import raster.Raster;
import transforms.Col;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TextureColor implements FragmentShader {

    private Raster<Col> texture;

    public TextureColor(Raster<Col> texture) {
        this.texture = texture;
    }

    @Override
    public Col getColor(Vertex v) {
        // Get texture coordinates from vertex
        double u = v.getTexU();
        double vCoord = v.getTexV();

        // Convert to pixel coordinates
        int texX = (int) (u * (texture.getWidth() - 1));
        int texY = (int) (vCoord * (texture.getHeight() - 1));

        System.out.println("Vertex position: " + v.getPosition() + ", texCoords: (" + u + ", " + vCoord + ")");
        System.out.println("Mapped to texture pixel: (" + texX + ", " + texY + ")");

        // Check borders and get color
        if (texture.checkBorders(texX, texY)) {
            Col pixelCol = texture.getPixel(texX, texY);
            System.out.println("Texture color: " + pixelCol);
            return pixelCol;
        }

        System.out.println("Out of texture bounds, using vertex color: " + v.getCol());
        return v.getCol();
    }

    public static Raster<Col> loadTexture(String path) {
        try {
            // Načtení obrázku ze souboru
            BufferedImage image = ImageIO.read(new File(path));
            if(image == null) {
                throw new FileNotFoundException("Format obrazku neni podporovan"+path);
            }

            // Získání rozměrů obrázku
            int width = image.getWidth();
            int height = image.getHeight();

            // Vytvoření Rasteru pro texturu
            Raster<Col> texture = new ImageBuffer(width, height);

            // Procházení všech pixelů obrázku
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Získání barvy pixelu
                    int rgb = image.getRGB(x, y);

                    // Převod barvy z ARGB na Col
                    int alpha = (rgb >> 24) & 0xFF;
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Normalizace barev na rozsah [0, 1]
                    double r = red / 255.0;
                    double g = green / 255.0;
                    double b = blue / 255.0;

                    // Vytvoření objektu Col a uložení do Rasteru
                    texture.setPixel(x, y, new Col(r, g, b));
                }
            }

            return texture;
        } catch (IOException e) {
            System.err.println("Chyba při načítání textury: " + e.getMessage());
            return null; // Vrátí null, pokud se texturu nepodaří načíst
        }
    }
}