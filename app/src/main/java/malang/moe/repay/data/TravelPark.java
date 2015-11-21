package malang.moe.repay.data;

import java.util.List;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class TravelPark {
    public int list_total_count;
    public List<TravelRow> row;
    public TravelPark (int list_total_count, List<TravelRow> row){
        this.list_total_count = list_total_count;
        this.row = row;
    }
}
