package songm.account.entity;

import java.util.List;

public class EntityUtil {

	/**
	 * 延迟加载管理器
	 * 
	 * @param lleList
	 * @param llm
	 */
	public static <T> void resetLazyLoaderManager(List<T> lleList,
			LazyLoaderManager llm) {
		if (lleList == null) return;
		for (T o : lleList) {
			LazyLoadEntity lle = (LazyLoadEntity)o;
			lle.setLazyLoaderManager(llm);
		}
	}

	/**
	 * 延迟加载管理器
	 * 
	 * @param lleList
	 * @param llm
	 */
	public static void resetLazyLoaderManager(LazyLoadEntity lle,
			LazyLoaderManager llm) {
		if (lle == null) return;
		lle.setLazyLoaderManager(llm);
	}
}
