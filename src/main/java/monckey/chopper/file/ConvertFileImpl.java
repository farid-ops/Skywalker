package monckey.chopper.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ConvertFileImpl implements ConvertFile {

    @Override
    public File convertToFile(MultipartFile file, String filename) throws IOException {
        File tempFile = new File(filename);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
            fos.close();
        }
        return tempFile;
    }
}
