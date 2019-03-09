package ac.cn.chm.base.dao;

import ac.cn.chm.base.Page;
import ac.cn.chm.base.PageData;

import java.util.List;

/**
 * dao层实现标准
 * @param <K> 实体类主键
 * @param <V> 实体类类型
 */
public interface IDAO<K,V> {

    /**
     * 添加一条新的数据
     *
     * @param v 实体类类型
     * @return 添加成功返回添加成功条数，添加失败返回-1
     */
    int doCreate(V v) throws Exception;

    /**
     * 按条件编辑指定信息（这样的话,ids要怎么处理呢？）
     *
     * @param pd 要更新的数据
     * @return 编辑成功返回编辑成功条数，编辑失败返回-1
     */
    int doUpdate(PageData pd) throws Exception;

    /**
     * 根据ID逻辑删除指定数据(直接调用了doUpdate)
     *
     * @param id 逻辑删除的指定数据
     * @return 删除成功返回1,删除失败返回-1
     */
    //int doRemove(K id) throws Exception;

    /**
     * 批量删除(直接调用了doUpdate)
     * @param ids 要批量删除的数据的id合集
     * @return 删除成功返回被删除数据的数量，删除失败返回-1
     */
    //int doRemoves(List<K> ids) throws Exception;

    /**
     * 查询信息列表
     *
     * @param page
     * @return
     */
    List<V> listPagePd(Page page) throws Exception;

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    V findPdById(K id) throws Exception;

    /**
     * 根据ids获取指定的信息(直接调用了listPagePd)
     *
     * @param ids
     * @return
     */
    //List<V> findPdByIDs(List<K> ids) throws Exception;
}
