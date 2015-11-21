package malang.moe.repay.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class WelfareCenter_Response {
    @SerializedName("gangwon-welfare-_selfsufficiency_center")
    public WelfareCenter welfareCenter;
    public WelfareCenter_Response(WelfareCenter welfareCenter){
        this.welfareCenter = welfareCenter;
    }
}
