package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Roles;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/roles")
public class RolesController {


    @Autowired
    RolesService rolesService;


    @GetMapping("/")
    public R getAllRoles() {

        return R.ok().data("list", rolesService.list());
    }

    @GetMapping("/{id}")
    public R getRolesById(@PathVariable int id) {
        return R.ok().data("item", rolesService.getById(id));
    }


    @GetMapping("pageRoles/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<Roles> page = new Page<>(pageNo, pageCount);
        rolesService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addRoles")
    public R addRoles(@RequestBody Roles roles) {
        boolean save = rolesService.save(roles);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateRoles")
    public R updateRoles(@RequestBody Roles roles) {
        boolean b = rolesService.updateById(roles);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteRoles(@PathVariable int id) {

        boolean flag = rolesService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

}
