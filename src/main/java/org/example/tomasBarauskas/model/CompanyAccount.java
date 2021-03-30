package org.example.tomasBarauskas.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CompanyAccount implements Serializable {
    private BigDecimal companyAccount = new BigDecimal(0);

    public CompanyAccount() {
    }

    public BigDecimal getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(BigDecimal companyAccount) {
        this.companyAccount = companyAccount;
    }

    @Override
    public String toString() {
        return "Imones saskaitos balansas: " + companyAccount;
    }
}
