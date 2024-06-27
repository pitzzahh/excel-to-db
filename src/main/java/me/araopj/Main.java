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
import java.util.Date;

import static io.github.pitzzahh.util.utilities.Print.println;

public class Main {
    public static void main(String[] args) {
        final var trance = new HashMap<Integer, Integer>();

        Date date = new Date();
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("MM/dd/yyyy");
        //String fDate = formatter.format(date);

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
                        List<String[]> rawData = getFileContents(file, 1, ':', StandardCharsets.ISO_8859_1);

                        // Convert the raw data to a list of salaries
                        // A Salary can have multiple steps
                        // We store the salaries based on their salary grade
                        // Each salary grade has multiple steps, so we store them in a list
                        List<RawInfo> salaries = rawData.stream()
                                .map(arr -> {
                                    System.out.println("arr = " + Arrays.toString(arr));
                                    return RawInfo.builder()
                                            .firstName(arr[0])
                                            .middleName(arr[1])
                                            .lastName(arr[2])
                                            .appel(arr[3])
                                            .posCode(arr[4])
                                            .stationCode(arr[5] == null ? 0 : Integer.parseInt(arr[5]))
                                            .grade(arr[6] == null ? 0 : Integer.parseInt(arr[6]))
                                            .step(arr[7] == null ? 0 : Integer.parseInt(arr[7]))
                                            .basic(arr[8] == null ? 0.00 : convertToDouble(arr[8]))
                                            .trans(arr[9] == null ? 0.00 : convertToDouble(arr[9]))
                                            .pera(arr[10] == null ? 0.00 : convertToDouble(arr[10]))
                                            .aca(arr[11] == null ? 0.00 : convertToDouble(arr[11]))
                                            .rata(arr[12] == null ? 0.00 : convertToDouble(arr[12]))
                                            .ontop(arr[13] == null ? 0.00 : convertToDouble(arr[13]))
                                            .longs(arr[14] == null ? 0.00 : convertToDouble(arr[14]))
                                            .dedLnr(arr[15] == null ? 0.00 : convertToDouble(arr[15]))
                                            .dedMed(arr[16] == null ? 0.00 : convertToDouble(arr[16]))
                                            .dedTax(arr[17] == null ? 0.00 : convertToDouble(arr[17]))
                                            .dedPag(arr[18] == null ? 0.00 : convertToDouble(arr[18]))
                                            .netPay(arr[19] == null ? 0.00 : convertToDouble(arr[19]))
                                            .pagCode(arr[20] == null ? 0 : Integer.parseInt(arr[20]))
                                            .taxCode(arr[21] == null ? 0 : Integer.parseInt(arr[21]))
                                            .civilStatus(arr[22])
                                            .attend(arr[23])
                                            .status(arr[24])
                                            .sex(arr[25])
                                            .birthDate(arr[26] == null ? null : convertToLocalDate(arr[26]))
                                            .tin(arr[27] == null ? 0L : convertToLong(arr[27]))
                                            .gsis_Pn(arr[28])
                                            .gsis_Id(arr[29] == null  ? 0L : Long.parseLong(arr[29])) //long
                                            .govtLnr(arr[30] == null ? 0.00 : convertToDouble(arr[30]))
                                            .govtEcc(arr[31] == null ? 0.00 : convertToDouble(arr[31]))
                                            .govMed(arr[32] == null ? 0.00 : convertToDouble(arr[32]))
                                            .govtPag(arr[33] == null ? 0.00 : convertToDouble(arr[33]))
                                            .upCode(arr[34])
                                            .upDate(arr[35] == null ? null : convertToLocalDate(arr[26]))
                                            .payDate(arr[36] == null ? null : convertToLocalDate(arr[26]))
                                            .editor(arr[37])
                                            .payMode(arr[38])
                                            .hireDate(arr[39] == null ? null : convertToLocalDate(arr[26]))
                                            .hdmf_Id(arr[40] == null  ? 0L : Long.parseLong(arr[40])) //long
                                            .phic_Id(arr[41] == null ? 0L : convertToLong(arr[41])) //long
                                            .banker(arr[42] == null ? 0 : Integer.parseInt(arr[42]))
                                            .pib(arr[43] == null ? 0.00 : convertToDouble(arr[43]))
                                            .regCode(arr[44] == null ? 0 : Integer.parseInt(arr[44]))
                                            .divCode(arr[45] == null ? 0 : Integer.parseInt(arr[45]))
                                            .employeeNumber(arr[46] == null ? 0 : Integer.parseInt(arr[46]))  //It should be long
                                            .account(arr[47] == null ? 0 : convertToLong(arr[47]))
                                            .fullName(arr[48])
                                            .clothing(arr[49] == null ? 0.00 : convertToDouble(arr[49]))
                                            .cashFlow(arr[50] == null ? 0.00 : convertToDouble(arr[50]))
                                            .mid_Bonus(arr[51] == null ? 0.00 : convertToDouble(arr[51]))
                                            .mid_Cash(arr[52] == null ? 0.00 : convertToDouble(arr[52]))
                                            .end_Bonus(arr[53] == null ? 0.00 : convertToDouble(arr[53]))
                                            .end_Cash(arr[54] == null ? 0.00 : convertToDouble(arr[54]))
                                            .add_Bonus(arr[55] == null ? 0.00 : convertToDouble(arr[55]))
                                            .taxRef(arr[56] == null ? 0.00 : convertToDouble(arr[56]))
                                            .special(arr[57] == null ? 0.00 : convertToDouble(arr[57]))
                                            .over_Wheld(arr[58] == null ? 0.00 : convertToDouble(arr[58]))
                                            .exposCode(arr[59])
                                            .exGrade(arr[60] == null ? 0 : Integer.parseInt(arr[60]))
                                            .exStep(arr[61] == null ? 0 : Integer.parseInt(arr[61]))
                                            .exBasic(arr[62] == null ? 0.00 : convertToDouble(arr[62]))
                                            .exLnr(arr[63] == null ? 0.00 : convertToDouble(arr[63]))
                                            .exMed(arr[64] == null ? 0.00 : convertToDouble(arr[64]))
                                            .exPag(arr[65] == null ? 0.00 : convertToDouble(arr[65]))
                                            .exTax(arr[66] == null ? 0.00 : convertToDouble(arr[66]))
                                            .exGlnr(arr[67] == null ? 0.00 : convertToDouble(arr[67]))
                                            .exGmed(arr[68] == null ? 0.00 : convertToDouble(arr[68]))
                                            .exGpag(arr[69] == null ? 0.00 : convertToDouble(arr[69]))
                                            .exGecc(arr[70] == null ? 0.00 : convertToDouble(arr[70]))
                                            .salDiff(arr[71] == null ? 0.00 : convertToDouble(arr[71]))
                                            .lnrDiff(arr[72] == null ? 0.00 : convertToDouble(arr[72]))
                                            .medDiff(arr[73] == null ? 0.00 : convertToDouble(arr[73]))
                                            .pagDiff(arr[74] == null ? 0.00 : convertToDouble(arr[74]))
                                            .taxDiff(arr[75] == null ? 0.00 : convertToDouble(arr[75]))
                                            .glnrDiff(arr[76] == null ? 0.00 : convertToDouble(arr[76]))
                                            .gmedDiff(arr[77] == null ? 0.00 : convertToDouble(arr[77]))
                                            .gpagDiff(arr[78] == null ? 0.00 : convertToDouble(arr[78]))
                                            .geccDiff(arr[79] == null ? 0.00 : convertToDouble(arr[79]))

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
        return Double.parseDouble(numberString.replace(",", ""));
    }

    public static LocalDate convertToLocalDate(String date) {

        String[] dateParts = date.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);
        return LocalDate.of(year,month,day);
    }

    public static long convertToLong(String numberString) {
        if(numberString.contains("-")) {
            return Long.parseLong(numberString.replace("-", ""));
        }
        return Long.parseLong(numberString);
    }
}