package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerAddress {
    private String employerStreet;
    private String employerWard;
    private String employerDistrict;
    private String employerProvince;
}