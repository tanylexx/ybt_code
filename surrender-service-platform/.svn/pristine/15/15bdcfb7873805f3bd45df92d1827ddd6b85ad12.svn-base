package com.sinosoft.surrender.common.util;

import java.math.BigDecimal;

import com.sinosoft.surrender.cashvalue.dto.InterestSubsectionDTO;

public class InterestUtil {
	/**
	 * 计算利息
	 * 
	 * @history: 2018-4-22 wangwl_sinosoft
	 * @param interestSubsection
	 * @param insuaccbala
	 * @return
	 */
	public static BigDecimal calAccValue(InterestSubsectionDTO interestSubsection, BigDecimal insuaccbala) {
		// 计算利息
		// 1. 获取利率和日期间隔
		BigDecimal daliyRate = interestSubsection.getRate();
		int days = interestSubsection.getDays();
		// 2. 计算利息 = 账户价值*利率-账户价值
		BigDecimal finalRate = daliyRate.add(BigDecimal.ONE);// 1+日利率
		BigDecimal sumInsuaccbala = finalRate.pow(days).multiply(insuaccbala);// 时间间隔的幂次方
		BigDecimal distValue = sumInsuaccbala.subtract(insuaccbala);
		//BigDecimal subsectionRate = new BigDecimal(ArithUtil.round(distValue.doubleValue(), 2) + "");// 保留两位小数(加字符串失精度)
		return distValue;
	}

	/**
	 * 利率转换函数
	 * 
	 * @param OriginRate
	 *            double 原始利率
	 * @param OriginRateType
	 *            String 原始利率类型：年利率（"Y")，月利率("M")，日利率("D")
	 * @param TransType
	 *            String 复利转换("C")compound，单利转换("S")simple
	 * @param DestRateType
	 *            String 年利率，月利率,日利率
	 * @return double 例子：TransAccRate(0.48,"Y","C","D") 将0.48的年复利，转换为日复利
	 */
	public static double TransAccRate(double OriginRate, String OriginRateType, String TransType, String DestRateType) {
		double DestRate = 0;
		double aPower;

		// 复利处理
		if (TransType.equals("C")) {
			// 年复利转换
			if (OriginRateType.equals("Y")) {
				// translate to year
				if (DestRateType.equals("Y")) {
					DestRate = OriginRate;
				}
				// translate to month
				else if (DestRateType.equals("M")) {
					aPower = 1.0 / 12.0;
					DestRate = java.lang.Math.pow(1 + OriginRate, aPower) - 1;
				}
				// translate to day
				else if (DestRateType.equals("D")) {
					aPower = 1.0 / 365;
					DestRate = java.lang.Math.pow(1 + OriginRate, aPower) - 1;
				}

			}
			// 月复利转换
			else if (OriginRateType.equals("M")) {
				// translate to month
				if (DestRateType.equals("M")) {
					DestRate = OriginRate;
				}
				// translate to year
				else if (DestRateType.equals("Y")) {
					aPower = 12;
					DestRate = java.lang.Math.pow(1 + OriginRate, aPower) - 1;
				}
				// translate to day
				else if (DestRateType.equals("D")) {
					aPower = 1.0 / 30.0;
					DestRate = java.lang.Math.pow(1 + OriginRate, aPower) - 1;
				} else {
					System.out.println("-----CM no this DestRateType----");
				}
			}
			// 日复利转换
			else if (OriginRateType.equals("D")) {
				// translate to day
				if (DestRateType.equals("D")) {
					DestRate = OriginRate;
				}
				// translate to month
				else if (DestRateType.equals("M")) {
					aPower = 30;
					DestRate = java.lang.Math.pow(1 + OriginRate, aPower) - 1;
				}
				// translate to year
				else if (DestRateType.equals("Y")) {
					aPower = 365;
					DestRate = java.lang.Math.pow(1 + OriginRate, aPower) - 1;
				}
			}
		}
		// 单利处理
		else if (TransType.equals("S")) {
			// 年单利转换
			if (OriginRateType.equals("Y")) {
				// translate to year
				if (DestRateType.equals("Y")) {
					DestRate = OriginRate;
				}
				// translate to month
				else if (DestRateType.equals("M")) {
					DestRate = OriginRate / 12;
				}
				// translate to day
				else if (DestRateType.equals("D")) {
					DestRate = OriginRate / 365;
				}
			}
			// 月单利转换
			else if (OriginRateType.equals("M")) {
				// translate to month
				if (DestRateType.equals("M")) {
					DestRate = OriginRate;
				}
				// translate to year
				else if (DestRateType.equals("Y")) {
					DestRate = OriginRate * 12;
				}
				// translate to day
				else if (DestRateType.equals("D")) {
					DestRate = OriginRate / 30;
				}
			}
			// 日单利转换
			else if (OriginRateType.equals("D")) {
				// translate to day
				if (DestRateType.equals("D")) {
					DestRate = OriginRate;
				}
				// translate to month
				else if (DestRateType.equals("M")) {
					DestRate = OriginRate * 30;
				}
				// translate to year
				else if (DestRateType.equals("Y")) {
					DestRate = OriginRate * 365;
				}
			}
		}
		// 返回转换后的实际利率
		return DestRate;
	}

}
