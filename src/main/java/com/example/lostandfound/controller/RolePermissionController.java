package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.RolePermission;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/role-permission")
@CrossOrigin
public class RolePermissionController {

    @Autowired
    RolePermissionService rolePermissionService;


    @GetMapping("/")
    public R getAllRolePermission() {

        return R.ok().data("list", rolePermissionService.list());
    }

    @GetMapping("/{id}")
    public R getRolePermissionById(@PathVariable int id) {
        return R.ok().data("item", rolePermissionService.getById(id));
    }


    @GetMapping("pageRolePermission/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<RolePermission> page = new Page<>(pageNo, pageCount);
        rolePermissionService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addRolePermission")
    public R addRolePermission(@RequestBody RolePermission rolePermission) {
        boolean save = rolePermissionService.save(rolePermission);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateRolePermission")
    public R updateRolePermission(@RequestBody RolePermission rolePermission) {
        boolean b = rolePermissionService.updateById(rolePermission);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteRolePermission(@PathVariable int id) {

        boolean flag = rolePermissionService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

}
