package com.roll.casserole.leecode;

import okhttp3.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author roll
 * created on 2020-01-31 10:56
 */
public class Solution5 {
    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     * <p>
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        Set<String> tempSet = new HashSet<>();
        int i = 0;
        while (i < s.length()) {
            i++;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "biz_code=zhibiaoguan&id_number=410122199105252652&black_box=6224900598341823&sequence_id=1534843456268&application_id=WF2018062015224413855084");
        Request request = new Request.Builder()
                .url("http://localhost:9000/bodyguard/apply/v4.5?partner_code=newnew&partner_key=123456789&app_name=newnew_web&=")
                .post(body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("User-Agent", "PostmanRuntime/7.15.2")
                .addHeader("Accept", "*/*")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "13650f49-3d0f-46d4-b6d2-b6560bef8fac,4443e0f8-adeb-499b-99b5-3aaedc22fc98")
                .addHeader("Host", "localhost:9000")
                .addHeader("Accept-Encoding", "gzip, deflate")
                .addHeader("Content-Length", "142")
                .addHeader("Connection", "keep-alive")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body());
    }
}
