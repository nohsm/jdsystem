package jd.web.com.dao;

import java.util.List;

import jd.com.base.BaseDAO;
import jd.web.com.vo.CommonVO;

import org.springframework.stereotype.Repository;

/**
 * <pre>
 * CommonDAO.java
 * </pre>
 *
 * @ClassName   : CommonDAO.java
 * @Description : CommonDAO.java
 * @author SMJ
 * @since 2016. 1. 14.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2016. 1. 14.        SMJ                 CREATE
 * </pre>
 */
@Repository("commonDAO")
public class CommonDAO extends BaseDAO {

	/**
	 * insert
	 *
	 * @param vo
	 * @return String
	 * @throws Exception
	 */
	public String insert(CommonVO vo) throws Exception {
		return (String) insertSql("commonDAO.insert", vo);
	}

	/**
	 * list
	 *
	 * @param paramVO
	 * @return List<CommonVO>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CommonVO> list(CommonVO paramVO) throws Exception {
		return (List<CommonVO>) listSql("commonDAO.list", paramVO);
	}

	/**
	 * count
	 *
	 * @param paramVO
	 * @return int
	 * @throws Exception
	 */
	public int count(CommonVO paramVO) throws Exception {
		return (Integer) selectCountSql("commonDAO.count", paramVO);
	}
}