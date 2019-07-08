new Vue({
    el:'#app',
    data:{
        Addresslist:[],  //地址列表
        Order:[],  //渲染订单信息
        OrderNumber:'',//渲染订单编号
        NewAddress: [],  //储存新增的地址
        Regular:false,//添加or修改弹出的输入框
        YesBut:false,  //这是修改弹窗里确认按钮的隐藏与显示
        Default:'',  //这是用来获取当前点击的模块的默认地址
        Num:'', //这是用于存储点击“修改”传过来的index
        number:0,  //这是用来记录当前点击的模块
        Total:0 , //记录总金额
        GiveAddress:'',  //记录配送地址
        Consignee:'',//记录收货人姓名
        Phone:''//记录收货人号码
    },
    mounted:function(){
        this.GetData();
        this. CalculationTotal();
    },
    //金额过滤
    filters:{
        money:function (val) {
            return val.toFixed(2);
        },
        money2:function (val,type) {
            return "￥"+val.toFixed(2);
        }
    },
    methods: {
        //从localStorage取出新增的地址
        GetData: function () {
        	var this_ = this;
        	//查询所有收货地址开始
        	$.ajax({
                url:"http://localhost:8080/web_war_exploded/servletFindAllAddress",//连接替换
                type:"GET",
                success:function(res){
                	var ress = eval('(' + res + ')');//JSON.parse(res);
                	this_.Addresslist = ress.Addresslist;
                	console.log(this_.Addresslist);
                }
            });
        	//查询所有收货地址解释
            //console.log(this.Addresslist)
            window.localStorage .setItem('Key',JSON.stringify([]));//先把空的存到本地
            this.Order=JSON.parse(window.localStorage.getItem('Massage')); //取出需购买商品的信息
            this.OrderNumber=JSON.parse(window.localStorage.getItem('OrderNum')); //取出上一步生成的订单编号
            console.log(this.OrderNumber);
        },
        //点击模块选中
        ClickModule: function (index,name,address,phone,id) {
            var this_ = this;
//            var Give = [];  //临时记录
            this.number = index;
   //   window.localStorage.setItem('NowAddress', JSON.stringify(this.Addresslist[index]));//储存当前点击的模块为NowAddress
//            this_.GiveAddress = JSON.parse(window.localStorage.getItem('NowAddress')); //取出客户的配送地址
//            for(var i in  this_.GiveAddress ){
//                Give.push(this_.GiveAddress[i]);
//            }
            this_.GiveAddress=[];
            this_.Consignee=[];
            this_.Phone=[];
//            this_.Total = 123;
            this_.GiveAddress = address;//地址
            this_.Consignee = name;//name
            this_.Phone = phone;//电话
            document.getElementById('orderaddress').value = address;
            document.getElementById('orderpeople').value = name;
            document.getElementById('orderphone').value = phone;
            document.getElementById('orderprice').value = this_.Total;
            document.getElementById('ordercode').value = this_.OrderNumber;
            console.log(this_.GiveAddress);
        },
        //修改地址
        ChangeAddress: function (id,index) {
            this.Num = index;
            this.Regular = true;
            this.YesBut = true;   //点击修改后，“确认”按钮就显示
            //根据id查询地址开始
            $.ajax({
                url:"http://localhost:8080/web_war_exploded/findByIdAddressServlet",//连接替换
                type:"GET",
                data : {id:id},
                success:function(res){
                	var ress = eval('(' + res + ')');//JSON.parse(res);
                	this.Addresslist=ress.Addresslist;
                	document.getElementById('firstname').value = ress.name;
                	document.getElementById('address').value = ress.address;
                	document.getElementById('phone').value = ress.phone;
                	document.getElementById('addressid').value = ress.id;
//                	console.info(this.Addresslist);
                }
            });
            //根据id查询地址结束
            //console.log( this.Default );
        },
        //按下确认按钮
        ClickYesBut: function () {
        	this.Modify();
        	//修改地址结束
        },
        //删除地址
        RemoveAddress: function (id,index) {
        	//删除地址开始
        	$.ajax({
                url:"http://localhost:8080/web_war_exploded/servletDeleteAddress",//连接替换
                type:"GET",
                data : {id:id},
                success:function(res){}
            });
        	//删除地址结束
            this.Addresslist.splice(index, 1);
            this.GiveAddress='';
            this.Consignee='';
            this.Phone='';
            window.localStorage.setItem('Key', JSON.stringify(this.Addresslist));//重新储存
        },
        //增加地址
        But: function (index) {
                this.Add_ddress(index);
                console.log(index);
        },
        //结算总金额
        CalculationTotal: function () {
            var this_ = this;
            this_.Total = 0;
            this_.Order.forEach(function (item) {
                    this_.Total += item.count * item.price;
            })
        },
        Modify: function () {
            var myform = document.getElementsByTagName('form')[0];
            var Nmae = document.getElementById('name');
            var telphone = document.getElementById('telphone');
            var phone = document.getElementById('phone').value;
            var firstname = document.getElementById('firstname').value;
            var address = document.getElementById('address').value;
            var addressid = document.getElementById('addressid').value;
            var Location = document.getElementById('location');
            var ZhengZeNmae = /^[\u4e00-\u9fa5 ]{2,20}$/;
            var zhengZe = /^[1][3,4,5,7,8][0-9]{9}$/;
            var getInput = myform.getElementsByTagName('input');//获取表单中所有输入框
            var getInputText = [];//用于临时存储input框的value
            //判断各个input输入的是否为空和是否正确
            if (!ZhengZeNmae.test(firstname)) {
                Nmae.innerHTML = "请输入有效的姓名";
                Nmae.style.color = 'red';
            } else {
                Nmae.innerHTML = '';
            }
            if (getInput[1].value) {
                Location.innerHTML = '';
            } else {
                Location.innerHTML = "请输入有效地址";
                Location.style.color = 'red';
            }
            if (!zhengZe.test(phone)) {
                telphone.innerHTML = "请输入有效号码";
                telphone.style.color = 'red';
            } else {
                telphone.innerHTML = '';
            }
            if (ZhengZeNmae.test(firstname) && zhengZe.test(phone) && getInput[1].value) {
                // 修改地址开始
                $.ajax({
                    url:"http://localhost:8080/web_war_exploded/servletUpdateAddress",//连接替换
                    type:"POST",
                    data : {id:addressid,name:firstname,address:address,phone:phone},
                    success:function(res){

                    }
                });
                //Json.splice(this.Num, 1, obj);  //删除当前位置的模块，并添加修改后的模块
                var this_ = this;
                $.ajax({
                    url:"http://localhost:8080/web_war_exploded/servletFindAllAddress",//连接替换
                    type:"GET",
                    success:function(res){
                        var ress = eval('(' + res + ')');//JSON.parse(res);
                        this_.Addresslist = ress.Addresslist;
                        this_.Regular = false; //关闭窗口
                    }
                });
            }
        },  //封装修改地址代码
        Add_ddress: function (index) {
            var Default = false;  //Default用来判断是否默认地址
            var Nmae = document.getElementById('name');
            var firstname = document.getElementById('firstname').value;
            var phone = document.getElementById('phone').value;
            var telphone = document.getElementById('telphone');
            var Location = document.getElementById('location');
            var myform = document.getElementsByTagName('form')[0];
            var xinp = myform.getElementsByTagName('input');//获取表单中所有输入框
            var ZhengZeNmae = /^[\u4e00-\u9fa5 ]{2,20}$/;
            var zhengZe = /^[1][3,4,5,7,8][0-9]{9}$/;
            if (index > 0) {
                //判断各个input输入的是否为空和是否正确
                if (!ZhengZeNmae.test(firstname)) {
                    Nmae.innerHTML = "请输入有效的姓名";
                    Nmae.style.color = 'red';
                } else {
                    Nmae.innerHTML = '';
                }
                if (xinp[1].value) {
                    Location.innerHTML = '';
                } else {
                    Location.innerHTML = "请输入有效地址";
                    Location.style.color = 'red';
                }
                if (!zhengZe.test(phone)) {
                    telphone.innerHTML = "请输入有效号码";
                    telphone.style.color = 'red';
                } else {
                    telphone.innerHTML = '';
                }
                //如果这三个input里的值都不为空且都正确的情况下，增加新地址
                if (ZhengZeNmae.test(firstname) && zhengZe.test(phone) && xinp[1].value) {
                    telphone.innerHTML = '';
                    Nmae.innerHTML = '';
                    Location.innerHTML = '';
                    var obj = {};
                    var AddresslistTest = [];//用于临时存储input框的value
                    for (var i = 0; i < xinp.length; i++) {
                        AddresslistTest.push(xinp[i].value);//将每个输入框中的值付给数组对应项
                        xinp[i].value = null;
                    }
                    AddresslistTest.push(Default);
                    //console.log(AddresslistTest.length);
                    this.NewAddress = ( JSON.parse(window.localStorage.getItem('Key')));  //把之前储存的数据取出来用来叠加
                    //给obj设置对象属性
                    obj.userName = AddresslistTest[1];
                    obj.location = AddresslistTest[2];
                    obj.telphone = AddresslistTest[3];
                    obj.Default = AddresslistTest[4];
                    //////////新增地址
                    var  this_ = this;
                    $.ajax({
                    url:"http://localhost:8080/web_war_exploded/servletAddAddress",//连接替换
                    type:"POST",
                    data : {name:obj.userName,address:obj.location,phone:obj.telphone},//参数
                    success:function(res){
                        this_.NewAddress.push(obj);
                        window.localStorage.setItem('Key', JSON.stringify(this_.NewAddress));//储存NewAddress里的数据
                        //重新ajax获取后台地址用于叠加this.Addresslist
                        $.ajax({
                            url:"http://localhost:8080/web_war_exploded/servletFindAllAddress",//连接替换
                            type:"GET",
                            success:function(res){
                                var ress = eval('(' + res + ')');//JSON.parse(res);
                                this_.Addresslist = ress.Addresslist;
                            }
                        });
                        //获取Key里的数据,使Addresslist动态更新
                        this_.Addresslist=(JSON.parse(window.localStorage.getItem('Key')));
                        this_.Regular = false; //关闭窗口
                    }
                });
                }
            } else {
                //把所有提示设置为空
                telphone.innerHTML = '';
                Nmae.innerHTML = '';
                Location.innerHTML = '';
                //把所有值设置为空
                for (var a = 0; a < xinp.length; a++) {
                    xinp[a].value = '';
                }
                this.Regular = false;//关闭窗口
            }
        } , //封装增加地址代码
        order : function(){
        	var this_ = this;
        	///提交订单详情开始
        	for (var a = 0; a < this_.Order.length; a++) {
        		this_.Order[a].centent = '';//请求文件过大只能把这个制空了
            }
        	var jsa = JSON.stringify(this_.Order);//订单详情数据
        	var ordercode = document.getElementById('ordercode').value;//订单编码
        	var people = document.getElementById('orderpeople').value;//收货人
        	var address = document.getElementById('orderaddress').value;//收货地址
        	$.ajax({
                url:"http://localhost:8080/web_war_exploded/addOrder",//连接替换
                type:"post",
                data : {info:jsa,ordercode:ordercode,people:people,address:address,money:this_.Total},
                success:function(res){
                	var ress = eval('(' + res + ')');//转json
//                	debugger;
                	console.info(this_.Order.length);
                	//清空本页订单信息
                    this_.Order.splice(0,this_.Order.length);
                    localStorage.removeItem('Massage');//删除localstorage里的订单key
                    localStorage.removeItem('OrderNum');
                	console.info(this_.Order);
                	if(ress.success){
                	    alert("订单提交成功，我们会尽快安排发货，请耐心等待！");
                		//制空订单,提交完后回到首页
                		window.location.href='http://localhost:8080/web_war_exploded/Address.jsp';//页面刷新
                	}
                }
            });
        	///提交订单详情结束
        }
    }
});
