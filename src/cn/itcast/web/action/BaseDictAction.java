package cn.itcast.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.domain.BaseDict;
import cn.itcast.service.BaseDictService;
import net.sf.json.JSONArray;
@Controller("baseDictAction")
@Scope("prototype")
public class BaseDictAction extends ActionSupport{

	private String dict_type_code;
	
	@Resource(name="baseDictService")
	private BaseDictService baseDictService;
	@Override
	public String execute() throws Exception {
		//1.����service����dict_type_code��������ֵ����list
		List<BaseDict> list=baseDictService.getListByTypeCode(dict_type_code);
		//2.��Listת��Ϊjson��ʽ
		String json = JSONArray.fromObject(list).toString();
		//3.��json���͸������
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}
	
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	
}
