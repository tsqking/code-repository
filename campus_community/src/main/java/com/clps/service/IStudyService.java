/**
 * Project Name:campus_community
 * File Name:IStudyService.java
 * Package Name:com.clps.service
 * Date:2017年3月23日下午11:36:20
 * Copyright (c) 2017, tsqking@163.com All Rights Reserved.
 *
*/

package com.clps.service;

import java.util.List;

/**
 * ClassName:IStudyService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年3月23日 下午11:36:20 <br/>
 * @author   Charles
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
import com.clps.common.bean.Study;
import com.clps.common.exception.StudyServiceException;
import com.clps.common.util.PageVo;

public interface IStudyService {
	/**
	 * insertStudy:(前台界面发表文章). <br/>
	 * 
	 * @author Charles
	 * @param study
	 * @return
	 * @throws StudyServiceException
	 * @since JDK 1.8
	 */
	boolean insertStudy(Study study) throws StudyServiceException;

	/**
	 * listAllStudy:(前台分页查询所有可用文章). <br/>
	 * 
	 * @author Charles
	 * @param pageVo
	 * @return
	 * @throws StudyServiceException
	 * @since JDK 1.8
	 */
	PageVo<Study> listAllStudy(PageVo<Study> pv) throws StudyServiceException;

	/**
	 * listAllStudyMng:(后台分页查询所有文章). <br/>
	 * 
	 * @author Charles
	 * @param pageVo
	 * @return
	 * @throws StudyServiceException
	 * @since JDK 1.8
	 */
	PageVo<Study> listAllStudyMng(PageVo<Study> pv) throws StudyServiceException;

	/**
	 * updateStudyMng:(后台审核文章). <br/>
	 * 
	 * @author Charles
	 * @param study
	 * @return
	 * @throws StudyServiceException
	 * @since JDK 1.8
	 */
	boolean updateStudyMng(Study study) throws StudyServiceException;

	/**
	 * deleteStudyMng:(后台批量删除文章). <br/>
	 * 
	 * @author Charles
	 * @param idList
	 * @return
	 * @throws StudyServiceException
	 * @since JDK 1.8
	 */
	boolean deleteStudyMng(List<Integer> idList) throws StudyServiceException;

}
