/**
 * 上传文件
 */
var index ;
$(function() {
	index = parent.layer.getFrameIndex(window.name);
	parent.layer.iframeAuto(index);//自动调整页面
});

function change(obj,type) {
    var file = $(obj);
    var ext=file.val();
    var ext=file.val().substring(file.val().lastIndexOf("/")+1).toLowerCase();
	ext=ext.substring(ext.lastIndexOf("\\")+1).toLowerCase();
    $("#"+type+"_name").text(ext);
}

$(document).on("click","#batchUpload",
	function() {
		var ids = ["universityList"];//id
		var load_index=parent.layer.load(0, {
		    shade: [0.1,'#fff']
		});
    	ajaxFileUpload(ids,'../univ/university!batchAddUniversity.do',0,"",null,
	    		function(data,status){
		        	if(data.success=="true"){//上传成功
		        		parent.layer.msg(batchAddSucc);
		        		if(data.datas.message!=""){
		        			var content=data.datas.message;
			        		content=content.replace(new RegExp("&lt;","g"),"<");
			        		content=content.replace(new RegExp("&gt;","g"),">");
		        			parent.layer.alert(content,{"title":feedback});
		        		}
						parent.refreshTable();
		        	}else{//上传失败
		        		parent.layer.alert(data.message);
		        	}
		        	parent.layer.close(load_index);
		        	parent.layer.close(index);
	        	}
    	);
	}
);
/**
 * 下载批量添加模板
 */
$(document).on("click","#downloadmodel",
	function() {
		window.open("../univ/university!downloadmodel.do");
	}
);
