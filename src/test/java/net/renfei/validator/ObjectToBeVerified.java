package net.renfei.validator;

import net.renfei.validator.annotation.preset.IsNullCheck;
import net.renfei.validator.annotation.preset.NotNullCheck;
import net.renfei.validator.annotation.preset.UnifiedCodeCheck;

/**
 * 待校验对象
 *
 * @author renfei
 */
public class ObjectToBeVerified {
    @NotNullCheck(businessName = "该字段不为空")
    private Object notNullCheckTestField = null;

    @IsNullCheck(businessName = "该字段为空")
    private Object isNullCheckTestField = new Object();

    @UnifiedCodeCheck(businessName = "统一社会信用代码")
    private String UnifiedCodeTestField1 = "91310000775785553L";

    @NotNullCheck(businessName = "统一社会信用代码不为空")
    @UnifiedCodeCheck(businessName = "统一社会信用代码")
    private String UnifiedCodeTestField2 = "91310000775785552L";
}
