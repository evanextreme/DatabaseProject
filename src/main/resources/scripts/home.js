function getCategoryList(){
	$.ajax({
		type:  'GET',
		dataType: "text",
		url: "/api/brand/list",
		success:  function (data) {
			var str = '';
			
			var obj = JSON.parse(data);
			
			var i;
			for (i = 0; i < obj.length; i++){
				str += generateCategory(obj[i].id, obj[i].name);
			}
			updateCategoryList(str);
		}, error: function(data){
			console.log("ERROR: ", data);
		}
	});
}

//this code will run as soon as the page loads
$(function(){
	getCategoryList();
});