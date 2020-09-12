let login_info = new Vue({
	el:"#navbar",
	data:{
		user:{},
		loginStatus:false
	},
	mounted:function(){
		axios.post("user",qs.stringify({op:"check"})).then(rt =>{
			
			if(rt.status == 200 && rt.data.code == 200){
				this.loginStatus = true;
				this.user = rt.data.data;
				
				return;
			}
			this.loginStatus = false;
		})
	}
})