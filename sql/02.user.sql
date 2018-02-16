-- 增加用户及权限编辑功能


-- drop table user;
CREATE TABLE user
id  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标准ID' ,
login_name  varchar(100) CHARACTER  NOT NULL DEFAULT '' COMMENT '登录名' ,
password  varchar(100) CHARACTER  NOT NULL DEFAULT '' COMMENT '密码' ,
real_name  varchar(100) CHARACTER  NOT NULL DEFAULT '' COMMENT '真实名称' ,
user_type  varchar(10) CHARACTER  NOT NULL DEFAULT '' COMMENT '用户类型：管理员/普通/游客' ,
email  varchar(100) CHARACTER  NOT NULL DEFAULT '' COMMENT '邮箱' ,
mobile  varchar(100) CHARACTER  NOT NULL DEFAULT '' COMMENT '手机号' ,
create_datetime  bigint(20) NOT NULL DEFAULT 0 COMMENT '创建时间' ,
PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='用户表'
AUTO_INCREMENT=3
ROW_FORMAT=COMPACT
;





-- 增加用户及权限编辑功能
-- drop table user_item;
CREATE TABLE user_item
(
	id                          bigint          	NOT NULL AUTO_INCREMENT	COMMENT '标准ID',
	user_id                  	bigint         		DEFAULT 0          NOT NULL	COMMENT '用户ID',
	item_id                   	bigint    			DEFAULT 0          NOT NULL	COMMENT '节点ID',
	create_datetime             bigint          	DEFAULT 0          NOT NULL	COMMENT '创建时间',
PRIMARY KEY(id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='用户节点权限表'
AUTO_INCREMENT=1
ROW_FORMAT=COMPACT;