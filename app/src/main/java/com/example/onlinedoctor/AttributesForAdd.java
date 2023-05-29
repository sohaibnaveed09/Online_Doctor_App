package com.example.onlinedoctor;

public class AttributesForAdd {

    String D_Name;
    String D_Exp;
    String D_Qual;

    public AttributesForAdd(String d_Name, String d_Exp, String d_Qual) {
        this.D_Name = d_Name;
        this.D_Exp = d_Exp;
        this.D_Qual = d_Qual;
    }

    public String getD_Name() {
        return D_Name;
    }

    public String getD_Exp() {
        return D_Exp;
    }

    public String getD_Qual() {
        return D_Qual;
    }
}
