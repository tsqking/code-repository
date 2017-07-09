var info;
	$(function() {
		info = $("#info").val();
		//初始化
		init();
	});
	//初始化
	function init(){
		$("#contextOl").html("");
		$("#contextImg").html("");
		if(info==null||info==""){
			parent.layer.msg(findImageSim_92_no_image); // findImageSim_92_no_image:无图片可以展示!
			parent.layer.close(parent.layer.getFrameIndex(window.name));
			return null;
		}
		info = info.split("-");
		for(var i=0;i<info.length;i++){
			var uuid = info[i];
			var className = "";
			if(i==0){
				className = "active";
			}
			if(uuid!=null&&uuid!=""){
				$("#contextOl").html($("#contextOl").html()+
						'<li data-target="#carousel-example-generic" data-slide-to="'+i+'" class="'+className+'"></li>');
				$("#contextImg").html($("#contextImg").html()+
						('<div class="item '+className+'"><img onclick="javascript: parent.layer.close(parent.layer.getFrameIndex(window.name));"'+
						'style="height: 450px;width: 800px;" '+
						'src="../question/fileAction!getImage.do?fileName='+uuid+'" '+
						'alt=""><div class="carousel-caption">'+findImageSim_92_no+(i+1)+findImageSim_92_image+'</div></div>')) // findImageSim_92_no:第 // findImageSim_92_image:张图片
			}
		}	
	}
