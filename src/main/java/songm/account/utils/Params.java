package songm.account.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Params {

	private Map<String, Object> param = new HashMap<String, Object>();

	public void add(String key, Object value) {
		param.put(key, value);
	}

	@Override
	public String toString() {
		StringBuffer params = new StringBuffer("Params ");
		params.append("[");
		Set<String> keySet = param.keySet();
		Iterator<String> its = keySet.iterator();
		while (its.hasNext()) {
			String key = its.next();
			Object value = param.get(key);
			params.append(key + "=" + value + ", ");
		}
		params.append("]");
		return params.toString();
	}

}
