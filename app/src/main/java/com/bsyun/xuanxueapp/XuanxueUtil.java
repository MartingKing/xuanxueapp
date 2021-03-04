package com.bsyun.xuanxueapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class XuanxueUtil {
    private static final String TAG = "XuanxueUtil";
    /**
     * 对于年月日的天干地支
     */
    private static int year_ganZhi;
    private static int month_ganZhi;
    private static int day_ganZhi;
    private static int mYear;
    private static int mMonth;
    private static int mDday;
    /**
     * 关于阴历的相关信息
     */
    private static int[] lunar_info = {
            0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2,
            0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977,
            0x04970, 0x0a4b0, 0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950,
            0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557,
            0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0,
            0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0, 0x195a6,
            0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570,
            0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5,
            0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
            0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530,
            0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45,
            0x0b5a0, 0x056d0, 0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0};
    /**
     * 记录天干的信息
     */
    private static String[] gan_info = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    private static String[] zhi_info = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    /**
     * 单例模式
     */
    private static volatile XuanxueUtil instance = null;

    private XuanxueUtil() {
    }

    public static XuanxueUtil getInstance() {
        if (instance == null) {
            synchronized (XuanxueUtil.class) {
                if (instance == null) {
                    instance = new XuanxueUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 获取农历某年的总天数
     *
     * @param year
     * @return
     */
    private static int daysOfYear(int year) {
        int sum = 348;
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            sum += (lunar_info[year - 1900] & i) == 0 ? 0 : 1;
        }
        //获取闰月的天数
        int daysOfLeapMonth;
        if ((lunar_info[year - 1900] & 0xf) != 0) {
            daysOfLeapMonth = (lunar_info[year - 1900] & 0x10000) == 0 ? 29 : 30;
        } else {
            daysOfLeapMonth = 0;
        }
        return sum + daysOfLeapMonth;
    }

    /**
     * 初始化年月日对应的天干地支
     */
    public static void initGanZhi(int year, int month, int day) {
        //获取现在的时间
//        int year = DateUtil.getYearByTimeStamp(times);
//        int month = DateUtil.getYearByTimeStamp(times);
//        int day = DateUtil.getYearByTimeStamp(times);
        Calendar calendar_now = Calendar.getInstance();
        calendar_now.set(year, month - 1, day);
        long date_now = calendar_now.getTime().getTime();
        //获取1900-01-31的时间
        Calendar calendar_ago = Calendar.getInstance();
        calendar_ago.set(1900, 0, 31);
        long date_ago = calendar_ago.getTime().getTime();
        long offset = (date_now - date_ago) / 86400000L;
        float remainder = (date_now - date_ago) % 86400000L;
        //余数大于0算一天
        if (remainder > 0) {
            offset += 1;
        }
        //都是从甲子开始算起以1900-01-31为起点
        //1899-12-21是农历1899年腊月甲子日  40：相差1900-01-31有40天
        day_ganZhi = (int) offset + 40;
        //1898-10-01是农历甲子月  14：相差1900-01-31有14个月
        month_ganZhi = 14;
        int iYear, daysOfYear = 0;
        for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
            daysOfYear = daysOfYear(iYear);
            offset -= daysOfYear;
            month_ganZhi += 12;
        }
        if (offset < 0) {
            offset += daysOfYear;
            iYear--;
            month_ganZhi -= 12;
        }
        //农历年份
        mYear = iYear;
        //1864年是甲子年
        year_ganZhi = iYear - 1864;
        //哪个月是闰月
        int leapMonth = leapMonth(iYear);
        boolean isLeap = false;
        int daysOfLeapMonth = 0, iMonth;
        for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
            //闰月
            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !isLeap) {
                --iMonth;
                isLeap = true;
                daysOfLeapMonth = leapDays(mYear);
            } else {
                daysOfLeapMonth = monthDays(mYear, iMonth);
            }
            //设置非闰月
            if (isLeap && iMonth == (leapMonth + 1)) {
                isLeap = false;
            }
            offset -= daysOfLeapMonth;
            if (!isLeap) {
                month_ganZhi++;
            }
        }
        if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
            if (isLeap) {
                isLeap = false;
            } else {
                isLeap = true;
                --iMonth;
                --month_ganZhi;
            }
        }
        if (offset < 0) {
            offset += daysOfLeapMonth;
            --iMonth;
            --month_ganZhi;
        }
        mMonth = iMonth;
        mDday = (int) (offset + 1);
        Log.e("PaipanActivity", "闰月: " + isLeap + " 年份=" + mYear + " 总共天数=" + daysOfYear + " month_ganZhi:" + month_ganZhi);
    }

    //====== 传回农历 y年闰月的天数
    private static int leapDays(int y) {
        if (leapMonth(y) != 0) {
            if ((lunar_info[y - 1900] & 0x10000) != 0) {
                return 30;
            } else {
                return 29;
            }
        } else {
            return 0;
        }
    }

    //====== 传回农历 y年m月的总天数
    private static int monthDays(int y, int m) {
        if ((lunar_info[y - 1900] & (0x10000 >> m)) == 0) {
            return 29;
        } else {
            return 30;
        }
    }

    //====== 传回农历 y年闰哪个月 1-12 , 没闰传回 0
    private static int leapMonth(int y) {
        return (int) (lunar_info[y - 1900] & 0xf);
    }

    /**
     * @param n 年份
     * @return 是否是闰年
     */
    private static boolean isRunnian(int n) {
        return (n % 4 == 0 && n % 100 != 0) || n % 400 == 0;
    }

    /**
     * 将年月日转化为天干地支的显示方法
     *
     * @param index
     * @return
     */
    private static String ganZhi(int index) {
        return gan_info[index % 10] + zhi_info[index % 12];
    }

    /**
     * 获取天干地支
     *
     * @return
     */
    public static String getGanZhi() {
        // TODO: 2020/11/12 此处月干有误差
        return "农历" + ganZhi(year_ganZhi) + "年 " + ganZhi(month_ganZhi) + "月 " + ganZhi(day_ganZhi) + "日";
    }

    public static String getGanZhiYue() {
        return ganZhi(month_ganZhi);
    }

    public static String getGanZhiNian() {
        return ganZhi(year_ganZhi);
    }

    public static String getGanZhiRi() {
        return ganZhi(day_ganZhi);
    }

    /**
     * 子时23～1点，丑时1～3点，寅时3～5点，卯时5～7点，辰时7～9点，巳时9～11点，午时11～13点，未时13～15点，申时15～17点，酉时17～19点，戌时19～21点，亥时21～23点。
     */
    public static String getShichen(long timeStamp) {
        int hour1 = DateUtil.getHourByTimeStamp(timeStamp);
        int mimute = DateUtil.getMinuteByTimeStamp(timeStamp);

        int hour;
        if (mimute < 10) {
            hour = Integer.parseInt(hour1 + "0" + mimute);
        } else {
            hour = Integer.parseInt(hour1 + String.valueOf(mimute));
        }
        String sc = "";
        if (hour > 100 && hour < 300) {
            sc = "丑";
        } else if (hour >= 300 && hour < 500) {
            sc = "寅";
        } else if (hour >= 500 && hour < 700) {
            sc = "卯";
        } else if (hour >= 700 && hour < 900) {
            sc = "辰";
        } else if (hour >= 900 && hour < 1100) {
            sc = "巳";
        } else if (hour >= 1100 && hour < 1300) {
            sc = "午";
        } else if (hour >= 1300 && hour < 1500) {
            sc = "未";
        } else if (hour >= 1500 && hour < 1700) {
            sc = "申";
        } else if (hour >= 1700 && hour < 1900) {
            sc = "酉";
        } else if (hour >= 1900 && hour < 2100) {
            sc = "戌";
        } else if (hour >= 2100 && hour < 2300) {
            sc = "亥";
        } else {
            sc = "丑";
        }
        return sc;
    }


    private static List<String> datas = new ArrayList<>();
    private static String[] rigan = {"甲", "乙", "丙", "丁", "戊"};

    public static String getDizhiHour(long timeStamp) {
        datas.clear();
        int index = 0;
        String ganzhiri = getGanZhiRi().substring(0, 1);
        String str = "";
        switch (ganzhiri) {
            case "甲":
            case "己":
                str = "甲";
                break;
            case "乙":
            case "庚":
                str = "乙";
                break;
            case "丙":
            case "辛":
                str = "丙";
                break;
            case "丁":
            case "壬":
                str = "丁";
                break;
            case "戊":
            case "癸":
                str = "戊";
                break;
        }
        String riganzhi = str + getShichen(timeStamp);
        for (String s : rigan) {
            for (String value : zhi_info) {
                datas.add(s + value);
            }
        }
        //每隔10个index，十天干重新来一次
        for (int i = 0; i < datas.size(); i++) {
            if (datas.get(i).contains(riganzhi)) {
                index = i;
            }
        }
        if (index < 10) {
            return gan_info[index] + getShichen(timeStamp);
        } else {
            return gan_info[index % 10] + getShichen(timeStamp);
        }
    }


    //    private String[] siji = {"春季", "夏季", "秋季", "冬季", "季末"};
    private static String[] siji = {"木", "火", "金", "水", "土"};
    private static String[] bamen = {"休门", "生门", "伤门", "杜门", "景门", "死门", "惊门", "开门"};
    private static String[] bmsk = {"休", "死", "旺", "旺", "相", "死", "囚", "囚",
            "囚", "相", "休", "休", "旺", "相", "死", "死",
            "相", "休", "死", "死", "囚", "休", "旺", "旺",
            "旺", "囚", "相", "相", "死", "囚", "休", "休",
            "死", "旺", "囚", "囚", "休", "旺", "相", "相"};


    /**
     * @param wx  春季，夏季，秋季，冬季，季末的五行属性
     * @param men 休门，生门。。。
     * @return
     */
    public static String getBamenShengke(String wx, String men) {
        datas.clear();
        String str = wx + men;
        for (String s : siji) {
            for (String value : bamen) {
                datas.add(s + value);
            }
        }
        for (int i = 0; i < datas.size(); i++) {
            if (str.equals(datas.get(i))) {
                return bmsk[i];
            }
        }
        return "";
    }


    private static String[] wuxing = {"金", "木", "水", "火", "土"};
    private static String[] jiuxing = {"天蓬", "天任", "天冲", "天辅", "天英", "天芮", "天柱", "天心", "天禽"};
    private static String[] jxsk = {"废", "旺", "囚", "囚", "休", "旺", "相", "相", "旺",
            "旺", "囚", "相", "相", "废", "囚", "休", "休", "囚",
            "相", "休", "废", "废", "囚", "休", "旺", "旺", "休",
            "休", "废", "旺", "旺", "相", "废", "囚", "囚", "废",
            "囚", "相", "休", "休", "旺", "相", "废", "废", "相"};

    /**
     * @param position 乾，兑，震...position
     * @param jx       天蓬星，天心星...
     * @return
     */
    public static String getJiuxingShengke(int position, String jx) {
        datas.clear();
        String[] gwWx = {"木", "火", "土", "木", "土", "金", "土", "水", "金"};
        String wx = gwWx[position];
        String str = wx + jx;
        for (String s : wuxing) {
            for (String value : jiuxing) {
                datas.add(s + value);
            }
        }
        for (int i = 0; i < datas.size(); i++) {
            if (str.equals(datas.get(i))) {
                return jxsk[i];
            }
        }
        return "";
    }

    private static String[] zhangsheng = {
            "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓", "绝", "胎", "养", "长生",
            "病", "衰", "帝旺", "临官", "冠带", "沐浴", "长生", "养", "胎", "绝", "墓", "死",
            "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓", "绝",
            "绝", "墓", "死", "病", "衰", "帝旺", "临官", "冠带", "沐浴", "长生", "养", "胎",
            "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病", "死", "墓", "绝",
            "绝", "墓", "死", "病", "衰", "帝旺", "临官", "冠带", "沐浴", "长生", "养", "胎",
            "死", "墓", "绝", "胎", "养", "长生", "沐浴", "冠带", "临官", "帝旺", "衰", "病",
            "长生", "养", "胎", "绝", "墓", "死", "病", "衰", "帝旺", "临官", "冠带", "沐浴",
            "帝旺", "衰", "病", "死", "墓", "绝", "胎", "养", "长生", "沐浴", "冠带", "临官",
            "临官", "冠带", "沐浴", "长生", "养", "胎", "绝", "墓", "死", "病", "衰", "帝旺",};

    /**
     * @param tg 甲乙丙丁...
     * @param dz 子丑寅卯...
     * @return String[] zhi_info = {"辰巳", "午", "未申", "卯", "", "酉", "丑寅", "子", "亥戌"};
     */
    public static String getZhangsheng(String tg, String dz) {
        datas.clear();
        //如果是四个角的宫位 地支有两个，需要拆开来计算
        for (String s : gan_info) {
            for (String value : zhi_info) {
                datas.add(s + value);
            }
        }
        if (dz.length() == 2) {
            String dz1 = dz.substring(0, 1);
            String dz2 = dz.substring(1);
            String str1 = tg + dz1;
            String str2 = tg + dz2;
            int index1 = 0, index2 = 0;
            for (int i = 0; i < datas.size(); i++) {
                if (str1.equals(datas.get(i))) {
                    index1 = i;
                }
                if (str2.equals(datas.get(i))) {
                    index2 = i;
                }
            }
            return zhangsheng[index1] + "\n" + zhangsheng[index2];
        } else {
            String str = tg + dz;
            int index = 0;
            for (int i = 0; i < datas.size(); i++) {
                if (str.equals(datas.get(i))) {
                    index = i;
                }
            }
            return zhangsheng[index];
        }
    }
}