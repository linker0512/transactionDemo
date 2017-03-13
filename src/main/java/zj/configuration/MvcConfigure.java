package zj.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import zj.interceptor.UserSecurityInterceptor;

/**
 * Created by zj on 2017-2-23.
 */
@Configuration
public class MvcConfigure extends WebMvcConfigurerAdapter{

    @Autowired
    UserSecurityInterceptor userSecurityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userSecurityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login.do")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register.do");
    }

//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        List<Cache> caches = new ArrayList<Cache>();
//        ConcurrentMapCache a = new ConcurrentMapCache();
//        caches.add(new ConcurrentMapCache("test"));
//        cacheManager.setCaches(caches);
//        return cacheManager;
//    }
    @Bean
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
        factory.setShared(true);
        return factory;
    }

}
