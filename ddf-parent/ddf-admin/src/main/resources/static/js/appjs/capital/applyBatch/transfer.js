var prefix = "/capital/applyBatch"
$(function() {
	load();
});

function load() {
	$('#queryTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/query/data", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						//pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						//pageSize : 10, // 如果设置了分页，每页数据条数
						//pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								batchId:$("#id").val()
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
																{
									field : 'id', 
									title : 'id' 
								},								{
									field : 'user.userName', 
									title : '用户姓名' 
								},						{
									field : 'user.mobile', 
									title : '用户手机' 
								},
																{
									field : 'alipayAccount', 
									title : '支付宝账号' 
								},								{
									field : 'amount', 
									title : '提现金额' 
								},
																								{
									field : 'createDate', 
									title : '创建时间' 
								} ]
					});
}
function transfer() {
	var id=$("#id").val();
	var alipayBatchNo=$("#alipayBatchNo").val();
	$.ajax({
		cache : true,
		type : "get",
		url : "/capital/applyBatch/transferCheck",
		data : {
			"batchId" : id
		},// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				window.open("/capital/applyBatch/transfer?batchId="+id+"&alipayBatchNo="+alipayBatchNo);
			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
	/*layer.confirm('确认转账吗?', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			cache : true,
			type : "get",
			url : "/capital/applyBatch/transferCheck",
			data : {
				"batchId" : id
			},// 你的formid
			async : false,
			error : function(request) {
				parent.layer.alert("Connection error");
			},
			success : function(data) {
				if (data.code == 0) {
					window.open("/capital/applyBatch/transfer?batchId="+id+"&alipayBatchNo="+alipayBatchNo);
				} else {
					parent.layer.alert(data.msg)
				}

			}
		});
	})*/
}

