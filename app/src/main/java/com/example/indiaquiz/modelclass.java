

        package com.example.indiaquiz;

public class modelclass {
    String question;
    String OA;
    String OB;
    String OC;
    String OD;
    String ans;

    public modelclass(String question, String OA, String OB, String OC, String OD, String ans) {
        this.question = question;
        this.OA = OA;
        this.OB = OB;
        this.OC = OC;
        this.OD = OD;
        this.ans = ans;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOA() {
        return OA;
    }

    public void setOA(String OA) {
        this.OA = OA;
    }

    public String getOB() {
        return OB;
    }

    public void setOB(String OB) {
        this.OB = OB;
    }

    public String getOC() {
        return OC;
    }

    public void setOC(String OC) {
        this.OC = OC;
    }

    public String getOD() {
        return OD;
    }

    public void setOD(String OD) {
        this.OD = OD;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
