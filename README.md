# Validator 验证器

## 使用

Maven项目可添加引用：

```xml

<dependency>
    <groupId>net.renfei</groupId>
    <artifactId>validator</artifactId>
    <version>1.0.0</version>
</dependency>
```

然后给需要校验的对象字段上增加注解，实例化一个```Verifier```校验器对象，执行```verifier.verification(object)```，获得一个```VerifiedReport```
校验报告对象，例如使用内置的注解：

```java
public class ObjectToBeVerified {
    @NotNullCheck(businessName = "统一社会信用代码不为空")
    @UnifiedCodeCheck(businessName = "统一社会信用代码")
    private String UnifiedCodeTestField2 = "91310000775785552L";
}

public class ValidatorTests {
    @Test
    public void test() {
        ObjectToBeVerified objectToBeVerified = new ObjectToBeVerified();
        Verifier verifier = new Verifier();
        VerifiedReport report = verifier.verification(objectToBeVerified);
        System.out.println("| 校验结果：" + report.getPassed());
        System.out.println("| 校验对象：" + report.getObjectName());
        System.out.println("| 结果详情：");
        for (VerifiedReportDetail detail : report.getDetailList()
        ) {
            System.out.printf("| 校验结果：【" + (detail.getPassed() ? "通过" : "不通过") + "】\t");
            System.out.printf("业务字段：【" + detail.getBusinessName() + "】\t");
            System.out.printf("数据原值：【" + detail.getFieldValue() + "】");
            System.out.println();
        }
    }
}
```

## 内置注解

| 注解                      | 描述           | 参数                             |
|-------------------------|--------------|--------------------------------|
| @ChinaIdCardCheck       | 中国身份证号校验     | businessName=业务描述              |
| @ChinaMobilePhoneCheck  | 中国移动通信手机号校验  | businessName=业务描述              |
| @ChinaMvnoPhoneCheck    | 中国手机虚拟运营商号校验 | businessName=业务描述              |
| @ChinaPhoneCheck        | 中国手机号校验      | businessName=业务描述              |
| @ChinaTelecomPhoneCheck | 中国电信手机号校验    | businessName=业务描述              |
| @ChinaUnicomPhoneCheck  | 中国联通手机号校验    | businessName=业务描述              |
| @DomainNameCheck        | 域名格式校验       | businessName=业务描述              |
| @EmailCheck             | 电子邮箱格式校验     | businessName=业务描述              |
| @IsNullCheck            | 数据为空验证       | businessName=业务描述              |
| @LengthCheck            | 数据长度验证       | length=长度,businessName=业务描述    |
| @LengthMaxCheck         | 数据最大长度验证     | max=长度,businessName=业务描述       |
| @MaxCheck               | 数据最大值验证      | max=长度,businessName=业务描述       |
| @MinCheck               | 数据最小值验证      | min=长度,businessName=业务描述       |
| @NotNullCheck           | 数据不为空验证      | businessName=业务描述              |
| @PatternCheck           | 正则表达式验证      | regexp=正则表达式,businessName=业务描述 |
| @UnifiedCodeCheck       | 统一社会信用代码验证   | rbusinessName=业务描述             |

## 自定义验证器与自定义注解

在很多复杂的业务中，数据校验可能需要联合多个字段进行校验，这个时候就需要自定义验证器，甚至自定义注解。

### 自定义验证器

在下面的案例中，我们将自定义一个验证器，例如需求是：当对象的```name```为空时，```email```为必填项：

```java
public class Demo{
    public void test() {
        MyObj myObj = new MyObj();
        net.renfei.validator.Verifier verifier = new net.renfei.validator.Verifier();
        VerifiedReport report = verifier.verification(myObj);
        System.out.println("校验结果：" + report.getPassed());
        System.out.println("校验对象：" + report.getObjectName());
        System.out.println("结果详情：");
        for (VerifiedReportDetail detail : report.getDetailList()
        ) {
            System.out.printf("校验结果：【" + (detail.getPassed() ? "通过" : "不通过") + "】\t");
            System.out.printf("业务字段：【" + detail.getBusinessName() + "】\t");
            System.out.printf("数据原值：【" + detail.getFieldValue() + "】");
            System.out.println();
        }
    }
}

public class MyObj {
    private String name;
    @Validator(businessName = "邮箱字段", verifier = MyVerifier.class)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

public class MyVerifier implements Verifier {
    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        field.setAccessible(true);
        if (object instanceof MyObj) {
            MyObj myObj = (MyObj) object;
            try {
                report.setPassed(true);
                if (myObj.getName() == null) {
                    if (myObj.getEmail() == null) {
                        report.setPassed(false);
                    }
                }
                report.setFieldValue(field.get(object) == null ? null : field.get(object).toString());
                report.setFieldType(field.getType().getName());
            } catch (IllegalAccessException e) {
                report.setPassed(false);
                e.printStackTrace();
            }
        } else {
            report.setPassed(false);
        }
        report.setFieldName(field.getName());
        report.setVerificationTime(new Date());
        report.setVerifierName(this.getClass().getName());
        report.setValidatorName(annotation.toString());
        report.setBusinessName(((Validator) annotation).businessName());
        return report;
    }
}
```

执行结果得到：

```text
校验结果：false
校验对象：net.renfei.demo.MyObj
结果详情：
校验结果：【不通过】	业务字段：【邮箱字段】	数据原值：【null】
```

### 自定义注解

如果```net.renfei.validator.annotation.Validator```注解不够用，那就需要自定义注解，例如我们想传递年龄进去，那就这样：
```java
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Validator(verifier = MyVerifier.class)
public @interface Age {
    long age() default 18;
}
```

那么在自定义验证器中这样获取注解：
```java
public class MyVerifier implements Verifier {
    @Override
    public VerifiedReportDetail verification(Annotation annotation, Object object, Field field) {
        VerifiedReportDetail report = new VerifiedReportDetail();
        Age age = (Age) annotation;
        // 拿到注解中传递的值
        age.age();
        // 省略一些业务代码
        return report;
    }
}
```