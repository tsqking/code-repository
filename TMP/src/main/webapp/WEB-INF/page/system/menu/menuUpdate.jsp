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
<script src="${pageContext.request.contextPath}/system/menu/menuUpdate.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/system/menu/${system_lang}.js"></script>
<title>菜单编辑界面</title>
</head>
<!-- onload="window.print();" -->
<body>
	<!-- Main content -->
    <section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
    <div class="box box-primary" style="margin-bottom: 0px;">
        <form id="infoForm">
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
              <!-- 菜单编号 -->
                <label><s:property value="getText('menuId')"/></label>           
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-bookmark-o"></li></span>
                	<input type="text" id="id" name="id" class="form-control" readonly="readonly">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单名称 -->
                <label for="name"><s:property value="getText('menuName')"/><small><i class="fa fa-star notNull"></i></small></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
                	<input type="text" id="name" name="name" class="form-control" >
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单名称 -->
                <label for="name_en_US"><s:property value="getText('menuNameEN')"/><small><i class="fa fa-star notNull"></i></small></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
                	<input type="text" id="name_en_US" name="name_en_US" class="form-control" >
              	</div>
              </div>
              <!-- /.form-group -->  
              <div class="form-group">
              <!-- 菜单等级 -->
                <label><s:property value="getText('menuLevel')"/></label>
		        <select class="form-control select" id="level" style="width: 100%;" disabled="disabled">
		        </select>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单排序 -->
              	<label for="order"><s:property value="getText('menuOrder')"/></label>
              	<div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-signal"></li></span>
                	<input type="text" id="order" name="order" class="form-control" >
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 创建时间 -->
                <label><s:property value="getText('createTime')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-clock-o"></li></span>
                	<input type="text" id="create_time" class="form-control" disabled="disabled">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 更新时间 -->
                <label><s:property value="getText('updateTime')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-clock-o"></li></span>
                	<input type="text" id="update_time" class="form-control" disabled="disabled">
              	</div>
              </div>
              <!-- /.form-group -->
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <div class="form-group">
              <!-- 菜单URL -->
                <label for="url"><s:property value="getText('UrlAddress')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-television"></li></span>
                	<input type="text" id="url" name="url" class="form-control" >
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
               <!-- 菜单描述 -->
                <label><s:property value="getText('menuDesc')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-sticky-note-o"></li></span>
                	<input type="text" id="description" name="description" class="form-control">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
               <!-- 菜单描述 -->
                <label><s:property value="getText('menuDescEN')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-sticky-note-o"></li></span>
                	<input type="text" id="description_en_US" name="description_en_US" class="form-control">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 父级菜单 -->
                <label><s:property value="getText('pMenu')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-user"></li></span>
                	<input type="text" id="parent_name" class="form-control" disabled="disabled">
                </div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 启用/禁用 -->
                <label for="enable"><s:property value="getText('menuStatus')"/><small><i class="fa fa-star notNull"></i></small></label>
                <select class="form-control select" id="enable" name="enable" style="width: 100%;">
		        </select>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 创建人 -->
                <label><s:property value="getText('createPerson')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-user-secret"></li></span>
                	<input type="text" id="create_person" class="form-control" disabled="disabled">
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 更新人 -->
                <label><s:property value="getText('updatePerson')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-user-secret"></li></span>
                	<input type="text" id="update_person" class="form-control" disabled="disabled">
              	</div>
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
                    <button type="button" id="closeIframe" class="btn btn-block btn-primary btn-sm">
                    <!-- 关闭编辑信息窗口 -->
					<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;<s:property value="getText('closeEditWin')"/></button> 
                  </td>                 
                  <td style="width:200px;">
                   <button type="button" id="updateIframe" class="btn btn-block btn-success btn-sm">
                    <!-- 提交编辑结果 -->
					<li class="glyphicon glyphicon-ok"></li>&nbsp;&nbsp;<s:property value="getText('applyUpdate')"/></button>  
                  </td>   
                </tr>
           </table>
        </div>
        </form>
      </div>     
    </section>
    <!-- /.content -->
</body>
</html>

<input type="hidden" value="${menu.id}" id="menuId"/>