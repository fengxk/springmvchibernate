function isidcard(str)
{
    var result=str.match(/\d{15}|\d{18}/);
    if(result==null) return false;
    return true;
}
function ismacAddress(str){
	var regu = /[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}/;
	var re = new RegExp(regu);
	if (re.test(str)) {
		return true;
	}else{
		return false;
	}
}
function trim(str){
   return str.replace(/(^\s*)|(\s*$)/g, "");
}
function isNull( str ){ 
	if ( str == "" ) return true; 
	var regu = "^[ ]+$"; 
	var re = new RegExp(regu); 
	return re.test(str); 
} 
function isIncludeSpecialChar(str){
	var regx = /[#$%^&]+/
	return regx.test(str);
}
function isEmail( str ){
	var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	if(myReg.test(str)) return true;
	return false;
}
function isChinaOrNumbOrLett( s ){
	var regu = "^[0-9a-zA-Z\u4e00-\u9fa5]+$";  
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	}else{
		return false;
	}
}
function isNumbOrLett( s ){
	var regu = "^[0-9a-zA-Z]+$";  
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	}else{
		return false;
	}
}
function cellphonecheck(s){
    var str=s;
	 var reg=/(^1[34578]{1}[0-9]{9}$)/; 
	 if (reg.test(str)==false)
	 	return false;
	 else
	 	return true;
}
function namecheck(s){
   	var str=s;
	var reg=/(^[a-zA-Z\u4E00-\u9FA5]{2,}$)/; 
	if (reg.test(str)==false)
		return false;
	else
		return true;
}
function isIncludeSpecialChar(str){
	var regx = /[#$%^&]+/
	return regx.test(str);
}