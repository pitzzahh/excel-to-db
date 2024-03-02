package me.araopj;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RawInfo {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String gender;
    private String jobTitle;
    private String department;
    private String salaryCurrency;
    private Date hireDate;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String socialSecurityNumber;
    private String startTime;
    private String endTime;
    private int overTime;
    private int vacationDays;
    private int performanceRating;
    private int managerId;
    private String benefitsPackage;
    private boolean trainingCompleted;
    private String workLocation;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String employeePhoto;
}
