package jd.com.base;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * BaseVO.java
 * </pre>
 *
 * @ClassName   : BaseVO.java
 * @Description : BaseVO.java
 * @author SMJ
 * @since 2016. 1. 16.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 1. 16.        SMJ                 CREATE
 * </pre>
 */
public class BaseVO {

	/** LOG */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}

	private boolean mobile;

	public boolean isMobile() {
		return mobile;
	}

	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}

	/**
	 * Required Fields - 이 필드들은 페이징 계산을 위해 반드시 입력되어야 하는 필드 값들이다.
	 * 
	 * currentPageNo : 현재 페이지 번호 recordCountPerPage : 한 페이지당 게시되는 게시물 건 수
	 * pageSize : 페이지 리스트에 게시되는 페이지 건수, totalRecordCount : 전체 게시물 건 수.
	 */
	private int visiblePages = 5;
	private int currentPageNo = 1;
	private int recordCountPerPage = 10;
	private int pageSize = 10;
	private int totalRecordCount;

	public int getVisiblePages() {
		return visiblePages;
	}

	public void setVisiblePages(int visiblePages) {
		this.visiblePages = visiblePages;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	/**
	 * Not Required Fields - 이 필드들은 Required Fields 값을 바탕으로 계산해서 정해지는 필드 값이다.
	 * 
	 * totalPageCount: 페이지 개수 firstPageNoOnPageList : 페이지 리스트의 첫 페이지 번호
	 * lastPageNoOnPageList : 페이지 리스트의 마지막 페이지 번호 firstRecordIndex : 페이징 SQL의
	 * 조건절에 사용되는 시작 rownum. lastRecordIndex : 페이징 SQL의 조건절에 사용되는 마지막 rownum.
	 */
	private int totalPageCount;
	private int firstPageNoOnPageList;
	private int lastPageNoOnPageList;
	private int firstRecordIndex;
	private int lastRecordIndex;

	public int getTotalPageCount() {
		totalPageCount = ((getTotalRecordCount() - 1) / getRecordCountPerPage()) + 1;
		return totalPageCount;
	}

	public int getFirstPageNo() {
		return 1;
	}

	public int getLastPageNo() {
		return getTotalPageCount();
	}

	public int getFirstPageNoOnPageList() {
		firstPageNoOnPageList = ((getCurrentPageNo() - 1) / getPageSize()) * getPageSize() + 1;
		return firstPageNoOnPageList;
	}

	public int getLastPageNoOnPageList() {
		lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;
		if (lastPageNoOnPageList > getTotalPageCount()) {
			lastPageNoOnPageList = getTotalPageCount();
		}
		return lastPageNoOnPageList;
	}

	public int getFirstRecordIndex() {
		firstRecordIndex = (getCurrentPageNo() - 1) * getRecordCountPerPage();
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
		return lastRecordIndex;
	}
}