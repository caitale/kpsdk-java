package org.scribe.utils;

import java.util.*;

/**
 * Utils for {@link Map} manipulation
 * 
 * @author Pablo Fernandez
 */
public class MapUtils
{
  /**
   * Sorts a Map
   * 
   * @param map unsorted map
   * @return sorted map
   */
  public static final Map<String, String> sort(Map<String, String> map)
  {
    Preconditions.checkNotNull(map, "Cannot sort a null object.");

    Map<String, String> sorted = new LinkedHashMap<String, String>();
    for (String key : getSortedKeys(map))
    {
      sorted.put(key, map.get(key));
    }
    return sorted;
  }

  private static List<String> getSortedKeys(Map<String, String> map)
  {
    List<String> keys = new ArrayList<String>(map.keySet());
    Collections.sort(keys);
    return keys;
  }

  /**
   * Form-urlDecodes and appends all keys from the source {@link Map} to the
   * target {@link Map}
   *
   * @param source Map from where the keys get copied and decoded
   * @param target Map where the decoded keys are copied to
   */
  public static void decodeAndAppendEntries(Map<String, String> source, Map<String, String> target)
  {
    for (String key: source.keySet())
    {
      target.put(URLUtils.percentEncode(key), URLUtils.percentEncode(source.get(key)));
    }
  }
}
