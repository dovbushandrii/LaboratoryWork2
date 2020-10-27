package appPack;

import java.util.ArrayList;

/**
 * Sorter class for ArrayList<SingleNote>
 * sort.
 */
public class Sorter {

    /**
     * Sort @list by time of last update.
     *
     * @param list - list that needs to be sorted
     * @return - returns sorted list.
     */
    public static ArrayList<SingleNote> sortByDate(ArrayList<SingleNote> list) {
        SingleNote temp;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 1; j < (list.size() - i); j++) {
                if (list.get(j - 1).getLocalDateTime().isBefore(list.get(j).getLocalDateTime())) {
                    temp = list.get(j - 1);
                    list.set(j - 1, list.get(j));
                    list.set(j, temp);
                }

            }
        }
        return list;
    }
}
