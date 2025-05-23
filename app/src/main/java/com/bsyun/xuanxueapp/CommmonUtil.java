package com.bsyun.xuanxueapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Assuming a Lunar class might be used as hinted in getPanInfo,
// but not creating it as part of this task.
// If it's in the same package, no import needed.
// If it's elsewhere, an import would be:
// import com.example.Lunar; // Or actual package

public class CommmonUtil {

    // Constants
    public static final String TIME = "time_extra_key";
    public static final String[] yuanBamen = {"休", "生", "伤", "杜", "景", "死", "惊", "开", "中"}; // 9 elements, palace order
    public static final String[] yuanJiuXing = {"蓬", "任", "冲", "辅", "英", "芮", "柱", "心", "禽"}; // 9 elements, palace order
    public static final int[] iconGongwei = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // Placeholder, actual R.drawable.gongwei_x IDs needed

    // Methods

    /**
     * Placeholder for Qi Men Dun Jia pan calculation.
     * The actual return type and content will depend on the specific QMDJ library or logic.
     * This placeholder returns a list of mixed-type objects as an example.
     * @param time The time in milliseconds for which to calculate the pan.
     * @param rigz The Day GanZhi string (e.g., "甲子").
     * @return A list containing various pieces of pan information.
     */
    public static List<Object> getPanInfo(long time, String rigz) {
        List<Object> panInfo = new ArrayList<>();
        panInfo.add(true); // isYangdun (boolean) - Placeholder
        panInfo.add("甲子"); // Placeholder for Xun Shou Gan of the hour pillar or similar
        panInfo.add("子丑"); // Placeholder for current hour pillar's Xun Kong (Empty/Void)
        panInfo.add(1);    // jushu (int) - Placeholder for Ju number (e.g., Yang Dun 1 Ju)
        // Example of adding a more complex object, assuming a Lunar class exists
        // panInfo.add(new Lunar(time)); // If Lunar class is available and needed here
        return panInfo;
    }

    /**
     * Gets the Xunshou Gan (旬首干) for a given ShiGan (时干 - Hour Stem).
     * @param curShiGan The current hour Heavenly Stem.
     * @return Placeholder Xunshou Gan.
     */
    public static String getXunshouGan(String curShiGan) {
        // Actual logic would involve finding the start of the 10-day/10-hour cycle (Xun)
        return "甲"; // Placeholder
    }

    /**
     * Placeholder for identifying special hour stems (e.g., Liu Jia, San Qi, etc.).
     * @param curShiGan The current hour Heavenly Stem.
     * @return The original stem or a modified one if special.
     */
    public static String getSpecialShigan(String curShiGan) {
        // Actual logic to identify and potentially transform stems based on QMDJ rules.
        // For example, if it's a Liu Yi Ji Xing (六仪击刑) or other special cases.
        if ("甲".equals(curShiGan)) return "戊"; // 甲遁在戊
        return curShiGan; // Placeholder, returns original
    }

    /**
     * Generates a map of Heavenly Stems for the 9 palaces (Di Pan - Earth Plate).
     * This is highly dependent on Dun Jia (阳遁/阴遁) and Ju Shu (局数).
     * @param isYangdun True for Yang Dun (阳遁), false for Yin Dun (阴遁).
     * @param jushu The Ju number (局数), e.g., 1 for Yang Dun 1 Ju.
     * @return A map where key is palace index (0-8, Lo Shu order) and value is the Heavenly Stem.
     */
    public static Map<Integer, String> getYinYangTianganMap(boolean isYangdun, int jushu) {
        Map<Integer, String> map = new HashMap<>();
        // Placeholder: Example Di Pan (Earth Plate) stems for Yang Dun 1 Ju (阳遁一局)
        // Lo Shu order: 坎1, 坤2, 震3, 巽4, 中5, 乾6, 兑7, 艮8, 离9
        // Corresponding array indices: 0, 1, 2, 3, 4, 5, 6, 7, 8
        String[] defaultStems;
        if (isYangdun) {
            // Example for Yang Dun Ju 1: 戊 己 庚 辛 壬 癸 丁 丙 乙 (starting from Kan 1 palace)
            // This is a simplification. Actual arrangement depends on jushu and starting stem.
            defaultStems = new String[]{"戊", "己", "庚", "辛", "壬", "癸", "丁", "丙", "乙"};
        } else {
            // Example for Yin Dun Ju 1: 戊 癸 壬 辛 庚 己 乙 丙 丁 (starting from Kan 1 palace, reversed sequence)
            defaultStems = new String[]{"戊", "癸", "壬", "辛", "庚", "己", "乙", "丙", "丁"};
        }
        // This needs to be dynamically generated based on jushu.
        // For jushu N, the stem for palace N (N-1 index) would be 戊 (Wu).
        // Then stems are laid out forwards (YangDun) or backwards (YinDun).

        for (int i = 0; i < 9; i++) {
            map.put(i, defaultStems[i]); // Palace index 0-8 (mapping to Lo Shu 1-9)
        }
        return map;
    }

