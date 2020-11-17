package com.sinosoft.surrender.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.sinosoft.surrender.common.contant.DateConstant;

/**
 * 
 * 日期处理工具类
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-上午11:11:29
 * @version:
 */
public class DateUtil {
	private static Map<String, SimpleDateFormat> dateFmtMap = new HashMap<String, SimpleDateFormat>();
	private static final String LOCK_OBJ = "LOCK";

	public static final String DATE_TYPE_Y = "Y";
	public static final String DATE_TYPE_A = "A";
	public static final String DATE_TYPE_M = "M";
	public static final String DATE_TYPE_D = "D";
	public static final String DATE_TYPE_W = "W";
	public static final String DATE_TYPE_H = "H";
	public static final String DATE_TYPE_MI = "MI";

	/**
	 * 
	 * 将yyyy-MM-dd HH24:mm:ss拆分成日期和时间
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param fullTime
	 * @return
	 */
	public static String[] getDateAndTime(String fullTime) {
		String[] dateAndTime = new String[2];

		if (StringUtils.isNotBlank(fullTime)) {
			dateAndTime[0] = fullTime.substring(0, 10);
			dateAndTime[1] = fullTime.substring(11, 19);
		}

		return dateAndTime;
	}

	/**
	 * 将保险区间1-5D或1D格式拆分
	 * 
	 * @history: 2016年1月3日 wangyw_sinosoft
	 * @param fullPeriod
	 * @return
	 */
	public static String[] getInsPeriodAndFlag(String fullPeriod) {
		String[] insPeriodAndFlag = new String[2];

		if (StringUtils.isNotBlank(fullPeriod)) {
			String[] split = fullPeriod.split("-");
			fullPeriod = split[split.length - 1];
			insPeriodAndFlag[0] = fullPeriod.substring(0, fullPeriod.length() - 1);
			insPeriodAndFlag[1] = fullPeriod.substring(fullPeriod.length() - 1, fullPeriod.length());
		}

		return insPeriodAndFlag;
	}

