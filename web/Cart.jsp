<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link rel="stylesheet" href="css/Shopping-Cart.css">
</head>
<body>
<div class="app">
        <div class="list">
            <ul>
                <li>商品信息</li>
                <li>商品金额</li>
                <li>商品数量</li>
                <li>总金额</li>
                <li>编辑</li>
            </ul>
        </div>
        <div class="mian">
            <li v-for="(item,index) in commodity">
                <div class="mian-name">
                    <input type="checkbox" :class="{'check':item.checked}" :checked="item.checked"
                           @click="ChangeElection(item,index)">
                    <img :src="item.image">
                </div>
                <div class="mian-price">
                    <td >{{item.price | money}}</td>
                </div>
                <div class="mian-count">
                <td>
                    <button :disabled="item.count==1" @click="changeReduce(item,-1)">-</button>
                    <input type="text" disabled v-model="item.count">
                    <button @click="changeReduce(item,1)">+</button>
                </td>
            </div>
                <div class="mian-total">
                    <td>
                        {{item.price*item.count | money2('元')}}
                    </td>
                </div>
                <div class="mian-remove">
                    <td>
                        <button @click="Remove(item)">移除</button>
                    </td>
                </div>
            </li>
            <div class="All-election">
                <input type="radio" @click="allElection()" :class="{'check':checked2}" :checked="checked2"><span>全选</span>
            </div>
            <button class="jiesuan" @click="JumpPage()">结算({{ShopNumber}})</button>
            <div class="total-Price">
                <h2>合计：</h2>
                <h3>{{Total | money2('元')}}</h3>
            </div>
            <div v-show="freeShoppingCart" :class="{'freeShop' : freeShoppingCart}">
                <span>购物车为空!</span>
            </div>
        </div>
    <div v-show="show" :class="{'show':show}">`
        <div class="remove-One">
            <span>您确认删除此订单信息吗?</span>
            <button @click="RemoveCart()">确认</button>
            <button @click="show = !show">取消</button>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
<script src="https://unpkg.com/vue-router@2.0.0/dist/vue-router.js"></script>
<script src="js/Shopping-Cart.js"></script>

</body>
</html>