# ![]() Detective Mask
![GitHub](https://img.shields.io/github/license/creatureox/detective-mask)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/creatureox/detective-mask/CI)

旨在日志结构化数据脱敏

## 快速开始
1. 实现`Mask`类
```java
import com.github.creatureox.detectivemask.mask.Mask;

public class MobileMask implements Mask {
    @Override
    public String doMask(String value) {
    // 数据处理
    return maskedValue;
    }
}
```

2. 配置匹配规则
   * 关键字匹配：detective-mask.&lt;Mask实现类名称&gt;.keywords
   * 正则表达式匹配：detective-mask.&lt;Mask实现类名称&gt;.pattern
```shell
detective-mask.MobileMask.keywords=phone
detective-mask.MobileMask.pattern=^1\\d{10}$
```
3. `logback.xml`中添加配置项

```xml
<configuration>
   <conversionRule conversionWord="msg" converterClass="com.github.creatureox.detectivemask.DetectiveMaskLogConverter" />
   
   <appender>...</appender>
</configuration>
```

4. 运行应用查看日志
```
输入：{"mobile":"12345678901"}
输出：{"mobile":"123******01"}
```

```
输入：{"phone":"22345678901"}
输出：{"phone":"223******01"}
```


