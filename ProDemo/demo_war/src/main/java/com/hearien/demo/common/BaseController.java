package com.hearien.demo.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.apache.shiro.subject.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName BaseController
 * @Author WangHaiyang
 * @Date 2018/10/25 14:26
 **/
public abstract class BaseController implements ApplicationContextAware {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @Autowired
    Environment environment;
    /**
     * Spring应用上下文环境
     */
    protected ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取param.property的配置信息
     *
     * @param key
     * @return
     */
    protected String getProperty(String key) {
        return environment.getProperty(key);
    }

    /**
     * 获取param.property的配置信息
     *
     * @param key
     * @return
     */
    protected String getProperty(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }

    @ModelAttribute
    protected void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    /**
     * 取得当前Subject的简化函数.
     */
    protected Subject getCurrSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 取得HttpSession的简化函数.
     *
     * @param flag
     *            创建session标识,true：创建；false：不创建
     */
    protected Session getSession(boolean flag) {
        return SecurityUtils.getSubject().getSession(flag);
    }

    /**
     * 取得HttpSession的简化函数.默认不创建新的session
     */
    protected Session getSession() {
        return getSession(false);
    }

    /**
     * 取得HttpSession中Attribute的简化函数.
     */
    protected Object getSessionAttribute(String key) {
        Session session = getSession();
        return session != null ? session.getAttribute(key) : null;
    }

    /**
     * 设置HttpSession中Attribute的简化函数.
     */
    protected void setSessionAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 删除HttpSession中Attribute的简化函数.
     */
    protected Object removeSessionAttribute(String key) {
        return getSession().removeAttribute(key);
    }


    /**
     * 检查上传文件的大小
     *
     * @param limitSize
     * @throws SizeLimitExceededException
     */
    protected void checkUploadFileSize(long limitSize) throws FileUploadBase.SizeLimitExceededException {
        // 如果文件大小超出限制
        String fileSizeLimitExceeded = (String) request.getAttribute("fileSizeLimitExceeded");
        // 实际大小
        String fileActualSize = (String) request.getAttribute("fileActualSize");
        fileActualSize = fileActualSize == null ? "0" : fileActualSize;
        Long fileSize = Long.valueOf(fileActualSize);
        if (fileSizeLimitExceeded != null) {
            throw new FileUploadBase.SizeLimitExceededException("文件大小限制",Long.valueOf(fileActualSize),limitSize);
        } else if (fileSize > limitSize && limitSize != 0) {
            throw new FileUploadBase.SizeLimitExceededException("文件超出大小",Long.valueOf(fileActualSize),limitSize);
        }
    }
}
