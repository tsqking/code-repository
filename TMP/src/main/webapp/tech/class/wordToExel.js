$("#batchTransferBtn").click(
		function() {
			var id = $("input[id=id]").val();
			var ids = [ "batchApply" ];
			var excelName="";
			var excelPath="";
			var load_index = layer.load(0, {
				shade : [ 0.1, '#fff' ]
			});
			ajaxFileUpload(ids, '../tech/class!batchWordToExel.do?classVo.id='
					+ id, 0, "", null, function(data, status) {
				document.getElementById('excelName').value = data.datas.excelName;
				document.getElementById('excelPath').value = data.datas.excelPath;
//				 excelName=data.datas.excelName;
//				 excelPath=data.datas.excelPath;
				layer.close(load_index);
				if (data.success == "true") {// 上传成功
				// $('#batchTransfer').modal('hide');
					/* $("#personTable").DataTable().draw(); */
					$('#batchTransfermodal').modal('hide');
					var content = data.datas.message;
					content = content.replace(new RegExp("&lt;", "g"), "<");
					content = content.replace(new RegExp("&gt;", "g"), ">");
					layer.alert(content, {
						title : feedback
					});
						var excelName = document.getElementById('excelName').value;
						var excelPath = document.getElementById('excelPath').value;
						window.open("../tech/class!downExcel.do?excelName="+excelName+"&excelPath="+excelPath);
					
				} else {// 上传失败
					layer.alert(data.message, {
						title : feedback
					});
				}
//				console.info(excelName+">>>>>"+excelPath);
//				download(excelName,excelPath);
			});
			
//			setTimeout(function(){
//				download();
//			    //这里的代码将在1000ms(1s后执行)
//			},1000)
			
			
		});

//	function download(){
//		var excelName = document.getElementById('excelName').value;
//		var excelPath = document.getElementById('excelPath').value;
//		window.open("../tech/class!downExcel.do?excelName="+excelName+"&excelPath="+excelPath);
//	}