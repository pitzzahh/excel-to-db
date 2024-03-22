package me.araopj;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class Salary {
    private int salaryGrade;
    private String stepName;
    private double stepAmount;
}
