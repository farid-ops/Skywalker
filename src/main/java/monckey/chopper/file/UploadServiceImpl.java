package monckey.chopper.file;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {

    private static String TEMP_URL;
    private final GetExtension extension;
    private final ConvertFile convertToFile;

    public UploadServiceImpl(GetExtension extension,
                             ConvertFile convertToFile){
        this.extension = extension;
        this.convertToFile = convertToFile;
    }

    private static final String DOWNLOAD_URL = "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-ck5ul%40tijara-edf92.iam.gserviceaccount.com";

    @Override
    public String uploadFile(File file, String filename) throws IOException {
        BlobId blobId = BlobId.of("tijara-edf92.appspot.com", filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("./key/tijara-edf92-firebase-adminsdk-ck5ul-55736f3105.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return URLEncoder.encode(filename, StandardCharsets.UTF_8.name());
    }

    @Override
    public Object upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.extension.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return ResponseEntity.ok().body("Successfully Uploaded !"+TEMP_URL);                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }
}