	/**
	 * 转换日期格式
	 * 
	 * @param str
	 * @return
	 */
	public synchronized static Date getDate(String str, String pattern) {
		Date date = null;

		if (str != null && !"".equals(str)) {
			SimpleDateFormat df = getSimpleDateFormat(pattern);
			try {
				date = df.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	private static SimpleDateFormat getSimpleDateFormat(String format) {
		SimpleDateFormat sdf = null;
		if (dateFmtMap.containsKey(format)) {
			sdf = dateFmtMap.get(format);
			return sdf;
		}
		synchronized (LOCK_OBJ) {
			sdf = new SimpleDateFormat(format, Locale.ENGLISH);
			dateFmtMap.put(format, sdf);
		}
		return sdf;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @history: 2016年1月3日 wangyw_sinosoft
	 * @return
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 日期字符串格式
	 * 
	 * 2016年1月17日 wangyw_sinosoft
	 * 
	 * @param sourceDate
	 * @param format
	 * @return
	 */
	public static synchronized String getDateStr(Date sourceDate, String format) {
		SimpleDateFormat df = getSimpleDateFormat(format);
		return df.format(sourceDate);
	}

	/**
	 * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔 ,舍弃法author: HST
	 * <p>
	 * <b>Example: </b>
	 * <p>
	 * <p>
	 * 参照calInterval2(String cstartDate, String cendDate, String
	 * unit)，前两个变量改为日期型即可
	 * <p>
	 * 
	 * @param startDate
	 *            起始日期，Date变量
	 * @param endDate
	 *            终止日期，Date变量
	 * @param unit
	 *            时间间隔单位，可用值("Y"--年 "M"--月 "D"--日)
	 * @return 时间间隔,整形变量int
	 */
	public static int calIntervalOfAbandon(Date startDate, Date endDate, String unit) {
		int interval = 0;

		GregorianCalendar sCalendar = new GregorianCalendar();
		sCalendar.setTime(startDate);
		int sYears = sCalendar.get(Calendar.YEAR);
		int sMonths = sCalendar.get(Calendar.MONTH);
		int sDays = sCalendar.get(Calendar.DAY_OF_MONTH);
		int sDaysOfYear = sCalendar.get(Calendar.DAY_OF_YEAR);

		GregorianCalendar eCalendar = new GregorianCalendar();
		eCalendar.setTime(endDate);
		int eYears = eCalendar.get(Calendar.YEAR);
		int eMonths = eCalendar.get(Calendar.MONTH);
		int eDays = eCalendar.get(Calendar.DAY_OF_MONTH);
		int eDaysOfYear = eCalendar.get(Calendar.DAY_OF_YEAR);

		if (DATE_TYPE_Y.equals(unit)) {
			interval = eYears - sYears;
			if (eMonths < sMonths) {
				interval--;
			} else {
				if (eMonths == sMonths && eDays < sDays) {
					interval--;
					if (eMonths == 1) {
						// 如果同是2月，校验润年问题
						if ((sYears % 4) == 0 && (eYears % 4) != 0) {
							// 如果起始年是润年，终止年不是润年
							if (eDays == 28) {
								// 如果终止年不是润年，且2月的最后一天28日，那么补一
								interval++;
							}
						}
					}
				}
			}
		}
		if (DATE_TYPE_M.equals(unit)) {
			interval = eYears - sYears;
			interval *= 12;
			interval += eMonths - sMonths;
			if (eDays < sDays) {
				interval--;
				// eDays如果是月末，则认为是满一个月
				int maxDate = eCalendar.getActualMaximum(Calendar.DATE);
				if (eDays == maxDate) {
					interval++;
				}
			}
		}
		if (DATE_TYPE_D.equals(unit)) {
			interval = eYears - sYears;
			interval *= 365;
			interval += eDaysOfYear - sDaysOfYear;

			// 处理润年
			int n = 0;
			eYears--;
			if (eYears > sYears) {
				int i = sYears % 4;
				if (i == 0) {
					sYears++;
					n++;
				}
				int j = (eYears) % 4;
				if (j == 0) {
					eYears--;
					n++;
				}
				n += (eYears - sYears) / 4;
			}
			if (eYears == sYears) {
				int i = sYears % 4;
				if (i == 0) {
					n++;
				}
			}
			interval += n;
		}
		return interval;
	}

	/**
	 * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔 约进法author: HST
	 * <p>
	 * <b>Example: </b>
	 * <p>
	 * <p>
	 * 参照calInterval2(String cstartDate, String cendDate, String
	 * unit)，前两个变量改为日期型即可
	 * <p>
	 * 
	 * @param startDate
	 *            起始日期，Date变量
	 * @param endDate
	 *            终止日期，Date变量
	 * @param unit
	 *            时间间隔单位，可用值("Y"--年 "M"--月 "D"--日)
	 * @return 时间间隔,整形变量int
	 */
	public static int calIntervalOfAbout(Date startDate, Date endDate, String unit) {
		int interval = 0;

		GregorianCalendar sCalendar = new GregorianCalendar();
		sCalendar.setTime(startDate);
		int sYears = sCalendar.get(Calendar.YEAR);
		int sMonths = sCalendar.get(Calendar.MONTH);
		int sDays = sCalendar.get(Calendar.DAY_OF_MONTH);
		int sDaysOfYear = sCalendar.get(Calendar.DAY_OF_YEAR);

		GregorianCalendar eCalendar = new GregorianCalendar();
		eCalendar.setTime(endDate);
		int eYears = eCalendar.get(Calendar.YEAR);
		int eMonths = eCalendar.get(Calendar.MONTH);
		int eDays = eCalendar.get(Calendar.DAY_OF_MONTH);
		int eDaysOfYear = eCalendar.get(Calendar.DAY_OF_YEAR);

		if (DATE_TYPE_Y.equals(unit)) {
			interval = eYears - sYears;
			if (eMonths > sMonths) {
				interval++;
			} else {
				if (eMonths == sMonths && eDays > sDays) {
					interval++;
					/*
					 * if (eMonths == 1) { // 如果同是2月，校验润年问题 if ((sYears % 4) ==
					 * 0 && (eYears % 4) != 0) { // 如果起始年是润年，终止年不是润年 if (eDays
					 * == 28) { // 如果终止年不是润年，且2月的最后一天28日，那么补一 interval++; } } }
					 */
				}
			}
		}
		if (DATE_TYPE_M.equals(unit)) {
			interval = eYears - sYears;
			interval *= 12;
			interval += eMonths - sMonths;
			if (eDays > sDays) {
				interval++;
				// eDays如果是月末，则认为是满一个月
				int maxDate = eCalendar.getActualMaximum(Calendar.DATE);
				if (eDays == maxDate) {
					interval--;
				}
			}
		}
		if (DATE_TYPE_D.equals(unit)) {
			interval = eYears - sYears;
			interval *= 365;
			interval += eDaysOfYear - sDaysOfYear;

			// 处理润年
			int n = 0;
			eYears--;
			if (eYears > sYears) {
				int i = sYears % 4;
				if (i == 0) {
					sYears++;
					n++;
				}
				int j = (eYears) % 4;
				if (j == 0) {
					eYears--;
					n++;
				}
				int temp = eYears - sYears;
				for (int m = 1; m < temp; m++) {
					if ((sYears + m) % 4 == 0) {
						n++;
					}
				}
				// n += (eYears - sYears) / 4;
			}
			if (eYears == sYears) {
				int i = sYears % 4;
				if (i == 0) {
					n++;
				}
			}
			interval += n;
		}
		return interval;
	}

	public static synchronized Date calDate(Date sourceDate, int dayIntval) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.DATE, dayIntval);
		return calendar.getTime();
	}

	public static synchronized Date calSecond(Date sourceDate, int dayIntval) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sourceDate);
		calendar.add(Calendar.SECOND, dayIntval);
		return calendar.getTime();
	}

