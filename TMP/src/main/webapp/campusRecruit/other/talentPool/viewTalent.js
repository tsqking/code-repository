/**
 * 
 */
var index;
$(function() {
	index = parent.layer.getFrameIndex(window.name);
});

function downLoadResume(id){
	var resume_name=$("#resumeFile_name").val();
	if(resume_name!="")
		window.open("../campusRC/talent!downTalentResume.do?id="+id);
	else
		parent.layer.msg(noResumeTip);
}

function goEdit(id,name){
	parent.editTalent(id,name);
	parent.layer.close(index);
}
