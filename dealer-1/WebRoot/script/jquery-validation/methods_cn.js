/*
 * Localized default methods for the jQuery validation plugin.
 * Locale: CN
 */
jQuery.extend(jQuery.validator.methods, {
	
	// 用户编码
	usercode: function(value, element, param) {
		var length = this.getLength(jQuery.trim(value), element);
		return this.optional(element) || ( length >= param[0] && length <= param[1] && /^[A-Za-z0-9_]+$/.test(value) );
	},
	// 用户名
	username: function(value, element, param) {
		var length = this.getLength(jQuery.trim(value), element);
		return this.optional(element) || ( length >= param[0] && length <= param[1] && /^[A-Za-z0-9_\u4E00-\u9FA5]*$/g.test(value) );
	},
	
	// 密码
	password: function(value, element, param) {
		var length = this.getLength(jQuery.trim(value), element);
		return this.optional(element) || ( length >= param[0] && length <= param[1] && /^[A-Za-z0-9_]+$/.test(value) );
	}
});