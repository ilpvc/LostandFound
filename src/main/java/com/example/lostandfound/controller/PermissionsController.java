package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.Permissions;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ilpvc
 * @since 2023-03-22 09:28:33
 */
@RestController
@RequestMapping("/lostandfound/permissions")
public class PermissionsController {

    @Autowired
    PermissionsService permissionsService;


    @GetMapping("/")
    public R getAllPermissions() {

        return R.ok().data("list", permissionsService.list());
    }

    @GetMapping("/{id}")
    public R getPermissionsById(@PathVariable int id) {
        return R.ok().data("item", permissionsService.getById(id));
    }


    @GetMapping("pagePermissions/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<Permissions> page = new Page<>(pageNo, pageCount);
        permissionsService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addPermissions")
    public R addPermissions(@RequestBody Permissions permissions) {
        boolean save = permissionsService.save(permissions);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updatePermissions")
    public R updatePermissions(@RequestBody Permissions permissions) {
        boolean b = permissionsService.updateById(permissions);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deletePermissions(@PathVariable int id) {

        boolean flag = permissionsService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

}
