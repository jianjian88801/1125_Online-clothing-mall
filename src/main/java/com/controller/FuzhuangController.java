
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 服装
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fuzhuang")
public class FuzhuangController {
    private static final Logger logger = LoggerFactory.getLogger(FuzhuangController.class);

    @Autowired
    private FuzhuangService fuzhuangService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("fuzhuangDeleteStart",1);params.put("fuzhuangDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = fuzhuangService.queryPage(params);

        //字典表数据转换
        List<FuzhuangView> list =(List<FuzhuangView>)page.getList();
        for(FuzhuangView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FuzhuangEntity fuzhuang = fuzhuangService.selectById(id);
        if(fuzhuang !=null){
            //entity转view
            FuzhuangView view = new FuzhuangView();
            BeanUtils.copyProperties( fuzhuang , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody FuzhuangEntity fuzhuang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fuzhuang:{}",this.getClass().getName(),fuzhuang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<FuzhuangEntity> queryWrapper = new EntityWrapper<FuzhuangEntity>()
            .eq("fuzhuang_name", fuzhuang.getFuzhuangName())
            .eq("fuzhuang_types", fuzhuang.getFuzhuangTypes())
            .eq("fuzhuang_kucun_number", fuzhuang.getFuzhuangKucunNumber())
            .eq("fuzhuang_price", fuzhuang.getFuzhuangPrice())
            .eq("fuzhuang_clicknum", fuzhuang.getFuzhuangClicknum())
            .eq("shangxia_types", fuzhuang.getShangxiaTypes())
            .eq("fuzhuang_delete", fuzhuang.getFuzhuangDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FuzhuangEntity fuzhuangEntity = fuzhuangService.selectOne(queryWrapper);
        if(fuzhuangEntity==null){
            fuzhuang.setFuzhuangClicknum(1);
            fuzhuang.setShangxiaTypes(1);
            fuzhuang.setFuzhuangDelete(1);
            fuzhuang.setCreateTime(new Date());
            fuzhuangService.insert(fuzhuang);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FuzhuangEntity fuzhuang, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,fuzhuang:{}",this.getClass().getName(),fuzhuang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<FuzhuangEntity> queryWrapper = new EntityWrapper<FuzhuangEntity>()
            .notIn("id",fuzhuang.getId())
            .andNew()
            .eq("fuzhuang_name", fuzhuang.getFuzhuangName())
            .eq("fuzhuang_types", fuzhuang.getFuzhuangTypes())
            .eq("fuzhuang_kucun_number", fuzhuang.getFuzhuangKucunNumber())
            .eq("fuzhuang_price", fuzhuang.getFuzhuangPrice())
            .eq("fuzhuang_clicknum", fuzhuang.getFuzhuangClicknum())
            .eq("shangxia_types", fuzhuang.getShangxiaTypes())
            .eq("fuzhuang_delete", fuzhuang.getFuzhuangDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FuzhuangEntity fuzhuangEntity = fuzhuangService.selectOne(queryWrapper);
        if("".equals(fuzhuang.getFuzhuangPhoto()) || "null".equals(fuzhuang.getFuzhuangPhoto())){
                fuzhuang.setFuzhuangPhoto(null);
        }
        if(fuzhuangEntity==null){
            fuzhuangService.updateById(fuzhuang);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<FuzhuangEntity> list = new ArrayList<>();
        for(Integer id:ids){
            FuzhuangEntity fuzhuangEntity = new FuzhuangEntity();
            fuzhuangEntity.setId(id);
            fuzhuangEntity.setFuzhuangDelete(2);
            list.add(fuzhuangEntity);
        }
        if(list != null && list.size() >0){
            fuzhuangService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<FuzhuangEntity> fuzhuangList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            FuzhuangEntity fuzhuangEntity = new FuzhuangEntity();
//                            fuzhuangEntity.setFuzhuangName(data.get(0));                    //服装名称 要改的
//                            fuzhuangEntity.setFuzhuangPhoto("");//照片
//                            fuzhuangEntity.setFuzhuangTypes(Integer.valueOf(data.get(0)));   //服装类型 要改的
//                            fuzhuangEntity.setFuzhuangKucunNumber(Integer.valueOf(data.get(0)));   //服装库存 要改的
//                            fuzhuangEntity.setFuzhuangPrice(Integer.valueOf(data.get(0)));   //购买获得积分 要改的
//                            fuzhuangEntity.setFuzhuangOldMoney(data.get(0));                    //服装原价 要改的
//                            fuzhuangEntity.setFuzhuangNewMoney(data.get(0));                    //现价/积分 要改的
//                            fuzhuangEntity.setFuzhuangClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            fuzhuangEntity.setFuzhuangContent("");//照片
//                            fuzhuangEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            fuzhuangEntity.setFuzhuangDelete(1);//逻辑删除字段
//                            fuzhuangEntity.setCreateTime(date);//时间
                            fuzhuangList.add(fuzhuangEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        fuzhuangService.insertBatch(fuzhuangList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = fuzhuangService.queryPage(params);

        //字典表数据转换
        List<FuzhuangView> list =(List<FuzhuangView>)page.getList();
        for(FuzhuangView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FuzhuangEntity fuzhuang = fuzhuangService.selectById(id);
            if(fuzhuang !=null){

                //点击数量加1
                fuzhuang.setFuzhuangClicknum(fuzhuang.getFuzhuangClicknum()+1);
                fuzhuangService.updateById(fuzhuang);

                //entity转view
                FuzhuangView view = new FuzhuangView();
                BeanUtils.copyProperties( fuzhuang , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody FuzhuangEntity fuzhuang, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fuzhuang:{}",this.getClass().getName(),fuzhuang.toString());
        Wrapper<FuzhuangEntity> queryWrapper = new EntityWrapper<FuzhuangEntity>()
            .eq("fuzhuang_name", fuzhuang.getFuzhuangName())
            .eq("fuzhuang_types", fuzhuang.getFuzhuangTypes())
            .eq("fuzhuang_kucun_number", fuzhuang.getFuzhuangKucunNumber())
            .eq("fuzhuang_price", fuzhuang.getFuzhuangPrice())
            .eq("fuzhuang_clicknum", fuzhuang.getFuzhuangClicknum())
            .eq("shangxia_types", fuzhuang.getShangxiaTypes())
            .eq("fuzhuang_delete", fuzhuang.getFuzhuangDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FuzhuangEntity fuzhuangEntity = fuzhuangService.selectOne(queryWrapper);
        if(fuzhuangEntity==null){
            fuzhuang.setFuzhuangDelete(1);
            fuzhuang.setCreateTime(new Date());
        fuzhuangService.insert(fuzhuang);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
