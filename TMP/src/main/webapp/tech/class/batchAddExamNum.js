/**
 * 上传
 */
$(document).ready(function() {
	$("#batchAddExamNumUpload").click(function() {
		var id = $("input[id=id]").val();// 得到后台传过来的classVo的Id
		console.log(id);
		var ids = [ "batchPeopleAllInfoList" ];
		var load_index = layer.load(0, {
			shade : [ 0.1, '#fff' ]
		});
		ajaxFileUpload(ids, '../tech/class!batchAddUserExamNum.do?classVo.id='+id, 0, "", null, function(data, status) {
			layer.close(load_index);
			if (data.success == "true") {// 上传成功
				$('#transferModal').modal('hide');
				//$("#personTable").DataTable().draw();
				var content = data.datas.message;
				content = content.replace(new RegExp("&lt;", "g"), "<");
				content = content.replace(new RegExp("&gt;", "g"), ">");
				layer.alert(content, {
					title : "准考证发送成功"
				});
				getMonitor();
			} else {// 上传失败
				layer.alert(data.message, {
					title : feedback
				});
			}
		})

	})

})

