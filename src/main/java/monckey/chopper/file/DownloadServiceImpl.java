package monckey.chopper.file;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class DownloadServiceImpl implements DownloadService {


    private final GetExtension extension;

    public DownloadServiceImpl(GetExtension extension){
        this.extension = extension;
    }

    @Override
    public Object download(String filename) throws IOException {
        String destfilename = UUID.randomUUID().toString().concat(this.extension.getExtension(filename));// to set random strinh for destination file name
        String destfilepath = "downloads-files" + destfilename;// to set destination file path

        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("key/tijara-edf92-firebase-adminsdk-ck5ul-55736f3105.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("tijara-edf92.appspot.com", filename));
        blob.downloadTo(Paths.get(destfilepath));
        return HttpStatus.OK;
    }
}
