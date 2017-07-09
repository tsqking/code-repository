部门管理日志表
#创建tbl_department_log表
DROP TABLE IF EXISTS tbl_department_log;
create  table tbl_department_log (
log_id BIGINT primary key comment '日志编号',
log_dep_name varchar(50) comment '部门名称',
log_create_name varchar(50) comment '创建人姓名',
log_update_name varchar(50) comment '修改人姓名',
log_update_content varchar(50) comment '修改内容',
log_update_type int comment '修改类型',
log_status varchar(20) comment '修改状态',
log_remark varchar(200)comment '备注')