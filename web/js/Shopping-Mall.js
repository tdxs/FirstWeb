 new Vue({
   el:'#app',
    data:{
        Commodity:[],     //用于循环商品列表
        NowClickItem:[],     //用于保存所选择的商品
        AddShoppingCart:[],   //用于保存需要添加购物车的商品
       Temporary :[], //临时的
        show:true,
        iframeShow:false
    },
      mounted:function(){
          this.scratch();
      },

      //金额过滤
      filters:{
          UnitPrice:function (val,type) {
              return "¥"+val.toFixed(2)+type;
          }
      },
    methods:{
        scratch:function () {
                var this_=this;
                $.ajax({
                    url:"http://localhost:8080/web_war_exploded/servletShopFindAll",//连接替换
                    type:"GET",
                    success:function(res){
                    	//res = $.parseJSON( res );//JSON.parse(res)
                    	var ress = eval('(' + res + ')');//JSON.parse(res);
                        this_.Commodity=ress.commodity;
                    }
                });
               /* console.info(window.localStorage.getItem('key'));
                console.info('');*/
                window.localStorage .setItem('key',JSON.stringify([]));//先把空的存到本地
                this_.AddShoppingCart=(JSON.parse(window.localStorage.getItem('key')));//把之前存在key里的取出来重叠
            /*console.log(this_.AddShoppingCart);
            console.info(localStorage.getItem('NowClick'));*/
            this_.NowClickItem=JSON.parse(localStorage.getItem('NowClick'));//把之前存在NowClick里的取出来重叠
        },
        //点击商品，在左边弹出当前点击商品的信息
        ButMainSon:function (index,item) {
            this.show=false;
           this.NowClickItem = this.Commodity.slice(0,index);
             this.NowClickItem.splice(0,index,item);
            window.localStorage .setItem('NowClick',JSON.stringify(this.NowClickItem));
        },
		//添加购物车
		AddCart : function() {
			if (this.NowClickItem != '') {
				var arr = [];//临时的
				//把当前商品添加进去
				for (var i = 0; i < this.NowClickItem.length; i++) {
					console.log("当前的NowClickItem:"	+ this.NowClickItem[i].name);
					arr.push(this.NowClickItem[i]);
					console.log(arr[i]);
					this.AddShoppingCart.push(arr[i]);
                  //  this.AddShoppingCart = arr;
                    console.log(this.AddShoppingCart);
				}
				this.deleteAddShop();
				alert("添加成功");
			}
		},
        deleteAddShop:function(){
            var arr=[];//临时的
            //判断是否重复
                for (var j = 0;j < this.AddShoppingCart.length; j++){
                    if(this.AddShoppingCart.indexOf(this.AddShoppingCart[j]) === j){
                        arr.push(this.AddShoppingCart[j]);
                    }
                }
               console.log("arr长度是"+arr.length);
            window.localStorage .setItem('key',JSON.stringify(arr));
        },
        //数量增加按钮
        changeReduce: function (item, val) {
            if (val < 1) {
                item.count--;
                window.localStorage.setItem('NowClick',JSON.stringify(this.NowClickItem));//重新储存
            } else {
                item.count++;
                window.localStorage.setItem('NowClick',JSON.stringify(this.NowClickItem));//重新储存
            }
        },
        //前往购物车
        GoToCart:function () {
            /*记录打开的窗体对象*/
         var showDocContentWindow;
                if(showDocContentWindow){ // 如果已经打开过
                    showDocContentWindow.close(); // 关闭
                }
           // window.location.href = 'Cart.jsp';
          window.open("Cart.jsp","showDocContentWindow");
        }
    }
});