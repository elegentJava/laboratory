package com.bupt.ltb.sem.vouching.type.base;

import com.bupt.ltb.common.type.Type;

/**
 * 资源类型
 * 
 * @author Hogan
 * 
 */
public interface TemplateType extends Type {

	/**
	 * 通过ID获取对应类型
	 * 
	 * @param id
	 * @return
	 */
	public TemplateType byId(int id);
}
