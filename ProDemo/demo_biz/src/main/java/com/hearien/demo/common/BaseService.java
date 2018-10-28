package com.hearien.demo.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName BaseService
 * @Author WangHaiyang
 * @Date 2018/10/25 11:37
 **/
public abstract class BaseService<D extends Mapper<T>,T/* extends BaseEntity*/> {

    @Autowired
    private D dao;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public boolean insertSeletive(T entity){
        //entity.setCreateTime(sdf.format(new Date()));
        return dao.insertSelective(entity)>0?true:false;
    }

    public boolean deleteByPrimaryKey(Object key){
        return dao.deleteByPrimaryKey(key)>0?true:false;
    }

    public boolean updateSelective(T entity){
        //entity.setUpdateId(sdf.format(new Date()));
        return dao.updateByPrimaryKeySelective(entity)>0?true:false;
    }

    public T selectOne(T entity){
        return dao.selectOne(entity);
    }

    public T selectByPrimaryKey(Object key){
        return dao.selectByPrimaryKey(key);
    }

    public PageInfo<T> getList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<T> list = dao.selectAll();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        return pageInfo;
    }
}
