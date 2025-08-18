package models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InitLeadRequest {
    // Required
    private String requestId;
    private String productCode;
    private String partnerCode;
    private String telPhoneNumber;

    // Optional
    private String programCode;
    private String fullName;
    private String email;
    private String birthday;
    private String gender;
    private String marriageStatus;
    private String personalIdNo;
    private String issueDate;
    private String issuePlace;
    private String oldPersonalIdNo;
    private HometownAddress hometownAddress;
    private ResidenceAddress residenceAddress;
    private String ipAddress;
    private Device device;
    private String employerStatus;
    private String jobPosition;
    private String employerName;
    private EmployerAddress employerAddress;
    private String incomeAmount;

    // Private constructor
    private InitLeadRequest(Builder builder) {
        this.requestId = builder.requestId;
        this.productCode = builder.productCode;
        this.partnerCode = builder.partnerCode;
        this.telPhoneNumber = builder.telPhoneNumber;
        this.programCode = builder.programCode;
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.birthday = builder.birthday;
        this.gender = builder.gender;
        this.marriageStatus = builder.marriageStatus;
        this.personalIdNo = builder.personalIdNo;
        this.issueDate = builder.issueDate;
        this.issuePlace = builder.issuePlace;
        this.oldPersonalIdNo = builder.oldPersonalIdNo;
        this.hometownAddress = builder.hometownAddress;
        this.residenceAddress = builder.residenceAddress;
        this.ipAddress = builder.ipAddress;
        this.device = builder.device;
        this.employerStatus = builder.employerStatus;
        this.jobPosition = builder.jobPosition;
        this.employerName = builder.employerName;
        this.employerAddress = builder.employerAddress;
        this.incomeAmount = builder.incomeAmount;
    }

    public static class Builder {
        // Required
        private String requestId;
        private String productCode;
        private String partnerCode;
        private String telPhoneNumber;

        // Optional
        private String programCode;
        private String fullName;
        private String email;
        private String birthday;
        private String gender;
        private String marriageStatus;
        private String personalIdNo;
        private String issueDate;
        private String issuePlace;
        private String oldPersonalIdNo;
        private HometownAddress hometownAddress;
        private ResidenceAddress residenceAddress;
        private String ipAddress;
        private Device device;
        private String employerStatus;
        private String jobPosition;
        private String employerName;
        private EmployerAddress employerAddress;
        private String incomeAmount;


        public Builder(String requestId, String productCode, String partnerCode, String telPhoneNumber) {
            this.requestId = requestId;
            this.productCode = productCode;
            this.partnerCode = partnerCode;
            this.telPhoneNumber = telPhoneNumber;
        }

        public Builder programCode(String programCode) {
            this.programCode = programCode;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder birthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder marriageStatus(String marriageStatus) {
            this.marriageStatus = marriageStatus;
            return this;
        }

        public Builder personalIdNo(String personalIdNo) {
            this.personalIdNo = personalIdNo;
            return this;
        }

        public Builder issueDate(String issueDate) {
            this.issueDate = issueDate;
            return this;
        }

        public Builder issuePlace(String issuePlace) {
            this.issuePlace = issuePlace;
            return this;
        }

        public Builder oldPersonalIdNo(String oldPersonalIdNo) {
            this.oldPersonalIdNo = oldPersonalIdNo;
            return this;
        }

        public Builder hometownAddress(HometownAddress hometownAddress) {
            this.hometownAddress = hometownAddress;
            return this;
        }

        public Builder residenceAddress(ResidenceAddress residenceAddress) {
            this.residenceAddress = residenceAddress;
            return this;
        }

        public Builder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public Builder device(Device device) {
            this.device = device;
            return this;
        }

        public Builder employerStatus(String employerStatus) {
            this.employerStatus = employerStatus;
            return this;
        }

        public Builder jobPosition(String jobPosition) {
            this.jobPosition = jobPosition;
            return this;
        }

        public Builder employerName(String employerName) {
            this.employerName = employerName;
            return this;
        }

        public Builder employerAddress(EmployerAddress employerAddress) {
            this.employerAddress = employerAddress;
            return this;
        }

        public Builder incomeAmount(String incomeAmount) {
            this.incomeAmount = incomeAmount;
            return this;
        }

        public InitLeadRequest build() {
            return new InitLeadRequest(this);
        }
    }
}
