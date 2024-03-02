package me.araopj;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        var file = new File("MOCK_DATA.csv");
        var data = getFileContents(file, 1, ",", StandardCharsets.ISO_8859_1);
        data.stream()
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    public static List<String[]> getFileContents(File file, int line, String separator, Charset charset) throws IOException {
        Objects.requireNonNull(file, "File cannot be null");
        Objects.requireNonNull(separator, "separator cannot be null, please use an empty String to separate everything (\"\")");
        try (var data = Files.lines(Paths.get(file.getAbsolutePath()), charset)) {
           return data.skip(line)
                   .map((a) -> Arrays.stream(a.split(separator)).map(String::trim).collect(Collectors.joining(separator)))
                   .map((e) -> e.split(separator))
                   .collect(Collectors.toList());
        }
    }
}