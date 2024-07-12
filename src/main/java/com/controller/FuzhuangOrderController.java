
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
 * 服装订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fuzhuangOrder")
public class FuzhuangOrderController {
    private static final Logger logger = LoggerFactory.getLogger(FuzhuangOrderController.class);

    @Autowired
    private FuzhuangOrderService fuzhuangOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private AddressService addressService;
    @Autowired
    private FuzhuangService fuzhuangService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private FuzhuangCommentbackService fuzhuangCommentbackService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = fuzhuangOrderService.queryPage(params);

        //字典表数据转换
        List<FuzhuangOrderView> list =(List<FuzhuangOrderView>)page.getList();
        for(FuzhuangOrderView c:list){
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
        FuzhuangOrderEntity fuzhuangOrder = fuzhuangOrderService.selectById(id);
        if(fuzhuangOrder !=null){
            //entity转view
            FuzhuangOrderView view = new FuzhuangOrderView();
            BeanUtils.copyProperties( fuzhuangOrder , view );//把实体数据重构到view中

                //级联表
                AddressEntity address = addressService.selectById(fuzhuangOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                    view.setAddressYonghuId(address.getYonghuId());
                }
                //级联表
                FuzhuangEntity fuzhuang = fuzhuangService.selectById(fuzhuangOrder.getFuzhuangId());
                if(fuzhuang != null){
                    BeanUtils.copyProperties( fuzhuang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setFuzhuangId(fuzhuang.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(fuzhuangOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody FuzhuangOrderEntity fuzhuangOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fuzhuangOrder:{}",this.getClass().getName(),fuzhuangOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            fuzhuangOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        fuzhuangOrder.setInsertTime(new Date());
        fuzhuangOrder.setCreateTime(new Date());
        fuzhuangOrderService.insert(fuzhuangOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FuzhuangOrderEntity fuzhuangOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,fuzhuangOrder:{}",this.getClass().getName(),fuzhuangOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            fuzhuangOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<FuzhuangOrderEntity> queryWrapper = new EntityWrapper<FuzhuangOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FuzhuangOrderEntity fuzhuangOrderEntity = fuzhuangOrderService.selectOne(queryWrapper);
        if(fuzhuangOrderEntity==null){
            fuzhuangOrderService.updateById(fuzhuangOrder);//根据id更新
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
        fuzhuangOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<FuzhuangOrderEntity> fuzhuangOrderList = new ArrayList<>();//上传的东西
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
                            FuzhuangOrderEntity fuzhuangOrderEntity = new FuzhuangOrderEntity();
//                            fuzhuangOrderEntity.setFuzhuangOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            fuzhuangOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收获地址 要改的
//                            fuzhuangOrderEntity.setFuzhuangId(Integer.valueOf(data.get(0)));   //服装 要改的
//                            fuzhuangOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            fuzhuangOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            fuzhuangOrderEntity.setFuzhuangOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            fuzhuangOrderEntity.setFuzhuangOrderCourierName(data.get(0));                    //快递公司 要改的
//                            fuzhuangOrderEntity.setFuzhuangOrderCourierNumber(data.get(0));                    //订单快递单号 要改的
//                            fuzhuangOrderEntity.setFuzhuangOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            fuzhuangOrderEntity.setFuzhuangOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            fuzhuangOrderEntity.setInsertTime(date);//时间
//                            fuzhuangOrderEntity.setCreateTime(date);//时间
                            fuzhuangOrderList.add(fuzhuangOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("fuzhuangOrderUuidNumber")){
                                    List<String> fuzhuangOrderUuidNumber = seachFields.get("fuzhuangOrderUuidNumber");
                                    fuzhuangOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fuzhuangOrderUuidNumber = new ArrayList<>();
                                    fuzhuangOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("fuzhuangOrderUuidNumber",fuzhuangOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<FuzhuangOrderEntity> fuzhuangOrderEntities_fuzhuangOrderUuidNumber = fuzhuangOrderService.selectList(new EntityWrapper<FuzhuangOrderEntity>().in("fuzhuang_order_uuid_number", seachFields.get("fuzhuangOrderUuidNumber")));
                        if(fuzhuangOrderEntities_fuzhuangOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FuzhuangOrderEntity s:fuzhuangOrderEntities_fuzhuangOrderUuidNumber){
                                repeatFields.add(s.getFuzhuangOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fuzhuangOrderService.insertBatch(fuzhuangOrderList);
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
        PageUtils page = fuzhuangOrderService.queryPage(params);

        //字典表数据转换
        List<FuzhuangOrderView> list =(List<FuzhuangOrderView>)page.getList();
        for(FuzhuangOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FuzhuangOrderEntity fuzhuangOrder = fuzhuangOrderService.selectById(id);
            if(fuzhuangOrder !=null){


                //entity转view
                FuzhuangOrderView view = new FuzhuangOrderView();
                BeanUtils.copyProperties( fuzhuangOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(fuzhuangOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    FuzhuangEntity fuzhuang = fuzhuangService.selectById(fuzhuangOrder.getFuzhuangId());
                if(fuzhuang != null){
                    BeanUtils.copyProperties( fuzhuang , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setFuzhuangId(fuzhuang.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(fuzhuangOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody FuzhuangOrderEntity fuzhuangOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fuzhuangOrder:{}",this.getClass().getName(),fuzhuangOrder.toString());
            FuzhuangEntity fuzhuangEntity = fuzhuangService.selectById(fuzhuangOrder.getFuzhuangId());
            if(fuzhuangEntity == null){
                return R.error(511,"查不到该服装");
            }
            // Double fuzhuangNewMoney = fuzhuangEntity.getFuzhuangNewMoney();

            if(false){
            }
            else if((fuzhuangEntity.getFuzhuangKucunNumber() -fuzhuangOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }
            else if(fuzhuangEntity.getFuzhuangNewMoney() == null){
                return R.error(511,"服装价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            fuzhuangOrder.setFuzhuangOrderTypes(3); //设置订单状态为已支付
            fuzhuangOrder.setFuzhuangOrderTruePrice(0.0); //设置实付价格
            fuzhuangOrder.setYonghuId(userId); //设置订单支付人id
            fuzhuangOrder.setFuzhuangOrderUuidNumber(String.valueOf(new Date().getTime()));
            fuzhuangOrder.setFuzhuangOrderPaymentTypes(1);
            fuzhuangOrder.setInsertTime(new Date());
            fuzhuangOrder.setCreateTime(new Date());
                fuzhuangEntity.setFuzhuangKucunNumber( fuzhuangEntity.getFuzhuangKucunNumber() -fuzhuangOrder.getBuyNumber());
                fuzhuangService.updateById(fuzhuangEntity);
                fuzhuangOrderService.insert(fuzhuangOrder);//新增订单
            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String fuzhuangOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = Integer.valueOf(String.valueOf(params.get("addressId")));

        Integer fuzhuangOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("fuzhuangOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("fuzhuangs"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> fuzhuangs = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<FuzhuangOrderEntity> fuzhuangOrderList = new ArrayList<>();
        //商品表
        List<FuzhuangEntity> fuzhuangList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);
        // 获取折扣
        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", "huiyuandengji_types")
                .eq("dic_name", "会员等级类型")
                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                ;
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
        if(dictionaryEntity != null ){
            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
        }

        //循环取出需要的数据
        for (Map<String, Object> map : fuzhuangs) {
           //取值
            Integer fuzhuangId = Integer.valueOf(String.valueOf(map.get("fuzhuangId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            FuzhuangEntity fuzhuangEntity = fuzhuangService.selectById(fuzhuangId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(fuzhuangEntity.getFuzhuangKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(fuzhuangEntity.getFuzhuangName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                fuzhuangEntity.setFuzhuangKucunNumber(fuzhuangEntity.getFuzhuangKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            FuzhuangOrderEntity fuzhuangOrderEntity = new FuzhuangOrderEntity<>();

            //赋值订单信息
            fuzhuangOrderEntity.setFuzhuangOrderUuidNumber(fuzhuangOrderUuidNumber);//订单号
            fuzhuangOrderEntity.setAddressId(addressId);//收获地址
            fuzhuangOrderEntity.setFuzhuangId(fuzhuangId);//服装
            fuzhuangOrderEntity.setYonghuId(userId);//用户
            fuzhuangOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            fuzhuangOrderEntity.setFuzhuangOrderTypes(3);//订单类型
            fuzhuangOrderEntity.setFuzhuangOrderPaymentTypes(fuzhuangOrderPaymentTypes);//支付类型
            fuzhuangOrderEntity.setInsertTime(new Date());//订单创建时间
            fuzhuangOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(fuzhuangOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(fuzhuangEntity.getFuzhuangNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                        buyJifen = new BigDecimal(fuzhuangEntity.getFuzhuangPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
                            yonghuEntity.setHuiyuandengjiTypes(1);
                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                            yonghuEntity.setHuiyuandengjiTypes(2);
                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                            yonghuEntity.setHuiyuandengjiTypes(3);


                    fuzhuangOrderEntity.setFuzhuangOrderTruePrice(money);

                }
            }
            else{//积分支付

                Double money = fuzhuangEntity.getFuzhuangNewMoney() * buyNumber;
                if(yonghuEntity.getYonghuNewJifen() - money <0 ){
                    return R.error("积分不足,无法支付");
                }else{
                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - money);//设置现在积分


                    fuzhuangOrderEntity.setFuzhuangOrderTruePrice(money);//实付积分
                }

            }
            fuzhuangOrderList.add(fuzhuangOrderEntity);
            fuzhuangList.add(fuzhuangEntity);

        }
        fuzhuangOrderService.insertBatch(fuzhuangOrderList);
        fuzhuangService.updateBatchById(fuzhuangList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);
        return R.ok();
    }











    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            FuzhuangOrderEntity fuzhuangOrder = fuzhuangOrderService.selectById(id);
            Integer buyNumber = fuzhuangOrder.getBuyNumber();
            Integer fuzhuangOrderPaymentTypes = fuzhuangOrder.getFuzhuangOrderPaymentTypes();
            Integer fuzhuangId = fuzhuangOrder.getFuzhuangId();
            if(fuzhuangId == null)
                return R.error(511,"查不到该服装");
            FuzhuangEntity fuzhuangEntity = fuzhuangService.selectById(fuzhuangId);
            if(fuzhuangEntity == null)
                return R.error(511,"查不到该服装");
            Double fuzhuangNewMoney = fuzhuangEntity.getFuzhuangNewMoney();
            if(fuzhuangNewMoney == null)
                return R.error(511,"服装价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            //判断是什么支付方式 1代表余额 2代表积分
            if(fuzhuangOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = fuzhuangEntity.getFuzhuangNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(fuzhuangEntity.getFuzhuangPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);

            }
            else{//积分支付

                Double money = fuzhuangEntity.getFuzhuangNewMoney() * buyNumber;
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + money); //设置现积分

            }

            fuzhuangEntity.setFuzhuangKucunNumber(fuzhuangEntity.getFuzhuangKucunNumber() + buyNumber);



            fuzhuangOrder.setFuzhuangOrderTypes(2);//设置订单状态为退款
            fuzhuangOrderService.updateById(fuzhuangOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            fuzhuangService.updateById(fuzhuangEntity);//更新订单中服装的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String fuzhuangOrderCourierNumber, String fuzhuangOrderCourierName){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        FuzhuangOrderEntity  fuzhuangOrderEntity = new  FuzhuangOrderEntity();;
        fuzhuangOrderEntity.setId(id);
        fuzhuangOrderEntity.setFuzhuangOrderTypes(4);
        fuzhuangOrderEntity.setFuzhuangOrderCourierNumber(fuzhuangOrderCourierNumber);
        fuzhuangOrderEntity.setFuzhuangOrderCourierName(fuzhuangOrderCourierName);
        boolean b =  fuzhuangOrderService.updateById( fuzhuangOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }









    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        FuzhuangOrderEntity  fuzhuangOrderEntity = new  FuzhuangOrderEntity();
        fuzhuangOrderEntity.setId(id);
        fuzhuangOrderEntity.setFuzhuangOrderTypes(5);
        boolean b =  fuzhuangOrderService.updateById( fuzhuangOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer fuzhuangCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            FuzhuangOrderEntity fuzhuangOrder = fuzhuangOrderService.selectById(id);
        if(fuzhuangOrder == null)
            return R.error(511,"查不到该订单");
        if(fuzhuangOrder.getFuzhuangOrderTypes() != 5)
            return R.error(511,"您不能评价");
        Integer fuzhuangId = fuzhuangOrder.getFuzhuangId();
        if(fuzhuangId == null)
            return R.error(511,"查不到该服装");

        FuzhuangCommentbackEntity fuzhuangCommentbackEntity = new FuzhuangCommentbackEntity();
            fuzhuangCommentbackEntity.setId(id);
            fuzhuangCommentbackEntity.setFuzhuangId(fuzhuangId);
            fuzhuangCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            fuzhuangCommentbackEntity.setFuzhuangCommentbackText(commentbackText);
            fuzhuangCommentbackEntity.setInsertTime(new Date());
            fuzhuangCommentbackEntity.setReplyText(null);
            fuzhuangCommentbackEntity.setUpdateTime(null);
            fuzhuangCommentbackEntity.setCreateTime(new Date());
            fuzhuangCommentbackService.insert(fuzhuangCommentbackEntity);

            fuzhuangOrder.setFuzhuangOrderTypes(1);//设置订单状态为已评价
            fuzhuangOrderService.updateById(fuzhuangOrder);//根据id更新
            return R.ok();
    }







}
