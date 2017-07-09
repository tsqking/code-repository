<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../../common.jsp"></s:include>
<!-- 页面js -->
<script src="${pageContext.request.contextPath}/campusRecruit/other/talentPool/viewTalent.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/campusRecruit/other/talentPool/${system_lang}.js"></script>
<title>查看人才</title>
</head>
<body>
	<section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
		<div class="box">
			<div class="box-body">
				<form id="addForm" method="post">
					<div style="display:none">
						<input type="hidden" id="id" name="id" class="form-control" value="${talentVo.id}" placeholder="">
					</div>
					<div class="row">
						<div class="col-md-8 col-xs-8">
							<div class="row">
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="name"><!-- 人才姓名 --><s:text name="tal.name"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-user"></li></span>
											<input type="text" id="name" name="name" class="form-control" value="${talentVo.name}" placeholder="" readonly>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="spell_name"><!-- 姓名拼音 --><s:text name="tal.spell_name"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-user"></li></span>
											<input type="text" id="spell_name" name="spell_name" class="form-control" value="${talentVo.spell_name }" placeholder="" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="gender"><!-- 人才性别 --><s:text name="tal.gender"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-transgender"></li></span>
											<input type="text" id="gender" name="gender" class="form-control" value="${talentVo.gender }" placeholder="" readonly>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="age"><!-- 人才年龄 --><s:text name="tal.age"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-clock-o"></li></span>
											<input type="text" id="age" name="age" class="form-control" value="${talentVo.age }" placeholder="" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="birthday"><!-- 出生日期 --><s:text name="tal.birthday"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-birthday-cake"></li></span>
											 <input type="text" id="birthday" name="birthday" class="form-control" value="${talentVo.birthday }" readonly>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="recruit_state"><!-- 招聘状态 --><s:text name="tal.rcState"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-history"></li></span>
											 <input type="text" id="recruit_state" name="recruit_state" class="form-control" value="${talentVo.recruit_state }" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 col-xs-12">
									<div class="form-group">
										<label for="state_comment"><!-- 状态说明 --><s:text name="tal.state_comment"/></label>
										<textarea class="form-control" id="state_comment" name="state_comment" rows="2"  readonly
					                				placeholder="Recruit State Comments ...">${talentVo.state_comment }</textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4 col-xs-4">
							<div class="form-group">
								<label><!-- 人才头像 --><s:text name="tal.photo"/></label>
								<div>
									<div style="text-align:center;width:80%;margin-left: auto;margin-right: auto;">
										<img id="photo" class="attachment-img" 
											style="width: 130px;height: 130px;margin-top: 5%;;border:0px solid #88C4F7;" 
											src="../campusRC/talent!readTalentPhoto.do?id=${talentVo.id }"
											onerror="this.src='${pageContext.request.contextPath}/common/image/user2-160x160.jpg'">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 col-xs-8">
							<div class="row">
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="cardno"><!-- 身份证号 --><s:text name="tal.cardno"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-credit-card"></li></span>
											<input type="text" id="cardno" name="cardno" class="form-control" value="${talentVo.cardno }" placeholder="" readonly>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="mobile"><!-- 手机号码--><s:text name="tal.mobile"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-mobile"></li></span>
											<input type="text" id="mobile" name="mobile" class="form-control" value="${talentVo.mobile }" placeholder="" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="degree"><!-- 人才学历 --><s:text name="tal.degree"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-mortar-board"></li></span>
											<input type="text" id="mobile" name="mobile" class="form-control" value="${talentVo.degree }" placeholder="" readonly>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="graduate_month"><s:text name="tal.graduateMonth"/></label>
										<div class="input-group">
											<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
											<input type="text"  name="graduate_month"  id="graduate_month" class="form-control" value="${talentVo.graduate_month }"
											placeholder="" readonly>
										</div>
									</div>
								</div>	
							</div>
							<div class="row">
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="univ"><!-- 学校名称 --><s:text name="tal.univ"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-university"></li></span>
											<input type="text" id="univ" name="univ.name" class="form-control" value="${talentVo.univ.name }" placeholder="" readonly style="width: 50%">
											<input type="text" id="univComment" name="univComment" value="${talentVo.univComment}" class="form-control" style="width: 50%;float:right;" readonly placeholder="No school.Please key in" >
											
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="college"><!-- 学院名称--><s:text name="tal.college"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-university"></li></span>
											<input type="text" id="college" name="college.name" class="form-control" value="${talentVo.college.name }" placeholder="" readonly style="width: 50%;">
											<input type="text" id="collegeComment" name="collegeComment" class="form-control" style="width: 50%;float:right;" placeholder="" value="${talentVo.collegeComment }" readonly>
										</div>
									</div>
								</div>
							</div>
						<div class="row">
								<div class="col-md-6 col-xs-6">
									<div class="form-group-addon">
										<label for="univ"><!-- 岗位 --><s:text name="tal.position"/></label>
										<div class="input-group" >
											<span class="input-group-addon" ><li class="fa fa-file-text"></li></span> 
											<input type="text" id="position" name="position" class="form-control" value="${talentVo.position }" placeholder=" " readonly style="width: 50%;">
											<input type="text" id="positionComment" name="positionComment" class="form-control" value="${talentVo.positionComment }" placeholder=" " readonly style="width: 50%;float:right;">
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="employmt_agreemt"><!-- 就业协议 --><s:text name="tal.employmtAgreemt"/></label>
											<input type="text"  id="employmt_agreemt" name="employmt_agreemt" class="form-control"   value="${talentVo.employmt_agreemt }" style="width: 100%;" readonly>
									</div>
								</div>
 						</div>
 						<div class="row">
								<div class="col-md-6 col-xs-6">
										<div class="form-group">
										<label for="work_location"><!-- 户籍--><s:text name="tal.nativePlaceProv"/></label>
										<div class="input-group" style="width: 100%;">										
											<input type="text"  id="native_place_prov" name="native_place_prov" class="form-control"   value="${talentVo.native_place_prov }" style="width: 33%;" readonly>
											<input type="text"  id="native_place_city" name="native_place_city" class="form-control"   value="${talentVo.native_place_city }" style="width: 33%;" readonly>
											<input type="text" id="native_comment" name="native_comment" class="form-control" style="width: 34%;float:right;" value="${talentVo.native_place_comment}" readonly>
										</div>
									</div>
								</div>	
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="work_location"><!-- 工作地点--><s:text name="tal.workLocation"/></label>
										<div class="input-group" style="width: 100%;">
											<input type="text" id="work_loc_comment" name="work_loc_comment" class="form-control"  value="${talentVo.work_location}" style="width: 50%;" placeholder="" readonly >
											<input type="text" id="work_loc_comment" name="work_loc_comment" class="form-control"  value="${talentVo.work_loc_comment}" style="width: 50%;float:right;" placeholder="" readonly>
										</div>
									</div>
								</div>
 							</div>
 							<div class="row">
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="in_company_time"><!-- 入职公司时间--><s:text name="tal.inCompanyTime"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-birthday-cake"></li></span>
											 <input type="text" id="in_company_time" name="in_company_time" class="form-control" value="${talentVo.in_company_time }" readonly>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-xs-6">
									<div class="form-group">
										<label for="in_proj_time"><s:text name="tal.inProjTime"/></label>
											<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-birthday-cake"></li></span>
											 <input type="text" id="in_proj_time" name="in_proj_time" class="form-control" value="${talentVo.in_proj_time }" readonly>
										</div>
									</div>
								</div>		
 							</div>
 							<div class="row">
 								<div class="col-md-12 col-xs-12">
									<div class="form-group">
										<label for="remark"><!-- 备注 --><s:text name="tal.remark"/></label>
										<textarea class="form-control" id="remark" name="remark" rows="1"  readonly
					                				placeholder="Remark ..."> ${talentVo.remark}</textarea>
									</div>
								</div>					
 							</div>
					</div>
 					
						<div class="col-md-4 col-xs-4">
							<div class="row">
								<div class="col-md-12 col-xs-12">
									<div class="form-group">
										<label for="email"><!-- 电子邮件--><s:text name="tal.email"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-envelope"></li></span>
											<input type="email" class="form-control" id="email" name="email" value="${talentVo.email }" placeholder="" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 col-xs-12">
									<div class="form-group">
										<label for="engLevel"><!-- 英语等级--><s:text name="tal.engLevel"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-reorder"></li></span>
											<input type="text" class="form-control" id="engLevel" name="english_level" value="${talentVo.english_level }" placeholder="" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="row">								
								<div class="col-md-12 col-xs-12">
									<div class="form-group"> 
										<label for="major"><!-- 专业--><s:text name="tal.major"/></label>
											<div class="input-group" style="width: 100%;">										
												<input type="text" id="major" name="major" class="form-control" value="${talentVo.major }" placeholder=""  style="width: 50%;" readonly>
												<input type="text" id="major_comment" name="major_comment" class="form-control"  style="width: 50%;float:right;" placeholder=""  value="${talentVo.major_comment }" readonly>									
											</div> 
									</div>
								</div>								
							</div>	
							<div class="row">
								<div class="col-md-12 col-xs-12">
									<div class="form-group">
										<label for="talSource"><!-- 人才来源--><s:text name="tal.talSource"/></label>
										<div class="input-group">
											<span class="input-group-addon"><li class="fa fa-home"></li></span>
											<input type="text" class="form-control" id="talSource" name="talent_source" value="${talentVo.talent_source }" placeholder="" readonly>
										</div>
									</div>
								</div>
							</div>
							<div class="row">								
								<div class="col-md-12 col-xs-12">
									<div class="form-group"> 
										<label for="major"><!-- 项目组--><s:text name="tal.projGrop"/></label>
											<div class="input-group" style="width: 100%;">										
												<input type="text" id="proj_group" name="proj_group" class="form-control" value="${talentVo.proj_group }" placeholder=""  style="width: 50%;" readonly>
												<input type="text" id="proj_group_comment" name="proj_group_comment" class="form-control"  style="width: 50%;float:right;" placeholder=""  value="${talentVo.proj_group_comment }" readonly>									
											</div> 
									</div>
								</div>								
							</div>
							<div class="row">
								<div class="col-md-12 col-xs-12">
									<div class="form-group">
										<label for="leave_time"><s:text name="tal.leaveTime"/></label>
											<div class="input-group">
												<div class="input-group-addon"><i class="fa fa-calendar"></i></div>
													<input type="text"  name="leave_time"  id="leave_time" class="form-control" value="${talentVo.leave_time }" placeholder="" readonly>
											</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 col-xs-12">
									<div class="form-group">
		                				<label id="inputLabel"><!-- 简历附件--><s:text name="tal.resume"/></label>
               							<div class="input-group">
			            					<input type="text" class="form-control" id="resumeFile_name" value="${talentVo.resume_url}" readonly>
			            					<span class="input-group-btn">
			            					<button type="button" id="downResumeBtn" class="btn btn-default btn-flat" onclick="downLoadResume(${talentVo.id})">
											<li class="fa fa-download"></li>&nbsp;&nbsp;
											<s:text name="tal.downLoadResumeFile"/>
											</button>
			               					</span>
			           					</div>
		                			</div>
								</div>
							</div>	
						</div>
					</div>
					<div class="row">
					
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="create_person"><!-- 创建人员--><s:text name="createPerson"/></label>
								<div class="input-group">
									<span class="input-group-addon"><li class="fa fa-user"></li></span>
									<input type="text" class="form-control" id="create_person" name="create_person" value="${talentVo.create_person }" placeholder="" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="create_time"><!-- 创建时间--><s:text name="createTime"/></label>
								<div class="input-group">
									<span class="input-group-addon"><li class="fa fa-clock-o"></li></span>
									<input type="text" class="form-control" id="create_time" name="create_time" value="${talentVo.create_time }" placeholder="" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="update_person"><!-- 更新人员--><s:text name="updatePerson"/></label>
								<div class="input-group">
									<span class="input-group-addon"><li class="fa fa-user"></li></span>
									<input type="text" class="form-control" id="update_person" name="update_person" value="${talentVo.update_person }" placeholder="" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-3 col-xs-3">
							<div class="form-group">
								<label for="update_time"><!-- 更新时间--><s:text name="updateTime"/></label>
								<div class="input-group">
									<span class="input-group-addon"><li class="fa fa-clock-o"></li></span>
									<input type="text" class="form-control" id="update_time" name="update_time" value="${talentVo.update_time }" placeholder="" readonly>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="box-footer" style="padding: 0 0 0 0;">
				<div class="col-md-12 col-xs-12">
					<div class="col-md-3 col-xs-3 col-md-offset-6 col-xs-offset-6">
						<button type="button" id="goEditButton"
							class="btn btn-block btn-primary btn-sm" onclick="javascript: goEdit(${talentVo.id},'${talentVo.name}');">
							<i class="fa fa-edit"></i>&nbsp;&nbsp;&nbsp;<s:text name="editButton"/>
						</button>
					</div>
					<div class="col-md-3 col-xs-3">
						<button type="button" id="closeButton"
							class="btn btn-block btn-primary btn-sm" onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));" >
							<i class="fa fa-close"></i>&nbsp;&nbsp;&nbsp;<s:text name="closeButton"/>
						</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>