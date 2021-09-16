package com.atguigu.test;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/*推荐Spring项目就可以使用Spring的单元测试，可以自动注入我们需要的组件*/
/*导入SpringTest模块
* @ContextConfiguration指定Spring配置文件的位置
* 直接autowired
* */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    /*
    * 测试DepartmentMapper
    * */
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD(){
//         //1.创建SpringIOC容器
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        //2.从容器中获取mapper
//        DepartmentMapper bean = context.getBean(DepartmentMapper.class);
        System.out.println(departmentMapper);
        /*插入几个部门*/
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"宣传部"));

        /*生成员工的数据*/
//        employeeMapper.insertSelective(new Employee(null,"Jerry","M","Jerry@163.com",1));

        //批量插入多个员工
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String s = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,"s","M",s+"@163.com",1));
        }
        System.out.println("批量执行完毕");
    }
}
