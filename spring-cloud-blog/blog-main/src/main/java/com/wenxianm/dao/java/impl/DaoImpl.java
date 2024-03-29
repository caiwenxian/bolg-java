package com.wenxianm.dao.java.impl;

import com.wenxianm.dao.java.Dao;
import com.wenxianm.model.enums.C;
import com.wenxianm.model.po.base.Method;
import com.wenxianm.model.po.base.Po;
import com.wenxianm.model.po.base.Pram;
import com.wenxianm.model.po.base.WherePrams;
import com.wenxianm.model.po.base.annotation.FieldName;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.wenxianm.utils.GenericsUtils;
import com.wenxianm.utils.RandomUtil;
import com.wenxianm.utils.SqlUtil;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Repository
public class DaoImpl<T extends Po, PK extends Serializable> implements Dao<T, PK> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplateASS;

    private Class<T> entityClass;

    private String pkName;                  //主键字段

    private String idName;                  //对应id名称

    private String seq;                     //当前主键

    private String tableName;

    private List<Pram> sqlParms;


    private List<Pram> selectSqlParms;

    private SqlUtil<T> sqlUtil;

    @SuppressWarnings("unchecked")
    public DaoImpl() {
        super();

        this.sqlUtil = new SqlUtil<T>();

        this.entityClass = (Class<T>) GenericsUtils.getSuperClassGenricType(this.getClass());

        this.sqlParms = this.sqlUtil.getPramList(this.entityClass);

        this.selectSqlParms = this.sqlUtil.getPramListOfSelect(this.entityClass);

        this.tableName = this.sqlUtil.getTableName(this.entityClass);

        this.pkName = "id";

        this.idName = "id";

        this.seq = "id";

    }


    @Override
    public int addLocal(T po) {
        // TODO Auto-generated method stub

        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";

        List<Pram> pramList = SqlUtil.getPramListofStatic(po);

        int index = 0;
        for (int i = 0; i < pramList.size(); i++) {
            if (pramList.get(i).getValue() == null || (pramList.get(i).getValue() + "").equals("0")) {
                continue;
            } else {
                if (index > 0) {
                    prams += ",";
                    values += ",";
                }
                prams += pramList.get(i).getFile();
                Object value = pramList.get(i).getValue();
                if (value instanceof byte[]) {
                    values += "'" + new String((byte[]) value) + "'";
                } else if (value instanceof String) {
                    values += "'" + value + "'";
                } else {
                    values += value;
                }

                index++;
            }
        }
        sql += prams + ") value (" + values + ");";

        SqlUtil.setFileValue(po, "id", nextId());

        logger.debug(sql);
        return sqlSessionTemplateASS.insert("addLocal", sql);

    }

    @Override
    public int add(T po, boolean withId) {
        // TODO Auto-generated method stub
        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";

        List<Pram> pramList = SqlUtil.getPramListofStatic(po);
//        SqlUtil.getFileValue(po, "id");
        if (!withId) {
            pramList.add(new Pram("id", RandomUtil.getUid()));
        }
        for (int i = 0; i < pramList.size(); i++) {
            if ("createTime".equals(pramList.get(i).getFile()) || "modifyTime".equals(pramList.get(i).getFile())) {
                pramList.get(i).setValue(LocalDateTime.now());
            }
            prams += pramList.get(i).getFile();
            if (pramList.get(i).getValue() == null) {
                values += "null";
            } else {
                values += "'" + pramList.get(i).getValue() + "'";
            }

            if (i < pramList.size() - 1) {
                prams += ",";
                values += ",";
            }


        }
        sql += prams + ") value (" + values + ");";

//        SqlUtil.setFileValue(po, "id", RandomUtil.getUid());
//        sql = sql.replaceAll("'", "''");  //单引号替换成两个单引号
        logger.debug("sql:" + sql);
        return sqlSessionTemplateASS.insert("addEntity", sql);
    }

    @Override
    public T get(PK id) {
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if (i < selectSqlParms.size() - 1) {
                sql += ",";
            } else {
                sql += " ";
            }
        }
        sql += " from " + tableName + " where id='" + id + "';";
        logger.debug("sql:" + sql);
        Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne(
                "getById", sql);

        return handleResult(resultMap, this.entityClass);
    }

    @Override
    public Serializable getField(PK id, String fileName) {
        // TODO Auto-generated method stub
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询字段失败(无法找到" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        } else {
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + " where id='" + id + "';";
        Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne(
                "getFieldById", sql);
        return (Serializable) resultMap.get(fileName);
    }

    @Override
    public T get(WherePrams where) {
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if (i < selectSqlParms.size() - 1) {
                sql += ",";
            } else {
                sql += " ";
            }
        }
        sql += "from " + tableName + where.getWherePrams() + ";";

        Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne(
                "getByParm", sql);

        return handleResult(resultMap, this.entityClass);
    }

    @Override
    public Serializable getFile(WherePrams where, String fileName) {
        // TODO Auto-generated method stub
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询字段失败(无法找到" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        } else {
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + where.getWherePrams() + ";";
        Map<String, Object> resultMap = sqlSessionTemplateASS.selectOne(
                "getFieldByParm", sql);
        return (Serializable) resultMap.get(fileName);
    }

    @Override
    public List<T> list(WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if (i < selectSqlParms.size() - 1) {
                sql += ",";
            } else {
                sql += " ";
            }
        }
        sql += "from " + tableName + where.getWherePrams() + ";";

        List<Map<String, Object>> selectList = sqlSessionTemplateASS.selectList("selectList", sql);

        List<T> list = new ArrayList<T>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(map, this.entityClass);
            list.add(t);
        }

        return list;

    }

    @Override
    public Serializable[] listFile(WherePrams where, String fileName) {
        // TODO Auto-generated method stub

        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询指定字段集失败(无法序列化" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        } else {
            tabField = annotation.name() + " as " + fileName;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + where.getWherePrams() + ";";
        List<Map<String, Object>> resultMap = sqlSessionTemplateASS.selectList("selectListField", sql);

        Serializable[] fields = new Serializable[resultMap.size()];

        for (int i = 0; i < resultMap.size(); i++) {
            if (null != resultMap.get(i)) {
                fields[i] = (Serializable) resultMap.get(i).get(fileName);
            }
        }

        return fields;
    }

    @Override
    public List<Map<String, Serializable>> listFiles(WherePrams where, String[] files) {
        // TODO Auto-generated method stub
        String tabField = "";
        int index = 1;
        for (String field : files) {
            Field f = sqlUtil.getField(this.entityClass, field);
            if (null == f) {
                logger.error("查询指定字段集失败(无法序列化" + this.entityClass + "中的" + field + "字段)");
            }
            FieldName annotation = f.getAnnotation(FieldName.class);
            if (null == annotation) {
                tabField += sqlUtil.toTableString(field) + " as " + field;
            } else {
                tabField += annotation.name() + " as " + field;
            }
            if (index < files.length) {
                tabField += ",";
            }

            index++;
        }

        String sql = "select ";
        sql += tabField + " from " + tableName + where.getWherePrams() + ";";
        List<Map<String, Serializable>> resultMap = sqlSessionTemplateASS.selectList("selectListField", sql);

        return resultMap;
    }

    @Override
    public int updateLocal(T po) {
        // TODO Auto-generated method stub

        Serializable id = sqlUtil.getFileValue(po, "id");

        if (null == id) {
            return 0;
        }
        String sql = "update " + tableName + " set ";
        List<Pram> prams = sqlUtil.getPramList(po);
        int len = 0;
        for (int i = 0; i < prams.size(); i++) {
            if (null != prams.get(i).getValue()) {
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[]) {
                    sql += "'" + new String((byte[]) value) + "'";
                } else if (value instanceof String || value instanceof LocalDateTime) {
                    sql += "'" + value + "'";
                } else {
                    sql += value;
                }

//              sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() - 1) {
                    sql += ",";
                }
                len++;
            }
        }
