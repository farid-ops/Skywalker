package monckey.chopper.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface UploadService {

    String uploadFile(File file, String filename) throws IOException;

    Object upload(MultipartFile multipartFile);
}
