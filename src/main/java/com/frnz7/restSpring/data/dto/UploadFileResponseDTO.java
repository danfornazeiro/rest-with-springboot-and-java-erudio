package com.frnz7.restSpring.data.dto;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UploadFileResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private String size;

    public UploadFileResponseDTO(String fileName, String fileDownloadUri, String fileType, String size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public UploadFileResponseDTO() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        UploadFileResponseDTO that = (UploadFileResponseDTO) object;
        return Objects.equals(fileName, that.fileName) && Objects.equals(fileDownloadUri, that.fileDownloadUri) && Objects.equals(fileType, that.fileType) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileDownloadUri, fileType, size);
    }
}
