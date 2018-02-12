package com.ddf.admin.module.dic.controller;

import com.ddf.admin.common.utils.PageUtils;
import com.ddf.admin.common.utils.R;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.dic.dto.Area;
import com.ddf.entity.dic.dto.Xiaoqu;
import com.ddf.entity.dic.eo.AreaType;
import com.ddf.entity.dic.eo.XiaoquLoopLine;
import com.ddf.entity.dic.query.XiaoquQuery;
import com.ddf.entity.dic.vo.AreaVo;
import com.ddf.entity.dic.vo.XiaoquVo;
import com.ddf.entity.response.ApiResponse;
import com.ddf.reference.dic.AreaReference;
import com.ddf.reference.dic.XiaoquReference;
import com.ddf.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 小区
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-02-03 10:52:32
 */

@Controller
@RequestMapping("/dic/xiaoqu")
public class XiaoquController {

    @Autowired
    private XiaoquReference xiaoquReference;
    @Autowired
    private AreaReference areaReference;
//    @Autowired
//    private FileReference fileReference;
    @GetMapping()
    @RequiresPermissions("dic:xiaoqu:xiaoqu")
    String Xiaoqu(Model model) {
//        model.addAttribute("loopLineList", XiaoquLoopLine.values());
        return "dic/xiaoqu/xiaoqu";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("dic:xiaoqu:xiaoqu")
    public PageUtils list(@ModelAttribute XiaoquQuery query) {
        query.getXiaoqu().setId(query.getId());
        query.getXiaoqu().setName(query.getName());
        query.getXiaoqu().setAddress(query.getAddress());
        query.getXiaoqu().setDisplayState(query.getDisplayState());
        //查询列表数据
        ApiResponse<Page<XiaoquVo>> response = xiaoquReference.pageQuery(query);
        List<XiaoquVo> list = new ArrayList<>();
        if (response !=null && response.getData() !=null){
            list = response.getData().getList();
            for (XiaoquVo xiaoquVo : list) {
                String cityId = xiaoquVo.getCityId();
                String districtId = xiaoquVo.getDistrictId();
                String circleId = xiaoquVo.getCircleId();
                String positionStr ="";
                if (StringUtil.isNotEmpty(cityId)){
                    ApiResponse<Area> query1 = areaReference.query(cityId);
                    if (query1 !=null && query1.getData() !=null){
                        positionStr = query1.getData().getName()+"-";
                    }
                }
                if (StringUtil.isNotEmpty(districtId)){
                    ApiResponse<Area> query2 = areaReference.query(districtId);
                    if (query2 !=null && query2.getData() !=null){
                        positionStr += query2.getData().getName()+"-";
                    }
                }
                if (StringUtil.isNotEmpty(circleId)){
                    ApiResponse<Area> query3 = areaReference.query(circleId);
                    if (query3 !=null && query3.getData() !=null){
                        positionStr += query3.getData().getName();
                    }
                }
                xiaoquVo.setPosition(positionStr);
            }

        }
        Page<XiaoquVo> data  = response.getData();
        PageUtils pageUtils = new PageUtils(data.getList(), (int) data.getTotalCount());
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("dic:xiaoqu:add")
    String add(Model model) {
        model.addAttribute("loopLineList", XiaoquLoopLine.values());
        model.addAttribute("cityList", areaReference.queryListByType(AreaType.CITY).getData());
        return "dic/xiaoqu/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("dic:xiaoqu:edit")
    String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("loopLineList", XiaoquLoopLine.values());
        model.addAttribute("cityList", areaReference.queryListByType(AreaType.CITY).getData());
        model.addAttribute("districtList", areaReference.queryListByType(AreaType.DISTRICT).getData());
        model.addAttribute("circleList", areaReference.queryListByType(AreaType.CIRCLE).getData());
        model.addAttribute("xiaoqu", xiaoquReference.query(id).getData());
        return "dic/xiaoqu/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("dic:xiaoqu:add")
    public R save(HttpServletRequest request,Xiaoqu xiaoqu) {
//        ApiResponse<List<String>> fileUrls = fileReference.fileUpload4sameFileName(request);
        String result = xiaoquReference.create(xiaoqu).getResult();
        if(StringUtil.isNotEmpty(result)){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("dic:xiaoqu:edit")
    public R update(Xiaoqu xiaoqu) {
        if(xiaoquReference.modify(xiaoqu).getData()){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("dic:xiaoqu:remove")
    public R remove(String id) {
        if(xiaoquReference.remove(id).getData()){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("dic:xiaoqu:batchRemove")
    public R remove(@RequestParam("ids[]") String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            if(xiaoquReference.remove(ids[i]).getData()){
                return R.ok();
            }
        }
        return R.error();
    }
    /**
     * 联动查询 城市
     */
    @PostMapping("/code")
    @ResponseBody
    @RequiresPermissions("dic:xiaoqu:xiaoqu")
    public List<AreaVo> code(@RequestParam("id") String id) {
        List<AreaVo> data = areaReference.queryChilds(id).getData();
        return data;
    }
}
