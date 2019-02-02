package pl.majk.learn.utilities;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

    private MultipartFile filename;

    public MultipartFile getFilename() {
        return filename;
    }

    public void setFilename(MultipartFile filename) {
        this.filename = filename;
    }
}
