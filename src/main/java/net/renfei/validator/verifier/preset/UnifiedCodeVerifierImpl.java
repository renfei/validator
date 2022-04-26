package net.renfei.validator.verifier.preset;

import net.renfei.validator.annotation.preset.UnifiedCodeCheck;
import net.renfei.validator.entity.VerifiedReportDetail;
import net.renfei.validator.verifier.Verifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 社会统一信用代码校验
 *
 * @author renfei
 */
public class UnifiedCodeVerifierImpl implements Verifier {
    private static final int[] WI = {1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28};

    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        try {
            report.setPassed(field.get(object) != null && check(field.get(object).toString()));
            report.setFieldValue(field.get(object) == null ? null : field.get(object).toString());
            report.setFieldType(field.getType().getName());
        } catch (IllegalAccessException e) {
            report.setPassed(false);
            e.printStackTrace();
        }
        report.setFieldName(field.getName());
        report.setVerificationTime(new Date());
        report.setVerifierName(this.getClass().getName());
        report.setValidatorName(annotation.toString());
        report.setBusinessName(((UnifiedCodeCheck) annotation).businessName());
        return report;
    }

    public boolean check(String code) {
        if (code.length() != 18) {
            return false;
        }
        //用于计算当前判断的统一社会信用代码位数
        int index = 0;
        //用于存放当前位的统一社会信用代码
        char testc;
        //用于存放代码字符和加权因子乘积之和
        int tempSum = 0;
        int tempNum = 0;
        for (index = 0; index <= 16; index++) {
            testc = code.charAt(index);

            if (index == 0) {
                if (testc != '1' && testc != '5' && testc != '9' && testc != 'Y') {
                    return false;
                }
            }

            if (index == 1) {
                if (testc != '1' && testc != '2' && testc != '3' && testc != '9') {
                    return false;
                }
            }

            tempNum = charToNum(testc);
            //验证代码中是否有错误字符
            if (tempNum != -1) {
                tempSum += WI[index] * tempNum;
            } else {
                return false;
            }
        }
        tempNum = 31 - tempSum % 31;
        if (tempNum == 31) {
            tempNum = 0;
        }
        //按照GB/T 17710标准对统一社会信用代码前17位计算校验码，并与第18位校验位进行比对
        return charToNum(code.charAt(17)) == tempNum;
    }

    private int charToNum(char c) {
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            case 'G':
                return 16;
            case 'H':
                return 17;
            case 'J':
                return 18;
            case 'K':
                return 19;
            case 'L':
                return 20;
            case 'M':
                return 21;
            case 'N':
                return 22;
            case 'P':
                return 23;
            case 'Q':
                return 24;
            case 'R':
                return 25;
            case 'T':
                return 26;
            case 'U':
                return 27;
            case 'W':
                return 28;
            case 'X':
                return 29;
            case 'Y':
                return 30;
            default:
                return -1;
        }
    }
}
