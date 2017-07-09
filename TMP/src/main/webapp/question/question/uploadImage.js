	//<img alt="" src="../question/fileAction!getImage.do?fileName=70eab5bc4d8b497cba2f68c6bb4c9f4a">
	/*
	 var index = layer.open({
	 title: [
	 '提交图片',
	 'background-color:#3C8DBC; color:#ffffff;'
	 ],
	 offset: '1%',// 距离页面顶距离
	 type: 2,
	 area: [ '100%' , '100%'],
	 fix: false, //不固定
	 maxmin: true,
	 content: "../question/fileAction!toUploadImage.do?questionVo.id="+row.id,
	 });
	 layer.full(index);
	 */
	var question_id;
	var info;
	$(function() {
		question_id = $("#question_id").val();
		info = $("#info").val();
		$("#file-1").fileinput({
			uploadUrl : '../question/fileAction!filesUpload.do',
			allowedFileExtensions : [ 'jpg', 'png' ],
			overwriteInitial : false,
			maxFileSize : 500,//k单位
			maxFilesNum : 6,
			allowedFileTypes : [ 'image' ],
			showRemove : true,
			showUpload : true,
			showCancel : false,
			initialDelimiter : '*$$*',
			slugCallback : function(filename) {
				return filename.replace('(', '_').replace(']', '_');
			},
			uploadAsync : false
		});
		//异步上传返回结果处理
		$('#file-1').on('fileerror', function(event, data, msg) {
			upload(data.response);
		});
		//异步上传返回结果处理
		$("#file-1").on("fileuploaded",
				function(event, data, previewId, index) {
					upload(data.response);
				});
		//同步上传错误处理
		$('#file-1').on('filebatchuploaderror', function(event, data, msg) {
			upload(data.response);
		});
		//同步上传返回结果处理
		$("#file-1").on("filebatchuploadsuccess",
				function(event, data, previewId, index) {
					upload(data.response);
				});
		function upload(data) {
			//后台一定要返回json对象,空的也行。否则会出现提示警告。
			//返回对象的同时会显示进度条，可以根据返回值进行一些功能扩展
			if (data.success == 'true') {
				if (data.message == '1') {
					//失败
					$('#file-1').fileinput('clear');
					layer.msg(uploadImage_79_fail); // uploadImage_79_fail:提交图片失败!
				} else if (data.message == '0') {
					//成功
					parent.layer.msg(uploadImage_79_success); // uploadImage_79_success:提交图片成功!请勿忘记保存试题信息
					//反写页面方法
				    parent.$('#'+info).val(parent.$('#'+info).val()+data.datas.data);
				    parent.layer.close(parent.layer.getFrameIndex(window.name));
				}
			} else {
				$('#file-1').fileinput('clear');
				layer.msg(uploadImage_79_fail_46); // uploadImage_79_fail_46:提交图片失败!
				console.info(data.message);
			}
		}
		;
	});
