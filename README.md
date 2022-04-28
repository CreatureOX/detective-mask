# ![]() Detective Mask
Designed to data desensitization

## Quick Start
1. implement `Mask`
```java
import com.creatureox.Mask;

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

3. run `DetectiveMaskStarter#process(String log)` to check result
```
input：{"mobile":"12345678901"}
output：{"mobile":"123******01"}
```

```
input：{"phone":"22345678901"}
output：{"phone":"223******01"}
```

