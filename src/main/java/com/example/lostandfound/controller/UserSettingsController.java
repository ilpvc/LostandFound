package com.example.lostandfound.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.lostandfound.entity.UserSettings;
import com.example.lostandfound.entity.VO.R;
import com.example.lostandfound.service.UserSettingsService;
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
@RequestMapping("/lostandfound/user-settings")
public class UserSettingsController {


    @Autowired
    UserSettingsService userSettingsService;


    @GetMapping("/")
    public R getAllUserSettings() {

        return R.ok().data("list", userSettingsService.list());
    }

    @GetMapping("/{id}")
    public R getUserSettingsById(@PathVariable int id) {
        return R.ok().data("item", userSettingsService.getById(id));
    }


    @GetMapping("pageUserSettings/{pageNo}/{pageCount}")
    public R pageConfig(@PathVariable int pageNo, @PathVariable int pageCount) {
        Page<UserSettings> page = new Page<>(pageNo, pageCount);
        userSettingsService.page(page, null);
        return R.ok().data("list", page);
    }


    @PostMapping("/addUserSettings")
    public R addUserSettings(@RequestBody UserSettings userSettings) {
        boolean save = userSettingsService.save(userSettings);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PutMapping("/updateUserSettings")
    public R updateUserSettings(@RequestBody UserSettings userSettings) {
        boolean b = userSettingsService.updateById(userSettings);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteUserSettings(@PathVariable int id) {

        boolean flag = userSettingsService.removeById(id);
        if (flag) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }
}
