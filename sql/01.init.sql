-- drop table tree_item;
CREATE TABLE tree_item
(
	id                             bigint          NOT NULL AUTO_INCREMENT	COMMENT '标准ID',
	p_id                  bigint          DEFAULT 0          NOT NULL	COMMENT '上级ID',
	name                   varchar(100)    DEFAULT ''         NOT NULL	COMMENT '节点名',
	icon_skin                   varchar(10)    DEFAULT ''         NOT NULL	COMMENT '自定义节点样式',
	sort                        int             DEFAULT 0          	COMMENT '排序字段',
	create_datetime                  bigint          DEFAULT 0          NOT NULL	COMMENT '创建时间',
	modify_datetime                  bigint          DEFAULT 0          NOT NULL	COMMENT '修改时间',
PRIMARY KEY(id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='节点信息表'
AUTO_INCREMENT=1
ROW_FORMAT=COMPACT;

-- drop table tree_content;
CREATE TABLE tree_content
(
	id                             bigint          NOT NULL AUTO_INCREMENT	COMMENT '标准ID',
	item_id                  bigint          DEFAULT 0          NOT NULL	COMMENT '节点ID',
	content                  text           COMMENT '文案内容',
	lockby                   varchar(1)    DEFAULT '0'         NOT NULL	COMMENT '是否被锁，0否：1是',
	lockby_id              bigint        DEFAULT '0'         NOT NULL	COMMENT '锁定人ID',
	lockby_name            varchar(32)    DEFAULT ''         NOT NULL	COMMENT '锁定人名称：冗余',
	create_datetime                  bigint          DEFAULT 0          NOT NULL	COMMENT '创建时间',
PRIMARY KEY(id)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='节点内容表'
AUTO_INCREMENT=1
ROW_FORMAT=COMPACT;