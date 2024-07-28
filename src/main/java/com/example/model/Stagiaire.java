package com.example.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("STAGIAIRE")
public class Stagiaire extends Personne {
    private String cne;

    @Lob
    private byte[] assuranceData;

    @Lob
    private byte[] cvData;

    @Lob
    private byte[] attestationData;

    // Getters and Setters
    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public byte[] getAssuranceData() {
        return assuranceData;
    }

    public void setAssuranceData(byte[] assuranceData) {
        this.assuranceData = assuranceData;
    }

    public byte[] getCvData() {
        return cvData;
    }

    public void setCvData(byte[] cvData) {
        this.cvData = cvData;
    }

    public byte[] getAttestationData() {
        return attestationData;
    }

    public void setAttestationData(byte[] attestationData) {
        this.attestationData = attestationData;
    }
}
