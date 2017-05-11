# NetExcutor
NetExcutor

## 用法

* Android Studio
	
	```
	compile 'com.changtu.development:net-excutor:1.0.0'
	```
  


## 用法示例

```java

NetExcutor.executorCommonRequest(this, new CommonNetUIListener<Object>() {


            @Override
            public void onComplete(Object bean, NetUtils.NetRequestStatus netRequestStatus) {

                if(NetUtils.NetRequestStatus.SUCCESS==netRequestStatus){
                    // do your business
                    
                }else{
                    Toast.makeText(HomeActivity.this, netRequestStatus.getNote(), Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public List<KeyValuePair> submitNetParams() {
                List<KeyValuePair> keypair = new ArrayList<>();
                keypair.add(new com.changtu.development.net.BasicKeyValuePair("account", 
                        "zhangsan"));
                keypair.add(new
                        com.changtu.development.net.BasicKeyValuePair("password", "zhangsan"));
                return keypair;
            }

            @Override
            public String createUrl() {
                return "http://172.18.0.69/zentao/user-login.html";
            }
        });
```

## 目前对以下需求进行了封装
* 一般的get请求
```java
 @Override
 public NetRequestConfig.Method getMethod() {
        return NetRequestConfig.Method.GET;
 }
 ```
* 一般的post请求
默认配置
