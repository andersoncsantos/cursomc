package com.anderson.cursomc.services;

import com.anderson.cursomc.services.exceptions.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile multipartFile) {
        String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!"png".equals(ext) && !"jpg".equals(ext)) {
            throw new FileException("Somente imagens .png e .jpg são permitidas");
        }

        try {
            BufferedImage img = ImageIO.read(multipartFile.getInputStream());
            if ("png".equals(ext)) {
                img = pngToJpg(img);
            }
            return img;
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo");
        }
    }

    // convert file to .jpg
    public BufferedImage pngToJpg(BufferedImage img) {
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    // getting InputStream from BufferedImage
    public InputStream getInputStream(BufferedImage img, String extension) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo");
        }
    }

    public BufferedImage cropSquare(BufferedImage bufferedImage) {
        int min = (bufferedImage.getHeight() <= bufferedImage.getWidth() ? bufferedImage.getHeight() : bufferedImage.getWidth());
        return Scalr.crop(bufferedImage, (bufferedImage.getWidth() / 2) - (min / 2), (bufferedImage.getHeight() / 2) - (min / 2), min, min);
    }

    public BufferedImage resize(BufferedImage bufferedImage, int size){
        return Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, size);
    }
}
