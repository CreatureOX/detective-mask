# ![]() Detective Mask
旨在数据脱敏

## 快速开始
1. 实现`Mask`类
```java
import com.creatureox.Mask;

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

3. 运行 `DetectiveMaskStarter#process(String log)` 查看结果
```
输入：{"mobile":"12345678901"}
输出：{"mobile":"123******01"}
```

```
输入：{"phone":"22345678901"}
输出：{"phone":"223******01"}
```


