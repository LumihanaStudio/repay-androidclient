package malang.moe.repay.data;

/**
 * Created by kotohana5706 on 2015. 11. 29..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class MedicalCenterData {
    public String name;
    public String address;
    public String type;
    public String call_num;
    public String homepage_url;
    public MedicalCenterData(String name, String address, String type, String call_num, String homepage_url){
        this.name = name;
        this.address = address;
        this.type  = type;
        this.call_num = call_num;
        this.homepage_url = homepage_url;
    }
}
