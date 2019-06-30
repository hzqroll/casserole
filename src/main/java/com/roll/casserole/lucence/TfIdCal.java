package com.roll.casserole.lucence;

import java.util.Arrays;
import java.util.List;

/**
 * @author zongqiang.hao
 * created on 2018/10/5 上午10:40.
 */
public class TfIdCal {
    //文档中出现的次数/文档数量
    public double tf(List<String> doc, String term) {
        double termFrequency = 0;
        for (String str : doc) {
            if (str.equalsIgnoreCase(term)) {
                termFrequency++;
            }
        }
        return termFrequency / doc.size();
    }

    //文档集中出现的次数
    public int df(List<List<String>> docs, String term) {
        int n = 0;
        if (term != null && term != "") {
            for (List<String> doc : docs) {
                for (String word : doc) {
                    if (term.equalsIgnoreCase(word)) {
                        n++;
                        break;
                    }
                }
            }
        } else {
            System.out.println("not null");
        }
        return n;
    }

    public double idf(List<List<String>> docs, String term) {
        return Math.log(docs.size() / (double) df(docs, term) + 1);
    }

    public double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);
    }

    public static void main(String args[]) {
        List<String> doc1 = Arrays.asList("人工", "只能", "成为", "互联网", "大会", "焦点");
        List<String> doc2 = Arrays.asList("谷歌", "退出", "开源", "人工", "智能", "系统", "工具");
        List<String> doc3 = Arrays.asList("互联网", "的", "未来", "在", "人工", "智能");
        List<String> doc4 = Arrays.asList("谷歌", "开源", "机器", "学习", "工具");

        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3, doc4);
        TfIdCal tfIdCal = new TfIdCal();
        System.out.println(tfIdCal.tf(doc2, "谷歌"));
        System.out.println(tfIdCal.df(documents, "谷歌"));
        double tfidf = tfIdCal.tfIdf(doc2, documents, "谷歌");
        System.out.println("TF_IDF(谷歌) = " + tfidf);

    }

}
