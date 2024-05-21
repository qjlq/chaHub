package com.example.hello_world_with_mvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.hello_world_with_mvc.entity.Group;
import com.example.hello_world_with_mvc.entity.User;
import com.example.hello_world_with_mvc.service.DatabaseService;

import lombok.extern.slf4j.Slf4j; //log
@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // 总之，在执行效率方面，JdbcTemplate通常会有更好的性能，因为它允许直接编写SQL语句并有更多的控制权。MyBatis在性能和便捷性之间取得了平衡，而JPA则以自动化和简化为主要优势，但可能在某些情况下性能较差。具体选择哪种方法取决于项目需求、开发团队的经验和技能以及应用场景。
    @Override
    public User getVaiUserID(String cid){
        try {
            String sql = "select uid,username,password from chat.user where uid = ?";
            List<User> result = jdbcTemplate.query(sql,(resultset,i)->{
                User user = new User();
                user.setUserId(resultset.getString("uid"));
                user.setUserName(resultset.getString("username"));
                user.setPassWord(resultset.getString("password"));
                return user;
            },cid);
            return result.get(0);
        } catch (Exception e) {
            User user = new User();
            user.setUserId(null);
            log.info("{} user does ont exit error:{}",cid,e.toString());
            return user;
        }

    }
    @Override
    public int addUser(String uid,String password,String username){

        return jdbcTemplate.update("insert into chat.user(uid, username, password) values(?, ?,?)",uid, username, password);
    }

    @Override
    public String getNameByCid(String cid){
        try {
            String sql = "select username from chat.user where uid = ?";
            List<String> result = jdbcTemplate.query(sql,(resultset,i)->{
                return resultset.getString("username");
            },cid);
            return result.get(0);
        } catch (Exception e) {
            log.info("{} cid does not exit error:{}",cid,e.toString());
            return null;
        }
    }

    @Override
    public List<String> getCidByGid(String gid){
        try {
            String sql = "select cid from chat.groupMember where gid = ?";
            List<String> result = jdbcTemplate.query(sql,(resultset,i)->{
                return resultset.getString("cid");
            },gid);
            return result;
        } catch (Exception e) {
            log.info("{} gid does not exit error:{}",gid,e.toString());
            return null;
        }
    }
    @Override
    public List<String> getGidByCid(String cid){
        try {
            String sql = "select gid from chat.groupMember where cid = ?";
            List<String> result = jdbcTemplate.query(sql,(resultset,i)->{
                return resultset.getString("gid");
            },cid);
            return result;
        } catch (Exception e) {
            log.info("{} cid does not exit error:{}",cid,e.toString());
            return null;
        }
    }

    @Override
    public String getGnamebyGid(String gid){
        try {
            String sql = "select groupname from chat.groupinfo where gid = ?";
            List<String> result = jdbcTemplate.query(sql,(resultset,i)->{
                return resultset.getString("username");
            },gid);
            return result.get(0);
        } catch (Exception e) {
            log.info("{} gid does not exit error:{}",gid,e.toString());
            return null;
        }
    }

    @Override
    public Group getGroupGid(String gid){
        try {
            String sql = "select groupname,ccid,number from chat.groupinfo where gid = ?";
            List<Group> result = jdbcTemplate.query(sql,(resultset,i)->{
                Group group = new Group();
                //user.setUserId(resultset.getString("gid"));
                group.setgroupname(resultset.getString("groupname"));
                group.setcid(resultset.getString("ccid"));
                group.setnumber(resultset.getString("number"));

                return group;
            },gid);
            return result.get(0);
        } catch (Exception e) {
            Group group = new Group();
            group.setgid(null);
            log.info("{} user does ont exit error:{}",gid,e.toString());
            return group;
        }
    }

    @Override
    public int AddtoGroupInfo(Group group){
        return jdbcTemplate.update("insert into chat.groupinfo(gid, ccid, groupname, number) values(?,?,?,?)",group.getgid(), group.getcid(), group.getgroupname(),group.getnumber());
    }

    @Override
    public int AddtoGroupMember(String gid,String cid){
        return jdbcTemplate.update("insert into chat.groupMember(gid, cid ) values(?, ?)",gid,cid);
    }
}
