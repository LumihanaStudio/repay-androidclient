package malang.moe.repay.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class MedicalCenter_Response {
    @SerializedName("medical_tour-medical")
    public MedicalCenter medicalCenter;

    public MedicalCenter_Response(MedicalCenter medicalCenter) {
        this.medicalCenter = medicalCenter;
    }
}
