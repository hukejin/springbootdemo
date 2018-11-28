layui.use(['element','jquery','layer','table','form'], function() {
    var element = layui.element, $ = layui.$,layer = layui.layer,table = layui.table,form = layui.form,layedit = layui.layedit;

    //表头
    var cols = [
        // {type: 'checkbox', fixed: 'left'}
        // ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
        {field: 'coursename', title: '课程名'}
        ,{field: 'coursecode', title: '课程编码',align:'center'}
        ,{field: 'coursetype', title: '课程类型',align:'center'}
        ,{field: 'coursedesc', title: '课程描述',align:'left'}
        ,{field: 'courselevel', title: '所属年级',align:'right'}
        ,{fixed: 'right',width:160,title:'操作', align:'center', toolbar: '#barDemo'}//表格子项加入操作按钮
    ];

    //执行一个 table 实例
    var tableinstant = table.render({
        elem: '#demo'
        ,url: '' //数据接口
        ,cellMinWidth: 80
        ,title: '用户表'
        ,method: 'post' //默认：get请求
        ,page: true //开启分页
        ,toolbar: '#toolbarDemo' //开启工具栏，自定义模板
        ,totalRow: true //开启合计行
        ,cols:[
            cols
        ]
        ,id:'table'

    });

    var tableurl;
    element.on('nav(side-menu)', function(elem){
        tableurl = $(elem).attr("data-url");
        console.log(tableurl)
        if(tableurl == null)
            return;
        tableinstant.reload({url: tableurl});

    });

    var index
    //头工具栏事件,添加课程
    table.on('toolbar(test)', function(obj){
        switch(obj.event){
            case 'additem':
                index  = layer.open({
                    type:1,
                    title:'添加课程',
                    content:$('#addlayer'),
                    area: '500px',
                    success: function(layero, index){
                        console.log(layero, index);
                    }
                })
                break;
        };
    });


    //监听行工具事件
    table.on('tool(test)',function(obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值
        if(layEvent == 'detail'){
            layer.alert('detail');
        }else if(layEvent == 'edit'){
            layer.msg('edit');
        }else if(layEvent == 'del'){
            layer.msg('del');
        }
    });

    //富文本编辑器
    layedit.build('demo'); //建立编辑器
})