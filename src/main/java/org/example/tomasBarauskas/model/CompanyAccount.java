package org.example.tomasBarauskas.model;

import org.example.tomasBarauskas.util.json.FileJsonRW;

import java.io.Serializable;
import java.math.BigDecimal;

public class CompanyAccount implements Serializable {
    private FileJsonRW jsonRW = new FileJsonRW();
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