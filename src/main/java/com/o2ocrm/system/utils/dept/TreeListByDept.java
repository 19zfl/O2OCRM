package com.o2ocrm.system.utils.dept;

import com.o2ocrm.system.domain.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName:TreeListByDept
 * @Description: 获取部门树
 * @Author:zfl19
 * @CreateDate:2024/3/30 18:07
 */

public class TreeListByDept {

    public static List<Department> getDeptTreeList(List<Department> deptList) {
        // 创建一个空集合存放数据最后返回给前端
        List<Department> treeDeptList = new ArrayList<>();
        // 创建一个集合存放遍历数据
        Map<Long, Department> map = new HashMap<>();
        for (Department dept : deptList) {
            map.put(dept.getId(), dept);
        }
        // 遍历部门数据
        for (Department dept : deptList) {
            // 判断是否有父级id
            if (dept.getParentId() == null) {
                // 没有，说明是顶级
                treeDeptList.add(dept);
            } else {
                // 有，将自己存入父级对象的子集中
                // 通过子级部门的parentId拿到父级部门对象
                Department parentDept = map.get(dept.getParentId());
                // 将自己存入父级对象中
                parentDept.getChildren().add(dept);
            }
        }
        return treeDeptList;
    }

}
