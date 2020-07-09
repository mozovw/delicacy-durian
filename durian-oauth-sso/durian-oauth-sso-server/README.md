
### sso oauth2 的使用

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

### 遇到的问题


1、Possible CSRF detected - state parameter was required but no state could be found

**方案解决**

server.servlet.session.cookie.name=OAUTH2SESSION

[https://blog.csdn.net/JasonYang8088/article/details/80896486](https://blog.csdn.net/JasonYang8088/article/details/80896486)



### 关于权限拦截的使用 
&emsp;&emsp;关于ResourceServerConfigurerAdapter和WebSecurityConfigurerAdapter的configure(HttpSecurity http)方法使用，ResourceServerConfigurerAdapter比WebSecurityConfigurerAdapter的优先级要高（鉴于ResourceServerConfiguration的order=3，WebSecurityConfigurerAdapter的order=100）
所以如果是针对状态的请求（需要配置token消息头）过滤优先配置在ResourceServerConfigurerAdapter里面，而后才是针对有状态的请求需要在WebSecurityConfigurerAdapter配置


如下是针对/index请求配置permitAll的日志打印
```
2019-12-04 10:59:19.907  INFO 8680 --- [nio-8001-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2019-12-04 10:59:19.907  INFO 8680 --- [nio-8001-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2019-12-04 10:59:19.915  INFO 8680 --- [nio-8001-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 8 ms
2019-12-04 10:59:19.946 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/user/**']
2019-12-04 10:59:19.946 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Checking match of request : '/index'; against '/user/**'
2019-12-04 10:59:19.946 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : No matches found
2019-12-04 10:59:19.946 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Request '/index' matched by universal pattern '/**'
2019-12-04 10:59:19.947 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 1 of 14 in additional filter chain; firing Filter: 'WebAsyncManagerIntegrationFilter'
2019-12-04 10:59:19.948 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 2 of 14 in additional filter chain; firing Filter: 'SecurityContextPersistenceFilter'
2019-12-04 10:59:19.948 DEBUG 8680 --- [nio-8001-exec-1] w.c.HttpSessionSecurityContextRepository : No HttpSession currently exists
2019-12-04 10:59:19.948 DEBUG 8680 --- [nio-8001-exec-1] w.c.HttpSessionSecurityContextRepository : No SecurityContext was available from the HttpSession: null. A new one will be created.
2019-12-04 10:59:19.950 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 3 of 14 in additional filter chain; firing Filter: 'HeaderWriterFilter'
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 4 of 14 in additional filter chain; firing Filter: 'LogoutFilter'
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/logout', GET]
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Checking match of request : '/index'; against '/logout'
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/logout', POST]
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /index' doesn't match 'POST /logout'
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/logout', PUT]
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /index' doesn't match 'PUT /logout'
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/logout', DELETE]
2019-12-04 10:59:19.951 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /index' doesn't match 'DELETE /logout'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : No matches found
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 5 of 14 in additional filter chain; firing Filter: 'OAuth2ClientAuthenticationProcessingFilter'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Checking match of request : '/index'; against '/login'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 6 of 14 in additional filter chain; firing Filter: 'UsernamePasswordAuthenticationFilter'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /index' doesn't match 'POST /login'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 7 of 14 in additional filter chain; firing Filter: 'DefaultLoginPageGeneratingFilter'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 8 of 14 in additional filter chain; firing Filter: 'DefaultLogoutPageGeneratingFilter'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Checking match of request : '/index'; against '/logout'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 9 of 14 in additional filter chain; firing Filter: 'RequestCacheAwareFilter'
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.s.HttpSessionRequestCache        : saved request doesn't match
2019-12-04 10:59:19.952 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 10 of 14 in additional filter chain; firing Filter: 'SecurityContextHolderAwareRequestFilter'
2019-12-04 10:59:19.953 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 11 of 14 in additional filter chain; firing Filter: 'AnonymousAuthenticationFilter'
2019-12-04 10:59:19.955 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.a.AnonymousAuthenticationFilter  : Populated SecurityContextHolder with anonymous token: 'org.springframework.security.authentication.AnonymousAuthenticationToken@cf8cb311: Principal: anonymousUser; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@b364: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: null; Granted Authorities: ROLE_ANONYMOUS'
2019-12-04 10:59:19.955 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 12 of 14 in additional filter chain; firing Filter: 'SessionManagementFilter'
2019-12-04 10:59:19.955 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 13 of 14 in additional filter chain; firing Filter: 'ExceptionTranslationFilter'
2019-12-04 10:59:19.955 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index at position 14 of 14 in additional filter chain; firing Filter: 'FilterSecurityInterceptor'
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/logout', GET]
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Checking match of request : '/index'; against '/logout'
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/logout', POST]
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /index' doesn't match 'POST /logout'
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/logout', PUT]
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /index' doesn't match 'PUT /logout'
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : Trying to match using Ant [pattern='/logout', DELETE]
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Request 'GET /index' doesn't match 'DELETE /logout'
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.web.util.matcher.OrRequestMatcher  : No matches found
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.u.matcher.AntPathRequestMatcher  : Checking match of request : '/index'; against '/index'
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.a.i.FilterSecurityInterceptor    : Secure object: FilterInvocation: URL: /index; Attributes: [permitAll]
2019-12-04 10:59:19.956 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.a.i.FilterSecurityInterceptor    : Previously Authenticated: org.springframework.security.authentication.AnonymousAuthenticationToken@cf8cb311: Principal: anonymousUser; Credentials: [PROTECTED]; Authenticated: true; Details: org.springframework.security.web.authentication.WebAuthenticationDetails@b364: RemoteIpAddress: 0:0:0:0:0:0:0:1; SessionId: null; Granted Authorities: ROLE_ANONYMOUS
2019-12-04 10:59:19.962 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.access.vote.AffirmativeBased       : Voter: org.springframework.security.web.access.expression.WebExpressionVoter@4b9b85a4, returned: 1
2019-12-04 10:59:19.962 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.a.i.FilterSecurityInterceptor    : Authorization successful
2019-12-04 10:59:19.962 DEBUG 8680 --- [nio-8001-exec-1] o.s.s.w.a.i.FilterSecurityInterceptor    : RunAsManager did not change Authentication object
2019-12-04 10:59:19.963 DEBUG 8680 --- [nio-8001-exec-1] o.s.security.web.FilterChainProxy        : /index reached end of additional filter chain; proceeding with original chain
```

我们会发现如下依次过滤器链

```
WebAsyncManagerIntegrationFilter
SecurityContextPersistenceFilter
HeaderWriterFilter
LogoutFilter
OAuth2ClientAuthenticationProcessingFilter（配置在ResourceServerConfiguration需要拦截的请求）
UsernamePasswordAuthenticationFilter
DefaultLoginPageGeneratingFilter
DefaultLogoutPageGeneratingFilter
SecurityContextHolderAwareRequestFilter
AnonymousAuthenticationFilter
SessionManagementFilter
ExceptionTranslationFilter
FilterSecurityInterceptor
```

### 实现代码
[https://github.com/mozovw/delicacy-durian/tree/master/durian-oauth-sso](https://github.com/mozovw/delicacy-durian/tree/master/durian-oauth-sso)
