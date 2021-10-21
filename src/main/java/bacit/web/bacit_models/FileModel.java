package bacit.web.bacit_models;

public class FileModel {

    private String name;
    private byte[] contents;
    private String mimeType;

    public FileModel(String name, byte[] contents, String mimeType) {
        this.name = name;
        this.contents = contents;
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
