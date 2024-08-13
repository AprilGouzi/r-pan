package com.imooc.pan.server.modules.recycle.controller;

import com.imooc.pan.core.response.R;
import com.imooc.pan.server.common.utils.UserIdUtil;
import com.imooc.pan.server.modules.file.vo.RPanUserFileVO;
import com.imooc.pan.server.modules.recycle.context.QueryRecycleFileListContext;
import com.imooc.pan.server.modules.recycle.service.IRecycleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author AprilGouzi
 * version 1.0
 * 回收站模块控制器
 */
@RestController
@Api(tags = "回收站模块")
public class RecycleController {

    @Autowired
    private IRecycleService iRecycleService;

    @ApiOperation(
            value = "获取回收站文件列表",
            notes = "该接口提供了获取回收站文件列表的功能",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @GetMapping("recycles")
    public R<List<RPanUserFileVO>> recycles() {
        QueryRecycleFileListContext context = new QueryRecycleFileListContext();
        context.setUserId(UserIdUtil.get());
        List<RPanUserFileVO> result = iRecycleService.recycles(context);
        return R.data(result);
    }

}
