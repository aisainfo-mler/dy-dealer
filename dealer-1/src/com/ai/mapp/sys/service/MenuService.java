package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.MenuDao;
import com.ai.mapp.sys.entity.Menu;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-21 下午08:30:51
 * 类说明:
 */

@Service("mappMenuService")
@Transactional
public class MenuService {
	public final Log log = LogFactory.getLog(MenuService.class);
	
	@Autowired
	private MenuDao menuDao;
	
	public Collection<Menu> listMenus(Menu menu,int start,int limit)throws Exception{
		try{
			log.debug(menu==null?"menu is null":menu.toString());
			
			Collection<Menu> c = null;
			if(start < 0){
				c = menuDao.listAll(menu);
			}else{
				c = menuDao.list(menu, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveMenu(Menu menu)throws Exception{
		log.debug(menu==null?"menu is null":menu.toString());
		menuDao.save(menu);
	}
	
	public void deleteMenu(Menu menu)throws Exception{
		log.debug(menu==null?"menu is null":menu.toString());
		menuDao.delete(menu);
	}
	
	public int countMenu(Menu menu) throws Exception{
		return menuDao.count(menu);
	}
	
	public Menu loadMenu(Long id)throws Exception{
		return menuDao.get(id);
	}
	
	public Map<Long,List<Menu>> getSubMenu(List<Menu> mainMenu)throws Exception{
		
		Menu menu = new Menu();
		Map<Long,List<Menu>> subMenuMap = new HashMap<Long,List<Menu>>();
		
		List<Menu> subMenu = null;
		if(mainMenu != null && mainMenu.size() != 0){
			for(Menu temp:mainMenu){
				menu.setParent(temp);
				menu.setStatus(SYSConstant.STATE_VALID);
				subMenu = (List<Menu>)listMenus(menu, -1, 0);
				subMenuMap.put(temp.getId(), subMenu);
			}
		}
		return subMenuMap;
	}
}
