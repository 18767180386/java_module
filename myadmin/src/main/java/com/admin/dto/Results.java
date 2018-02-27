package com.admin.dto;
import java.util.List;

import com.admin.util.JsonUtils;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Results {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private Object ext;

    private Object other;

    public static Results build(Integer status, String msg, Object data, String ext) {
        return new Results(status, msg, data, ext);
    }

    public static Results build(Integer status, String msg, Object data, String ext,String other) {
        return new Results(status, msg, data, ext,other);
    }

    public static Results build(Integer status, String msg, Object data) {
        return new Results(status, msg, data);
    }

    public static Results success(Object data) {
        return new Results(data);
    }


    public static Results success() {
        return new Results(null);
    }

    public Results() {

    }

    public static Results build(Integer status, String msg) {
        return new Results(status, msg, null);
    }

    /**
     * 返回值并打印断点信息：msg和logMsg一致
     * @param status
     * @param msg
     * @param logger
     * @return
     */
    public static Results build(Integer status, String msg,Logger logger) {
        logger.error("------------------"+msg+"----------------");
        return new Results(status, msg, null);
    }
    /**
     * 返回值并打印断点信息：msg和logMsg不一致
     * @param status
     * @param msg
     * @param logger
     * @return
     */
    public static Results build(Integer status, String msg,Logger logger,String logMsg) {
        logger.error("-------------"+logMsg+"----------------");
        return new Results(status, msg, null);
    }

    public Results(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Results(Integer status, String msg, Object data, Object ext) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ext = ext;
    }

    public Results(Integer status, String msg, Object data, Object ext, Object other) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ext = ext;
        this.other = other;
    }

    public Results(Object data) {
        this.status = 200;
        this.msg = "操作成功";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }



    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    /**
     * 将json结果集转化为Result对象
     *
     * @param jsonData json数据
     * @param clazz Result中的object类型
     * @return
     */
    public static Results formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, Results.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static Results format(String json) {
        try {
            return MAPPER.readValue(json, Results.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static Results formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public String toString() {
        System.out.println("数据："+ JsonUtils.objectToJson(this));
        return super.toString();
    }
}
