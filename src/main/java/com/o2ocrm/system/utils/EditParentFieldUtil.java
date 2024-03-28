package com.o2ocrm.system.utils;

import com.o2ocrm.system.domain.Department;

/**
 * @ClassName:EditParentFieldUtil
 * @Description: 修改父级id和path工具类
 * @Author:zfl19
 * @CreateDate:2024/3/28 21:56
 */

public class EditParentFieldUtil {

    public static void updateParentAndPath(Department dept) {
        // 获取此数据的父级id数组
        Long[] parentIds = dept.getParentIds();
        // 创建字符串操作对象StringBuffer
        StringBuffer sb = new StringBuffer();
        // 判空
        if (parentIds != null && parentIds.length > 0) {
            for (Long ids : parentIds) {
                sb.append("/" + ids);
            }
            sb.append("/" + dept.getId());
        }
        dept.setDirPath(sb.toString());
        dept.setParentId(parentIds[parentIds.length - 1]);
    }

}
