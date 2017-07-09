package com.clps.tmp.demo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.clps.tmp.common.util.AjaxReturnInfo;
import com.clps.tmp.common.util.DownloadFileUtil;
import com.clps.tmp.common.util.MailSenderUtil;
import com.clps.tmp.common.util.BtTableUtil;
import com.clps.tmp.common.vo.BtTableVo;
import com.clps.tmp.core.common.action.BaseAction;
import com.clps.tmp.core.common.util.RedisUtil;
import com.clps.tmp.core.common.util.config.SpringContextUtil;
import com.clps.tmp.demo.service.DemoService;
import com.clps.tmp.demo.vo.DemoVo;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("publicPackage")
@Namespace("/demo")
@Controller
@Scope("prototype")
@Action("demo")
@Results({
	 @Result(name="toBtTableManagePage",location="btTableTest.jsp"),
	@Result(name="json",type="json",params={"root","resultMap"})
})
public class DemoAction extends BaseAction implements ModelDriven<DemoVo> {
	private DemoVo demoVo;
	@Override
	public DemoVo getModel() {
		if (demoVo == null) {
			demoVo = new DemoVo();
		}
		return demoVo;
	}
	@Resource
	private DemoService demoService;
	private HashMap<String, Object> resultMap;
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	/**
	 * 测试方法：启动登陆后，使用url访问 http://localhost:8080/TMP/demo/demo!getDemo.do
	 * 2016年3月13日 Seven
	 */
	public String getDemo() throws Exception{
		DemoVo vo1=demoService.queryByName("Seven");
		AjaxReturnInfo rtn=AjaxReturnInfo.success("");
		rtn.add("demoVo_Map", vo1);
		resultMap=rtn.getReturnMap();
		
		//发送邮件
		String [] to={"seven.sun@clps.com.cn"};
		String [] attachFile={"D:\\CLPS\\Work\\TMP-V2.0\\立项\\项目立项报告TMPV2.0.doc"};
		Map<String,Object> modelMap=new HashMap<String,Object>();
		modelMap.put("userName", "Seven");
		MailSenderUtil.sendTemplateEmail(to, null, null, "Test", "template_mail", modelMap, attachFile);
		MailSenderUtil.sendEmail(to, null, null, "Test 2", "Dear Seven,<br><i>你好！</i>", attachFile);
		
		return "json";
	}
	
	/**
     * 跳转管理界面
     */
    public String toBtTableManagePage() {
        return "toBtTableManagePage";
    }
    /**
     * Bootstrap Table 使用Demo
     * 2016年3月21日 Seven
     */
	public String getBtTableDemo()throws Exception{
		System.out.println("获取BtTable Demo数据");
	    BtTableUtil bootStrapTable = new BtTableUtil();
	    Map<String,Object> dataMap = bootStrapTable.getParamers(this.httpRequest);
	    BtTableVo<DemoVo> bootStrapPageVo= demoService.findDemoList(dataMap);
	    AjaxReturnInfo rtn=AjaxReturnInfo.success("");
	    rtn.add("data", bootStrapPageVo);
	    resultMap=rtn.getReturnMap();
	    return "json";
	}
	/**
	 * 打包下载
	 * @throws Exception
	 */
	public void zipDownloadDemo() throws Exception{
		List<String> fileList=new ArrayList<String>();
		fileList.add("D://CLPS//Work//FU//数据库备份//Drop所有表.sql");
		fileList.add("D://home//fudan//Question//images//9fe91813342f42eea6d50586b04c397b.jpg");
		DownloadFileUtil.downLoadZipFiless(fileList, this.httpRequest, this.httpResponse);
	}
}
