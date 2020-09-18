package com.IpManage.config;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
    private static Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

    private final static String fileName = "build";
    private final static String frontDir = System.getProperty("user.dir") + "/frontend/web/" + fileName + "/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 注册前端项目静态资源
        registryResources(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }


    /**
     * 解决String类型返回JSON时报错问题
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> item : converters) {
            if (item instanceof StringHttpMessageConverter) {
                converters.remove(item);
                break;
            }
        }

    }

    //Make a filter that matches files and directories
    final static IOFileFilter FILE_FILTER = new IOFileFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return true;
        }

        @Override
        public boolean accept(File file) {
            return file.getParent().endsWith("/" + fileName) && file.getName().endsWith(".html");
        }
    };

    //Make a filter that matches directories
    final static DirectoryFileFilter DIRECTORY_FILE_FILTER = new DirectoryFileFilter() {
        @Override
        public boolean accept(File file) {
            return file.isDirectory() && file.getParent().endsWith("/" + fileName);
        }
    };

    /**
     * 注册静态资源
     *
     * @param registry
     */
    private void registryResources(ResourceHandlerRegistry registry) {
        logger.info("Reading resources in the folder: {}", frontDir);
        Collection<File> files = readStaticResources(frontDir);
        for (File file : files) {
            if (file.isDirectory())
                registryStaticDirectoryResource(registry, file);
            else
                registryStaticFileResource(registry, file);
        }
    }

    /**
     * 读取资源
     *
     * @param path
     * @return
     */
    private Collection<File> readStaticResources(String path) {
        if (!checked(path)) {
            logger.warn("Web static resources failed to load, you can deploy your front project by a web server if you want access it.");
            return new ArrayList<>();
        }
        Collection<File> files = FileUtils.listFiles(new File(path), FILE_FILTER, DIRECTORY_FILE_FILTER);
        files.removeIf(file -> fileName.equals(file.getName()));
        return files;
    }

    private boolean checked(String path) {
        File file = new File(path);
        if (!file.exists()) {
            logger.warn("Web static resources directory is not exist.");
            return false;
        }
        if (!file.isDirectory()) {
            logger.warn("Your web static resources is not a directory.");
            return false;
        }

        return file.exists();
    }

    /**
     * 注册文件
     *
     * @param registry
     * @param file
     */
    private void registryStaticFileResource(ResourceHandlerRegistry registry, File file) {
        registryResource(registry, file.getName(), "file:" + frontDir);
    }

    /**
     * 注册文件夹
     *
     * @param registry
     * @param file
     */
    private void registryStaticDirectoryResource(ResourceHandlerRegistry registry, File file) {
        registryResource(registry, file.getName() + "/**", "file:" + frontDir + file.getName() + "/");
    }

    /**
     * 注册资源
     *
     * @param registry
     * @param pathPattern
     * @param resourceLocation
     */
    private void registryResource(ResourceHandlerRegistry registry, String pathPattern, String resourceLocation) {
        registry.addResourceHandler(pathPattern)
                .addResourceLocations(resourceLocation);

        logger.info("Registry static file resource: pathPattern = {}, resourceLocation = {}", pathPattern, resourceLocation);
    }
}
