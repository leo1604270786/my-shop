var App = function () {
    //iCheck
    var icheck_master;
    var checkboxs;
    //用于存放id的数组
    var idArray;
    //默认的Dropzone参数
    var defaultDropzoneOpts = {
        url: "", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传"+this.maxFiles+"个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消"
    };
    /*
    * 获取所有的多选框
    * */
    var handlerInitCheckbox = function () {
        // 激活 iCheck
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        //获取控制checkbox
        icheck_master = $('input[type="checkbox"].minimal.ickeck_master');
        //获取所有checkbox
        checkboxs = $('input[type="checkbox"].minimal');
    };
    /*
    * 全选和取消全选
    * */
    var handlerCheckAll = function () {
        icheck_master.on("ifClicked",function (e) {
            //全部不选
            if(e.target.checked){
                checkboxs.iCheck("uncheck");
            }
            //全选
            else{
                checkboxs.iCheck("check");
            }
        });
    };
    /*
    * 批量删除
    * */
    var handlerDeleteMulti = function (url) {
        idArray = new Array();
        //将选中列的id存入数组
        checkboxs.each(function () {
            var id = $(this).attr("id");
            if (id != null && id != "undefine" && $(this).is(":checked") ) {
                idArray.push(id);
            }
        });
        //判断是否选择了数据
        if (idArray.length === 0){
            $("#modal-message").html("您还没有选择任何数据，请至少选择一项！");
        }
        else{
            $("#modal-message").html("您确定删除选定的数据吗？！");
        }
        //弹出自定义模态框提示
        $("#modal-default").modal("show");
        //绑定确定按钮单击事件 删除用户信息
        $("#modalBtnOk").bind("click",function () {
            deleteUser();
        });
        //删除数据
        handlerDeleteData(url);
    };
    /*
    * 删除一条信息
    * */
    var handlerDeleteOne = function (id,url,msg) {
        //可选参数
        if (!msg) msg = null;
        //将id放入数组中
        idArray = new Array();
        idArray.push(id);
        //设置提示信息
        $("#modal-message").html(msg == null ? "您确定删除数据项吗？" : msg);
        $("#modal-default").modal("show");
        //绑定删除事件
        $("#modalBtnOk").bind("click",function () {
            handlerDeleteData(url);
        });
    };
    /*
    * 异步删除数据
    * */
    var handlerDeleteData = function (url) {
        //关闭模态框
        $("#modal-default").modal("hide");
        //选择了数据 异步删除
        if(idArray.length > 0) {
            setTimeout(function () {
                $.ajax({
                    "url":url,
                    "type":"POST",
                    "data":{"ids":idArray.toString()},
                    "dataType":"JSON",
                    "success": function (data) {
                        $("#modalBtnOk").unbind("click");
                        //删除成功
                        if (data.status === 200){
                            $("#modalBtnOk").bind("click",function () {
                                window.location.reload();
                            });
                        }
                        //删除失败
                        else{
                            $("#modalBtnOk").bind("click",function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        //显示提示信息
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            }, 500);
        }
    };
    /*
    * 初始化DataTables
    * */
    var handlerInitDataTables = function (url, columns) {
        var dataTable = $("#dataTable").DataTable({
            "ordering": false,
            "lengthChange": false,
            "stateSave": true,
            "processing": true,
            "searching": false,
            "deferRender": true,
            "serverSide": true,
            "ajax": {
                "url": url
            },
            "columns": columns,
            "drawCallback":function ( settings) {
                handlerInitCheckbox();
                handlerCheckAll();
            },
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
        return dataTable;
    };
    /*
    * 查看详情
    * */
    var handlerShowDetail = function (url) {
        $.ajax({
            url: url,
            type: "get",
            dateType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };
    /*
    * 初始化ZTree
    * */
    var handlerInitZTree = function (url,autoParam,callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam
            }
        };
        $.fn.zTree.init($("#myTree"), setting);

        $("#modalBtnOk").bind("click",function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();
            //未选择数据
            if (nodes.length === 0){
                alert("请至少选择一个类目！");
            }
            //选择了数据 传给后台
            else{
                callback(nodes);
            }
        });
    };
    /*
    * 初始化Dropzone
    * */
    var handlerInitDropzone = function (opts) {
        //关闭Dropzone自动发现功能
        Dropzone.autoDiscover = false;
        //继承
        $.extend(defaultDropzoneOpts,opts);
        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    };
    return{
        //初始化CheckBoxs
        init:function () {
            handlerInitCheckbox();
            handlerCheckAll();
        },
        //批量删除
        deleteMulti:function (url) {
            handlerDeleteMulti(url);
        },
        //删除一条数据
        deleteOne:function(id,url,msg){
          handlerDeleteOne(id,url,msg);
        },
        //返回CheckBoxs
        getCheckBox:function(){
          return checkboxs;
        },
        //初始化DataTables
        initDataTables:function (url, columns) {
            return handlerInitDataTables(url,columns);
        },
        //查看详情
        showDetail:function (url) {
            handlerShowDetail(url);
        },
        //初始化ZTree
        initZTree:function (url,autoParam,callback) {
            handlerInitZTree(url,autoParam,callback);
        },
        //初始化Dropzone
        initDropzone:function (opts) {
            handlerInitDropzone(opts);
        }
    }
}();

$(document).ready(function () {
    App.init();
});