import java.util.ArrayList;
import java.util.List;
public class HashMap<K, V> implements Map<K, V>
{
    private List<Map.Node<K, V>>[] list;
    
    public HashMap()
    {
        this(10);
    }
    
    @SuppressWarnings("unchecked")
    public HashMap(int n)
    {
        list = (ArrayList<Map.Node<K,V>>[])new ArrayList[n];
        //populate the array with Lists
        for(int i = 0; i < list.length; i++)
        {
            list[i] = new ArrayList<Map.Node<K,V>>();
        }
    }
    private int getIndex(Object key)
    {
        int index = key.hashCode();
        index = Math.abs(index);
        index = index%list.length;
        return index;
    }
    public boolean containsKey(Object key)
    {
        for(int i=0;i<list.length;i++)
        {
            ArrayList<Map.Node<K,V>> nodeList = (ArrayList)list[i];
            for(int j=0;j<nodeList.size();j++)
            {
                Map.Node<K,V> node = nodeList.get(j);
                if(node.getKey().equals(key))
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean containsValue(Object value)
    {
        for(int i=0;i<list.length;i++)
        {
            ArrayList<Map.Node<K,V>> nodeList = (ArrayList)list[i];
            for(int j=0;j<nodeList.size();j++)
            {
                Map.Node<K,V> node = nodeList.get(j);
                if(node.getValue().equals(value))
                {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isEmpty()
    {
        for(int i = 0;i<list.length;i++)
        {
            ArrayList<Map.Node<K,V>> nodeList = (ArrayList)list[i];
            if(nodeList.size()!=0)
            {
                return false;
            }
        }
        return true;
    }
    public int size()
    {
        int ret = 0;
        for(int i = 0;i<list.length;i++)
        {
            List<Map.Node<K,V>> nodeList = list[i];
            ret+=nodeList.size();
        }
        return ret;
    }
    public V get(Object key)
    {
        int index = getIndex(key);
        List<Map.Node<K,V>> nodes = list[index];
        for(Map.Node<K,V> node: nodes)
        {
            if(node.getKey().equals(key))
            {
                return node.getValue();
            }
        }
        return null;
    }
    
    /*
     *  Associate the specified value with the specified key
     *  If the map already contains the specified key, the replace 
     *  the value of that key and return the original value.
     *  Otherwise add a new Node to the map and return null.
     *  Precondition: key != null && value != null
     */
    public V put(K key, V value)
    {
        int index = getIndex(key);
        List<Map.Node<K,V>> nodes = list[index];
        for(Map.Node<K,V> node: nodes)
        {
            if(node.getKey().equals(key))
            {
                V ret = node.getValue();
                node.setValue(value); 
                return ret;
            }
        }
        nodes.add(new Map.Node<K,V>(key,value));
        return null;
    }
    
    /*
     *  Remove the association for the specified key from this map.
     *  Return the value that was associated with the specified key
     *  Or return null if no value was associated with the key.
     *  Precondition: key != null
     */
    public V remove(Object key)
    {
        int index = getIndex(key);
        List<Map.Node<K,V>> nodes = list[index];
        V ret = null;
        for(Map.Node<K,V> node: nodes)
        {
            if(node.getKey().equals(key))
            {
                ret = node.getValue();
                nodes.remove(node);
                return ret;
            }
        }
        return ret;
    }
    
    /*
     *  Return a List<V> of the values that are stored in this map
     */
    public List<V> values()
    {
        List<V> ret = new ArrayList<V>();
        for(List<Map.Node<K,V>> nodes: list)
        {
            //hi
            for(Map.Node<K,V> node: nodes)
            {
                ret.add(node.getValue());
            }
        }
        return ret;
    }
    public Set<K> keySet()
    {
        Set<K> ret = new ListSet<K>();
        for(List<Map.Node<K,V>> nodes: list)
        {
            for(Map.Node<K,V> node: nodes)
            {
                ret.add(node.getKey());
            }
        }
        return ret;
    }
}
