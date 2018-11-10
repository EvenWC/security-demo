package com.wangcheng.zeus.config;

import com.wangcheng.zeus.core.config.authentication.account.AccountAuthenticationConfigurer;
import com.wangcheng.zeus.core.config.authentication.constant.ZeusSecurityConstants;
import com.wangcheng.zeus.core.config.authentication.mobile.SmsAuthenticationConfigurer;
import com.wangcheng.zeus.core.config.properties.ZeusProperties;
import com.wangcheng.zeus.core.config.validate.code.config.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

/**
 * @author : evan
 * @Date: 2018/9/17 20:36
 * @Description:
 */

@Configuration
public class ZeusWebSecurityConfigurerAdapter extends AbstractWebSecurityConfig {

    @Autowired
    private ZeusProperties zeusProperties;

    @Autowired
    private SmsAuthenticationConfigurer smsAuthenticationConfigurer;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    private AccountAuthenticationConfigurer accountAuthenticationConfigurer;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用默认的用户名和密码登录相关
        super.applyPasswordAuthenticationConfig(http);
        http
            .apply(validateCodeSecurityConfig)
            .and()
            //自定义用户名和密码登录
            .apply(accountAuthenticationConfigurer)
            .and()
            .apply(smsAuthenticationConfigurer)
            .and()
            .apply(springSocialConfigurer)
            .and()
            .authorizeRequests()
            //过滤放行
            .antMatchers(ZeusSecurityConstants.URI_EXCLUDE(zeusProperties.getBrowser().getLoginPage()))
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable().cors().and();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
