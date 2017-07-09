<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background-color:#ecf0f5";>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/common/image/logo.ico" />
<s:include value="../../common.jsp"></s:include>
<!-- 页面js -->
<script
	src="${pageContext.request.contextPath}/tech/teacherpool/teacherpoolManage.js"></script>
<!-- 页面js国际化 -->
 <script src="${pageContext.request.contextPath}/tech/teacherpool/${session.system_lang}.js"></script> 
<title>教师资源库管理</title>
</head>
<body>
	<!-- Main content -->
    <section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
    <!-- 搜索条件 --> 
    <div class="box box-primary collapsed-box">
        <div class="box-header with-border" data-widget="collapse">
        <i class="fa fa-plus"></i>
          <h3 class="box-title"><!-- 搜索条件 --><s:property value="getText('searchInfo')"/></h3>
        </div>
        <form id="searchForm">
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
            <div class="col-md-6">        
              <div class="form-group">
                <label><!-- 学号/教员号 --><s:property value="getText('teacherNumber')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-bookmark-o"></li></span>
                	<input type="text" name="tepVo.no" id="no" class="form-control" placeholder="User Number">
              	</div>
              </div>
              <div class="form-group">
               <label><!--一级技能--><s:text name="firstSkill"/></label> <select class="form-control select"
									name="tepVo.first_skill" id="first_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
              </div>
             <div class="form-group">
                <label><!--三级技能--><s:text name="thirdSkill"/></label> <select class="form-control select"
									name="tepVo.third_skill" id="third_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
              	</div>           
                                
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <div class="form-group">
                <label><!-- 姓名 --><s:property value="getText('trueName')"/></label>
	            <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-mobile-phone"></li></span>
                	<input type="text" name="tepVo.name" id="name" class="form-control" placeholder="Name">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
                  <label><!--二级技能--><s:text name="secondSkill"/></label> <select class="form-control select"
									name="tepVo.second_skill" id="second_skill"
									style="width: 100%;">
									<option value=""></option>
								</select>
              	</div>
              
              <!-- /.form-group -->
              <div class="form-group">
               <label><!--知识点--><s:text name="knowledgePoint"/></label> <select class="form-control select"
									name="tepVo.knowledge" id="knowledge"
									style="width: 100%;">
									<option value=""></option>
								</select>
              </div>
            

              <!-- /.form-group -->   
            </div>
            <!-- /.col -->
          </div>
          <!-- /.row -->
        </div>
        <!-- /.box-body -->
        <div class="box-footer" style="padding:0 0 0 0">
          <table class="table text-center" style="margin-bottom:0px;">
                <tr>
                  <td style="width:200px;">
                  </td>
                  <td style="width:200px;">
                  </td>                 
                  <td style="width:200px;">
                  </td>     
                  <td style="width:200px;">
                    <button type="button" id="resetButton" class="btn btn-block btn-primary btn-sm">
					<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;<!-- 取消搜索条件 --><s:property value="getText('resetSearch')"/></button> 
                  </td>                 
                  <td style="width:200px;">
                   <button type="button" id="searchButton" class="btn btn-block btn-success btn-sm">
					<li class="glyphicon glyphicon-ok"></li>&nbsp;&nbsp;<!-- 应用搜索条件 --><s:property value="getText('applySearch')"/></button>  
                  </td>   
                </tr>
           </table>
        </div>
        </form>
      </div>
      <!-- /.box -->
      <!-- 数据表格 -->
      <div class="row">
        <div class="col-xs-12">
          <div class="box" style="margin-bottom: 5px;">
            <div class="box-header">
              <h3 class="box-title"  style="font-family:msyh" ><!-- 教师资源库列表 --><s:property value="getText('teacherpoolList')"/></h3>
            </div>
            <table class="table text-center" style="margin-bottom:0px;width:100%;">
                <tr>     
                  <td style="width:100%;">
                    <button type="button" class="btn btn-block btn-primary btn-sm" onclick="add()">
					<li class="glyphicon glyphicon-plus"></li>&nbsp;&nbsp;<!-- 添加新教师 --><s:property value="getText('addNewTeacher')"/></button> 
                  </td>           
                </tr>
           </table>
            <div class="box-body">
              <table id="teacherpool" class="table table-bordered table-striped table-hover">
                <thead>
                <tr>
                  <th><!-- <li class="fa fa-fw fa-venus-mars"></li>&nbsp;&nbsp; --><!-- 编号 --><s:property value="getText('teacherId')"/></th>        
                  <th><!-- <li class="fa fa-fw fa-tag"></li>&nbsp;&nbsp; --><!--姓名 --><s:property value="getText('trueName')"/></th>
                  <th><!-- <li class="fa fa-fw fa-child"></li>&nbsp;&nbsp; --><!-- 年龄 --><s:property value="getText('teacherAge')"/></th>
                  <th><!-- <li class="fa fa-fw fa-mobile-phone"></li>&nbsp;&nbsp; --><!-- 手机号码(移动) --><s:property value="getText('teacherMobile')"/></th>
                  <th><!-- <li class="fa fa-fw fa-envelope-o"></li>&nbsp;&nbsp; --><!-- 电子邮件 --><s:property value="getText('email')"/></th>                
                  <th><!-- <li class="fa fa-fw fa-envelope-o"></li>&nbsp;&nbsp; --><!-- 教师类型 --><s:property value="getText('role')"/></th>                
                  <th><!-- <li class="fa fa-fw fa-envelope-o"></li>&nbsp;&nbsp; --><!-- 专业方向 --><s:property value="getText('direction')"/></th>                
                  <th><!-- <li class="fa fa-fw fa-gear"></li>&nbsp;&nbsp; --><!-- 操作 --><s:property value="getText('operation')"/></th>
                </tr>
                </thead>
              </table>
            </div>          
          </div>         
        </div>       
      </div>          
    </section>
  
</body>
<script type="text/javascript">	
var index = parent.layer.getFrameIndex(window.name);
$(function() {
	parent.layer.iframeAuto(index);
	//下拉框初始化
	selectInitial("first_skill", "../tech/skill!findSkillOption.do?skillVo.parent_id=0", "", false);
	//获取二级联下拉框
	$('#first_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("second_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
		var second=$("#second_skill").val();
		var third=$("#third_skill").val();
		selectInitial("knowledge","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+selectedOption+"&pointVo.second_skill_id="+second+"&pointVo.third_skill_id="+third,"",false);
	});
	//获取三级下拉框
	$('#second_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		selectInitial("third_skill","../tech/skill!findSkillOption.do?skillVo.parent_id="+selectedOption,"",false);
		var first=$("#first_skill").val();
		var third=$("#third_skill").val();
		selectInitial("knowledge","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+first+"&pointVo.second_skill_id="+selectedOption+"&pointVo.third_skill_id="+third,"",false);
	});
	//获取四级下拉框
	$('#third_skill').on('change', function() {
		var selectedOption = $(this).find("option:selected").val();
		if(selectedOption == null || "" == selectedOption){
			return;
		}
		var first=$("#first_skill").val();
		var second=$("#second_skill").val();
		selectInitial("knowledge","../tech/point!getPointOptionBySkillIds.do?pointVo.first_skill_id="+first+"&pointVo.second_skill_id="+second+"&pointVo.third_skill_id="+selectedOption,"",false);
	});
});
	</script>
</html>
