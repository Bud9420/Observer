drop database if exists observer;
create database observer charset utf8;
use observer;

-- ----------------------------
-- 1.用户
-- ----------------------------
# 企业
create table user_company
(
    id              int primary key auto_increment,
    create_time     datetime            not null,
    update_time     datetime            not null,
    name            varchar(255) unique not null,
    license_path    varchar(255) unique not null,
    legal_person    varchar(255)        not null,
    phone           varchar(255) unique not null,
    company_website varchar(255)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into user_company
values (1, now(), now(), '中国铁路成都局集团有限公司', '/root/software/observer/imgs/company/license/中国石油天然气集团有限公司/license.jpg',
        '冯定清',
        '028-86433080', 'http://www.cd-rail.cn');

# 用户信息
create table user_userinfo
(
    id          int primary key auto_increment,
    create_time datetime            not null,
    update_time datetime            not null,
    username    varchar(255) unique not null,
    password    varchar(255)        not null,
    phone       varchar(255) unique not null,
    head_path   varchar(255) unique not null,
    company_id  int,
    foreign key (company_id) references user_company (id)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into user_userinfo
values (1, now(), now(), 'chenxiaodeng', 'mbkpRtchTZJjoDGeV3NJ+Q==', '19922877297',
        '/root/software/observer/imgs/user/head/chenxiaodeng/chenxiaodeng.jpg', 1);

-- ----------------------------
-- 2.监控
-- ----------------------------
# 监控设备
create table monitor_device
(
    id            int primary key auto_increment,
    create_time   datetime            not null,
    update_time   datetime            not null,
    name          varchar(255)        not null,
    device_serial varchar(255) unique not null,
    channel_no    int                 not null,
    user_id       int                 not null,
    foreign key (user_id) references user_userinfo (id)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into monitor_device
values (1, now(), now(), '重庆北站北广场检票口', 'F82272589', 1, 1);

-- ----------------------------
-- 3.萤石开放平台
-- ----------------------------
# 应用密钥
create table ysopen_secret
(
    id           int primary key auto_increment,
    create_time  datetime     not null,
    update_time  datetime     not null,
    app_key      varchar(255) not null,
    app_secret   varchar(255) not null,
    access_token varchar(255),
    user_id      int unique   not null,
    foreign key (user_id) references user_userinfo (id)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into ysopen_secret
values (1, now(), now(), 'b66e5c4e3a46410e9ae59db9f00394df', '17317702d4275e5c34c3d03f0244df75', null, 1);

-- ----------------------------
-- 4.监控——应用场景：公共场所
-- ----------------------------
# 信息非法的标准
create table monitor_public_standard
(
    id                 int primary key auto_increment,
    create_time        datetime not null,
    update_time        datetime not null,
    # 每个人体框的信息
    loc_height         int,
    loc_width          int,
    loc_left           int,
    loc_top            int,
    # 人体属性
    gender             varchar(255),
    age                varchar(255),
    upper_wear         varchar(255),
    upper_color        varchar(255),
    upper_wear_texture varchar(255),
    upper_wear_fg      varchar(255),
    lower_wear         varchar(255),
    lower_color        varchar(255),
    head_wear          varchar(255),
    glasses            varchar(255),
    bag                varchar(255),
    face_mask          varchar(255),
    orientation        varchar(255),
    cellphone          varchar(255),
    smoke              varchar(255),
    carrying_item      varchar(255),
    umbrella           varchar(255),
    vehicle            varchar(255),
    occlusion          varchar(255),
    upper_cut          varchar(255),
    lower_cut          varchar(255),
    is_human           varchar(255)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into monitor_public_standard
    (id, create_time, update_time, face_mask, smoke, vehicle)
values (1, now(), now(), '无口罩', '吸烟', '骑摩托车,骑自行车,骑三轮车');

# 监控设备与非法标准的联系
create table monitor_public_device_standard
(
    id          int primary key auto_increment,
    create_time datetime      not null,
    update_time datetime      not null,
    device_id   int unique    not null,
    standard_id int default 1 not null,
    foreign key (device_id) references monitor_device (id),
    foreign key (standard_id) references monitor_public_standard (id)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into monitor_public_device_standard
values (1, now(), now(), 1, 1);

# 非法监控图像
create table monitor_public_img
(
    id          int primary key auto_increment,
    create_time datetime                                                                                    not null,
    update_time datetime                                                                                    not null,
    path        varchar(255) unique                                                                         not null,
    # 非法信息处理状态
    status      varchar(255) default 'untreated' check (status in ('untreated', 'processing', 'processed')) not null,
    device_id   int                                                                                         not null,
    foreign key (device_id) references monitor_device (id)
) engine = InnoDB
  charset utf8;

# 产生非法信息的对象—人
create table monitor_public_people
(
    id                 int primary key auto_increment,
    create_time        datetime                                                                              not null,
    update_time        datetime                                                                              not null,
    # 每个人体框的信息
    loc_height         int                                                                                   not null,
    loc_width          int                                                                                   not null,
    loc_left           int                                                                                   not null,
    loc_top            int                                                                                   not null,
    # 人体属性
    gender             varchar(255) check ( gender in ('男性', '女性', '不确定') )                                  not null,
    age                varchar(255) check ( age in ('幼儿', '青少年', '青年', '中年', '老年', '不确定') )                  not null,
    upper_wear         varchar(255) check ( upper_wear in ('长袖', '短袖', '不确定') )                              not null,
    upper_color        varchar(255) check ( upper_color in
                                            ('红', '橙', '黄', '绿', '蓝', '紫', '粉', '黑', '白', '灰', '棕', '不确定') ) not null,
    upper_wear_texture varchar(255) check ( upper_wear_texture in ('纯色', '图案', '碎花', '条纹或格子', '不确定') )       not null,
    upper_wear_fg      varchar(255) check ( upper_wear_fg in
                                            ('T恤', '无袖', '衬衫', '西装', '毛衣', '夹克', '羽绒服', '风衣', '外套', '不确定') ) not null,
    lower_wear         varchar(255) check ( lower_wear in ('长裤', '短裤', '长裙', '短裙', '不确定') )                  not null,
    lower_color        varchar(255) check ( lower_color in
                                            ('红', '橙', '黄', '绿', '蓝', '紫', '粉', '黑', '白', '灰', '棕', '不确定') ) not null,
    head_wear          varchar(255) check ( head_wear in ('无帽', '普通帽', '安全帽', '不确定') )                       not null,
    glasses            varchar(255) check ( glasses in ('戴眼镜', '戴墨镜', '无眼镜', '不确定') )                        not null,
    bag                varchar(255) check ( bag in ('无背包', '单肩包', '双肩包', '不确定') )                            not null,
    face_mask          varchar(255) check ( face_mask in ('无口罩', '戴口罩', '不确定') )                             not null,
    orientation        varchar(255) check ( orientation in ('正面', '背面', '左侧面', '右侧面', '不确定') )               not null,
    cellphone          varchar(255) check ( cellphone in ('未使用手机', '看手机', '打电话', '不确定') )                    not null,
    smoke              varchar(255) check ( smoke in ('吸烟', '未吸烟', '不确定') )                                  not null,
    carrying_item      varchar(255) check ( carrying_item in ('无手提物', '有手提物', '不确定') )                       not null,
    umbrella           varchar(255) check ( umbrella in ('打伞', '未打伞', '不确定') )                               not null,
    vehicle            varchar(255) check ( vehicle in ('无交通工具', '骑摩托车', '骑自行车', '骑三轮车', '不确定') )            not null,
    occlusion          varchar(255) check ( occlusion in ('无遮挡', '轻度遮挡', '重度遮挡', '不确定') )                    not null,
    upper_cut          varchar(255) check ( upper_cut in ('无上方截断', '有上方截断', '不确定') )                         not null,
    lower_cut          varchar(255) check ( lower_cut in ('无下方截断', '有下方截断', '不确定') )                         not null,
    is_human           varchar(255) check ( is_human in ('正常人体', '非正常人体', '不确定') )                           not null,
    img_id             int                                                                                   not null,
    foreign key (img_id) references monitor_public_img (id)
) engine = InnoDB
  charset utf8;

# 非法信息的统计结果，以天为统计单位
create table monitor_public_statis
(
    id             int primary key auto_increment,
    create_time    datetime      not null,
    update_time    datetime      not null,
    date           date          not null,
    total_num      int default 1 not null,
    untreated_num  int default 1 not null,
    processing_num int default 0 not null,
    processed_num  int default 0 not null,
    device_id      int           not null,
    foreign key (device_id) references monitor_device (id)
) engine = InnoDB
  charset utf8;

-- ----------------------------
-- 5.监控——应用场景：驾驶行为
-- ----------------------------
# 信息非法的标准
create table monitor_driving_standard
(
    id                       int primary key auto_increment,
    create_time              datetime not null,
    update_time              datetime not null,
    # 驾驶员的位置
    loc_height               int,
    loc_width                int,
    loc_left                 int,
    loc_top                  int,
    # 驾驶员的行为属性
    smoke                    int,
    cellphone                int,
    not_buckling_up          int,
    both_hands_leaving_wheel int,
    not_facing_front         int,
    no_face_mask             int,
    yawning                  int,
    eyes_closed              int,
    head_lowered             int
) engine = InnoDB
  charset utf8;
# 初始数据
insert into monitor_driving_standard
(id, create_time, update_time, smoke, cellphone, not_buckling_up, both_hands_leaving_wheel, no_face_mask, yawning,
 head_lowered)
values (1, now(), now(), 1, 1, 1, 1, 1, 1, 1);

# 监控设备与非法标准的联系
create table monitor_driving_user_standard
(
    id          int primary key auto_increment,
    create_time datetime      not null,
    update_time datetime      not null,
    device_id   int unique    not null,
    standard_id int default 1 not null,
    foreign key (device_id) references monitor_device (id),
    foreign key (standard_id) references monitor_driving_standard (id)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into monitor_driving_user_standard
values (1, now(), now(), 1, 1);

# 非法监控图像
create table monitor_driving_img
(
    id          int primary key auto_increment,
    create_time datetime                                                           not null,
    update_time datetime                                                           not null,
    path        varchar(255) unique                                                not null,
    # 非法信息处理状态
    status      varchar(255) default '未处理' check (status in ('未处理', '已处理', '处理中')) not null,
    device_id   int                                                                not null,
    foreign key (device_id) references monitor_device (id)
) engine = InnoDB
  charset utf8;

# 产生非法信息的对象—驾驶员
create table monitor_driving_driver
(
    id                       int primary key auto_increment,
    create_time              datetime                                         not null,
    update_time              datetime                                         not null,
    # 驾驶员的位置
    loc_height               int                                              not null,
    loc_width                int                                              not null,
    loc_left                 int                                              not null,
    loc_top                  int                                              not null,
    # 驾驶员的行为属性
    smoke                    int check ( smoke in (0, 1) )                    not null,
    cellphone                int check ( cellphone in (0, 1) )                not null,
    not_buckling_up          int check ( not_buckling_up in (0, 1) )          not null,
    both_hands_leaving_wheel int check ( both_hands_leaving_wheel in (0, 1) ) not null,
    not_facing_front         int check ( not_facing_front in (0, 1) )         not null,
    no_face_mask             int check ( no_face_mask in (0, 1) )             not null,
    yawning                  int check ( yawning in (0, 1) )                  not null,
    eyes_closed              int check ( eyes_closed in (0, 1) )              not null,
    head_lowered             int check ( head_lowered in (0, 1) )             not null,
    img_id                   int                                              not null,
    foreign key (img_id) references monitor_driving_img (id)
) engine = InnoDB
  charset utf8;

# 非法信息的统计结果，以天为统计单位
create table monitor_driving_statis
(
    id             int primary key auto_increment,
    create_time    datetime      not null,
    update_time    datetime      not null,
    date           date          not null,
    total_num      int default 1 not null,
    untreated_num  int default 1 not null,
    processing_num int default 0 not null,
    processed_num  int default 0 not null,
    device_id      int           not null,
    foreign key (device_id) references monitor_device (id)
) engine = InnoDB
  charset utf8;

-- ----------------------------
-- 6.权限
-- ----------------------------
# 角色
create table auth_role
(
    id          int primary key auto_increment,
    create_time datetime            not null,
    update_time datetime            not null,
    name        varchar(255) unique not null
) engine = InnoDB
  charset utf8;
# 插入初始数据
insert into auth_role
values (1, now(), now(), 'monitor_public_user'),
       (2, now(), now(), 'monitor_driving_user');

# 用户与角色的联系
create table auth_user_role
(
    id          int primary key auto_increment,
    create_time datetime not null,
    update_time datetime not null,
    user_id     int      not null,
    role_id     int      not null,
    foreign key (user_id) references user_userinfo (id),
    foreign key (role_id) references auth_role (id)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into auth_user_role
values (1, now(), now(), 1, 1),
       (2, now(), now(), 1, 2);

# 权限
create table auth_permission
(
    id          int primary key auto_increment,
    create_time datetime            not null,
    update_time datetime            not null,
    name        varchar(255) unique not null
) engine = InnoDB
  charset utf8;
insert into auth_permission
values (1, now(), now(), 'monitor_public:insert'),
       (2, now(), now(), 'monitor_public:delete'),
       (3, now(), now(), 'monitor_public:update'),
       (4, now(), now(), 'monitor_public:select'),
       (5, now(), now(), 'monitor_driving:insert'),
       (6, now(), now(), 'monitor_driving:delete'),
       (7, now(), now(), 'monitor_driving:update'),
       (8, now(), now(), 'monitor_driving:select');

# 角色与权限的联系
create table auth_role_permission
(
    id            int primary key auto_increment,
    create_time   datetime not null,
    update_time   datetime not null,
    role_id       int      not null,
    permission_id int      not null,
    foreign key (role_id) references auth_role (id),
    foreign key (permission_id) references auth_permission (id)
) engine = InnoDB
  charset utf8;
# 初始数据
insert into auth_role_permission
values (1, now(), now(), 1, 1),
       (2, now(), now(), 1, 2),
       (3, now(), now(), 1, 3),
       (4, now(), now(), 1, 4),
       (5, now(), now(), 2, 1),
       (6, now(), now(), 2, 2),
       (7, now(), now(), 2, 3),
       (8, now(), now(), 2, 4);

