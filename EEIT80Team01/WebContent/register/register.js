		
( function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define( [ "../widgets/datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.datepicker );
	}
}( function( datepicker ) {

datepicker.regional[ "zh-TW" ] = {
	closeText: "關閉",
	prevText: "&#x3C;上月",
	nextText: "下月&#x3E;",
	currentText: "今天",
	monthNames: [ "一月","二月","三月","四月","五月","六月",
	"七月","八月","九月","十月","十一月","十二月" ],
	monthNamesShort: [ "一月","二月","三月","四月","五月","六月",
	"七月","八月","九月","十月","十一月","十二月" ],
	dayNames: [ "星期日","星期一","星期二","星期三","星期四","星期五","星期六" ],
	dayNamesShort: [ "周日","周一","周二","周三","周四","周五","周六" ],
	dayNamesMin: [ "日","一","二","三","四","五","六" ],
	weekHeader: "周",
	dateFormat: "yy/mm/dd",
	firstDay: 1,
	isRTL: false,
	showMonthAfterYear: true,
	yearSuffix: "年" };
datepicker.setDefaults( datepicker.regional[ "zh-TW" ] );

return datepicker.regional[ "zh-TW" ];

} ) );

		$().ready(function(){
			$.datepicker.setDefaults( $.datepicker.regional[ "zh-TW" ] );
			$("#birthDay").datepicker({
			      changeMonth: true,
			      changeYear: true,
			      defaultDate : (new Date(new Date().getFullYear() - 60
                          + "/01/01") - new Date())
                          / (1000 * 60 * 60 * 24),
			      yearRange : "1900:",
			      onClose: function () {
			          $(this).focusout();
			      }
			    });
		    $("#reset").click(function() {
		    	$("#form").validate().resetForm();
		    });
			
			$.validator.addMethod("ID_regex",
				      function(citizenid, element) {
				 		citizenid = citizenid.toUpperCase();
				        citizenid = citizenid.replace(/\s+/g, "");
				        return (
				            this.optional(element) ||
				            /^[A-Z]{1}[1-2]{1}[0-9]{8}$/.test(citizenid)
				          );
				      }, "身份證格式有誤"
				    );
			
			$.validator.addMethod("ID_arithmetic",
				      function(citizenid, element) {
				 		citizenid = citizenid.toUpperCase();
				        var local_table = [10,11,12,13,14,15,16,17,34,18,19,20,21,
				                             22,35,23,24,25,26,27,28,29,32,30,31,33];
				                         /* A, B, C, D, E, F, G, H, I, J, K, L, M,
				                            N, O, P, Q, R, S, T, U, V, W, X, Y, Z */
				        var local_digit = local_table[citizenid.charCodeAt(0)-'A'.charCodeAt(0)];
				        var checksum = 0;
				        checksum += Math.floor(local_digit / 10);
				        checksum += (local_digit % 10) * 9;
				        /* i: index; p: permission value */
				        /* this loop sums from [1] to [8] */
				        /* permission value decreases */
				        for (var i=1, p=8; i <= 8; i++, p--)
				        {
				          checksum += parseInt(citizenid.charAt(i)) * p;
				        }
				        checksum += parseInt(citizenid.charAt(9));    /* add the last number */
				        return (
				            this.optional(element) || !(checksum % 10)
				          );
				      }, "請輸入合法的身份證"
				    );
			
			$("#form").validate({
					submitHandler:function(form) {
						form.submit();
					},
					rules:{
						username:{
							required:true,
							minlength: 5,
							remote: {
							    url: "checkAccount",     //后台处理程序
							    type: "post",               //数据发送方式
							    dataType: "json",           //接受数据格式   
							    data: {                     //要传递的数据
							        username: function() {
							            return $("#username").val();
							        }
							    }
							}//end of remote							
						},//end of username
						password:{
							required:true,
							minlength: 5
						},
						passwordCheck:{
							required:true,
							minlength: 5,
							equalTo:"#password"
						},
						id:{
							required:true,
							ID_regex:true,
							ID_arithmetic:true
						},
						firstName:{
							required:true
						},
						lastName:{
							required:true
						},
						email:{
							required: true,
						    email: true
						},
						birthDay:{
							required: true,
							date:true
						},
						gender:{
							required: true
						}
					},//end of rules
					messages: {
						username:{
							minlength: "帳號至少五個字",
							remote:"帳號已重覆"
						},
						password:{
							minlength:"密碼至少五個字"
						},
						passwordCheck:{
							minlength: "密碼至少五個字",
							equalTo:"兩次密碼不相同，請確認密碼"
						},
						id:{
							required:"請輸入身份證字號",
							ID_regex:"身份證格式有誤",
							ID_arithmetic:"請輸入合法的身份證字號"						
						},
						firstName:{
							required: "名字未輸入"
						},
						lastName:{
							required: "姓氏未輸入"
						},
						email:{
							required: "請輸入E-mail",
						    email: "請輸入正確的E-mail格式"
						},
						birthDay:{
							required: "請輸入生日",
							date:"請輸入合法的日期"
						},
						gender:{
							required: "姓別欄未選擇"
						}
					}
			
			});//end of validate
		})