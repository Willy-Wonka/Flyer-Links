package com.muscleye.will.flyerlinks;

import com.muscleye.will.flyerlinks.model.Store;

import java.util.Comparator;

public class SortByDistance implements Comparator<Store>
{
    @Override
    public int compare(Store itemBean1, Store itemBean2)
    {

        float distance1 = 0;
        float distance2 = 0;

        if(!"".equals(itemBean1.getDistance()))
        {
            distance1 = itemBean1.getDistance();
        }

        if(!"".equals(itemBean2.getDistance()))
        {
            distance2 = itemBean2.getDistance();
        }

        if(distance1 > distance2){
            return 1;
        }
        else if(distance1 < distance2){
            return -1;
        }
        else{
            return 0;
        }
    }
}