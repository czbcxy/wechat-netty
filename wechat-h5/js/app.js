window.app = {
	baseUrl: "http://192.168.1.100:8088",
	/**
	 * 判断字符串是否存在
	 * @param {Object} str
	 */
	isNotNull: function(str) {
		if(str != null && str != "" && str != undefined) {
			return true;
		}
		return false;
	},
	/**
	 * 封装 消息提示框， h5+ 
	 * @param {Object} msg
	 * @param {Object} type
	 */
	showToast: function(msg, type) {
		plus.nativeUI.toast(msg, {
			icon: "Images/" + type + ".png",
			verticalAlign: "center"
		});
	},
	/**
	 * 封装用户信息到全局对象
	 * @param {Object} userInfo
	 */
	setUserGlobalInfo: function(userInfo) {
		var userInfo = JSON.stringify(userInfo);
		//		plus.storage.setItem("userInfo", userInfo);
		localStorage.setItem("userInfo",userInfo);
	},
	/**
	 * 获取全局用户信息 
	 */
	getUserGlobalInfo: function() {
		//		var userIf = plus.storage.getItem("userInfo");
		var userIf = localStorage.getItem("userInfo")
		return JSON.parse(userIf);
	},
	loginback: function(){
		localStorage.removeItem("userInfo");
	}
}