new Vue({
   el:'#order',
   data:{
       Order:[],//渲染所购买的商品
       Address:[],//渲染配送地址
   },
    mounted:function(){
       this.getData();
    },
    methods:{
       getData:function () {
           var this_ = this;
           var arr = [];
           this.Order=(JSON.parse(window.localStorage.getItem('Massage'))); //取出需购买商品的信息
           this.Address = JSON.parse(window.localStorage.getItem('NowAddress')); //取出客户的配送地址
               console.log(this.Order);
               console.log(this.Address);
       }
    }
});