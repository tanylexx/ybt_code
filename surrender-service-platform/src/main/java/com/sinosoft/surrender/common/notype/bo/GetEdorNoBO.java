package com.sinosoft.surrender.common.notype.bo;

public interface GetEdorNoBO {

	/**
	 * 
	 * 成流水号 对应业务枚举ENUM_LIS_EDORNO_TYPE 取到对应的编码进行操作 例如保全受理号的生成（正常编码为16位）： 1.
	 * 取到的编码为90488 2. 查询maxNo序列 3. 拼接字段：90488+00..00+maxNo+8 总位数为16位，不足补0
	 * 
	 * @history: 2018-4-4
	 * @author: wangwl_sinosoft
	 * @param limitNo
	 * @param edorNo
	 * @return
	 */
	String getEdorNo(String limitNo, String edorNo);

}
