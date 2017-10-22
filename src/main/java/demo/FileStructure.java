package demo;

import java.nio.file.Path;

public class FileStructure {


    private Path path;
    private String name;
    private String type;
    private long size;
    private String extension;

    public FileStructure(String name, String type, long size, String extension, Path path) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.extension = extension;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }


    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

}
