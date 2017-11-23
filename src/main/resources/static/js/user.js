$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tb_users').bootstrapTable({
            url: 'testPage',         			//请求后台的URL（*）
            method: 'get',                      //请求方式（*）
//            contentType : "application/x-www-form-urlencoded", //post提交方式是，要指定类型，不写默认是json格式提交
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 3,                       //每页的记录行数（*）
            pageList: [3, 10, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 400,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "uId",                     //每一行的唯一标识，一般为主键列
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            showExport: true,                    //导出excel按钮
            exportDataType:'all',                //导出所有数据
            exportTypes:['excel'],  			 //导出文件类型
            exportOptions:{  
                ignoreColumn: [0],  //忽略某一列的索引  
                fileName: '总台帐报表',  //文件名称设置  
                worksheetName: 'sheet1',  //表格工作区名称  
                tableName: '总台帐报表',  
                excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],  
                onMsoNumberFormat: DoOnMsoNumberFormat  
            },  
            responseHandler:function (res) {
            	return {
            		"rows": res.list,
            		"total": res.total};
            },
            onLoadSuccess: function(data){  //加载成功时执行
            },
            onLoadError: function(){  //加载失败时执行
                layer.msg("加载数据失败", {time : 1500, icon : 2});
            },
            columns: [{
                checkbox: true
            }, {
                field: 'uId',
                title: '用户ID'
            }, {
                field: 'nicheng',
                title: '昵称'
            }, {
                field: 'sex',
                title: '性别',
                formatter: function (value, row, index) {
                	if(value == 0){
                		return "男";
                	}else{
                		return "女";
                	}
                }
            }, {
                field: 'phone',
                title: '电话'
            }, ]
        });
    };
    function DoOnMsoNumberFormat(cell, row, col) {  
        var result = "";  
        if (row > 0 && col == 0)  
            result = "\\@";  
        return result;  
    }  

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
    		offset: params.offset,   //页面大小
    		pageSize: params.limit,  //页码
            nicheng: $("#txt_search_nicheng").val()
        };
        return temp;
    };
    return oTableInit;
};


var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
    	$('#btn_query').click(function(){
    		$('#tb_users').bootstrapTable('refresh','testPage');
    	});
    	//初始化页面上面的按钮事件
    	$('#btn_add').click(function(){
    		
    	});
    };
    return oInit;
};