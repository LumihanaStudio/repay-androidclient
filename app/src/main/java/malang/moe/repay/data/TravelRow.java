package malang.moe.repay.data;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class TravelRow {
    public String LOC_ARE,
            PROVINCIALCAPITAL2,
            PARK_NM,PARK_MANAGE_NUM,PARK_SEPRAT,PROVINCIALCAPITAL_PARCELADDR,
            PROVINCIALCAPITAL_ROADADDR,LA,LO,CONTECT,DATA_STD_DT;
     public TravelRow(String LOC_ARE, String PROVINCIALCAPITAL2, String PARK_NM, String PARK_MANAGE_NUM,
                      String PARK_SEPRAT, String CONTECT, String PROVINCIALCAPITAL_PARCELADDR, String PROVINCIALCAPITAL_ROADADDR,
                      String LA, String LO, String DATA_STD_DT){
         this.LOC_ARE = LOC_ARE;
         this.PROVINCIALCAPITAL2 = PROVINCIALCAPITAL2;
         this.PARK_NM = PARK_NM;
         this.PARK_MANAGE_NUM = PARK_MANAGE_NUM;
         this.PARK_SEPRAT = PARK_SEPRAT;
         this.PROVINCIALCAPITAL_ROADADDR  = PROVINCIALCAPITAL_ROADADDR;
         this.PROVINCIALCAPITAL_PARCELADDR = PROVINCIALCAPITAL_PARCELADDR;
         this.LA = LA;
         this.LO = LO;
         this.DATA_STD_DT = DATA_STD_DT;
         this.CONTECT = CONTECT;
         }
     }
