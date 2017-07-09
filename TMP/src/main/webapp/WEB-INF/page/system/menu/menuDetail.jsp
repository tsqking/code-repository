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
<script src="${pageContext.request.contextPath}/system/menu/menuDetail.js"></script>
<!-- js国际化 -->
<script src="${pageContext.request.contextPath}/system/menu/${system_lang}.js"></script>
<title>菜单详细界面</title>
</head>
<!-- onload="window.print();" -->
<body>
	<!-- Main content -->
    <section class="content" style="padding-top: 5px;padding-bottom: 5px;background-color:#ecf0f5">
    <div class="box box-primary" style="margin-bottom: 0px;">
        <form id="searchForm">
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group">
               <!-- 菜单编号 -->
                <label><s:property value="getText('menuId')"/></label>           
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-bookmark-o"></li></span>
                	<span type="text" id="id" class="form-control"></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单名称 -->
                <label><s:property value="getText('menuName')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
                	<span type="text" id="name" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单名称 -->
                <label><s:property value="getText('menuNameEN')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-tag"></li></span>
                	<span type="text" id="name_en_US" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单等级 -->
                <label><s:property value="getText('menuLevel')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-sticky-note-o"></li></span>
                	<span type="text" id="level" class="form-control"></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 菜单排序 -->
                <label><s:property value="getText('menuOrder')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-signal"></li></span>
                	<span type="text" id="order" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 创建时间 -->
                <label><s:property value="getText('createTime')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-clock-o"></li></span>
                	<span type="text" id="create_time" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 更新时间 -->
                <label><s:property value="getText('updateTime')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-clock-o"></li></span>
                	<span type="text" id="update_time" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
            </div>
            <!-- /.col -->
            <div class="col-md-6">
              <div class="form-group">
              <!-- 菜单URL -->
                <label><s:property value="getText('UrlAddress')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-television"></li></span>
                	<span type="text" id="url" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
             <div class="form-group">
              <!-- 菜单描述 -->
                <label><s:property value="getText('menuDesc')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-font"></li></span>
                	<span type="text" id="description" class="form-control"></span>
              	</div>
              </div>
              <!-- /.form-group -->
             <div class="form-group">
              <!-- 菜单描述 -->
                <label><s:property value="getText('menuDescEN')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-font"></li></span>
                	<span type="text" id="description_en_US" class="form-control"></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 父级菜单 -->
                <label><s:property value="getText('pMenu')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-user"></li></span>
                	<span type="text" id="parent_name" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 启用/禁用 -->
                <label><s:property value="getText('menuStatus')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-area-chart"></li></span>
                	<span type="text" id="enable_name" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 创建人 -->
                <label><s:property value="getText('createPerson')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-user-secret"></li></span>
                	<span type="text" id="create_person" class="form-control" ></span>
              	</div>
              </div>
              <!-- /.form-group -->
              <div class="form-group">
              <!-- 更新人 -->
                <label><s:property value="getText('updatePerson')"/></label>
                <div class="input-group">
                	<span class="input-group-addon"><li class="fa fa-fw fa-user-secret"></li></span>
                	<span type="text" id="update_person" class="form-control" ></span>
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
                    <!-- 关闭详细信息窗口 -->
					<li class="glyphicon glyphicon-remove"></li>&nbsp;&nbsp;<s:property value="getText('closeDetailWin')"/></button> 
                  </td>                 
                  <td style="width:200px;">
                   <button type="button" id="editIframe" class="btn btn-block btn-success btn-sm">
                   <!-- 编辑菜单信息 -->
					<li class="glyphicon glyphicon-ok"></li>&nbsp;&nbsp;<s:property value="getText('editMenuInfo')"/></button>  
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