    /**
     * Placeholder for any default transformations of a Heavenly Stem if needed.
     * @param gan The Heavenly Stem.
     * @return The processed Heavenly Stem.
     */
    public static String getDefaultGan(String gan) {
        // For example, if "甲" needs to be consistently represented as "戊" in some contexts.
        if ("甲".equals(gan)) return "戊";
        return gan; // Simple pass-through for now
    }

    /**
     * Gets the Xun Kong (旬空 - Empty/Void branches) for the current ShiGan (Hour Stem).
     * @param curShiGan The current hour Heavenly Stem.
     * @return Placeholder Xun Kong string.
     */
    public static String getXunkong(String curShiGan) {
        // Actual logic involves finding the two Earthly Branches that are "empty"
        // in the current 10-day/10-hour cycle (Xun).
        // Example: If curShiGan is 甲子, Xun Kong is 戌亥.
        return "戌亥"; // Placeholder
    }

    /**
     * Gets the palace index (0-8) for the Ma Xing (马星 - Horse Star).
     * This depends on the Day Branch (日支) or Hour Branch (时支).
     * Assuming it's based on curShiGan for now, which is not standard.
     * It should typically be based on Day Branch or Hour Branch.
     * @param curShiGan (Incorrect parameter, should be Day/Hour Branch)
     * @return Placeholder palace index (0-8, Lo Shu) for Ma Xing.
     */
    public static int getStarMa(String curShiGan) {
        // Actual logic: Ma Xing is derived from the Day Branch (日支) or Hour Branch (时支).
        // 冲三合局的第一个字 (Opposite of the first branch in the San He combo of the current branch)
        // E.g., Day Branch 寅 (Yin Tiger): San He is 寅午戌 (Tiger-Horse-Dog). First is 寅. Opposite of 寅 is 申.
        // So Ma Xing is 申 (Monkey), which is Kun palace (宫位2, index 1 if 0-indexed).
        // This is a placeholder.
        return 1; // Placeholder (e.g. Kun palace for Shen Ma Xing)
    }

    /**
     * Gets a placeholder string (like Wuxing) based on RecyclerView position (0-8).
     * This mapping is speculative and needs to be defined by a Qi Men expert.
     * Corresponds to Lo Shu palaces: Kan 1, Kun 2, Zhen 3, Xun 4, Zhong 5, Qian 6, Dui 7, Gen 8, Li 9.
     * @param position The RecyclerView adapter position (0-8).
     * @return A string representing some attribute of the palace.
     */
    public static String getMonthByRecyclerPosition(int position) {
        // Lo Shu: Kan(1), Kun(2), Zhen(3), Xun(4), Zhong(5), Qian(6), Dui(7), Gen(8), Li(9)
        // Array indices:   0,      1,      2,      3,      4,       5,      6,      7,      8
        String[] palaceWuxing = {"水", "土", "木", "木", "土", "金", "金", "土", "火"}; // Wuxing of palaces
        if (position >= 0 && position < palaceWuxing.length) {
            return palaceWuxing[position];
        }
        return "土"; // Default, should ideally not happen with positions 0-8
    }

    /**
     * Gets placeholder Earthly Branch(es) based on RecyclerView position (0-8).
     * This mapping is speculative and needs to be defined by a Qi Men expert.
     * Corresponds to Lo Shu palaces.
     * @param position The RecyclerView adapter position (0-8).
     * @return A string representing the Earthly Branch(es) of the palace.
     */
    public static String getDZByRecyclerPosition(int position) {
        // Lo Shu: Kan(1), Kun(2), Zhen(3), Xun(4), Zhong(5), Qian(6), Dui(7), Gen(8), Li(9)
        // Array indices:   0,      1,      2,      3,      4,       5,      6,      7,      8
        String[] palaceBranches = {"子", "未申", "卯", "辰巳", "戊己", "戌亥", "酉", "丑寅", "午"}; // Earthly Branches of Palaces
        // Note: Zhong Gong (Central Palace) is often associated with Wu/Ji (戊己) rather than a specific branch,
        // or it takes on characteristics from the current season/JieQi or is used for寄宫 (hosting).
        // Using 戊己 as a placeholder.
        if (position >= 0 && position < palaceBranches.length) {
            return palaceBranches[position];
        }
        return "子"; // Default, should ideally not happen with positions 0-8
    }
}
