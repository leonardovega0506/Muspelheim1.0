package mx.com.ananda.midgard.service.util;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {

    private String folder = "images//";

    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(folder + file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

    public boolean deleteImage(String nombre) {
        String ruta = folder;
        File file = new File(ruta + nombre);
        boolean borrado = file.delete();
        if(borrado){
            return true;
        }
        else{
            return false;
        }
    }

    public String getImageUrl(String imageName) {
        return folder + imageName;
    }
}
