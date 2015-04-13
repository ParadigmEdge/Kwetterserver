package kwetter.utils;

import java.util.HashSet;

/**
 *
 * @author unknown
 * Set that converts all the added values to lowercase to make it case insensitive
 */
public class CaseInsensitiveSet extends HashSet<String>
{
    @Override
    public boolean add(String value){
        return super.add(value.toLowerCase());
    }
}
