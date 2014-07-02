package com.obp.system.common.id;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.obp.system.common.service.SequenceService;
import com.obp.system.common.util.ApplicationContextUtils;
import com.obp.system.common.util.DateUtils;
import com.obp.system.common.util.OBPUtils;
import com.obp.system.model.metatype.Dto;
import com.obp.system.model.metatype.impl.BaseDto;

/**
 * 
 * @Title:IDGenerator.java
 * @Package:com.obp.system.common.id
 * @Description:ID生成器
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年5月6日下午8:05:29
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class IDGenerator {

	Logger logger = LogManager.getLogger();
	public static Long getId(){
		return getId("DEFAULT_ID");
	}
	
	/**
	 * @Description:根据表名获取一个序列值
	 * @author: wangzhao
	 * @date: 2014年5月7日上午10:36:53
	 * @mail: wangzhao@huateng.com
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Long getId(String tableName){
		SequenceService sequenceService = (SequenceService)ApplicationContextUtils.getBean("sequenceService");
		Dto dto = sequenceService.searchSysSequenceByTableName(tableName);
		Integer sequenceId = 1;
		String lastDate = DateUtils.getCurrentDate_8();
		if(OBPUtils.isEmpty(dto)){
			dto = new BaseDto();
			dto.put("SEQUENCE_ID", sequenceId);
			dto.put("TABLE_NAME", tableName);
			dto.put("MAX_ID", "9999999");
			dto.put("REMARK", "");
			dto.put("PATTERN", "");
			dto.put("ID_TYPE", "1");
			dto.put("LAST_DATE", lastDate);
			sequenceService.saveSysSequence(dto);
		}else{
			sequenceId = dto.getAsInteger("SEQUENCE_ID");
			lastDate = dto.getAsString("LAST_DATE");
			if(StringUtils.equals(lastDate, DateUtils.getCurrentDate_8())){
				sequenceId++;
			}else{
				sequenceId = 1;
			}
			dto.put("SEQUENCE_ID", sequenceId);
			sequenceService.updateSysSequence(dto);
		}
		
		return Long.valueOf(DateUtils.getCurrentDate_8()+StringUtils.leftPad(sequenceId.toString(), 7,"0"));
	}
	
}
