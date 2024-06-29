package com.starscream.soundgood.utils;

import com.starscream.soundgood.enums.FileTypeEnum;
import lombok.experimental.UtilityClass;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@UtilityClass
public class FileUploadUtil {

    public static boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType != null) {
            return contentType.startsWith("image/");
        }
        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            String extension = getFileExtension(fileName);
            return extension != null && isImageExtension(extension);
        }
        return false;
    }

    public static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return null;
    }

    private static boolean isImageExtension(String extension) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif"};
        for (String ext : imageExtensions) {
            if (extension.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkFileType(MultipartFile file, FileTypeEnum fileTypeEnum) {
        try (TikaInputStream tikaInputStream = TikaInputStream.get(file.getInputStream())) {
            Detector detector = new DefaultDetector();
            Metadata metadata = new Metadata();
            MediaType mediaType = detector.detect(tikaInputStream, metadata);

            if (mediaType != null && mediaType.getType().equals(fileTypeEnum.name().toLowerCase())) {
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
