package malang.moe.repay.utils;

import malang.moe.repay.data.ExcerciseVideoResponse;
import malang.moe.repay.data.MedicalCenter_Response;
import malang.moe.repay.data.TravelPark_Response;
import malang.moe.repay.data.WelfareCenter_Response;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by kotohana5706 on 2015. 11. 13..
 */
public interface NetworkService {
    String gangwonApikey = "5674456a706b6f74313139714a636862";
    String googleApikey = DeveloperService.SERVER_DEVELOPER_KEY;

    @GET(gangwonApikey + "/json/cybergt-travel-park/{start}/{end}/")
    Call<TravelPark_Response> getTravelList(@Path("start") int start, @Path("end") int end);

    @GET(gangwonApikey + "/json/gangwon-welfare-_selfsufficiency_center/{start}/{end}/")
    Call<WelfareCenter_Response> getWelfareList(@Path("start") int start, @Path("end") int end);

    @GET(gangwonApikey + "/json/medical_tour-medical/{start}/{end}/")
    Call<MedicalCenter_Response> getMedicalList(@Path("start") int start, @Path("end") int end);

    @GET("/youtube/v3/search?part=snippet&key="+googleApikey+"&type=video&maxResults=20")
    Call<ExcerciseVideoResponse> getExcerciseVideoList(@Query("q") String name, @Query("pageToken") String pgToken);
}
