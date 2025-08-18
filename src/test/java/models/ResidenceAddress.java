package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResidenceAddress {
    private String residenceStreet;
    private String residenceWard;
    private String residenceDistrict;
    private String residenceProvince;
}