package com.roll.casserole.todel;

import java.util.Arrays;

/**
 * @author roll
 * created on 2019-11-26 14:10
 */
public class PTest {
    static String a = "newnew,cbhb,bszxyh,hhnsh,pdsyh,cdyh,xtyh,jzbank,zgcbank,hmccb,yqjqnxs,taccb,bankofdl,tjns,uccb,whlhbank,gsyh,zjkccb,sdlm,qlbchina,bhnsh,809,jxrt,cebbank,bobcfc,mslsfx,hrbb,cyxfjr,zzbank,yillionbank,baixin,szsccb,bjyhhzfh,bocfullertonbank,bjyhshfh,bankofbeijing,citicbank,bjbank,ccbz,ksdao,cmbchina,shbankloan,suningcredit,bhzcf,njcb,jnbank,njcbzx,cmbchinalsxd,ecitic,wzyh,hbbankxfjr,dongguanbank,zhongyouxf,gzcb,drcbank,cscbemd,jinmeixin,58jinrong,hkbea,ccpingan,pinganhq,hzlhyh,jxfl,fmyh,gybank,ncssyyh,cqcbankxyk,yznsh,huzhou,nbcb,czcbank,qzyh,sfcapital,crbank,xib,gzccbsz,qzccbank,ljnhh,gdabc,xwxysd,baoshengbank,kshbank,gdrcu,fjnsh,cdrcb,goldendata,zxxj,mfhj,jnyh,sdictktrust,wrcb,jcyhzb,qhdbank,homecreditcfc,jingdongsc,meituan,zjx,lhp,dadi,ygcx,picc,hanhua,liangxin,bcm,oppojr,zhaoyang";

    public static void main(String[] args) {
        String[] b = a.split(",");
        for (int i = 0; i < b.length; i++) {
            b[i] = "\"" + b[i] + "\"";
        }
        System.out.println(Arrays.toString(b));
    }
}
