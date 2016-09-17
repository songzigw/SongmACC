package songm.account.entity;

/**
 * 延迟加载
 * 
 * @author 张松
 * 
 * @param <T>
 */
public abstract class LazyLoader<T> implements java.io.Serializable {

	private static final long serialVersionUID = -5660391207375880774L;

	public abstract T execute(Object obj);

	public abstract Class<T> getType();
}
