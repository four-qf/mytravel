package com.tour.suse.action;
//使用ModelDriven极大简化 并且 model初始化放入的是对象栈中
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.Privilege;
import com.tour.suse.entity.Role;
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	private Long[] privilegeIds;
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	/*列表*/
	public String list(){
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);//这是放在map中
		return "list";
	}
	
	/*删除*/
	public String delete(){
		roleService.delete(model.getId());
		return "toList";
	}
	/*添加页面*/
	public String addUI(){
		return "saveUI";
	}
	
	/*添加*/
	public String add(){
		roleService.save(model);
		return "toList";
	}
	
	/*更新页面 注意点*/
	public String editUI(){
		model= roleService.getById(model.getId());
		return "saveUI";
	}
	/*更新*/
	public String edit(){
		Role role = roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);//更新传过来的entity
		return "toList";
	}
	
	/*设置权限页面*/
	public String setPrivilegeUI()
	{
		//准备回显数据
		model= roleService.getById(model.getId());
		
		if (model.getPrivileges() != null) {
			privilegeIds = new Long[model.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : model.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}
		}

		// 准备数据 privilegeList
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList); 
		
		return "setPrivilegeUI";
	}
	
	/** 设置权限 */
	public String setPrivilege() throws Exception {
		// 1，从数据库中获取原对象
		Role role = roleService.getById(model.getId());

		// 2，设置要修改的属性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 3，更新到数据库
		roleService.update(role);

		return "toList";
	}

}
