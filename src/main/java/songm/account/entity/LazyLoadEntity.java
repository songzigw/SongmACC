package songm.account.entity;



/**
 * 实体延迟加载
 * 
 * @author 张松
 * 
 */
public abstract class LazyLoadEntity extends Entity {

	private static final long serialVersionUID = -3954368739089900341L;

	/** 延迟加载管理器 */
	private LazyLoaderManager lazyLoaderManager;

	public LazyLoaderManager getLazyLoaderManager() {
		return lazyLoaderManager;
	}

	public void setLazyLoaderManager(LazyLoaderManager lazyLoaderManager) {
		this.lazyLoaderManager = lazyLoaderManager;
	}

	/**
	 * 实体类的初始化
	 */
	public abstract void init();
}
