# Java Project

## Architect

![architect](./architect.png)

圖如所示，actor 階層關係為： mySystem(main) -> fileScanner -> fileParser -> aggregator。

運作模式也圖所示，每個 module 有對應的文字描述 handle 細節。

## Execution Result

指令：
```sh
gradle run
```

![Execution Result](./execution_result.png)
