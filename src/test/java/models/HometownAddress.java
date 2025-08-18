package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HometownAddress {
    private String hometownStreet;
    private String hometownWard;
    private String hometownDistrict;
    private String hometownProvince;
}


