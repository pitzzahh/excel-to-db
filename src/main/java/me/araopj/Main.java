package me.araopj;

import com.google.gson.GsonBuilder;
import io.github.pitzzahh.util.utilities.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import static io.github.pitzzahh.util.utilities.Print.println;

public class Main {
    public static void main(String[] args) throws IOException {
        var file = new File("MOCK_DATA.psv");
        var data = getFileContents(file, 5, '|', StandardCharsets.UTF_8);
        List<List<Salary>> salaries = data.stream()
                .map(arr -> {
                            List<Salary> list = new ArrayList<>();
                            for (int i = 0; i < arr.length; i++) {
                                Salary build = Salary.builder()
                                        .salaryGrade(Integer.parseInt(arr[0]))
                                        .stepName("Step " + (i + 1))
                                        .stepAmount(convertToDouble(arr[i + 1]))
                                        .build();
                                list.add(build);
                            }
                            return list;
                        }
                ).toList();
        salaries.forEach(s -> {
                    println("Number of employees = " + s.size());
                    println("First employee = " + s.getFirst());
                    println("Last employee = " + s.getLast());
                    s.forEach(System.out::println);
                });

//        println("Number of employees = " + salaries.size());
//        println("First employee = " + salaries.getFirst());
//        println("Last employee = " + salaries.getLast());
//        salaries.forEach(System.out::println);
//        println("-------CONVERTING TO JSON---------");
//        var gson = new GsonBuilder().setPrettyPrinting()
//                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
//                .create();
//        var json = gson.toJson(salaries);
//        FileUtil.writeToATextFile(json, new File("mock_data.json"), false);
    }

    public static List<String[]> getFileContents(File file, int startLine, char separator, Charset charset) throws IOException {
        Objects.requireNonNull(file, "File cannot be null");
        Objects.requireNonNull(charset, "Charset cannot be null");
        try (var data = Files.lines(Paths.get(file.getAbsolutePath()), charset)) {
            return data.skip(startLine)
                    .map((a) -> Arrays.stream(a.split(String.valueOf(separator)))
                            .map(s -> "BLANKS".equals(s.trim()) ? null : s.trim())
                            .toArray(String[]::new))
                    .collect(Collectors.toList());
        }
    }

    public static double convertToDouble(String numberString) {
        return Double.parseDouble(numberString.replace(",", ""));
    }
}