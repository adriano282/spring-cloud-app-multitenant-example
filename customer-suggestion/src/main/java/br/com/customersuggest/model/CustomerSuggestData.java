package br.com.customersuggest.model;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CUSTOMER_SUGGEST_DATA")
@FilterDef(name = "storeFilter", parameters = @ParamDef(name = "storeId", type = "string"))
@Filter(name = "storeFilter", condition = "store_id = :storeId")
public class CustomerSuggestData implements MultiTenant {

    @Id
    @Column(name = "DOCUMENT")
    protected Long document;

    @Column(name = "STORE_ID")
    protected String storeId;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZONE")
    private String zone;

    @Column(name = "QUARTER")
    private String quarter;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPD_DATE")
    private Date updateDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INS_DATE")
    private Date insertDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Long getDocument() { return document; }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
