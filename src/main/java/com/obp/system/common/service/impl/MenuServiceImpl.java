package com.obp.system.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.obp.system.common.constants.SystemConstants;
import com.obp.system.common.entity.MenuVO;
import com.obp.system.common.service.MenuService;
import com.obp.system.common.util.OBPUtils;
import com.obp.system.model.exception.CommonException;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;
import com.obp.system.model.service.impl.BaseServiceImpl;

@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {

	@SuppressWarnings("unchecked")
	public Dto getCardList(Dto pDto) {
		Dto outDto = new BaseDto();
		List<MenuVO> resultList = new ArrayList<MenuVO>();
		String userType = pDto.getAsString("userType");
		if (!userType.equalsIgnoreCase(SystemConstants.ACCOUNTTYPE_NORMAL)) {
			resultList = dao.queryForList("Menu.getCardListBasedSuperAndDeveloper", pDto);
			outDto.setDefaultAList(resultList);
			return outDto;
		}
		List<MenuVO> cardListBasedRole = dao.queryForList("Menu.getCardList", pDto);
		outDto.setDefaultAList(cardListBasedRole);
		/*List<MenuVO> cardListBasedUser = dao.queryForList("Menu.getCardListBasedUser", pDto);
		if (OBPUtils.isEmpty(cardListBasedRole)) {
			resultList.addAll(cardListBasedUser);	
		} else {
			resultList.addAll(cardListBasedRole);
			for (int i = 0; i < cardListBasedUser.size(); i++) {
				MenuVO MenuVOBaseUser = (MenuVO) cardListBasedUser.get(i);
				boolean flag = true;
				for (int j = 0; j < cardListBasedRole.size(); j++) {
					MenuVO MenuVOBaseRole = (MenuVO) cardListBasedRole.get(j);
					if (MenuVOBaseUser.getMenuId().equals(MenuVOBaseRole.getMenuId())) {
						flag = false;
					}
				}
				if (flag)
					resultList.add(MenuVOBaseUser);
			}
		}*/
		outDto.setDefaultAList(resultList);
		return outDto;
	}

	public Dto getCardTreeList(Dto pDto) throws CommonException {
		return (Dto) dao.queryForObject("Menu.getCardList", pDto);
	}

	public String searchSysMenuNameByMenuId(String menuId)
			throws CommonException {
		return (String) dao.queryForObject("Menu.searchSysMenuNameByMenuId", menuId);
	}

}
