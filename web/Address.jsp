<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css//Address-options.css">
    <link rel="stylesheet" type="text/css" href="./css/Order.css">
    <link rel="stylesheet" type="text/css" href="./css/detailed.css">
</head>
<body>
<div id="app">
        <div class="header"><span>确认收货地址</span></div>
    <div class="centent">
        <li v-for = "(item,index) in Addresslist">
           <div class="Address-frame" :class="{'Address-frame2' : number == index}" @click="ClickModule(index,item.name,item.address,item.phone,item.id)">
                <h3 class="Address-frameSon">{{item.name}}</h3>
                <div class="Address-frameSon2">{{item.address}}</div>
                <div class="Address-frameSon3">{{item.phone}}</div>
                <div>
                    <a href="#" @click="ChangeAddress(item.id,index)">修改</a>
                    <a href="#" @click="RemoveAddress(item.id,index)">删除</a>
                </div>
            </div>
        </li>
        <div class="Address-frame" @click="Regular=true,YesBut=false">
            <div class="addlocation"></div>
            <div class="addlocation2"></div>
            <span>添加新地址</span>
        </div>
    </div>
    <!--订单信息-->
    <div class="header_Two"><span>确认订单信息</span></div>
    <div class="centent_Two">
        <div class="Order">
            <ul>
                <li>商品信息</li>
                <li>单价</li>
                <li>数量</li>
                <li>优惠方式</li>
                <li>小计</li>
            </ul>
        </div>
        <div class="OrderMassage">
            <li v-for="(item,index) in Order" class="ShopMassage">
                <div class="Order-Image">
                    <a href="#"><img src="./img/order.jpg" style="width: 14px"></a>
                    <a href="#"><img src="./img/order2.jpg" style="width: 23px"></a>
                    <a href="#"><img src="./img/order3.jpg" style="width: 17px"></a>
                </div>
                <div class="ShopMassage-Price"><span>{{item.price | money}}</span></div>
                <div class="ShopMassage-Count"><span>{{item.count}}</span></div>
                <div class="ShopMassage-Image"> <img :src="item.image" style="width: 50px"></div>
                <div class="ShopMassage-Name"><span>{{item.name}}</span></div>
                <div class="ShopMassage-seller"><span>发货时间：卖家承诺24小时内</span></div>
                <div class="ShopMassage-Discount"><span>暂时无优惠</span></div>
                <div class="ShopMassage-Computed"><span>{{item.price*item.count | money}}</span></div>
            </li>
        </div>
        <div class="detailed">
            <div class="detailed-OrderNum">
                <h5>订单编号：<span>{{OrderNumber}}</span></h5>
                <input type="hidden" id="ordercode" name="ordercode" />
            </div>
            <div class="Total">
                <h5>实付款：<span>{{Total | money2}}</span></h5>
                <input type="hidden" id="orderprice" name="orderprice" />
            </div>
            <div class="Give">
                <h5>寄送到：<span>{{GiveAddress}}</span></h5>
                <input type="hidden" id="orderaddress" name="orderaddress" value="" />
            </div>
            <div class="Consignee">
                <h5>收货人：<span>{{Consignee}}</span></h5>
                <input type="hidden" id="orderpeople" name="orderpeople" />
            </div>
            <div class="Phone">
                <h5>手机号：<span>{{Phone}}</span></h5>
                <input type="hidden" id="orderphone" name="orderphone" />
            </div>
        </div>
    </div>
    <button  @click="order()" class="Next">提交</button>
    <form :class="{'regular' : Regular}" v-show="Regular">
    <input type="hidden" id="addressid" placeholder="id">
        <div ><label style="color:white">姓名：</label><input type="text" id="firstname" placeholder="姓名"></div>
        <div id="name"></div>
        <div><label style="color: white">地址：</label><input type="text" id="address" placeholder="收货地址"></div>
        <div id="location"></div>
        <div><label style="color: white">电话：</label><input type="text" id="phone" placeholder="手机号码"></div>
        <div id="telphone"></div>
        <button type="button" id="but" @click="But(1)" class="Add-location"  v-show="!YesBut">添加</button>
        <button type="button" id="but1" @click="ClickYesBut" class="Add-location" v-show="YesBut">确认</button>
        <button type="button" id="but2" @click="But(0)" class="Close-frame">关闭</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-1.9.0.min.js"></script>
<script src="js/AddressOptions.js"></script>
</body>
</html>