	public static String getNowDateStr(String pattern) {
		Date currDate = new Date();

		if (StringUtils.isBlank(pattern)) {
			return null;
		}
		return getDateStr(currDate, pattern);
	}

	/**
	 * 日期计算，月加减
	 * 
	 * @param date
	 *            初始日期
	 * @param amount
	 *            月数增量（负数为减）
	 * @return 计算后的日期
	 */
	public static Date addMonths(Date date, int amount) {
		return DateUtils.addMonths(date, amount);
	}

	/**
	 * 日期计算，年加减
	 * 
	 * @param date
	 *            初始日期
	 * @param amount
	 *            年数增量（负数为减）
	 * @return 计算后的日期
	 */
	public static Date addYears(Date date, int amount) {
		return DateUtils.addYears(date, amount);
	}

	/**
	 * 得到传入时间n天后凌晨00:00
	 */
	public static Date getMorrow(Date date, int amount) {
		Date MorrowDate = DateUtils.addDays(date, amount);
		SimpleDateFormat df = getSimpleDateFormat("yyyy-MM-dd");
		try {
			MorrowDate = df.parse(df.format(MorrowDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return MorrowDate;
	}

	/**
	 * 得到传入时间第n天的23:59:59
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static Date getEndTime(Date date, int amount) {
		Date endTime = DateUtils.addDays(date, amount);
		SimpleDateFormat df = getSimpleDateFormat("yyyy-MM-dd");
		try {
			endTime = df.parse(df.format(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return DateUtils.addSeconds(endTime, -1);
	}

	/**
	 * 截止日期计算
	 * 
	 * @param date
	 *            初始日期
	 * @param amount
	 *            增量（负数为减）
	 * @param unit
	 *            时间间隔单位，可用值("A"--岁"Y"--年 "M"--月 "D"--日)
	 * @return 计算后的日期
	 */
	public static Date getEndDate(Date startDate, int amount, String unit) {
		if (DATE_TYPE_A.equals(unit)) {
			return DateUtils.addYears(startDate, amount);
		}
		if (DATE_TYPE_Y.equals(unit)) {
			return DateUtils.addYears(startDate, amount);
		}
		if (DATE_TYPE_M.equals(unit)) {
			return DateUtils.addMonths(startDate, amount);
		}
		if (DATE_TYPE_D.equals(unit)) {
			Date morrowDate = DateUtils.addDays(startDate, amount);
			SimpleDateFormat df = getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				morrowDate = df.parse(df.format(morrowDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return morrowDate;
		}
		if (DATE_TYPE_H.equals(unit)) {
			Date MorrowDate = DateUtils.addHours(startDate, amount);
			SimpleDateFormat df = getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				MorrowDate = df.parse(df.format(MorrowDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return MorrowDate;
		}
		return null;
	}

	/**
	 * 获取某天的初始时间
	 * 
	 * @return
	 */
	public static Date getTodayStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(todayStart.get(Calendar.YEAR), todayStart.get(Calendar.MONTH),
				todayStart.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		String dateStartStr = DateUtil.getDateStr(todayStart.getTime(), "yyyy-MM-dd HH:mm:ss");
		Date date = DateUtil.getDate(dateStartStr, "yyyy-MM-dd HH:mm:ss");
		return date;
	}

	
	
	/**
	 * 获得yyyy-MM-dd格式的日期，用于存表
	 * 
	 * 
	 * 2017年12月26日 wangyanwei
	 */
	public static Date getCurrentDate() {
		String currentDateStr = getNowDateStr("yyyy-MM-dd");
		Date currentDate = getDate(currentDateStr, "yyyy-MM-dd");
		return currentDate;
	}
	/**
	 * 获得yyyyMMdd格式的日期，用于存表
	 * @author yangyb
	 * @date 2019年12月3日
	 * @return
	 * @throws ParseException 
	 */
	public static String getCurrentDateForSurrenderMsgUrl(){
		return getNowDateStr("yyyyMMdd");
	}
	public static Date getCurrentDateForSurrenderMsgUrlWithDate() {
		String currentDateStr = getNowDateStr("yyyyMMdd");
		Date currentDate = getDate(currentDateStr, "yyyyMMdd");
		return currentDate;
	}
	/**
	 * 获得HH:mm:ss格式的日期，用于存表
	 * 
	 * 
	 * 2017年12月26日 wangyanwei
	 */
	public static String getCurrentTime() {
		return getNowDateStr("HH:mm:ss");
	}

	/**
	 * 获取系统日期中的年份
	 * 
	 * @return String 年
	 */
	public static String getYear() {
		StringBuffer tSBql = new StringBuffer();
		int intYear = Calendar.getInstance().get(Calendar.YEAR);
		tSBql.append(intYear);
		return tSBql.toString();
	}

	/**
	 * 获取系统日期中的月份
	 * 
	 * @return String 月
	 */
	public static String getMonth() {
		StringBuffer tSBql = new StringBuffer();
		int intMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (intMonth < 10) {
			tSBql.append("0");
			tSBql.append(intMonth);
		} else {
			tSBql.append(intMonth);
		}
		return tSBql.toString();
	}

	 public static boolean isLastDayOfMonth(Date date) { 
	        Calendar calendar = Calendar.getInstance(); 
	        calendar.setTime(date); 
	        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); 
	        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) { 
	            return true; 
	        } 
	        return false; 
	    } 
	
	/**
	 * 计算日期的函数 参数compareDate为空时： 返回日期为基础日期增加指定年、月后的当天，或者增加指定天数后的某天。
	 * a、如果基础日期是当月的月底，则返回结果也是增加时间间隔后该月的月底；
	 * b、如果基础日期的在当月的经过天数大于或者等于增加时间间隔后该月的总天数，则返回结果为该月月底；
	 * 
	 * @param baseDate
	 *            起始日期
	 * @param interval
	 *            时间间隔
	 * @param unit
	 *            时间间隔单位
	 * @param compareDate
	 *            参照日期 参照日期指当按照年月进行日期的计算的时候，参考的日期
	 * @return Date类型变量
	 */
	@SuppressWarnings("deprecation")
	public static Date calDate(Date baseDate, int interval, String unit, Date compareDate) {
		Date returnDate = null;

		GregorianCalendar tBaseCalendar = new GregorianCalendar();
		tBaseCalendar.setTime(baseDate);

		if (unit.equals("Y")) {
			tBaseCalendar.add(Calendar.YEAR, interval);
		}
		if (unit.equals("M")) {
			tBaseCalendar.add(Calendar.MONTH, interval);
		}
		if (unit.equals("D")) {
			tBaseCalendar.add(Calendar.DATE, interval);
		}

		if (compareDate != null) {
			GregorianCalendar tCompCalendar = new GregorianCalendar();
			tCompCalendar.setTime(compareDate);
			int nBaseYears = tBaseCalendar.get(Calendar.YEAR);
			int nBaseMonths = tBaseCalendar.get(Calendar.MONTH);
			int nCompMonths = tCompCalendar.get(Calendar.MONTH);
			int nCompDays = tCompCalendar.get(Calendar.DATE);

			if (unit.equals("Y")) {
				tCompCalendar.set(nBaseYears, nCompMonths, nCompDays);
				if (tCompCalendar.before(tBaseCalendar)) {
					tBaseCalendar = getFormatDate(nBaseYears + 1, nCompMonths, nCompDays);
					returnDate = tBaseCalendar.getTime();
				} else {
					tCompCalendar = getFormatDate(nBaseYears, nCompMonths, nCompDays);
					returnDate = tCompCalendar.getTime();
				}
			}
			if (unit.equals("M")) {
				tCompCalendar.set(nBaseYears, nBaseMonths, nCompDays);
				if (tCompCalendar.before(tBaseCalendar)) {
					tBaseCalendar = getFormatDate(nBaseYears, nBaseMonths + 1, nCompDays);
					returnDate = tBaseCalendar.getTime();
				} else {
					tCompCalendar = getFormatDate(nBaseYears, nBaseMonths, nCompDays);
					returnDate = tCompCalendar.getTime();
				}
			}
			if (unit.equals("D")) {
				returnDate = tBaseCalendar.getTime();
			}
			tCompCalendar = null;
		} else {
			returnDate = tBaseCalendar.getTime();

			// XinYQ added on 2006-09-25 : 修正闰年闰月和月底天数,和Oracle保持一致
			GregorianCalendar tLeapCalendar = new GregorianCalendar();
			tLeapCalendar.setTime(returnDate);
			int arrComnYearEndDate[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			int arrLeapYearEndDate[] = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
			int nOldYear = 1900 + baseDate.getYear();
			int nOldMonth = baseDate.getMonth();
			int nOldDate = baseDate.getDate();
			int nNewYear = tLeapCalendar.get(Calendar.YEAR);
			int nNewMonth = tLeapCalendar.get(Calendar.MONTH);

			if ((isLeapYear(nOldYear) && nOldDate == arrLeapYearEndDate[nOldMonth])
					|| (!isLeapYear(nOldYear) && nOldDate == arrComnYearEndDate[nOldMonth])) {
				if (unit != null && (unit.equalsIgnoreCase("Y") || unit.equalsIgnoreCase("M"))) {
					if (isLeapYear(nNewYear)) {
						// 2012年2月23日16:10:50 国华 于绍雷修改 和以前版本统一
						if (nOldDate <= arrLeapYearEndDate[nNewMonth]) {
							if(isLastDayOfMonth(baseDate)) {
								returnDate.setDate(arrLeapYearEndDate[nNewMonth]);	
							}else {
							returnDate.setDate(nOldDate);
							}
						} else {
							returnDate.setDate(arrLeapYearEndDate[nNewMonth]);
						}

					} else {
						returnDate.setDate(arrComnYearEndDate[nNewMonth]);
					}
				}
			}
			tLeapCalendar = null;
		}
		tBaseCalendar = null;

		return returnDate;
	}

	/**
	 * 对传入的Year、Month、Day进行组装并格式化，并且如果Day大于本月最大日期，则重置Day为本月最后一天
	 * 
	 * @param tYear
	 * @param tMonth
	 * @param tDay
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar getFormatDate(int tYear, int tMonth, int tDay) {
		GregorianCalendar tCalendar = new GregorianCalendar();
		int arrComnYearEndDate[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int arrLeapYearEndDate[] = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		tCalendar.set(Calendar.YEAR, tYear);
		tCalendar.set(Calendar.MONTH, tMonth);
		if (isLeapYear(tYear + tMonth / 11) && tDay > arrLeapYearEndDate[tMonth % 11]) {
			tCalendar.set(Calendar.DATE, arrLeapYearEndDate[tMonth % 11]);
		} else if (!isLeapYear(tYear + tMonth / 11) && tDay > arrComnYearEndDate[tMonth % 11]) {
			tCalendar.set(Calendar.DATE, arrComnYearEndDate[tMonth % 11]);
		} else {
			tCalendar.set(Calendar.DATE, tDay);
		}
		return tCalendar;
	}

	/**
	 * 判断是否为闰年 XinYQ added on 2006-09-25
	 */
	public static boolean isLeapYear(int nYear) {
		boolean ResultLeap = false;
		ResultLeap = (nYear % 400 == 0) | (nYear % 100 != 0) & (nYear % 4 == 0);
		return ResultLeap;
	}

	/**
	 * 
	 * 查询 闰年平年二月份特殊日期
	 * 
	 * @history: 2018-3-20
	 * @author: wangwl_sinosoft
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkSpecDate(Date startDate, Date endDate) {
		GregorianCalendar sCalendar = new GregorianCalendar();
		sCalendar.setTime(startDate);
		int sMonth = sCalendar.get(Calendar.MONTH);
		int sDaty = sCalendar.get(Calendar.DAY_OF_MONTH);
		sCalendar.setTime(endDate);
		int eYear = sCalendar.get(Calendar.YEAR);
		int eMonth = sCalendar.get(Calendar.MONTH);
		int eDaty = sCalendar.get(Calendar.DAY_OF_MONTH);
		// 获取二月的天数
		Calendar c = Calendar.getInstance();
		c.set(eYear, 2, 1);
		c.add(Calendar.DATE, -1);
		int days = c.get(Calendar.DATE);
		if (sMonth == 1 && sDaty == 29 && eMonth == 1 && eDaty == 28 && days == 28) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 根据保单生效日期以及退保时间计算退保年度
	 * 
	 * @history: 2018-3-20
	 * @author: wangwl_sinosoft
	 * @param polCValiDate
	 * @param zTPoint
	 * @return
	 */
	public static int calSpecZtYear(Date polCValiDate, Date zTPoint) {
		Date calCValiDate = calDate(polCValiDate, -1, DATE_TYPE_D,null);
		//若是闰年2月份特殊日期,需要减2 例如:2016-02-29生效的保单2018-02-28退保
		if (checkSpecDate(polCValiDate, zTPoint)) {
			calCValiDate = calDate(polCValiDate, -2, DATE_TYPE_D,null);
		}
		int ztYear = calIntervalOfAbout(calCValiDate, zTPoint, DATE_TYPE_Y);
		return ztYear;
	}

	
	public static void main(String[] args) {
		//String dateStartStr = DateUtil.getDateStr(DateUtil.getNowDate(), "yyyy-MM-dd HH:mm:ss");
	//	Date b  = getEndDate(DateUtil.getCurrentDate(),-2,DATE_TYPE_Y);
		//Date b = getDate("2020-02-27","yyyy-MM-dd");
		//Date a =  DateUtil.calDate(getDate("2019-02-28","yyyy-MM-dd"), 1, DateUtil.DATE_TYPE_Y, null);
		//System.out.println(getDateStr(a,"yyyy-MM-dd"));
		//int w = calIntervalOfAbout(a,b,DateUtil.DATE_TYPE_D);
		//System.out.println(getDateStr(b,"yyyy-MM-dd"));
		//Date a = DateUtil.calDate(DateUtil.getCurrentDate(),12, DateConstant.PERIOD_UNITS_MONTH, b);
		//System.out.println(w);
		
		/*Date date = new Date();
		Date calDate = calDate(date, -2);
		System.out.println(getDateStr(calDate,"yyyy-MM-dd"));*/
		/*String time1 = getCurrentTime();
		System.out.println(time1);
		String time2 = time1.substring(3, 5);
		System.out.println(time2);*/
		
		/*String address = "河北省廊坊市文安县文安镇丰利路恒通小区河北省廊坊市文安县丰利路恒通小区";
		if(address.trim().getBytes().length >= 97){
			System.out.println(address.getBytes().length);
			address = address.substring(0,(97/4)) + "...";
			System.out.println(address);
			String dian = "...";
			System.out.println(dian.getBytes().length);
		}*/
		Date startDate = getDate("2020-02-28", "yyyy-MM-dd");
	//	Date endDate = getDate("2018-11-11", "yyyy-MM-dd");
		
	//	int calIntervalOfAbout = calIntervalOfAbout(startDate, endDate, DATE_TYPE_D);
		 Date calDate = DateUtil.calDate(startDate, 48, DateConstant.PERIOD_UNITS_MONTH,null);
		// int zTYearDays = DateUtil.calIntervalOfAbandon(startDate, calDate, DateUtil.DATE_TYPE_D);
		System.out.println(calDate);
		
	}
}
