package songm.account.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟加载管理器
 * 
 * @author 张松
 * 
 */
public class LazyLoaderManager implements java.io.Serializable {

	private static final long serialVersionUID = -6680304602595858307L;
	
	private Map<Class<?>, LazyLoader<?>> loadersMap = new HashMap<Class<?>, LazyLoader<?>>();

	public <T> void addEntityLoader(LazyLoader<T> lazyLoader) {
		loadersMap.put(lazyLoader.getType(), lazyLoader);
	}

	@SuppressWarnings("unchecked")
	public <T> LazyLoader<T> getEntityLoader(Class<T> m) {
		return (LazyLoader<T>) loadersMap.get(m);
	}
}
