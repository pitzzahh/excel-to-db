package me.araopj;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Salary {
    private int salaryGrade;
    private int step;
    private double amount;
    private int year;
    private int tranche;
}
