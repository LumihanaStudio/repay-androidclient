package malang.moe.repay.data;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class MedicalRow {
    public String GOV_TYPE, MED_NM, MED_ADDRESS, TEL_NUM, FAX_NUM, HOMEPAGE;
    public MedicalRow(String GOV_TYPE, String MED_NM, String MED_ADDRESS, String TEL_NUM, String FAX_NUM, String HOMEPAGE){
        this.GOV_TYPE = GOV_TYPE;
        this.MED_NM = MED_NM;
        this.MED_ADDRESS = MED_ADDRESS;
        this.TEL_NUM = TEL_NUM;
        this.FAX_NUM = FAX_NUM;
        this.HOMEPAGE = HOMEPAGE;
    }
}
