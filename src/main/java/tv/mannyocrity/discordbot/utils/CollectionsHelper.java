package tv.mannyocrity.discordbot.utils;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for helping with Java Collections classes.
 */
public final class CollectionsHelper {

    /**
     * Utility class does not need a public constructor.
     */
    private CollectionsHelper() {
    }

    /**
     * Returns a list of keys from a LInkedHashMap in the same order the keys were added to map.
     *
     * @param map - LinkedHashMap to retrieve keys form.
     * @return - List of keys.
     */
    public static List<String> getKeysFromMap(final Map<String, DayOfWeek> map) {
        List<String> days = new ArrayList<>();
        Map<String, DayOfWeek> mapCopy = new LinkedHashMap<>(map);

        Iterator it = mapCopy.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            days.add((String) pair.getKey());
            it.remove(); // avoids a ConcurrentModificationException
        }
        return days;
    }

}
