package com.o2ocrm.system.mapper;

import com.o2ocrm.O2OCRMApp;
import com.o2ocrm.system.domain.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = O2OCRMApp.class)
public class DepartmentMapperTest {

    @Resource
    private DepartmentMapper mapper;

    /**
     * 测试Mapper接口是否可以获取到MySQL数据
     */
    @Test
    public void test1() {
        List<Department> deptList = mapper.selectAll();
        deptList.forEach(System.out::println);
    }

}