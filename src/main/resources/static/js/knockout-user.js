//初始化
$(function () {
    //1、初始化表格
    tableInit.Init();

    //2、注册增删改事件
    operate.operateInit();
});

//初始化表格
var tableInit = {
    Init: function () {
        //绑定table的viewmodel
        this.myViewModel = new ko.bootstrapTableViewModel({
            url: 'testPage',         			//请求后台的URL（*）
            method: 'get',                      //请求方式（*）
//            contentType : "application/x-www-form-urlencoded", //post提交方式是，要指定类型，不写默认是json格式提交
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: tableInit.queryParams, //传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 3,                       //每页的记录行数（*）
            pageList: [3, 10, 50, 100],        //可供选择的每页的行数（*）
            height: 400,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "uId",                     //每一行的唯一标识，一般为主键列
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
                onMsoNumberFormat: tableInit.DoOnMsoNumberFormat  
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
        ko.applyBindings(this.myViewModel, document.getElementById("tb_users"));
    },
 	DoOnMsoNumberFormat: function(cell, row, col) {  
	    var result = "";  
	    if (row > 0 && col == 0)  
	        result = "\\@";  
	    return result;  
 	}, 
	//得到查询的参数
	queryParams: function (params) {
	    var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
			offset: params.offset,   //页面大小
			pageSize: params.limit,  //页码
	        nicheng: $("#txt_search_nicheng").val()
	    };
	    return temp;
	}
};
//操作
var operate = {
    //初始化按钮事件
    operateInit: function () {
        this.operateAdd();
        this.operateUpdate();
        this.operateDelete();
        this.operateQuery();
        this.UserModel = {
            uId: ko.observable(),
            nicheng: ko.observable(),
            sex: ko.observable(),
            phone: ko.observable()
        };
    },
  //查询
    operateQuery: function(){
        $('#btn_query').on("click", function () {
        	  tableInit.myViewModel.refresh();
        });
    },
    //新增
    operateAdd: function(){
        $('#btn_add').on("click", function () {
            $("#myModal").modal().on("shown.bs.modal", function () {
                var oEmptyModel = {
					uId: ko.observable(),
					nicheng: ko.observable(),
					sex: ko.observable(),
					phone: ko.observable()
                };
                ko.utils.extend(operate.UserModel, oEmptyModel);
                ko.applyBindings(operate.UserModel,document.getElementById("myModal"));
                operate.operateSave();
            }).on('hidden.bs.modal', function () {
                ko.cleanNode(document.getElementById("myModal"));
            });
        });
    },
    //编辑
    operateUpdate: function () {
        $('#btn_edit').on("click", function () {
        	var arrselectedData = tableInit.myViewModel.getSelections();
        	if (!operate.operateCheck(arrselectedData)) { return; }
            $("#myModal").modal().on("shown.bs.modal", function () {
                //将选中该行数据有数据Model通过Mapping组件转换为viewmodel
                ko.utils.extend(operate.UserModel, ko.mapping.fromJS(arrselectedData[0]));
                ko.applyBindings(operate.UserModel, document.getElementById("myModal"));
                operate.operateSave();
            }).on('hidden.bs.modal', function () {
                //关闭弹出框的时候清除绑定(这个清空包括清空绑定和清空注册事件)
                ko.cleanNode(document.getElementById("myModal"));
            });
        });
    },
    //删除
    operateDelete: function () {
//        $('#btn_delete').on("click", function () {
//            var arrselectedData = tableInit.myViewModel.getSelections();
//            $.ajax({
//                url: "/Department/Delete",
//                type: "post",
//                contentType: 'application/json',
//                data: JSON.stringify(arrselectedData),
//                success: function (data, status) {
//                    alert(status);
//                    //tableInit.myViewModel.refresh();
//                }
//            });
//        });
    },
    //保存数据
    operateSave: function () {
        $('#btn_submit').on("click", function () {
            //取到当前的viewmodel
            var oViewModel = operate.UserModel;
            //将Viewmodel转换为数据model
            var oDataModel = ko.toJS(oViewModel);
            var funcName = oDataModel.id?"updateUser":"addUser";
            $.ajax({
                url: funcName,
                type: "post",
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(oDataModel),
                success: function (data, status) {
                    alert(status);
                    tableInit.myViewModel.refresh();
                }
            });
        });
    },
    //数据校验
    operateCheck:function(arr){
        if (arr.length <= 0) {
            alert("请至少选择一行数据");
            return false;
        }
        if (arr.length > 1) {
            alert("只能编辑一行数据");
            return false;
        }
        return true;
    }
}