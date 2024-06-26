package me.araopj;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class RawInfo {
    private String firstName;
    private String middleName;
    private String lastName;
    private String appel;
    private String posCode;
    private int stationCode;
    private int grade;
    private int step;
    private double basic;
    private double trans;
    private double pera;
    private double rata;
    private double ontop;
    private double longs;
    private double dedLnr;
    private double dedMed;
    private double dedTax;
    private double dedPag;
    private double netPay;
    private double pagCode;
    private int taxCode;
    private String civilStatus;
    private String attend;
    private String status;
    private String sex;
    private LocalDate birthDate;
    private long tin;
    private String gsis_Pin;
    private long gsis_Id;
    private double govtLnr;
    private double govtEcc;
    private double govMed;
    private double govtPag;
    private String upCode;
    private LocalDate upDate;
    private LocalDate payDate;
    private String editor;
    private String payMode;
    private LocalDate hireDate;
    private long hdmf_Id;
    private long phic_Id;
    private int banker;
    private double pib;
    private int regCode;
    private int divCode;
    private int employeeNumber;
    private int account;
    private String fullName;
    private double clothing;
    private double cashFlow;
    private double mid_Bonus;
    private double mid_Cash;
    private double end_Bonus;
    private double end_Cash;
    private double add_Bonus;
    private double taxRef;
    private double special;
    private double over_Wheld;
    private String exposCode;
    private int exGrade;
    private int exStep;
    private double exBasic;
    private double exLnr;
    private double exMed;
    private double exPag;
    private double exTax;
    private double exGlnr;
    private double exGmed;
    private double exGpag;
    private double exGecc;
    private double salDiff;
    private double lnrDiff;
    private double medDiff;
    private double pagDiff;
    private double taxDiff;
    private double glnrDiff;
    private double gmedDiff;
    private double gpagDiff;
    private double geccDiff;
}
