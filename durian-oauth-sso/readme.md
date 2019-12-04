
### sso oauth 的使用

- oauth获取token
```$xslt：
1、 页面输入如下地址：
http://localhost:8000/oauth/authorize?response_type=code&client_id=client_id_1&redirect_uri=http://localhost:8001/code&scope=write
2、 拦截跳转到登陆页面：
http://localhost:8000/login
3、 登陆成功后会获取如下内容：
http://localhost:8001/code?code=2tBHA3
4、通过授权码获取token：
http://localhost:8000/oauth/token?grant_type=authorization_code&client_id=client_id_1&client_secret=123456&redirect_uri=http://localhost:8001/code&code=2tBHA3
5、结果如下：
{
  access_token: "ae7e6bbd-0603-4e22-b9be-546c9a673025",
  token_type: "bearer",
  refresh_token: "fb882868-68c7-4532-ba79-4bc368b1b6f0",
  expires_in: 3599,
  scope: "write"
 }
```

- 页面跳转
```$xslt：
1、 页面输入如下地址：
http://localhost:8001/index  ==》 login
2、拦截跳转到登陆页面：
http://localhost:8000/login
3、 登陆成功后会跳转到：
http://localhost:8001/securedPage
```
