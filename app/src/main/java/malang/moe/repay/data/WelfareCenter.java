package malang.moe.repay.data;

import java.util.List;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class WelfareCenter {
    public int list_total_count;
    public List<WelfareRow> row;
    public WelfareCenter(int list_total_count, List<WelfareRow> row){
        this.list_total_count = list_total_count;
        this.row = row;
    }
}
