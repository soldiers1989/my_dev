$().ready(function() {
	validateRule();
    $("#cityId").change(function(){
        var t = $("#cityId").val();
        if(t ==''){
            return;
        }

        $.ajax({
            url:'/dic/xiaoqu/code',
            async:false,
            type:'post',
            data:{id:t,ranNum:Math.random()},
            success:function(data){
            	debugger
                var t3 = $("#districtId").empty();
                for ( var i = 0; i < data.length; i++) {
                    t3.append("<option value='"+data[i].id+"'>"+ data[i].name+"</option>");
                }
            }
        })
    });
    $("#districtId").change(function(){
        var t = $("#districtId").val();
        if(t ==''){
            return;
        }

        $.ajax({
            url:'/dic/xiaoqu/code',
            async:false,
            type:'post',
            data:{id:t,ranNum:Math.random()},
            success:function(data){

                // map.panTo(new BMap.Point(data.lng,data));
                var t3 = $("#circleId").empty();
                for ( var i = 0; i < data.length; i++) {
                    t3.append("<option value='"+data[i].id+"'>"+ data[i].name+"</option>");
                }
            }
        })
    });
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/dic/xiaoqu/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "请输入姓名"
			}
		}
	})
}
