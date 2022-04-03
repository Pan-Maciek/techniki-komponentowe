package pl.edu.agh.odt.logic;

import pl.edu.agh.odt.result.FileSearchResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFinder {

    private final List<String> exceptions;
    private final String rootPath;
    private final String phrase;

    public FileFinder(String rootPath, String phrase){
        this.exceptions = new ArrayList<>();
        this.rootPath = rootPath;
        this.phrase = phrase;
    }

    public List<String> getExceptions(){
        return exceptions;
    }

    public List<FileSearchResult> findInDirectory() throws IOException {
        return getOdtPathStream().map(filePath -> {
            try {
                return OdtSearcher.phraseOccurrences(phrase, filePath);
            } catch (Exception e) {
                exceptions.add(e.getMessage());
                return null;
            }
        }).collect(Collectors.toList());
    }

    private Stream<String> getOdtPathStream() throws IOException {
       return Files.walk(Path.of(rootPath))
        .filter(file -> file.getFileName().toString().endsWith(".odt") || file.getFileName().toString().endsWith(".odf"))
        .map(Path::toString);
    }



}
