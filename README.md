# ![]() Detective Mask
![GitHub](https://img.shields.io/github/license/creatureox/detective-mask)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/creatureox/detective-mask/CI)

Designed to desensitize log structured data

## Quick Start
1. implement `Mask`
```java
import com.github.creatureox.detectivemask.mask.Mask;

public class MobileMask implements Mask {
    @Override
    public String doMask(String value) {
    // value process
    return maskedValue;
    }
}
```

2. configure match rule
    * keywords match：detective-mask.&lt;mask-name&gt;.keywords
    * regex match：detective-mask.&lt;mask-name&gt;.pattern
```shell
detective-mask.MobileMask.keywords=phone
detective-mask.MobileMask.pattern=^1\\d{10}$
```

3. add conversionRule in `logback.xml`

```xml

<configuration>
   <conversionRule conversionWord="msg" converterClass="com.github.creatureox.detectivemask.DetectiveMaskLogConverter"/>

   <appender>...</appender>
</configuration>
```

4. run application to check log
```
input：{"mobile":"12345678901"}
output：{"mobile":"123******01"}
```

```
input：{"phone":"22345678901"}
output：{"phone":"223******01"}
```

