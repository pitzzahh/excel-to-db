package me.araopj;

import com.google.gson.GsonBuilder;
import io.github.pitzzahh.util.utilities.FileUtil;
import io.github.pitzzahh.util.utilities.Print;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.pitzzahh.util.utilities.Print.println;

public class Main {
    public static void main(String[] args) {
        final var trance = new HashMap<Integer, Integer>();

        trance.put(2021, 2);
        trance.put(2022, 3);
        trance.put(2023, 4);

        Arrays.stream(new String[]{"MASTFILE.csv"})
                .map(File::new) // Convert the string to a File (the string is the file name with extension)
                .forEach(file -> {
                    String fileName = file.getName().replace(".csv", "");
                    if (!file.exists()) {
                        throw new IllegalArgumentException("File " + fileName + " does not exist");
                    }
                    try {
                        List<String[]> rawData = getFileContents(file, 1, ',', StandardCharsets.ISO_8859_1);

                        // Convert the raw data to a list of salaries
                        // A Salary can have multiple steps
                        // We store the salaries based on their salary grade
                        // Each salary grade has multiple steps, so we store them in a list
                        List<RawInfo> salaries = rawData.stream()
                                .map(arr ->{
                                    return RawInfo.builder()
                                            .firstName(arr[0])
                                            .middleName(arr[1])
                                            .lastName(arr[2])
                                            .appel(arr[3])
                                            .posCode(arr[4])
                                            .longs(arr[14] == null ?  0.00 : convertToDouble(arr[14]))
                                            .build();
                                }).toList();

                        println("Salaries for the year " + fileName);


                        println("-------CONVERTING TO JSON---------");
                        var gson = new GsonBuilder().setPrettyPrinting()
                                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                                .create();
                        var json = gson.toJson(salaries);
                        FileUtil.writeToATextFile(json, new File(String.format("%s.json", fileName)), false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * This method reads the contents of a file and returns them as a list of arrays of strings.
     *
     * @param file      the file to read
     * @param startLine the line to start reading from
     * @param separator the separator to use
     * @param charset   the charset to use
     * @return a list of arrays of strings
     * @throws IOException if an I/O error occurs
     */
    public static List<String[]> getFileContents(File file, int startLine, char separator, Charset charset) throws IOException {
        Objects.requireNonNull(file, "File cannot be null");
        Objects.requireNonNull(charset, "Charset cannot be null");
        try (var data = Files.lines(Paths.get(file.getAbsolutePath()), charset)) {
            return data.skip(startLine)
                    .map((a) -> Arrays.stream(a.split(String.valueOf(separator)))
                            .map(cell -> "BLANKS".equals(cell.trim()) ? null : cell.trim())
                            .toArray(String[]::new))
                    .collect(Collectors.toList());
        }
    }

    public static double convertToDouble(String numberString) {
        System.out.println("numberString = " + numberString);
        return Double.parseDouble(numberString.replace(",", ""));
    }
}