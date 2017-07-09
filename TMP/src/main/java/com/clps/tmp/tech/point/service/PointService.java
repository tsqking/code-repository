package com.clps.tmp.tech.point.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clps.tmp.common.vo.FileUploadInfoVo;
import com.clps.tmp.common.vo.PageVo;
import com.clps.tmp.core.sm.vo.SelectVo;
import com.clps.tmp.tech.point.vo.DetailVo;
import com.clps.tmp.tech.point.vo.PointVo;

/**
 * @author Seven
 *
 * 2015年11月3日
 */
public interface PointService {
	/**
	 * 获取知识点分页信息
	 */
	PageVo<PointVo> queryPointPage(PageVo<PointVo> page,String userId,String userRole) throws Exception;
	/**
	 * 添加知识点
	 * <br>添加知识点-技能 mapping
	 * <br>返回新加的知识点id号
	 */
	public HashMap<String,Object> addPoint(PointVo point) throws Exception;
	/**
	 * 添加知识点详细内容
	 */
	public int addPointDetail(DetailVo detail) throws Exception;
	/**
	 * 根据ID修改教材资料文件信息<br>返回MAP 携带更新时间update_time 更新时间update_person 影响记录数 effect
	 */
	public Map<String, Object> updateMaterialInfoByID(String pointId,String userName,FileUploadInfoVo fileVo) throws Exception;
	/**
	 * 根据ID修改教师手册信息<br>返回MAP 携带更新时间update_time 更新时间update_person 影响记录数 effect
	 */
	public Map<String, Object> updateTHandBookInfoByID(String pointId,String userName,FileUploadInfoVo fileVo) throws Exception;
	/**
	 * 根据ID修改学员手册信息<br>返回MAP 携带更新时间update_time 更新时间update_person 影响记录数 effect
	 */
	public Map<String, Object> updateSHandBookInfoByID(String pointId,String userName,FileUploadInfoVo fileVo) throws Exception;
	/**
	 * 根据ID修改参考资料信息<br>返回MAP 携带更新时间update_time 更新时间update_person 影响记录数 effect
	 */
	public Map<String, Object> updateReferenceInfoByID(String pointId,String userName,FileUploadInfoVo fileVo) throws Exception;
	/**
	 * 根据ID查询知识点
	 */
	public PointVo getPointInfoByID(String pointId) throws Exception;
	/**
	 * 根据ID查询知识点详细
	 */
	public DetailVo getDetailInfoByID(String pointId) throws Exception;
	/**
	 * 更新知识点信息
	 */
	public int updatePointInfo(PointVo pointVo) throws Exception;
	/**
	 * 更新知识点详细信息
	 */
	public int updateDetailInfo(DetailVo detailVo) throws Exception;
	/**
	 * 删除知识点<br>
	 * 返回Map <br>
	 * 	code -'0-删除成功/1-不能删除，有课程用/2-不能删除，有教学计划用/3-不能删除，课程和计划都在用/4-不能删除，除课程教学之外，其他的关联' <br>
	 *  delete='0'时 deleteEffect-'删除影响记录数'<br> 
	 *  delete='1'时 inUseCourse-'courseNo-courseName' <br> 
	 *  delete='2'时 inUsePlan-'courseNo-courseName' <br> 
	 *  delete='3'时 inUseCourse & inUsePlan  <br> 
	 *  delete='4'时 <br>
	 */
	public Map<String, Object> deletePoint(String pointId) throws Exception;
	/**
	 * 复制知识点
	 */
	public int copyPoint(PointVo point, DetailVo sourceDetail) throws Exception;
	/**
	 * 根据所给的技能id获取其下的所有知识点下拉框
	 */
	public List<SelectVo> getPointOptionBySkillIds(String firstSkillId, String secondSkillId, String thirdSkillId) throws Exception;
	/**
	 * 授权管理知识点
	 * 2016年2月2日 Seven
	 */
	public void grantManageToTeacher(String teacherId,String pointId,String manage)throws Exception;
	/**
	 * 获取下载的知识点数据
	 * 2016年4月8日 Seven
	 */
	List<Map<String, Object>> getTopicData(Map<String, Object> paramMap)  throws Exception ;
}
