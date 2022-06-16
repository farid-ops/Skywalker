package monckey.chopper.file;

import org.springframework.stereotype.Service;

@Service
public class GetExtensionImpl implements GetExtension {

    @Override
    public String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }
}
