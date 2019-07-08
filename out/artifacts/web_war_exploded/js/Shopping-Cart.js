var vm =new Vue({
   el:'.app',
    data:{
        Total:0,
        checked2:false,
        show:false,
        now_item:0,  //now_item存储当前要删除的索引
        inde:0, //记录商品数量，如果达到所有商品数量，全选按钮选上
        ShopNumber:0,  //记录购物车已选中的商品数量
        commodity:[], //渲染购物车所有商品
        freeShoppingCart:false
    },
    mounted:function(){
       this.Receive();
    },
    //金额过滤
    filters:{
        money:function (val) {
            return "¥"+val.toFixed(2);
        },
        money2:function (val,type) {
            return "¥"+val.toFixed(2)+type;
        }
    },
    methods: {
        Receive:function(){
            //console.log(this.$route.params.name);
            //取出localStorage的数据
            this.commodity=JSON.parse(window.localStorage.getItem('key'));
            if(this.commodity!=null){
                this.inde=this.commodity.length;
            }else {
                this.freeShoppingCart = true;
            }
            console.log(this.inde);
        },
        //数量增加按钮
        changeReduce: function (item, val) {
            if (val < 1) {
                item.count--;
                window.localStorage.setItem('key',JSON.stringify(this.commodity));//重新储存
            } else {
                item.count++;
                window.localStorage.setItem('key',JSON.stringify(this.commodity));//重新储存
            }
            this.CalculationTotal();
        },
        //全选与取消全选
        allElection: function () {
            var this_ = this;
            var Allmassage = [];//接收已勾选的商品
            this_.ShopNumber = 0;
            //如果已经全选了，所有单选的取消
            if (this_.checked2) {
                    this_.commodity.forEach(function (item,index) {
                        Allmassage.splice(index, 1);//删除当前索引元素
                        item.checked = false;
                    });
                this_.checked2 = false;
                window.localStorage.setItem('Massage',JSON.stringify(Allmassage));//删除后重新储存
            } else {
                this_.commodity.forEach(function (item,index) {
                    item.checked = true;
                    this_.ShopNumber++;
                    //储存全部选择的商品信息，后续在订单页面取用
                    Allmassage.push(this_.commodity[index]);
                    window.localStorage.setItem('Massage',JSON.stringify(Allmassage));
                    console.log(Allmassage);
                });
                this_.checked2 = true;
            }
            this.CalculationTotal();
        },
        //单选
        ChangeElection: function (item,index) {
            var this_ = this;
            var massage = [];//接收勾选的商品
            var i = 0; //记录购物车商品数量
            if (item.checked) {
                item.checked = !item.checked;
                this_.checked2 = false;
                this_.ShopNumber--;
                massage.splice(index, 1);//删除当前索引1个元素
                window.localStorage.setItem('Massage',JSON.stringify(massage));//重新存储
            } else {
               // massage = JSON.parse(window.localStorage.getItem('Massage'));
                massage.push(this_.commodity[index]);
                //储存当前选择的商品信息，后续在订单页面取用
                window.localStorage.setItem('Massage',JSON.stringify(massage));
                item.checked = !item.checked;
                this_.ShopNumber++;
                this_.commodity.forEach(function (item) {
                    if (item.checked) {
                        i++;
                    }
                });
                //如果当前i大于或等于该实例的inde 那么全选就为true
                if (i === this_.inde) {
                    this_.checked2 = true;
                }
            }
            this.CalculationTotal();
        },
        //结算总金额
        CalculationTotal: function () {
            var this_ = this;
            this_.Total = 0;
            this_.commodity.forEach(function (item) {
                if (item.checked) {
                    this_.Total += item.count * item.price;
                }
            })
        },
        //删除订单信息
        Remove: function (item) {
            this.show = true;
            this.now_item = item;   //把当前点击的索引保存
        },
        //确认删除按钮
        RemoveCart: function () {
            var this_ = this;
            var index = this_.commodity.indexOf(this_.now_item);     //获取
            //删除当前本地存储的数据
            this_.commodity.splice(index, 1);//删除当前索引1个元素
            //再把当前删除的信息的checked设为false
            this_.commodity.forEach(function (item) {
                if (item === index) {
                    item.checked = false;
                }
            });
            delete this_.commodity[this.now_item]; // 删除指定元素
            window.localStorage.setItem('key',JSON.stringify(this_.commodity));//重新储存
            //如果当前索引里的checked为true  就自减选中的购物车数量，相反则不减
            if (this_.now_item.checked) {
                this.ShopNumber--;
            }
            this_.inde--;  //记录商品数量，如果达到所有商品数量，全选按钮选上，这里是删除后自减
            if (this_.inde === 0) {  //如果商品数量是0，全选就false
                this_.checked2 = false;
                this_.freeShoppingCart = true;  //此时购物车为空的模块就为true
            }
            this_.show = false;//关闭窗口
            this_.Receive(); //删除后需要重新获取一遍key里的商品
            this_.CalculationTotal();//需要重新计算一次总金额
        },
        //点击结算进行页面跳转，如果选项为0则不跳
        JumpPage: function () {
            /*记录打开的窗体对象*/
            /* var showDocContentWindow;
                 if(showDocContentWindow){ // 如果已经打开过
                     showDocContentWindow.close(); // 关闭
                 }*/
            if (this.ShopNumber !== 0) {
                window.location='Address.jsp';
               // window.open('Address.jsp',"showDocContentWindow");
                localStorage.removeItem('key');
                window.localStorage.setItem('OrderNum',JSON.stringify(this.random_No()));//存储订单号
            } else alert("订单数量不能为0!");
        },
        //订单编号生成
      random_No:function(j) {
                  var random_no = "";
                  for (var i = 0; i < j; i++) //j位随机数，用以加在时间戳后面。
                  {
                        random_no += Math.floor(Math.random() * 10);
                  }
                      random_no = new Date().getTime() + random_no;
                    return random_no;
        }
    }
});