package com.example.hello_world_with_mvc.service;

import com.example.hello_world_with_mvc.entity.User;

public interface DatabaseService {
    // 总之，在执行效率方面，JdbcTemplate通常会有更好的性能，因为它允许直接编写SQL语句并有更多的控制权。MyBatis在性能和便捷性之间取得了平衡，而JPA则以自动化和简化为主要优势，但可能在某些情况下性能较差。具体选择哪种方法取决于项目需求、开发团队的经验和技能以及应用场景。
    public User getVaiUserID(String cid);
    
}