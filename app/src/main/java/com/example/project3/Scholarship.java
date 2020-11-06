package com.example.project3;

public class Scholarship {

    int scholarID; //장학금 번호
    String scholarGroup;//구분(교내,교외)
    String scholarAgency;//운영기관명
    String scholarType;//장학금유형
    String scholarName;//장학명
    String scholarUniv;//대학구분
    String scholarGrade;//학과
    String scholarBenefit;//장학혜택
    String scholarStandard;//성적기준
    String scholarLink;//링크

    public int getScholarID() {
        return scholarID;
    }

    public void setScholarID(int scholarID) {
        this.scholarID = scholarID;
    }

    public String getScholarGroup() {
        return scholarGroup;
    }

    public void setScholarGroup(String scholarGroup) {
        this.scholarGroup = scholarGroup;
    }

    public String getScholarAgency() {
        return scholarAgency;
    }

    public void setScholarAgency(String scholarAgency) {
        this.scholarAgency = scholarAgency;
    }

    public String getScholarType() {
        return scholarType;
    }

    public void setScholarType(String scholarType) {
        this.scholarType = scholarType;
    }

    public String getScholarName() {
        return scholarName;
    }

    public void setScholarName(String scholarName) {
        this.scholarName = scholarName;
    }

    public String getScholarUniv() {
        return scholarUniv;
    }

    public void setScholarUniv(String scholarUniv) {
        this.scholarUniv = scholarUniv;
    }

    public String getScholarGrade() {
        return scholarGrade;
    }

    public void setScholarGrade(String scholarGrade) {
        this.scholarGrade = scholarGrade;
    }

    public String getScholarBenefit() {
        return scholarBenefit;
    }

    public void setScholarBenefit(String scholarBenefit) {
        this.scholarBenefit = scholarBenefit;
    }

    public String getScholarStandard() {
        return scholarStandard;
    }

    public void setScholarStandard(String scholarStandard) {
        this.scholarStandard = scholarStandard;
    }

    public String getScholarLink() {
        return scholarLink;
    }

    public void setScholarLink(String scholarLink) {
        this.scholarLink = scholarLink;
    }

    public Scholarship(int scholarID, String scholarGroup, String scholarAgency, String scholarType, String scholarName, String scholarUniv, String scholarGrade, String scholarBenefit, String scholarStandard, String scholarLink) {
        this.scholarID = scholarID;
        this.scholarGroup = scholarGroup;
        this.scholarAgency = scholarAgency;
        this.scholarType = scholarType;
        this.scholarName = scholarName;
        this.scholarUniv = scholarUniv;
        this.scholarGrade = scholarGrade;
        this.scholarBenefit = scholarBenefit;
        this.scholarStandard = scholarStandard;
        this.scholarLink = scholarLink;
    }
}
