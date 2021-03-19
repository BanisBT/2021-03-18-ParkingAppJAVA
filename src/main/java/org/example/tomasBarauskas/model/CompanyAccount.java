package org.example.tomasBarauskas.model;

import java.math.BigDecimal;

public class CompanyAccount {
    private BigDecimal companyAccount = new BigDecimal(0);

    public CompanyAccount() {
    }

    @Override
    public String toString() {
        return "CompanyAccount{" +
                "companyAccount=" + companyAccount +
                '}';
    }

    public BigDecimal getCompanyAccount() {
        return companyAccount;
    }

    public void setCompanyAccount(BigDecimal companyAccount) {
        this.companyAccount = companyAccount;
    }
}
