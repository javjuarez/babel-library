package com.example.babellibrary.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.babellibrary.models.services.interfaces.UploadFileServiceInterface;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements UploadFileServiceInterface {

    private final static String UPLOAD_FOLDER = "uploads";

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathFoto = null;

        if (filename.equals("noCover")) {
            URI noCoverBook;
            try {
                noCoverBook = new ClassPathResource("static/img/no-cover.jpg").getURI();
                pathFoto = Paths.get(noCoverBook);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            pathFoto = getPath(filename);
        }

        Resource resource = new UrlResource(pathFoto.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("No se puede cargar la imágen: " + pathFoto.toString());
        }
        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        Path rootPath = getPath(file.getOriginalFilename());
        Files.copy(file.getInputStream(), rootPath);
        return file.getOriginalFilename();
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File archivoFoto = rootPath.toFile();

        if (archivoFoto.exists() && archivoFoto.canRead()) {
            if (archivoFoto.delete()) {
                return true;
            }
        }
        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOAD_FOLDER).resolve(filename).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOAD_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        Files.createDirectory(Paths.get(UPLOAD_FOLDER));
    }

}