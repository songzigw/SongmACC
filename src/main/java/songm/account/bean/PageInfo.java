package songm.account.bean;

import java.util.List;

/**
 * 分页信息
 * 
 * @author 张松
 * 
 */
public class PageInfo<T> extends Entity {

    private static final long serialVersionUID = 7422828225644996987L;

    /** 当前页码 */
	public static final String CURRPAGE = "curr_page";

	/** 每页显示数 */
	public static final String PAGESIZE = "page_size";
	
	/** 当前页码 */
	private int currPage;

	/** 页尺寸（显示数量） */
	private int pageSize;

	/** 数据总数 */
	private int totalNum;

	/** 总页数 */
	private int totalPage;

	/** 结果集 */
	private List<T> items;

	public PageInfo(int currPage, int pageSize, int totalNum) {
		if (pageSize <= 0) {
			this.pageSize = 1;
		} else {
			this.pageSize = pageSize;
		}
		this.totalNum = totalNum;
		// 计算总页数
		this.totalPage = countTotalPage();

		this.currPage = currPage;
		if (this.currPage < 1) {
			this.currPage = 1;
		} else if (this.currPage > totalPage) {
			this.currPage = totalPage;
		}
	}

	private int countTotalPage() {
		int n = 0;
		if (totalNum % pageSize == 0) {
			n = totalNum / pageSize;
		} else {
			n = totalNum / pageSize + 1;
		}
		return n == 0 ? 1 : n;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public List<T> getItems() {
		return items;
	}

	public int getCurrPage() {
		return currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 获取数据起始序列（偏移量）
	 * 
	 * @param currPage
	 *            当前页码
	 * @param pageSize
	 *            页尺寸（没有显示数）
	 * @return
	 */
	public static int getSkips(int currPage, int pageSize) {
		return (pageSize * currPage) - pageSize;
	}

	public static int parseCurrPage(String currPage) {
		if (currPage == null || currPage.equals("")) {
			return 1;
		}
		try {
			return Integer.parseInt(currPage);
		} catch (Exception e) {
			return 1;
		}
	}

	public static int parsePageSize(String pageSize) {
		if (pageSize == null || pageSize.equals("")) {
			return 10;
		}
		try {
			return Integer.parseInt(pageSize);
		} catch (Exception e) {
			return 10;
		}
	}
}
