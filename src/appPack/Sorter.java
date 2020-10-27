package appPack;

import java.util.ArrayList;


public class Sorter {
    public static ArrayList<SingleNote> sortByDate(ArrayList<SingleNote> list){
        SingleNote temp;
        for(int i=0; i < list.size(); i++){
            for(int j=1; j < (list.size() - i); j++){
                if(list.get(j-1).getLocaldatetime().isBefore(list.get(j).getLocaldatetime())){
                    temp = list.get(j-1);
                    list.set(j-1,list.get(j));
                    list.set(j,temp);
                }

            }
        }
        return list;
    }
}
