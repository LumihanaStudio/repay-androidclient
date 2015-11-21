package malang.moe.repay.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kotohana5706 on 2015. 11. 14..
 */
public class TravelPark_Response {
    @SerializedName("cybergt-travel-park")
    public TravelPark travelPark;
    public TravelPark_Response(TravelPark travelPark){
        this.travelPark = travelPark;
    }
}
