package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private String userAgent;
    private String country;
    private String city;
    private String typeOfDevice;
    private String operatingSystem;
    private String browserName;
}
