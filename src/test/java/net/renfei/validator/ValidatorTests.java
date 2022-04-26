package net.renfei.validator;

import net.renfei.validator.entity.VerifiedReport;
import net.renfei.validator.entity.VerifiedReportDetail;
import org.junit.Test;

/**
 * 校验测试
 *
 * @author renfei
 */
public class ValidatorTests {
    @Test
    public void test() {
        ObjectToBeVerified objectToBeVerified = new ObjectToBeVerified();
        Verifier verifier = new Verifier();
        VerifiedReport report = verifier.verification(objectToBeVerified);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| 校验结果：" + report.getPassed());
        System.out.println("| 校验对象：" + report.getObjectName());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("| 结果详情：");
        System.out.println("|-------------------------------------------------");
        for (VerifiedReportDetail detail : report.getDetailList()
        ) {
            System.out.printf("| 校验结果：【" + (detail.getPassed() ? "通过" : "不通过") + "】\t");
            System.out.printf("业务字段：【" + detail.getBusinessName() + "】\t");
            System.out.printf("数据原值：【" + detail.getFieldValue() + "】");
            System.out.println();
            System.out.println("|-------------------------------------------------");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}


