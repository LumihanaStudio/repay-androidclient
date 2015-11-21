package malang.moe.repay.data;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class WelfareRow {
    public String WELFARE_NM, SERVICE_TYPE, ADDRESS, INC_TYPE, TEL, FAX;
    public WelfareRow(String WELFARE_NM, String SERVICE_TYPE, String ADDRESS,   String INC_TYPE, String TEL, String FAX){
        this.WELFARE_NM = WELFARE_NM;
        this.SERVICE_TYPE = SERVICE_TYPE;
        this.ADDRESS = ADDRESS;
        this.INC_TYPE = INC_TYPE;
        this.TEL = TEL;
        this.FAX = FAX;

    }
}
