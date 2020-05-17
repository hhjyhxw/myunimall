$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'unimall/unimallorder/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '', name: 'channel', index: 'channel', width: 80 }, 			
			{ label: '', name: 'orderNo', index: 'order_no', width: 80 }, 			
			{ label: '', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '', name: 'status', index: 'status', width: 80 }, 			
			{ label: '', name: 'skuOriginalTotalPrice', index: 'sku_original_total_price', width: 80 }, 			
			{ label: '', name: 'skuTotalPrice', index: 'sku_total_price', width: 80 }, 			
			{ label: '', name: 'freightPrice', index: 'freight_price', width: 80 }, 			
			{ label: '', name: 'couponPrice', index: 'coupon_price', width: 80 }, 			
			{ label: '', name: 'couponId', index: 'coupon_id', width: 80 }, 			
			{ label: '', name: 'groupShopId', index: 'group_shop_id', width: 80 }, 			
			{ label: '', name: 'actualPrice', index: 'actual_price', width: 80 }, 			
			{ label: '', name: 'payPrice', index: 'pay_price', width: 80 }, 			
			{ label: '', name: 'payId', index: 'pay_id', width: 80 }, 			
			{ label: '', name: 'payChannel', index: 'pay_channel', width: 80 }, 			
			{ label: '', name: 'gmtPay', index: 'gmt_pay', width: 80 }, 			
			{ label: '', name: 'shipNo', index: 'ship_no', width: 80 }, 			
			{ label: '', name: 'shipCode', index: 'ship_code', width: 80 }, 			
			{ label: '', name: 'gmtShip', index: 'gmt_ship', width: 80 }, 			
			{ label: '', name: 'gmtConfirm', index: 'gmt_confirm', width: 80 }, 			
			{ label: '若province字段为空，表示无需收货地址', name: 'province', index: 'province', width: 80 }, 			
			{ label: '', name: 'city', index: 'city', width: 80 }, 			
			{ label: '', name: 'county', index: 'county', width: 80 }, 			
			{ label: '', name: 'address', index: 'address', width: 80 }, 			
			{ label: '', name: 'phone', index: 'phone', width: 80 }, 			
			{ label: '', name: 'consignee', index: 'consignee', width: 80 }, 			
			{ label: '', name: 'mono', index: 'mono', width: 80 }, 			
			{ label: '', name: 'adminMonoLevel', index: 'admin_mono_level', width: 80 }, 			
			{ label: '', name: 'adminMono', index: 'admin_mono', width: 80 }, 			
			{ label: '', name: 'refundReason', index: 'refund_reason', width: 80 }, 			
			{ label: '', name: 'gmtUpdate', index: 'gmt_update', width: 80 }, 			
			{ label: '', name: 'gmtCreate', index: 'gmt_create', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#icloudapp',
	data:{
		showList: true,
		title: null,
		unimallOrder: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.unimallOrder = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.unimallOrder.id == null ? "unimall/unimallorder/save" : "unimall/unimallorder/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.unimallOrder),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "unimall/unimallorder/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "unimall/unimallorder/info/"+id, function(r){
                vm.unimallOrder = r.unimallOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});