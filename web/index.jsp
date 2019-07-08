<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Shopping-Mall.css">
</head>
<body>
 <div id="app">
    <!--头部-->
    <div class=header>
      <h2 align="center">个人网上书城</h2>
    </div>
    <div class="main-center">
      <!--左部侧栏-->
      <div class="left">
        <input type="text" placeholder="请输入您想要的书籍:" class="seach">
        <input type="button" class="seach-but" value="🔍">
        <div class="left-Exhibition">
          <div class="left-Field">
            <p>Name:</p>
            <p>Price:</p>
          </div>
          <input type="button" class="But-Cart" value="加入购物车" @click="AddCart">
          <button class="Goto-Cart" @click="GoToCart">前往购物车</button>
          <li v-for="(item,index) in NowClickItem">
            <div id="Exhibition-son">
              <img :src="item.image" style="width: 110px">
              <span class="left-font-name">{{item.name}}</span>
              <p class="left-font-price">{{item.price | UnitPrice('元')}}</p>
              <div class="Count">
                <button :disabled="item.count==1" @click="changeReduce(item,-1)">-</button>
                <input type="text" disabled v-model="item.count">
                <button @click="changeReduce(item,1)">+</button>
              </div>
            </div>
            <div class="left-Recommend">
              <h4>编辑推荐:</h4>
              <span> {{item.detailed}}</span>
            </div>
            <div class="left-centent">
              <h4>内容介绍:</h4>
              <span>{{item.centent}}</span>
            </div>
          </li>
        </div>
      </div>
      <!--主体部分-->
      <div class="main">
        <div class="Class-Ification">
          <h1 align="center" style="font-family: 华文行楷">编程语言</h1>
        </div>
        <li v-for="(item,index) in Commodity">
          <div class="main-son" @click="ButMainSon(index,item)">
            <img :src="item.image" class="img">
            <div align="center" class="main-son-name">{{item.name}}</div>
            <div align="center" style="color: #e54b29">{{item.price | UnitPrice('元')}}</div>
          </div>
        </li>
      </div>
    </div>
    <!--底部-->
    <div class="foot">
      <h4 align="center" style="color: #989b9b">Copyright ©[2019-5-9] by @Mr.Potato</h4>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script src="https://cdn.staticfile.org/vue-resource/1.5.1/vue-resource.min.js"></script>
  <script src="https://ajax.aspnetcdn.com/ajax/jquery/jquery-1.9.0.min.js"></script>
  <script src="https://unpkg.com/vue-router@3.0.0/dist/vue-router.js"></script>
  <script src="js/Shopping-Mall.js"></script>
</body>
</html>