package me.araopj;

import com.google.gson.Gson;
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
        var file = new File("MOCK_DATA.csv");
        var data = getFileContents(file, 1, ",", StandardCharsets.ISO_8859_1);
        List<RawInfo> employeelist = data.stream()
                .map(arr -> RawInfo.builder()
                        .employeeId(Integer.parseInt(arr[0]))
                        .firstName(arr[1])
                        .lastName(arr[2])
                        .age(Integer.parseInt(arr[3]))
                        .email(arr[4])
                        .gender(arr[5])
                        .jobTitle(arr[6])
                        .department(arr[7])
                        .salaryCurrency(arr[8])
                        .hireDate(LocalDate.parse(arr[9], DateTimeFormatter.ofPattern("M/d/yyyy")))
                        .address(arr[10])
                        .city(arr[11])
                        .state(arr[12])
                        .postalCode(arr[13])
                        .country(arr[14])
                        .phoneNumber(arr[15])
                        .socialSecurityNumber(arr[16])
                        .startTime(arr[17])
                        .endTime(arr[18])
                        .overTime(Integer.parseInt(arr[19]))
                        .vacationDays(Integer.parseInt(arr[20]))
                        .performanceRating(Integer.parseInt(arr[21]))
                        .managerId(Integer.parseInt(arr[22]))
                        .benefitsPackage(arr[23])
                        .trainingCompleted(Boolean.parseBoolean(arr[24]))
                        .workLocation(arr[25])
                        .emergencyContactName(arr[26])
                        .emergencyContactPhone(arr[27])
                        .employeePhoto(arr[28])
                        .build())
                .toList();

        println("Number of employees = " + employeelist.size());
        println("First employee = " + employeelist.getFirst());
        println("Last employee = " + employeelist.getLast());
        employeelist.forEach(System.out::println);
        println("-------CONVERTING TO JSON---------");
        var gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        var json = gson.toJson(employeelist);
        FileUtil.writeToATextFile(json, new File("mock_data.json"), false);
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