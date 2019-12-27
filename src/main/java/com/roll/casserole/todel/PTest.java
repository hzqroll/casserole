package com.roll.casserole.todel;

import java.util.Arrays;

/**
 * @author roll
 * created on 2019-11-26 14:10
 */
public class PTest {
    static String a = "newnew,cbhb,hhnsh,jzbank,zgcbank,hmccb,yqjqnxs,taccb,bankofdl,tjns,uccb,whlhbank,gsyh,sdlm,qlbchina,809,jxrt,cebbank,bobcfc,cyxfjr,zzbank,baixin,szsccb,ccbz,ksdao,cmbchina,shbankloan,bhzcf,njcb,jnbank,njcbzx,cmbchinalsxd,wzyh,hbbankxfjr,dongguanbank,gzcb,drcbank,cscbemd,jinmeixin,58jinrong,hkbea,ccpingan,pinganhq,hzlhyh,jxfl,fmyh,gybank,ncssyyh,cqcbankxyk,yznsh,huzhou,nbcb,czcbank,qzyh,sfcapital,crbank,xib,gzccbsz,qzccbank,xwxysd,baoshengbank,kshbank,gdrcu,fjnsh,goldendata,zxxj,jnyh,wrcb,jcyhzb,meituan,ygcx,liangxin,bcm";

    public static void main(String[] args) {
        String[] b = a.split(",");
        for (int i = 0; i < b.length; i++) {
            b[i] = "\"" + b[i] + "\"";
        }
        System.out.println(Arrays.toString(b));
    }
}
