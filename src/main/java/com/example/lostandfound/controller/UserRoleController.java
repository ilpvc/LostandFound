package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.UserRole;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户角色表 前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/user-role")
public class UserRoleController {


    @Autowired
    UserRoleService userRoleService;


    @GetMapping("/")
    public R getAllUserRole() {

        return R.ok().data("list", userRoleService.list());
    }

    @GetMapping("/{id}")
    public R getUserRoleById(@PathVariable int id) {
        return R.ok().data("item", userRoleService.getById(id));
    }


    @GetMapping("pageUserRole/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<UserRole> page = new Page<>(pageNo, pageCount);
        userRoleService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addUserRole")
    public R addUserRole(@RequestBody UserRole userRole) {
        boolean save = userRoleService.save(userRole);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateUserRole")
    public R updateUserRole(@RequestBody UserRole userRole) {
        boolean b = userRoleService.updateById(userRole);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteUserRole(@PathVariable int id) {

        boolean flag = userRoleService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }
}