//        if (len < prams.size()) {
//            sql = sql.substring(0, sql.length() - 1);
//        }
        sql += " where id='" + id + "';";

        return sqlSessionTemplateASS.update("updateLocal", sql);
    }

    @Override
    public int update(T po) {
        // TODO Auto-generated method stub

        Serializable id = sqlUtil.getFileValue(po, "id");

        if (null == id) {
            return 0;
        }
        String sql = "update " + tableName + " set ";

        List<Pram> prams = sqlUtil.getPramList(po);

        for (int i = 0; i < prams.size(); i++) {
            if (null != prams.get(i).getValue()) {
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[]) {
                    sql += "'" + new String((byte[]) value) + "'";
                } else if (value instanceof String) {
                    sql += "'" + value + "'";
                } else if(value instanceof LocalDateTime) {
                    sql += "'" + value + "'";
                }else {
                    sql += value;
                }
//              sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() - 1) {
                    sql += ",";
                }
            } else {
                sql += prams.get(i).getFile() + "=null";
                if (i < prams.size() - 1) {
                    sql += ",";
                }
            }
        }
        sql += " where id='" + id + "';";
        logger.debug("sql:" + sql);
        return sqlSessionTemplateASS.update("updateEntity", sql);
    }

    @Override
    public int updateLocal(T po, WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "update " + tableName + " set ";
        List<Pram> prams = sqlUtil.getPramList(po);
        for (int i = 0; i < prams.size(); i++) {
            if (null != prams.get(i).getValue()) {
                sql += prams.get(i).getFile() + "=";
                Object value = prams.get(i).getValue();
                if (value instanceof byte[]) {
                    sql += "'" + new String((byte[]) value) + "'";
                } else if (value instanceof String) {
                    sql += "'" + value + "'";
                } else {
                    sql += value;
                }
//              sql += prams.get(i).getFile() + "='" + prams.get(i).getValue() + "'";
                if (i < prams.size() - 1) {
                    sql += ",";
                }
            }
        }
        sql += where.getWherePrams() + "';";

        return sqlSessionTemplateASS.update("updateLocalByPram", sql);

    }

    @Override
    public int update(T po, WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "update " + tableName + " set ";
        Object[] o = new Object[sqlParms.size()];
        for (int i = 0; i < sqlParms.size(); i++) {
            if (null != sqlParms.get(i).getValue()) {
                sql += sqlParms.get(i).getFile() + "=" + sqlParms.get(i).getValue();
                if (i < sqlParms.size() - 1) {
                    sql += ",";
                }
            } else {
                sql += sqlParms.get(i).getFile() + "=null";
                if (i < sqlParms.size() - 1) {
                    sql += ",";
                }
            }
        }
        sql += where.getWherePrams() + "';";

        return sqlSessionTemplateASS.update("updateByPram", sql);

    }

    @Override
    public int del(PK id) {
        // TODO Auto-generated method stub
        String sql = "delete from " + tableName + " where id=" + id;

        return sqlSessionTemplateASS.delete("deleteById", sql);
    }

    @Override
    public int del(WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "delete from " + tableName + where.getWherePrams();

        return sqlSessionTemplateASS.delete("deleteByparm", sql);
    }

    @Override
    public List<Map<String, Object>> listBySql(String sql) {
        // TODO Auto-generated method stub

        List<Map<String, Object>> selectList = sqlSessionTemplateASS.selectList("selectBySql", sql);
        return selectList;
    }

    @Override
    public int excuse(String sql) {
        // TODO Auto-generated method stub
        return sqlSessionTemplateASS.update("excuse", sql);
    }

    @Override
    public long count(WherePrams where) {
        // TODO Auto-generated method stub

        String sql = "select count(*) from ";

        sql += tableName + where.getWherePrams();

        long count = sqlSessionTemplateASS.selectOne("selectCountByParm", sql);

        return count;
    }

    @Override
    public long size() {
        // TODO Auto-generated method stub
        String sql = "select count(*) from " + tableName;
        long count = sqlSessionTemplateASS.selectOne("selectCount", sql);
        return count;
    }

    @Override
    public boolean isExist(T po) {
        // TODO Auto-generated method stub
        WherePrams wherePrams = Method.createDefault();

        List<Pram> list = SqlUtil.getPramListofStatic(po);

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                wherePrams = Method.where(list.get(i).getFile(), C.EQ, (Serializable) list.get(i).getValue());
            } else {
                wherePrams.and(list.get(i).getFile(), C.EQ, (Serializable) list.get(i).getValue());
            }
        }


        return count(wherePrams) > 0;
    }

    @Override
    public boolean isExist(WherePrams where) {
        // TODO Auto-generated method stub
        return count(where) > 0;
    }

    @Override
    public List<T> in(String fileName, Serializable[] values) {
        // TODO Auto-generated method stub

        String sql = "select ";
        for (int i = 0; i < sqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if (i < selectSqlParms.size() - 1) {
                sql += ",";
            } else {
                sql += " ";
            }
        }
        sql += "from " + tableName + " where " + fileName + " in ";
        String value = "(";
        for (int i = 0; i < values.length; i++) {
            if (i < values.length - 1) {
                value += values[i] + ",";
            } else {
                value += values[i] + ");";
            }
        }
        sql += value;

        List<Map<String, Object>> selectList = sqlSessionTemplateASS.selectList("selectIn", sql);

        List<T> list = new ArrayList<T>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(map, this.entityClass);
            list.add(t);
        }

        return list;
    }

    private T handleResult(Map<String, Object> resultMap, Class<T> tClazz) {
        if (null == resultMap) {
            return null;
        }
        T t = null;
        try {
            t = tClazz.newInstance();
        } catch (InstantiationException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        } catch (IllegalAccessException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        }
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            Serializable val = (Serializable) entry.getValue();
            try {
                SqlUtil.setFileValue(t, key, val);
            } catch (Exception e) {
                // TODO: handle exception
                logger.error("/********************************");
                logger.error("/实例化Bean失败(" + this.entityClass + ")不能赋值到字段(" + key + "):"
                        + e.getMessage());
                logger.error("/********************************");
            }
        }
        return t;
    }

    /**
     * 获取某表的下一个Id
     */
    public long nextId() {
        String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='" + tableName + "'";
        Long idVal = sqlSessionTemplateASS.selectOne("fetchSeqNextval", sql);
        if (null == idVal) {
            logger.error("/********************************");
            logger.error("/获取id失败，" + tableName + "表异常。请检查是否存在或者配为自增主键");
            logger.error("/********************************");
            return 0;
        }
        return idVal;

    }


}
